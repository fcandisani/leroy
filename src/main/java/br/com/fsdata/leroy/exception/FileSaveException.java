package br.com.fsdata.leroy.exception;

public class FileSaveException  extends Exception{
	
	private static final long serialVersionUID = 1L;

	public FileSaveException() {		
	}
	
	public FileSaveException(Exception e) {		
		super(e);
	}	
	
	public FileSaveException(String msg) {		
		super(msg);
	}
	
	public FileSaveException(String msg, Exception e) {		
		super(msg,e);
	}
}
