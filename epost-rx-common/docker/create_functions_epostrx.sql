
/****** Object:  UserDefinedFunction [dbo].[F_isOrderReshipped]    Script Date: 6/23/2022 9:00:19 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO




Create FUNCTION [dbo].[F_isOrderReshipped](@ordernum numeric(35))

RETURNS bit

AS BEGIN

DECLARE
@replaceparentordernum numeric(30),
@orderstatus numeric(5)

	-- if not a replacement order - return '0', else return '1'
select @replaceparentordernum = replace_parent_order_num,
       @orderstatus = order_status_num
from order_header(nolock)
where order_num=@ordernum

    if(@replaceparentordernum IS NOT NULL)
         -- AND
        --  @orderstatus= 1) -- OPEN
           return 1

      return 0

END

GO

/****** Object:  UserDefinedFunction [dbo].[F_IsDMEItem] ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create function [dbo].[F_IsDMEItem] (@scriptid numeric(30))
returns BIT
as
begin
DECLARE
@counter numeric(5)
select  @counter = count(e_script_msg_attribute_seq)
from e_script_msg_attributes (nolock)
where
        pwo_item = 'Y'
  and e_script_msg_attribute_seq = @scriptid
    if( @counter > 0) return 1
return 0
end

GO

/****** Object:  UserDefinedFunction [dbo].[F_ItemPrecheckRequired] ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create function [dbo].[F_ItemPrecheckRequired] (@escript varchar(35))
returns BIT
as
begin
DECLARE
@need_precheck bit,
	 @otcasrxprecheck char(1),
	 @dmeasrxprecheck char(1),
	 @rx_req char(1),
	 @precheckall char(1),
	 @ischecked int,
	 @tpnum numeric(20),
	 @rxnum varchar(35),
	 @disp_prod_id numeric(30)

  --settings to drive whether we want to pre-check OTC and DME items
select @otcasrxprecheck	= ISNULL(otc_as_rx_precheck,'N'),
       @dmeasrxprecheck	= ISNULL(pwo_precheck,'N'),
       @precheckall = ISNULL(precheck_all,'N')
from rx_defaults(nolock)
    --default, assume item needs pre-check
    set @need_precheck = 1

select @rxnum = es.rx_number,
       @tpnum = es.trading_partner_num,
       @disp_prod_id = es.dispensed_product_id
from e_script_msg_attributes es(nolock)
where es.e_script_msg_attribute_seq = @escript

    if @rxnum IS NOT NULL
BEGIN
		--check if the item has already been screened
	    if(@precheckall='Y')
BEGIN
select @ischecked = count(*)
from e_script_msg_attributes e (nolock),
     rx_fill_aux f (nolock)
where e.e_script_msg_attribute_seq=@escript
  and e.e_script_msg_attribute_seq = f.e_script_msg_attribute_seq
  and f.fill_precheck_datetime IS NOT NULL
END
	   if(@precheckall='N')
BEGIN
		--check if the item has already been screened at some point  in RX life
select @ischecked = count(*)
from e_script_msg_attributes e (nolock),
     rx_fill_aux f (nolock)
where e.rx_number=@rxnum
  and e.e_script_msg_attribute_seq = f.e_script_msg_attribute_seq
  and f.fill_precheck_datetime IS NOT NULL
END

		--if already prechked, return, otherwise check OTC/DME
		if  @ischecked > 0
BEGIN
		   set @need_precheck = 0
END
ELSE
BEGIN
		   --rx_req is our flag to indicate if item is an OTC item beign filled as an RX
select @rx_req = ISNULL(p.prod_rx_required,'Y')
from products p(nolock)
where p.prod_id = @disp_prod_id
      --if DME/check=N or OTC/check=N....skip precheck
    if (dbo.F_IsDMEItem(@escript) = 1 AND @dmeasrxprecheck ='N') or (@rx_req = 'N' and @otcasrxprecheck = 'N')
set @need_precheck = 0
END
END
RETURN @need_precheck
END
GO

/****** Object:  UserDefinedFunction [dbo].[F_isRxFirstTimeFill] ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create function [dbo].[F_isRxFirstTimeFill] (@scriptid numeric(30))
returns BIT
as
begin
DECLARE
@rstatus BIT,
	 @dispensedfills int,
	 @rxnumber varchar(60),
	 @tpnum numeric(30)

					 -- get rx number
Select @rxnumber = e.rx_number,
       @tpnum = e.trading_partner_num
FROM rx_fill_aux r with (nolock),e_script_msg_attributes e with (nolock)
WHERE
    r.e_script_msg_attribute_seq=e.e_script_msg_attribute_seq
  and r.e_script_msg_attribute_seq=@scriptid

-- get diespense count
SELECT @dispensedfills = count(*)
FROM rx_fill_aux r with (nolock),e_script_msg_attributes e with (nolock)
WHERE
    r.e_script_msg_attribute_seq=e.e_script_msg_attribute_seq
  and r.fill_status_num IN(2,9)
  and e.rx_number=@rxnumber
-- and e.trading_partner_num=@tpnum

-- first time = 1 Not first time ==0
    if(@dispensedfills = 0) return 1
    return 0
end

GO

/****** Object:  UserDefinedFunction [dbo].[F_isPrimaryEcsPaid] ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[F_isPrimaryEcsPaid](@escript numeric(35))

RETURNS numeric(30)

AS BEGIN

DECLARE
@seqnum numeric(30)

	--Incident 8809399 -?Rejected OTC Claims from InComm are not being routed to the to the OTC Failed Claims Queue in ePost.
	--Start

select @seqnum = count(1) from third_party_claim_requests tpcrq with (nolock)
 				join third_party_claim_responses tpcrp with (nolock) on tpcrq.transaction_id = tpcrp.transaction_id
    join benefit_response_codes brc with (nolock) on tpcrp.response_code = brc.id
where tpcrq.transaction_code ='LS'
  and brc.Approval_code ='AA'
  and tpcrq.e_script_msg_attribute_seq = @escript
    --order by tpcrp.id desc

    If @seqnum > 0
    return @seqnum
    else
--End
SELECT @seqnum = r.ecs_resp_seq_num
FROM  dbo.ecs_responses r with(nolock),
			 rx_fill_aux rf with(nolock)
WHERE
    rf.e_script_msg_attribute_seq=r.e_script_msg_attribute_seq
  and r.ecs_resp_seq_num=rf.fill_ecs_status
  and r.ecs_response_status_type IN ('P','C','D')
  and r.e_script_msg_attribute_seq=@escript
    return @seqnum
END

GO

/****** Object:  UserDefinedFunction [dbo].[F_IsRxExceedMaxPVDays] ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create function [dbo].[F_IsRxExceedMaxPVDays] (@scriptid numeric(30))
returns BIT
as
begin
DECLARE
@calcwrittendate datetime,
	 @now datetime,
	 @maxpvdays int,
	 @isRxFirstTimeFill int

SELECT @isRxFirstTimeFill= [dbo].[F_isRxFirstTimeFill] (@scriptid)
    -- check if RX is first time filled(only NEWRX)
    if(@isRxFirstTimeFill= 1)
BEGIN

          -- get max PV days
select  @maxpvdays = ISNULL(max_pv_days,0) from rx_defaults with (nolock)
-- get today
select   @now = getdate()
-- get fill written date + Max PV days
select  @calcwrittendate = dateadd(day,@maxpvdays,written_date)
from e_script_msg_attributes with (nolock)
where e_script_msg_attribute_seq=@scriptid
-- If calculated written date < today - then flag it
    if(@calcwrittendate < getdate())
    return 1
END
return 0
end

GO

/****** Object:  UserDefinedFunction [dbo].[F_split_string]    Script Date: 6/11/2022 10:29:38 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE function [dbo].[F_split_string]
(
    @string varchar(4000),
    @delimiter varchar(10)
)
returns @table table
(
    [Value] varchar(4000)
)
begin
    declare @nextString varchar(4000)
    declare @pos int, @nextPos int

    set @nextString = ''
    set @string = @string + @delimiter

    set @pos = charindex(@delimiter, @string)
    set @nextPos = 1
    while (@pos <> 0)
begin
        set @nextString = substring(@string, 1, @pos - 1)

        insert into @table
        (
            [Value]
        )
        values
        (
            @nextString
        )

        set @string = substring(@string, @pos + len(@delimiter), len(@string))
        set @nextPos = @pos
        set @pos = charindex(@delimiter, @string)
end
    return
end

GO

/****** Object:  UserDefinedFunction [dbo].[F_getWorkflowQueueNumbers]    Script Date: 6/8/2022 8:37:05 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE function [dbo].[F_getWorkflowQueueNumbers]
(
    @queues varchar(max),
    @tpnum numeric(16,0)
)
returns @table table
(
   workflow_queue_num  numeric(5,0)
)
begin
	if NULLIF(@tpnum,0) is null
begin
insert into @table	(workflow_queue_num)
select distinct wql.workflow_queue_num from epostrx_workflow.dbo.workflow_queue_names wqn with (nolock)
			join epostrx_workflow.dbo.workflow_queue_list wql with (nolock) on wqn.workflow_queue_name_id = wql.workflow_queue_name_id
    join (SELECT value from dbo.F_split_string (@queues,'#')      ) X on wqn.workflow_queue_name = X.Value
END

else
BEGIN
insert into @table	(workflow_queue_num)
select distinct wql.workflow_queue_num from epostrx_workflow.dbo.workflow_queue_names wqn with (nolock)
			join epostrx_workflow.dbo.workflow_queue_list wql with (nolock) on wqn.workflow_queue_name_id = wql.workflow_queue_name_id
    join (SELECT value from dbo.F_split_string (@queues,'#')) X on wqn.workflow_queue_name = X.Value
where wql.trading_partner_num = @tpnum
END

    return
end

GO

/**********************************************************************
 * <b>P_duplicateGPI</b>
 *
 * PROC WILL RETURN ERROR MESSAGE IF DUPLICATE GPI IS PRESENT WITH IN AN ORDER OR ACROSS OPEN, INCOMPLETE/PENDING ORDERS
 * FOR GIVEN PATIENT
 *
 * <p>=================================================================
 * <br><b>change history </b><p>
 *
 *  date  name   description
 *  ----------- --------------- ---------------------------------------
 * 2014-10-13 RS-IT   modified in terms of performance
 *  2014-05-27 RS-IT   initial version
 * 04/02/2019 RSIT   PR89673 - Hard coded workflow_queue_num and trading_partner_num
 * =================================================================
 *
 *********************************************************************
 */

