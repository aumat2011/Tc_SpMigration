CREATE TYPE [dbo].[CHAR_ID_TYPE] FROM [char](1) NULL;
CREATE TYPE [dbo].[YES_NO_TYPE] FROM [char](1) NOT NULL;
CREATE TYPE [dbo].[ID_BIG_TYPE] FROM [numeric](16, 0) NULL;
CREATE TYPE [dbo].[STATE_TYPE] FROM [char](2) NULL;
CREATE TYPE [dbo].[NARCOTIC_CODE_TYPE] FROM [char](10) NULL;
CREATE TYPE [dbo].[DESC_SMALL_TYPE] FROM [varchar](60) NULL;

CREATE TYPE [dbo].[RxEScriptsTableType] AS TABLE
(
    [e_script_msg_attribute_seq] [numeric](35, 0),
    [rx_refills_left] [numeric](7, 0),
    [written_quantity] [numeric](15, 0),
    [dispensed_quantity] [numeric](15, 0),
    [rx_number] [VARCHAR](60),
    [original_num_refills] [numeric](15, 0),
	[num_refills] [numeric](15, 0),
    [fill_status_num] [numeric](2, 0),
    [fill_qty_dispensed] [numeric](15, 0),
    [ortf_qty] [numeric](12, 0),
    [prod_dea] [numeric](1, 0)
);

CREATE TABLE [dbo].[code_value] (
    [code_value_id] [int] NOT NULL,
	[fk_code_cat_id] [char](8) NOT NULL,
	[code_value_keyword] [varchar](200) NOT NULL,
	[code_value_desc] [varchar](200) NOT NULL,
	[status_flag] [char](1) NOT NULL,
	[create_ts] [datetime2](7) NOT NULL,
	[last_updt_ts] [datetime2](7) NOT NULL,
	[last_updt_by] [varchar](50) NOT NULL,
	[restart_required] [char](1) NOT NULL,
	[application_name] [varchar](40) NOT NULL
	PRIMARY KEY CLUSTERED ([code_value_id] ASC)
);

CREATE TABLE [dbo].[products](
	[prod_id] [numeric](16, 0) NOT NULL,
	[prod_name_desc] [varchar](60) NOT NULL,
	[prod_generic_ref_num] [varchar](60) NULL,
	[prod_rx_required] [dbo].[YES_NO_TYPE] NOT NULL,
    [prod_number] [varchar](60) NOT NULL,
    [prod_dea] [numeric](1, 0) NULL,
    [prod_name_type] [char](1) NULL,
    PRIMARY KEY CLUSTERED ([prod_id] ASC)
);

CREATE TABLE [dbo].[trading_partner_types](
	[trading_partner_type_id] [numeric](5, 0) NOT NULL,
	[trading_partner_type_desc] [varchar](60) NOT NULL,
	PRIMARY KEY CLUSTERED ([trading_partner_type_id] ASC)
);

CREATE TABLE [dbo].[trading_partner_general](
	[trading_partner_num] [numeric](16, 0) NOT NULL,
	[trading_partner_type_id] [numeric](5, 0) NOT NULL,
	PRIMARY KEY CLUSTERED ([trading_partner_num] ASC),
	CONSTRAINT trading_partner_general_FK1 FOREIGN KEY ([trading_partner_type_id]) REFERENCES [dbo].[trading_partner_types]([trading_partner_type_id])
);

CREATE TABLE [dbo].[rx_status_codes](
    [rx_status_code_num]  [numeric](2, 0) NOT NULL,
    [rx_status_code_desc] [varchar](30)   NOT NULL,
    PRIMARY KEY CLUSTERED ([rx_status_code_num] ASC)
);

