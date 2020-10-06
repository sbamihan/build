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
public class LineDetailPK implements Serializable {

	private static final long serialVersionUID = -6136714559873555944L;
	@Column(name = "TRAN_NO")
	private Long tranNo;
	@Column(name = "LINE_CODE")
	private String lineCode;

}
