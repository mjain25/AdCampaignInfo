package com.comcast.model;

import org.springframework.validation.FieldError;

import lombok.Data;

@Data
public class SimpleFieldError {
	public SimpleFieldError(FieldError fieldError) {
		this.fieldName = fieldError.getField();
		this.error = fieldError.getDefaultMessage();
	}
	private String fieldName;
	private String error;
}
