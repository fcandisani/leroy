package br.com.fsdata.leroy.exception;

public class FileProcessException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public FileProcessException() {

	}
	
	public FileProcessException(Exception e) {		
		super(e);
	}
	
	public FileProcessException(String msg) {
		super(msg);
	}
	
	public FileProcessException(String msg, Exception e) {
		super(msg,e);
	}
}
