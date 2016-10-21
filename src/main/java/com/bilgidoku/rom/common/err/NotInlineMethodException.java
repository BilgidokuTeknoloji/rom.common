package com.bilgidoku.rom.common.err;

public class NotInlineMethodException extends Exception{
	private String href;
	public NotInlineMethodException(String href) {
		this.href=href;
	}
	
	

}
