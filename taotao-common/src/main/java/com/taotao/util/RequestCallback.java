package com.taotao.util;

import java.io.InputStream;
import java.util.Map;

public interface RequestCallback {
	/**
	 * 结果处理
	 * @param stream
	 * @param resHeader
	 * @return
	 */
	public abstract boolean processResult(InputStream stream, Map<String, String> resHeader);
}
