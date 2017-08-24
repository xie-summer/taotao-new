
package com.taotao.utils;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@SuppressWarnings("rawtypes")
public class XmlUtil {
	
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    
    public final static String ENCODING_GBK = "GBK";
    
    public final static String ENCODING_UTF8 = "utf-8";

    /**
     * 展现String
     * @throws IOException
     */
    public static void showXML(String strXML){
    	// Modified By Yuyuan 2014/05/07
    	// 修改Writer关闭方式,不改变原有Exception处理方式
    	XMLWriter writer = null;
        try {
            Document document = DocumentHelper.parseText(strXML);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding(ENCODING_GBK);
            format.setTrimText(false);
            writer = new XMLWriter(System.out, format);
            writer.write(document);
        } catch (Exception ex) {
           logger.error(ex.getMessage());
        }finally{
        	closeQuietly(writer);
        }
    }

    /**
     * 展现文本格式的xml文件而不是一长串
     */
    public static String showXMLString(String strXML, String encoding) {
        XMLWriter writer = null;
        StringWriter stringWriter = null;
        String returnStr = "";
        try {
            Document document = DocumentHelper.parseText(strXML);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setExpandEmptyElements(true);
            format.setEncoding(encoding);
            format.setTrimText(false);
            stringWriter = new StringWriter();
            writer = new XMLWriter(stringWriter, format);
            writer.write(document);
            if (stringWriter != null) {
                returnStr = stringWriter.toString();
            }

        } catch (Exception ex) {
           logger.error(ex.getMessage());
        } finally {
        	IOUtils.closeQuietly(stringWriter);
        	closeQuietly(writer);
        }
        return returnStr;
    }
    
    /**
     * 展现文本格式的xml文件而不是一长串
     */
    public static String showXMLString(String strXML, String encoding, boolean suppressDeclaration) {
        XMLWriter writer = null;
        StringWriter stringWriter = null;
        String returnStr = "";
        try {
            Document document = DocumentHelper.parseText(strXML);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setExpandEmptyElements(true);
            format.setEncoding(encoding);
            format.setTrimText(false);
            format.setSuppressDeclaration(suppressDeclaration);//是否生成头信息
            stringWriter = new StringWriter();
            writer = new XMLWriter(stringWriter, format);
            writer.write(document);
            if (stringWriter != null) {
                returnStr = stringWriter.toString();
            }

        } catch (Exception ex) {
           logger.error(ex.getMessage());
        } finally {
        	IOUtils.closeQuietly(stringWriter);
        	closeQuietly(writer);
        }
        return returnStr;
    }
    public static void closeQuietly(XMLWriter writer){
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }
    
    /**
     * 展现一长串的xml文件而不是文本格式
     */
    public static String showXMLCompact(String strXML) {
        XMLWriter writer = null;
        StringWriter stringWriter = null;
        String returnStr = "";
        try {
            Document document = DocumentHelper.parseText(strXML);
            OutputFormat format = OutputFormat.createCompactFormat();
            format.setEncoding(ENCODING_GBK);
            format.setTrimText(true);
            stringWriter = new StringWriter();
            writer = new XMLWriter(stringWriter, format);
            writer.write(document);
            if (stringWriter != null) {
                returnStr = stringWriter.toString();
            }

        } catch (Exception ex) {
          logger.error(ex.getMessage());
        } finally {
        	IOUtils.closeQuietly(stringWriter);
        	closeQuietly(writer);
        }
        return returnStr;
    }


    /**
     * 用xpath取得某一节点的值
     */
    public static String getNodeValue(Node node, String xpath) {
        Node node2 = node.selectSingleNode(xpath);
        if (node2 != null) {
            String value = node2.getText();
            return value;
        }
        return null;
    }

    /**
     * 用xpath得到某一节点的list，用于得到列表
     */

	public static List getNodeList(Node node, String xpath) {
        List nodeList = node.selectNodes(xpath);
        return nodeList;
    }

