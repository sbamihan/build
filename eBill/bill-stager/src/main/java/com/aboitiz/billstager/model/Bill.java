/*
 * To change this license header, choose License Bill in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billstager.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bill {

	private Long tranNo;
	private String batchCd;
	private long batchNo;
	private long duSetId;
	private String billingBatchNo;
	private String billColor;
	private String courierCode;
	private String billType;
	private Date billMonth;
	private Date billDate;
	private Date dueDate;
	private String billNo;
	private String areaCode;
	private Short readingBatchNo;
	private Integer bookNo;
	private Integer oldSeqNo;
	private Long newSeqNo;
	private String crc;
	private String acctNo;
	private String rateSchedule;
	private String rateScheduleDesc;
	private String customerName;
	private String premiseAdd1;
	private String premiseAdd2;
	private String premiseAdd3;
	private String billingAdd1;
	private String billingAdd2;
	private String billingAdd3;
	private BigDecimal powerFactorValue;
	private BigDecimal billedKwhrCons;
	private BigDecimal billedDemandCons;
	private BigDecimal billedKvarCons;
	private BigDecimal overdueAmt;
	private Short overdueBillCount;
	private BigDecimal billAmt;
	private BigDecimal totalAmtDue;
	private Date lastPaymentDate;
	private BigDecimal lastPaymentAmount;
	private String mainSaId;
	private String altBillId;
	private Date parMonth;
	private BigDecimal parKwhr;
	private Date completeDate;
	private Character noBatchPrtSw;
	private Character ebillOnlySw;
	private Date extractedOn;
	private String tin;
	private String busActivity;
	private String busAdd1;
	private String busAdd2;
	private String busAdd3;
	private String busAdd4;
	private String busAdd5;
	private BillMessage billMessage;
	private Collection<MeterDetail> meterDetails;
	private Collection<ConsumptionHistory> consumptionHistory;
	private Collection<LineDetail> lineDetails;
	private Collection<Contact> contacts;
	private String uuid;

}
