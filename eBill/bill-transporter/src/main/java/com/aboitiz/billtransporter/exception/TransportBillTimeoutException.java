package com.aboitiz.billtransporter.exception;

import java.util.concurrent.TimeoutException;

public class TransportBillTimeoutException extends TimeoutException {

	private static final long serialVersionUID = -2904885239730178266L;

	public TransportBillTimeoutException(String errorMessage) {
		super(errorMessage);
	}

}