CREATE procedure [dbo].[P_checkDuplicateGPI]
(
@order_invoice_number VARCHAR(60),
@patient_num NUMERIC(20,0),
@error_msg VARCHAR(8000) OUTPUT
)

AS
BEGIN

DECLARE

@error_number VARCHAR(4000),
@error_severity VARCHAR(4000),
@error_state VARCHAR(4000),
@error_procedure VARCHAR(4000),
@error_line VARCHAR(4000),
@error_message VARCHAR(4000),
@proc_name VARCHAR(400),
@proc_params VARCHAR(4000),
@workflow_queue_name varchar(60), --Incident 5517172: Function changes
@return_code INT,
@lvar_patient_num NUMERIC(20,0)

set @lvar_patient_num = @patient_num

/* CHECK FOR INPUT IS NOT NULL */
BEGIN TRY
IF @lvar_patient_num  IS NULL
 RETURN -1
IF @order_invoice_number IS NULL
 RETURN -2

/* LOAD ALL OPEN & INCOMPLETE/PENDING ORDERS OF PATIENT ALONG WITH DISPENSED PRODUCT DETAILS */
DECLARE @patient_all_open_incomplete_orders TABLE (order_num NUMERIC(30,0), order_status_num NUMERIC(2,0), order_invoice_number VARCHAR(60), prod_name_desc VARCHAR(60), prod_generic_ref_num VARCHAR(60))
INSERT INTO @patient_all_open_incomplete_orders (order_num, order_status_num, order_invoice_number, prod_name_desc, prod_generic_ref_num)
SELECT
    oh.order_num,
    oh.order_status_num,
    oh.order_invoice_number,
    p.prod_name_desc,
    p.prod_generic_ref_num
FROM dbo.order_header oh WITH (NOLOCK)
          JOIN dbo.order_lines ol WITH (NOLOCK) ON oh.order_num = ol.order_num
    JOIN dbo.e_script_msg_attributes e WITH (NOLOCK) ON ol.e_script_msg_attribute_seq = e.e_script_msg_attribute_seq
    JOIN dbo.products p WITH (NOLOCK) ON e.dispensed_product_id = p.prod_id
WHERE oh.order_status_num IN (1,98)
  AND ol.order_line_status_num = 1
  AND e.patient_num = @lvar_patient_num
  AND p.prod_generic_ref_num IS NOT NULL
  AND oh.replace_parent_order_num IS NULL --1509W Defect#200710 - eRx DCCO Rule ?MF1 BR3  - Reship/Replacement Orders w/Same Rx Numbers are not excluded
  AND p.prod_generic_ref_num not in (select code_value_keyword from dbo.code_value with (nolock) where fk_code_cat_id = 'DCCO_GPI')  -- Added for MF4-FR-4.10 DCCO Same GPI DME

/* CHECK DUPLICATE GPI EXISTS OR NOT FOR THE PATIENT */
    IF EXISTS (SELECT 1 FROM @patient_all_open_incomplete_orders
    GROUP BY prod_generic_ref_num
    HAVING COUNT(prod_generic_ref_num) > 1 )

/* DUPLICATE GPI EXISTS FOR THE PATIENT*/
BEGIN
            /* CONCATENATE PRODUCT NAMES HAVING SAME GPI FOR EVERY ORDER OF THE PATIENT */
            DECLARE @patient_dispensed_product_details TABLE (order_num NUMERIC(30,0),
                                                              order_status_num NUMERIC(2,0),
                                                              order_invoice_number VARCHAR(60),
                                                              prod_generic_ref_num VARCHAR(60),
                                                              prod_name_desc_str VARCHAR(8000),
                                                              gpi_count NUMERIC(20,0))
            INSERT INTO @patient_dispensed_product_details (order_num, order_status_num, order_invoice_number, prod_generic_ref_num, prod_name_desc_str, gpi_count)
SELECT DISTINCT P.order_num,
                P.order_status_num,
                P.order_invoice_number,
                P.prod_generic_ref_num,
    LEFT(
    REPLACE(
    STUFF((SELECT ',' + '''' +  prod_name_desc + ''''
    FROM @patient_all_open_incomplete_orders P1
    WHERE P.order_num = P1.order_num AND P.prod_generic_ref_num = P1.prod_generic_ref_num
    FOR XML PATH('')), 1, 1, '')
        ,'&#x0d;','')
        ,7999),
    COUNT(prod_name_desc) OVER(PARTITION BY p.prod_generic_ref_num, p.order_invoice_number)
FROM @patient_all_open_incomplete_orders P
ORDER BY P.order_num

