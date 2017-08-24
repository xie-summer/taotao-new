package com.taotao.util;

public interface HttpResultCallback {
	void processResult(HttpResult result);
	void processError(Throwable t);
}
