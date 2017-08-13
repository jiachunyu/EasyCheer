package com.springrain.easycheer.exception;

public class ObjectNotFoundException extends RestBaseException {

	public ObjectNotFoundException(int errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

}
