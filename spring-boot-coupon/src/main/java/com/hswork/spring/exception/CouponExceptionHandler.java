package com.hswork.spring.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CouponExceptionHandler {
	
	private static final String INTERNAL_ERROR_MSG = "내부 처리 중 지연이 발생하였습니다. 잠시 후 다시 시도해 주십시오.";
	
	@ExceptionHandler(AlreadyIssueException.class)
	@ResponseBody
	public ResponseEntity<ErrorMessage> alreadyIssueExceptionHandler(HttpServletRequest request
			, AlreadyIssueException ex) {
		ErrorMessage errorMsg = new ErrorMessage();
		errorMsg.setErrorMessage(ex.getMessage());
		
		return new ResponseEntity<ErrorMessage>(errorMsg, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<ErrorMessage> exceptionHandler(HttpServletRequest request
			, Exception ex) {
		ErrorMessage errorMsg = new ErrorMessage();
		errorMsg.setErrorMessage(INTERNAL_ERROR_MSG);
		
		return new ResponseEntity<ErrorMessage>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