CREATE TABLE [dbo].[e_script_msg_attributes] (
	[e_script_msg_attribute_seq] [numeric](35, 0) NOT NULL,
	[patient_num] [numeric](20, 0) NULL,
	[order_invoice_number] [varchar](60) NULL,
	[dispensed_product_id] [numeric](16, 0) NULL,
	[rx_number] [varchar](60) NULL,
	[edi_transaction_code] [varchar](10) NOT NULL,
	[pwo_item] [char](1) NULL,
	[written_date] [datetime] NULL,
	[rx_expiration_date] [datetime] NULL,
	[written_quantity] [numeric](15, 0) NULL,
	[dispensed_quantity] [numeric](15, 0) NULL,
	[num_refills] [numeric](15, 0) NULL,
	[otc_ppnum] [numeric](16, 0) NULL,
    [original_num_refills] [numeric](15, 0) NULL,
    [rx_status_code_num] [numeric](2, 0) NULL,
    [doctor_num] [numeric](16, 0) NULL,
    [tp_addr_seq] [numeric](16, 0) NULL,
    [trading_partner_num] [numeric](16, 0) NULL,
    [written_product_id] [numeric](16, 0) NULL,
    [dispensed_drug_daw] [char](3) NULL,
    [written_drug_sig] [varchar](255) NULL,
    [dispensed_drug_sig] [varchar](255) NULL,
    [dispensed_product] [varchar](60) NULL,
    [origination_num] [numeric](5, 0) NULL,
	PRIMARY KEY CLUSTERED ([e_script_msg_attribute_seq] ASC),
	CONSTRAINT e_script_msg_attributes_FK_1 FOREIGN KEY ([dispensed_product_id]) REFERENCES [dbo].[products]([prod_id]),
    CONSTRAINT e_script_msg_attributes_FK_2 FOREIGN KEY([doctor_num]) REFERENCES [dbo].[trading_partner_general] ([trading_partner_num]),
    CONSTRAINT e_script_msg_attributes_FK_3 FOREIGN KEY([rx_status_code_num]) REFERENCES [dbo].[rx_status_codes] ([rx_status_code_num]),
    CONSTRAINT e_script_msg_attributes_FK_4 FOREIGN KEY([written_product_id]) REFERENCES [dbo].[products] ([prod_id])
);

CREATE TABLE [dbo].[general_ledger](
	[gl_post_num] [numeric](30, 0) NOT NULL,
	PRIMARY KEY CLUSTERED ([gl_post_num] ASC)
);

CREATE TABLE [dbo].[payment_card_reply](
	[pc_reply_seq_num] [numeric](35, 0) NOT NULL,
	[gl_post_num] [numeric](30, 0) NULL,
	[pc_reply_type] [dbo].[CHAR_ID_TYPE] NOT NULL,
	[pc_reply_auth_code] [varchar](65) NULL,
	PRIMARY KEY CLUSTERED ([pc_reply_seq_num] ASC)
);

CREATE TABLE [dbo].[order_header] (
	[order_num] [numeric](30, 0) NOT NULL,
	[order_status_num] [numeric](2, 0) NULL,
	[order_invoice_number] [varchar](60) NULL,
	[replace_parent_order_num] [numeric](30, 0) NULL,
	[trading_partner_num] [numeric](16, 0) NULL,
	[order_gl_posted] [numeric](30, 0) NULL,
	[order_total_amount] [numeric](12, 0) NULL,
	PRIMARY KEY CLUSTERED ([order_num] ASC),
	CONSTRAINT order_header_FK_1 FOREIGN KEY ([trading_partner_num]) REFERENCES [dbo].[trading_partner_general]([trading_partner_num])
);

CREATE TABLE [dbo].[order_lines] (
	[order_line_num] [numeric](3, 0) NOT NULL,
	[order_num] [numeric](30, 0) NOT NULL,
	[e_script_msg_attribute_seq] [numeric](35, 0) NOT NULL,
	[order_line_status_num] [numeric](2, 0) NULL,
	PRIMARY KEY CLUSTERED ([order_num] ASC, [order_line_num] ASC),
	CONSTRAINT order_lines_FK_1 FOREIGN KEY ([order_num]) REFERENCES [dbo].[order_header]([order_num]),
	CONSTRAINT order_lines_FK_2 FOREIGN KEY ([e_script_msg_attribute_seq]) REFERENCES [dbo].[e_script_msg_attributes]([e_script_msg_attribute_seq])
);

CREATE TABLE [dbo].[rules] (
	[rule_id] [numeric](16, 0) NOT NULL,
	[order_based_rule] [char](1) NULL,
	PRIMARY KEY CLUSTERED ([rule_id] ASC)
);

