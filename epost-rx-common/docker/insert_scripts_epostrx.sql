
insert into dbo.rx_defaults (rx_def_auto_refill, otc_as_rx_precheck, pwo_precheck, precheck_all, max_pv_days, check_narcotic_refills) values (N'Y', N'Y', N'Y', N'Y', 2, N'Y');
insert into dbo.order_defaults (ord_def_print_note, pc_address_verification, family_mode) values (N'Y', N'Y', N'Y');

/****** Ans Rx Product Check ******/
-- 1. ITEM CANNOT BE SHIPPED TO P.O. BOX  @coldpack='1'
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq,sys_user_num,edi_message_id,edi_transaction_code,image_available,script_legible,clone_rx,auto_refill, dispensed_product_id, order_invoice_number, trading_partner_num) values (2090101,1,1,N'FILLRX',N'N',N'N',N'N',N'N', 2090101, '2090101', 2090101);
insert into dbo.products (prod_id,prod_active,prod_rx_required,prod_name_desc,prod_unit_of_use,prod_number,prod_compound) values(2090101,N'Y',N'Y',N'',N'Y',N'2090101',N'N');
insert into dbo.auto_route_rules(auto_route_rule_seq, target_value, priority) values(2090101, N'2090101', 300);
insert into dbo.order_header (order_num,order_date,order_high_priority,order_picked,order_validated, order_invoice_number, trading_partner_num) values(2090101,N'2022-10-01 19:49:30.447',N'Y',N'Y',N'Y', '2090101', 2090101);
insert into dbo.order_billship(order_num,ord_ship_free_shipping, patient_ship_address_seq) values(2090101,'Y', 2090101);
insert into dbo.patient_address(patient_addr_seq,csz_zip_num,address_type_num,patient_num,patient_address,created,created_user_num,updated,updated_user_num,verified,override_reason_id,origination_num) values(2090101,2090101,291,2090101,'XPOBOX',getDate(),1,getDate(),1,0,1,1);

-- 2. ITEM CANNOT BE SHIPPED TO P.O. BOX  @prodea=2
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq,sys_user_num,edi_message_id,edi_transaction_code,image_available,script_legible,clone_rx,auto_refill, dispensed_product_id, order_invoice_number, trading_partner_num) values (2090201,1,1,N'FILLRX',N'N',N'N',N'N',N'N', 2090201, '2090201', 2090201);
insert into dbo.products (prod_id,prod_active,prod_rx_required,prod_name_desc,prod_unit_of_use,prod_number,prod_compound, prod_dea) values(2090201,N'Y',N'Y',N'',N'Y',N'2090201',N'N', 2);
insert into dbo.order_header (order_num,order_date,order_high_priority,order_picked,order_validated, order_invoice_number, trading_partner_num) values(2090201,N'2022-10-01 19:49:30.447',N'Y',N'Y',N'Y', '2090201', 2090201);
insert into dbo.order_billship(order_num,ord_ship_free_shipping, patient_ship_address_seq) values(2090201,'Y', 2090201);
insert into dbo.patient_address(patient_addr_seq,csz_zip_num,address_type_num,patient_num,patient_address,created,created_user_num,updated,updated_user_num,verified,override_reason_id,origination_num) values(2090201,2090201,292,2090201,'XPOBOX',getDate(),1,getDate(),1,0,1,1);

-- 3. ITEM CANNOT BE SHIPPED TO P.O. BOX  @proddeaState=2
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq,sys_user_num,edi_message_id,edi_transaction_code,image_available,script_legible,clone_rx,auto_refill, dispensed_product_id, order_invoice_number, trading_partner_num) values (2090301,1,1,N'FILLRX',N'N',N'N',N'N',N'N', 2090301, '2090301', 2090301);
insert into dbo.products (prod_id,prod_active,prod_rx_required,prod_name_desc,prod_unit_of_use,prod_number,prod_compound) values(2090301,N'Y',N'Y',N'',N'Y',N'2090301',N'N');
insert into dbo.state_cs_products(state_cs_product_seq, state_num, prod_dea, prod_id) values(2090301, 293, 2, 2090301);
insert into dbo.order_lines (order_line_num,order_num,e_script_msg_attribute_seq,order_line_type_num,return_replace_ecs,return_replace_inventory,return_replace_payment_card, order_line_status_num) values(293,2090301,2090301,1,N'Y',N'Y',N'Y', 1);
insert into dbo.order_header (order_num,order_date,order_high_priority,order_picked,order_validated, order_invoice_number, trading_partner_num) values(2090301,N'2022-10-01 19:49:30.447',N'Y',N'Y',N'Y', '2090301', 2090301);
insert into dbo.order_billship(order_num,ord_ship_free_shipping, patient_ship_address_seq) values(2090301,'Y', 2090301);
insert into dbo.patient_address(patient_addr_seq,csz_zip_num,address_type_num,patient_num,patient_address,created,created_user_num,updated,updated_user_num,verified,override_reason_id,origination_num) values(2090301,2090301,293,2090301,'XPOBOX',getDate(),1,getDate(),1,0,1,1);
insert into dbo.city_state_zip (csz_zip_num,csz_city_name,state_num,ctry_num,csz_zip_code) values(2090301,'',293,293,'293');
insert into dbo.states (state_num,state_name,pmp_enabled,real_time_reporting_on, state_code) values(293,'',0,0, '93');

-- 4. ITEM CANNOT BE SHIPPED TO P.O. BOX  @activePobox='Y'
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq,sys_user_num,edi_message_id,edi_transaction_code,image_available,script_legible,clone_rx,auto_refill, dispensed_product_id, order_invoice_number, trading_partner_num, prod_generic_ref_num) values (2090401,1,1,N'FILLRX',N'N',N'N',N'N',N'N', 2090401, '2090401', 2090401, 'GPI');
insert into dbo.products (prod_id,prod_active,prod_rx_required,prod_name_desc,prod_unit_of_use,prod_number,prod_compound) values(2090401,N'Y',N'Y',N'',N'Y',N'2090401',N'N');
insert into dbo.state_cs_products(state_cs_product_seq, state_num, prod_dea, prod_id, pobox_active) values(2090401, 294, 3, 2090401, 'Y');
insert into dbo.states (state_num,state_name,pmp_enabled,real_time_reporting_on, state_code) values(294,'',0,0, '94');
insert into dbo.order_lines (order_line_num,order_num,e_script_msg_attribute_seq,order_line_type_num,return_replace_ecs,return_replace_inventory,return_replace_payment_card, order_line_status_num) values(294,2090401,2090401,1,N'Y',N'Y',N'Y', 1);
insert into dbo.order_billship(order_num,ord_ship_free_shipping, patient_ship_address_seq) values(2090401,'Y', 2090401);
insert into dbo.order_header (order_num,order_date,order_high_priority,order_picked,order_validated, order_invoice_number, trading_partner_num) values(2090401,N'2022-10-01 19:49:30.447',N'Y',N'Y',N'Y', '2090401', 2090401);
insert into dbo.patient_address(patient_addr_seq,csz_zip_num,address_type_num,patient_num,patient_address,created,created_user_num,updated,updated_user_num,verified,override_reason_id,origination_num) values(2090401,2090401,294,2090401,'XPOBOX',getDate(),1,getDate(),1,0,1,1);
insert into dbo.city_state_zip (csz_zip_num,csz_city_name,state_num,ctry_num,csz_zip_code) values(2090401,'',294,294,'294');

-- 5. ITEM CANNOT BE SHIPPED TO P.O. BOX  @uspshippingmethoddesc >0
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq,sys_user_num,edi_message_id,edi_transaction_code,image_available,script_legible,clone_rx,auto_refill, dispensed_product_id, order_invoice_number, trading_partner_num) values (2090501,1,1,N'FILLRX',N'N',N'N',N'N',N'N', 2090501, '2090501', 2090501);
insert into dbo.products (prod_id,prod_active,prod_rx_required,prod_name_desc,prod_unit_of_use,prod_number,prod_compound) values(2090501,N'Y',N'Y',N'',N'Y',N'2090501',N'N');
insert into dbo.order_header (order_num,order_date,order_high_priority,order_picked,order_validated, order_invoice_number, trading_partner_num) values(2090501,N'2022-10-01 19:49:30.447',N'Y',N'Y',N'Y', '2090501', 2090501);
insert into dbo.order_billship(order_num,ord_ship_free_shipping, patient_ship_address_seq, ship_method_id) values(2090501,'Y', 2090501, 295);
insert into dbo.patient_address(patient_addr_seq,csz_zip_num,address_type_num,patient_num,patient_address,created,created_user_num,updated,updated_user_num,verified,override_reason_id,origination_num) values(2090501,2090501,295,2090501,'XPOBOX',getDate(),1,getDate(),1,0,1,1);
insert into dbo.shipping_methods(ship_method_id,ship_method_desc,shipping_cod) values(295,'XUPS','Y');

-- 6. ITEM CANNOT BE SHIPPED TO P.O. BOX  no error
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq,sys_user_num,edi_message_id,edi_transaction_code,image_available,script_legible,clone_rx,auto_refill, dispensed_product_id, order_invoice_number, trading_partner_num) values (2090601,1,1,N'FILLRX',N'N',N'N',N'N',N'N', 2090601, '2090601', 2090601);
insert into dbo.products (prod_id,prod_active,prod_rx_required,prod_name_desc,prod_unit_of_use,prod_number,prod_compound) values(2090601,N'Y',N'Y',N'',N'Y',N'2090601',N'N');
insert into dbo.order_header (order_num,order_date,order_high_priority,order_picked,order_validated, order_invoice_number, trading_partner_num) values(2090601,N'2022-10-01 19:49:30.447',N'Y',N'Y',N'Y', '2090601', 2090601);
insert into dbo.order_billship(order_num,ord_ship_free_shipping, patient_ship_address_seq, ship_method_id) values(2090601,'Y', 2090601, 296);
insert into dbo.patient_address(patient_addr_seq,csz_zip_num,address_type_num,patient_num,patient_address,created,created_user_num,updated,updated_user_num,verified,override_reason_id,origination_num) values(2090601,2090601,296,2090601,'XPOBOX',getDate(),1,getDate(),1,0,1,1);


-- 7. State Req(NY): requires rx triplicate serial# on-file for CS. @SHIP_STATE = 'NY'  and @NY_CONTROL_REFILL = 0
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq,sys_user_num,edi_message_id,edi_transaction_code,image_available,script_legible,clone_rx,auto_refill, dispensed_product_id) values (2090701,1,1,N'FILLRX',N'N',N'N',N'N',N'N', 2090701);
insert into dbo.products (prod_id,prod_active,prod_rx_required,prod_name_desc,prod_unit_of_use,prod_number,prod_compound, prod_dea) values(2090701,N'Y',N'Y',N'',N'Y',N'2090701',N'N', 3);
insert into dbo.order_lines (order_line_num,order_num,e_script_msg_attribute_seq,order_line_type_num,return_replace_ecs,return_replace_inventory,return_replace_payment_card, order_line_status_num) values(297,2090701,2090701,1,N'Y',N'Y',N'Y', 1);
insert into dbo.order_billship(order_num,ord_ship_free_shipping, patient_ship_address_seq) values(2090701,'Y', 2090701);
insert into dbo.patient_address(patient_addr_seq,csz_zip_num,address_type_num,patient_num,patient_address,created,created_user_num,updated,updated_user_num,verified,override_reason_id,origination_num) values(2090701,2090701,297,2090701,'XPOBOX',getDate(),1,getDate(),1,0,1,1);
insert into dbo.city_state_zip (csz_zip_num,csz_city_name,state_num,ctry_num,csz_zip_code) values(2090701,'',297,297,'297');
insert into dbo.states (state_num,state_name,pmp_enabled,real_time_reporting_on, state_code) values(297,'',0,0, 'NY');

-- 8. State Req(NY): requires rx triplicate serial# on-file for CS. @SHIP_STATE = 'NY'  and @NY_CONTROL_REFILL != 0
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq,sys_user_num,edi_message_id,edi_transaction_code,image_available,script_legible,clone_rx,auto_refill, dispensed_product_id) values (2090801,1,1,N'FILLRX',N'N',N'N',N'N',N'N', 2090801);
insert into dbo.products (prod_id,prod_active,prod_rx_required,prod_name_desc,prod_unit_of_use,prod_number,prod_compound, prod_dea) values(2090801,N'Y',N'Y',N'',N'Y',N'2090801',N'N', 3);
insert into dbo.order_lines (order_line_num,order_num,e_script_msg_attribute_seq,order_line_type_num,return_replace_ecs,return_replace_inventory,return_replace_payment_card, order_line_status_num) values(298,2090801,2090801,1,N'Y',N'Y',N'Y', 1);
insert into dbo.order_billship(order_num,ord_ship_free_shipping, patient_ship_address_seq) values(2090801,'Y', 2090801);
insert into dbo.patient_address(patient_addr_seq,csz_zip_num,address_type_num,patient_num,patient_address,created,created_user_num,updated,updated_user_num,verified,override_reason_id,origination_num) values(2090801,2090801,298,2090801,'XPOBOX',getDate(),1,getDate(),1,0,1,1);
insert into dbo.city_state_zip (csz_zip_num,csz_city_name,state_num,ctry_num,csz_zip_code) values(2090801,'',297,298,'298');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq,fill_status_num,rx_refill_num,relationship_num,post_edit_rx, triplicate_serial_num) values(2090801,9,0,0,N'N', '2090801');