/* LOAD CURRENT ORDER & OTHER ORDER DETAILS WITH QUEUE NAME HAVING DUPLICATE GPI */
DECLARE @order_rule_based TABLE (current_order_prod_name_desc_str  VARCHAR(8000),
                                        other_order_prod_name_desc_str VARCHAR(8000),
                                        other_order_invoice_number VARCHAR(60),
                                        other_order_workflow_queue_num NUMERIC(5,0))
            INSERT INTO @order_rule_based (current_order_prod_name_desc_str, other_order_prod_name_desc_str, other_order_invoice_number, other_order_workflow_queue_num)
                  /* LOAD CURRENT ORDER & OTHER ORDER DETAILS WHICH IS IN ORDER_BASED QUEUE */
SELECT gpi_current_order.prod_name_desc_str,
       gpi_other_orders.prod_name_desc_str,
       gpi_other_orders.order_invoice_number,
       rqe.workflow_queue_num
FROM
    (SELECT * FROM @patient_dispensed_product_details where order_invoice_number = @order_invoice_number) gpi_current_order JOIN
    (SELECT * FROM @patient_dispensed_product_details where order_invoice_number != @order_invoice_number) gpi_other_orders ON
            gpi_current_order.prod_generic_ref_num = gpi_other_orders.prod_generic_ref_num
                                                                                                                            JOIN dbo.rule_queue_exception rqe WITH (NOLOCK) ON gpi_other_orders.order_num = rqe.order_num
    JOIN epostrx_workflow.dbo.rules r WITH (NOLOCK) ON rqe.workflow_step_num = r.rule_id
WHERE r.order_based_rule = 'Y'

UNION

/* LOAD CURRENT ORDER & OTHER ORDER DETAILS WHICH IS IN NON-ORDER_BASED QUEUE */
SELECT gpi_current_order.prod_name_desc_str, gpi_other_orders.prod_name_desc_str, gpi_other_orders.order_invoice_number, rqe.workflow_queue_num
FROM
    (SELECT * FROM @patient_dispensed_product_details where order_invoice_number = @order_invoice_number) gpi_current_order JOIN
    (SELECT * FROM @patient_dispensed_product_details where order_invoice_number != @order_invoice_number) gpi_other_orders ON
            gpi_current_order.prod_generic_ref_num = gpi_other_orders.prod_generic_ref_num
                                                                                                                            JOIN dbo.rule_queue_exception rqe WITH (NOLOCK) ON gpi_other_orders.order_num = rqe.order_num
    JOIN dbo.order_lines ol WITH (NOLOCK) ON rqe.e_script_msg_attribute_seq = ol.e_script_msg_attribute_seq
    JOIN epostrx_workflow.dbo.rules r WITH (NOLOCK) ON rqe.workflow_step_num = r.rule_id
    JOIN dbo.e_script_msg_attributes e WITH (NOLOCK) ON rqe.e_script_msg_attribute_seq = e.e_script_msg_attribute_seq
    JOIN dbo.products p WITH (NOLOCK) ON p.prod_generic_ref_num = gpi_current_order.prod_generic_ref_num AND e.dispensed_product_id = p.prod_id
WHERE
    r.order_based_rule = 'N'
  AND ol.order_line_status_num = 1


/*Incident 5517172: Function changes - Added Variable for Function Replacement Begins*/
SELECT @workflow_queue_name=wq.workflow_queue_name  ---Incident 5609118  ePostRx - Showing on every order (see attachment)
FROM @order_rule_based orlb
         join epostrx_workflow.dbo.workflow_queue_list w with (nolock)
on w.workflow_queue_num=orlb.other_order_workflow_queue_num
    join epostrx_workflow.dbo.workflow_queue_names wq with (nolock)
on wq.workflow_queue_name_id=w.workflow_queue_name_id
WHERE w.workflow_queue_num =orlb.other_order_workflow_queue_num
/*Added Variable for Function Replacement Ends*/



/* CREATE ERROR MESSAGE */
SELECT @error_msg =  ISNULL(@error_msg,'') + ( +error.errmsg+ '-!-')  FROM
    (
        /* CHECK DUPLICATE GPI WITHIN ORDER */
        SELECT 'Duplicate GPI ' + prod_name_desc_str + ' within the same Order. ' AS errmsg
        FROM @patient_dispensed_product_details
        WHERE order_invoice_number = @order_invoice_number
          AND gpi_count > 1

        UNION

        /* CHECK DUPLICATE GPI ACROSS OTHER OPEN ORDERS WHICH ARE IN ORDER BASED QUEUES AND NON ORDER BASED QUEUES */
        --PR89673 - Hard coded workflow_queue_num and trading_partner_num
        SELECT (CASE WHEN other_order_workflow_queue_num IN (select workflow_queue_num from dbo.F_getWorkflowQueueNumbers ('REFILL OUTREACH#DR. CALL TECH OUTBOUND#DR. CALL TECH RESPONSE',null))--(20, 528, 529)
                         THEN
                             'Duplicate GPI ' + current_order_prod_name_desc_str + ' in this Order, ' + other_order_prod_name_desc_str
                             + ' in Order ' + other_order_invoice_number + ' with fax in '
                             + @workflow_queue_name  --Incident 5517172: Function changes
                             + ' Queue. '
                     ELSE 'Duplicate GPI ' + current_order_prod_name_desc_str + ' in this Order, ' + other_order_prod_name_desc_str
                         + ' in Open Order ' + other_order_invoice_number + ' in '
                         + @workflow_queue_name  --Incident 5517172: Function changes
                         + ' Queue. '  END) AS errmsg
        FROM @order_rule_based


        UNION

        /*CHECK DUPLICATE GPI ACROSS INCOMPLETE/PENDING ORDERS*/
        SELECT 'Duplicate GPI '+ gpi_current_order.prod_name_desc_str + ' in this Order, ' + gpi_other_orders.prod_name_desc_str
                   + ' in Incomplete/Pending Order ' + gpi_other_orders.order_invoice_number + '. '
                   AS errmsg
        FROM
            (SELECT * FROM @patient_dispensed_product_details where order_invoice_number = @order_invoice_number) gpi_current_order JOIN
            (SELECT * FROM @patient_dispensed_product_details where order_invoice_number != @order_invoice_number and order_status_num =98) gpi_other_orders
            ON gpi_current_order.prod_generic_ref_num = gpi_other_orders.prod_generic_ref_num

    )error
    SET @error_msg = LEFT(@error_msg,7999)
END
END TRY
BEGIN CATCH
         /*LOG THE ERROR */
SELECT @error_number = ERROR_NUMBER(),
       @error_severity = ERROR_SEVERITY(),
       @error_state = ERROR_STATE(),
       @error_procedure = ERROR_PROCEDURE(),
       @error_line = ERROR_LINE(),
       @error_message = ERROR_MESSAGE()

       -- NEED TO LOG SOMEWHERE
    SET @proc_params = 'EXCEPTION:' +
                       ' @lvar_patient_num:' + CONVERT(VARCHAR,@lvar_patient_num) +
        ' @order_invoice_number:' + CONVERT(VARCHAR,@order_invoice_number)
SET @proc_name = 'P_checkDuplicateGPI'
    RETURN -3
END CATCH
RETURN 0

END

