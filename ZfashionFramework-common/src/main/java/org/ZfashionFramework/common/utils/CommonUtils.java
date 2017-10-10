package org.ZfashionFramework.common.utils;

import org.ZfashionFramework.common.enums.DicTypeEnum;

public class CommonUtils {

	/**
	 * 创建补0字符串
	 * @param valve		值
	 * @param length	长度
	 * @return
	 */
	public static String coverZero(int valve, int length) {

		return String.format("%0" + length + "d", valve);
	}

	/**
	 * 生成 字典码
	 * @param code	已存在字典码
	 * @param type	字典类型
	 * @return
	 */
	public static String generateDicCode(String code, String type) {
		
		String prex = DicTypeEnum.getPrex(type);
		Integer value = Integer.parseInt(code.substring(1, code.length()));
		return prex + coverZero(value + 1, code.length() - 1);
	}
	
	public static void main(String[] args) {
		String code = "U003";
		System.out.println(generateDicCode(code, "usertype"));
	}
	
}
