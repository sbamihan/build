/*
 * To change this license header, choose License Bill in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aboitiz.billretriever.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeterDetailsPK implements Serializable {

	private static final long serialVersionUID = -3984117926313050468L;
	@Column(name = "TRAN_NO")
	private long tranNo;
	@Column(name = "BADGE_NO")
	private String badgeNo;

}