CREATE TABLE [dbo].[rule_queue_exception] (
	[rule_queue_exception_seq] [numeric](35, 0) NOT NULL,
	[order_num] [numeric](30, 0) NULL,
	[e_script_msg_attribute_seq] [numeric](35, 0) NOT NULL,
	[workflow_step_num] [varchar](60) NULL,
	[workflow_queue_num] [numeric](5, 0) NULL,
	PRIMARY KEY CLUSTERED ([rule_queue_exception_seq] ASC),
	CONSTRAINT rule_queue_exception_FK_1 FOREIGN KEY ([order_num]) REFERENCES [dbo].[order_header]([order_num]),
	CONSTRAINT rule_queue_exception_FK_2 FOREIGN KEY ([e_script_msg_attribute_seq]) REFERENCES [dbo].[e_script_msg_attributes]([e_script_msg_attribute_seq])
);

CREATE TABLE [dbo].[payors](
	[payor_num] [numeric](16, 0) NOT NULL,
	[payor_bill_type_num] [numeric](5, 0) NULL,
	PRIMARY KEY CLUSTERED ([payor_num] ASC)
);

CREATE TABLE [dbo].[payor_plans](
	[pp_num] [numeric](16, 0) NOT NULL,
	[payor_num] [numeric](16, 0) NULL,
	[general_status_code] [char](1) NULL,
	[pp_type_code] [char](1) NULL,
	PRIMARY KEY CLUSTERED ([pp_num] ASC),
	CONSTRAINT payor_plans_FK FOREIGN KEY ([payor_num]) REFERENCES [dbo].[payors]([payor_num])
);

CREATE TABLE [dbo].[ecs_responses](
	[ecs_resp_seq_num] [numeric](30, 0) NOT NULL,
	[e_script_msg_attribute_seq] [numeric](35, 0) NOT NULL,
	[ecs_response_status_type] [char](1) NULL,
    [ecs_resp_date] [datetime] NOT NULL,
	PRIMARY KEY CLUSTERED ([ecs_resp_seq_num] ASC)
);

CREATE TABLE [dbo].[rx_fill_aux] (
	[e_script_msg_attribute_seq] [numeric](35, 0) NOT NULL,
	[fill_status_num] [numeric](2, 0) NOT NULL,
	[fill_precheck_datetime] [datetime] NULL,
	[fill_ecs_status] [numeric](30, 0) NULL,
	[pp_num] [numeric](16, 0) NULL,
    [fill_days_supply] [numeric](5, 0) NULL,
    [conversion_link] [varchar](60) NULL,
    [rx_number] [varchar](60) NULL,
	PRIMARY KEY CLUSTERED ([e_script_msg_attribute_seq] ASC),
	CONSTRAINT rx_fill_aux_FK1 FOREIGN KEY ([e_script_msg_attribute_seq]) REFERENCES [dbo].[e_script_msg_attributes]([e_script_msg_attribute_seq]),
	CONSTRAINT rx_fill_aux_FK2 FOREIGN KEY ([pp_num]) REFERENCES [dbo].[payor_plans]([pp_num])
);

CREATE TABLE [dbo].[rx_defaults] (
	[max_pv_days] [numeric](3, 0) NULL,
	[otc_as_rx_precheck] [char](1) NULL,
	[pwo_precheck] [char](1) NULL,
	[precheck_all] [char](1) NULL,
    [check_narcotic_refills] [char](1) NULL
);

CREATE TABLE [dbo].[benefit_response_codes](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[approval_code] [char](2) NULL,
	PRIMARY KEY CLUSTERED ([id] ASC)
);

CREATE TABLE [dbo].[third_party_claim_requests](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[e_script_msg_attribute_seq] [numeric](17, 0) NULL,
	[transaction_code] [char](4) NULL,
	[transaction_id] [varchar](60) NULL,
	PRIMARY KEY CLUSTERED ([id] ASC)
);

CREATE TABLE [dbo].[third_party_claim_responses](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[transaction_id] [varchar](60) NULL,
	[response_code] [bigint] NULL,
	PRIMARY KEY CLUSTERED ([id] ASC),
	CONSTRAINT third_party_claim_responses_FK FOREIGN KEY ([response_code]) REFERENCES [dbo].[benefit_response_codes]([id])
);

