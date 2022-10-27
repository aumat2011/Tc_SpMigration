/****** Object:  UserDefinedDataType [dbo].[ID_BIG_TYPE]    ******/
CREATE TYPE [dbo].[ID_BIG_TYPE] FROM [numeric](16, 0) NULL
    GO
/****** Object:  UserDefinedDataType [dbo].[YES_NO_TYPE]    ******/
CREATE TYPE [dbo].[YES_NO_TYPE] FROM [char](1) NOT NULL
    GO
/****** Object:  UserDefinedDataType [dbo].[ZIP_CODE_FOUR_TYPE] ******/
CREATE TYPE [dbo].[ZIP_CODE_FOUR_TYPE] FROM [numeric](4, 0) NULL
    GO
/****** Object:  UserDefinedDataType [dbo].[CHAR_ID_TYPE]  ******/
CREATE TYPE [dbo].[CHAR_ID_TYPE] FROM [char](1) NULL
    GO
/****** Object:  UserDefinedDataType [dbo].[STATE_TYPE]  ******/
CREATE TYPE [dbo].[STATE_TYPE] FROM [varchar](4) NULL
    GO
/****** Object:  UserDefinedDataType [dbo].[DESC_SMALL_TYPE]  ******/
CREATE TYPE [dbo].[DESC_SMALL_TYPE] FROM [varchar](30) NULL
    GO
/****** Object:  UserDefinedDataType [dbo].[CODE_SMALL_TYPE]  ******/
CREATE TYPE [dbo].[CODE_SMALL_TYPE] FROM [char](2) NULL
    GO
/****** Object:  UserDefinedDataType [dbo].[ADDRESS_TYPE]  ******/
CREATE TYPE [dbo].[ADDRESS_TYPE] FROM [varchar](60) NULL
    GO
/****** Object:  UserDefinedDataType [dbo].[YES_NO_UNKNOWN_TYPE]  ******/
CREATE TYPE [dbo].[YES_NO_UNKNOWN_TYPE] FROM [char](1) NULL
    GO
/****** Object:  UserDefinedDataType [dbo].[NARCOTIC_CODE_TYPE]  ******/
CREATE TYPE [dbo].[NARCOTIC_CODE_TYPE] FROM [char](10) NULL
    GO
/****** Object:  UserDefinedDataType [dbo].[PERCENT_TYPE]  ******/
CREATE TYPE [dbo].[PERCENT_TYPE] FROM [numeric](5, 0) NULL
    GO
