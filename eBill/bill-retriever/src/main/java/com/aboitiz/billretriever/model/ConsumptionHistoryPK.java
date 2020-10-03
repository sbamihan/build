/*
 * To change this license header, choose License Bill in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billretriever.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumptionHistoryPK implements Serializable {

	private static final long serialVersionUID = 6828958188837938696L;
	@Column(name = "TRAN_NO")
	private Long tranNo;
	@Column(name = "RDG_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date rdgDate;

}
