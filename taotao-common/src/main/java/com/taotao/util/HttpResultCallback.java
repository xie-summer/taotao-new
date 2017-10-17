package com.taotao.util;

public interface HttpResultCallback {
	/**
	 * 结果处理
	 * @param result
	 */
	void processResult(HttpResult result);

	/**
	 * 错误处理
	 * @param t
	 */
	void processError(Throwable t);
}