/****** Object:  Table [dbo].[products] ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[products](
    [prod_id] [numeric](16, 0) NOT NULL,
    [prod_name] [varchar](60) NULL,
    [product_service_id] [char](2) NULL,
    [prod_active] [char](1) NOT NULL,
    [prod_rx_required] [char](1) NOT NULL,
    [prod_name_desc] [varchar](60) NOT NULL,
    [prod_reference_num] [varchar](60) NULL,
    [prod_strength] [varchar](15) NULL,
    [prod_strength_unit] [varchar](15) NULL,
    [prod_package_size] [numeric](11, 0) NULL,
    [prod_unit_of_use] [char](1) NOT NULL,
    [prod_dea] [numeric](1, 0) NULL,
    [dosage_form_id] [char](2) NULL,
    [prod_name_type] [char](1) NULL,
    [universal_product_id] [varchar](60) NULL,
    [prod_generic_ref_num] [varchar](60) NULL,
    [prod_form_type_code] [char](1) NULL,
    [prod_attributes] [varchar](1500) NULL,
    [prod_number] [varchar](60) NOT NULL,
    [prod_packsize_unitcode] [varchar](15) NULL,
    [prod_awp_defaultprice] [numeric](10, 0) NULL,
    [minimum_product_margin] [numeric](12, 0) NULL,
    [conversion_link] [varchar](60) NULL,
    [prod_compound] [char](1) NOT NULL,
    [prod_def_package_size] [numeric](12, 0) NULL,
    [drug_maker] [varchar](60) NULL,
    [prod_formatted_id] [varchar](60) NULL,
    [prod_type_num] [numeric](5, 0) NULL,
    [prod_default_sig] [varchar](255) NULL,
    [prod_default_quantity] [numeric](5, 0) NULL,
    [prod_default_days] [numeric](5, 0) NULL,
    [package_quantity] [numeric](5, 0) NULL,
    [prod_drug_awp_price] [numeric](12, 0) NULL,
    [prod_drug_awp_unit_price] [numeric](12, 0) NULL,
    [compound_form_code] [varchar](35) NULL,
    [compound_admin_route] [numeric](5, 0) NULL,
    [qty_per_label] [numeric](5, 0) NULL,
    [product_color] [varchar](60) NULL,
    [product_shape] [varchar](60) NULL,
    [product_imprint] [varchar](255) NULL,
    [prod_awp_defaultunitprice] [numeric](12, 0) NULL,
    [compound_unit_form] [numeric](1, 0) NULL,
    [prod_default_prescribe_reason] [varchar](255) NULL,
    [prod_default_packqty] [numeric](5, 0) NULL,
    [sigis_id] [numeric](20, 0) NULL,
    [multi_source_code] [varchar](3) NULL,
    [speciality_product] [char](1) NULL,
    [storage_code] [varchar](60) NULL,
    [last_update] [datetime] NULL,
    [review_product] [char](1) NULL,
    [data_collection] [char](1) NULL,
    [compound_type] [char](2) NULL,
    [prod_admin_route] [varchar](60) NULL,
    [drugdb_inactive_date] [varchar](60) NULL,
    [dme_product] [char](1) NULL,
    [hrm_product] [char](1) NULL,
    [sound_alike] [varchar](500) NULL,
    [prod_consolidate] [char](1) NULL,
    [prod_default_drug_wacunit] [numeric](12, 0) NULL,
    [prod_drug_wacunit] [numeric](7, 0) NULL,
    [myb_product] [char](1) NULL,
    [manalloc_product] [char](1) NULL,
    [backorder_product] [char](1) NULL,
    [prod_handling_seq1] [numeric](7, 0) NULL,
    [prod_handling_seq2] [numeric](7, 0) NULL,
    [prod_handling_seq3] [numeric](7, 0) NULL,
    [prod_handling_seq4] [numeric](7, 0) NULL,
    [date_added] [datetime] NULL,
    [prod_tee_code] [varchar](60) NULL,
    [potency_unit_code] [varchar](60) NULL,
    [cs_potency_unit_code] [varchar](60) NULL,
    [autotestfs] [char](1) NULL,
    [rowversion] [timestamp] NOT NULL,
    [hrm_active] [char](1) NULL,
    [hrm_eligible] [char](1) NULL,
    [Special_tee_code] [varchar](60) NULL,
    PRIMARY KEY CLUSTERED
(
[prod_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[order_header]******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[order_header](
    [order_num] [numeric](30, 0) NOT NULL,
    [trading_partner_num] [numeric](16, 0) NULL,
    [workflow_num] [numeric](5, 0) NULL,
    [order_status_num] [numeric](2, 0) NULL,
    [origination_num] [numeric](5, 0) NULL,
    [sys_user_num] [numeric](16, 0) NULL,
    [order_date] [datetime] NOT NULL,
    [order_amount] [numeric](12, 0) NULL,
    [order_tax] [numeric](12, 0) NULL,
    [order_high_priority] [dbo].[YES_NO_TYPE] NOT NULL,
    [order_total_amount] [numeric](12, 0) NULL,
    [order_invoice_number] [varchar](60) NULL,
    [order_total_payments] [numeric](12, 0) NULL,
    [order_gl_posted] [numeric](30, 0) NULL,
    [order_picked] [dbo].[YES_NO_TYPE] NOT NULL,
    [date_processed] [datetime] NULL,
    [return_datetime] [datetime] NULL,
    [replace_datetime] [datetime] NULL,
    [order_auto_filled] [numeric](1, 0) NULL,
    [order_validated] [dbo].[YES_NO_TYPE] NOT NULL,
    [quote_datetime] [datetime] NULL,
    [suborder_validated] [char](1) NULL,
    [order_print] [char](1) NULL,
    [cc_busy] [numeric](1, 0) NULL,
    [notified] [datetime] NULL,
    [order_patient_validated] [char](1) NULL,
    [return_shipping_cost] [numeric](1, 0) NULL,
    [sanityok] [numeric](1, 0) NULL,
    [batchecs] [numeric](1, 0) NULL,
    [conversion_link] [varchar](60) NULL,
    [workflow_complete] [datetime] NULL,
    [order_tote_number] [varchar](60) NULL,
    [post_edit] [char](1) NULL,
    [order_dur] [numeric](1, 0) NULL,
    [pickup_delivery_methods_seq] [numeric](5, 0) NULL,
    [locked] [numeric](1, 0) NULL,
    [external_review] [char](1) NULL,
    [sanityok2] [numeric](1, 0) NULL,
    [intake_fill_location] [varchar](60) NULL,
    [intake_complete] [datetime] NULL,
    [hippa_validated] [char](1) NULL,
    [ar_validated] [char](1) NULL,
    [current_balance] [numeric](12, 0) NULL,
    [induct_datetime] [datetime] NULL,
    [pending_shipment] [numeric](1, 0) NULL,
    [auto_ship_complete] [char](1) NULL,
    [cycle_fill_run_number] [varchar](60) NULL,
    [need_by_date] [datetime] NULL,
    [order_type] [varchar](60) NULL,
    [private_label] [char](1) NULL,
    [auto_split] [char](1) NULL,
    [sanityok3] [numeric](1, 0) NULL,
    [auto_route] [char](1) NULL,
    [auto_dispense_location] [numeric](16, 0) NULL,
    [auto_workflow_type_num] [numeric](7, 0) NULL,
    [cancel_datetime] [datetime] NULL,
    [cancel_usernum] [numeric](16, 0) NULL,
    [date_received] [datetime] NULL,
    [dis_recovery] [char](1) NULL,
    [replace_parent_order_num] [numeric](30, 0) NULL,
    [merge_parent_order_num] [numeric](30, 0) NULL,
    [ext_srcinfo] [varchar](255) NULL,
    [external_review_datetime] [datetime] NULL,
    [auto_error] [numeric](16, 0) NULL,
    [replace_review_datetime] [datetime] NULL,
    [replace_review_usernum] [numeric](16, 0) NULL,
    [return_review_datetime] [datetime] NULL,
    [return_review_usernum] [numeric](16, 0) NULL,
    [auto_rts_split] [char](1) NULL,
    [auto_cancel] [char](1) NULL,
    [pwo_checked] [char](1) NULL,
    [source_host] [varchar](60) NULL,
    [source_user] [varchar](60) NULL,
    [rowversion] [timestamp] NOT NULL,
    [order_split_date] [datetime] NULL,
    [is_myb_order] [char](1) NULL,
    [salestax_calculated] [char](1) NULL,
    [basket_number] [varchar](40) NULL,
    [order_oft_validated] [char](1) NULL,
    [finance_validated] [varchar](1) NULL,
    [specialty_validated] [bit] NULL,
    PRIMARY KEY CLUSTERED
(
[order_num] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[code_value] ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[code_value](
    [code_value_id] [int] NOT NULL,
    [fk_code_cat_id] [char](8) NOT NULL,
    [code_value_keyword] [varchar](200) NOT NULL,
    [code_value_desc] [varchar](200) NOT NULL,
    [status_flag] [char](1) NOT NULL,
    [create_ts] [datetime2](7) NOT NULL,
    [last_updt_ts] [datetime2](7) NOT NULL,
    [last_updt_by] [varchar](50) NOT NULL,
    [restart_required] [char](1) NOT NULL,
    [application_name] [varchar](40) NOT NULL,
    CONSTRAINT [pk_code_value] PRIMARY KEY CLUSTERED
(
[code_value_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[rule_queue_exception] ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[rule_queue_exception](
    [rule_queue_exception_seq] [numeric](35, 0) NOT NULL,
    [under_review_by_user] [numeric](16, 0) NULL,
    [workflow_queue_num] [numeric](5, 0) NULL,
    [rule_exception_datetime] [datetime] NOT NULL,
    [available] [dbo].[YES_NO_TYPE] NOT NULL,
    [rule_exception_link] [numeric](35, 0) NULL,
    [workflow_txn_id] [numeric](30, 0) NULL,
    [e_script_msg_attribute_seq] [numeric](35, 0) NOT NULL,
    [workflow_step] [varchar](60) NULL,
    [workflow_step_num] [varchar](60) NULL,
    [rule_engine_id] [numeric](32, 0) NULL,
    [order_num] [numeric](30, 0) NULL,
    [outbound_fax_seq] [numeric](25, 0) NULL,
    [rx_number] [varchar](60) NULL,
    [rule_exception_text] [varchar](3500) NULL,
    [rule_exception_script_text] [varchar](2500) NULL,
    [rule_exception_context] [varchar](60) NULL,
    [rule_exception_context_rfc] [varchar](60) NULL,
    [rule_exception_seqnum] [varchar](60) NULL,
    [rule_exception_keynum] [varchar](60) NULL,
    [rule_exception_keynum1] [varchar](60) NULL,
    [rule_exception_keynum2] [varchar](60) NULL,
    [rule_exception_soft] [char](1) NULL,
    [rule_queue_transfer_seq] [numeric](30, 0) NULL,
    [rule_exception_subcontext] [varchar](60) NULL,
    PRIMARY KEY CLUSTERED
(
[rule_queue_exception_seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 70) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[e_script_msg_attributes] ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[e_script_msg_attributes](
    [e_script_msg_attribute_seq] [numeric](35, 0) NOT NULL,
    [free_text] [varchar](250) NULL,
    [patient_care_order_num] [numeric](20, 0) NULL,
    [coupon_num] [numeric](5, 0) NULL,
    [doctor_num] [numeric](16, 0) NULL,
    [patient_num] [numeric](20, 0) NULL,
    [origination_num] [numeric](5, 0) NULL,
    [rx_status_code_num] [numeric](2, 0) NULL,
    [sys_user_num] [numeric](16, 0) NOT NULL,
    [written_product] [varchar](120) NULL,
    [written_product_id] [numeric](16, 0) NULL,
    [written_quantity] [numeric](15, 0) NULL,
    [doctor_first_name] [varchar](60) NULL,
    [doctor_last_name] [varchar](60) NULL,
    [written_drug_form_desc] [varchar](60) NULL,
    [patient_first_name] [varchar](60) NULL,
    [patient_last_name] [varchar](60) NULL,
    [doctor_middle_name] [varchar](60) NULL,
    [written_product_number] [varchar](35) NULL,
    [written_drug_form] [char](3) NULL,
    [doctor_contact_number] [varchar](80) NULL,
    [dispensed_product_id] [numeric](16, 0) NULL,
    [doctor_contact_type_desc] [varchar](35) NULL,
    [written_drug_sig_code] [varchar](35) NULL,
    [written_drug_sig_freq] [numeric](12, 0) NULL,
    [written_drug_item_agency] [varchar](3) NULL,
    [doctor_contact_type] [char](3) NULL,
    [written_drug_sig] [varchar](255) NULL,
    [dispensed_date] [datetime] NULL,
    [doctor_practice_name] [varchar](60) NULL,
    [written_drug_strength] [varchar](70) NULL,
    [dispensed_drug_form] [char](3) NULL,
    [dispensed_drug_form_desc] [varchar](60) NULL,
    [doctor_address] [varchar](60) NULL,
    [num_refills] [numeric](15, 0) NULL,
    [dispensed_drug_strength] [varchar](70) NULL,
    [dispensed_drug_sig] [varchar](255) NULL,
    [dispensed_drug_item_agency] [varchar](3) NULL,
    [doctor_city] [varchar](35) NULL,
    [doctor_state] [varchar](9) NULL,
    [dispensed_drug_daw] [char](3) NULL,
    [original_num_refills] [numeric](15, 0) NULL,
    [doctor_id_type_desc] [varchar](35) NULL,
    [dispensed_drug_daw_desc] [varchar](60) NULL,
    [written_date] [datetime] NULL,
    [doctor_zip] [varchar](11) NULL,
    [patient_dob] [datetime] NULL,
    [written_drug_daw] [char](3) NULL,
    [last_refill_date] [datetime] NULL,
    [doctor_id] [varchar](35) NULL,
    [patient_id] [varchar](35) NULL,
    [patient_contact_type_desc] [varchar](35) NULL,
    [patient_middle_name] [varchar](60) NULL,
    [doctor_id_type] [char](3) NULL,
    [patient_contact_number] [varchar](80) NULL,
    [dispensed_product] [varchar](60) NULL,
    [patient_contact_type] [char](3) NULL,
    [dispensed_quantity] [numeric](15, 0) NULL,
    [patient_id_type_desc] [varchar](35) NULL,
    [patient_id_type] [char](3) NULL,
    [patient_gender] [char](1) NULL,
    [patient_address] [varchar](60) NULL,
    [patient_city] [varchar](35) NULL,
    [patient_state] [varchar](9) NULL,
    [patient_zip] [varchar](11) NULL,
    [dispensed_product_number] [varchar](35) NULL,
    [written_drug_daw_desc] [varchar](60) NULL,
    [refill_status_response] [char](1) NULL,
    [refill_status_response_desc] [varchar](35) NULL,
    [edi_message_id] [numeric](35, 0) NOT NULL,
    [edi_transaction_code] [varchar](10) NOT NULL,
    [pharmacy_name] [varchar](60) NULL,
    [pharmacist_first_name] [varchar](60) NULL,
    [patient_cardholder_id] [varchar](60) NULL,
    [patient_cardholder_name] [varchar](60) NULL,
    [pharmacist_middle_name] [varchar](60) NULL,
    [pharmacist_last_name] [varchar](60) NULL,
    [patient_group_number] [varchar](35) NULL,
    [patient_group_name] [varchar](60) NULL,
    [pharmacy_contact_number] [varchar](80) NULL,
    [pharmacy_contact_type] [char](3) NULL,
    [pharmacy_contact_type_desc] [varchar](60) NULL,
    [pharmacy_address] [varchar](60) NULL,
    [pharmacy_city] [varchar](60) NULL,
    [pharmacy_state] [varchar](9) NULL,
    [pharmacy_zip] [varchar](11) NULL,
    [pharmacy_id] [varchar](60) NULL,
    [pharmacy_id_type] [char](3) NULL,
    [pharmacy_id_type_desc] [varchar](60) NULL,
    [rx_number] [varchar](60) NULL,
    [rx_expiration_date] [datetime] NULL,
    [rx_qty_left] [numeric](25, 0) NULL,
    [rx_refills_left] [numeric](7, 0) NULL,
    [order_invoice_number] [varchar](60) NULL,
    [image_available] [dbo].[YES_NO_TYPE] NOT NULL,
    [script_legible] [dbo].[YES_NO_TYPE] NOT NULL,
    [previous_rx_number] [varchar](60) NULL,
    [trading_partner_num] [numeric](16, 0) NULL,
    [clone_rx] [dbo].[YES_NO_TYPE] NOT NULL,
    [auto_refill] [dbo].[YES_NO_TYPE] NOT NULL,
    [patient_pet_seq] [numeric](16, 0) NULL,
    [cycle_fill_run_number] [varchar](60) NULL,
    [conversion_link] [varchar](60) NULL,
    [allocated] [numeric](12, 0) NULL,
    [shipper_pickup_datetime] [datetime] NULL,
    [pwo_item] [char](1) NULL,
    [pwo_exp_date] [datetime] NULL,
    [pat_pay_schedule_seq] [numeric](30, 0) NULL,
    [sys_user_task_id] [numeric](16, 0) NULL,
    [otc_ppnum] [numeric](16, 0) NULL,
    [erx_id] [varchar](60) NULL,
    [tp_addr_seq] [numeric](16, 0) NULL,
    [image_triage_seq] [numeric](30, 0) NULL,
    [erx_po] [varchar](60) NULL,
    [source_host] [varchar](60) NULL,
    [source_user] [varchar](60) NULL,
    [rowversion] [timestamp] NOT NULL,
    [patient_address2] [varchar](35) NULL,
    [erx_msgid] [varchar](60) NULL,
    [outreach_medication] [varchar](120) NULL,
    [DateLastFilled] [datetime] NULL,
    [DateFirstFilled] [datetime] NULL,
    [copay_consent_flag] [char](1) NULL,
    [is_medsync_queue] [char](1) NULL,
    [is_mdp_queue] [char](1) NULL,
    PRIMARY KEY CLUSTERED
(
[e_script_msg_attribute_seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[order_lines]  ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[order_lines](
    [order_line_num] [numeric](35, 0) NOT NULL,
    [order_num] [numeric](30, 0) NOT NULL,
    [e_script_msg_attribute_seq] [numeric](35, 0) NOT NULL,
    [replace_reason_num] [numeric](5, 0) NULL,
    [return_reason_num] [numeric](5, 0) NULL,
    [order_suborder_seq] [numeric](16, 0) NULL,
    [order_line_status_num] [numeric](2, 0) NULL,
    [order_line_type_num] [numeric](2, 0) NOT NULL,
    [order_line_creation_datetime] [datetime] NULL,
    [order_line_qty] [numeric](15, 0) NULL,
    [order_line_unit_price] [numeric](12, 0) NULL,
    [order_line_amount] [numeric](12, 0) NULL,
    [order_line_tax] [numeric](12, 0) NULL,
    [order_line_total_amt] [numeric](12, 0) NULL,
    [split_order_num] [numeric](20, 0) NULL,
    [split_order_line_num] [numeric](3, 0) NULL,
    [return_replace_quantity] [numeric](15, 0) NULL,
    [order_split_reason_num] [numeric](5, 0) NULL,
    [order_line_gen_savings] [numeric](12, 0) NULL,
    [order_line_inv_status] [numeric](3, 0) NULL,
    [order_manualtest_datetime] [datetime] NULL,
    [order_manualtest_user] [numeric](16, 0) NULL,
    [order_shippack_datetime] [datetime] NULL,
    [order_shippack_user] [numeric](16, 0) NULL,
    [return_replace_datetime] [datetime] NULL,
    [return_replace_user] [numeric](16, 0) NULL,
    [return_replace_ecs] [char](1) NOT NULL,
    [return_replace_inventory] [char](1) NOT NULL,
    [return_replace_payment_card] [char](1) NOT NULL,
    [return_replace_amount] [numeric](12, 0) NULL,
    [order_manualqa_datetime] [datetime] NULL,
    [return_replace_total_amt] [numeric](12, 0) NULL,
    [order_manualqa_user] [numeric](16, 0) NULL,
    [return_replace_gl] [numeric](30, 0) NULL,
    [order_manual_pack_user] [numeric](16, 0) NULL,
    [order_manual_pack_datetime] [datetime] NULL,
    [order_precheck_datetime] [datetime] NULL,
    [order_precheck_user] [numeric](16, 0) NULL,
    [care_kit] [varchar](60) NULL,
    [expiration_date] [datetime] NULL,
    [lot_number] [varchar](60) NULL,
    [lot_number2] [varchar](60) NULL,
    [lot_number3] [varchar](60) NULL,
    [intake_user] [numeric](16, 0) NULL,
    [intake_datetime] [datetime] NULL,
    [expiration_date2] [datetime] NULL,
    [expiration_date3] [datetime] NULL,
    [fill_mode] [varchar](60) NULL,
    [order_line_quote] [numeric](12, 0) NULL,
    [order_line_wac_unit_price] [numeric](15, 0) NULL,
    [order_line_quote_datetime] [datetime] NULL,
    [return_replace_zero_price] [char](1) NULL,
    [city_tax] [numeric](12, 0) NULL,
    [state_tax] [numeric](12, 0) NULL,
    [county_tax] [numeric](12, 0) NULL,
    [cancel_datetime] [datetime] NULL,
    [cancel_usernum] [numeric](16, 0) NULL,
    [replace_review_datetime] [datetime] NULL,
    [replace_review_usernum] [numeric](16, 0) NULL,
    [return_review_datetime] [datetime] NULL,
    [return_review_usernum] [numeric](16, 0) NULL,
    [replace_review_status] [varchar](60) NULL,
    [return_review_status] [varchar](60) NULL,
    [return_inventory_location] [numeric](30, 0) NULL,
    [split_order_usernum] [numeric](16, 0) NULL,
    [split_order_datetime] [datetime] NULL,
    [order_line_tax_rate] [varchar](20) NULL,
    [estimated_line_tax] [numeric](12, 0) NULL,
    [original_order_line_amount] [numeric](12, 0) NULL,
    [copay_waived] [char](1) NULL,
    [product_flat_sales_tax_submitted] [numeric](12, 0) NULL,
    [OrderLineItemTypeID] [int] NULL,
    [order_line_checked] [numeric](5, 0) NULL,
    [routing_tp_num] [numeric](16, 0) NULL,
    [benefit_month] [varchar](10) NULL,
    PRIMARY KEY CLUSTERED
(
[order_line_num] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 70) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[rx_defaults]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[rx_defaults](
    [general_status_code] [char](1) NULL,
    [rx_status_code_num] [numeric](2, 0) NULL,
    [rx_hold_reason_num] [numeric](5, 0) NULL,
    [patient_location_id] [numeric](2, 0) NULL,
    [note_type_id] [numeric](5, 0) NULL,
    [origination_num] [numeric](5, 0) NULL,
    [daw_code] [numeric](5, 0) NULL,
    [rx_def_auto_refill] [dbo].[YES_NO_TYPE] NOT NULL,
    [days_past_expiration] [numeric](5, 0) NULL,
    [refill_by_utilization] [numeric](5, 0) NULL,
    [num_refills] [numeric](5, 0) NULL,
    [auto_refiller_delta] [numeric](5, 0) NULL,
    [otc_as_rx_precheck] [char](1) NULL,
    [otc_as_rx_postcheck] [char](1) NULL,
    [autosend_drfax] [char](1) NULL,
    [auto_fill_mode] [char](1) NULL,
    [check_dupes] [char](1) NULL,
    [show_expiration] [numeric](5, 0) NULL,
    [include_gpi] [char](1) NULL,
    [ecs_logging] [char](1) NULL,
    [generic_link_type] [char](1) NULL,
    [speciality_delta] [numeric](5, 0) NULL,
    [max_sig_label] [numeric](5, 0) NULL,
    [generic_loss] [numeric](15, 0) NULL,
    [brand_loss] [numeric](15, 0) NULL,
    [copay_differential] [numeric](12, 0) NULL,
    [force_product_selection] [char](1) NULL,
    [autorefill_cc] [char](1) NULL,
    [pwo_precheck] [char](1) NULL,
    [pwo_postcheck] [char](1) NULL,
    [show_quantity] [char](1) NULL,
    [advance_precheck] [char](1) NULL,
    [include_teecode] [char](1) NULL,
    [allow_consolidate] [char](1) NULL,
    [check_narcotic_refills] [char](1) NULL,
    [hrm_age] [numeric](2, 0) NULL,
    [precheck_all] [char](1) NULL,
    [icd_mode] [varchar](10) NULL,
    [max_pv_days] [numeric](3, 0) NULL
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[rx_fill_aux] ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[rx_fill_aux](
    [e_script_msg_attribute_seq] [numeric](35, 0) NOT NULL,
    [pp_num] [numeric](16, 0) NULL,
    [patient_location_id] [numeric](2, 0) NULL,
    [secondary_pp_num] [numeric](16, 0) NULL,
    [fill_status_num] [numeric](2, 0) NOT NULL,
    [rx_refill_num] [numeric](10, 0) NOT NULL,
    [relationship_num] [numeric](5, 0) NOT NULL,
    [fill_dispense_date] [datetime] NULL,
    [fill_qty_dispensed] [numeric](15, 0) NULL,
    [fill_days_supply] [numeric](5, 0) NULL,
    [fill_ecs_status] [numeric](30, 0) NULL,
    [calculated_days_supply] [numeric](5, 0) NULL,
    [fill_ecs_override_date] [datetime] NULL,
    [tp_gl_posted] [numeric](30, 0) NULL,
    [fill_precheck_rph_initials] [varchar](15) NULL,
    [patient_copay] [numeric](12, 0) NULL,
    [fill_postcheck_rph_initials] [varchar](15) NULL,
    [fill_rx_rph_initials] [varchar](15) NULL,
    [fill_precheck_datetime] [datetime] NULL,
    [fill_postcheck_datetime] [datetime] NULL,
    [post_edit_rx] [dbo].[YES_NO_TYPE] NOT NULL,
    [date_processed] [datetime] NULL,
    [retail_sold_datetime] [datetime] NULL,
    [tp_gl_credited] [numeric](30, 0) NULL,
    [fill_inv_status] [numeric](3, 0) NULL,
    [auto_fill_bin] [varchar](60) NULL,
    [conversion_link] [varchar](60) NULL,
    [prescribing_reason] [varchar](60) NULL,
    [refill_by_date] [datetime] NULL,
    [fill_expiration_date] [datetime] NULL,
    [calculated_plan_price] [numeric](12, 0) NULL,
    [external_link] [varchar](60) NULL,
    [clinic_id] [varchar](60) NULL,
    [triplicate_serial_num] [varchar](60) NULL,
    [ecs_busy] [numeric](1, 0) NULL,
    [rx_unit_price] [numeric](12, 0) NULL,
    [fill_cob_status] [numeric](30, 0) NULL,
    [fill_auto_status] [numeric](1, 0) NULL,
    [fill_discard_date] [datetime] NULL,
    [pc_diagnosis_num] [numeric](16, 0) NULL,
    [sanityok] [numeric](1, 0) NULL,
    [fill_ship_date] [datetime] NULL,
    [workflow_complete] [datetime] NULL,
    [send_fax] [char](1) NULL,
    [rx_link] [numeric](35, 0) NULL,
    [batch_ecs] [numeric](1, 0) NULL,
    [calculated_inv_price] [numeric](12, 0) NULL,
    [awp_price] [numeric](12, 0) NULL,
    [awp_unit_price] [numeric](12, 0) NULL,
    [cob_check] [numeric](1, 0) NULL,
    [fill_cob3_status] [numeric](30, 0) NULL,
    [comp_prod_seq] [numeric](16, 0) NULL,
    [items_dispensed] [numeric](5, 0) NULL,
    [payor_paid_amt] [numeric](12, 0) NULL,
    [rx_number] [varchar](60) NULL,
    [translated_sig] [nvarchar](1000) NULL,
    [translated_datetime] [datetime] NULL,
    [translated_initials] [varchar](10) NULL,
    [number_metric_items] [numeric](5, 0) NULL,
    [sanityok2] [numeric](1, 0) NULL,
    [metric_quantity] [numeric](12, 0) NULL,
    [ingredient_paid_amt] [numeric](12, 0) NULL,
    [calculated_inv_wac_price] [numeric](12, 0) NULL,
    [rx_wac_unit_price] [numeric](12, 0) NULL,
    [validated] [numeric](1, 0) NULL,
    [ortf_qty] [numeric](12, 0) NULL,
    [sanityok3] [numeric](1, 0) NULL,
    [ecsclar_metric_quantity] [numeric](8, 3) NULL,
    [rxoverride] [numeric](1, 0) NULL,
    [initial_fill_qty] [numeric](5, 0) NULL,
    [initial_fill_days] [numeric](5, 0) NULL,
    [attest_collected] [char](1) NULL,
    [daily_tests] [numeric](2, 0) NULL,
    [consolidated] [char](1) NULL,
    [drugdb_wac_unit] [numeric](12, 0) NULL,
    [ftf_master_days] [numeric](5, 0) NULL,
    [ftf_master_qty] [numeric](5, 0) NULL,
    [myb_info] [varchar](60) NULL,
    [allow_consolidate] [char](1) NULL,
    [consent_status] [varchar](60) NULL,
    [consent_datetime] [datetime] NULL,
    [consent_usernum] [numeric](16, 0) NULL,
    [source_host] [varchar](60) NULL,
    [source_user] [varchar](60) NULL,
    [de_first_name] [varchar](60) NULL,
    [de_last_name] [varchar](60) NULL,
    [de_phone_area_code] [varchar](3) NULL,
    [de_phone_number] [varchar](7) NULL,
    [rowversion] [timestamp] NOT NULL,
    [erx_review_usernum] [numeric](16, 0) NULL,
    [erx_review_datetime] [datetime] NULL,
    [tertiary_pp_num] [numeric](16, 0) NULL,
    [relates_to_messageid] [varchar](35) NULL,
    [prescriber_order_number] [varchar](35) NULL,
    [drug_dbcode] [varchar](35) NULL,
    [drug_dbcode_qualifier] [varchar](3) NULL,
    [message_id] [varchar](35) NULL,
    [erx_by_passed] [char](1) NULL,
    [voucher_amount] [numeric](12, 0) NULL,
    [voucher_patient_amount] [numeric](12, 0) NULL,
    [voucher_number] [varchar](60) NULL,
    [voucher_datetime] [datetime] NULL,
    [voucher_orig_patcopay] [numeric](12, 0) NULL,
    [original_rx_number] [varchar](25) NULL,
    [rewrite_due_to] [varchar](10) NULL,
    [direct_entry_value] [varchar](1) NULL,
    [translated_image_path] [varchar](1000) NULL,
    [translated_sig_paraphrase_id] [varchar](255) NULL,
    [translated_language_id] [numeric](5, 0) NULL,
    [translated_sig_pharaphrase] [varchar](1000) NULL,
    [RxFillIndicator] [varchar](100) NULL,
    [ProhibitRenewalRequest] [char](1) NULL,
    [FollowUpPrescriber_Last_Name] [varchar](35) NULL,
    [FollowUpPrescriber_First_Name] [varchar](35) NULL,
    [FollowUpPrescriber_Middle_Name] [varchar](35) NULL,
    CONSTRAINT [PK__rx_fill___3A652AC869D19EED] PRIMARY KEY CLUSTERED
(
[e_script_msg_attribute_seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[benefit_response_codes] ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[benefit_response_codes](
    [id] [bigint] NOT NULL,
    [approval_code] [char](2) NULL,
    [display_message] [varchar](20) NULL,
    [internal_response_code] [varchar](10) NULL,
    [transaction_resp_code] [varchar](10) NULL,
    [internal_description] [varchar](100) NULL,
    CONSTRAINT [PK_benefit_response_codes_Id] PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[third_party_claim_requests]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[third_party_claim_requests](
    [id] [bigint] NOT NULL,
    [e_script_msg_attribute_seq] [numeric](17, 0) NULL,
    [transaction_code] [char](4) NULL,
    [request_datetime] [datetime] NULL,
    [sys_user_num] [varchar](60) NULL,
    [benefit_card_num] [numeric](19, 0) NOT NULL,
    [transaction_id] [varchar](60) NULL,
    [order_invoice_number] [numeric](30, 0) NULL,
    [order_amount] [numeric](9, 0) NULL,
    [redemption_req_amount] [numeric](9, 0) NULL,
    [redemption_req_quantity] [numeric](3, 0) NULL,
    [manual] [bit] NULL,
    [processor] [varchar](60) NULL,
    [server] [varchar](60) NULL,
    CONSTRAINT [PK_third_party_claim_requests_Id] PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[third_party_claim_responses]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[third_party_claim_responses](
    [id] [bigint] NOT NULL,
    [transaction_id] [varchar](60) NULL,
    [response_code] [bigint] NULL,
    [capture_date] [datetime2](7) NULL,
    [redeemed_quantity] [numeric](3, 0) NULL,
    [redeemed_amount] [numeric](12, 0) NULL,
    [error_code] [varchar](10) NULL,
    [error_description] [varchar](60) NULL,
    [Authorization_id] [varchar](20) NULL,
    [Stan_id] [varchar](12) NULL,
    CONSTRAINT [pk_third_party_claim_responses] PRIMARY KEY CLUSTERED
(
[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO



/****** Object:  Table [dbo].[ecs_responses]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[ecs_responses](
    [ecs_response_status_type] [dbo].[CHAR_ID_TYPE] NULL,
    [ecs_basis_reimb_det_code] [numeric](2, 0) NULL,
    [ecs_req_seq_num] [numeric](30, 0) NULL,
    [ecs_transaction_code] [varchar](5) NULL,
    [pp_num] [numeric](16, 0) NULL,
    [ecs_resp_seq_num] [numeric](30, 0) NOT NULL,
    [e_script_msg_attribute_seq] [numeric](35, 0) NULL,
    [ecs_resp_date] [datetime] NOT NULL,
    [ecs_resp_pat_pay_amt] [numeric](12, 0) NULL,
    [ecs_resp_ingred_cost_paid] [numeric](12, 0) NULL,
    [ecs_resp_reference_number] [varchar](60) NULL,
    [ecs_resp_other_amount_paid] [numeric](12, 0) NULL,
    [ecs_resp_sales_tax_paid] [numeric](12, 0) NULL,
    [ecs_resp_total_amt_paid] [numeric](12, 0) NULL,
    [ecs_resp_sales_tax_submitted] [numeric](12, 0) NULL,
    [ecs_resp_authorization_num] [varchar](20) NULL,
    [ecs_resp_msg] [varchar](2000) NULL,
    [ecs_resp_addl_msg] [varchar](max) NULL,
    [ecs_resp_accum_ded_amt] [numeric](12, 0) NULL,
    [ecs_resp_incentive_fee_submitted] [numeric](12, 0) NULL,
    [ecs_resp_remain_ded_amt] [numeric](12, 0) NULL,
    [ecs_resp_other_amount_submitted] [numeric](12, 0) NULL,
    [ecs_resp_remain_bene_amt] [numeric](12, 0) NULL,
    [ecs_resp_amt_apply_per_ded] [numeric](12, 0) NULL,
    [ecs_resp_prof_service_fee_submitted] [numeric](12, 0) NULL,
    [ecs_resp_amt_copay] [numeric](12, 0) NULL,
    [esc_resp_help_desk_phone] [varchar](18) NULL,
    [ecs_resp_dispense_fee_paid] [numeric](12, 0) NULL,
    [ecs_resp_dispense_fee_submitted] [numeric](12, 0) NULL,
    [ecs_resp_prof_service_fee_paid] [numeric](12, 0) NULL,
    [ecs_resp_per_sales_tax_paid] [numeric](12, 0) NULL,
    [ecs_resp_amt_apply_prod_sel] [numeric](12, 0) NULL,
    [ecs_resp_per_sales_tax_submitted] [numeric](12, 0) NULL,
    [ecs_resp_amt_exc_bene_max] [numeric](12, 0) NULL,
    [ecs_resp_incentive_fee_paid] [numeric](12, 0) NULL,
    [ecs_resp_amt_apply_sales_tax] [numeric](12, 0) NULL,
    [ecs_resp_dur_overflow] [numeric](1, 0) NULL,
    [ecs_resp_ingred_cost_submitted] [numeric](12, 0) NULL,
    [ecs_resp_gross_due_submitted] [numeric](12, 0) NULL,
    [ecs_resp_msg_ack] [dbo].[YES_NO_TYPE] NOT NULL,
    [ecs_resp_rej_msg_ack] [dbo].[YES_NO_TYPE] NOT NULL,
    [fill_usual_customary_price] [numeric](12, 0) NULL,
    [rx_number] [varchar](60) NULL,
    [ecs_log] [varchar](8000) NULL,
    [batch_ecs] [varchar](60) NULL,
    [manual_ecs] [numeric](1, 0) NULL,
    [other_coverage_type] [numeric](5, 0) NULL,
    [sys_user_num] [numeric](30, 0) NULL,
    [cob_txn] [varchar](60) NULL,
    [comp_prod_seq] [numeric](16, 0) NULL,
    [patient_name] [varchar](120) NULL,
    [product_name] [varchar](120) NULL,
    [doctor_name] [varchar](120) NULL,
    [trading_partner_num] [varchar](60) NULL,
    [product_number] [varchar](60) NULL,
    [bin] [varchar](60) NULL,
    [pcn] [varchar](60) NULL,
    [fill_number] [numeric](7, 0) NULL,
    [fill_quantity] [numeric](12, 0) NULL,
    [cost_basis] [varchar](35) NULL,
    [force_accept] [numeric](1, 0) NULL,
    [provider_id] [varchar](60) NULL,
    [provider_id_code] [varchar](60) NULL,
    [cardholderid] [varchar](60) NULL,
    [ecs_datetime] [datetime] NULL,
    [metric_quantity] [numeric](12, 0) NULL,
    [awp_unit_price] [numeric](12, 0) NULL,
    [supplier_unit_price] [numeric](12, 0) NULL,
    [wac_unit_price] [numeric](12, 0) NULL,
    [paid_seq_num] [numeric](30, 0) NULL,
    [ecs_version_code] [varchar](10) NULL,
    [ecs_resp_flat_sales_tax] [numeric](12, 0) NULL,
    [patient_dob] [varchar](60) NULL,
    [txn_ref_number] [varchar](60) NULL,
    [adj_payment_type] [varchar](60) NULL,
    [int_ctrl_number] [varchar](60) NULL,
    [web_url] [varchar](500) NULL,
    [medicaid_icn] [varchar](60) NULL,
    [processor_fee] [numeric](12, 0) NULL,
    [pat_sales_tax] [numeric](12, 0) NULL,
    [plan_sales_tax] [numeric](12, 0) NULL,
    [amt_coinsurance] [numeric](12, 0) NULL,
    [est_generic_savings] [numeric](12, 0) NULL,
    [basis_of_calc_coinsurance] [varchar](60) NULL,
    [spend_amount_remain] [numeric](12, 0) NULL,
    [health_spend_amt] [numeric](12, 0) NULL,
    [amt_network] [numeric](12, 0) NULL,
    [amt_brand] [numeric](12, 0) NULL,
    [amt_preferred] [numeric](12, 0) NULL,
    [amt_non_preferred] [numeric](12, 0) NULL,
    [amt_coverage_gap] [numeric](12, 0) NULL,
    [ingred_cost_contract_amt] [numeric](12, 0) NULL,
    [dispense_fee_contract_amt] [numeric](12, 0) NULL,
    [medicaid_id_number] [varchar](60) NULL,
    [medicaid_agency_number] [varchar](60) NULL,
    [medicaid_cardholder_id] [varchar](60) NULL,
    [payor_id] [varchar](60) NULL,
    [plan_id] [varchar](60) NULL,
    [group_id] [varchar](60) NULL,
    [network_id] [varchar](60) NULL,
    [formulary_id] [varchar](60) NULL,
    [benefit_id] [varchar](60) NULL,
    [medicare_cov_code] [varchar](60) NULL,
    [medicare_effect_date] [varchar](60) NULL,
    [medicare_term_date] [varchar](60) NULL,
    [medicare_contract_number] [varchar](60) NULL,
    [cms_low_income_level] [varchar](60) NULL,
    [percent_sales_tax_rate_submitted] [numeric](12, 0) NULL,
    [percent_sales_tax_rate_paid] [numeric](12, 0) NULL,
    [percent_sales_tax_basis_paid] [numeric](12, 0) NULL,
    [doc_delivery] [char](1) NULL,
    [fill_days] [numeric](7, 0) NULL,
    [other_payor_amt_recognized] [numeric](12, 0) NULL,
    [ecs_resp_flat_sales_tax_submitted] [numeric](12, 0) NULL,
    [processor] [varchar](60) NULL,
    [server] [varchar](60) NULL,
    [percent_sales_tax_basis_submitted] [numeric](5, 0) NULL,
    [prod_id] [numeric](30, 0) NULL,
    [patient_num] [numeric](30, 0) NULL,
    [doctor_num] [numeric](30, 0) NULL,
    [rebill_reason] [varchar](500) NULL,
    [pa_usernum] [numeric](16, 0) NULL,
    [location] [varchar](500) NULL,
    [pa_replycode] [varchar](35) NULL,
    [pa_url] [varchar](3500) NULL,
    [pa_datetime] [datetime] NULL,
    CONSTRAINT [PK_ecs_responses_ecs_resp_seq_num] PRIMARY KEY CLUSTERED
(
[ecs_resp_seq_num] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[order_billship] ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[order_billship](
    [order_num] [numeric](30, 0) NOT NULL,
    [payment_method_id] [numeric](5, 0) NULL,
    [coupon_num] [numeric](5, 0) NULL,
    [payment_card_seq_num] [dbo].[ID_BIG_TYPE] NULL,
    [ship_method_id] [numeric](5, 0) NULL,
    [payment_amount] [numeric](12, 0) NULL,
    [payment_number] [varchar](60) NULL,
    [bank_routing_id] [varchar](60) NULL,
    [check_account] [varchar](60) NULL,
    [csz_zip_num] [numeric](16, 0) NULL,
    [ord_ship_address] [varchar](60) NULL,
    [order_payment_owner] [numeric](20, 0) NULL,
    [ord_ship_address2] [varchar](60) NULL,
    [ord_ship_zip_four] [numeric](4, 0) NULL,
    [ord_ship_price] [numeric](12, 0) NULL,
    [ord_ship_tax] [numeric](12, 0) NULL,
    [ord_ship_address3] [varchar](60) NULL,
    [ord_ship_track_num] [varchar](60) NULL,
    [ord_ship_date] [datetime] NULL,
    [ord_ship_first_name] [varchar](60) NULL,
    [ord_ship_middle_name] [varchar](60) NULL,
    [ord_ship_last_name] [varchar](60) NULL,
    [ord_ship_free_shipping] [dbo].[YES_NO_TYPE] NOT NULL,
    [ord_ship_cost] [numeric](12, 0) NULL,
    [ord_ship_note] [varchar](1000) NULL,
    [patient_preauth_datetime] [datetime] NULL,
    [shipper_pickup_datetime] [datetime] NULL,
    [ord_ship_account_number] [varchar](60) NULL,
    [patient_address_seq] [numeric](16, 0) NULL,
    [patient_ship_address_seq] [numeric](16, 0) NULL,
    [signature_required] [numeric](1, 0) NULL,
    [notified] [datetime] NULL,
    [force_ship_address] [char](1) NULL,
    [ship_weight] [numeric](12, 0) NULL,
    [family_id] [varchar](60) NULL,
    [patient_preauth_status] [varchar](60) NULL,
    [patient_preauth_note] [varchar](255) NULL,
    [patient_preauth_user] [numeric](16, 0) NULL,
    [shipper_method] [varchar](60) NULL,
    [ship_check] [numeric](1, 0) NULL,
    [sm_check] [numeric](1, 0) NULL,
    [address_validated] [char](1) NULL,
    [rowversion] [timestamp] NOT NULL,
    [est_ship_tax] [numeric](12, 0) NULL,
    [estimated_delivery_date] [datetime] NULL,
    [odc_fill_location] [varchar](35) NULL,
    [odc_order_type] [varchar](10) NULL,
    [order_dispensed] [char](1) NULL,
    PRIMARY KEY CLUSTERED
(
[order_num] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO


/****** Object:  Table [dbo].[patient_address]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[patient_address](
    [patient_addr_seq] [dbo].[ID_BIG_TYPE] NOT NULL,
    [csz_zip_num] [numeric](16, 0) NOT NULL,
    [address_type_num] [numeric](5, 0) NOT NULL,
    [patient_num] [numeric](20, 0) NOT NULL,
    [patient_address] [varchar](60) NOT NULL,
    [patient_address2] [varchar](60) NULL,
    [patient_zip_four] [dbo].[ZIP_CODE_FOUR_TYPE] NULL,
    [patient_address3] [varchar](60) NULL,
    [default_ship_address] [char](1) NULL,
    [default_bill_address] [char](1) NULL,
    [start_date] [datetime] NULL,
    [end_date] [datetime] NULL,
    [active] [char](1) NULL,
    [conversion_link] [varchar](60) NULL,
    [address_desc] [varchar](250) NULL,
    [verified_date] [datetime] NULL,
    [verified_user] [varchar](60) NULL,
    [patient_phone_seq] [numeric](16, 0) NULL,
    [created] [datetime2](7) NOT NULL,
    [created_user_num] [numeric](16, 0) NOT NULL,
    [updated] [datetime2](7) NOT NULL,
    [updated_user_num] [numeric](16, 0) NOT NULL,
    [verified] [bit] NOT NULL,
    [override_reason_id] [tinyint] NOT NULL,
    [last_verification] [datetime2](7) NULL,
    [origination_num] [numeric](5, 0) NOT NULL,
    [rowversion] [timestamp] NOT NULL,
    [deactivated] [datetime2](7) NULL,
    PRIMARY KEY CLUSTERED
(
[patient_addr_seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
CREATE INDEX patient_address_patient_num ON [dbo].[patient_address](patient_num);
GO

/****** Object:  Table [dbo].[trading_partner_general]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[trading_partner_general](
    [trading_partner_num] [numeric](16, 0) NOT NULL,
    [trading_partner_type_id] [numeric](5, 0) NOT NULL,
    [doctor_speciality_num] [numeric](5, 0) NULL,
    [region_num] [numeric](5, 0) NOT NULL,
    [account_num] [numeric](16, 0) NOT NULL,
    [tp_routing_name] [varchar](35) NULL,
    [general_status_code] [char](1) NOT NULL,
    [tp_email_address] [varchar](80) NULL,
    [tp_middle_name] [varchar](60) NULL,
    [tp_name] [varchar](60) NULL,
    [tp_routing_id] [varchar](35) NULL,
    [tp_password] [varchar](35) NULL,
    [tp_first_name] [varchar](60) NULL,
    [tp_last_name] [varchar](60) NULL,
    [tp_code] [varchar](35) NULL,
    [tp_default_version_id] [numeric](3, 0) NULL,
    [inventory_verification] [dbo].[YES_NO_TYPE] NOT NULL,
    [hub_affiliate] [numeric](16, 0) NULL,
    [is24hr] [dbo].[YES_NO_TYPE] NOT NULL,
    [override_mandatory_fields] [dbo].[YES_NO_TYPE] NOT NULL,
    [override_data_validation] [dbo].[YES_NO_TYPE] NOT NULL,
    [common_doctor] [dbo].[YES_NO_TYPE] NOT NULL,
    [tp_xmission_code] [varchar](6) NULL,
    [server_num] [numeric](5, 0) NULL,
    [serial_num] [numeric](5, 0) NULL,
    [wl_vendor_code] [varchar](60) NULL,
    [wl_num_labels] [numeric](2, 0) NULL,
    [tp_web_address] [varchar](255) NULL,
    [turnaround_days] [numeric](2, 0) NULL,
    [translation_mode] [dbo].[YES_NO_TYPE] NOT NULL,
    [workflow_auto_notify] [dbo].[YES_NO_TYPE] NOT NULL,
    [master_physician_index] [varchar](60) NULL,
    [conversion_link] [varchar](60) NULL,
    [external_link] [varchar](60) NULL,
    [pic_name] [varchar](80) NULL,
    [pic_license] [varchar](60) NULL,
    [pic_state_num] [numeric](5, 0) NULL,
    [pic_initials] [varchar](7) NULL,
    [sup_receiver_id] [varchar](35) NULL,
    [inventory_area_verification] [char](1) NULL,
    [use_location_billship] [char](1) NULL,
    [no_drfax] [char](1) NULL,
    [uses_inventory] [char](1) NULL,
    [rx_image_path] [varchar](80) NULL,
    [daw12] [char](1) NULL,
    [dr_type] [char](1) NULL,
    [in_patient_service] [char](1) NULL,
    [use_catalog_price] [char](1) NULL,
    [family_mode] [char](1) NULL,
    [fax_image_path] [varchar](80) NULL,
    [catalog_price_type] [varchar](10) NULL,
    [tp_warning_level] [numeric](5, 0) NULL,
    [tp_critical_level] [numeric](5, 0) NULL,
    [tp_alias] [varchar](60) NULL,
    [external_request_mode] [char](1) NULL,
    [sup_lead_time] [numeric](5, 0) NULL,
    [sup_810] [char](1) NULL,
    [source_host] [varchar](60) NULL,
    [source_user] [varchar](60) NULL,
    [sup_832_group] [varchar](80) NULL,
    [sup_account] [varchar](35) NULL,
    [pmp_template_path] [varchar](500) NULL,
    [pmp_output_path] [varchar](500) NULL,
    PRIMARY KEY CLUSTERED
(
[trading_partner_num] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[trading_partner_types]   ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[trading_partner_types](
    [trading_partner_type_id] [numeric](5, 0) NOT NULL,
    [trading_partner_type_desc] [varchar](60) NOT NULL,
    PRIMARY KEY CLUSTERED
(
[trading_partner_type_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO


/****** Object:  Table [dbo].[patient_plans]   ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[patient_plans](
    [pp_num] [numeric](16, 0) NOT NULL,
    [patient_num] [numeric](20, 0) NOT NULL,
    [cp_plan_id] [varchar](20) NULL,
    [coverage_type_id] [numeric](2, 0) NOT NULL,
    [sig_source_code] [dbo].[CHAR_ID_TYPE] NULL,
    [cp_activation_date] [datetime] NULL,
    [relationship_num] [numeric](5, 0) NULL,
    [cp_first_name] [varchar](60) NULL,
    [cp_middle_name] [varchar](60) NULL,
    [cp_effective_date] [datetime] NULL,
    [cp_last_name] [varchar](60) NULL,
    [cp_person_code] [char](3) NULL,
    [cp_home_plan] [varchar](3) NULL,
    [cp_host_plan] [varchar](3) NULL,
    [cp_deactivation_date] [datetime] NULL,
    [cp_expiration_date] [datetime] NULL,
    [cp_copay_amount] [numeric](12, 0) NULL,
    [cp_plan_id_ext] [varchar](20) NULL,
    [cp_alt_plan_id] [varchar](20) NULL,
    [external_link] [varchar](100) NULL,
    [override_group_id] [varchar](60) NULL,
    [rowversion] [timestamp] NOT NULL,
    PRIMARY KEY CLUSTERED
(
    [pp_num] ASC,
[patient_num] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
CREATE INDEX patient_plans_patient_num ON [dbo].[patient_plans](patient_num);
GO


/****** Object:  Table [dbo].[patient_general]   ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[patient_general](
    [patient_num] [numeric](20, 0) NOT NULL,
    [gender_code] [dbo].[CHAR_ID_TYPE] NULL,
    [patient_id_code] [varchar](3) NULL,
    [general_status_code] [char](1) NOT NULL,
    [language_id] [numeric](5, 0) NOT NULL,
    [title_id] [numeric](5, 0) NOT NULL,
    [patient_first_name] [varchar](60) NOT NULL,
    [patient_middle_name] [varchar](60) NULL,
    [patient_last_name] [varchar](60) NOT NULL,
    [patient_dob] [datetime] NOT NULL,
    [patient_email_address] [varchar](60) NULL,
    [patient_id] [varchar](60) NULL,
    [patient_membership_num] [varchar](60) NULL,
    [patient_credit_limit] [numeric](10, 0) NULL,
    [patient_privacy] [char](1) NULL,
    [patient_privacy_startdate] [datetime] NULL,
    [patient_privacy_enddate] [datetime] NULL,
    [patient_pc_physician] [numeric](16, 0) NULL,
    [care_category_num] [numeric](5, 0) NULL,
    [patient_attending_physician] [numeric](16, 0) NULL,
    [patient_alias] [varchar](80) NULL,
    [patient_maiden_name] [varchar](60) NULL,
    [conversion_link] [varchar](60) NULL,
    [external_link] [varchar](60) NULL,
    [care_coordinator_num] [numeric](16, 0) NULL,
    [care_kit_seq] [numeric](7, 0) NULL,
    [trading_partner_num] [numeric](25, 0) NULL,
    [family_id] [varchar](60) NULL,
    [hippa_counter] [numeric](3, 0) NULL,
    [vip] [char](1) NULL,
    [date_added] [datetime] NULL,
    [verified] [char](1) NULL,
    [facility_station_seq] [numeric](16, 0) NULL,
    [next_assessment_date] [datetime] NULL,
    [assessment_workflow] [numeric](7, 0) NULL,
    [assessment_eligible] [char](1) NULL,
    [patient_aob_startdate] [datetime] NULL,
    [patient_aob_enddate] [datetime] NULL,
    [last_aob_datetime] [datetime] NULL,
    [auto_contact_type_id] [numeric](5, 0) NULL,
    [marketing] [datetime] NULL,
    [patient_pref_hour_seq] [numeric](7, 0) NULL,
    [patient_privacy_sentdate] [datetime] NULL,
    [consent_status] [varchar](60) NULL,
    [consent_startdatetime] [datetime] NULL,
    [consent_enddatetime] [datetime] NULL,
    [source_host] [varchar](60) NULL,
    [source_user] [varchar](60) NULL,
    [patient_origination_seq] [numeric](5, 0) NULL,
    [cmr_status_code] [varchar](4) NULL,
    [IMPAIRMENT_INDICATOR] [varchar](5) NULL,
    [Secondary_Language_ID] [int] NULL,
    [consent_accepted_date] [datetime2](7) NULL,
    [additional_patient_id_type_code] [varchar](3) NULL,
    [additional_patient_id] [varchar](60) NULL,
    [is_med_sync] [char](1) NULL,
    [med_sync_updated_by] [numeric](5, 0) NULL,
    [med_sync_updated_date] [datetime2](7) NULL,
    [is_mdp] [char](1) NULL,
    [mdp_updated_by] [numeric](16, 0) NULL,
    [mdp_updated_date] [datetime2](7) NULL,
    CONSTRAINT [PK__patient___EE846DDE07C12930] PRIMARY KEY CLUSTERED
(
[patient_num] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO


/****** Object:  Table [dbo].[payors]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[payors](
    [payor_code] [varchar](10) NOT NULL,
    [payor_num] [numeric](16, 0) NOT NULL,
    [general_status_code] [char](1) NULL,
    [payor_type_num] [numeric](5, 0) NULL,
    [payor_bill_type_num] [numeric](5, 0) NULL,
    [payor_name] [varchar](60) NOT NULL,
    [payor_date_added] [datetime] NULL,
    [payor_provider_num] [varchar](15) NULL,
    [payor_accept_assignment] [dbo].[YES_NO_TYPE] NULL,
    [payor_max_billing_days] [numeric](5, 0) NULL,
    [conversion_link] [varchar](60) NULL,
    [payor_group] [varchar](60) NULL,
    [turnaround_days] [numeric](7, 0) NULL,
    [print_cost] [char](1) NULL,
    [daw12] [char](1) NULL,
    [termination_date] [datetime] NULL,
    [payor_min_util_pct] [numeric](5, 0) NULL,
    [web_url] [varchar](255) NULL,
    [auto_refill] [char](1) NULL,
    [print_marketing] [char](1) NULL,
    [hrm] [char](1) NULL,
    [consolidate_rxs] [char](1) NULL,
    [myb] [char](1) NULL,
    [dme_collection] [char](1) NULL,
    [restricted_view] [char](1) NULL,
    [auto_refilltoosoon] [char](1) NULL,
    [rx_consent] [char](1) NULL,
    PRIMARY KEY CLUSTERED
(
[payor_num] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[payor_plans]   ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[payor_plans](
    [pp_num] [numeric](16, 0) NOT NULL,
    [general_status_code] [char](1) NULL,
    [trading_partner_num] [numeric](16, 0) NULL,
    [pp_plan_name] [varchar](45) NOT NULL,
    [ship_method_id] [numeric](5, 0) NULL,
    [pp_group_name] [varchar](100) NULL,
    [payor_num] [numeric](16, 0) NULL,
    [pp_activation_date] [datetime] NULL,
    [pp_max_util_pct] [numeric](5, 0) NULL,
    [pp_max_refill_allowed] [numeric](3, 0) NULL,
    [pp_max_days_supply] [numeric](4, 0) NULL,
    [pp_union] [dbo].[YES_NO_TYPE] NOT NULL,
    [pp_type_code] [char](1) NULL,
    [pp_plan_id] [varchar](8) NULL,
    [pp_group_id] [varchar](15) NULL,
    [pp_minimum_cash_price] [numeric](12, 0) NULL,
    [conversion_link] [varchar](60) NULL,
    [u_and_c_pricing] [char](1) NULL,
    [doctor_id_type_code] [varchar](3) NULL,
    [use_sale_price] [char](1) NULL,
    [form_group_seq] [numeric](7, 0) NULL,
    [skip_pricing] [char](1) NULL,
    [termination_date] [datetime] NULL,
    [credit_limit] [numeric](12, 0) NULL,
    [vip] [char](1) NULL,
    [pp_max_quantity] [numeric](12, 0) NULL,
    [plan_alias] [varchar](60) NULL,
    [pp_min_days_supply] [numeric](4, 0) NULL,
    [pp_min_quantity] [numeric](12, 0) NULL,
    [pp_phone_number] [varchar](35) NULL,
    [daw12] [char](1) NULL,
    [send_uc_as_tpprice] [char](1) NULL,
    [pp_min_util_pct] [numeric](5, 0) NULL,
    [lost_margin] [char](1) NULL,
    [generic_loss] [numeric](15, 0) NULL,
    [brand_loss] [numeric](15, 0) NULL,
    [min_margin] [char](1) NULL,
    [auto_refill] [char](1) NULL,
    [print_marketing] [char](1) NULL,
    [hrm] [char](1) NULL,
    [consolidate_rxs] [char](1) NULL,
    [myb] [char](1) NULL,
    [dme_collection] [char](1) NULL,
    [restricted_view] [char](1) NULL,
    [auto_refilltoosoon] [char](1) NULL,
    [rx_consent] [char](1) NULL,
    [BenefitPeriodType] [char](1) NULL,
    PRIMARY KEY CLUSTERED
(
[pp_num] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[payment_card_reply]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[payment_card_reply](
    [gl_post_num] [numeric](30, 0) NULL,
    [pc_sub_status_num] [numeric](2, 0) NULL,
    [pc_reply_seq_num] [numeric](35, 0) NOT NULL,
    [pc_reply_type] [dbo].[CHAR_ID_TYPE] NOT NULL,
    [pc_reply_request_id] [varchar](255) NULL,
    [pc_reply_rcode] [varchar](10) NULL,
    [pc_reply_rflag] [varchar](255) NULL,
    [pc_reply_rmsg] [varchar](1500) NULL,
    [pc_reply_amount] [numeric](12, 0) NULL,
    [pc_reply_auth_code] [varchar](65) NULL,
    [pc_avs_code] [dbo].[CHAR_ID_TYPE] NULL,
    [pc_reply_timestamp] [varchar](80) NULL,
    [pc_request_msg] [image] NULL,
    [pc_reply_msg] [image] NULL,
    [order_num] [numeric](30, 0) NULL,
    [pc_datetime] [datetime] NULL,
    [payment_card_number] [varchar](60) NULL,
    [payment_card_seq_num] [varchar](60) NULL,
    [batch_number] [varchar](60) NULL,
    [batch_datetime] [datetime] NULL,
    [batch_status] [varchar](500) NULL,
    [manual] [char](1) NULL,
    [batch] [char](1) NULL,
    [action_code] [varchar](35) NULL,
    [fsa_card] [char](1) NULL,
    [provider] [varchar](60) NULL,
    [resp_avs_code] [varchar](300) NULL,
    [pc_log] [varchar](1500) NULL,
    [vault_first_name] [varchar](60) NULL,
    [vault_last_name] [varchar](60) NULL,
    [vault_bill_address1] [varchar](60) NULL,
    [vault_bill_address2] [varchar](60) NULL,
    [vault_bill_city] [varchar](60) NULL,
    [vault_bill_state] [varchar](10) NULL,
    [vault_bill_country] [varchar](10) NULL,
    [vault_bill_zip] [varchar](10) NULL,
    [vault_bill_email] [varchar](60) NULL,
    [vault_bill_phone] [varchar](60) NULL,
    [vault_year_expire] [varchar](4) NULL,
    [vault_month_expire] [varchar](2) NULL,
    [vault_card_type] [varchar](4) NULL,
    [vault_batch_settled_datetime] [datetime] NULL,
    PRIMARY KEY CLUSTERED
(
[pc_reply_seq_num] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[patient_address_type_assignments]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[patient_address_type_assignments](
    [patient_addr_seq] [numeric](16, 0) NOT NULL,
    [address_type_num] [numeric](5, 0) NOT NULL,
    [patient_num] [numeric](20, 0) NOT NULL,
    [updated] [datetime2](7) NOT NULL,
    [updated_user_num] [numeric](16, 0) NOT NULL,
    CONSTRAINT [pk_address_type_assignments] PRIMARY KEY CLUSTERED
(
    [patient_num] ASC,
[address_type_num] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[general_ledger]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[general_ledger](
    [gl_post_num] [numeric](30, 0) NOT NULL,
    CONSTRAINT [pk_general_ledger] PRIMARY KEY CLUSTERED
(
[gl_post_num] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[states]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[states](
    [state_code] [dbo].[STATE_TYPE] NULL,
    [state_num] [numeric](5, 0) NOT NULL,
    [state_name] [dbo].[DESC_SMALL_TYPE] NOT NULL,
    [ctry_num] [numeric](5, 0) NULL,
    [sales_tax] [numeric](10, 0) NULL,
    [ncpdp_state_code] [varchar](3) NULL,
    [cs_report_seq] [numeric](5, 0) NULL,
    [state_license] [varchar](60) NULL,
    [server_num] [numeric](5, 0) NULL,
    [cs_patient_report_seq] [numeric](7, 0) NULL,
    [rph_state_license] [varchar](60) NULL,
    [cs_empty_file] [char](1) NULL,
    [cs_frequency] [numeric](7, 0) NULL,
    [cs_next_date] [datetime] NULL,
    [cs_last_date] [datetime] NULL,
    [cs_day_of_week] [varchar](60) NULL,
    [cs_extension] [varchar](120) NULL,
    [cs_skip_c1] [char](1) NULL,
    [cs_skip_c2] [char](1) NULL,
    [cs_skip_c3] [char](1) NULL,
    [cs_skip_c4] [char](1) NULL,
    [cs_skip_c5] [char](1) NULL,
    [cs_skip_c6] [char](1) NULL,
    [cs_filename] [varchar](120) NULL,
    [cs_filename_dates] [char](1) NULL,
    [cs_skip_run] [char](1) NULL,
    [cs_fill_report] [char](1) NULL,
    [consent_age] [numeric](2, 0) NULL,
    [daw12] [char](1) NULL,
    [collect_tax] [char](1) NULL,
    [sales_tax_type] [varchar](60) NULL,
    [charge_ecs] [char](1) NULL,
    [charge_ecs_basis] [varchar](60) NULL,
    [calc_ecs_basis] [varchar](60) NULL,
    [dme_only] [char](1) NULL,
    [PMP_ENABLED] [bit] NOT NULL,
    [REAL_TIME_REPORTING_ON] [bit] NOT NULL,
    PRIMARY KEY CLUSTERED
(
[state_num] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[city_state_zip]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[city_state_zip](
    [csz_zip_num] [numeric](16, 0) NOT NULL,
    [csz_city_name] [varchar](35) NOT NULL,
    [region_num] [numeric](5, 0) NULL,
    [state_num] [numeric](5, 0) NOT NULL,
    [ctry_num] [numeric](5, 0) NOT NULL,
    [csz_zip_code] [varchar](10) NOT NULL,
    [active] [char](1) NULL,
    PRIMARY KEY CLUSTERED
(
[csz_zip_num] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[OK_hydrocodone_sanity_products] ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[OK_hydrocodone_sanity_products](
    [Category] [nvarchar](255) NULL,
    [GPI] [nvarchar](255) NULL,
    [NDC] [nvarchar](255) NULL,
    [Generic Name] [nvarchar](255) NULL,
    [Display Name] [nvarchar](255) NULL,
    [Federal Class] [nvarchar](255) NULL
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[rx_status_codes]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[rx_status_codes](
    [rx_status_code_num] [numeric](2, 0) NOT NULL,
    [rx_status_code_desc] [varchar](30) NOT NULL,
    PRIMARY KEY CLUSTERED
(
[rx_status_code_num] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[rx_fill_dur]   ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[rx_fill_dur](
    [e_script_msg_attribute_seq] [numeric](35, 0) NULL,
    [sys_user_num] [numeric](16, 0) NULL,
    [dur_date] [datetime] NOT NULL,
    [dur_severity_code] [char](1) NULL,
    [rx_interaction_num] [numeric](2, 0) NULL,
    [dur_seq] [numeric](20, 0) NOT NULL,
    [result_svc_code] [dbo].[CODE_SMALL_TYPE] NULL,
    [prof_svc_code] [dbo].[CODE_SMALL_TYPE] NULL,
    [reason_svc_code] [dbo].[CODE_SMALL_TYPE] NULL,
    [dur_comment] [varchar](1000) NULL,
    [dur_resolve_date] [datetime] NULL,
    [interact_escript_seq] [numeric](25, 0) NULL,
    [interaction_desc] [varchar](2500) NULL,
    [interact_code] [numeric](2, 0) NULL,
    [interact_ddxcn] [numeric](2, 0) NULL,
    [interact_ddxcn_sn] [numeric](2, 0) NULL,
    [patient_disease_seq] [numeric](20, 0) NULL,
    [patient_allergy_seq] [numeric](20, 0) NULL,
    [prod_ref_num] [varchar](60) NULL,
    [interact_msg_hash] [varchar](60) NULL,
    [rx_number] [varchar](60) NULL,
    [trading_partner_num] [numeric](16, 0) NULL,
    [order_num] [numeric](30, 0) NULL,
    [level_of_effort] [varchar](60) NULL,
    [co_agent_id] [varchar](60) NULL,
    [co_agent_qualifier] [varchar](60) NULL,
    [external_med] [char](1) NULL,
    PRIMARY KEY CLUSTERED
(
[dur_seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[state_cs_products]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[state_cs_products](
    [state_cs_product_seq] [numeric](16, 0) NOT NULL,
    [state_num] [numeric](5, 0) NULL,
    [prod_id] [numeric](16, 0) NULL,
    [prod_dea] [numeric](3, 0) NULL,
    [prod_filter] [varchar](10) NULL,
    [pobox_active] [varchar](1) NULL,
    PRIMARY KEY CLUSTERED
(
[state_cs_product_seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[trading_partner_address]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[trading_partner_address](
    [trading_partner_num] [numeric](16, 0) NOT NULL,
    [address_type_num] [numeric](5, 0) NULL,
    [tp_addr_seq] [dbo].[ID_BIG_TYPE] NOT NULL,
    [csz_zip_num] [numeric](16, 0) NULL,
    [tp_address] [dbo].[ADDRESS_TYPE] NOT NULL,
    [tp_zip_four] [dbo].[ZIP_CODE_FOUR_TYPE] NULL,
    [tp_address3] [varchar](40) NULL,
    [tp_address2] [dbo].[ADDRESS_TYPE] NULL,
    [address_desc] [varchar](250) NULL,
    [tp_phone_seq] [numeric](16, 0) NULL,
    [tp_fax_seq] [numeric](16, 0) NULL,
    [active] [char](1) NULL,
    [spi] [varchar](60) NULL,
    [spilog] [varchar](100) NULL,
    [external_link] [char](60) NULL,
    [external_link_phone] [char](60) NULL,
    PRIMARY KEY CLUSTERED
(
[tp_addr_seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

CREATE INDEX trading_partner_address_partner_num ON [dbo].[trading_partner_address](trading_partner_num);
GO

/****** Object:  Table [dbo].[patient_attributes]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[patient_attributes](
    [patient_num] [numeric](20, 0) NOT NULL,
    [patient_height] [numeric](5, 0) NULL,
    [religion_num] [numeric](5, 0) NULL,
    [ship_method_id] [numeric](5, 0) NULL,
    [payment_method_id] [numeric](5, 0) NULL,
    [pickup_delivery_methods_seq] [numeric](5, 0) NULL,
    [patient_location_id] [numeric](2, 0) NULL,
    [patient_school_status] [varchar](2) NULL,
    [race_id] [numeric](5, 0) NULL,
    [patient_weight] [numeric](5, 0) NULL,
    [patient_smokes] [dbo].[YES_NO_TYPE] NOT NULL,
    [species_num] [numeric](5, 0) NULL,
    [patient_blood_glucose_level] [char](6) NULL,
    [patient_blood_pressure] [char](10) NULL,
    [patient_cholesterol_level] [char](4) NULL,
    [patient_HbA1C_level] [char](5) NULL,
    [patient_gen_if_avail] [dbo].[YES_NO_TYPE] NOT NULL,
    [patient_twin] [dbo].[YES_NO_UNKNOWN_TYPE] NOT NULL,
    [patient_married] [dbo].[YES_NO_UNKNOWN_TYPE] NOT NULL,
    [patient_disabled] [dbo].[YES_NO_UNKNOWN_TYPE] NOT NULL,
    [patient_safety_caps] [dbo].[YES_NO_TYPE] NOT NULL,
    [patient_family_planning] [dbo].[YES_NO_UNKNOWN_TYPE] NOT NULL,
    [patient_student] [dbo].[YES_NO_UNKNOWN_TYPE] NOT NULL,
    [patient_school] [varchar](30) NULL,
    [patient_visually_impaired] [char](1) NULL,
    [patient_retired] [dbo].[YES_NO_UNKNOWN_TYPE] NULL,
    [patient_refill_reminder] [dbo].[YES_NO_UNKNOWN_TYPE] NULL,
    [patient_pregnant] [dbo].[YES_NO_UNKNOWN_TYPE] NULL,
    [facility_id] [varchar](10) NULL,
    [workflow_auto_notify] [dbo].[YES_NO_TYPE] NOT NULL,
    [bill_secondary_coverage] [dbo].[YES_NO_TYPE] NOT NULL,
    [patient_hair_color] [varchar](60) NULL,
    [patient_eye_color] [varchar](60) NULL,
    [patient_blood_type] [varchar](60) NULL,
    [patient_preauth_approval] [dbo].[YES_NO_TYPE] NULL,
    [shipping_account_number] [varchar](60) NULL,
    [other] [varchar](256) NULL,
    [pc_trading_partner_num] [numeric](16, 0) NULL,
    [patient_residence_id] [numeric](2, 0) NULL,
    [copay_differential] [char](1) NULL,
    [copay_diff_amount] [numeric](12, 0) NULL,
    [doc_delivery] [char](1) NULL,
    [prod_consolidate] [char](1) NULL,
    [patient_service_id] [numeric](5, 0) NULL,
    [patient_hearing_impaired] [char](1) NULL,
    [auto_refill] [char](1) NULL,
    [copay_consent_required] [char](1) NULL,
    [copay_consent_amount] [numeric](12, 0) NULL,
    [insulin_pump_required] [char](1) NOT NULL,
    PRIMARY KEY CLUSTERED
(
[patient_num] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[rx_params]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[rx_params](
    [narcotic_code] [dbo].[NARCOTIC_CODE_TYPE] NOT NULL,
    [state_num] [numeric](5, 0) NOT NULL,
    [num_refills_allowed] [numeric](5, 0) NULL,
    [num_days_from_orig_date] [numeric](5, 0) NULL,
    [num_days_expire] [numeric](5, 0) NULL,
    [max_days_supply] [numeric](5, 0) NULL,
    [min_pct_utilization] [numeric](5, 0) NULL,
    [accept_fax] [char](1) NULL,
    [send_fax] [char](1) NULL,
    [popup_text] [varchar](255) NULL,
    [auto_refill] [char](1) NULL,
    [state_restricted] [char](1) NULL,
    [notes] [varchar](160) NULL,
    PRIMARY KEY CLUSTERED
(
    [state_num] ASC,
[narcotic_code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[rx_params_gpis]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[rx_params_gpis](
    [gpi_class_id] [varchar](60) NOT NULL,
    [state_num] [numeric](5, 0) NOT NULL,
    [num_refills_allowed] [numeric](5, 0) NULL,
    [num_days_from_orig_date] [numeric](5, 0) NULL,
    [num_days_expire] [numeric](5, 0) NULL,
    [max_days_supply] [numeric](5, 0) NULL,
    [min_pct_utilization] [dbo].[PERCENT_TYPE] NULL,
    [accept_fax] [char](1) NULL,
    [send_fax] [char](1) NULL,
    [gpi_class_desc] [varchar](80) NULL,
    CONSTRAINT [XPKrx_params] PRIMARY KEY CLUSTERED
(
    [state_num] ASC,
[gpi_class_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO


/****** Object:  Table [dbo].[shipping_methods]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[shipping_methods](
    [ship_method_id] [numeric](5, 0) NOT NULL,
    [ship_method_desc] [varchar](60) NOT NULL,
    [shipping_method_price] [numeric](12, 0) NULL,
    [shipping_cod] [dbo].[YES_NO_TYPE] NOT NULL,
    [shipping_days] [numeric](2, 0) NULL,
    [shipping_code] [varchar](60) NULL,
    [signature_required] [char](1) NULL,
    [active] [char](1) NULL,
    [priority] [char](1) NULL,
    PRIMARY KEY CLUSTERED
(
[ship_method_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO


/****** Object:  Table [dbo].[patient_family]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[patient_family](
    [patient_family_seq] [numeric](16, 0) NOT NULL,
    [patient_num] [numeric](20, 0) NULL,
    [family_id] [varchar](60) NULL,
    [head_of_household] [char](1) NULL,
    [web_access] [char](1) NULL,
    [credit_limit] [numeric](12, 0) NULL,
    [conversion_link] [varchar](60) NULL,
    [credit_account_status] [varchar](10) NULL,
    PRIMARY KEY CLUSTERED
(
[patient_family_seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO


/****** Object:  Table [dbo].[order_defaults]  ******/
    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[order_defaults](
    [replace_reason_num] [numeric](5, 0) NULL,
    [coupon_num] [numeric](5, 0) NULL,
    [order_status_num] [numeric](2, 0) NULL,
    [order_line_status_num] [numeric](2, 0) NULL,
    [order_hold_reason_num] [numeric](5, 0) NULL,
    [payment_method_id] [numeric](5, 0) NULL,
    [note_type_id] [numeric](5, 0) NULL,
    [return_reason_num] [numeric](5, 0) NULL,
    [origination_num] [numeric](5, 0) NULL,
    [ord_def_print_note] [dbo].[YES_NO_TYPE] NOT NULL,
    [order_split_reason_num] [numeric](5, 0) NULL,
    [pc_address_verification] [dbo].[YES_NO_TYPE] NOT NULL,
    [order_split_hold_reason_num] [numeric](5, 0) NULL,
    [order_return_shipcost] [char](1) NULL,
    [bill_cc_shippack] [char](1) NULL,
    [pickup_delivery_methods_seq] [numeric](5, 0) NULL,
    [same_patient] [char](1) NULL,
    [lock_order] [char](1) NULL,
    [family_mode] [char](1) NULL,
    [auto_split_days] [numeric](4, 0) NULL,
    [return_ecs] [char](1) NULL,
    [return_cc] [char](1) NULL,
    [return_inv] [char](1) NULL,
    [replace_ecs] [char](1) NULL,
    [replace_cc] [char](1) NULL,
    [replace_inv] [char](1) NULL,
    [need_by_delta] [numeric](5, 0) NULL,
    [quote_days] [numeric](3, 0) NULL,
    [skip_zero_amt] [char](1) NULL,
    [return_location] [numeric](16, 0) NULL,
    [final_ship_status] [numeric](7, 0) NULL,
    [same_dispensed_GPI_shipped_days] [numeric](3, 0) NULL,
    [max_return_days] [numeric](3, 0) NULL,
    [MYBBypassDays] [numeric](3, 0) NULL,
    [cp_adjudication] [char](1) NULL
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[rx_Multi_Link] ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[rx_Multi_Link](
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
PRIMARY KEY CLUSTERED
(
	[rx_link_seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

/****** Object:  Table [dbo].[system_users] ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[system_users](
	[sys_user_num] [numeric](16, 0) NOT NULL,
	[user_class_name] [varchar](30) NOT NULL,
	[system_user_type_num] [numeric](5, 0) NULL,
	[sys_user_login] [varchar](30) NOT NULL,
	[sys_user_password] [varchar](30) NULL,
	[sys_user_first_name] [varchar](60) NOT NULL,
	[sys_user_middle_name] [varchar](60) NULL,
	[sys_user_last_name] [varchar](60) NOT NULL,
	[sys_user_email_address] [varchar](45) NULL,
	[sys_user_initials] [varchar](7) NULL,
	[general_status_code] [char](1) NOT NULL,
	[sys_user_area_code] [numeric](3, 0) NULL,
	[sys_user_phone_num] [numeric](7, 0) NULL,
	[sys_user_phone_ext] [numeric](7, 0) NULL,
	[ctry_num] [numeric](5, 0) NULL,
	[phone_type_num] [numeric](5, 0) NULL,
	[trading_partner_num] [numeric](16, 0) NULL,
	[account_num] [numeric](16, 0) NULL,
	[patient_num] [numeric](20, 0) NULL,
	[miscellaneous] [varchar](80) NULL,
	[region_num] [numeric](5, 0) NULL,
	[secondary_password] [varchar](35) NULL,
	[conversion_link] [varchar](60) NULL,
	[language_id] [numeric](5, 0) NULL,
	[session_timeout] [numeric](4, 0) NULL,
	[login_server] [numeric](5, 0) NULL,
	[external_link] [varchar](60) NULL,
	[lockout] [char](1) NULL,
	[rowversion] [timestamp] NOT NULL,
	[ad_user_id] [varchar](60) NULL,
 CONSTRAINT [PK__system_u__7FF9F35E1AD3FDA4] PRIMARY KEY CLUSTERED
(
	[sys_user_num] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[quantity_change_tracking] ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[quantity_change_tracking](
	[e_script_msg_attribute_seq] [numeric](35, 0) NOT NULL,
	[rx_number] [varchar](60) NULL,
	[is_quantity_lowered] [char](1) NULL,
	[created_on] [datetime] NULL,
	[updated_on] [datetime] NULL,
PRIMARY KEY CLUSTERED
(
	[e_script_msg_attribute_seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[auto_escript_filler] ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[auto_escript_filler](
	[auto_efiller_seq] [numeric](30, 0) NOT NULL,
	[escript_id] [numeric](35, 0) NULL,
	[location_num] [varchar](60) NULL,
	[auto_efiller_datetime] [datetime] NULL,
	[processed_datetime] [datetime] NULL,
	[create_order] [char](1) NULL,
	[patient_num] [numeric](30, 0) NULL,
	[order_num] [numeric](30, 0) NULL,
	[order_invoice_number] [varchar](60) NULL,
	[status] [int] NULL,
	[batch_number] [varchar](60) NULL,
	[log_note] [varchar](5000) NULL,
	[scan_server] [varchar](60) NULL,
	[order_creation_date] [datetime] NULL,
PRIMARY KEY CLUSTERED
(
	[auto_efiller_seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[rx_link_substitution_log]  ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[rx_link_substitution_log](
	[rx_link_substituion_log_seq] [numeric](35, 0) IDENTITY(1,1) NOT NULL,
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
PRIMARY KEY CLUSTERED
(
	[rx_link_substituion_log_seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[rx_Link_Dcco_GPI_List]  ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[rx_Link_Dcco_GPI_List](
	[GPI] [varchar](60) NULL
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[rx_link_logging] ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[rx_link_logging](
	[rx_link_log_seq] [numeric](35, 0) NOT NULL,
	[parent_rx_number] [varchar](60) NULL,
	[parent_e_script_msg_attr_seq] [numeric](35, 0) NULL,
	[child_rx_number] [varchar](60) NULL,
	[child_e_script_msg_attr_seq] [numeric](35, 0) NULL,
	[ACTION] [varchar](500) NULL,
	[LOG_DATETIME] [datetime2](7) NULL,
	[LOG_USER] [numeric](10, 0) NULL,
PRIMARY KEY CLUSTERED
(
	[rx_link_log_seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[rx_link_logging] ADD  DEFAULT (getdate()) FOR [LOG_DATETIME]
GO

/****** Object:  Sequence [dbo].[S_rx_link_seq] ******/

CREATE SEQUENCE [dbo].[S_rx_link_seq]

 AS [bigint]

START WITH 39827160

INCREMENT BY 1

MINVALUE -9223372036854775808

MAXVALUE 9223372036854775807

CACHE

GO

/****** Object:  Sequence [dbo].[s_rx_link_logging_seq] ******/

CREATE SEQUENCE [dbo].[s_rx_link_logging_seq]
    AS [bigint]
    START WITH 500
    INCREMENT BY 1
    MINVALUE -9223372036854775808
    MAXVALUE 9223372036854775807
    CACHE
GO

/****** Object:  Table [dbo].[sig_groups] ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[sig_groups](
	[sig] [varchar](255) NOT NULL,
	[sig_group] [int] NOT NULL,
PRIMARY KEY CLUSTERED
(
	[sig] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[auto_route_rules] ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[auto_route_rules](
	[auto_route_rule_seq] [numeric](20, 0) NOT NULL,
	[auto_route_type_name] [varchar](80) NULL,
	[location_num] [varchar](60) NULL,
	[target_display_value] [varchar](255) NULL,
	[target_value] [varchar](60) NULL,
	[priority] [numeric](7, 0) NULL,
PRIMARY KEY CLUSTERED
(
	[auto_route_rule_seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY]
GO
