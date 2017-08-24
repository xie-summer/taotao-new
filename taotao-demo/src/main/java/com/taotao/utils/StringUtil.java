/*
 * ====================================================================
 * 龙果学院： www.roncoo.com （微信公众号：RonCoo_com）
 * 超级教程系列：《微服务架构的分布式事务解决方案》视频教程
 * 讲师：吴水成（水到渠成），840765167@qq.com
 * 课程地址：http://www.roncoo.com/details/7ae3d7eddc4742f78b0548aa8bd9ccdb
 * ====================================================================
 */

package com.taotao.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @类功能说明： String字符串工具类.
 */
public final class StringUtil {

//	private static final Log LOG = Log.class.getLog(StringUtil.class);

    /**
     * 私有构造方法,将该工具类设为单例模式.
     */
    private StringUtil() {
    }

    /**
     * 函数功能说明 ： 判断字符串是否为空 . 修改者名字： 修改日期： 修改内容：
     *
     * @return boolean
     * @throws
     * @参数： @param str
     * @参数： @return
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    /**
     * 函数功能说明 ： 判断对象数组是否为空. 修改者名字： 修改日期： 修改内容：
     *
     * @return boolean
     * @throws
     * @参数： @param obj
     * @参数： @return
     */
    public static boolean isEmpty(Object[] obj) {
        return null == obj || 0 == obj.length;
    }

