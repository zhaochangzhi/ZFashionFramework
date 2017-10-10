package org.ZfashionFramework.common.enums;

public enum DicTypeEnum {
	
	    
	USERTYPE("usertype", "U"),
	BRAND("brand", "B");
	
	String type;	//字典类型
	String prex;	//字典前缀
	
	DicTypeEnum(String type, String prex) {
		
		this.type = type;
		this.prex = prex;
	}
	
	public static String getPrex(String type) {
		
        for (DicTypeEnum dic : DicTypeEnum.values()) {
        	
            if (dic.getType().equals(type)) {
            	
                return dic.prex;
            }
        }
        return null;
    }
	
	public String getType() {
		
		return type; 
	}
	
	public void setType(String type) {
		
		this.type = type;
	}
	
	public String getPrex() {
		
		return prex;
	}
	
	public void setPrex(String prex) {
		
		this.prex = prex;
	}
}