CREATE TABLE [dbo].[patient_general](
	[patient_num] [numeric](20, 0) NOT NULL,
	[patient_dob] [datetime] NOT NULL,
	[general_status_code] [char](1) NOT NULL,
	[family_id] [varchar](60) NULL,
    [patient_id_code] [varchar](3) NULL,
    [patient_id] [varchar](60) NULL,
    [additional_patient_id_type_code] [varchar](3) NULL,
    [additional_patient_id] [varchar](60) NULL,
	PRIMARY KEY CLUSTERED ([patient_num] ASC)
);

CREATE TABLE [dbo].[states](
	[state_code] [dbo].[STATE_TYPE] NULL,
	[state_num] [numeric](5, 0) NOT NULL,
    [state_name] [dbo].[DESC_SMALL_TYPE] NOT NULL,
	PRIMARY KEY CLUSTERED ([state_num] ASC)
);

CREATE TABLE [dbo].[city_state_zip](
	[csz_zip_num] [numeric](16, 0) NOT NULL,
	[state_num] [numeric](5, 0) NOT NULL,
    [state_name] [dbo].[DESC_SMALL_TYPE] NOT NULL,
	PRIMARY KEY CLUSTERED ([csz_zip_num] ASC),
	CONSTRAINT city_state_zip_FK1 FOREIGN KEY ([state_num]) REFERENCES [dbo].[states] ([state_num])
);

CREATE TABLE [dbo].[patient_address](
	[patient_addr_seq] [dbo].[ID_BIG_TYPE] NOT NULL,
	[patient_num] [numeric](20, 0) NOT NULL,
	[active] [char](1) NULL,
    [csz_zip_num] [numeric](16, 0) NOT NULL,
	PRIMARY KEY CLUSTERED ([patient_addr_seq] ASC),
	CONSTRAINT patient_address_FK1 FOREIGN KEY ([patient_num]) REFERENCES [dbo].[patient_general]([patient_num]),
    CONSTRAINT patient_address_FK2 FOREIGN KEY ([csz_zip_num]) REFERENCES [dbo].[city_state_zip] ([csz_zip_num])
);

CREATE TABLE [dbo].[patient_address_type_assignments](
	[patient_num] [numeric](20, 0) NOT NULL,
	[address_type_num] [numeric](5, 0) NOT NULL,
	[patient_addr_seq] [numeric](16, 0) NOT NULL,
	PRIMARY KEY CLUSTERED ([patient_num] ASC, [address_type_num] ASC),
	CONSTRAINT patient_address_type_assignments_FK FOREIGN KEY ([patient_num]) REFERENCES [dbo].[patient_general]([patient_num])
);

CREATE TABLE [dbo].[patient_family](
	[patient_family_seq] [numeric](16, 0) NOT NULL,
	[patient_num] [numeric](20, 0) NULL,
	[family_id] [varchar](60) NULL,
	[head_of_household] [char](1) NULL,
	PRIMARY KEY CLUSTERED ([patient_family_seq] ASC),
	CONSTRAINT patient_family_FK FOREIGN KEY ([patient_num]) REFERENCES [dbo].[patient_general]([patient_num])
);

CREATE TABLE [dbo].[patient_plans](
	[pp_num] [numeric](16, 0) NOT NULL,
	[patient_num] [numeric](20, 0) NOT NULL,
	[coverage_type_id] [numeric](2, 0) NOT NULL,
	[relationship_num] [numeric](5, 0) NULL,
	[cp_deactivation_date] [datetime] NULL,
	[cp_expiration_date] [datetime] NULL,
	PRIMARY KEY CLUSTERED ([pp_num] ASC, [patient_num] ASC),
	CONSTRAINT patient_plans_FK1 FOREIGN KEY ([pp_num]) REFERENCES [dbo].[payor_plans]([pp_num]),
	CONSTRAINT patient_plans_FK2 FOREIGN KEY ([patient_num]) REFERENCES [dbo].[patient_general]([patient_num])
);

