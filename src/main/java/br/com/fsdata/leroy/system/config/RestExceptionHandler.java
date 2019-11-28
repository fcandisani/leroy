package br.com.fsdata.leroy.system.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler  {


	@ExceptionHandler({HttpClientErrorException.class})
	protected ResponseEntity<ResponseMessage> handleException(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(), HttpStatus.PRECONDITION_FAILED);
	}

}
