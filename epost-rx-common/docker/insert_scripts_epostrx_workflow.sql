insert into dbo.workflow_queue_names (workflow_queue_name_id, workflow_queue_name, workflow_queue_desc) values (1001, N'wqn1001', N'wqn1001 desc');
insert into dbo.workflow_queue_names (workflow_queue_name_id, workflow_queue_name, workflow_queue_desc) values (1002, N'wqn1002', N'wqn1002 desc');
insert into dbo.workflow_queue_names (workflow_queue_name_id, workflow_queue_name, workflow_queue_desc) values (1003, N'wqn1003', N'wqn1003 desc');
insert into dbo.workflow_queue_names (workflow_queue_name_id, workflow_queue_name, workflow_queue_desc) values (1004, N'wqn1004', N'wqn1004 desc');
insert into dbo.workflow_queue_names (workflow_queue_name_id, workflow_queue_name, workflow_queue_desc) values (1005, N'wqn1005', N'wqn1005 desc');

insert into dbo.workflow_queue_names (workflow_queue_name_id, workflow_queue_name, workflow_queue_desc) values (1006, N'DR. CALL TECH OUTBOUND', N'wqn1006 desc');
insert into dbo.workflow_queue_names (workflow_queue_name_id, workflow_queue_name, workflow_queue_desc) values (1007, N'wqn1007', N'wqn1007 desc');
insert into dbo.workflow_queue_names (workflow_queue_name_id, workflow_queue_name, workflow_queue_desc) values (1008, N'wqn1008', N'wqn1008 desc');
insert into dbo.workflow_queue_names (workflow_queue_name_id, workflow_queue_name, workflow_queue_desc) values (1009, N'wqn1009', N'wqn1009 desc');
insert into dbo.workflow_queue_names (workflow_queue_name_id, workflow_queue_name, workflow_queue_desc) values (1010, N'wqn1010', N'wqn1010 desc');

insert into dbo.workflow_queue_names (workflow_queue_name_id, workflow_queue_name, workflow_queue_desc) values (1011, N'wqn1011', N'wqn1011 desc');
insert into dbo.workflow_queue_names (workflow_queue_name_id, workflow_queue_name, workflow_queue_desc) values (1012, N'wqn1012', N'wqn1012 desc');
insert into dbo.workflow_queue_names (workflow_queue_name_id, workflow_queue_name, workflow_queue_desc) values (1013, N'wqn1013', N'wqn1013 desc');
insert into dbo.workflow_queue_names (workflow_queue_name_id, workflow_queue_name, workflow_queue_desc) values (1014, N'wqn1014', N'wqn1014 desc');
insert into dbo.workflow_queue_names (workflow_queue_name_id, workflow_queue_name, workflow_queue_desc) values (1015, N'wqn1015', N'wqn1015 desc');

insert into dbo.workflow_queue_list (workflow_queue_num, workflow_queue_name_id, trading_partner_num, auto_alert) values (2001, 1001, null, N'N');
insert into dbo.workflow_queue_list (workflow_queue_num, workflow_queue_name_id, trading_partner_num, auto_alert) values (2002, 1002, null, N'N');
insert into dbo.workflow_queue_list (workflow_queue_num, workflow_queue_name_id, trading_partner_num, auto_alert) values (2003, 1003, null, N'N');
insert into dbo.workflow_queue_list (workflow_queue_num, workflow_queue_name_id, trading_partner_num, auto_alert) values (2004, 1004, null, N'N');
insert into dbo.workflow_queue_list (workflow_queue_num, workflow_queue_name_id, trading_partner_num, auto_alert) values (2005, 1005, null, N'N');

insert into dbo.workflow_queue_list (workflow_queue_num, workflow_queue_name_id, trading_partner_num, auto_alert) values (2006, 1006, null, N'N');
insert into dbo.workflow_queue_list (workflow_queue_num, workflow_queue_name_id, trading_partner_num, auto_alert) values (2007, 1007, null, N'N');
insert into dbo.workflow_queue_list (workflow_queue_num, workflow_queue_name_id, trading_partner_num, auto_alert) values (2008, 1008, null, N'N');
insert into dbo.workflow_queue_list (workflow_queue_num, workflow_queue_name_id, trading_partner_num, auto_alert) values (2009, 1009, null, N'N');
insert into dbo.workflow_queue_list (workflow_queue_num, workflow_queue_name_id, trading_partner_num, auto_alert) values (2010, 1010, null, N'N');

insert into dbo.workflow_queue_list (workflow_queue_num, workflow_queue_name_id, trading_partner_num, auto_alert) values (2011, 1011, null, N'N');
insert into dbo.workflow_queue_list (workflow_queue_num, workflow_queue_name_id, trading_partner_num, auto_alert) values (2012, 1012, null, N'N');
insert into dbo.workflow_queue_list (workflow_queue_num, workflow_queue_name_id, trading_partner_num, auto_alert) values (2013, 1013, null, N'N');
insert into dbo.workflow_queue_list (workflow_queue_num, workflow_queue_name_id, trading_partner_num, auto_alert) values (2014, 1014, null, N'N');
insert into dbo.workflow_queue_list (workflow_queue_num, workflow_queue_name_id, trading_partner_num, auto_alert) values (2015, 1015, null, N'N');

insert into dbo.rules (rule_id, workflow_queue_num, workflow_num, rule_action_id, rule_desc, rule_name, rule_enabled, allow_override, order_based_rule) values (3001, null, 1, 1, N'r1 desc', N'r1', N'Y', N'N', N'Y');
insert into dbo.rules (rule_id, workflow_queue_num, workflow_num, rule_action_id, rule_desc, rule_name, rule_enabled, allow_override, order_based_rule) values (3002, null, 2, 2, N'r2 desc', N'r2', N'Y', N'N', N'Y');
insert into dbo.rules (rule_id, workflow_queue_num, workflow_num, rule_action_id, rule_desc, rule_name, rule_enabled, allow_override, order_based_rule) values (3003, null, 3, 3, N'r3 desc', N'r3', N'Y', N'N', N'N');
insert into dbo.rules (rule_id, workflow_queue_num, workflow_num, rule_action_id, rule_desc, rule_name, rule_enabled, allow_override, order_based_rule) values (3004, null, 4, 4, N'r4 desc', N'r4', N'Y', N'N', N'N');