GO








  /**
   *
   * Copyright (c) 2001-2015 AdvanceNet Health Solutions, Inc. All Rights Reserved.
   *
   * THIS SOFTWARE IS THE CONFIDENTIAL AND PROPRIETARY INFORMATION OF
   * ADVANCENET SOLUTIONS, INC. ("CONFIDENTIAL INFORMATION").
   * AND IS PROTECTED BY U.S. COPYRIGHT LAW. ANY USER SHALL NOT DISCLOSE
   * SUCH CONFIDENTIAL INFORMATION AND SHALL USE THIS SOFTWARE IN
   * ACCORDANCE WITH THE TERMS OF ANY DISCLOSURES AND/OR LICENSE AGREEMENTS
   * ENTERED INTO WITH ADVANCENET. FAILURE TO ABIDE IS PUNISHABLE BY A COURT OF
   * LAW.
   *-
   **/


  /**********************************************************************
   * <b>P_ansordersanitycheck</b>
   *
   * Sanity check for customers to add their own custom order checks
   * Returns null if no hits, a message(s) if there are hits
   *
   *
   * <p>=================================================================
   * <br><b>Change History </b><p>
   *
   *  Date  Name   Description
   *  ----------- --------------- ---------------------------------------
   * 2012-01-19 Matt Walko Initial Version<br>
   * 04/02/2019 RSIT   PR89673 - Hard coded workflow_queue_num and trading_partner_num
   * <p>==================================================================
   *
   **********************************************************************/
/*
declare @msg varchar(max) = NULL
declare @qnum int =  NULL
declare @qname varchar(200) =NULL
exec P_ansordersanitycheck 11325, @msg OUTPUT, @qnum OUTPUT, @qname OUTPUT
print @msg
*/



CREATE PROCEDURE [dbo].[P_ansordersanitycheck]
(

  @ordernum numeric(30),
  @queueToBeUsed varchar(5),
  @errormsgs varchar(2500) OUTPUT,
  @overridequeue varchar(60) OUTPUT,
  @overrideqname varchar(60) OUTPUT
)
AS
BEGIN

  /*
  ** Declarations.
  */
DECLARE @tc INT,
  @err INT,
  @rc INT,
  @familyid varchar(60),
  @patient_num int,
  @shipaddressseq numeric(30),
  @currentshippatnum numeric(20),
  @currentbillpatnum numeric(20),
  @newshippatnum numeric(30),
  @dob datetime,
  @HIPPAAGE INT,
  @force char(1),
  @currshippatage int,
        @currshippatcardholder char(1),
  @currshippatrelcode int,
  @currentshipseq numeric(16,0),
  @currentbillseq numeric(16,0),
  @patient_num_adult numeric(20),
     @patient_num_minor numeric(20),
     @propershippat numeric(20),
     @def_bill_addr ID_BIG_TYPE,
     @def_ship_addr ID_BIG_TYPE,
     @tmp_ship_addr ID_BIG_TYPE,
     @def_ship_cnt int,
     @def_bill_cnt int,
     @tmp_addr_cnt int,
     @addr_error int,
     @currfirstname varchar(60),
     @currlastname varchar(60),
     @properfirstname varchar(60),
     @properlastname varchar(60),
     @proper_addr1 varchar(60),
  @proper_addr2 varchar(60),
  @proper_addr3 varchar(60),
  @proper_city varchar(35),
  @proper_state varchar(4),
  @proper_zip varchar(10),
  @proper_bill_string varchar(360),
  @proper_ship_string varchar(360),
  @proper_temp_string varchar(360),
  @free_text char(1),
  @tpnum numeric(30,0) -- Added for Q19.04 , PR00089673 - MF47 , Kiosk OTC Processing - Restrict QMSI
  /*VARIABLE DECLARATION FOR ERROR LOGGING*/
 Declare  @proc_params varchar(4000),
    @ERROR_NUMBER varchar(4000),
    @ERROR_SEVERITY varchar(4000),
    @ERROR_STATE varchar(4000),
    @ERROR_PROCEDURE varchar(4000),
    @ERROR_LINE varchar(4000),
    @ERROR_MESSAGE varchar(4000),
    @proc_name varchar(400),
    @return_code int
/*ERROR TRACK STARTS FOR 91836 20.02*/
BEGIN TRY

set @addr_error = 0
  set @HIPPAAGE = 18

  -- ERROR to be returned
  declare @ERROR as varchar(5000)
  set @ERROR = ''

  -- TMP ERROR (to hold info )
  declare @TMP_ERROR as varchar(2000)
  set @TMP_ERROR = ''

  -- Count of errors
  declare @ERRCOUNT as int
  set @ERRCOUNT = 1

  -- NOW so that the time doesn't change for each insert (if there are any)
  declare @NOW as datetime
  set @NOW = getdate()

      /*
      ** ensure we have the correct params passed in. Else return

      PRINT @ordernum
      */

      if(@ordernum IS NULL)
BEGIN
      set @errormsgs=null
          RETURN -1
END

  DECLARE @OPEN AS VARCHAR
  SET @OPEN = ''

  DECLARE @SAME_ADDR AS INT
  SET @SAME_ADDR = 0

  DECLARE @POBOX AS INT
  SET @POBOX = 0

  DECLARE @order_num AS INT
  SET @order_num = @ordernum

  DECLARE @ord_ship_method AS INT
  SET @ord_ship_method = -1

  DECLARE @pat_ship_method AS INT
  SET @pat_ship_method = -1

  --jss added to check for auth record

  DECLARE @noauthrecordfound INT
  SET @noauthrecordfound = -1


 -- get family id--
select @familyid = family_id from order_billship with (nolock) where order_num=@order_num --testraghav

    --04/02/2019 RSIT   PR89673 - Hard coded workflow_queue_num and trading_partner_num
set @tpnum = (select trading_partner_num from order_header with (nolock) where order_num = @order_num)

DECLARE @Wrong_Pat_On_Order AS INT
SET @Wrong_Pat_On_Order = -1

DECLARE @Hippa_Bad_Address AS INT
SET @Hippa_Bad_Address = -1

--  Sanity check performance fix Humana - 2012 week before Christmas - BEGIN
declare @cpdt datetime
declare @cpage datetime
set @cpdt = GETDATE()
set @cpage=dateadd(year,-18,@cpdt)

--  Sanity check performance fix Humana - 2012 week before Christmas - END

/* 2822458 T1PRJ0003815: INC1428855 - ePostRx - Refills for two different members were placed on the same order - starts */
/* checks if single order has multiple patients attached, if yes - it should struck in Hoppa verification queue */
DECLARE @Hippa_multiple_patient_count AS INT
IF EXISTS (SELECT 1 FROM dbo.code_value WITH(NOLOCK) WHERE code_value_keyword='SAME_ORDER_PROCESS_MULTIPLE_PATIENT' AND status_flag='A' )
BEGIN
if(@ordernum IS NOT NULL)
BEGIN

        if exists ( select * from order_lines with(nolock) where order_num=@ordernum and order_line_status_num in (1))
begin
select @Hippa_multiple_patient_count =  COUNT(distinct e.patient_num )
from e_script_msg_attributes e with(nolock)
          join order_lines ol with(nolock) on ol.e_script_msg_attribute_seq=e.e_script_msg_attribute_seq
where ol.order_num=@ordernum and  order_line_status_num in (1)  --62101506
end

END

END
/* 2822458 T1PRJ0003815: INC1428855 - ePostRx - Refills for two different members were placed on the same order - ends */

  /* Determine if the Order is OPEN */
select @OPEN = CAST(oh.order_status_num AS VARCHAR) from order_header oh WITH (NOLOCK)
where
    oh.[order_num] = @order_num --testraghav
  AND oh.order_status_num <> 1


/* get the patient # of member who owns the current ship-to address */
select @currentshippatnum = pa.patient_num
from patient_address pa WITH (NOLOCK),
             order_billship ob WITH (NOLOCK)
where pa.patient_addr_seq=ob.patient_ship_address_seq
  and ob.order_num=@ordernum --testraghav


    /* family mode, make sure that all members receiving items
       on this shipment are part of the same family.  They should be.
      */
    if(@familyid IS NOT NULL)
BEGIN
   -- family mode --
