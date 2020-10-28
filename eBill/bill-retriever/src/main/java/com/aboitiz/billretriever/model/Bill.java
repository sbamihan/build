/*
 * To change this license header, choose License Bill in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billretriever.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BP_HEADERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

	@Id
	@Column(name = "TRAN_NO")
	private Long tranNo;
	@Column(name = "BATCH_CD")
	private String batchCd;
	@Column(name = "BATCH_NO")
	private long batchNo;
	@Column(name = "DU_SET_ID")
	private long duSetId;
	@Column(name = "BILLING_BATCH_NO")
	private String billingBatchNo;
	@Column(name = "BILL_COLOR")
	private String billColor;
	@Column(name = "COURIER_CODE")
	private String courierCode;
	@Column(name = "BILL_TYPE")
	private String billType;
	@Column(name = "BILL_MONTH")
	@Temporal(TemporalType.TIMESTAMP)
	private Date billMonth;
	@Column(name = "BILL_DATE")
	@Temporal(TemporalType.DATE)
	private Date billDate;
	@Column(name = "DUE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dueDate;
	@Column(name = "BILL_NO")
	private String billNo;
	@Column(name = "AREA_CODE")
	private String areaCode;
	@Column(name = "READING_BATCH_NO")
	private Short readingBatchNo;
	@Column(name = "BOOK_NO")
	private Integer bookNo;
	@Column(name = "OLD_SEQ_NO")
	private Integer oldSeqNo;
	@Column(name = "NEW_SEQ_NO")
	private Long newSeqNo;
	@Column(name = "CRC")
	private String crc;
	@Column(name = "ACCT_NO")
	private String acctNo;
	@Column(name = "RATE_SCHEDULE")
	private String rateSchedule;
	@Column(name = "RATE_SCHEDULE_DESC")
	private String rateScheduleDesc;
	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	@Column(name = "PREMISE_ADD1")
	private String premiseAdd1;
	@Column(name = "PREMISE_ADD2")
	private String premiseAdd2;
	@Column(name = "PREMISE_ADD3")
	private String premiseAdd3;
	@Column(name = "BILLING_ADD1")
	private String billingAdd1;
	@Column(name = "BILLING_ADD2")
	private String billingAdd2;
	@Column(name = "BILLING_ADD3")
	private String billingAdd3;
	@Column(name = "POWER_FACTOR_VALUE")
	private BigDecimal powerFactorValue;
	@Column(name = "BILLED_KWHR_CONS")
	private BigDecimal billedKwhrCons;
	@Column(name = "BILLED_DEMAND_CONS")
	private BigDecimal billedDemandCons;
	@Column(name = "BILLED_KVAR_CONS")
	private BigDecimal billedKvarCons;
	@Column(name = "OVERDUE_AMT")
	private BigDecimal overdueAmt;
	@Column(name = "OVERDUE_BILL_COUNT")
	private Short overdueBillCount;
	@Column(name = "BILL_AMT")
	private BigDecimal billAmt;
	@Column(name = "TOTAL_AMT_DUE")
	private BigDecimal totalAmtDue;
	@Column(name = "LAST_PAYMENT_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastPaymentDate;
	@Column(name = "LAST_PAYMENT_AMOUNT")
	private BigDecimal lastPaymentAmount;
	@Column(name = "MAIN_SA_ID")
	private String mainSaId;
	@Column(name = "ALT_BILL_ID")
	private String altBillId;
	@Column(name = "PAR_MONTH")
	@Temporal(TemporalType.TIMESTAMP)
	private Date parMonth;
	@Column(name = "PAR_KWHR")
	private BigDecimal parKwhr;
	@Column(name = "COMPLETE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date completeDate;
	@Column(name = "NO_BATCH_PRT_SW")
	private Character noBatchPrtSw;
	@Column(name = "EBILL_ONLY_SW")
	private Character ebillOnlySw;
	@Column(name = "EXTRACTED_ON")
	@Temporal(TemporalType.TIMESTAMP)
	private Date extractedOn;
	@Column(name = "TIN")
	private String tin;
	@Column(name = "BUS_ACTIVITY")
	private String busActivity;
	@Column(name = "BUS_ADD1")
	private String busAdd1;
	@Column(name = "BUS_ADD2")
	private String busAdd2;
	@Column(name = "BUS_ADD3")
	private String busAdd3;
	@Column(name = "BUS_ADD4")
	private String busAdd4;
	@Column(name = "BUS_ADD5")
	private String busAdd5;
	@JoinColumn(name = "MESSAGE_CODE", referencedColumnName = "MESSAGE_CODE", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private BillMessage billMessage;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bill")
	private Collection<MeterDetail> meterDetails;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bill")
	private Collection<ConsumptionHistory> consumptionHistory;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bill")
	private Collection<LineDetail> lineDetails;

}
