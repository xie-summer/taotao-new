package com.taotao.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 根据文件路径、文件名、列名、数据生成csv文件<br>
 * 如果是数字，或数字中有‘.’的将会生成=“2.34” 这样格式的字符串<br>
 * 否则是原字符串
 * @author c_xiaoshiwei-001
 *
 */
public class CSVUtils {
	private static Logger logger = LoggerFactory.getLogger(CSVUtils.class);
	/**格式化数字**/
	private static String symbol = "####################################.#######################################";
	/**默认编码**/
	private static String defaultCharset = "GB2312";
	/**格式化表达式**/
	private static DecimalFormat df = new DecimalFormat(symbol);
	/**为数字或‘.’的正则表达式**/
	private static Pattern pattern = Pattern.compile("[0-9.]+");
	/**=“字符**/
	private static String startStr = "=\"";
	/**”,字符**/
	private static String endStr = "\",";
	/**,字符**/
	private static String comma = ",";
	
	/**
	 * 生成csv文件
	 * @param head 列名
	 * @param dataList 数据列表
	 * @param outPutPath 输出文件路径
	 * @param filename 输出文件名称
	 * @return
	 * @throws Exception
	 */
    public static File createCSVFile(Object[] head, List<Object []> dataList,
                                     String filePath, String fileName ) throws Exception {
    	return createCSVFile(head,null, dataList, filePath, fileName, null);
    }
    
	/**
	 * 生成csv文件
	 * @param headList 列名
	 * @param dataList 数据列表
	 * @param outPutPath 输出文件路径
	 * @param filename 输出文件名称
	 * @return
	 * @throws Exception
	 */
    public static File createCSVFile(List<String> headList, List<Object []> dataList,
                                     String filePath, String fileName ) throws Exception {
    	return createCSVFile(null,headList, dataList, filePath, fileName, null);
    }
    
	/**
	 * 生成csv文件
	 * @param head 列名
	 * @param dataList 数据列表
	 * @param outPutPath 输出文件路径
	 * @param filename 输出文件名称
	 * @param charset 编码格式
	 * @return
	 * @throws Exception
	 */
    public static File createCSVFile(Object[] head, List<String> headList, List<Object []> dataList,
                                     String filePath, String fileName , String charset) throws Exception {
    	if(StringUtils.isBlank(filePath) || StringUtils.isBlank(fileName) ){
    		throw new Exception("文件路径或文件名不能为空");
    	}
        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
        	StopWatch t = new StopWatch("createCSVFile");
        	t.start(" create BufferedWriter ");
            csvFile = new File(TextUtils.joinObject(filePath , File.separator , fileName , ".csv"));
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
            logger.debug("csvFile path:"+csvFile.getPath());
            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), StringUtils.isBlank(charset)?defaultCharset:charset), 1024);
            t.stop();
            t.start("writeRow");
            // 写入文件头部
            writeRow(headList,csvWtriter);
            writeRow(head, csvWtriter);
            // 写入文件内容
            if(CollectionUtils.isNotEmpty(dataList)){
                for (Object[] row : dataList) {
                    writeRow(row, csvWtriter);
                }
            }
            csvWtriter.flush();
            t.stop();
            logger.info(t.prettyPrint());
        } catch (Exception e) {
        	logger.error("CSVUtils.createCSVFile is error", e);
        	throw e;
        } finally {
           IOUtils.closeQuietly(csvWtriter);
        }
        return csvFile;
    }
    
    /**
     * 写一行数据方法
     * @param row
     * @param csvWriter
     * @throws IOException
     */
    private static void writeRow(List<String> rowList, BufferedWriter csvWriter) throws IOException {
    	if(CollectionUtils.isEmpty(rowList)){ return; }
        for (String row : rowList) {
            csvWriter.write(row);
        }
        csvWriter.newLine();
    }

    /**
     * 写一行数据方法
     * @param row
     * @param csvWriter
     * @throws IOException
     */
    private static void writeRow(Object[] row, BufferedWriter csvWriter) throws IOException {
    	if(row==null){ return; }
        for (Object data : row) {
            csvWriter.write(getStr(data));
        }
        csvWriter.newLine();
    }
    
    /**
     * 获取csv需要的格式的字符串
     * @param o
     * @return
     */
	private static String getStr(Object o) {
		if (o == null){
			return comma;
		}else if (o instanceof Date) {
			return TextUtils.joinObject(DateFormatUtils.parseDateToDefaultDateString((Date)o),comma);
		}else if (o instanceof Double ||
				o instanceof BigDecimal ||
				o instanceof Float) {
			return TextUtils.joinObject(startStr,df.format(o),endStr);
		}else if(o instanceof Integer ||
				o instanceof Long ||
				o instanceof BigInteger){
			return TextUtils.joinObject(startStr,o.toString(),endStr);
		}else {
			String temp = o.toString();
			if(pattern.matcher(temp).matches()){
				return TextUtils.joinObject(startStr,temp,endStr);
			}
			return TextUtils.joinObject(temp,comma);
		}
	}

}
