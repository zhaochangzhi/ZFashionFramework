package org.ZFashionFramework.CommonRedis.redis.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ZFashionFramework.CommonRedis.client.jedis.ShardedJedis;
import org.ZFashionFramework.CommonRedis.redis.utils.RedisMessage;
import org.ZFashionFramework.CommonRedis.redis.utils.RedisUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * REDIS Hash处理类 强行不支持使用以下命令 HINCRBY(只支持域为64为数字型)；HINCRBYFLOAT(只支持域为64为数字型)
 * 
 * @author hanzheng
 * 
 */
public class JRedisHashDaoIMP extends BaseRedisDao implements IJRedisHashDao{

	private static final Log logger = LogFactory.getLog(JRedisHashDaoIMP.class);
	public JRedisHashDaoIMP(String poolName ){
		super.setPoolName(poolName);
	}
	public JRedisHashDaoIMP(){
	
	}
	/**
	 * HASH存储 域和对象 REDIS(HSET)
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @param classType
	 * @return
	 */
	public boolean setHash(Object key, Object field, Object value, Class<?> classType) {
		return setHash(key, field, value, 0, classType);
	}

	/**
	 * HASH存储 域和对象 带超时（秒） REDIS(HSET)
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @param outTime
	 * @param classType
	 * @return
	 */
	public boolean setHash(Object key, Object field, Object value, int outTime, Class<?> classType) {
	 String types=classType.getName();
		boolean flag = false;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return flag;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {
			if (STRINGTYPE.equals(types)) {
				jedis.hset((String) key, (String) field, (String) value);
				
			} else {
				jedis.hset(RedisUtils.object2Bytes(key), RedisUtils.object2Bytes(field),
						RedisUtils.object2Bytes(value));
				 
			}
			RedisUtils.setExpire(jedis, key, types, outTime);
			logger.debug(RedisMessage.REDISMESSAGE_001 + "setHash");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}

	/**
	 * 批量执行 HASH存储 域和对象 对象长度应小于1000 REDIS(HSET)
	 * 
	 * @param keys
	 * @param fields
	 * @param values
	 * @param classType
	 * @return
	 */
	public boolean setHashs(Object[] keys, Object[] fields, Object[] values, Class<?> classType) {
		return setHashs(keys, fields, values, 0, classType);
	}

	/**
	 * 批量执行 HASH存储 域和对象 对象长度应小于1000 REDIS(HSET)
	 * 
	 * @param keys
	 * @param fields
	 * @param values
	 * @param outTime
	 * @param classType
	 * @return
	 */
	public boolean setHashs(Object[] keys, Object[] fields, Object[] values, int outTime,
			Class<?> classType) {
		 String types=classType.getName();
		boolean flag = false;
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {
			int i = 0;
			for (Object key : keys) {
				if (STRINGTYPE.equals(types)) {
					jedis.hset((String) key, (String) fields[i], (String) values[i]);
					
				} else {
					jedis.hset(RedisUtils.object2Bytes(key), RedisUtils.object2Bytes(fields[i]),
							RedisUtils.object2Bytes(values[i]));
					 
				}
				RedisUtils.setExpire(jedis, key, types, outTime);
				i = i + 1;
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "setHashs");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}

	/**
	 * 删除方法 REDIS(HDEL)
	 * 
	 * @param key
	 * @param field
	 * @param classType
	 * @return
	 */
	public boolean delHashKey(Object key, Object field, Class<?> classType) {
		 String types=classType.getName();
		boolean flag = false;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return flag;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {
			if (STRINGTYPE.equals(types)) {
				jedis.hdel((String) key, (String) field);
			} else {
				jedis.hdel(RedisUtils.object2Bytes(key), RedisUtils.object2Bytes(field));
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "delHashKey");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}

	/**
	 * 查看 是否存在HASH 的值 REDIS(HEXISTS)
	 * 实现读写分离
	 * @param key
	 * @param field
	 * @param classType
	 * @return
	 */
	public boolean exisHash(Object key, Object field, Class<?> classType) {
		 String types=classType.getName();
	 
		boolean flag = false;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return flag;
		}
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		try {
			if (STRINGTYPE.equals(types)) {
				flag = jedis.hexists((String) key, (String) field);
			} else {
				flag = jedis.hexists(RedisUtils.object2Bytes(key), RedisUtils.object2Bytes(field));
				logger.debug(RedisMessage.REDISMESSAGE_001 + "exisHash");
				return flag;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return flag;
	}

	/**
	 * 获得HASH key的集合长度 REDIS(HLEN)
	 * 
	 * @param key
	 * @param classType
	 * @return
	 */
	public long getHashLen(Object key,  Class<?> classType) {
		String types=classType.getName();
		long len = 0;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return len;
		}
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		try {
			if (STRINGTYPE.equals(types)) {
				len = jedis.hlen((String) key);
			} else {
				len = jedis.hlen(RedisUtils.object2Bytes(key));
				logger.debug(RedisMessage.REDISMESSAGE_001 + "getHashLen");
				return len;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return len;
	}

	/**
	 * 通过key（主键） 和field（域）获得HASH的对象 注意 ：classType不为String时 需做二进制转换 REDIS(HGET)
	 * 
	 * @param key
	 * @param field
	 * @param classType
	 * @return
	 */
	public Object getHashByField(Object key, Object field, Class<?> classType) {
		String types=classType.getName();
		Object rs = null;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return null;
		}
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		try {
			if (STRINGTYPE.equals(types)) {
				rs = jedis.hget((String) key, (String) field);
			} else {
				rs = jedis.hget(RedisUtils.object2Bytes(key), RedisUtils.object2Bytes(field));

			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "getHashValue");
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return null;
	}

	/**
	 * 通过key（主键） 和field数组（域）获得HASH的对象集合 注意 ：classType不为String时 需做二进制转换
	 * 数组长度不宜大于1000 REDIS(HGET)
	 * 
	 * @param key
	 * @param field
	 * @param classType
	 * @return
	 */
	public List<Object> getHashByFields(Object key, Object[] fields, Class<?> classType) {
		String types=classType.getName();
		 
		Object rs = null;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return null;
		}
		List<Object> list_rs = new ArrayList<Object>();
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		try {
			for (Object field : fields) {

				if (STRINGTYPE.equals(types)) {
					rs = jedis.hget((String) key, (String) field);
				} else {
					rs = jedis.hget(RedisUtils.object2Bytes(key), RedisUtils.object2Bytes(field));

				}
				list_rs.add(rs);
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "getHashByFields");
			if (list_rs.size() == 0) {
				return null;
			}
			return list_rs;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return null;
	}

	/**
	 * 通过key（主键） 和field（域）获得HASH的对象 注意 ：classType不为String时 需做二进制转换
	 * REDIS(HGETALL)
	 * 
	 * @param key
	 * @param classType
	 * @return
	 */
	public Map<?, ?> getHashAll(Object key, Class<?> classType) {
		String types=classType.getName();
		 
		Map<?, ?> map = null;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return null;
		}
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		try {
			if (STRINGTYPE.equals(types)) {
				map = jedis.hgetAll((String) key);
			} else {
				map = jedis.hgetAll(RedisUtils.object2Bytes(key));
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "getHashAll");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return null;
	}

	/**
	 * 获得HASH表中KEY的所有域名 注意 ：classType不为String时 需做二进制转换 REDIS(HKEYS)
	 * 
	 * @param key
	 * @param classType
	 * @return
	 */
	public Object getHashField(Object key, Class<?> classType) {
		String types=classType.getName();
		Object rs = null;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return null;
		}
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		try {
			if (STRINGTYPE.equals(types)) {
				rs = jedis.hkeys((String) key);
			} else {
				rs = jedis.hkeys(RedisUtils.object2Bytes(key));
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "getHashField");
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return null;
	}

	/**
	 * 获得HASH表中KEY的所有对象 注意 ：classType不为String时 需做二进制转换 REDIS(HVALS)
	 * 
	 * @param key
	 * @param classType
	 * @return
	 */
	public Object getHashValues(Object key, Class<?> classType) {
		String types=classType.getName();
		Object rs = null;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return null;
		}
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		try {
			if (STRINGTYPE.equals(types)) {
				rs = jedis.hvals((String) key);
			} else {
				rs = jedis.hvals(RedisUtils.object2Bytes(key));
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "getHashValues");
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return null;
	}


}
