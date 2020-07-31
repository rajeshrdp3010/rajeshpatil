package com.product.order.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

public class CustomErrorMessage {
	
	private HttpStatus httpStatus;
	private List<String> errors;
	
	public CustomErrorMessage(HttpStatus httpStatus,List<String> errors) {
		this.httpStatus = httpStatus;
		this.errors = errors;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	
}
