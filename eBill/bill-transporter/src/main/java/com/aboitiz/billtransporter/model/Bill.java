/*
 * To change this license header, choose License Bill in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billtransporter.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

	@JsonIgnore
	private String billGroupId;
	@JsonIgnore
	private String uuid;
	private Long tranNo;
	private String batchCd;
	private long batchNo;
	private long duSetId;
	private String billingBatchNo;
	private String billColor;
	private String courierCode;
	private String billType;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private Date billMonth;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private Date billDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private Date lastPaymentDate;
	private BigDecimal lastPaymentAmount;
	private String mainSaId;
	private String altBillId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private Date parMonth;
	private BigDecimal parKwhr;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private Date completeDate;
	private Character noBatchPrtSw;
	private Character ebillOnlySw;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
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

}
