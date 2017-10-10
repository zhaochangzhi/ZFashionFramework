package org.ZFashionFramework.CommonRedis.redis.utils;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

public class DBTools {
	/**
	 * 切割数组
	 * 
	 * @param ary
	 * @param subSize
	 * @return
	 */
	public static Object[] splitAry(String[] ary, int subSize) {
		int count = ary.length % subSize == 0 ? ary.length / subSize
				: ary.length / subSize + 1;

		List<List<String>> subAryList = new ArrayList<List<String>>();

		for (int i = 0; i < count; i++) {
			int index = i * subSize;

			List<String> list = new ArrayList<String>();
			int j = 0;
			while (j < subSize && index < ary.length) {
				list.add(ary[index++]);
				j++;
			}

			subAryList.add(list);
		}

		Object[] subAry = new Object[subAryList.size()];

		for (int i = 0; i < subAryList.size(); i++) {
			List<String> subList = subAryList.get(i);

			String[] subAryItem = new String[subList.size()];
			for (int j = 0; j < subList.size(); j++) {
				subAryItem[j] = subList.get(j);
			}

			subAry[i] = subAryItem;
		}

		return subAry;
	}
	public static String  getXmlText( Node object){
		    if(object==null){
		    	return null;
		    }
		    if(object.getTextContent()!=null &&!"".equals(object.getTextContent())){
		    	return object.getTextContent();
		    }
		    if(object.getAttributes().getNamedItem("value")!=null &&!"".equals(object.getAttributes().getNamedItem("value"))){
		    	return object.getAttributes().getNamedItem("value").getNodeValue();
		    }
		return "";
		
	}
}
