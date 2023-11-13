package com.inetum.appliBibliotheque.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.inetum.appliBibliotheque.dto.ApiError;
import com.inetum.appliBibliotheque.exception.LivreIndisponibleException;
import com.inetum.appliBibliotheque.exception.NotFoundException;



@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable( 		HttpMessageNotReadableException ex,
																		HttpHeaders headers, 
																		HttpStatus status, 
																		WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error));
	}

	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(NotFoundException ex) {
		return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()));
	}
	
	@ExceptionHandler(LivreIndisponibleException.class)
	protected ResponseEntity<Object> handleEntityNotFound(LivreIndisponibleException ex) {
		return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()));
	}
		
	@ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
	protected ResponseEntity<Object> handleEntityNotFound(org.springframework.dao.DataIntegrityViolationException ex) {
		String s=ex.getMostSpecificCause().toString();
		String requiredString = s.substring(s.indexOf("ORA"), s.indexOf("ORA")+9);
		return buildResponseEntity(new ApiError(HttpStatus.FORBIDDEN, "violation de contrainte : "+requiredString));
	}

	/*
	@ExceptionHandler(ConflictException.class)
	protected ResponseEntity<Object> handleConflict(ConflictException ex) {
		return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, ex.getMessage()));
	}
	*/
}