-- 9. State Req(NY): requires rx triplicate serial# on-file for CS. @SHIP_STATE = 'TX'  and @TX_CONTROL_REFILL_CHK = 0
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq,sys_user_num,edi_message_id,edi_transaction_code,image_available,script_legible,clone_rx,auto_refill, dispensed_product_id) values (2090901,1,1,N'FILLRX',N'N',N'N',N'N',N'N', 2090901);
insert into dbo.products (prod_id,prod_active,prod_rx_required,prod_name_desc,prod_unit_of_use,prod_number,prod_compound, prod_dea) values(2090901,N'Y',N'Y',N'',N'Y',N'2090901',N'N', 2);
insert into dbo.order_lines (order_line_num,order_num,e_script_msg_attribute_seq,order_line_type_num,return_replace_ecs,return_replace_inventory,return_replace_payment_card, order_line_status_num) values(299,2090901,2090901,1,N'Y',N'Y',N'Y', 1);
insert into dbo.order_billship(order_num,ord_ship_free_shipping, patient_ship_address_seq) values(2090901,'Y', 2090901);
insert into dbo.patient_address(patient_addr_seq,csz_zip_num,address_type_num,patient_num,patient_address,created,created_user_num,updated,updated_user_num,verified,override_reason_id,origination_num) values(2090901,2090901,299,2090901,'XPOBOX',getDate(),1,getDate(),1,0,1,1);
insert into dbo.city_state_zip (csz_zip_num,csz_city_name,state_num,ctry_num,csz_zip_code) values(2090901,'',299,299,'299');
insert into dbo.states (state_num,state_name,pmp_enabled,real_time_reporting_on, state_code) values(299,'',0,0, 'TX');

-- 10. State Req(NY): requires rx triplicate serial# on-file for CS. @SHIP_STATE = 'TX'  and @TX_CONTROL_REFILL_CHK != 0
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq,sys_user_num,edi_message_id,edi_transaction_code,image_available,script_legible,clone_rx,auto_refill, dispensed_product_id) values (2091001,1,1,N'FILLRX',N'N',N'N',N'N',N'N', 2091001);
insert into dbo.products (prod_id,prod_active,prod_rx_required,prod_name_desc,prod_unit_of_use,prod_number,prod_compound, prod_dea) values(2091001,N'Y',N'Y',N'',N'Y',N'2091001',N'N', 2);
insert into dbo.order_lines (order_line_num,order_num,e_script_msg_attribute_seq,order_line_type_num,return_replace_ecs,return_replace_inventory,return_replace_payment_card, order_line_status_num) values(290,2091001,2091001,1,N'Y',N'Y',N'Y', 1);
insert into dbo.order_billship(order_num,ord_ship_free_shipping, patient_ship_address_seq) values(2091001,'Y', 2091001);
insert into dbo.patient_address(patient_addr_seq,csz_zip_num,address_type_num,patient_num,patient_address,created,created_user_num,updated,updated_user_num,verified,override_reason_id,origination_num) values(2091001,2091001,290,2091001,'XPOBOX',getDate(),1,getDate(),1,0,1,1);
insert into dbo.city_state_zip (csz_zip_num,csz_city_name,state_num,ctry_num,csz_zip_code) values(2091001,'',299,290,'290');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq,fill_status_num,rx_refill_num,relationship_num,post_edit_rx, triplicate_serial_num) values(2091001,9,0,0,N'N', '2091001');

/****** Ans Default Rx Plan Params Challenge ******/
insert into dbo.rx_params (narcotic_code, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, min_pct_utilization, accept_fax) values (0, 0, 1, 2, 3, 4, 5, N'Y');

-- 01
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (25010101, N'Y', N'N', N'p1', N'Y', N'25010101', N'prod number1', N'N', DEFAULT, 1);
insert into dbo.rx_params (narcotic_code, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, min_pct_utilization, accept_fax) values (1, 0, 11, 12, 13, 14, 15, N'Y');

-- 02
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (25010201, N'Y', N'N', N'p2', N'Y', N'25010102', N'prod number2', N'N', DEFAULT, null);

-- 03
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union, pp_max_refill_allowed, pp_min_util_pct, pp_max_util_pct, pp_min_quantity, pp_max_quantity, pp_min_days_supply, pp_max_days_supply) values (25020301, N'ppInformation', 0, N'A', N'B', N'Y', 1, 2, 3, 4, 5, 6, 7);

-- 04
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (25010401, N'Y', N'N', N'p4', N'Y', N'25010401', N'prod number4', N'N', DEFAULT, 4);
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number, rx_expiration_date) values (25020401, 25020401, 1, 25010401, 1, 1, N'FILLRX', N'N', N'N', N'N', N'N', N'rx250401', current_timestamp, 1, 302, 1, N'OIN250401', N'2023-01-10 19:49:30.447');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (25030401,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN250401', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (25040401, 25030401, 25020401, 1, 1, N'N', N'N', N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (25050401, N' ', 25041, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion, last_verification, patient_address2, active) values (25060401, 25050401, 0, 1, N'12345678901234567890123456789012345', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 0, 1, 1, default , N'2021-01-10 19:49:30.447', N'12345678901234567890123456789012345', N'Y');
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num, force_ship_address, ship_method_id) values (25030401, N'Y', 25060401, 25060401, 1, 2, 25060401, N'N', 83101);
insert into dbo.patient_address_type_assignments (patient_addr_seq, address_type_num, patient_num, updated, updated_user_num) values (25060401, 10, 25020401, N'2021-01-10 19:49:30.447', 1);
insert into dbo.state_cs_products (state_cs_product_seq, state_num, prod_id, prod_dea) values (25070401, 25041, 25010401, 9);
insert into dbo.rx_params (narcotic_code, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, min_pct_utilization, accept_fax) values (4, 25041, 41, 42, 43, 44, 45, null);
insert into dbo.rx_params (narcotic_code, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, min_pct_utilization, accept_fax) values (9, 25041, 91, 92, 93, 94, 95, null);

-- 05
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (25010502, N'Y', N'N', N'p5', N'Y', N'25010502', N'prod number5', N'N', DEFAULT, 5);
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number, rx_expiration_date) values (25020501, 1, 1, null, 1, 1, N'FILLRX', N'N', N'N', N'N', N'N', N'rx250401', current_timestamp, 1, 302, 1, N'OIN250501', N'2023-01-10 19:49:30.447');
insert into dbo.state_cs_products (state_cs_product_seq, state_num, prod_id, prod_dea) values (25070501, 0, 25010502, 5);
insert into dbo.rx_params (narcotic_code, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, min_pct_utilization, accept_fax) values (5, 0, 51, 52, 53, 54, 55, null);



/****** Rs Rx Link Workflow Min Utilization Challenge ******/
insert into dbo.code_value (code_value_id, fk_code_cat_id, code_value_keyword, code_value_desc, status_flag, create_ts, last_updt_ts, last_updt_by, restart_required, application_name) values (220001, N'RXSANITY', N'RXSANITY', N'RXSANITY', N'A', N'2021-01-10 19:49:30.0000001', N'2021-01-10 19:49:30.0000001', N'X', N'X', N'X');


