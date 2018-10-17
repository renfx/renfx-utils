package com.rfxdevelop.utils.copy.system;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * 系统通用工具类
 * copy from com.se.common.util
 * @author zhihong.pzh
 * 
 */
public class SystemUtil {

	public static String OS = System.getProperty("os.name").toLowerCase();

	public final static String ENVIRONMENT = "ENVIRONMENT";

	public final static String ENVIRONMENT_PRE = "pre";

	public final static String ENVIRONMENT_GRAY = "gray";

	/**
	 * 判断当前操作系统是否是windows
	 * 
	 * @return
	 */
	public static boolean isWindows() {
		return (OS.indexOf("win") != -1);
	}


	public static String getEnvironment() {
		String environment = System.getenv(ENVIRONMENT);

		if (environment == null) {
			environment = System.getenv(ENVIRONMENT.toLowerCase());
		}

		return environment;
	}

	public static boolean isPreEnvironment() {
		String environment = getEnvironment();

		if (StringUtils.isNotBlank(environment) && (environment.indexOf(ENVIRONMENT_PRE) != -1 || environment.indexOf(ENVIRONMENT_PRE.toUpperCase()) != -1)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isGrayEnvironment() {
		String environment = getEnvironment();

		if (StringUtils.isNotBlank(environment) && (environment.indexOf(ENVIRONMENT_GRAY) != -1 || environment.indexOf(ENVIRONMENT_GRAY.toUpperCase()) != -1)) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println("Environment:"+getEnvironment());
		System.out.println("isPreEnvironment:"+isPreEnvironment());
		System.out.println("isGrayEnvironment:"+isGrayEnvironment());
	}
}