package com.thefirstlineofcode.crystal.framework.error;

public enum GeneralError {
	@ErrorCode(value = Code.INTERNAL_SERVER_ERROR, description = "系统错误。例如：解析请求参数失败，数据库访问失败等。")
	INTERNAL_SERVER_ERROR;
	
	public interface Code {
		public static final int INTERNAL_SERVER_ERROR = 990001;
	}
}
