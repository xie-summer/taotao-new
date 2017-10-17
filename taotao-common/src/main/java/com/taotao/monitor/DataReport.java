package com.taotao.monitor;

import java.util.List;
import java.util.Map;

public interface DataReport {
	/**
	 * 获取reportData数据,返回List集合Map
	 * @return
	 */
	List<Map<String, String>> getReportData();

	/**
	 *
	 * 获取report类型
	 * @return
	 */
	ReportType getReptype();
}
