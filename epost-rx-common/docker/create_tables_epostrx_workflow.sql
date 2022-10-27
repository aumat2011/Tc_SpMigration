/****** Object:  UserDefinedDataType [dbo].[ID_BIG_TYPE]    ******/
CREATE TYPE [dbo].[ID_BIG_TYPE] FROM [numeric](16, 0) NULL
    GO
/****** Object:  UserDefinedDataType [dbo].[YES_NO_TYPE]    ******/
CREATE TYPE [dbo].[YES_NO_TYPE] FROM [char](1) NOT NULL
    GO

/****** Object:  Table [dbo].[workflow_queue_names] ******/
SET ANSI_NULLS ON
    GO

SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[workflow_queue_names](
    [workflow_queue_name_id] [numeric](7, 0) NOT NULL,
    [workflow_queue_name] [varchar](60) NOT NULL,
    [workflow_queue_desc] [varchar](255) NULL,
    PRIMARY KEY CLUSTERED
(
[workflow_queue_name_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[workflow_queue_list] ******/
SET ANSI_NULLS ON
    GO

SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[workflow_queue_list](
    [workflow_queue_num] [numeric](5, 0) NOT NULL,
    [workflow_queue_name_id] [numeric](7, 0) NULL,
    [barcode_library_id] [numeric](5, 0) NULL,
    [sys_user_num] [numeric](16, 0) NULL,
    [trading_partner_num] [numeric](16, 0) NULL,
    [warning_level] [numeric](5, 0) NULL,
    [critical_level] [numeric](5, 0) NULL,
    [serial_num] [numeric](5, 0) NULL,
    [server_num] [numeric](5, 0) NULL,
    [resource_connector_id] [numeric](7, 0) NULL,
    [auto_alert] [dbo].[YES_NO_TYPE] NOT NULL,
    [max_duration] [numeric](5, 0) NULL,
    [show_prefix] [char](1) NULL,
    [turnaround_type] [char](1) NULL,
    [queue_priority_seq] [numeric](5, 0) NULL,
    PRIMARY KEY CLUSTERED
(
[workflow_queue_num] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[rules] ******/
SET ANSI_NULLS ON
    GO

SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[rules](
    [rule_id] [numeric](16, 0) NOT NULL,
    [rule_criteria_id] [numeric](5, 0) NULL,
    [workflow_queue_num] [numeric](5, 0) NULL,
    [serial_num] [numeric](5, 0) NULL,
    [rule_response_id] [numeric](2, 0) NULL,
    [workflow_num] [numeric](5, 0) NOT NULL,
    [resource_connector_id] [numeric](7, 0) NULL,
    [business_hour_type_id] [numeric](5, 0) NULL,
    [rule_action_id] [numeric](2, 0) NOT NULL,
    [rule_group_id] [numeric](5, 0) NULL,
    [rule_list_table_id] [numeric](5, 0) NULL,
    [routing_tp_num] [numeric](16, 0) NULL,
    [operator_id] [numeric](2, 0) NULL,
    [rule_desc] [varchar](255) NOT NULL,
    [trading_partner_num] [numeric](16, 0) NULL,
    [rule_value] [varchar](45) NULL,
    [rule_name] [varchar](60) NOT NULL,
    [tp_contact_seq] [dbo].[ID_BIG_TYPE] NULL,
    [tp_connectivity_seq] [numeric](16, 0) NULL,
    [rule_enabled] [dbo].[YES_NO_TYPE] NOT NULL,
    [server_num] [numeric](5, 0) NULL,
    [rule_retry] [numeric](2, 0) NULL,
    [allow_override] [dbo].[YES_NO_TYPE] NOT NULL,
    [order_based_rule] [varchar](1) NULL,
    [rule_params] [varchar](500) NULL,
    PRIMARY KEY CLUSTERED
(
[rule_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

/****** Object:  Table [dbo].[workflow_transactions]    Script Date: 7/29/2022 9:19:44 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[workflow_transactions](
	[workflow_txn_id] [numeric](30, 0) NOT NULL,
	[e_script_msg_attribute_seq] [numeric](35, 0) NOT NULL,
	[workflow_num] [numeric](5, 0) NULL,
	[rule_id] [numeric](16, 0) NULL,
	[order_num] [numeric](30, 0) NULL,
	[workflow_step] [varchar](60) NULL,
	[workflow_step_num] [numeric](2, 0) NULL,
	[initial_txn_submit_datetime] [datetime] NULL,
	[current_txn_datetime] [datetime] NULL,
	[order_running] [numeric](1, 0) NULL,
	[step_busy] [numeric](1, 0) NULL,
	[rule_engine_status_id] [numeric](2, 0) NULL,
	[rule_engine_id] [numeric](35, 0) NULL,
	[edi_message_id] [numeric](35, 0) NULL,
	[intransition] [numeric](1, 0) NULL,
	[mode] [varchar](60) NULL,
	[rxnumber] [varchar](60) NULL,
	[queuename] [varchar](60) NULL,
	[queuenum] [varchar](60) NULL,
	[exception_seq] [varchar](60) NULL,
	[trading_partner_num] [varchar](60) NULL,
	[rule_order_num] [varchar](10) NULL,
	[host_server] [varchar](80) NULL,
	[jms_msg_id] [varchar](35) NULL,
PRIMARY KEY CLUSTERED
(
	[workflow_txn_id] ASC,
	[e_script_msg_attribute_seq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 70) ON [PRIMARY]
) ON [PRIMARY]
GO
