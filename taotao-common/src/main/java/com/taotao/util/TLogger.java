package com.taotao.util;

import java.util.Map;

public interface TLogger {
	/**
	 *
	 * @param msg
	 */
	void warn(String msg);

	/**
	 *
	 * @param msg
	 */
	void error(String msg);

	/**
	 *
	 * @param msg
	 * @param e
	 */
	void warn(String msg, Throwable e);

	/**
	 *
	 * @param e
	 * @param rows
	 */
	void warn(Throwable e, int rows);

	/**
	 *
	 * @param msg
	 * @param e
	 * @param rows
	 */
	void warn(String msg, Throwable e, int rows);

	/**
	 *
	 * @param msg
	 * @param e
	 */
	void error(String msg, Throwable e);

	/**
	 *
	 * @param msg
	 * @param e
	 * @param rows
	 */
	void error(String msg, Throwable e, int rows);

	/**
	 *
	 * @param e
	 * @param rows
	 */
	void error(Throwable e, int rows);

	/**
	 *
	 * @param msgMap
	 */
	void warnMap(Map msgMap);

	/**
	 *
	 * @param type
	 * @param msgMap
	 */
	void warnMap(String type, Map msgMap);

	/**
	 *
	 * @param msgMap
	 */
	void errorMap(Map msgMap);

	/**
	 *
	 * @param type
	 * @param msgMap
	 */
	void errorMap(String type, Map msgMap);

	/**
	 *
	 * @param type
	 * @param msg
	 * @param e
	 */
	void warnWithType(String type, String msg, Throwable e);

	/**
	 * 警告信息,类型
	 * @param type
	 * @param msg
	 * @param e
	 * @param rows
	 */
	void warnWithType(String type, String msg, Throwable e, int rows);

	/**
	 * 警告信息,类型
	 * @param type
	 * @param msg
	 */
	void warnWithType(String type, String msg);

	/**
	 * 错误信息,类型
	 * @param type
	 * @param msg
	 */
	void errorWithType(String type, String msg);

	/**
	 * 错误信息,类型并输出异常堆栈
	 * @param type
	 * @param msg
	 * @param e
	 */
	void errorWithType(String type, String msg, Throwable e);

	/**
	 * 错误信息,类型并指定行数,输出异常堆栈
	 * @param type
	 * @param msg
	 * @param e
	 * @param rows
	 */
	void errorWithType(String type, String msg, Throwable e, int rows);
}