SELECT @Wrong_Pat_On_Order = COUNT(1) FROM
    [e_script_msg_attributes] e WITH (NOLOCK)
    JOIN [order_lines] ol WITH (NOLOCK) ON e.[e_script_msg_attribute_seq] = ol.[e_script_msg_attribute_seq]
    AND ol.[order_line_status_num] = 1
    JOIN [order_header] oh WITH (NOLOCK) ON ol.[order_num] = oh.[order_num]
    JOIN [order_billship] ob WITH (NOLOCK) ON oh.[order_num] = ob.[order_num]
    JOIN [patient_general] pg WITH (NOLOCK) ON e.patient_num = pg.[patient_num]
WHERE
    pg.[family_id] <> ob.[family_id]
  AND oh.order_status_num IN (1, 2)
  AND oh.[order_num] = @order_num --testraghav
END

  /* HIPPA - make sure we are using the shipping address
     of a member receiving items in the shipment.  Package must be addressed
     to a member with contents in the shipment
  */

   if(@familyid IS NOT NULL)
BEGIN
 IF (@tpnum NOT IN (SELECT trading_partner_num
      FROM trading_partner_general tpg WITH (NOLOCK)
      JOIN trading_partner_types tpt WITH (NOLOCK)
      ON tpg.trading_partner_type_id = tpt.trading_partner_type_id
       WHERE tpt.trading_partner_type_desc = 'KIOSK PHARMACY'))
BEGIN

SELECT @Hippa_Bad_Address = COUNT(1) FROM
    [e_script_msg_attributes] e WITH (NOLOCK)
    JOIN [order_lines] ol WITH (NOLOCK) ON e.[e_script_msg_attribute_seq] = ol.[e_script_msg_attribute_seq]
    AND ol.[order_line_status_num] = 1
    JOIN [order_header] oh WITH (NOLOCK) ON ol.[order_num] = oh.[order_num]
    JOIN [order_billship] ob WITH (NOLOCK) ON oh.[order_num] = ob.[order_num]
    JOIN [patient_address] pa WITH (NOLOCK) ON ob.[patient_ship_address_seq] = pa.[patient_addr_seq]
WHERE
    e.[patient_num] = pa.[patient_num]
  AND oh.order_status_num IN (1, 2)
  AND oh.[order_num] = @order_num --testraghav

END
END

-- NOT FAMILY MODE
    if(@familyid IS NULL)
BEGIN

select @currentshippatnum = ISNULL(min(pa.patient_num),0),
       @currentshipseq = ISNULL(min(ob.patient_ship_address_seq),0),
       @currentbillseq = ISNULL(min(ob.patient_address_seq),0)
from patient_address pa WITH (NOLOCK),
           order_billship ob WITH (NOLOCK)
where pa.patient_addr_seq=ob.patient_ship_address_seq
  and ob.order_num=@ordernum --testraghav


select @currentbillpatnum = order_payment_owner
from order_billship WITH (NOLOCK)
where order_num=@ordernum --testraghav

    IF (@tpnum NOT IN (SELECT trading_partner_num
    FROM trading_partner_general tpg WITH (NOLOCK)
    JOIN trading_partner_types tpt WITH (NOLOCK)
    ON tpg.trading_partner_type_id = tpt.trading_partner_type_id
    WHERE tpt.trading_partner_type_desc = 'KIOSK PHARMACY'))
BEGIN

      if exists( select 1
      from patient_address pa WITH (NOLOCK) inner join patient_address_type_assignments pata with (nolock)
           on pa.patient_addr_seq=pata.patient_addr_seq
        where pa.patient_num = @currentbillpatnum --testraghav

         and pata.address_type_num=10
         and pa.active = 'Y')
BEGIN
select @def_bill_addr = pa.patient_addr_seq
from patient_address pa WITH (NOLOCK) inner join patient_address_type_assignments pata with (nolock)
on pa.patient_addr_seq=pata.patient_addr_seq
where pa.patient_num = @currentbillpatnum --testraghav
  and pata.address_type_num=10
  and pa.active = 'Y'

end
else
BEGIN
         /* ERROR - NO DEF BILL ADDR */
select @addr_error = 2
END


    if exists( select 1
       from patient_address pa WITH (NOLOCK) inner join patient_address_type_assignments pata with (nolock)
             on pa.patient_addr_seq=pata.patient_addr_seq
         where pa.patient_num = @currentshippatnum--testraghav
          and pata.address_type_num=11
          and pa.active = 'Y')
BEGIN
select @def_ship_addr = pa.patient_addr_seq
from patient_address pa WITH (NOLOCK) inner join patient_address_type_assignments pata with (nolock)
on pa.patient_addr_seq=pata.patient_addr_seq
where pa.patient_num = @currentshippatnum --testraghav
  and pata.address_type_num=11
  and pa.active = 'Y'

END
else
BEGIN
     /* ERROR - NO DEF SHIP ADDR */
select @addr_error = 5
END
END

END

  /* lets check all our address STUFF for FAMILY MODE*/
    if(@familyid IS NOT NULL)
BEGIN
            /* get applicable data elements needed to determine
      what address the order should be try to use
      and whether it actually IS using the correct one or not
   */

   /*
get the seq of the current ship address
               and the patient who owns it
            */

select @currentshippatnum = ISNULL(min(pa.patient_num),0),
       @currentshipseq = ISNULL(min(ob.patient_ship_address_seq),0),
       @currentbillseq = ISNULL(min(ob.patient_address_seq),0)
from patient_address pa WITH (NOLOCK),
         order_billship ob WITH (NOLOCK)
where pa.patient_addr_seq=ob.patient_ship_address_seq
  and ob.order_num=@ordernum --testraghav

    if @currentshipseq = 0
begin
select @free_text = 'Y'
end


      if @currentshippatnum > 0
BEGIN
select @dob = patient_dob
from patient_general WITH (NOLOCK)
where patient_num=@currentshippatnum

--select @currshippatage = dbo.F_AgeCalculation(@dob,GetDate())
select @currshippatage = CASE
                             WHEN
    MONTH(GETDATE()) > MONTH(@dob)
             OR MONTH(GETDATE()) = MONTH(@dob)
             AND DAY(GETDATE()) >= DAY(@dob)
            THEN
             DATEDIFF(year, @dob, GETDATE())
            ELSE
             DATEDIFF(year, @dob, GETDATE()) - 1
END

                 /* get current shipaddress owner cardholder status */
select @currshippatcardholder = pf.head_of_household
from patient_family pf WITH (NOLOCK)
where pf.patient_num = @currentshippatnum --testraghav

select @currfirstname = pg.patient_first_name,
       @currlastname = pg.patient_last_name
from patient_general pg WITH (NOLOCK)
where patient_num = @currentshippatnum  --testraghav

END

   /* HIPAA- check that shipmet is addressed to proper member within the shipment */

  --PR89673  M1907 DF-143: SIT_RTL_19.07_Test2_REG_Orders go to Failed Claims OTC with Sanity error for FLMEDICAID and LTC members
      IF (@tpnum NOT IN (SELECT trading_partner_num
    FROM trading_partner_general tpg WITH (NOLOCK)
    JOIN trading_partner_types tpt WITH (NOLOCK)
    ON tpg.trading_partner_type_id = tpt.trading_partner_type_id
     WHERE tpt.trading_partner_type_desc = 'KIOSK PHARMACY'))
BEGIN
   --get ranking adult member in the shipment
select @patient_num_adult = ISNULL(min(pg.patient_num),0)    --@patient_num =
from patient_family pf WITH (NOLOCK),
                   patient_general pg WITH (NOLOCK),
    order_lines ol WITH (NOLOCK),
    order_billship ob WITH (NOLOCK),
    e_script_msg_attributes es WITH (NOLOCK)
    left join rx_fill_aux rfa WITH (NOLOCK) on es.e_script_msg_attribute_seq = rfa.e_script_msg_attribute_seq,
    patient_plans pp WITH (NOLOCK)
