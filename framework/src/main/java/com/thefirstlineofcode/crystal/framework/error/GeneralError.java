package com.thefirstlineofcode.crystal.framework.error;

public enum GeneralError {
	@ErrorCode(value = Code.INTERNAL_SERVER_ERROR, description = "System error. For example: Failed to parsing parameters, Can't access database, ....")
	INTERNAL_SERVER_ERROR;
	
	public interface Code {
		public static final int INTERNAL_SERVER_ERROR = 990001;
	}
}
