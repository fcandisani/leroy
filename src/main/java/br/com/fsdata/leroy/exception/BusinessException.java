package br.com.fsdata.leroy.exception;

public class BusinessException extends Exception{
	

	private static final long serialVersionUID = 1L;

	public BusinessException() {
		
	}
	
	public BusinessException(Exception e) {
		
		super(e);
	}
	
	
	public BusinessException(String msg) {
		
		super(msg);
	}
	
	public BusinessException(String msg, Exception e) {
		
		super(msg,e);
	}	

}