where ol.order_num = @ordernum
  and ol.e_script_msg_attribute_seq=es.e_script_msg_attribute_seq
  and es.patient_num=pg.patient_num
  and pg.patient_num = pf.patient_num
  and ol.order_num = ob.order_num
  and pf.family_id = ob.family_id
  and pg.patient_dob<@cpage
  and pg.general_status_code = 'A'
  and pg.patient_num = pp.patient_num
  and pp.coverage_type_id<>4
  and (pp.cp_deactivation_date IS NULL or pp.cp_deactivation_date > @cpdt)
  and (pp.cp_expiration_date IS NULL or pp.cp_expiration_date > @cpdt)
  and pp.pp_num = CASE
    when ISNULL(es.otc_ppnum,0) = 1 then 2
    else ISNULL(rfa.pp_num,2)
END
and pp.relationship_num = (select MIN(pp2.relationship_num)
                                          from e_script_msg_attributes es2 WITH (NOLOCK),
                                               patient_general pg2 WITH (NOLOCK),
              order_lines ol2 WITH (NOLOCK),
              patient_plans pp2 WITH (NOLOCK),
              patient_family pf2 WITH (NOLOCK),
              order_billship obs2 WITH (NOLOCK)
          where ol2.order_num = @ordernum --TESTRAGHAV
            and ol2.e_script_msg_attribute_seq=es2.e_script_msg_attribute_seq
            and es2.patient_num = pp2.patient_num
            and pp2.patient_num = pg2.patient_num
            and pg2.general_status_code = 'A'
            and pg2.patient_dob<@cpage
           and pp2.pp_num = pp.pp_num
            and pg2.family_id = pf2.family_id
            and pf2.family_id = obs2.family_id
            )


             --get ranking minor on the shipment in case only minors exist
select @patient_num_minor = ISNULL(min(pg.patient_num) ,0)
from patient_family pf WITH (NOLOCK),
                   patient_general pg WITH (NOLOCK),
    order_lines ol WITH (NOLOCK),
    order_billship ob WITH (NOLOCK),
    e_script_msg_attributes es WITH (NOLOCK)
    left join rx_fill_aux rfa WITH (NOLOCK) on es.e_script_msg_attribute_seq = rfa.e_script_msg_attribute_seq,
    patient_plans pp WITH (NOLOCK)
where ol.order_num = @ordernum
  and ol.e_script_msg_attribute_seq=es.e_script_msg_attribute_seq
  and es.patient_num=pg.patient_num
  and pg.patient_num = pf.patient_num
  and ol.order_num = ob.order_num
  and pf.family_id = ob.family_id
  and pg.patient_dob>@cpage
  and pg.general_status_code = 'A'
  and pg.patient_num = pp.patient_num
  and pp.coverage_type_id<>4
  and (pp.cp_deactivation_date IS NULL or pp.cp_deactivation_date > @cpdt)
  and (pp.cp_expiration_date IS NULL or pp.cp_expiration_date > @cpdt)
  and pp.pp_num = CASE
    when ISNULL(es.otc_ppnum,0) = 1 then 2
    else ISNULL(rfa.pp_num,2)
END
and pp.relationship_num = (select MIN(pp2.relationship_num)
                                          from e_script_msg_attributes es2 WITH (NOLOCK),
                                               patient_general pg2 WITH (NOLOCK),
              order_lines ol2 WITH (NOLOCK),
              patient_plans pp2 WITH (NOLOCK),
              patient_family pf2 WITH (NOLOCK),
              order_billship obs2 WITH (NOLOCK)
          where ol2.order_num = @ordernum
            and ol2.e_script_msg_attribute_seq=es2.e_script_msg_attribute_seq
            and es2.patient_num = pp2.patient_num
            and pp2.patient_num = pg2.patient_num
            and pg2.general_status_code = 'A'
            and pg2.patient_dob>@cpage
            and pp2.pp_num = pp.pp_num
            and pg2.family_id = pf2.family_id
            and pf2.family_id = obs2.family_id
            )



        /* set the patient we SHOULD be shipping to into propershippat */
        if  (@patient_num_adult > 0)
begin
select @propershippat = @patient_num_adult
select @properfirstname = pg.patient_first_name,
       @properlastname = pg.patient_last_name
from patient_general pg WITH (NOLOCK)
where patient_num = @patient_num_adult --testraghav

end
else if (@patient_num_minor > 0)
begin
select @propershippat = @patient_num_minor --testraghav
select @properfirstname = pg.patient_first_name,
       @properlastname = pg.patient_last_name
from patient_general pg WITH (NOLOCK)
where patient_num = @patient_num_minor --testraghav
end
else
begin
select @addr_error = 12
/* SOME HOW NO ELIGIBLE SHIP MEMBER ON SHIPMENT */
end


  IF @propershippat is not null
BEGIN
    if exists( select 1
        from patient_address pa WITH (NOLOCK) inner join patient_address_type_assignments pata WITH (NOLOCK)
               on pa.patient_addr_seq=pata.patient_addr_seq
          where pa.patient_num = @propershippat --testraghav
           and pata.address_type_num=10
           and pa.active = 'Y')
BEGIN
select @def_bill_addr = pa.patient_addr_seq
from patient_address pa WITH (NOLOCK) inner join patient_address_type_assignments pata WITH (NOLOCK)
on pa.patient_addr_seq=pata.patient_addr_seq
where pa.patient_num = @propershippat --testraghav
  and pata.address_type_num=10
  and pa.active = 'Y'

END
else
BEGIN
      /* ERROR - NO DEF BILL ADDR */
select @addr_error = 2
END

   if exists ( select 1
       from patient_address pa WITH (NOLOCK) inner join patient_address_type_assignments pata WITH (NOLOCK)
             on pa.patient_addr_seq=pata.patient_addr_seq
         where pa.patient_num = @propershippat --testraghav
          and pata.address_type_num=11
          and pa.active = 'Y')
BEGIN
select @def_ship_addr = pa.patient_addr_seq
from patient_address pa with (nolock) inner join patient_address_type_assignments pata with (nolock)
on pa.patient_addr_seq=pata.patient_addr_seq
where pa.patient_num = @propershippat --testraghav
  and pata.address_type_num=11
  and pa.active = 'Y'

END
else
BEGIN
      /* ERROR - NO DEF SHIP ADDR */
select @addr_error = 5
END

END

END
END -- family mode only

/* END CHECK ADDRESS STUFF */

-- check if order is CC and there is a valid AUTH record
declare @paymentcardseq numeric(30)
declare @paymentmethodid numeric(9)
declare @orderglposted numeric(30)
declare @idseq numeric(30)
declare @orderamount numeric(30)


  -- get gl record from order
select @orderglposted = order_gl_posted,
       @orderamount = ISNULL(order_total_amount,0)
from order_header WITH (NOLOCK) where
    order_num=@ordernum --testraghav

--card assisgned to order?
select @paymentcardseq = ISNULL(payment_card_seq_num,0),
       @paymentmethodid  = ISNULL(payment_method_id,0)
from order_billship WITH (NOLOCK)
where order_num=@ordernum ---testraghav
-- card assigned to order?
    if(@paymentcardseq > 0)
BEGIN
    -- check if there is a valid AUTH record performed
SELECT @idseq = ISNULL(max(gl_post_num),0)
FROM payment_card_reply WITH (NOLOCK)
WHERE pc_reply_seq_num=(Select max(p.pc_reply_seq_num)
    FROM payment_card_reply p WITH (NOLOCK), general_ledger g WITH (NOLOCK)
    WHERE
    p.gl_post_num=g.gl_post_num
  and p.pc_reply_type='A'
  and p.pc_reply_auth_code IS NOT NULL
  and g.gl_post_num=@orderglposted) --testraghav
