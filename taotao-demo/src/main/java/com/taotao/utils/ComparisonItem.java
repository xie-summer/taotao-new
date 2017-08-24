package com.taotao.utils;

/**
 * 支持形如下表数值比较的场合：
 * <table border="1">
 * <thead>
 * <tr>
 * <th>groupInfo</th>
 * <th>displayInfo</th>
 * <th>value1</th>
 * <th>value2</th>
 * <th>value3</th>
 * <th>value4</th>
 * <th>value5</th>
 * </tr>
 * </thead> <tbody>
 * <tr>
 * <td>可用于分组字段</td>
 * <td>页面显示字段</td>
 * <td>比较值1</td>
 * <td>比较值2</td>
 * <td>比较值3</td>
 * <td>比较值4</td>
 * <td>比较值5</td>
 * </tr>
 * </tbody>
 * </table>
 * 
 * @author zhouqiyou
 * 
 */
public class ComparisonItem {
	/** 可用于分组字段 */
	private String groupInfo;
	/** 页面显示字段 */
	private String displayInfo;
	/** 比较值1 */
	private String value1;
	/** 比较值2 */
	private String value2;
	/** 比较值3 */
	private String value3;
	/** 比较值4 */
	private String value4;
	/** 比较值5 */
	private String value5;

	public ComparisonItem() {
		// empty
	}

	public ComparisonItem(String groupInfo, String displayInfo) {
		this.groupInfo = groupInfo;
		this.displayInfo = displayInfo;
	}

	public String getGroupInfo() {
		return groupInfo;
	}

	public void setGroupInfo(String groupInfo) {
		this.groupInfo = groupInfo;
	}

	public String getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(String displayInfo) {
		this.displayInfo = displayInfo;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getValue4() {
		return value4;
	}

	public void setValue4(String value4) {
		this.value4 = value4;
	}

	public String getValue5() {
		return value5;
	}

	public void setValue5(String value5) {
		this.value5 = value5;
	}

}
