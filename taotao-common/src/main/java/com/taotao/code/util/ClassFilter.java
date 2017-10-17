package com.taotao.code.util;

public interface ClassFilter {
	/**
	 * 过滤的clazz
	 * @param clazz
	 * @return
	 */
	boolean accept(Class clazz);
	public ClassFilter ACCEPT_ALL = new ClassFilter(){
		@Override
		public boolean accept(Class clazz) {
			return true;
		}
		
	};
}
