package org.ZFashionFramework.CommonRedis.client.util;

import java.io.UnsupportedEncodingException;

import org.ZFashionFramework.CommonRedis.client.jedis.Protocol;
import org.ZFashionFramework.CommonRedis.client.jedis.exceptions.JedisDataException;
import org.ZFashionFramework.CommonRedis.client.jedis.exceptions.JedisException;

/**
 * The only reason to have this is to be able to compatible with java 1.5 :(
 * 
 */
/**   
* @标题: REDISJAR包中文件未做改动
* @编写人： 韩峥
* @创建日期： 2012-8-2 下午4:30:49
* @修改人： 
* @修改日期： 2012-8-2 下午4:30:49
* @版本：    
* @描述: TODO
*/
public class SafeEncoder {
    public static byte[] encode(final String str) {
        try {
            if (str == null) {
                throw new JedisDataException("value sent to redis cannot be null");
            }
            return str.getBytes(Protocol.CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new JedisException(e);
        }
    }

    public static String encode(final byte[] data) {
        try {
            return new String(data, Protocol.CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new JedisException(e);
        }
    }
}
