package com.taotao.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 读取html的内容
 * @author zhushangjin
 *
 */
public class HtmlReader {   
   
    /**  
     * @param filePath 文件路径  
     * @return 获得html的全部内容  
     */  
	public static String readHtml(String filePath) {
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		FileInputStream fiStream = null;
		InputStreamReader isReader = null;
		try { // GB2312
			fiStream = new FileInputStream(filePath);
			isReader = new InputStreamReader(fiStream, "GB2312");
			br = new BufferedReader(isReader);
			String temp = null;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (isReader != null) {
					isReader.close();
				}
				if (fiStream !=null) {	
					fiStream.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return sb.toString();
	}


    /**  
     * @param filePath 文件路径  
     * @return 获得的html文本内容  
     */  
    public static String getTextFromHtml(String filePath) {
        //得到body标签中的内容   
        String str= readHtml(filePath);
        StringBuffer buff = new StringBuffer();
        int maxindex = str.length() - 1;   
        int begin = 0;   
        int end;               
        //截取>和<之间的内容   
        while((begin = str.indexOf('>',begin)) < maxindex){              
            end = str.indexOf('<',begin);   
            if(end - begin > 1){   
                buff.append(str.substring(++begin, end));                  
            }              
            begin = end+1;   
        }
        return buff.toString();   
    }
    /**
     * 获取素偶有 image的src的值
     * @param htmlStr
     * @return
     */
    public static List<String> getImgSrc(String htmlStr) {
        StringBuilder img = new StringBuilder();
        Pattern p_image;
        Matcher m_image;
        List<String> pics = new ArrayList<String>();
//       String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址  
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);  
        while (m_image.find()) {  
            img = img.append("," + m_image.group());
            // Matcher m =  
            // Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img); //匹配src  
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img.toString());
            while (m.find()) {  
                pics.add(m.group(1));  
            }  
        }  
        return pics;  
    }  
  
}  