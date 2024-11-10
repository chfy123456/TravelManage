package com.soft.common.util;

public class PathUtil {
	
	public static String getRootPath(String path) {
		String classpath = PathUtil.class.getResource("/").getPath().replaceFirst("/", "").replace("/BOOT-INF/classes","/");
		String filePath = classpath+"META-INF/resources/"+path;
		return filePath;
	}
	
	public static String getClassPath(String path) {
		String classpath =  PathUtil.class.getResource("/").getPath() + path;
		return classpath;
	}
}