CREATE TABLE [dbo].[order_billship](
	[order_num] [numeric](30, 0) NOT NULL,
	[order_payment_owner] [numeric](20, 0) NULL,
	[payment_card_seq_num] [dbo].[ID_BIG_TYPE] NULL,
	[patient_ship_address_seq] [numeric](16, 0) NULL,
	[family_id] [varchar](60) NULL,
    [csz_zip_num] [numeric](16, 0) NULL,
    [patient_address_seq] [numeric](16, 0) NULL,
	[ship_method_id] [numeric](5, 0) NULL,
	PRIMARY KEY CLUSTERED ([order_num] ASC),
	CONSTRAINT order_billship_FK1 FOREIGN KEY ([order_num]) REFERENCES [dbo].[order_header]([order_num]),
	CONSTRAINT order_billship_FK2 FOREIGN KEY ([order_payment_owner]) REFERENCES [dbo].[patient_general]([patient_num]),
    CONSTRAINT order_billship_FK3 FOREIGN KEY([csz_zip_num]) REFERENCES [dbo].[city_state_zip] ([csz_zip_num])
);

CREATE TABLE [dbo].[ok_hydrocodone_sanity_products](
	[GPI] [nvarchar](255) NULL
);

CREATE TABLE [dbo].[state_cs_products](
    [state_cs_product_seq] [numeric](16, 0) NOT NULL,
    [state_num] [numeric](5, 0) NULL,
    [prod_id] [numeric](16, 0) NULL,
    [prod_dea] [numeric](3, 0) NULL,
    PRIMARY KEY CLUSTERED([state_cs_product_seq] ASC),
    CONSTRAINT state_cs_products_FK1 FOREIGN KEY([prod_id]) REFERENCES [dbo].[products] ([prod_id]),
    CONSTRAINT state_cs_products_FK2 FOREIGN KEY([state_num]) REFERENCES [dbo].[states] ([state_num])
);

CREATE TABLE [dbo].[trading_partner_address](
    [trading_partner_num] [numeric](16, 0) NOT NULL,
    [address_type_num] [numeric](5, 0) NULL,
    [tp_addr_seq] [dbo].[ID_BIG_TYPE] NOT NULL,
    [address_desc] [varchar](250) NULL,
    [active] [char](1) NULL,
    [spi] [varchar](60) NULL,
    [external_link] [char](60) NULL,
    [csz_zip_num] [numeric](16, 0) NULL,
    PRIMARY KEY CLUSTERED([tp_addr_seq] ASC),
    CONSTRAINT trading_partner_address_FK1 FOREIGN KEY([trading_partner_num]) REFERENCES [dbo].[trading_partner_general] ([trading_partner_num])
);

CREATE TABLE [dbo].[rx_fill_dur](
    [e_script_msg_attribute_seq] [numeric](35, 0) NULL,
    [dur_resolve_date] [datetime] NULL,
    [dur_seq] [numeric](20, 0) NOT NULL,
    PRIMARY KEY CLUSTERED ([dur_seq] ASC)
);

CREATE TABLE [dbo].[rx_params]
(
    [narcotic_code]           [dbo].[NARCOTIC_CODE_TYPE] NOT NULL,
    [state_num]               [numeric](5, 0)            NOT NULL,
    [num_refills_allowed]     [numeric](5, 0)            NULL,
    [num_days_from_orig_date] [numeric](5, 0)            NULL,
    [num_days_expire]         [numeric](5, 0)            NULL,
    [max_days_supply]         [numeric](5, 0)            NULL,
    [accept_fax]              [char](1)                  NULL,
    PRIMARY KEY CLUSTERED ([state_num] ASC, [narcotic_code] ASC)
);

CREATE TABLE [dbo].[rx_params_gpis]
(
    [gpi_class_id]            [varchar](60)   NOT NULL,
    [state_num]               [numeric](5, 0) NOT NULL,
    [num_refills_allowed]     [numeric](5, 0) NULL,
    [num_days_from_orig_date] [numeric](5, 0) NULL,
    [num_days_expire]         [numeric](5, 0) NULL,
    [max_days_supply]         [numeric](5, 0) NULL,
    [accept_fax]              [char](1)       NULL,
    PRIMARY KEY CLUSTERED ([state_num] ASC, [gpi_class_id] ASC)
);

CREATE TABLE [dbo].[shipping_methods]
(
	[ship_method_id] [numeric](5, 0) NOT NULL,
	[ship_method_desc] [varchar](60) NOT NULL,
    PRIMARY KEY CLUSTERED ([ship_method_id] ASC)
);

CREATE TABLE [dbo].[order_defaults]
(
	[family_mode] [char](1) NULL,
);

