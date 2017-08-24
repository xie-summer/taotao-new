package com.taotao.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 日期比较
 *
 * @author c_xiaoshiwei-001
 */
public class ComparatorUtils {
    private static Logger logger = LoggerFactory.getLogger(ComparatorUtils.class);

    /**
     * 比较俩个字符串，如果是null 将排最前面如果是“null” 排最后面
     */
    public static class ComparatorStr implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if (StringUtils.isBlank(o1)) {
                return -1;
            }
            if (StringUtils.isBlank(o2)) {
                return 1;
            }
            return o1.compareTo(o2);
        }
    }

    /**
     * 比较俩个日期
     */
    public static class ComparatorDate implements Comparator<Date> {
        @Override
        public int compare(Date o1, Date o2) {
            if (o1 == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            return o1.compareTo(o2);
        }
    }

    /**
     * 比较字符串list
     *
     * @param list1
     * @param list2
     * @return
     */
    public static boolean equalStrList(List<String> list1, List<String> list2) {
        if (CollectionUtils.isEmpty(list1) && CollectionUtils.isEmpty(list2)) {
            return true;
        }
        if (list1.size() != list2.size()) {
            return false;
        }
        Collections.sort(list1, new ComparatorStr());
        Collections.sort(list2, new ComparatorStr());
        logger.debug("list1:" + list1.toString());
        logger.debug("list2:" + list2.toString());
        return list1.toString().equals(list2.toString());
    }

    /**
     * 比较字符串list
     *
     * @param list1
     * @param list2
     * @return
     */
    public static boolean equalDateList(List<Date> list1, List<Date> list2) {
        if (CollectionUtils.isEmpty(list1) && CollectionUtils.isEmpty(list2)) {
            return true;
        }
        if (list1.size() != list2.size()) {
            return false;
        }
        Collections.sort(list1, new ComparatorDate());
        Collections.sort(list2, new ComparatorDate());
        logger.info("list1:" + list1.toString());
        logger.info("list2:" + list2.toString());
        return list1.toString().equals(list2.toString());
    }

    public static void main(String[] args) {

    }

}
