package com.taotao.util;

import java.util.Map;

public interface TLogger {
	void warn(String msg);
	void error(String msg);
	void warn(String msg, Throwable e);
	void warn(Throwable e, int rows);
	void warn(String msg, Throwable e, int rows);
	void error(String msg, Throwable e);
	void error(String msg, Throwable e, int rows);
	void error(Throwable e, int rows);
	void warnMap(Map msgMap);
	void warnMap(String type, Map msgMap);
	void errorMap(Map msgMap);
	void errorMap(String type, Map msgMap);
	void warnWithType(String type, String msg, Throwable e);
	void warnWithType(String type, String msg, Throwable e, int rows);
	void warnWithType(String type, String msg);
	void errorWithType(String type, String msg);
	void errorWithType(String type, String msg, Throwable e);
	void errorWithType(String type, String msg, Throwable e, int rows);
}
