package com.aboitiz.billstager.exception;

public class InvalidBillExtractedEventException extends Exception {

	private static final long serialVersionUID = -8859602507212556655L;

	public InvalidBillExtractedEventException(String errorMessage) {
		super(errorMessage);
	}

}