-- valid auth?
    if(@orderamount > 0)
BEGIN
     if(@idseq <= 0)
BEGIN
       SET @noauthrecordfound = 1
END
END
END

/* 2822458 T1PRJ0003815: INC1428855 - ePostRx - Refills for two different members were placed on the same order - starts */
/* checks if single order has multiple patients attached, if yes - it should struck in Hoppa verification queue */
IF EXISTS (SELECT 1 FROM dbo.code_value WITH(NOLOCK) WHERE code_value_keyword='SAME_ORDER_PROCESS_MULTIPLE_PATIENT' AND status_flag='A' )
BEGIN
    IF (@Hippa_multiple_patient_count >1)
BEGIN

     set @TMP_ERROR = ' (' + cast(@ERRCOUNT as varchar) + ')  Order contains more than one member - Need to verify ' + char(10)
     set @ERROR = @ERROR + ' ' + @TMP_ERROR
     set @ERRCOUNT = @ERRCOUNT + 1
     set @errormsgs=@ERROR
     set @overrideqname='HIPPA VERIFICATION QUEUE'
     set @overridequeue = (select top 1 workflow_queue_num from dbo.F_getWorkflowQueueNumbers('HIPAA VERIFICATION QUEUE',@tpnum))
     return 0


END
END
/* 2822458 T1PRJ0003815: INC1428855 - ePostRx - Refills for two different members were placed on the same order - ends */
 /******************************************************************************************/
 -- Validations Starts
 -- Team Avengers -1705- Incident#5703506 -ePost PROD: HAS EXPIRED_System Resolved
 -- Incident#5753738 - ePOST PROD_RX_EXPIRED check is not triggering 100% of the time

    declare @err_msg varchar(1500)
       set @err_msg=''

SELECT @err_msg= STUFF((SELECT ',' + ES.RX_NUMBER FROM   ORDER_LINES OL WITH (NOLOCK)
    JOIN E_SCRIPT_MSG_ATTRIBUTES ES WITH (NOLOCK)
    ON OL.E_SCRIPT_MSG_ATTRIBUTE_SEQ = ES.E_SCRIPT_MSG_ATTRIBUTE_SEQ
    WHERE
    OL.ORDER_NUM = @ordernum
    AND OL.ORDER_LINE_STATUS_NUM = 1
    AND  EXISTS (select  1
    from e_script_msg_attributes with (nolock)
    where e_script_msg_attribute_seq=ES.E_SCRIPT_MSG_ATTRIBUTE_SEQ and rx_expiration_date < convert (varchar(10),getdate(),120))
    FOR XML PATH ( '' )), 1,1,'')
FROM   ORDER_HEADER OH WITH (NOLOCK)
WHERE  OH.ORDER_NUM = @ordernum

    If (ISNULL(@err_msg,'') <> '')
begin
              set @err_msg=' Order contains expired Rx:'+@err_msg
select @err_msg
    set @overrideqname='DR. CALL TECH FAX'
     -- PR89673 - Hard coded workflow_queue_num and trading_partner_num
set @overridequeue = (select top 1 workflow_queue_num from dbo.F_getWorkflowQueueNumbers(@overrideqname,@tpnum))

SET @errormsgs = @err_msg
    RETURN 0
End


 -- Validations Ends
 -- Team Avengers -1705- Incident#5703506 -ePost PROD: HAS EXPIRED_System Resolved
 -- Incident#5753738 - ePOST PROD_RX_EXPIRED check is not triggering 100% of the time
 /******************************************************************************************/


------ START: RX HAS INSUFFICIENT QUANTITY TO FILL ------

-- Now get the count of total_dispensed_quantity
SELECT esma.rx_number, SUM(esma.dispensed_quantity) AS total_dispensed_quantity
INTO #total_dispensed_by_rx  --drop table #total_dispensed_by_rx
FROM dbo.e_script_msg_attributes esma WITH (NOLOCK) JOIN
    dbo.rx_fill_aux rfa WITH (NOLOCK) ON esma.e_script_msg_attribute_seq = rfa.e_script_msg_attribute_seq
    JOIN dbo.e_script_msg_attributes esmao WITH (NOLOCK) on  rfa.rx_number = esmao.rx_number
    join ORDER_HEADER OH WITH (NOLOCK) on oh.order_invoice_number = esmao.order_invoice_number
WHERE oh.order_num = @ordernum
  and rfa.fill_status_num in (2,9)
GROUP BY esma.rx_number


SELECT @err_msg= STUFF((SELECT ',' + ES.RX_NUMBER FROM ORDER_LINES OL WITH (NOLOCK)
    JOIN E_SCRIPT_MSG_ATTRIBUTES ES WITH (NOLOCK)
    ON OL.E_SCRIPT_MSG_ATTRIBUTE_SEQ = ES.E_SCRIPT_MSG_ATTRIBUTE_SEQ
    join #total_dispensed_by_rx disprx on es.rx_number = disprx.rx_number
    WHERE ((es.num_refills + 1) * es.written_quantity) < disprx.total_dispensed_quantity + es.dispensed_quantity
    AND OL.ORDER_NUM = @ordernum
    AND OL.ORDER_LINE_STATUS_NUM = 1
    FOR XML PATH ( '' )), 1,1,'')
FROM   ORDER_HEADER OH WITH (NOLOCK)
WHERE  OH.ORDER_NUM = @ordernum

    IF (ISNULL(@err_msg,'') <> '')
BEGIN
              set @err_msg=' Order contains Rx has insufficient quantity to fill:'+@err_msg
select @err_msg

    set @overrideqname='DR. CALL TECH FAX'
     -- PR89673 - Hard coded workflow_queue_num and trading_partner_num
set @overridequeue = (select top 1 workflow_queue_num from dbo.F_getWorkflowQueueNumbers(@overrideqname,@tpnum))

SET @errormsgs = @err_msg
    RETURN 0
END

  ------ END: RX HAS INSUFFICIENT QUANTITY TO FILL ------


 /******************************************************************************************/
 -- Validations Starts
 -- Team Mavericks -1609- Incident#5017005 -EpostRX - Orders going out should be showing paid but showing either rejected or 0 cost to the member
 -- Incident#5400787 -epost - rejected items shipping after splitting paid items
 -- Q16.11 Defect#241993- Added conditions to match the validation same as in P_answorkflowrulecontext

 DECLARE @ord_not_paid AS INT
 declare @idsequence numeric(30)
   SET @ord_not_paid  = -1
   /*
  CHECK if all TP plan rxs on order have HAVE PAID status. Include SHIPPED STATUS since
  this rule may pass through workflow again once shipped
   */
SELECT @idsequence = count(*)  FROM
    order_lines l WITH (NOLOCK), e_script_msg_attributes e WITH (NOLOCK),
    order_header h WITH (NOLOCK), rx_fill_aux r WITH (NOLOCK), payor_plans p WITH (NOLOCK),
    payors ps with (nolock)-- Q16.11 Defect#241993-adding payors table
WHERE
    l.e_script_msg_attribute_seq=e.e_script_msg_attribute_seq
  and p.pp_num=r.pp_num
  and ps.payor_num=p.payor_num
  and r.e_script_msg_attribute_seq=e.e_script_msg_attribute_seq
  and l.order_num=h.order_num
  and l.order_line_status_num IN(1,3)
--skip reships
  and dbo.F_isOrderReshipped(@ordernum) = 0
