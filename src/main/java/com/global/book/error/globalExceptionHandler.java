package com.global.book.error;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class globalExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {

			List<String> errors= new ArrayList<>();
			
			for(FieldError error : ex.getBindingResult().getFieldErrors()) {
				errors.add(error.getDefaultMessage());
			}
			for(ObjectError error : ex.getBindingResult().getGlobalErrors()) {
				errors.add(error.getDefaultMessage());
			}
			
			ErrorResponse error= new ErrorResponse(ex.toString(),errors);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> handleRecordNotFound(RecordNotFoundException e){
		
		ErrorResponse error = new ErrorResponse(e.getLocalizedMessage(),Arrays.asList(e.getMessage()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	@ExceptionHandler(DuplicateRecordException.class)
	public ResponseEntity<?> handleDuplicateRecordException(DuplicateRecordException e){
		
		ErrorResponse error = new ErrorResponse(e.getLocalizedMessage(),Arrays.asList(e.getMessage()));
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
	public final ResponseEntity<?> handleAllExceptions(Exception e, WebRequest request){
		
		List<String> errors = new ArrayList<>();
		errors.add(e.getLocalizedMessage());

		ErrorResponse error = new ErrorResponse(e.getLocalizedMessage(),errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
