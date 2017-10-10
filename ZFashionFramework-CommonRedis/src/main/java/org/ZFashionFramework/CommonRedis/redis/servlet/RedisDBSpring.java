package org.ZFashionFramework.CommonRedis.redis.servlet;

import org.ZFashionFramework.CommonRedis.redis.readxml.ReadRedis;




public class RedisDBSpring {
	public RedisDBSpring(String xml) {
		ReadRedis.getInstance(xml);
	}

}