    /**
     * 函数功能说明 ： 判断对象是否为空. 修改者名字： 修改日期： 修改内容：
     *
     * @return boolean
     * @throws
     * @参数： @param obj
     * @参数： @return
     */
    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        }
        return !(obj instanceof Number) ? false : false;
    }

    /**
     * 函数功能说明 ： 判断集合是否为空. 修改者名字： 修改日期： 修改内容：
     *
     * @return boolean
     * @throws
     * @参数： @param obj
     * @参数： @return
     */
    public static boolean isEmpty(List<?> obj) {
        return null == obj || obj.isEmpty();
    }

    /**
     * 函数功能说明 ： 判断Map集合是否为空. 修改者名字： 修改日期： 修改内容：
     *
     * @return boolean
     * @throws
     * @参数： @param obj
     * @参数： @return
     */
    public static boolean isEmpty(Map<?, ?> obj) {
        return null == obj || obj.isEmpty();
    }

    /**
     * 函数功能说明 ： 获得文件名的后缀名. 修改者名字： 修改日期： 修改内容：
     *
     * @return String
     * @throws
     * @参数： @param fileName
     * @参数： @return
     */
    public static String getExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 获取去掉横线的长度为32的UUID串.
     *
     * @return uuid.
     * @author WuShuicheng.
     */
    public static String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取带横线的长度为36的UUID串.
     *
     * @return uuid.
     * @author WuShuicheng.
     */
    public static String get36UUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 验证一个字符串是否完全由纯数字组成的字符串，当字符串为空时也返回false.
     *
     * @param str 要判断的字符串 .
     * @return true or false .
     * @author WuShuicheng .
     */
    public static boolean isNumeric(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        } else {
            return str.matches("\\d*");
        }
    }

    /**
     * 计算采用utf-8编码方式时字符串所占字节数
     *
     * @param content
     * @return
     */
    public static int getByteSize(String content) {
        int size = 0;
        if (null != content) {
            try {
                // 汉字采用utf-8编码时占3个字节
                size = content.getBytes("utf-8").length;
            } catch (UnsupportedEncodingException e) {
//				LOG.error(e);
            }
        }
        return size;
    }

    /**
     * 函数功能说明 ： 截取字符串拼接in查询参数. 修改者名字： 修改日期： 修改内容：
     *
     * @return String
     * @throws
     * @参数： @param ids
     * @参数： @return
     */
    public static List<String> getInParam(String param) {
        boolean flag = param.contains(",");
        List<String> list = new ArrayList<String>();
        if (flag) {
            list = Arrays.asList(param.split(","));
        } else {
            list.add(param);
        }
        return list;
    }


    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }


    public static boolean isBlank(String value) {
        return null == value || value.length() == 0 || "".equals(value) || "".equals(value.trim());
    }

    /**
     * 获取固定长度的随机字符
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(Character.toString((char) (Math.random() * (127 - 32) + 32)));
        }
        return sb.toString();
    }

    /**
     * 截取大字符串为oracle存储的固定长度的字符串方法，可避免汉字被截为半个的情况，默认字符为UTF-8
     *
     * @param src    原始字符串
     * @param length 截取的字符串长度
     * @param max    最多可截取的段数
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String[] subString(String src, int length, int max) {
        String[] result = new String[max];

        try {
            char[] charArray = src.toCharArray();
            StringBuilder sb = new StringBuilder();
            int count = 0;
            int arrayCount = 0;
            for (int i = 0; i < charArray.length; i++) {
                char ch = charArray[i];
                int chLength = Character.toString(ch).getBytes("UTF-8").length;
                count += chLength;
                if (count > length) {
                    result[arrayCount] = sb.toString();
                    arrayCount++;
                    if (arrayCount > max - 1) break;
                    sb = new StringBuilder();
                    count = chLength;
                }
                sb.append(ch);
            }
            if (arrayCount < max)
                result[arrayCount] = sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncoding!", e);
        }

        return result;
    }

    /**
     * 返回源串中与目标串匹配位置之后的串
     */
    public static String after(String src, String dest) {
        int pos = src.indexOf(dest);
        if (pos == -1) {
            return src;
        }
        String last = src.substring(pos + 1);

        return last;
    }

    /**
     * 返回源串中与目标串匹配位置之前的串
     */
    public static String before(String src, String dest) {
        int pos = src.indexOf(dest);
        if (pos == -1) {
            return src;
        }
        String first = src.substring(0, pos);

        return first;
    }

    /**
     * 将第一个字符转成大写
     *
     * @param value
     * @return
     */
    public static String upperFirst(String value) {
        String first = value.substring(0, 1);
        String last = value.substring(1);
        return first.toUpperCase() + last;
    }

    /**
     * 将第一个字符转成小写
     *
     * @param value
     * @return
     */
    public static String lowerFirst(String value) {
        String first = value.substring(0, 1);
        String last = value.substring(1);
        return first.toLowerCase() + last;
    }

    /**
     * 对按分隔符分隔的字符串转换为Map，默认分隔符为“|”，字符串格式为：“key1=value1|key2=value2|key3=value3”
     *
     * @param value 输入字符串
     * @return
     */
    public static Map<String, String> toMap(String value) {
        return toMap(value, "|");
    }

    /**
     * 对按分隔符分隔的字符串转换为Map，默认分隔符为“|”，字符串格式为：“key1=value1|key2=value2|key3=value3”
     *
     * @param value          输入字符串
     * @param splitCharacter 分隔符
     * @return
     */
    public static Map<String, String> toMap(String value, String splitCharacter) {
        Map<String, String> result = new HashMap<String, String>();
        if (StringUtil.isNotBlank(value)) {
            String[] splitArray = value.split("\\" + splitCharacter); // property1=length
            for (String s : splitArray) {
                String[] split = s.split("=");
                result.put(split[0], String.valueOf(split[1]));
            }
        }
        return result;
    }

    /**
     * 将对象转成字符串，null转换成空串
     *
     * @param o
     * @return
     */
    public static String toString(Object o) {
        String symbol = "####################################.#######################################";
        if (o == null)
            return "";
        else if (o instanceof Date) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                    Locale.CHINA);
            return df.format(o);
        } else if (o instanceof Double || o instanceof BigDecimal
                || o instanceof Float) {
            DecimalFormat df = new DecimalFormat(symbol);
            return df.format(o);
        } else {
            return o.toString();
        }
    }

    public static Object toObject(String o, String type) {
        if (isBlank(o)) {
            return null;
        }

        if ("String".equalsIgnoreCase(type)) {
            return o;
        } else if ("Integer".equalsIgnoreCase(type)) {
            return Integer.valueOf(o);
        } else if ("BigInteger".equalsIgnoreCase(type)) {
            return new BigInteger(o);
        } else if ("Long".equalsIgnoreCase(type)) {
            return Long.valueOf(o);
        } else if ("BigDecimal".equalsIgnoreCase(type)) {
            return new BigDecimal(o);
        } else if ("Double".equalsIgnoreCase(type)) {
            return new Double(o);
        } else if ("Float".equalsIgnoreCase(type)) {
            return new Double(o);
        } else if ("Date".equalsIgnoreCase(type)) {
            return getDateFromString(o);
        } else if ("Boolean".equalsIgnoreCase(type)) {
            return Boolean.valueOf(o);
        } else
            return o;
    }

    private static Date getDateFromString(String o) {
        if (isBlank(o)) {
            return new Date(System.currentTimeMillis());
        } else {
            SimpleDateFormat df = null;
            if (o.length() == 10) {
                df = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            } else if (o.length() == 19 || o.length() == 21) {
                df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                        Locale.CHINA);
            } else {
                throw new RuntimeException("不支持的日期时间格式");
            }
            Date d = null;
            try {
                d = new Date(df.parse(o).getTime());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return d;
        }
    }

    /**
     * 将byte[]对象展现为{11 , 12 ,  -11 , ...}的形式
     *
     * @param 需要展现的byte[]对象
     * @return
     */
    public static String showByteArray(byte[] data) {
        if (null == data) {
            return null;
        }
        StringBuilder sb = new StringBuilder("{");
        for (byte b : data) {
            sb.append(b).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

}

