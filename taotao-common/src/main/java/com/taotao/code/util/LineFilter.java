package com.taotao.code.util;

import java.util.regex.Pattern;

public abstract class LineFilter {
	/**
	 * line 过滤
	 * @param line
	 * @return
	 */
	public abstract boolean accept(String line);
	public static LineFilter ACCEPT_ALL_FILTER = new LineFilter(){
		@Override
		public boolean accept(String line) {
			return true;
		}
	};
	public static class RegFilter extends LineFilter{
		private Pattern pattern = null;
		
		public RegFilter(String regexp){
			pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
		}
		@Override
		public boolean accept(String line) {
			return  pattern.matcher(line).find();
		}
		
	}
}
