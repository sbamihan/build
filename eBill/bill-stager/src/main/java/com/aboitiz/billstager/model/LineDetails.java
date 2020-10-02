package com.aboitiz.billstager.model;

import java.math.BigDecimal;

/**
 *
 * @author SBAmihan
 */
public class LineDetails {

	protected LineDetailsPK lineDetailsPK;
	private Long printPriority;
	private String description;
	private String rate;
	private BigDecimal amount;
	private Header header;

	public LineDetails() {
	}

	public LineDetailsPK getLineDetailsPK() {
		return lineDetailsPK;
	}

	public void setLineDetailsPK(LineDetailsPK lineDetailsPK) {
		this.lineDetailsPK = lineDetailsPK;
	}

	public Long getPrintPriority() {
		return printPriority;
	}

	public void setPrintPriority(Long printPriority) {
		this.printPriority = printPriority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((header == null) ? 0 : header.hashCode());
		result = prime * result + ((lineDetailsPK == null) ? 0 : lineDetailsPK.hashCode());
		result = prime * result + ((printPriority == null) ? 0 : printPriority.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineDetails other = (LineDetails) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (header == null) {
			if (other.header != null)
				return false;
		} else if (!header.equals(other.header))
			return false;
		if (lineDetailsPK == null) {
			if (other.lineDetailsPK != null)
				return false;
		} else if (!lineDetailsPK.equals(other.lineDetailsPK))
			return false;
		if (printPriority == null) {
			if (other.printPriority != null)
				return false;
		} else if (!printPriority.equals(other.printPriority))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LineDetails [lineDetailsPK=" + lineDetailsPK + ", printPriority=" + printPriority + ", description="
				+ description + ", rate=" + rate + ", amount=" + amount + ", header=" + header + "]";
	}

}
