package com.springrain.easycheer.rest;

import com.springrain.easycheer.exception.BaseException;

public class ErrorResponse {

	private int errorCode;

	private String errorMessage;

	public ErrorResponse() {
	}
	
	public ErrorResponse(BaseException exception) {
		this.errorCode = exception.getErrorCode();
		this.errorMessage = exception.getErrorMessage();
	}

	public ErrorResponse(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