--01
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (22010101, N'Y', N'N', N'p1', N'Y', N'pgrn220101', N'prod number1', N'N', DEFAULT);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number) values (22020101, 220101, 1, 22010101, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx2201', current_timestamp, 1, 302, 1, N'OIN2201');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (22030101,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN2201', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (22040101, 22030101, 22020101, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'2201', 22051, N'LA', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (22060101, N' ', 22051, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (22070101, 22060101, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (22030101, N'Y', 22070101, 22070101, 1, 2, 22060101);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed) values (22020101, 1, 2, 1, 0, N'N', 1001, null, 101);
insert into dbo.patient_address_type_assignments (patient_addr_seq, address_type_num, patient_num, updated, updated_user_num) values (22200101, 10, 220101, N'2021-01-10 19:49:30.447', 1);
insert into dbo.state_cs_products (state_cs_product_seq, state_num, prod_id, prod_dea) values (22210101, 22051, 22010101, 221);
insert into dbo.rx_params (narcotic_code, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (221, 22051, 1, 1, 1, 1, N'Y');
insert into dbo.rx_params_gpis (gpi_class_id, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax, min_pct_utilization) values (N'pgrn220101', 22051, 1, 1, 1, 1, N'Y', 201);
insert into dbo.system_users (sys_user_num, user_class_name, sys_user_login, sys_user_first_name, sys_user_last_name, general_status_code) values(220101, N'CC', N'LG', N'FN', N'LF', N'Y');
insert into dbo.rx_Multi_Link (rx_link_seq, parent_rx_number, child_rx_number, parent_e_script_msg_attribute_seq, child_e_script_msg_attribute_seq, active, created_sys_user_num) values(22230101, N'rx2201', N'rx2202', 22020102, 22020101, N'Y', 220101);
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number) values (22020102, 220101, 1, 22010101, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx2202', current_timestamp, 1, 302, 1, N'OIN2201');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, fill_days_supply) values (22020102, 1, 2, 1, 0, N'N', 1001, null, 101, 1);



--02
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (22010201, N'Y', N'N', N'p2', N'Y', N'pgrn220201', N'prod number2', N'N', DEFAULT);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number) values (22020201, 220201, 1, 22010201, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx2221', current_timestamp, 1, 302, 1, N'OIN2221');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (22030201,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN2221', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (22040201, 22030201, 22020201, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'2221', 22052, N'LA', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (22060201, N' ', 22052, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (22070201, 22060201, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (22030201, N'Y', 22070201, 22070201, 1, 2, 22060201);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, fill_days_supply) values (22020201, 1, 2, 1, 0, N'N', 1001, null, 101, 1);
insert into dbo.patient_address_type_assignments (patient_addr_seq, address_type_num, patient_num, updated, updated_user_num) values (22200201, 10, 220201, N'2021-01-10 19:49:30.447', 1);
insert into dbo.state_cs_products (state_cs_product_seq, state_num, prod_id, prod_dea) values (22210201, 22052, 22010201, 222);
insert into dbo.rx_params (narcotic_code, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax, min_pct_utilization) values (222, 22052, 1, 1, 1, 1, N'Y', 203);
insert into dbo.system_users (sys_user_num, user_class_name, sys_user_login, sys_user_first_name, sys_user_last_name, general_status_code) values(220201, N'CC', N'LG', N'FN', N'LF', N'Y');
insert into dbo.rx_Multi_Link (rx_link_seq, parent_rx_number, child_rx_number, parent_e_script_msg_attribute_seq, child_e_script_msg_attribute_seq, active, created_sys_user_num) values(22230201, N'rx2221', N'rx2222', 22020202, 22020201, N'Y', 220201);
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number) values (22020202, 220201, 1, 22010201, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx2222', current_timestamp, 1, 302, 1, N'OIN2221');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, fill_days_supply) values (22020202, 1, 2, 1, 0, N'N', 1001, null, 101, 1);


--03
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (22010301, N'Y', N'N', N'p3', N'Y', N'pgrn220301', N'prod number3', N'N', DEFAULT);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number) values (22020301, 220301, 1, 22010301, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx2231', current_timestamp, 1, 302, 1, N'OIN2231');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (22030301,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN2231', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (22040301, 22030301, 22020301, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'2231', 22053, N'LA', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (22060301, N' ', 22053, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (22070301, 22060301, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (22030301, N'Y', 22070301, 22070301, 1, 2, 22060301);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed) values (22020301, 1, 2, 1, 0, N'N', 1001, null, 101);
insert into dbo.patient_address_type_assignments (patient_addr_seq, address_type_num, patient_num, updated, updated_user_num) values (22200301, 10, 220301, N'2021-01-10 19:49:30.447', 1);
insert into dbo.state_cs_products (state_cs_product_seq, state_num, prod_id, prod_dea) values (22210301, 22053, 22010301, 223);
insert into dbo.rx_params (narcotic_code, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (223, 22053, 1, 1, 1, 1, N'Y');
insert into dbo.rx_params_gpis (gpi_class_id, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax, min_pct_utilization) values (N'pgrn220301', 22053, 1, 1, 1, 1, N'Y', 201);
insert into dbo.system_users (sys_user_num, user_class_name, sys_user_login, sys_user_first_name, sys_user_last_name, general_status_code) values(220301, N'CC', N'LG', N'FN', N'LF', N'Y');
insert into dbo.rx_Multi_Link (rx_link_seq, parent_rx_number, child_rx_number, parent_e_script_msg_attribute_seq, child_e_script_msg_attribute_seq, active, created_sys_user_num) values(22230301, N'rx2231', N'rx2232', 22020302, 22020301, N'Y', 220301);
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number) values (22020302, 220301, 1, 22010301, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx2232', current_timestamp, 1, 302, 1, N'OIN2231');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, fill_days_supply) values (22020302, 1, 2, 1, 0, N'N', 1001, null, 101, null);

--04
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (22010401, N'Y', N'N', N'p4', N'Y', N'pgrn220401', N'prod number4', N'N', DEFAULT);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number) values (22020401, 220401, 1, 22010401, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx2241', current_timestamp, 1, 302, 1, N'OIN2241');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (22030401,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN2241', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (22040401, 22030401, 22020401, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'2241', 22054, N'LA', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (22060401, N' ', 22054, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (22070401, 22060401, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (22030401, N'Y', 22070401, 22070401, 1, 2, 22060401);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed) values (22020401, 1, 2, 1, 0, N'N', 1001, null, 101);
insert into dbo.patient_address_type_assignments (patient_addr_seq, address_type_num, patient_num, updated, updated_user_num) values (22200401, 10, 220401, N'2021-01-10 19:49:30.447', 1);
insert into dbo.state_cs_products (state_cs_product_seq, state_num, prod_id, prod_dea) values (22210401, 22054, 22010401, 224);
insert into dbo.rx_params (narcotic_code, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (224, 22054, 1, 1, 1, 1, N'Y');
insert into dbo.rx_params_gpis (gpi_class_id, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax, min_pct_utilization) values (N'pgrn220401', 22054, 1, 1, 1, 1, N'Y', 201);
insert into dbo.system_users (sys_user_num, user_class_name, sys_user_login, sys_user_first_name, sys_user_last_name, general_status_code) values(220401, N'CC', N'LG', N'FN', N'LF', N'Y');
insert into dbo.rx_Multi_Link (rx_link_seq, parent_rx_number, child_rx_number, parent_e_script_msg_attribute_seq, child_e_script_msg_attribute_seq, active, created_sys_user_num) values(22230401, N'rx2241', N'rx2242', 22020402, 22020401, N'Y', 220401);
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number) values (22020402, 220401, 1, 22010401, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx2242', current_timestamp, 1, 302, 1, N'OIN2241');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, fill_days_supply) values (22020402, 1, 2, 1, 0, N'N', 1001, null, 101, null);


/****** P_RS_RxLinking_Insert challenge ****/
insert into dbo.code_value (code_value_id, fk_code_cat_id, code_value_keyword, code_value_desc, status_flag, create_ts, last_updt_ts, last_updt_by, restart_required, application_name) values (2010101, N'RXLINK', N'RX_LINKING_LIMIT', N'8', N'A', N'2021-01-10 19:49:30.447', N'2021-01-10 19:49:30.447', N'X', N'X', N'X');
insert into dbo.code_value (code_value_id, fk_code_cat_id, code_value_keyword, code_value_desc, status_flag, create_ts, last_updt_ts, last_updt_by, restart_required, application_name) values (2010102, N'RXLINK', N'RX_LINK_SWITCH', N'', N'A', N'2021-01-10 19:49:30.447', N'2021-01-10 19:49:30.447', N'X', N'X', N'X');
insert into dbo.system_users (sys_user_num, user_class_name, sys_user_login, sys_user_first_name, sys_user_last_name, general_status_code) values(2010101, N'CC', N'ANSUSR', N'FN', N'LF', N'Y');

-- 01, line 81 - 85
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq,sys_user_num,edi_message_id,edi_transaction_code,image_available,script_legible,clone_rx,auto_refill, rx_expiration_date) values (2010101,1,1,N'REFILLRX',N'N',N'N',N'N',N'N', N'2022-10-01 19:49:30.447');

-- 02, line 90 - 95
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq,sys_user_num,edi_message_id,edi_transaction_code,image_available,script_legible,clone_rx,auto_refill, rx_number) values (2010201,1,1,N'FILLRX',N'N',N'N',N'N',N'N', N'2010201');
insert into dbo.rx_multi_link (rx_link_seq,parent_rx_number,child_rx_number,parent_e_script_msg_attribute_seq,child_e_script_msg_attribute_seq,active) values (2010201,N'2010202',N'2010201',2010202,2010201,N'Y');

-- 03, line 97 - 103
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq,sys_user_num,edi_message_id,edi_transaction_code,image_available,script_legible,clone_rx,auto_refill, rx_number) values (2010301,1,1,N'FILLRX',N'N',N'N',N'N',N'N', N'2010301');
insert into dbo.rx_multi_link (rx_link_seq,parent_rx_number,child_rx_number,parent_e_script_msg_attribute_seq,child_e_script_msg_attribute_seq,active) values (2010301,N'2010301',N'2010302',2010301,2010302,N'Y');

-- 04, line 146 - 229
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq,sys_user_num,edi_message_id,edi_transaction_code,image_available,script_legible,clone_rx,auto_refill, rx_number, dispensed_product_id, patient_num, dispensed_drug_sig, dispensed_drug_daw) values (2010401,1,1,N'FILLRX',N'N',N'N',N'N',N'N', N'2010411', 2010401, 2010401, N'2010401', N'401');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq,sys_user_num,edi_message_id,edi_transaction_code,image_available,script_legible,clone_rx,auto_refill, rx_number, dispensed_product_id, patient_num, dispensed_drug_sig, dispensed_drug_daw) values (2010402,1,1,N'FILLRX',N'N',N'N',N'N',N'N', N'2010402', 2010401, 2010401, N'2010401', N'401');
insert into dbo.order_header (order_num,order_date,order_high_priority,order_picked,order_validated) values(2010402,N'2022-10-01 19:49:30.447',N'Y',N'Y',N'Y');
insert into dbo.order_lines (order_line_num,order_num,e_script_msg_attribute_seq,order_line_type_num,return_replace_ecs,return_replace_inventory,return_replace_payment_card) values(241,2010402,2010402,1,N'Y',N'Y',N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq,fill_status_num,rx_refill_num,relationship_num,post_edit_rx) values(2010402,9,0,0,N'N');
insert into dbo.products (prod_id,prod_active,prod_rx_required,prod_name_desc,prod_unit_of_use,prod_number,prod_compound, prod_generic_ref_num) values(2010401,N'Y',N'Y',N'',N'Y',N'2010401',N'N', N'2010401');
insert into dbo.rx_multi_link (rx_link_seq,parent_rx_number,child_rx_number,parent_e_script_msg_attribute_seq,child_e_script_msg_attribute_seq,active, created_sys_user_num) values (2010401,N'2010401',N'2010402',2010401,2010402,N'N', 2010101);
insert into dbo.rx_multi_link (rx_link_seq,parent_rx_number,child_rx_number,parent_e_script_msg_attribute_seq,child_e_script_msg_attribute_seq,active, created_sys_user_num) values (2010402,N'2010402',N'2010403',2010402,2010403,N'Y', 2010101);
insert into dbo.rx_multi_link (rx_link_seq,parent_rx_number,child_rx_number,parent_e_script_msg_attribute_seq,child_e_script_msg_attribute_seq,active, created_sys_user_num) values (2010403,N'2010403',N'2010404',2010403,2010404,N'Y', 2010101);
insert into dbo.rx_multi_link (rx_link_seq,parent_rx_number,child_rx_number,parent_e_script_msg_attribute_seq,child_e_script_msg_attribute_seq,active, created_sys_user_num) values (2010404,N'2010404',N'2010405',2010404,2010405,N'Y', 2010101);
insert into dbo.rx_multi_link (rx_link_seq,parent_rx_number,child_rx_number,parent_e_script_msg_attribute_seq,child_e_script_msg_attribute_seq,active, created_sys_user_num) values (2010405,N'2010405',N'2010406',2010405,2010406,N'Y', 2010101);
insert into dbo.rx_multi_link (rx_link_seq,parent_rx_number,child_rx_number,parent_e_script_msg_attribute_seq,child_e_script_msg_attribute_seq,active, created_sys_user_num) values (2010406,N'2010406',N'2010407',2010406,2010407,N'Y', 2010101);
insert into dbo.rx_multi_link (rx_link_seq,parent_rx_number,child_rx_number,parent_e_script_msg_attribute_seq,child_e_script_msg_attribute_seq,active, created_sys_user_num) values (2010407,N'2010407',N'2010408',2010407,2010408,N'Y', 2010101);
insert into dbo.rx_multi_link (rx_link_seq,parent_rx_number,child_rx_number,parent_e_script_msg_attribute_seq,child_e_script_msg_attribute_seq,active, created_sys_user_num) values (2010408,N'2010408',N'2010409',2010408,2010409,N'Y', 2010101);
insert into dbo.rx_multi_link (rx_link_seq,parent_rx_number,child_rx_number,parent_e_script_msg_attribute_seq,child_e_script_msg_attribute_seq,active, created_sys_user_num) values (2010409,N'2010409',N'2010410',2010409,2010410,N'Y', 2010101);

-- 06, line 236
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq,sys_user_num,edi_message_id,edi_transaction_code,image_available,script_legible,clone_rx,auto_refill, rx_number, dispensed_product_id, patient_num, dispensed_drug_sig, dispensed_drug_daw) values (2010601,1,1,N'FILLRX',N'N',N'N',N'N',N'N', N'2010601', 2010601, 2010601, N'2010601', N'601');
insert into dbo.order_header (order_num,order_date,order_high_priority,order_picked,order_validated) values(2010601,N'2022-10-01 19:49:30.447',N'Y',N'Y',N'Y');
insert into dbo.order_lines (order_line_num,order_num,e_script_msg_attribute_seq,order_line_type_num,return_replace_ecs,return_replace_inventory,return_replace_payment_card) values(261,2010601,2010601,1,N'Y',N'Y',N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq,fill_status_num,rx_refill_num,relationship_num,post_edit_rx) values(2010601,9,0,0,N'N');

-- 07, line 246 - 265
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq,sys_user_num,edi_message_id,edi_transaction_code,image_available,script_legible,clone_rx,auto_refill, rx_number) values (2010701,1,1,N'FILLRX',N'N',N'N',N'N',N'N', N'2010701');
insert into dbo.rx_multi_link (rx_link_seq,parent_rx_number,child_rx_number,parent_e_script_msg_attribute_seq,child_e_script_msg_attribute_seq,active, created_sys_user_num) values (2010701,N'2010701',N'2010702',2010701,2010702,N'Y', 2010101);

-- 08, line 270 - 286
insert into dbo.rx_multi_link (rx_link_seq,parent_rx_number,child_rx_number,parent_e_script_msg_attribute_seq,child_e_script_msg_attribute_seq,active, created_sys_user_num) values (2010801,N'2010801',N'2010802',2010801,2010802,N'Y', 2010101);

/****** Ans Validate Hippa Address Challenge ******/
--01
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON, consent_age) values (N'8001', 80501, N'LA', 1, 1, 16);
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (8030101,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN801', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (8040101, 8030101, 8020101, 1, 1, N'N', N'N', N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (8060101, N' ', 80501, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (8070101, 8060101, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num, force_ship_address) values (8030101, N'Y', 8070101, 8070101, 1, 2, 8060101, N'Y');

--02
insert into dbo.shipping_methods (ship_method_id, ship_method_desc, shipping_cod) values (83101, N'UPS 123', N'Y');
insert into dbo.patient_general (patient_num, patient_id_code, general_status_code, language_id, title_id, patient_first_name, patient_last_name, patient_dob) values (8130201, null, N'X', 1, 1, N'F', N'L', N'2021-01-10 19:49:30.447');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (8030201,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN802', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (8040201, 8030201, 8020201, 1, 1, N'N', N'N', N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (8060201, N' ', 80501, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (8070201, 8060201, 0, 8130201, N'general delivery', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num, force_ship_address, ship_method_id) values (8030201, N'Y', 8070201, 8070201, 1, 2, 8060201, N'N', 83101);

--03
--insert into dbo.shipping_methods (ship_method_id, ship_method_desc, shipping_cod) values (83101, N'UPS 123', N'Y');
insert into dbo.patient_general (patient_num, patient_id_code, general_status_code, language_id, title_id, patient_first_name, patient_last_name, patient_dob) values (8130301, null, N'X', 1, 1, N'F', N'L', N'2021-01-10 19:49:30.447');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (8030301,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN803', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (8040301, 8030301, 8020301, 1, 1, N'N', N'N', N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (8060301, N' ', 80501, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (8070301, 8060301, 0, 8130301, N' delivery', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 0, 0, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num, force_ship_address, ship_method_id) values (8030301, N'Y', 8070301, 8070301, 1, 2, 8060301, N'N', 83101);

--04
--insert into dbo.shipping_methods (ship_method_id, ship_method_desc, shipping_cod) values (83101, N'UPS 123', N'Y');
insert into dbo.patient_general (patient_num, patient_id_code, general_status_code, language_id, title_id, patient_first_name, patient_last_name, patient_dob) values (8130401, null, N'X', 1, 1, N'F', N'L', N'2021-01-10 19:49:30.447');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (8030401,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN804', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (8040401, 8030401, 8020401, 1, 1, N'N', N'N', N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (8060401, N' ', 80501, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion, last_verification) values (8070401, 8060401, 0, 8130401, N' delivery', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 0, 3, 1, default , N'2021-01-10 19:49:30.447');
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num, force_ship_address, ship_method_id) values (8030401, N'Y', 8070401, 8070401, 1, 2, 8060401, N'N', 83101);

--05
--insert into dbo.shipping_methods (ship_method_id, ship_method_desc, shipping_cod) values (83101, N'UPS 123', N'Y');
insert into dbo.patient_general (patient_num, patient_id_code, general_status_code, language_id, title_id, patient_first_name, patient_last_name, patient_dob) values (8130501, null, N'X', 1, 1, N'F', N'L', N'2021-01-10 19:49:30.447');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (8030501,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN805', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (8040501, 8030501, 8020501, 1, 1, N'N', N'N', N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (8060501, N' ', 80501, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion, last_verification, patient_address2) values (8070501, 8060501, 0, 8130501, N'123456789012345678901234567890123456', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 0, 1, 1, default , N'2021-01-10 19:49:30.447', N'123456789012345678901234567890123456');
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num, force_ship_address, ship_method_id) values (8030501, N'Y', 8070501, 8070501, 1, 2, 8060501, N'N', 83101);

--06
--insert into dbo.shipping_methods (ship_method_id, ship_method_desc, shipping_cod) values (83101, N'UPS 123', N'Y');
insert into dbo.patient_general (patient_num, patient_id_code, general_status_code, language_id, title_id, patient_first_name, patient_last_name, patient_dob) values (8130601, null, N'X', 1, 1, N'F', N'L', N'2021-01-10 19:49:30.447');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (8030601,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN806', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (8040601, 8030601, 8020601, 1, 1, N'N', N'N', N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (8060601, N' ', 80601, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion, last_verification, patient_address2) values (8070601, 8060601, 0, 8130601, N'12345678901234567890123456789012345', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 0, 1, 1, default , N'2021-01-10 19:49:30.447', N'12345678901234567890123456789012345');
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num, force_ship_address, ship_method_id) values (8030601, N'Y', 8070601, null, 1, 2, 8060601, N'N', 83101);

--07
--insert into dbo.shipping_methods (ship_method_id, ship_method_desc, shipping_cod) values (83101, N'UPS 123', N'Y');
insert into dbo.patient_general (patient_num, patient_id_code, general_status_code, language_id, title_id, patient_first_name, patient_last_name, patient_dob) values (8130701, null, N'X', 1, 1, N'F', N'L', N'2021-01-10 19:49:30.447');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (8030701,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN807', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (8040701, 8030701, 8020701, 1, 1, N'N', N'N', N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (8060701, N' ', 80701, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion, last_verification, patient_address2, active) values (8070701, 8060701, 0, 8130701, N'12345678901234567890123456789012345', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 0, 1, 1, default , N'2021-01-10 19:49:30.447', N'12345678901234567890123456789012345', N'N');
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num, force_ship_address, ship_method_id) values (8030701, N'Y', 8070701, 8070701, 1, 2, 8060701, N'N', 83101);

--08
--insert into dbo.shipping_methods (ship_method_id, ship_method_desc, shipping_cod) values (83101, N'UPS 123', N'Y');
insert into dbo.patient_general (patient_num, patient_id_code, general_status_code, language_id, title_id, patient_first_name, patient_last_name, patient_dob) values (8130801, null, N'X', 1, 1, N'F', N'L', N'2021-01-10 19:49:30.447');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (8030801,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN808', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (8040801, 8030801, 8020801, 1, 1, N'N', N'N', N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (8060801, N' ', 80801, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion, last_verification, patient_address2, active) values (8070801, 8060801, 0, 8130801, N'12345678901234567890123456789012345', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 0, 1, 1, default , N'2021-01-10 19:49:30.447', N'12345678901234567890123456789012345', N'Y');
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num, force_ship_address, ship_method_id) values (8030801, N'Y', 8070801, 8070801, 1, 2, 8060801, N'N', 83101);

--09
--insert into dbo.shipping_methods (ship_method_id, ship_method_desc, shipping_cod) values (83101, N'UPS 123', N'Y');
insert into dbo.patient_general (patient_num, patient_id_code, general_status_code, language_id, title_id, patient_first_name, patient_last_name, patient_dob) values (8130901, null, N'X', 1, 1, N'F', N'L', N'2021-01-10 19:49:30.447');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number, rx_expiration_date) values (8020901, 8130901, 1, 1, 1, 1, N'FILLRX', N'N', N'N', N'N', N'N', N'rx80901', current_timestamp, 1, 302, 1, N'OIN809', N'2023-01-10 19:49:30.447');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (8030901,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN809', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (8040901, 8030901, 8020901, 1, 1, N'N', N'N', N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (8060901, N' ', 80901, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion, last_verification, patient_address2, active) values (8070901, 8060901, 0, 8130901, N'12345678901234567890123456789012345', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 0, 1, 1, default , N'2021-01-10 19:49:30.447', N'12345678901234567890123456789012345', N'Y');
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num, force_ship_address, ship_method_id) values (8030901, N'Y', 8070901, 8070901, 1, 2, 8060901, N'N', 83101);
insert into dbo.patient_family (patient_family_seq, patient_num, family_id, head_of_household) values (8320901, 8130901, N'1', N'Y');

--10
--insert into dbo.shipping_methods (ship_method_id, ship_method_desc, shipping_cod) values (83101, N'UPS 123', N'Y');
insert into dbo.patient_general (patient_num, patient_id_code, general_status_code, language_id, title_id, patient_first_name, patient_last_name, patient_dob) values (8131001, null, N'A', 1, 1, N'F', N'L', N'2021-01-10 19:49:30.447');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number, rx_expiration_date) values (8021001, 8131001, 1, 1, 1, 1, N'FILLRX', N'N', N'N', N'N', N'N', N'rx81001', current_timestamp, 1, 302, 1, N'OIN810', N'2023-01-10 19:49:30.447');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (8031001,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN810', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (8041001, 8031001, 8021001, 1, 1, N'N', N'N', N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (8061001, N' ', 81001, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion, last_verification, patient_address2, active) values (8071001, 8061001, 0, 8131001, N'12345678901234567890123456789012345', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 0, 1, 1, default , N'2021-01-10 19:49:30.447', N'12345678901234567890123456789012345', N'Y');
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num, force_ship_address, ship_method_id) values (8031001, N'Y', 8071001, 8071001, 1, 1, 8061001, N'N', 83101);
insert into dbo.patient_family (patient_family_seq, patient_num, family_id, head_of_household) values (8321001, 8131001, N'1', N'Y');
insert into dbo.patient_plans (pp_num, patient_num, coverage_type_id, relationship_num) values (2,8131001, 4, 1);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, fill_days_supply) values (8021001, 2, 2, 1, 0, N'N', 1001, null, 101, 1);


/****** Rs Rx Linking Substitution Get Challenge ******/
--01 NEWRX
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number, rx_expiration_date) values (7010101, 1, 1, 7010101, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx70101', current_timestamp, 1, 302, 1, N'OIN70101', N'2023-01-10 19:49:30.447');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, fill_days_supply) values (7010101, 7010101, 2, 1, 0, N'N', 1001, null, 101, 90);

--02 REFILLRX
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number, rx_expiration_date) values (7010201, 1, 1, 7010201, 1, N'REFILLRX', N'N', N'N', N'N', N'N', N'rx70201', current_timestamp, 1, 302, 1, N'OIN70201', N'2023-01-10 19:49:30.447');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, fill_days_supply) values (7010201, 7010201, 2, 1, 0, N'N', 1001, null, 101, 90);

--03 quantity lowered
insert into dbo.code_value (code_value_id, fk_code_cat_id, code_value_keyword, code_value_desc, status_flag, create_ts, last_updt_ts, last_updt_by, restart_required, application_name) values (70001, N'QTY_CHNG', N'QTY_CHANGE_FLAG', N'', N'A', N'2021-01-10 19:49:30.447', N'2021-01-10 19:49:30.447', N'X', N'X', N'X');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number, rx_expiration_date) values (7010301, 7010301, 1, 7010301, 7010301, 1, N'FILLRX', N'N', N'N', N'N', N'N', N'rx70301', current_timestamp, 1, 302, 1, N'OIN70301', N'2023-01-10 19:49:30.447');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, fill_days_supply) values (7010301, 7010301, 2, 1, 0, N'N', 1001, null, 101, 90);
insert into dbo.patient_attributes (patient_num, patient_smokes, patient_gen_if_avail, patient_twin, patient_married, patient_disabled, patient_safety_caps, patient_family_planning, patient_student, workflow_auto_notify, bill_secondary_coverage, insulin_pump_required) values(7010301, N'N', N'Y', N'Y', N'Y', N'Y', N'Y', N'Y', N'Y', N'Y', N'Y', N'Y');
insert into dbo.system_users (sys_user_num, user_class_name, sys_user_login, sys_user_first_name, sys_user_last_name, general_status_code) values(70001, N'CC', N'LG', N'FN', N'LF', N'Y');
insert into dbo.rx_Multi_Link (rx_link_seq, parent_rx_number, child_rx_number, parent_e_script_msg_attribute_seq, child_e_script_msg_attribute_seq, active, created_sys_user_num) values(70001, N'rx70301', N'rx70302', 7010301, 7010302, N'Y', 70001);
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number, rx_expiration_date, rx_status_code_num) values (7010302, 7010302, 1, 7010302, 7010302, 1, N'FILLRX', N'N', N'N', N'N', N'N', N'rx70302', current_timestamp, 1, 302, 1, N'OIN70302', N'2023-01-10 19:49:30.447', 1);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, fill_days_supply) values (7010302, 7010302, 2, 1, 0, N'N', 1001, null, 101, 90);
insert into dbo.quantity_change_tracking(e_script_msg_attribute_seq, is_quantity_lowered) values(7010301, 'Y');

