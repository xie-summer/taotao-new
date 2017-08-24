package com.taotao.utils;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 根据path创建目录
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static File mkdirs(String path) throws IOException {
        if (StringUtils.isNotBlank(path)) {
            File files = new File(path);
            if (!files.exists()) {
                if (files.isDirectory()) {
                    files.mkdirs();
                } else {
                    files.createNewFile();
                }
            }
            return files;
        }
        return null;
    }

    /**
     * 根据path创建目录
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static File createNewFile(String filePath, String fileName) throws IOException {
        if (StringUtils.isNotBlank(fileName)) {
            File file = new File(filePath, fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            return file;
        }
        return null;
    }

/*	public static void writeFile(File file,Document document) {
		XMLWriter xmlWriter=null;
		try {
			if(file!=null){
				Writer fileWriter = new FileWriter(file);
				OutputFormat format = OutputFormat.createPrettyPrint();
				format.setEncoding("UTF-8");
				 xmlWriter = new XMLWriter(fileWriter,format);
				xmlWriter.write(document); // 写入文件中
				xmlWriter.close();
			}
		} catch (IOException e) {
			logger.error(e);
		}finally{
			if(xmlWriter!=null){
				try {
					xmlWriter.close();
				} catch (Exception e2) {
					logger.error("关闭xmlWriter出错"+e2);
				}
			}
		}
	}*/


    public static void moveFiles(String srcpath, String destpath) {
        File srcFile = new File(srcpath);

        if (!srcFile.exists()) {
            logger.info("创建目录:{}", srcpath);
            srcFile.mkdirs();
        }
        File dest = new File(destpath);
        if (!dest.exists()) {
            logger.info("创建目录:{}", destpath);
            dest.mkdirs();
        }

        //遍历源文件下的所有文件
        File[] srcfiles = srcFile.listFiles(new FilenameFilterUtil(".xml"));
        if (srcfiles != null && srcfiles.length > 0) {
            for (File file : srcfiles) {
                if (file.isFile() && file.canWrite() && StringUtils.endsWith(XmlUtil.readFileToString(file), "</message>")) {
                    logger.info("移动目标目录:{}", destpath);
                    logger.info("移动目标文件名:{}", file.getName());
                    File destFile = new File(destpath, file.getName());
                    file.renameTo(destFile);
                }
            }
        }

    }

    /**
     * @param path
     * @param str
     * @param fileName
     */
    public static void writerToFile(String path, Document document, String fileName) {
        XMLWriter xmlWriter = null;
        File file = new File(path, fileName);
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        try {
            xmlWriter = new XMLWriter(new FileWriter(file), format);
            if (document != null) {
                xmlWriter.write(document);
            }

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (xmlWriter != null) {
                try {
                    xmlWriter.close();
                } catch (Exception e2) {
                    logger.info(e2.getMessage());
                }
            }
        }

    }
	
/*	public static void main(String[] args) {
		String srcPath="C:\\cpic\\cxadm\\fileadapter\\files\\recvd\\autochannel";
		String destpath="C:\\cpic\\cxadm\\fileadapter\\files\\recvd\\temp_P11";
		FileUtil.moveFiles(srcPath, destpath);
	}*/

};
