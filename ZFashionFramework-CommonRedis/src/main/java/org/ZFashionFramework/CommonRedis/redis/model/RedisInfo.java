package org.ZFashionFramework.CommonRedis.redis.model;

public class RedisInfo {
	String key;
	Integer selectdb;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Integer getSelectdb() {
		return selectdb;
	}
	public void setSelectdb(Integer selectdb) {
		this.selectdb = selectdb;
	}
 
}