--04 already been selected
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number, rx_expiration_date) values (7010401, 7010401, 1, 7010401, 7010401, 1, N'FILLRX', N'N', N'N', N'N', N'N', N'rx70401', current_timestamp, 1, 302, 1, N'OIN70401', N'2023-01-10 19:49:30.447');
insert into dbo.system_users (sys_user_num, user_class_name, sys_user_login, sys_user_first_name, sys_user_last_name, general_status_code) values(70004, N'CC', N'LG', N'FN', N'LF', N'Y');
insert into dbo.rx_Multi_Link (rx_link_seq, parent_rx_number, child_rx_number, parent_e_script_msg_attribute_seq, child_e_script_msg_attribute_seq, active, created_sys_user_num) values(70004, N'rx70401', N'rx70402', 7010401, 7010402, N'Y', 70004);
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number, rx_expiration_date, rx_status_code_num, trading_partner_num) values (7010402, 7010402, 1, 7010402, 7010402, 1, N'FILLRX', N'N', N'N', N'N', N'N', N'rx70402', current_timestamp, 1, 302, 1, N'OIN70402', N'2023-01-10 19:49:30.447', 1, 7010402);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, fill_days_supply) values (7010402, 7010402, 2, 1, 0, N'N', 1001, null, 101, 90);
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (7010402, 1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN704', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (7010402, 7010402, 7010402, 1, 1, N'N', N'N', N'Y');
insert into dbo.trading_partner_general (trading_partner_num, trading_partner_type_id, region_num, account_num, general_status_code, inventory_verification, is24hr, override_mandatory_fields, override_data_validation, common_doctor, translation_mode, workflow_auto_notify, tp_name) values(7010402, 1, 1, 1, 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'trading partner location');

--05 is not eligible
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number, rx_expiration_date) values (7010501, 7010501, 1, 7010501, 7010501, 1, N'FILLRX', N'N', N'N', N'N', N'N', N'rx70501', current_timestamp, 1, 302, 1, N'OIN70501', N'2023-01-10 19:49:30.447');
insert into dbo.system_users (sys_user_num, user_class_name, sys_user_login, sys_user_first_name, sys_user_last_name, general_status_code) values(70005, N'CC', N'LG', N'FN', N'LF', N'Y');
insert into dbo.rx_Multi_Link (rx_link_seq, parent_rx_number, child_rx_number, parent_e_script_msg_attribute_seq, child_e_script_msg_attribute_seq, active, created_sys_user_num) values(70005, N'rx70501', N'rx70502', 7010501, 7010502, N'Y', 70005);
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, rx_expiration_date, rx_status_code_num) values (7010502, 7010502, 1, 7010502, 7010502, 1, N'FILLRX', N'N', N'N', N'N', N'N', N'rx70502', current_timestamp, 1, 302, 1, N'2023-01-10 19:49:30.447', 1);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, fill_days_supply) values (7010502, 7010502, 2, 1, 0, N'N', 1001, null, 101, 90);
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (7010502, 1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN705', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (7010502, 7010502, 7010502, 2, 1, N'N', N'N', N'Y');
insert into dbo.auto_escript_filler(auto_efiller_seq, escript_id) values(7010502, 7010502);

--06 already been selected on current order
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number, rx_expiration_date) values (7010601, 7010601, 1, 7010601, 7010601, 1, N'FILLRX', N'N', N'N', N'N', N'N', N'rx70601', current_timestamp, 1, 302, 1, N'OIN70601', N'2023-01-10 19:49:30.447');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (7010601, 7010602, 7010601, 1, 1, N'N', N'N', N'Y');
insert into dbo.system_users (sys_user_num, user_class_name, sys_user_login, sys_user_first_name, sys_user_last_name, general_status_code) values(70006, N'CC', N'LG', N'FN', N'LF', N'Y');
insert into dbo.rx_Multi_Link (rx_link_seq, parent_rx_number, child_rx_number, parent_e_script_msg_attribute_seq, child_e_script_msg_attribute_seq, active, created_sys_user_num) values(70006, N'rx70601', N'rx70602', 7010601, 7010602, N'Y', 70006);
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, rx_expiration_date, rx_status_code_num) values (7010602, 7010602, 1, 7010602, 7010602, 1, N'FILLRX', N'N', N'N', N'N', N'N', N'rx70602', current_timestamp, 1, 302, 1, N'2023-01-10 19:49:30.447', 1);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, fill_days_supply) values (7010602, 7010602, 2, 1, 0, N'N', 1001, null, 101, 90);
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (7010602, 1, 98, N'2021-01-10 19:49:30.447', N'N', N'OIN705', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (7010602, 7010602, 7010602, 1, 1, N'N', N'N', N'Y');

--07 patient plan information not found
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number, rx_expiration_date) values (7010701, 7010701, 1, 7010701, 7010701, 1, N'FILLRX', N'N', N'N', N'N', N'N', N'rx70701', current_timestamp, 1, 302, 1, N'OIN70701', N'2023-01-10 19:49:30.447');
insert into dbo.system_users (sys_user_num, user_class_name, sys_user_login, sys_user_first_name, sys_user_last_name, general_status_code) values(70007, N'CC', N'LG', N'FN', N'LF', N'Y');
insert into dbo.rx_Multi_Link (rx_link_seq, parent_rx_number, child_rx_number, parent_e_script_msg_attribute_seq, child_e_script_msg_attribute_seq, active, created_sys_user_num) values(70007, N'rx70701', N'rx70702', 7010701, 7010702, N'Y', 70007);
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, rx_expiration_date, rx_status_code_num) values (7010702, 7010702, 1, 7010702, 7010702, 1, N'FILLRX', N'N', N'N', N'N', N'N', N'rx70702', current_timestamp, 1, 302, 1, N'2023-01-10 19:49:30.447', 1);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, fill_days_supply) values (7010702, 7010702, 2, 1, 0, N'N', 1001, null, 101, 90);

