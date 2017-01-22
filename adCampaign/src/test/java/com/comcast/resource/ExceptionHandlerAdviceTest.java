package com.comcast.resource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.client.HttpServerErrorException;

import com.comcast.exception.DataNotFoundException;
import com.comcast.model.ErrorMessage;
import com.comcast.model.ValidationErrorMessage;

public class ExceptionHandlerAdviceTest {

	private ExceptionHandlerAdvice exceptionHandlerAdvice;

	@Before
	public void setUp() throws Exception {
		exceptionHandlerAdvice = new ExceptionHandlerAdvice();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHandleDataNotFoundException() {
		ErrorMessage errorMessage = exceptionHandlerAdvice
				.handleDataNotFoundException(new DataNotFoundException("error"));
		assertEquals(HttpStatus.NOT_FOUND.name(), errorMessage.getCode());
	}

	@Test
	public void testHandleException() throws Exception {
		ErrorMessage errorMessage = exceptionHandlerAdvice.handleException(new Exception("error"));
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.name(), errorMessage.getCode());
	}

	@Test
	public void testHandleHttpServerException() throws Exception {
		ErrorMessage errorMessage = exceptionHandlerAdvice
				.handleHttpServerException(new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE, "error"));
		assertEquals(HttpStatus.SERVICE_UNAVAILABLE.name(), errorMessage.getCode());
	}

	@Test
	public void testHandleHttpMessageNotReadableException() throws Exception {
		ErrorMessage errorMessage = exceptionHandlerAdvice
				.handleHttpMessageNotReadableException(new HttpMessageNotReadableException("error"));
		assertEquals(HttpStatus.SERVICE_UNAVAILABLE.name(), errorMessage.getCode());
	}

	@Test
	public void testHandleMissingServletRequestParameterException() throws Exception {
		ErrorMessage errorMessage = exceptionHandlerAdvice.handleMissingServletRequestParameterException(
				new MissingServletRequestParameterException("error", "String"));
		assertEquals(HttpStatus.BAD_REQUEST.name(), errorMessage.getCode());
	}

	@Test
	public void testHandleMethodArgumentNotValidException() throws Exception {
		MethodArgumentNotValidException ex = Mockito.mock(MethodArgumentNotValidException.class);

		BeanPropertyBindingResult bindException = Mockito.mock(BeanPropertyBindingResult.class);
		when(ex.getBindingResult()).thenReturn((BindingResult) bindException);
		when(bindException.getFieldErrors()).thenReturn(new ArrayList<FieldError>());

		ValidationErrorMessage errorMessage = exceptionHandlerAdvice.handleMethodArgumentNotValidException(ex);
		assertEquals(HttpStatus.BAD_REQUEST.name(), errorMessage.getCode());
	}

}
