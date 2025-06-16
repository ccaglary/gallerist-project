package com.caglar.handler;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.caglar.exception.BaseException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = {BaseException.class} )
	public ResponseEntity<ApiError<?>> handleBaseException(BaseException ex, WebRequest request) {
		return ResponseEntity.badRequest().body(createApiError(ex.getMessage(), request));
	}
	
	private String getHostName() {
		try {
			return Inet4Address.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			System.out.println("Hata oluştu " + e.getMessage());
		}
		return null;
	}
	
	public <E>ApiError<E> createApiError(E message, WebRequest request){
		
		ApiError<E> apiError = new ApiError<>();
		apiError.setStatus(HttpStatus.BAD_REQUEST.value());

		Exception exception = new Exception<>();
		exception.setHostName(getHostName());
		exception.setPath(request.getDescription(false).substring(4));
		exception.setCreateTime(new Date());
		exception.setMessage(message);
		
		apiError.setException(exception);
		
		return apiError;
	}
}
