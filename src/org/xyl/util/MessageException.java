package org.xyl.util;

@SuppressWarnings("serial")
public class MessageException extends RuntimeException{
	
	public MessageException(){
		super();
	}

	public MessageException(String message,Throwable cause){
		super(message,cause);
	}
	
	public MessageException(String message){
		super(message);
	}
	
	public MessageException(Throwable cause){
		super(cause);
	}
	
}