--08 patient plan is inactive
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number, rx_expiration_date) values (7010801, 7010801, 1, 7010801, 7010801, 1, N'FILLRX', N'N', N'N', N'N', N'N', N'rx70801', current_timestamp, 1, 302, 1, N'OIN70801', N'2023-01-10 19:49:30.447');
insert into dbo.system_users (sys_user_num, user_class_name, sys_user_login, sys_user_first_name, sys_user_last_name, general_status_code) values(70008, N'CC', N'LG', N'FN', N'LF', N'Y');
insert into dbo.rx_Multi_Link (rx_link_seq, parent_rx_number, child_rx_number, parent_e_script_msg_attribute_seq, child_e_script_msg_attribute_seq, active, created_sys_user_num) values(70008, N'rx70801', N'rx70802', 7010801, 7010802, N'Y', 70008);
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, rx_expiration_date, rx_status_code_num) values (7010802, 7010802, 1, 7010802, 7010802, 1, N'FILLRX', N'N', N'N', N'N', N'N', N'rx70802', current_timestamp, 1, 302, 1, N'2023-01-10 19:49:30.447', 1);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, fill_days_supply) values (7010802, 7010802, 2, 1, 0, N'N', 1001, null, 101, 90);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (7010802, N'ppInformation', 7010802, N'A', N'B', N'Y');
insert into dbo.patient_plans (pp_num, patient_num, coverage_type_id) values (7010802,7010802, 4);