-- check for paid
  and ISNULL(dbo.F_isPrimaryEcsPaid(e.e_script_msg_attribute_seq),0) = 0

  and p.general_status_code='A' --Q16.11 Defect#241993 -Adding condition to check if the attached plan is active
  and p.pp_type_code in ('A','C')-- added for incomm project
  and ps.payor_bill_type_num=2 --Q16.11 Defect#241993- Adding condition to check payor_bill_type_num= 2 (Real Time ECS)
  and h.order_num=@ordernum
    if(@idsequence > 0)
BEGIN
    SET @ord_not_paid = 1
END

 -- alert
 IF @ord_not_paid > 0
BEGIN
  set @TMP_ERROR = ' (' + cast(@ERRCOUNT as varchar) + ') Order contains RXs WITHOUT A PAID ECS STATUS. Please verify.'
  set @ERROR = @ERROR + ' ' + @TMP_ERROR
  set @ERRCOUNT = @ERRCOUNT + 1
  set @overrideqname='FAILED CLAIMS TASK APPROVAL'
  -- PR89673 - Hard coded workflow_queue_num and trading_partner_num
  set @overridequeue = (select top 1 workflow_queue_num from dbo.F_getWorkflowQueueNumbers(@overrideqname,@tpnum))

  SET @errormsgs = @ERROR
  RETURN 0
END
 -- Validations Ends
 -- Team Mavericks -1609-Incident#5017005 -EpostRX - Orders going out should be showing paid but showing either rejected or 0 cost to the member
 -- Incident#5400787 -epost - rejected items shipping after splitting paid items
 /******************************************************************************************/
  IF @OPEN IS NOT NULL AND @OPEN <> ''
BEGIN

  set @TMP_ERROR = '(' + cast(@ERRCOUNT as varchar) + ') Order is not open!'    + char(10)
  set @ERROR = @ERROR + ' ' + @TMP_ERROR
  set @ERRCOUNT = @ERRCOUNT + 1
END


IF @Wrong_Pat_On_Order > 0
BEGIN

 set @TMP_ERROR = ' (' + cast(@ERRCOUNT as varchar) + ') A patient on this order is *NOT* valid (please verify all members).' + char(10)
 set @ERROR = @ERROR + ' ' + @TMP_ERROR
 set @ERRCOUNT = @ERRCOUNT + 1

END

IF @Hippa_Bad_Address = 0 or @Hippa_Bad_Address is NULL
BEGIN

 set @TMP_ERROR = ' (' + cast(@ERRCOUNT as varchar) + ') HIPAA ship-to violation.  Address does not belong to a member within the shipment' + char(10)
 set @ERROR = @ERROR + ' ' + @TMP_ERROR
 set @ERRCOUNT = @ERRCOUNT + 1
 --Incident # 6944238 - Orders routing to STM queue instead of HIPAA Verification Queue
 --Start
 set @overrideqname='HIPPA VERIFICATION QUEUE'
 -- PR89673 - Hard coded workflow_queue_num and trading_partner_num
 set @overridequeue = (select top 1 workflow_queue_num from dbo.F_getWorkflowQueueNumbers('HIPAA VERIFICATION QUEUE',@tpnum))

 --End I #6944238
END
  /******************************************************************************************
  Start of Mavericks - Code change ; Incident#5403811; Release:16.09
  *******************************************************************************************/
IF @addr_error > 0 AND @addr_error <> 12
BEGIN

 set @TMP_ERROR =
    case

        when @addr_error = 2 then ' (' + cast(@ERRCOUNT as varchar) + ') Addr Error.  No Default Bill on file'  + char(10)

         when @addr_error = 5 then ' (' + cast(@ERRCOUNT as varchar) + ') Addr Error.  No Default Ship on File'  + char(10)

END


    set @ERROR = @ERROR + ' ' + @TMP_ERROR
 set @ERRCOUNT = @ERRCOUNT + 1
    set @overrideqname='HIPPA VERIFICATION QUEUE'
 -- PR89673 - Hard coded workflow_queue_num and trading_partner_num
 set @overridequeue = (select top 1 workflow_queue_num from dbo.F_getWorkflowQueueNumbers('HIPAA VERIFICATION QUEUE',@tpnum))


END
  /******************************************************************************************
  End of Mavericks - Code change ; Incident#5403811; Release:16.09
  *******************************************************************************************/
ELSE if @addr_error = 12
BEGIN
 SET @TMP_ERROR =' (' + cast(@ERRCOUNT as varchar) + ') Addr Error.  No Proper Ship Member '   + char(10)
 set @ERROR = @ERROR + ' ' + @TMP_ERROR
 set @ERRCOUNT = @ERRCOUNT + 1
 set @overrideqname='FAILED CLAIMS'
 -- PR89673 - Hard coded workflow_queue_num and trading_partner_num
 set @overridequeue = (select top 1 workflow_queue_num from dbo.F_getWorkflowQueueNumbers(@overrideqname,@tpnum))

end


------****************** User Story 225471 - 91606 - MF1 FR1.1 CCEQ Rule A - Finance Queue Initiative Databse Development changes*********

       IF @noauthrecordfound > 0
BEGIN
   set @TMP_ERROR = ' (' + cast(@ERRCOUNT as varchar) + ') Order with CC Payment Method does not have a valid AUTH record (please verify).'   + char(10)
 + CONVERT(VARCHAR,@orderamount)
 set @ERROR = isnull(@ERROR ,'')+ ' ' + @TMP_ERROR
 set @ERRCOUNT = @ERRCOUNT + 1
 SET @overrideqname='CREDIT CARD EXCEPTIONS QUEUE'
 IF EXISTS (SELECT 1 FROM epostrx_workflow.dbo.rules WITH (NOLOCK) WHERE rule_name = 'FINANCE QUEUE' AND rule_enabled = 'Y' and @queueToBeUsed is not null)
BEGIN
          IF @queueToBeUsed = '0'
     SET @overrideqname='FINANCE'
    ELSE
     SET @overrideqname='FINANCE OTC'
END
 -- PR89673 - Hard coded workflow_queue_num and trading_partner_num
  set @overridequeue = (select top 1 workflow_queue_num from dbo.F_getWorkflowQueueNumbers(@overrideqname,@tpnum))

END

------****************** User Story 225471 - 91606 - MF1 FR1.1 CCEQ Rule A - Finance Queue Initiative Databse Development changes*********



  SET @errormsgs = @ERROR

  -- RETURN Error # 0 for no problem
  IF @errormsgs IS NOT NULL OR @errormsgs <> ''
BEGIN
RETURN 0
END


RETURN 0
END TRY
/*ERROR TRACK ENDS FOR 91836 20.02*/
/*ERROR CATCH STARTS FOR 91836 20.02*/
BEGIN CATCH
SELECT @ERROR_NUMBER = ERROR_NUMBER(),
       @ERROR_SEVERITY = ERROR_SEVERITY(),
       @ERROR_STATE = ERROR_STATE(),
       @ERROR_PROCEDURE = ERROR_PROCEDURE(),
       @ERROR_LINE = ERROR_LINE(),
       @ERROR_MESSAGE = ERROR_MESSAGE()

select @return_code = -99
/*EXEC P_anslogsprocerrors
      @proc_name = 'P_ansordersanitycheck',
      @proc_params =@ordernum,
      @ERROR_NUMBER = @ERROR_NUMBER,
      @ERROR_SEVERITY = @ERROR_SEVERITY,
      @ERROR_STATE = @ERROR_STATE,
      @ERROR_PROCEDURE = @ERROR_PROCEDURE,
      @ERROR_LINE = @ERROR_LINE,
      @ERROR_MESSAGE = @ERROR_MESSAGE,
      @return_code = @return_code OUTPUT      */

END CATCH
/*ERROR CATCH ENDS FOR 91836 20.02*/
END

GO