package org.ZFashionFramework.CommonRedis.redis.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.ZFashionFramework.CommonRedis.client.jedis.ShardedJedis;

public class RedisUtils {
	private static final String STRINGTYPE = "java.lang.String";

	/**
	 * Redis结果转对象
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object rs2Object(Object objPram) {
		byte[] bytes = (byte[]) objPram;
		if (bytes == null || bytes.length == 0)
			return null;
		ObjectInputStream inputStream = null;
		try {

			inputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
			Object obj = inputStream.readObject();

			return (Object) obj;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 字节转化为对象
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object byte2Object(byte[] bytes) {
		if (bytes == null || bytes.length == 0)
			return null;
		ObjectInputStream inputStream = null;
		try {

			inputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
			Object obj = inputStream.readObject();

			return (Object) obj;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 对象转化为字节
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] object2Bytes(Object value) {
		byte[] by = null;
		if (value == null)
			return null;

		ByteArrayOutputStream arrayOutputStream = null;
		ObjectOutputStream outputStream = null;
		try {
			arrayOutputStream = new ByteArrayOutputStream();
			outputStream = new ObjectOutputStream(arrayOutputStream);

			outputStream.writeObject(value);
			by = arrayOutputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (arrayOutputStream != null) {
					arrayOutputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return by;
	}

	/**
	 * 设置是否设置过期超时
	 * 
	 * @param bytes
	 * @return
	 */
	public static void setExpire(ShardedJedis jedis, Object key,
			String classType, int outTime) {
		if (STRINGTYPE.equals(classType)) {
			if (outTime != 0) {
				jedis.expire((String) key, outTime);// 设置过期时间
			}
		} else {
			jedis.expire(object2Bytes(key), outTime);// 设置过期时间
		}
	}
}
