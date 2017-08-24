package com.taotao.util;

import com.taotao.support.TraceErrorException;

import java.io.File;

public class TmpFileUtil {
	private static final transient TLogger dbLogger = LoggerUtils.getLogger(HttpUtils.class);
	public static void deleteFile(File file){
		if(file.exists()){
			if(file.isFile()){
				file.delete();
			}else{
				throw new TraceErrorException("can't delete directory:" + file);
			}
		}else{
			dbLogger.warn("fileNotExists:" + file);
		}
	}

}
