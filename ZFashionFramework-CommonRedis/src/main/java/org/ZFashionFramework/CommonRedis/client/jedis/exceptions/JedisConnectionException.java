package org.ZFashionFramework.CommonRedis.client.jedis.exceptions;


/**
 * @标题: REDISJAR包中文件未做改动
 * @编写人： 韩峥
 * @创建日期： 2012-8-2 下午4:30:49
 * @修改人：
 * @修改日期： 2012-8-2 下午4:30:49
 * @版本：
 * @描述: TODO
 */
public class JedisConnectionException extends JedisException {
	private static final long serialVersionUID = 3878126572474819403L;

	public JedisConnectionException(String message) {
		super(message);
	}

	public JedisConnectionException(Throwable cause) {
		super(cause);
	}

	public JedisConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
}
