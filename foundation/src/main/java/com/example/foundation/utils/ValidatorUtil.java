/**
 *  www.yupaopao.cn 2014-2017©All Rights Reserved
 */
package com.example.foundation.utils;

import java.util.regex.Pattern;

/**
 * <p>
 * 数据格式交易工具类(手机号校验)
 * </p>
 */
public class ValidatorUtil {

	public static final String REGEX_MOBILE = "^1[34578]{1}\\d{9}$";

	/**
	 * <p>
	 * 校验手机号
	 * </p>
	 * 
	 * @param mobile
	 * @return 校验通过返回true，否则返回false
	 */
	public static boolean isMobile(String mobile) {
		return Pattern.matches(REGEX_MOBILE, mobile);
	}

}
