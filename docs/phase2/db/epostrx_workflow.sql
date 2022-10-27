CREATE TABLE [dbo].[workflow_queue_names] (
	[workflow_queue_name_id] [numeric](7, 0) NOT NULL,
	[workflow_queue_name] [varchar](60) NOT NULL,
	PRIMARY KEY CLUSTERED ([workflow_queue_name_id] ASC)
);

CREATE TABLE [dbo].[workflow_queue_list] (
	[workflow_queue_num] [numeric](5, 0) NOT NULL,
	[trading_partner_num] [numeric](16, 0) NULL,
	[workflow_queue_name_id] [numeric](7, 0) NULL,
	PRIMARY KEY CLUSTERED ([workflow_queue_num] ASC),
	CONSTRAINT workflow_queue_list_FK_1 FOREIGN KEY ([workflow_queue_name_id]) REFERENCES [dbo].[workflow_queue_names]([workflow_queue_name_id]),
);

CREATE TABLE [dbo].[rules] (
    [rule_id] [numeric](16, 0) NOT NULL,
    [order_based_rule] [char](1) NULL,
    PRIMARY KEY CLUSTERED ([rule_id] ASC)
);

CREATE TABLE [dbo].[workflow_transactions](
	[workflow_txn_id] [numeric](30, 0) NOT NULL,
	[e_script_msg_attribute_seq] [numeric](35, 0) NOT NULL,
	[workflow_num] [numeric](5, 0) NULL,
    PRIMARY KEY CLUSTERED ([workflow_txn_id] ASC,[e_script_msg_attribute_seq] ASC)
);