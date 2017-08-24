package com.taotao.utils;

import java.io.File;
import java.io.FilenameFilter;

/***
 * 根据文件名过滤文件
 * @author c_zhoukai-001
 *
 */
public class FilenameFilterUtil implements FilenameFilter {

	private String type;
	

	public FilenameFilterUtil(String type) {
		this.type = type;
	}

	@Override
	public boolean accept(File dir, String name) {
		return name.toLowerCase().endsWith(type);
	}

}
