package com.thefirstlineofcode.crystal.framework.error;

import java.lang.reflect.Field;

public class ErrorWrappedException extends RuntimeException implements IError {
	private static final long serialVersionUID = -8323561850581280796L;
	
	protected Enum<?> error;
	protected Object data;

	public ErrorWrappedException(Enum<?> error) {
		this.error = error;
	}
	
	public ErrorWrappedException(Enum<?> error, Throwable t) {
		super(t);
		this.error = error;
	}
	
	public ErrorWrappedException(Enum<?> error, String message) {
		super(message);
		this.error = error;
	}
	
	public ErrorWrappedException(Enum<?> error, String message, Throwable t) {
		super(message, t);
		this.error = error;
	}
	
	public ErrorWrappedException(Enum<?> error, Object data) {
		this.error = error;
		this.data = data;
	}
	
	public ErrorWrappedException(Enum<?> error, Object data, String message) {
		super(message);
		this.error = error;
		this.data = data;
	}
	
	public ErrorWrappedException(Enum<?> error, Object data, String message, Throwable t) {
		super(message, t);
		this.error = error;
		this.data = data;
	}
	
	@Override
	public int getErrorCode() {
		Field errorField = null;
		try {
			errorField = error.getClass().getField(error.name());
		} catch (Exception e) {
			throw new IllegalArgumentException("Can't read annotated field.", e);
		}
		
		ErrorCode errorCode = errorField.getAnnotation(ErrorCode.class);
		
		if (errorCode == null) {
			throw new IllegalArgumentException(String.format("%s.%s should to be annotated by @ErrorCode.", error.getClass().getName(), error));
		}
		
		return errorCode.value();
	}
	
	public Enum<?> getError() {
		return error;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public Object getData() {
		return data;
	}
}
