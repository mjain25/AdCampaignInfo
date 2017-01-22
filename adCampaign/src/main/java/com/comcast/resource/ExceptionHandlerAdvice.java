package com.comcast.resource;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;

import com.comcast.exception.DataNotFoundException;
import com.comcast.model.ErrorMessage;
import com.comcast.model.ValidationErrorMessage;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorMessage handleDataNotFoundException(DataNotFoundException e) {
		log.warn("Handling DataNotFoundException: {}", e.getClass().getSimpleName(), e);
		return new ErrorMessage(HttpStatus.NOT_FOUND.name(), e.getMessage());
	}

	@ExceptionHandler(HttpServerErrorException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	@ResponseBody
	public ErrorMessage handleHttpServerException(HttpServerErrorException e) {
		log.warn("Handling HttpServerErrorException: {}", e.getClass().getSimpleName(), e);
		return new ErrorMessage(HttpStatus.SERVICE_UNAVAILABLE.name(), e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorMessage handleException(Exception e) {
		// log.warn("Handling Exception: {}", e.getClass().getSimpleName(), e);
		return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getMessage());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	@ResponseBody
	public ErrorMessage handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

		log.warn("Handling HttpMessageNotReadableException: {}", e.getClass().getSimpleName(), e);
		return new ErrorMessage(HttpStatus.SERVICE_UNAVAILABLE.name(), e.getMessage());
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessage handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		log.warn("Handling MissingServletRequestParameterException: {}", e.getClass().getSimpleName(), e);
		return new ErrorMessage(HttpStatus.BAD_REQUEST.name(), e.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ValidationErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.warn("Handling MethodArgumentNotValidException: {}", e.getClass().getSimpleName(), e);
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		return new ValidationErrorMessage(HttpStatus.BAD_REQUEST.name(), "One or more fields contain an error",
				fieldErrors);
	}
}
