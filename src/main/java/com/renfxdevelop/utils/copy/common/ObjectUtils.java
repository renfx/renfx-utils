package com.renfxdevelop.utils.copy.common;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author
 */
public class ObjectUtils {

	public static boolean isNullOrEmptyString(Object o) {
		if(o == null)
			return true;
		if(o instanceof String) {
			String str = (String)o;
			if(str.length() == 0)
				return true;
		}
		return false;
	}
	public static boolean isNotNullOrEmptyString(Object o) {
		
		return !isNullOrEmptyString(o);
	}
	
	/**
	 * 可以用于判断 Map,Collection,String,Array是否为空
	 * @param o
	 * @return
	 */
	@SuppressWarnings("all")
    public static boolean isEmpty(Object o)  {
        if(o == null) return true;

        if(o instanceof String) {
            if(((String)o).length() == 0){
                return true;
            }
        } else if(o instanceof Collection) {
            if(((Collection)o).isEmpty()){
                return true;
            }
        } else if(o.getClass().isArray()) {
            if(Array.getLength(o) == 0){
                return true;
            }
        } else if(o instanceof Map) {
            if(((Map)o).isEmpty()){
                return true;
            }
        }else {
            return false;
        }

        return false;
    }

	/**
	 * 可以用于判断 Map,Collection,String,Array是否不为空
	 * @param c
	 * @return
	 */
	public static boolean isNotEmpty(Object c) throws IllegalArgumentException{
		return !isEmpty(c);
	}
	
	/**
	 * 判断是否是正确的手机号
	 * @param phone
	 * @return
	 */
	public static boolean isMobileNumber(String phone) {
		if(isNullOrEmptyString(phone)) return false;
		Pattern p = Pattern.compile("(^0?[1][34578][0-9]{9}$)");
		Matcher m = p.matcher(phone);
		return m.matches();
	}
	
	/**
	 * 判断是否是正确的数字,长度不能超过11位
	 * @param num
	 * @return
	 */
	public static boolean isNumber(String num) {
		if(isNullOrEmptyString(num)) return false;
		Pattern p = Pattern.compile("^-?[0-9]{1,11}$");
		Matcher m = p.matcher(num);
		return m.matches();
	}

	public static void main(String[] args) {
		System.out.println(isNumber("01"));
	}
}