    /**
     * 将xml写入指定的文件中
     * 
     * @param xml
     * @param path
     */
    public static void generateDoc(String xml, String path, String charset) {
    	// Modified By Yuyuan 2014/05/07
    	// 修改BufferedWriter关闭方式,不改变原有Exception处理方式
    	BufferedWriter writer = null;
        File file = new File(path);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
        	writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
            writer.write(showXMLString(xml,ENCODING_UTF8));
        } catch (IOException e) {
           logger.error(e.getMessage());
        }finally{
        	IOUtils.closeQuietly(writer);
        }
    }
    /**
	 * util.Date转换为xml的Date类型
	 * by shengyl
	 * @param date
	 * @return
	 */
	public static XMLGregorianCalendar parseXMLDate(Date date){
		if(date==null){
			return null;
		}
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		XMLGregorianCalendar damageDate = null;
		try {
			damageDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(
					cal);
		} catch (DatatypeConfigurationException e1) {
			logger.error(e1.getMessage());
		}
		return damageDate;
	}
	
	/**
	 * 
	 * @param o 要转换的对象
	 * @return	o的xml表达方式
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked" })
	public static String getXmlPresent(Object o )
	{
		StringBuffer sb = new StringBuffer();
		Class c = o.getClass();
		Field[] fields = c.getDeclaredFields();
		String temp = "";
		for (int i=0; i<fields.length; i++) {
			Field f = fields[i];
			String fName = f.getName();
			if (!fName.startsWith("_") && 
					!java.lang.reflect.Modifier.isStatic(f.getModifiers())  && 
					(f.getType() == String.class  ||
					f.getType() == Date.class ||
					f.getType() == Calendar.class ||
					f.getType() == Integer.class ||
					f.getType() == Float.class ||
					f.getType() == Double.class ||
					f.getType() == Character.class ||
					f.getType() == Boolean.class ||
					f.getType() == Short.class  ||
					f.getType() == Long.class ||
					f.getType() == Byte.class ||
					f.getType() == java.math.BigDecimal.class ||
					f.getType() == java.math.BigInteger.class ||
					f.getType() == int.class ||
					f.getType() == float.class ||
					f.getType() == double.class ||
					f.getType() == char.class ||
					f.getType() ==boolean.class ||
					f.getType() == short.class  ||
					f.getType() == long.class ||
					f.getType() == byte.class)) {
				sb.append("<" +f.getName() + ">");
				String getMethodName = "get" + f.getName().substring(0,1).toUpperCase() +
				f.getName().substring(1);
				Method getMethod = null;
				Object value = null;
				try {
					getMethod = c.getMethod(getMethodName,new Class[] {});
					value = getMethod.invoke(o,new Object[] {});
					if (f.getType() == Calendar.class && value!=null) {
						GregorianCalendar calendar = (GregorianCalendar)value;
							temp = "";
							int year = calendar.get(Calendar.YEAR);
							int month = calendar.get(Calendar.MONTH)+1;
							int date = calendar.get(Calendar.DATE);
							int hour = calendar.get(Calendar.HOUR_OF_DAY);
							int minute = calendar.get(Calendar.MINUTE);
							int second = calendar.get(Calendar.SECOND);
							temp += year + "-" + padding(month) + "-" + padding(date) + " " +
							padding(hour) + ":" + padding(minute) + ":" + padding(second);
							sb.append(temp);
					}
					else {
						if(value!=null){
							sb.append(value);
						}else{
							sb.append("");
						}
						
					}
				}
				catch (Exception e) {
					logger.error(e.getMessage());
				}
				sb.append("</" +f.getName() + ">");
			}
		}
		return sb.toString();
	}
	

	public static String padding(int num) {
		return padding(num,2,'0');
	}
	public static String padding(int num, int len, char placeholder) {
		StringBuffer sb = new StringBuffer();
		
		String s = ""+num;
		int len1 = s.length();
		if (len1>len) {
			return s;
		}
		for (int i=0; i<len-len1; i++) {
			sb.append(placeholder);
		}
		sb.append(s);
		
		return sb.toString();
	}
	
	/**
	 * 读取磁盘的文件组成字符串
	 * @param fileName
	 * @return
	 */
	public static String readFileToString(File file) {
    	// Modified By Yuyuan 2014/05/07
    	// 修改BufferedReader关闭方式,不改变原有Exception处理方式
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer();
		try {
			//读取文件流
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String strMessage = null;
			while ((strMessage = reader.readLine()) != null) {
				buffer.append(strMessage);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally{
			IOUtils.closeQuietly(reader);
		}
		return buffer.toString();
	}
	
	/**
	 * 过滤XML特殊字符
	 * @param xmlstr
	 * @return
	 */
	public static String filterSpicialChar(String xmlstr){
		return xmlstr.replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "");
	}
}
