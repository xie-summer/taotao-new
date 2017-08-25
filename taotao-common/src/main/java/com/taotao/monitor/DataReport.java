package com.taotao.monitor;

import java.util.List;
import java.util.Map;

public interface DataReport {
	List<Map<String, String>> getReportData();
	ReportType getReptype();
}
