package com.aboitiz.billstager.exception;

public class InvalidArgumentException extends Exception {

	private static final long serialVersionUID = 1014353599786890099L;

	public InvalidArgumentException(String errorMessage) {
		super(errorMessage);
	}

}
