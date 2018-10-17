package com.rfxdevelop.utils.copy.object;

import java.lang.reflect.Proxy;

/**
 * copy from com.se.common.util
 */
public class ProxyUtil {

	/**
	 * 是否JDK代理类
	 * @param object
	 * @return
	 */
	public static boolean isJdkDynamicProxy(Object object) {
		if (Proxy.isProxyClass(object.getClass())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否Cglib代理类
	 * @param object
	 * @return
	 */
	private static boolean isCglibProxyProxy(Object object) {
		if (isCglibProxyClass(object.getClass())) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isCglibProxy(Object object) {
		return isCglibProxyClass(object.getClass());
	}
	public static boolean isCglibProxyClass(Class<?> clazz) {
		return clazz != null && isCglibProxyClassName(clazz.getName());
	}

	public static boolean isCglibProxyClassName(String className) {
		return className != null && className.contains("$$");
	}
}