/****** Ans Rx Narc Code Check Challenge ******/
-- 01
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (6010101, N'Y', N'N', N'p1', N'Y', N'6220101', N'prod number1', N'N', DEFAULT);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number) values (6020101, 1, 1, 6010101, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx601', current_timestamp, 1, 302, 1, N'OIN601');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (6030101,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN601', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (6040101, 6030101, 6020101, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'6001', 60501, N'LA', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (6060101, N' ', 60501, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (6070101, 6060101, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (6030101, N'Y', 6070101, 6070101, 1, 2, 6060101);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (6020101, current_timestamp, 6080101, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 6090101,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (6100101, N'N', 6090101, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed) values (6020101, 6100101, 2, 1, 0, N'N', 1001, null, 101);
insert into dbo.state_cs_products (state_cs_product_seq, state_num, prod_id, prod_dea) values (6210101, 60501, 6010101, 61);
insert into dbo.rx_params (narcotic_code, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (61, 60501, 1, 1, 1, 1, N'Y');
insert into dbo.rx_params_gpis (gpi_class_id, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (N'6220101', 60501, 1, 1, 1, 1, N'Y');

-- 02
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (6010201, N'Y', N'N', N'p2', N'Y', N'6220201', N'prod number2', N'N', DEFAULT);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number) values (6020201, 1, 1, 6010201, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx602', current_timestamp, 3, 302, 1, N'OIN602');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (6030201,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN602', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (6040201, 6030201, 6020201, 1, 1, N'N', N'N', N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (6060201, N' ', 60501, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (6070201, 6060201, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (6030201, N'Y', 6070201, 6070201, 1, 2, 6060201);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (6020201, current_timestamp, 6080201, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 6090201,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (6100201, N'N', 6090201, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed) values (6020201, 6100201, 2, 1, 0, N'N', 1001, null, 101);
insert into dbo.state_cs_products (state_cs_product_seq, state_num, prod_id, prod_dea) values (6210201, 60501, 6010201, 62);
insert into dbo.rx_params (narcotic_code, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (62, 60501, 1, 1, 1, 1, N'Y');
insert into dbo.rx_params_gpis (gpi_class_id, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (N'6220201', 60501, 1, 1, 1, 1, N'Y');

-- 03
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (6010301, N'Y', N'N', N'p2', N'Y', N'6220301', N'prod number3', N'N', DEFAULT);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number) values (6020301, 1, 1, 6010301, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx603', N'2021-01-10 19:49:30', 1, 302, 1, N'OIN603');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (6030301,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN603', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (6040301, 6030301, 6020301, 1, 1, N'N', N'N', N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (6060301, N' ', 60501, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (6070301, 6060301, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (6030301, N'Y', 6070301, 6070301, 1, 2, 6060301);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (6020301, current_timestamp, 6080301, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 6090301,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (6100301, N'N', 6090301, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed) values (6020301, 6100301, 2, 1, 0, N'N', 1001, null, 101);
insert into dbo.state_cs_products (state_cs_product_seq, state_num, prod_id, prod_dea) values (6210301, 60501, 6010301, 63);
insert into dbo.rx_params (narcotic_code, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (63, 60501, 1, 1, 1, 1, N'Y');
insert into dbo.rx_params_gpis (gpi_class_id, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (N'6220301', 60501, 1, 0, 1, 1, N'Y');

-- 04
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (6010401, N'Y', N'N', N'p4', N'Y', N'6220401', N'prod number4', N'N', DEFAULT);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number) values (6020401, 1, 1, 6010401, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx604', current_timestamp, 1, 302, 1, N'OIN604');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (6030401,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN604', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (6040401, 6030401, 6020401, 1, 1, N'N', N'N', N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (6060401, N' ', 60501, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (6070401, 6060401, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (6030401, N'Y', 6070401, 6070401, 1, 2, 6060401);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (6020401, current_timestamp, 6080401, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 6090401,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (6100401, N'N', 6090401, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, fill_days_supply) values (6020401, 6100401, 2, 1, 0, N'N', 1001, null, 101, 10);
insert into dbo.state_cs_products (state_cs_product_seq, state_num, prod_id, prod_dea) values (6210401, 60501, 6010401, 64);
insert into dbo.rx_params (narcotic_code, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (64, 60501, 1, 1, 1, 1, N'Y');
insert into dbo.rx_params_gpis (gpi_class_id, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (N'6220401', 60501, 1, 1, 1, 1, N'Y');


-- 05
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (6010501, N'Y', N'N', N'p5', N'Y', N'6220501', N'prod number5', N'N', DEFAULT);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number, origination_num) values (6020501, 1, 1, 6010501, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx605', current_timestamp, 1, 302, 1, N'OIN605', 4);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (6030501,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN605', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (6040501, 6030501, 6020501, 1, 1, N'N', N'N', N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (6060501, N' ', 60501, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (6070501, 6060501, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (6030501, N'Y', 6070501, 6070501, 1, 2, 6060501);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (6020501, current_timestamp, 6080501, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 6090501,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (6100501, N'N', 6090501, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, fill_days_supply) values (6020501, 6100501, 2, 1, 0, N'N', 1001, null, 101, 1);
insert into dbo.state_cs_products (state_cs_product_seq, state_num, prod_id, prod_dea) values (6210501, 60501, 6010501, 65);
insert into dbo.rx_params (narcotic_code, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (65, 60501, 1, 1, 1, 1, N'Y');
insert into dbo.rx_params_gpis (gpi_class_id, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (N'6220501', 60501, 1, 1, 1, 1, N'N');


-- 06
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (6010601, N'Y', N'N', N'p6', N'Y', N'6220601X', N'prod number6', N'N', DEFAULT);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number) values (6020601, 1, 1, 6010601, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx606', current_timestamp, 3, 302, 1, N'OIN606');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (6030601,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN606', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (6040601, 6030601, 6020601, 1, 1, N'N', N'N', N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (6060601, N' ', 60501, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (6070601, 6060601, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (6030601, N'Y', 6070601, 6070601, 1, 2, 6060601);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (6020601, current_timestamp, 6080601, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 6090601,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (6100601, N'N', 6090601, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed) values (6020601, 6100601, 2, 1, 0, N'N', 1001, null, 101);
insert into dbo.state_cs_products (state_cs_product_seq, state_num, prod_id, prod_dea) values (6210601, 60501, 6010601, 2);
insert into dbo.rx_params (narcotic_code, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (2, 60501, 1, 1, 1, 1, N'Y');
insert into dbo.rx_params_gpis (gpi_class_id, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (N'6220601', 60501, 1, 1, 1, 1, N'Y');


-- 07
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (6010701, N'Y', N'N', N'p7', N'Y', N'6220701', N'prod number7', N'N', DEFAULT);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number) values (6020701, 1, 1, 6010701, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx607', N'2021-01-10 19:49:30', 1, 302, 1, N'OIN607');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (6030701,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN607', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (6040701, 6030701, 6020701, 1, 1, N'N', N'N', N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (6060701, N' ', 60501, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (6070701, 6060701, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (6030701, N'Y', 6070701, 6070701, 1, 2, 6060701);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (6020701, current_timestamp, 6080701, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 6090701,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (6100701, N'N', 6090701, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed) values (6020701, 6100701, 2, 1, 0, N'N', 1001, null, 101);
insert into dbo.state_cs_products (state_cs_product_seq, state_num, prod_id, prod_dea) values (6210701, 60501, 6010701, 2);
--insert into dbo.rx_params (narcotic_code, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (2, 60501, 1, 1, 1, 1, N'Y');
insert into dbo.rx_params_gpis (gpi_class_id, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (N'6220701', 60501, 1, 0, 1, 1, N'Y');

-- 08
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (6010801, N'Y', N'N', N'p8', N'Y', N'6220801X', N'prod number8', N'N', DEFAULT);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number) values (6020801, 1, 1, 6010801, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx608', current_timestamp, 1, 302, 1, N'OIN608');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (6030801,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN608', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (6040801, 6030801, 6020801, 1, 1, N'N', N'N', N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (6060801, N' ', 60501, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (6070801, 6060801, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (6030801, N'Y', 6070801, 6070801, 1, 2, 6060801);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (6020801, current_timestamp, 6080801, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 6090801,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (6100801, N'N', 6090801, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, fill_days_supply) values (6020801, 6100801, 2, 1, 0, N'N', 1001, null, 101, 10);
insert into dbo.state_cs_products (state_cs_product_seq, state_num, prod_id, prod_dea) values (6210801, 60501, 6010801, 2);
--insert into dbo.rx_params (narcotic_code, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (2, 60501, 1, 1, 1, 1, N'Y');
insert into dbo.rx_params_gpis (gpi_class_id, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (N'6220801', 60501, 1, 1, 1, 1, N'Y');


-- 09
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (6010901, N'Y', N'N', N'p9', N'Y', N'6220901X', N'prod number9', N'N', DEFAULT);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number, origination_num) values (6020901, 1, 1, 6010901, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx609', current_timestamp, 1, 302, 1, N'OIN609', 4);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (6030901,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN609', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (6040901, 6030901, 6020901, 1, 1, N'N', N'N', N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (6060901, N' ', 60501, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (6070901, 6060901, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (6030901, N'Y', 6070901, 6070901, 1, 2, 6060901);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (6020901, current_timestamp, 6080901, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 6090901,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (6100901, N'N', 6090901, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, fill_days_supply) values (6020901, 6100901, 2, 1, 0, N'N', 1001, null, 101, 1);
insert into dbo.state_cs_products (state_cs_product_seq, state_num, prod_id, prod_dea) values (6210901, 60501, 6010901, 3);
insert into dbo.rx_params (narcotic_code, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (3, 60501, 1, 1, 1, 1, N'N');
insert into dbo.rx_params_gpis (gpi_class_id, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (N'6220901', 60501, 1, 1, 1, 1, N'N');


-- 10
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (6011001, N'Y', N'N', N'p10', N'Y', N'6221001X', N'prod number10', N'N', DEFAULT);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number, doctor_num) values (6021001, 1, 1, 6011001, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx606', current_timestamp, 1, 302, 1, N'OIN610', 61001);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (6031001,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN610', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (6041001, 6031001, 6021001, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'LA', 60510, N'LA', 1, 1);
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'LB', 60511, N'LB', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (6061001, N' ', 60510, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (6071001, 6061001, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (6031001, N'Y', 6071001, 6071001, 1, 2, 6061001);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (6021001, current_timestamp, 6081001, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 6091001,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (6101001, N'N', 6091001, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed) values (6021001, 6101001, 2, 1, 0, N'N', 1001, null, 101);
insert into dbo.state_cs_products (state_cs_product_seq, state_num, prod_id, prod_dea) values (6211001, 60510, 6011001, 2);
insert into dbo.rx_params (narcotic_code, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (2, 60510, 1, 1, 1, 1, N'Y');
insert into dbo.rx_params_gpis (gpi_class_id, state_num, num_refills_allowed, num_days_from_orig_date, num_days_expire, max_days_supply, accept_fax) values (N'6221001', 60510, 1, 1, 1, 1, N'Y');
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (6061002, N' ', 60511, 1, N' ');
insert into dbo.trading_partner_address (trading_partner_num, tp_addr_seq, tp_address, active, spi, csz_zip_num) values (61001, 1, N'tpa5', N'Y', N'spi5', 6061002);




/****** Ans RX Sanity Check Challenge ******/
-- 01
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (5010101, N'Y', N'N', N'p1', N'Y', N'PGRN1', N'prod number1', N'N', DEFAULT);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity) values (5020101, 1, 1, 5010101, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx501', current_timestamp, 1, 302, 1);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5030101,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5040101, 5030101, 5020101, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'5001', 50501, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5060101, N' ', 50501, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5070101, 5060101, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5030101, N'Y', 5070101, 5070101, 1, 2, 5060101);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5020101, current_timestamp, 5080101, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5090101,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5100101, N'N', 5090101, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed) values (5020101, 5100101, 2, 1, 0, N'N', 1001, null, 101);

-- 02
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (5010201, N'Y', N'N', N'p2', N'Y', N'PGRN2', N'prod number2', N'N', DEFAULT, 1);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, dispensed_drug_sig, original_num_refills) values (5020201, 1, 1, 5010201, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx502', current_timestamp, 1, 302, 1, 'sig1', 0);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5030201,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5040201, 5030201, 5020201, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'5002', 50502, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5060201, N' ', 50502, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5070201, 5060201, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5030201, N'Y', 5070201, 5070201, 1, 2, 5060201);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5020201, current_timestamp, 5080201, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5090201,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5100201, N'N', 5090201, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, rx_number) values (5020201, 5100201, 2, 1, 0, N'N', 1001, current_timestamp, 101, N'rx502');

-- 03
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (5010301, N'Y', N'N', N'p3', N'Y', N'PGRN3', N'prod number3', N'N', DEFAULT, 1);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, dispensed_drug_sig, original_num_refills) values (5020301, 1, 1, 5010301, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx503', current_timestamp, 6, 302, 1, 'sig2', null);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5030301,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5040301, 5030301, 5020301, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'5003', 50503, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5060301, N' ', 50503, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5070301, 5060301, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5030301, N'Y', 5070301, 5070301, 1, 2, 5060301);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5020301, current_timestamp, 5080301, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5090301,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5100301, N'N', 5090301, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, rx_number) values (5020301, 5100301, 2, 1, 0, N'N', 1001, current_timestamp, 101, N'rx503');

-- 04
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (5010401, N'Y', N'N', N'p4', N'Y', N'PGRN4', N'prod number4', N'N', DEFAULT, 1);

insert into dbo.rx_status_codes (rx_status_code_num, rx_status_code_desc) values (51, N'rxs51');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, dispensed_drug_sig, original_num_refills, rx_status_code_num) values (5020401, 1, 1, 5010401, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx504', current_timestamp, 1, 302, 1, 'sig4', null, 51);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5030401,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5040401, 5030401, 5020401, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'5004', 50504, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5060401, N' ', 50504, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5070401, 5060401, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5030401, N'Y', 5070401, 5070401, 1, 2, 5060401);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5020401, current_timestamp, 5080401, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5090401,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5100401, N'N', 5090401, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, rx_number) values (5020401, 5100401, 2, 1, 0, N'N', 1001, current_timestamp, 101, N'rx504');

-- 05
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (5010501, N'Y', N'N', N'p5', N'Y', N'PGRN5', N'prod number5', N'N', DEFAULT, 1);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, dispensed_drug_sig, original_num_refills, doctor_num) values (5020501, 1, 1, 5010501, 1, N'REFILLRX', N'N', N'N', N'N', N'N', N'rx505', current_timestamp, 1, 302, 1, 'sig5', null, 505);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5030501,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5040501, 5030501, 5020501, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'5005', 50505, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5060501, N' ', 50505, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5070501, 5060501, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5030501, N'Y', 5070501, 5070501, 1, 2, 5060501);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5020501, current_timestamp, 5080501, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5090501,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5100501, N'N', 5090501, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, rx_number) values (5020501, 5100501, 2, 1, 0, N'N', 1001, current_timestamp, 101, N'rx505');
insert into dbo.trading_partner_address (trading_partner_num, tp_addr_seq, tp_address, active, spi) values (505, 5120501, N'tpa5', N'A', N'spi5');

-- 06
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (5010601, N'Y', N'N', N'p6', N'Y', N'PGRN6', N'prod number6', N'N', DEFAULT, 1);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, dispensed_drug_sig, original_num_refills) values (5020601, 1, 1, 5010601, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx506', current_timestamp, 1, 302, 1, 'sig4', null);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5030601,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5040601, 5030601, 5020601, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'5006', 50506, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5060601, N' ', 50506, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5070601, 5060601, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5030601, N'Y', 5070601, 5070601, 1, 2, 5060601);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5020601, current_timestamp, 5080601, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5090601,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5100601, N'N', 5090601, N'A', N'A', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, rx_number) values (5020601, 5100601, 2, 1, 0, N'N', 1001, current_timestamp, 101, N'rx506');

-- 07
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (5010701, N'Y', N'N', N'p7', N'Y', N'PGRN7', N'prod number7', N'N', DEFAULT, 1);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, dispensed_drug_sig, original_num_refills) values (5020701, 1, 1, 5010701, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx507', N'2020-01-10 19:49:30.447', 1, 302, 1, 'sig7', null);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5030701,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5040701, 5030701, 5020701, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'5007', 50507, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5060701, N' ', 50507, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5070701, 5060701, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5030701, N'Y', 5070701, 5070701, 1, 2, 5060701);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5020701, current_timestamp, 5080701, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5090701,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5100701, N'N', 5090701, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, rx_number) values (5020701, 5100701, 2, 1, 0, N'N', 1001, current_timestamp, 101, N'rx507');

-- 08
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (5010801, N'Y', N'N', N'p8', N'Y', N'PGRN8', N'prod number8', N'N', DEFAULT, 1);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, dispensed_drug_sig, original_num_refills) values (5020801, 1, 1, 5010801, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx508', N'2120-01-10 19:49:30.447', 1, 302, 1, 'sig8', null);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5030801,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5040801, 5030801, 5020801, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'5008', 50508, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5060801, N' ', 50508, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5070801, 5060801, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5030801, N'Y', 5070801, 5070801, 1, 2, 5060801);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5020801, current_timestamp, 5080801, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5090801,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5100801, N'N', 5090801, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, rx_number) values (5020801, 5100801, 2, 1, 0, N'N', 1001, current_timestamp, 101, N'rx508');

-- 09
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (5010901, N'Y', N'N', N'p9', N'Y', N'PGRN9', N'prod number9', N'N', DEFAULT, 2);

insert into dbo.patient_general (patient_num, patient_id_code, general_status_code, language_id, title_id, patient_first_name, patient_last_name, patient_dob) values (5130901, null, N'X', 1, 1, N'F', N'L', N'2021-01-10 19:49:30.447');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, dispensed_drug_sig, original_num_refills) values (5020901, 5130901, 1, 5010901, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx509', current_timestamp, 1, 302, 1, 'sig9', null);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5030901,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5040901, 5030901, 5020901, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'KY', 50509, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5060901, N' ', 50509, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5070901, 5060901, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5030901, N'Y', 5070901, 5070901, 1, 2, 5060901);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5020901, current_timestamp, 5080901, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5090901,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5100901, N'N', 5090901, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, rx_number) values (5020901, 5100901, 2, 1, 0, N'N', 1001, current_timestamp, 101, N'rx509');

-- 10
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (5011001, N'Y', N'N', N'p10', N'Y', N'72PGRN10', N'prod number10', N'N', DEFAULT, 1);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, dispensed_drug_sig, original_num_refills) values (5021001, 1, 1, 5011001, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx510', current_timestamp, 1, 302, 1, 'sig10', null);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5031001,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5041001, 5031001, 5021001, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'FL', 50510, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5061001, N' ', 50510, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5071001, 5061001, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5031001, N'Y', 5071001, 5071001, 1, 2, 5061001);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5021001, current_timestamp, 5081001, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5091001,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5101001, N'N', 5091001, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, rx_number) values (5021001, 5101001, 2, 1, 0, N'N', 1001, current_timestamp, 101, N'rx510');

--
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (5011002, N'Y', N'N', N'p102', N'Y', N'72PGRN102', N'prod number102', N'N', DEFAULT, 1);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, dispensed_drug_sig, original_num_refills) values (5021002, 1, 1, 5011002, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx510', current_timestamp, 1, 302, 1, 'sig10', null);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5031002,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5041002, 5031002, 5021002, 1, 1, N'N', N'N', N'Y');
--insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'FL', 50510, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5061002, N' ', 50510, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5071002, 5061002, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5031002, N'Y', 5071002, 5071002, 1, 2, 5061002);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5021002, current_timestamp, 5081002, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5091002,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5101002, N'N', 5091002, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, rx_number) values (5021002, 5101002, 2, 1, 0, N'N', 1001, current_timestamp, 101, N'rx510');


-- 11
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (5011101, N'Y', N'N', N'p11', N'Y', N'PGRN11', N'prod number11', N'N', DEFAULT, 2);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, dispensed_drug_sig, original_num_refills) values (5021101, 1, 1, 5011101, 1, N'NEWRX-N', N'N', N'N', N'N', N'N', N'rx511', current_timestamp, 1, 302, 1, 'sig11', null);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5031101,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5041101, 5031101, 5021101, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'5011', 50511, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5061101, N' ', 50511, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5071101, 5061101, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5031101, N'Y', 5071101, 5071101, 1, 2, 5061101);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5021101, current_timestamp, 5081101, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5091101,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5101101, N'N', 5091101, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, rx_number) values (5021101, 5101101, 2, 1, 0, N'N', 1001, current_timestamp, 101, N'rx511');

-- 12
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (5011201, N'Y', N'N', N'p12', N'Y', N'dPGRN12', N'prod number12', N'N', DEFAULT, 1);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (5011202, N'Y', N'N', N'p12', N'Y', N'wPGRN12', N'prod number12', N'N', DEFAULT, 1);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, dispensed_drug_sig, original_num_refills, dispensed_drug_daw) values (5021201, 1, 1, 5011201, 5011202, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx512', current_timestamp, 1, 302, 1, 'sig12', null, N'0');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5031201,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5041201, 5031201, 5021201, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'5012', 50512, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5061201, N' ', 50512, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5071201, 5061201, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5031201, N'Y', 5071201, 5071201, 1, 2, 5061201);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5021201, current_timestamp, 5081201, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5091201,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5101201, N'N', 5091201, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, rx_number) values (5021201, 5101201, 2, 1, 0, N'N', 1001, current_timestamp, 101, N'rx512');

-- 13
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea, prod_name_type) values (5011301, N'Y', N'N', N'p13', N'Y', N'39400010100320', N'prod number13', N'N', DEFAULT, 1, N'X');

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, dispensed_drug_sig, original_num_refills, dispensed_drug_daw) values (5021301, 1, 1, 5011301, 5011301, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx513', current_timestamp, 1, 302, 1, N'sig13', null, N'0');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5031301,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5041301, 5031301, 5021301, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'5013', 50513, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5061301, N' ', 50513, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5071301, 5061301, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5031301, N'Y', 5071301, 5071301, 1, 2, 5061301);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5021301, current_timestamp, 5081301, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5091301,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5101301, N'N', 5091301, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, rx_number) values (5021301, 5101301, 2, 1, 0, N'N', 1001, current_timestamp, 101, N'rx513');

--
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea, prod_name_type) values (5011302, N'Y', N'N', N'p1302', N'Y', N'34000003100330', N'prod number1302', N'N', DEFAULT, 1, N'T');

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, dispensed_drug_sig, original_num_refills, dispensed_drug_daw) values (5021302, 1, 1, 5011302, 5011302, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx51302', current_timestamp, 1, 302, 1, N'sig1302', null, N'0');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5031302,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5041302, 5031302, 5021302, 1, 1, N'N', N'N', N'Y');
--insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'5013', 50513, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5061302, N' ', 50513, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5071302, 5061302, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5031302, N'Y', 5071302, 5071302, 1, 2, 5061302);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5021302, current_timestamp, 5081302, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5091302,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5101302, N'N', 5091302, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, rx_number) values (5021302, 5101302, 2, 1, 0, N'N', 1001, current_timestamp, 101, N'rx51302');

--
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea, prod_name_type) values (5011303, N'Y', N'N', N'p1303', N'Y', N'83200030200315', N'prod number1303', N'N', DEFAULT, 1, N'G');
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea, prod_name_type) values (5011304, N'Y', N'N', N'p1304', N'Y', N'83200030200315', N'prod number1304', N'N', DEFAULT, 1, N'T');

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, dispensed_drug_sig, original_num_refills, dispensed_drug_daw) values (5021303, 1, 1, 5011303, 5011304, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx51303', current_timestamp, 1, 302, 1, N'sig1303', null, N'1');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5031303,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5041303, 5031303, 5021303, 1, 1, N'N', N'N', N'Y');
--insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'5013', 50513, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5061303, N' ', 50513, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5071303, 5061303, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5031303, N'Y', 5071303, 5071303, 1, 2, 5061303);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5021303, current_timestamp, 5081303, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5091303,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5101303, N'N', 5091303, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, rx_number) values (5021303, 5101303, 2, 1, 0, N'N', 1001, current_timestamp, 101, N'rx51303');


--
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea, prod_name_type) values (5011305, N'Y', N'N', N'p1305', N'Y', N'83200030200315', N'prod number1305', N'N', DEFAULT, 1, N'X');
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea, prod_name_type) values (5011306, N'Y', N'N', N'p1306', N'Y', N'83200030200315', N'005258049er1306', N'N', DEFAULT, 1, N'X');

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, written_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, dispensed_drug_sig, original_num_refills, dispensed_drug_daw) values (5021304, 1, 1, 5011305, 5011306, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx51304', current_timestamp, 1, 302, 1, N'sig1304', null, N'8');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5031304,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5041304, 5031304, 5021304, 1, 1, N'N', N'N', N'Y');
--insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'5013', 50513, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5061304, N' ', 50513, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5071304, 5061304, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5031304, N'Y', 5071304, 5071304, 1, 2, 5061304);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5021304, current_timestamp, 5081304, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5091304,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5101304, N'N', 5091304, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, rx_number) values (5021304, 5101304, 2, 1, 0, N'N', 1001, current_timestamp, 101, N'rx51304');


-- 14
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (5011401, N'Y', N'N', N'p14', N'Y', N'PGRN14', N'prod number14', N'N', DEFAULT, 1);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, dispensed_drug_sig, original_num_refills) values (5021401, 1, 1, 5011401, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx514', current_timestamp, 1, 302, 1, N'sig14', null);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5031401,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5041401, 5031401, 5021401, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'5014', 50514, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5061401, N' ', 50514, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5071401, 5061401, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5031401, N'Y', 5071401, 5071401, 1, 2, 5061401);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5021401, current_timestamp, 5081401, null);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5091401,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5101401, N'N', 5091401, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, rx_number) values (5021401, 5101401, 2, 1, 0, N'N', 1001, current_timestamp, 101, N'rx514');

-- 15
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (5011501, N'Y', N'N', N'p15', N'Y', N'PGRN15', N'prod number15', N'N', DEFAULT, 1);

insert into dbo.patient_general (patient_num, patient_id_code, general_status_code, language_id, title_id, patient_first_name, patient_last_name, patient_dob, additional_patient_id_type_code) values (5131501, N'03', N'X', 1, 1, N'F', N'L', N'2021-01-10 19:49:30.447', N'03');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, dispensed_drug_sig, original_num_refills) values (5021501, 5131501, 1, 5011501, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx515', current_timestamp, 1, 302, 1, N'sig15', null);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5031501,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5041501, 5031501, 5021501, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'HI', 50515, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5061501, N' ', 50515, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5071501, 5061501, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5031501, N'Y', 5071501, 5071501, 1, 2, 5061501);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5021501, current_timestamp, 5081501, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5091501,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5101501, N'N', 5091501, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, rx_number) values (5021501, 5101501, 2, 1, 0, N'N', 1001, current_timestamp, 101, N'rx515');


-- 91
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion, prod_dea) values (5019101, N'Y', N'N', N'p91', N'Y', N'PGRN91', N'prod number91', N'N', DEFAULT, 1);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, dispensed_drug_sig, original_num_refills) values (5029101, 1, 1, 5019101, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'rx591', current_timestamp, 1, 302, 1, 'sig91', null);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5039101,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5049101, 5039101, 5029101, 1, 1, N'N', N'N', N'Y');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'5091', 50591, N' ', 1, 1);
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (5069101, N' ', 50591, 1, N' ');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (5079101, 5069101, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_address_seq, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (5039101, N'Y', 5079101, 5079101, 1, 2, 5069101);
insert into dbo.rx_fill_dur (e_script_msg_attribute_seq, dur_date, dur_seq, dur_resolve_date) values (5029101, current_timestamp, 5089101, current_timestamp);
insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 5099101,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (5109101, N'N', 5099101, N'A', N'B', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, pp_num, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, rx_number) values (5029101, 5109101, 2, 1, 0, N'N', 1001, current_timestamp, 101, N'rx591');

/****** OK RX Sanity Check Challenge ******/
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity) values (4101, 1, 1, 41001, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'410001', N'2021-01-10 19:49:30.447', 1, 302, 1);
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity) values (4201, 1, 1, 42001, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'420001', N'2021-01-10 19:49:30.447', 1, 302);
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date) values (4301, 1, 1, 43001, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'430001', N'2021-01-10 19:49:30.447');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date) values (4302, 1, 1, 43001, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'430001', N'2021-01-10 19:49:30.447');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date) values (4401, 1, 1, 44001, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'440001', N'2021-01-10 19:49:30.447');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date) values (4402, 1, 1, 44001, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'440001', N'2021-01-10 19:49:30.447');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date) values (4501, 1, 1, 45001, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'450001', N'2021-01-10 19:49:30.447');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity) values (4601, 1, 1, 46001, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'460001', N'2021-01-10 19:49:30.447', 1, 2, 1);
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date) values (4602, 1, 1, 46002, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'460002', N'2021-01-10 19:49:30.447');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity) values (4603, 1, 1, 460033, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'460003', N'2021-01-10 19:49:30.447', 1, 2, 1);
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity) values (4604, 1, 1, 46004, 1, N'NEWRX', N'N', N'N', N'N', N'N', N'460004', N'2021-01-10 19:49:30.447', 1, 302, 1);

insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed) values (4101, 2, 1, 0, N'N', 1001, null, 101);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed) values (4201, 2, 1, 0, N'N', 2001, null, 101);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed) values (4301, 2, 1, 0, N'N', 3001, null, 101);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, ortf_qty) values (4302, 2, 1, 0, N'N', 3002, null, 201, 0);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, ortf_qty) values (4401, 2, 1, 0, N'N', 4001, null, 101, 200);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed, ortf_qty) values (4402, 2, 1, 0, N'N', 4002, null, 201, 1);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime) values (4501, 2, 1, 0, N'N', 5001, null);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed) values (4601, 2, 1, 0, N'N', 6001, null, 101);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed) values (4602, 2, 1, 0, N'N', 6002, null, 101);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed) values (4603, 2, 1, 0, N'N', 6003, null, 101);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, fill_qty_dispensed) values (4604, 2, 1, 0, N'N', 6004, null, 101);

insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (41001, N'Y', N'N', N'p1', N'Y', N'PGRN1', N'prod number1', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (42001, N'Y', N'N', N'p1', N'Y', N'PGRN2', N'prod number1', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (43001, N'Y', N'N', N'p1', N'Y', N'PGRN3', N'prod number1', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (44001, N'Y', N'N', N'p1', N'Y', N'PGRN4', N'prod number1', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (45001, N'Y', N'N', N'p1', N'Y', N'PGRN5', N'prod number1', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (46001, N'Y', N'N', N'p1', N'Y', N'PGRN61', N'prod number1', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (46002, N'Y', N'N', N'p1', N'Y', N'PGRN62', N'prod number1', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (46004, N'Y', N'N', N'p1', N'Y', N'PGRN64', N'prod number1', N'N', DEFAULT);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (460001,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (4600001, 460001, 4601, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (460001, N'Y', 4600001, 1, 2, 46000001);
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (4600001, 46000001, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (46000001, N' ', 4601, 1, N' ');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'OK', 4601, N' ', 1, 1);
insert into dbo.OK_hydrocodone_sanity_products (GPI) values (N'PGRN61');


insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (460002,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (4600002, 460002, 4602, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (460002, N'Y', 4600002, 1, 2, 46000002);
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (4600002, 46000002, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (46000002, N' ', 4602, 1, N' ');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'OK', 4602, N' ', 1, 1);
insert into dbo.OK_hydrocodone_sanity_products (GPI) values (N'PGRN62');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (460003,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (4600003, 460003, 4603, 2, 1, N'N', N'N', N'Y');
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (460003, N'Y', 4600003, 1, 2, 46000003);
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (4600003, 46000003, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (46000003, N' ', 4603, 1, N' ');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'OK', 4603, N' ', 1, 1);

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (460004,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (4600004, 460004, 4604, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_ship_address_seq, payment_card_seq_num, family_id, csz_zip_num) values (460004, N'Y', 4600004, 1, 2, 46000004);
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (4600004, 46000004, 0, 1, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.city_state_zip (csz_zip_num, csz_city_name, state_num, ctry_num, csz_zip_code) values (46000004, N' ', 4604, 1, N' ');
insert into dbo.states (state_code, state_num, state_name, PMP_ENABLED, REAL_TIME_REPORTING_ON) values (N'OK', 4604, N' ', 1, 1);
insert into dbo.OK_hydrocodone_sanity_products (GPI) values (N'PGRN64');

/****** Ansordersanitycheck Challenge ******/
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date) values (301, 2, 1, 6, 1, N'NEWRX', N'N', N'N', N'N', N'N', 1, N'2021-01-10 19:49:30.447');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (3001, null, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (30001, 3001, 301, 1, 1, N'N', N'N', N'Y');

insert into dbo.code_value (code_value_id, fk_code_cat_id, code_value_keyword, code_value_desc, status_flag, create_ts, last_updt_ts, last_updt_by, restart_required, application_name) values (10001, N'1', N'SAME_ORDER_PROCESS_MULTIPLE_PATIENT', N'', N'A', N'2021-01-10 19:49:30.447', N'2021-01-10 19:49:30.447', N'X', N'X', N'X');

insert into dbo.patient_general (patient_num, patient_id_code, general_status_code, language_id, title_id, patient_first_name, patient_last_name, patient_dob) values (300001, N'X', N'X', 1, 1, N'F', N'L', N'2021-01-10 19:49:30.447');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date) values (302, 300001, 1, 6, 1, N'NEWRX', N'N', N'N', N'N', N'N', 2, N'2021-01-10 19:49:30.447');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (3002,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (30002, 3002, 302, 1, 1, N'N', N'N', N'Y');

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, rx_expiration_date) values (303, 2, 1, 6, 1, N'NEWRX', N'N', N'N', N'N', N'N', 3, N'2021-01-10 19:49:30.447', N'2021-01-10 19:49:30.447' );
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (3003, null, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (30003, 3003, 303, 1, 1, N'N', N'N', N'Y');

insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, rx_number) values (304, 2, 0, 0, N'N', 1001, null, 4);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, num_refills, written_quantity, dispensed_quantity, order_invoice_number) values (304, 2, 1, 6, 1, N'NEWRX', N'N', N'N', N'N', N'N', 4, NULL, 0, 0, 100, N'1');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (3004,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (30004, 3004, 304, 1, 1, N'N', N'N', N'Y');

insert into dbo.payors (payor_code, payor_num, payor_name, payor_bill_type_num) values (N'c1', 101,  N'N', 2);
insert into dbo.payor_plans (pp_num, pp_plan_name, payor_num, general_status_code, pp_type_code, pp_union) values (1001, N'N', 101, N'A', N'A', N'Y');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime, rx_number, pp_num) values (305, 2, 0, 0, N'N', 1001, null, 4, 1001);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, order_invoice_number) values (305, 2, 1, 6, 1, N'NEWRX', N'N', N'N', N'N', N'N', 4, NULL, N'1');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (3005,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (30005, 3005, 305, 1, 1, N'N', N'N', N'Y');

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date, order_invoice_number) values (306, 2, 1, 6, 1, N'NEWRX', N'N', N'N', N'N', N'N', 4, NULL, N'1');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (3006,    1, 3, N'2021-01-10 19:49:30.447', N'N', N'1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (30006, 3006, 306, 1, 1, N'N', N'N', N'Y');

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date) values (307, 300002, 1, 6, 1, N'NEWRX', N'N', N'N', N'N', N'N', 2, N'2021-01-10 19:49:30.447');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (3007,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (30007, 3007, 307, 1, 1, N'N', N'N', N'Y');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (1, 0, 0, 300002, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_ship_address_seq, payment_card_seq_num, family_id) values (3007, N'Y', 1, 1, 2);

insert into dbo.patient_general (patient_num, patient_id_code, general_status_code, language_id, title_id, patient_first_name, patient_last_name, patient_dob, family_id) values (300003, N'X', N'X', 1, 1, N'F', N'L', N'2021-01-10 19:49:30.447', N'1');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date) values (308, 300003, 1, 6, 1, N'NEWRX', N'N', N'N', N'N', N'N', 2, N'2021-01-10 19:49:30.447');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (3008,    1, 3, N'2021-01-10 19:49:30.447', N'N', N'OIN1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (30008, 3008, 308, 2, 1, N'N', N'N', N'Y');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (2, 0, 0, 300003, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_ship_address_seq, payment_card_seq_num, family_id) values (3008, N'Y', 1, 1, 1);

insert into dbo.patient_general (patient_num, patient_id_code, general_status_code, language_id, title_id, patient_first_name, patient_last_name, patient_dob, family_id) values (300004, N'X', N'X', 1, 1, N'F', N'L', N'2021-01-10 19:49:30.447', N'1');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date) values (309, 300004, 1, 6, 1, N'NEWRX', N'N', N'N', N'N', N'N', 2, N'2021-01-10 19:49:30.447');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (3009,    1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN1', N'Y', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (30009, 3009, 309, 2, 1, N'N', N'N', N'Y');
insert into dbo.patient_address (patient_addr_seq, csz_zip_num, address_type_num, patient_num, patient_address, created, created_user_num, updated, updated_user_num, verified, override_reason_id, origination_num, rowversion) values (3, 0, 0, 300004, N' ', N'2021-01-10 19:49:30.0000447', 1, N'2021-01-10 19:49:30.0000447', 1, 1, 1, 1, default );
insert into dbo.order_billship (order_num, ord_ship_free_shipping, patient_ship_address_seq, payment_card_seq_num, family_id) values (3009, N'Y', 1, 1, 1);


/****** Ansordersanitycheck3 Challenge ******/
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date) values (201, 2, 1, 6, 1, N'NEWRX', N'N', N'N', N'N', N'N', 1, N'2021-01-10 19:49:30.447');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date) values (202, 2, 1, 6, 1, N'NEWRX', N'N', N'N', N'N', N'N', 2, N'2021-01-10 19:49:30.447');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date) values (203, 2, 1, 6, 1, N'NEWRX', N'N', N'N', N'N', N'N', 3, N'2021-01-10 19:49:30.447');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date) values (204, 2, 1, 6, 1, N'NEWRX', N'N', N'N', N'N', N'N', 4, NULL);

insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime) values (201, 0, 0, 0, N'N', 1001, null);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime) values (202, 9, 0, 0, N'N', 1002, N'2021-01-10 19:49:30.447');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime) values (203, 0, 0, 0, N'N', 1003, null);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime) values (204, 0, 0, 0, N'N', 1004, N'2021-01-10 19:49:30.447');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (2001, null, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN1', N'Y', N'Y');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (2002, 1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN1', N'Y', N'Y');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (2003, null, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN1', N'Y', N'Y');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (2004, 1, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN1', N'Y', N'Y');

insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (20001, 2001, 201, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (20002, 2002, 202, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (20003, 2003, 203, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (20004, 2004, 204, 1, 1, N'N', N'N', N'Y');


/****** Common Function Challenge ******/
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date) values (101, 2, 1, 6, 1, N'X', N'N', N'N', N'N', N'N', 1, N'2021-01-10 19:49:30.447');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date) values (102, 2, 1, 6, 1, N'X', N'N', N'N', N'N', N'N', 2, N'2021-01-10 19:49:30.447');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date) values (103, 2, 1, 6, 1, N'X', N'N', N'N', N'N', N'N', 3, N'2021-01-10 19:49:30.447');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill, rx_number, written_date) values (104, 2, 1, 6, 1, N'X', N'N', N'N', N'N', N'N', 4, NULL);

insert into dbo.ecs_responses (ecs_resp_seq_num, e_script_msg_attribute_seq, ecs_response_status_type, ecs_resp_date, ecs_resp_msg_ack, ecs_resp_rej_msg_ack) values (1001, 101, N'P', N'2021-01-10 19:49:30.447', N'Y', N'Y');
insert into dbo.ecs_responses (ecs_resp_seq_num, e_script_msg_attribute_seq, ecs_response_status_type, ecs_resp_date, ecs_resp_msg_ack, ecs_resp_rej_msg_ack) values (1002, 102, NULL, N'2021-01-10 19:49:30.447', N'Y', N'Y');
insert into dbo.ecs_responses (ecs_resp_seq_num, e_script_msg_attribute_seq, ecs_response_status_type, ecs_resp_date, ecs_resp_msg_ack, ecs_resp_rej_msg_ack) values (1003, 103, NULL, N'2021-01-10 19:49:30.447', N'Y', N'Y');
insert into dbo.ecs_responses (ecs_resp_seq_num, e_script_msg_attribute_seq, ecs_response_status_type, ecs_resp_date, ecs_resp_msg_ack, ecs_resp_rej_msg_ack) values (1004, 104, NULL, N'2021-01-10 19:49:30.447', N'Y', N'Y');

insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime) values (101, 2, 0, 0, N'N', 1001, null);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime) values (102, 9, 0, 0, N'N', 1002, N'2021-01-10 19:49:30.447');
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime) values (103, 0, 0, 0, N'N', 1003, null);
insert into dbo.rx_fill_aux (e_script_msg_attribute_seq, fill_status_num, rx_refill_num, relationship_num, post_edit_rx, fill_ecs_status, fill_precheck_datetime) values (104, 0, 0, 0, N'N', 1003, N'2021-01-10 19:49:30.447');

insert into dbo.benefit_response_codes (id, approval_code) values (10001, NULL);
insert into dbo.benefit_response_codes (id, approval_code) values (10002, NULL);
insert into dbo.benefit_response_codes (id, approval_code) values (10003, NULL);
insert into dbo.benefit_response_codes (id, approval_code) values (10004, N'AA');

insert into dbo.third_party_claim_requests (id, e_script_msg_attribute_seq, transaction_code, benefit_card_num, transaction_id) values (100001, 101, NULL, 0, 1);
insert into dbo.third_party_claim_requests (id, e_script_msg_attribute_seq, transaction_code, benefit_card_num, transaction_id) values (100002, 102, NULL, 0, 2);
insert into dbo.third_party_claim_requests (id, e_script_msg_attribute_seq, transaction_code, benefit_card_num, transaction_id) values (100003, 103, NULL, 0, 3);
insert into dbo.third_party_claim_requests (id, e_script_msg_attribute_seq, transaction_code, benefit_card_num, transaction_id) values (100004, 104, N'LS', 0, 4);

insert into dbo.third_party_claim_responses (id, transaction_id, response_code) values (1000001, 1, 10001);
insert into dbo.third_party_claim_responses (id, transaction_id, response_code) values (1000002, 2, 10002);
insert into dbo.third_party_claim_responses (id, transaction_id, response_code) values (1000003, 3, 10003);
insert into dbo.third_party_claim_responses (id, transaction_id, response_code) values (1000004, 4, 10004);



/****** Check Duplicate GPI Challenge ******/
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (1, null, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN1', N'Y', N'Y');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (2, null, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN2', N'Y', N'Y');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (3, null, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN3', N'Y', N'Y');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (4, null, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN3', N'Y', N'Y');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (5, null, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN5', N'Y', N'Y');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (6, null, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN6', N'Y', N'Y');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (7, null, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN7', N'Y', N'Y');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (8, null, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN8', N'Y', N'Y');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (9, null, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN8', N'Y', N'Y');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (10, null, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN10', N'Y', N'Y');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (11, null, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN11', N'Y', N'Y');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (12, null, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN12', N'Y', N'Y');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (13, null, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN13', N'Y', N'Y');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (14, null, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN13', N'Y', N'Y');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (15, null, 1, N'2021-01-10 19:49:30.447', N'N', N'OIN15', N'Y', N'Y');

insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (16, null, 98, N'2021-01-10 19:49:30.447', N'N', N'OIN16', N'Y', N'Y');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (17, null, 98, N'2021-01-10 19:49:30.447', N'N', N'OIN17', N'Y', N'Y');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (18, null, 98, N'2021-01-10 19:49:30.447', N'N', N'OIN18', N'Y', N'Y');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (19, null, 98, N'2021-01-10 19:49:30.447', N'N', N'OIN18', N'Y', N'Y');
insert into dbo.order_header (order_num, trading_partner_num, order_status_num, order_date, order_high_priority, order_invoice_number, order_picked, order_validated) values (20, null, 98, N'2021-01-10 19:49:30.447', N'N', N'OIN20', N'Y', N'Y');

insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (1, N'Y', N'N', N'p1', N'Y', N'PGRN1', N'prod number1', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (2, N'Y', N'N', N'p2', N'Y', N'PGRN2', N'prod number2', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (3, N'Y', N'N', N'p3', N'Y', N'PGRN3', N'prod number3', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (4, N'Y', N'N', N'p4', N'Y', N'PGRN3', N'prod number4', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (5, N'Y', N'N', N'p5', N'Y', N'PGRN1', N'prod number5', N'N', DEFAULT);

insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (6, N'Y', N'N', N'p6', N'Y', N'PGRN6', N'prod number6', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (7, N'Y', N'N', N'p7', N'Y', N'PGRN7', N'prod number7', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (8, N'Y', N'N', N'p8', N'Y', N'PGRN8', N'prod number8', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (9, N'Y', N'N', N'p9', N'Y', N'PGRN8', N'prod number9', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (10, N'Y', N'N', N'p10', N'Y', N'PGRN8', N'prod number10', N'N', DEFAULT);

insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (11, N'Y', N'N', N'p11', N'Y', N'PGRN11', N'prod number11', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (12, N'Y', N'N', N'p12', N'Y', N'PGRN12', N'prod number12', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (13, N'Y', N'N', N'p13', N'Y', N'PGRN13', N'prod number13', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (14, N'Y', N'N', N'p14', N'Y', N'PGRN13', N'prod number14', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (15, N'Y', N'N', N'p15', N'Y', N'PGRN13', N'prod number15', N'N', DEFAULT);

insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (16, N'Y', N'N', N'p16', N'Y', N'PGRN16', N'prod number16', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (17, N'Y', N'N', N'p17', N'Y', N'PGRN17', N'prod number17', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (18, N'Y', N'N', N'p18', N'Y', N'PGRN18', N'prod number18', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (19, N'Y', N'N', N'p19', N'Y', N'PGRN18', N'prod number19', N'N', DEFAULT);
insert into dbo.products (prod_id, prod_active, prod_rx_required, prod_name_desc, prod_unit_of_use, prod_generic_ref_num, prod_number, prod_compound, rowversion) values (20, N'Y', N'N', N'p20', N'Y', N'PGRN18', N'prod number20', N'N', DEFAULT);

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (1, 1, 1, 1, 1, N'X', N'N', N'N', N'N', N'N');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (2, 1, 2, 2, 1, N'X', N'N', N'N', N'N', N'N');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (3, 1, 3, 3, 1, N'X', N'N', N'N', N'N', N'N');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (4, 1, 4, 4, 1, N'X', N'N', N'N', N'N', N'N');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (5, 1, 5, 5, 1, N'X', N'N', N'N', N'N', N'N');

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (6, 2, 1, 6, 1, N'X', N'N', N'N', N'N', N'N');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (7, 2, 2, 7, 1, N'X', N'N', N'N', N'N', N'N');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (8, 2, 3, 8, 1, N'X', N'N', N'N', N'N', N'N');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (9, 2, 4, 9, 1, N'X', N'N', N'N', N'N', N'N');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (10, 2, 5, 10, 1, N'X', N'N', N'N', N'N', N'N');

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (11, 3, 1, 11, 1, N'X', N'N', N'N', N'N', N'N');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (12, 3, 2, 12, 1, N'X', N'N', N'N', N'N', N'N');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (13, 3, 3, 13, 1, N'X', N'N', N'N', N'N', N'N');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (14, 3, 4, 14, 1, N'X', N'N', N'N', N'N', N'N');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (15, 3, 5, 15, 1, N'X', N'N', N'N', N'N', N'N');

insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (16, 4, 1, 16, 1, N'X', N'N', N'N', N'N', N'N');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (17, 4, 2, 17, 1, N'X', N'N', N'N', N'N', N'N');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (18, 4, 3, 18, 1, N'X', N'N', N'N', N'N', N'N');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (19, 4, 4, 19, 1, N'X', N'N', N'N', N'N', N'N');
insert into dbo.e_script_msg_attributes (e_script_msg_attribute_seq, patient_num, sys_user_num, dispensed_product_id, edi_message_id, edi_transaction_code, image_available, script_legible, clone_rx, auto_refill) values (20, 4, 5, 20, 1, N'X', N'N', N'N', N'N', N'N');

insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (1, 1, 1, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (2, 2, 2, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (3, 3, 3, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (4, 4, 4, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (5, 1, 5, 1, 1, N'N', N'N', N'Y');

insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (6, 6, 6, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (7, 7, 7, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (8, 8, 8, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (9, 9, 9, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (10, 6, 10, 1, 1, N'N', N'N', N'Y');

insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (11, 11, 11, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (12, 12, 12, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (13, 13, 13, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (14, 14, 14, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (15, 11, 15, 1, 1, N'N', N'N', N'Y');

insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (16, 16, 16, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (17, 17, 17, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (18, 18, 18, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (19, 19, 19, 1, 1, N'N', N'N', N'Y');
insert into dbo.order_lines (order_line_num, order_num, e_script_msg_attribute_seq, order_line_status_num, order_line_type_num, return_replace_ecs, return_replace_inventory, return_replace_payment_card) values (20, 16, 20, 1, 1, N'N', N'N', N'Y');

insert into dbo.rule_queue_exception (rule_queue_exception_seq, workflow_queue_num, rule_exception_datetime, available, e_script_msg_attribute_seq, workflow_step_num, order_num) values (4001, 2001, N'2021-01-10 19:49:30.447', N'Y', 6, 3001, 6);
insert into dbo.rule_queue_exception (rule_queue_exception_seq, workflow_queue_num, rule_exception_datetime, available, e_script_msg_attribute_seq, workflow_step_num, order_num) values (4002, 2002, N'2021-01-10 19:49:30.447', N'Y', 7, 3001, 7);
insert into dbo.rule_queue_exception (rule_queue_exception_seq, workflow_queue_num, rule_exception_datetime, available, e_script_msg_attribute_seq, workflow_step_num, order_num) values (4003, 2003, N'2021-01-10 19:49:30.447', N'Y', 8, 3001, 8);
insert into dbo.rule_queue_exception (rule_queue_exception_seq, workflow_queue_num, rule_exception_datetime, available, e_script_msg_attribute_seq, workflow_step_num, order_num) values (4004, 2004, N'2021-01-10 19:49:30.447', N'Y', 9, 3001, 9);
insert into dbo.rule_queue_exception (rule_queue_exception_seq, workflow_queue_num, rule_exception_datetime, available, e_script_msg_attribute_seq, workflow_step_num, order_num) values (4005, 2005, N'2021-01-10 19:49:30.447', N'Y', 10, 3001, 6);

insert into dbo.rule_queue_exception (rule_queue_exception_seq, workflow_queue_num, rule_exception_datetime, available, e_script_msg_attribute_seq, workflow_step_num, order_num) values (4006, 2006, N'2021-01-10 19:49:30.447', N'Y', 11, 3001, 11);
insert into dbo.rule_queue_exception (rule_queue_exception_seq, workflow_queue_num, rule_exception_datetime, available, e_script_msg_attribute_seq, workflow_step_num, order_num) values (4007, 2007, N'2021-01-10 19:49:30.447', N'Y', 12, 3001, 12);
insert into dbo.rule_queue_exception (rule_queue_exception_seq, workflow_queue_num, rule_exception_datetime, available, e_script_msg_attribute_seq, workflow_step_num, order_num) values (4008, 2008, N'2021-01-10 19:49:30.447', N'Y', 13, 3001, 13);
insert into dbo.rule_queue_exception (rule_queue_exception_seq, workflow_queue_num, rule_exception_datetime, available, e_script_msg_attribute_seq, workflow_step_num, order_num) values (4009, 2009, N'2021-01-10 19:49:30.447', N'Y', 14, 3001, 14);
insert into dbo.rule_queue_exception (rule_queue_exception_seq, workflow_queue_num, rule_exception_datetime, available, e_script_msg_attribute_seq, workflow_step_num, order_num) values (4010, 2010, N'2021-01-10 19:49:30.447', N'Y', 15, 3001,11);

insert into dbo.rule_queue_exception (rule_queue_exception_seq, workflow_queue_num, rule_exception_datetime, available, e_script_msg_attribute_seq, workflow_step_num, order_num) values (4011, 2011, N'2021-01-10 19:49:30.447', N'Y', 16, 3001, 16);
insert into dbo.rule_queue_exception (rule_queue_exception_seq, workflow_queue_num, rule_exception_datetime, available, e_script_msg_attribute_seq, workflow_step_num, order_num) values (4012, 2012, N'2021-01-10 19:49:30.447', N'Y', 17, 3001, 17);
insert into dbo.rule_queue_exception (rule_queue_exception_seq, workflow_queue_num, rule_exception_datetime, available, e_script_msg_attribute_seq, workflow_step_num, order_num) values (4013, 2013, N'2021-01-10 19:49:30.447', N'Y', 18, 3001, 18);
insert into dbo.rule_queue_exception (rule_queue_exception_seq, workflow_queue_num, rule_exception_datetime, available, e_script_msg_attribute_seq, workflow_step_num, order_num) values (4014, 2014, N'2021-01-10 19:49:30.447', N'Y', 19, 3001, 19);
insert into dbo.rule_queue_exception (rule_queue_exception_seq, workflow_queue_num, rule_exception_datetime, available, e_script_msg_attribute_seq, workflow_step_num, order_num) values (4015, 2015, N'2021-01-10 19:49:30.447', N'Y', 20, 3001,16);
