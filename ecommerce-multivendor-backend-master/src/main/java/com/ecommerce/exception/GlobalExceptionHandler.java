package com.ecommerce.exception;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.dto.CommonApiResponse;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<CommonApiResponse> handleUserNotFoundException(UserNotFoundException ex) {
		String responseMessage = ex.getMessage();

		CommonApiResponse apiResponse = new CommonApiResponse();

		apiResponse.setResponseMessage(responseMessage);
		apiResponse.setSuccess(false);
		return new ResponseEntity<CommonApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(UserSaveFailedException.class)
	public ResponseEntity<CommonApiResponse> handleUserRegistrationFailedException(UserSaveFailedException ex) {
		String responseMessage = ex.getMessage();

		CommonApiResponse apiResponse = new CommonApiResponse();

		apiResponse.setResponseMessage(responseMessage);
		apiResponse.setSuccess(false);
		return new ResponseEntity<CommonApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(CategorySaveFailedException.class)
	public ResponseEntity<CommonApiResponse> handleCategorySaveFailedException(CategorySaveFailedException ex) {
		String responseMessage = ex.getMessage();

		CommonApiResponse apiResponse = new CommonApiResponse();

		apiResponse.setResponseMessage(responseMessage);
		apiResponse.setSuccess(false);
		return new ResponseEntity<CommonApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(ProductSaveFailedException.class)
	public ResponseEntity<CommonApiResponse> handleProductSaveFailedException(ProductSaveFailedException ex) {
		String responseMessage = ex.getMessage();

		CommonApiResponse apiResponse = new CommonApiResponse();

		apiResponse.setResponseMessage(responseMessage);
		apiResponse.setSuccess(false);
		return new ResponseEntity<CommonApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(CartSaveFailedException.class)
	public ResponseEntity<CommonApiResponse> handleCartSaveFailedException(CartSaveFailedException ex) {
		String responseMessage = ex.getMessage();

		CommonApiResponse apiResponse = new CommonApiResponse();

		apiResponse.setResponseMessage(responseMessage);
		apiResponse.setSuccess(false);
		return new ResponseEntity<CommonApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(OrderSaveFailedException.class)
	public ResponseEntity<CommonApiResponse> handleOrderSaveFailedException(OrderSaveFailedException ex) {
		String responseMessage = ex.getMessage();

		CommonApiResponse apiResponse = new CommonApiResponse();

		apiResponse.setResponseMessage(responseMessage);
		apiResponse.setSuccess(false);
		return new ResponseEntity<CommonApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	
	  @ExceptionHandler(ConstraintViolationException.class)
	  public ResponseEntity<CommonApiResponse>emailvalidationexception(ConstraintViolationException cv){
	  
	  
		  CommonApiResponse api = new CommonApiResponse();
	        Set<ConstraintViolation<?>> violations = cv.getConstraintViolations();

	        // Extract interpolated messages into a list of strings
	        String messages = violations.stream()
	                                    .map(ConstraintViolation::getMessage)
	                                    .collect(Collectors.joining("; "));

	        api.setResponseMessage(messages);
	        api.setSuccess(false);

	       
	  
	  return new
	  ResponseEntity<CommonApiResponse>(api,HttpStatusCode.valueOf(500)); }
	 
	
}
