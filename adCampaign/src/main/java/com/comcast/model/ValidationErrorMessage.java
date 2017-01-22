package com.comcast.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ValidationErrorMessage extends ErrorMessage {

	private List<SimpleFieldError> fields;

	public ValidationErrorMessage(String code, String message, List<FieldError> fieldErrors) {
		super(code, message);
		this.fields = processFieldErrors(fieldErrors);
	}

	private List<SimpleFieldError> processFieldErrors(List<FieldError> fieldErrors) {
		return fieldErrors.stream()
				.map(fieldError -> new SimpleFieldError(fieldError))
				.collect(Collectors.toList());
	}

}
