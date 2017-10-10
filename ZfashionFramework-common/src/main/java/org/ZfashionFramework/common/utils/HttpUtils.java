package org.ZfashionFramework.common.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class HttpUtils {
	
	/**
	 * ajax返回值输出 String
	 * @param response
	 * @param data
	 */
	public static void ajaxOutPrintString(HttpServletResponse response, String data) {
		
		PrintWriter out = null;
		try {
			
			out = response.getWriter();
			out.write(data);
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			
			if (out != null) {
				
				out.flush();
				out.close();
			}
		}
	}

}
