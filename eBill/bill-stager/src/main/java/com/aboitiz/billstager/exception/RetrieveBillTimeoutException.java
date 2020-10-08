package com.aboitiz.billstager.exception;

import java.util.concurrent.TimeoutException;

public class RetrieveBillTimeoutException extends TimeoutException {

	private static final long serialVersionUID = -1342805852582390979L;

	public RetrieveBillTimeoutException(String errorMessage) {
		super(errorMessage);
	}

}