CREATE TABLE [dbo].[rx_Multi_Link]
(
	[rx_link_seq] [numeric](16, 0) NOT NULL,
	[parent_rx_number] [varchar](60) NOT NULL,
	[child_rx_number] [varchar](60) NOT NULL,
	[parent_e_script_msg_attribute_seq] [numeric](30, 0) NOT NULL,
	[child_e_script_msg_attribute_seq] [numeric](30, 0) NOT NULL,
	[active] [char](1) NOT NULL,
	[created_sys_user_num] [numeric](16, 0) NULL,
	[created_datetime] [datetime] NULL,
	[deactivated_sys_user_num] [numeric](16, 0) NULL,
	[deactivated_datetime] [datetime] NULL,
	[comment] [varchar](max) NULL,
    PRIMARY KEY CLUSTERED ([rx_link_seq] ASC)
);

CREATE TABLE [dbo].[system_users]
(
	[sys_user_num] [numeric](16, 0) NOT NULL,
    PRIMARY KEY CLUSTERED ([sys_user_num] ASC)
);

CREATE TABLE [dbo].[patient_attributes]
(
	[patient_num] [numeric](20, 0) NOT NULL,
	[prod_consolidate] [char](1) NULL,
    PRIMARY KEY CLUSTERED ([patient_num] ASC)
);

CREATE TABLE [dbo].[quantity_change_tracking]
(
	[e_script_msg_attribute_seq] [numeric](35, 0) NOT NULL,
	[is_quantity_lowered] [char](1) NULL,
    PRIMARY KEY CLUSTERED ([e_script_msg_attribute_seq] ASC)
);

CREATE TABLE [dbo].[auto_escript_filler](
	[auto_efiller_seq] [numeric](30, 0) NOT NULL,
	[escript_id] [numeric](35, 0) NULL,
	[order_num] [numeric](30, 0) NULL,
    PRIMARY KEY CLUSTERED ([auto_efiller_seq] ASC)
);

CREATE TABLE [dbo].[rx_link_substitution_log]
(
	[rx_link_substituion_log_seq] [numeric](35, 0) NOT NULL,
	[original_rx_number] [varchar](60) NULL,
	[substituted_rx_number] [varchar](60) NULL,
	[original_written_prod_name] [varchar](60) NULL,
	[original_dispensed_prod_name] [varchar](60) NULL,
	[substituted_written_prod_name] [varchar](60) NULL,
	[substituted_dispensed_prod_name] [varchar](60) NULL,
	[order_number] [numeric](35, 0) NULL,
	[User_num] [numeric](35, 0) NULL,
	[type_of_action] [varchar](500) NULL,
	[Log_Date] [datetime] NULL,
    PRIMARY KEY CLUSTERED ([rx_link_substituion_log_seq] ASC)
);

CREATE TABLE [dbo].[sig_groups](
    [sig] [varchar](255) NOT NULL,
    [sig_group] [int] NOT NULL,
    PRIMARY KEY CLUSTERED ([sig] ASC)
);

CREATE TABLE [dbo].[rx_Link_Dcco_GPI_List](
    [GPI] [varchar](60) NULL
);

CREATE TABLE [dbo].[rx_link_logging](
    [rx_link_log_seq] [numeric](35, 0) NOT NULL,
    [parent_rx_number] [varchar](60) NULL,
    [parent_e_script_msg_attr_seq] [numeric](35, 0) NULL,
    [child_rx_number] [varchar](60) NULL,
    [child_e_script_msg_attr_seq] [numeric](35, 0) NULL,
    [ACTION] [varchar](500) NULL,
    [LOG_DATETIME] [datetime2](7) NULL,
    [LOG_USER] [numeric](10, 0) NULL,
    PRIMARY KEY CLUSTERED ([rx_link_log_seq] ASC)
);

CREATE SEQUENCE [dbo].[s_rx_link_logging_seq]
    AS [bigint]
    START WITH 500
    INCREMENT BY 1
    MINVALUE -9223372036854775808
    MAXVALUE 9223372036854775807
    CACHE
GO

CREATE SEQUENCE [dbo].[S_rx_link_seq]
    AS [bigint]
    START WITH 39827160
    INCREMENT BY 1
    MINVALUE -9223372036854775808
    MAXVALUE 9223372036854775807
    CACHE
GO