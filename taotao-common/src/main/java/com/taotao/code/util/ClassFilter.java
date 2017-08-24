package com.taotao.code.util;

public interface ClassFilter {
	boolean accept(Class clazz);
	public ClassFilter ACCEPT_ALL = new ClassFilter(){
		@Override
		public boolean accept(Class clazz) {
			return true;
		}
		
	};
}
