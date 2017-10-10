package org.ZFashionFramework.CommonRedis.redis.dao;

import org.ZFashionFramework.CommonRedis.client.jedis.ShardedJedis;
import org.ZFashionFramework.CommonRedis.redis.utils.RedisMessage;
import org.ZFashionFramework.CommonRedis.redis.utils.RedisUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @标题: FoundationDaoIMP.java
 * @包名： cn.com.carapp.redis.dao
 * @编写人： 韩峥
 * @创建日期： 2012-8-3 下午3:04:20
 * @修改人：
 * @修改日期： 2012-8-3 下午3:04:20
 * @版本：
 * @描述: REDIS基本类型DAO
 */
public class FoundationDaoIMP extends BaseRedisDao implements IFoundationDao {
	private static final Log logger = LogFactory.getLog(FoundationDaoIMP.class);

	public FoundationDaoIMP(String poolName) {
		super.setPoolName(poolName);
	}

	public FoundationDaoIMP() {
			this(null);
	}

	/**
	 * 检查KEY是否存在 REDIS(EXISTS)
	 * 
	 * @param key
	 *            关键字
	 * @param classType
	 *            关键字类型
	 * @return 成功失败
	 */
	public boolean exists(Object key, Class<?> classType) {
		boolean flag = false;
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());

		try {

			if (STRINGTYPE.equals(classType.getName())) {
				flag = jedis.exists((String) key);
			} else {
				flag = jedis.exists(RedisUtils.object2Bytes(key));
			}

			logger.debug(RedisMessage.REDISMESSAGE_001 + "exists");
			return flag;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			logger.error(e.getLocalizedMessage());
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return flag;
	}

	/**
	 * 设置超时时间 单位（秒） REDIS(EXPIRE)
	 * 
	 * @param key
	 *            关键字
	 * @param timeOut
	 *            超时时间（单位秒）
	 * @param classType
	 *            关键字类型
	 */
	public void expire(Object key, int timeOut, Class<?> classType) {
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());

		try {
			if (STRINGTYPE.equals(classType.getName())) {
				jedis.expire((String) key, timeOut);
			} else {
				jedis.expire(RedisUtils.object2Bytes(key), timeOut);
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "expire");
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} finally {
			this.getShardePool().returnResource(jedis);
		}
	}

	/**
	 * 设置超时时间 （time 为日期类型转换的LONG值） REDIS(EXPIREAT)
	 * 
	 * @param key
	 *            关键字
	 * @param timeOut
	 *            超时时间（单位秒）
	 * @param classType
	 *            关键字类型
	 */
	public void expireAt(Object key, long time, Class<?> classType) {
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());

		try {
			if (STRINGTYPE.equals(classType.getName())) {
				jedis.expireAt((String) key, time);
			} else {
				jedis.expireAt(RedisUtils.object2Bytes(key), time);
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "expireAt");
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} finally {
			this.getShardePool().returnResource(jedis);
		}
	}

	/**
	 * 得到KEY对应的自动增量值 步长=1 REDIS(INCRBY)
	 * 
	 * @param step
	 *            步长
	 * @param classType
	 *            关键字类型
	 * @return 长整型
	 */
	public long increment(Object key, Class<?> classType) {
		return increment(key, 1, classType);
	}

	/**
	 * 得到KEY对应的自动增量值 步长=-1 REDIS(INCRBY)
	 * 
	 * @param step
	 *            步长
	 * @param classType
	 *            关键字类型
	 * @return 长整型
	 */
	public long incrementDesc(Object key, Class<?> classType) {
		return increment(key, -1, classType);
	}

	/**
	 * 得到KEY对应的自动增量值 (step 步长 step>0增长 step<0减少) REDIS(INCRBY)
	 * 
	 * @param key
	 *            关键字
	 * @param step
	 * @param classType
	 * @return
	 */
	public long increment(Object key, int step, Class<?> classType) {
		long number = 0;
		if (step == 0) {
			logger.error(RedisMessage.REDIS_ERROR_003);
			return 0;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());

		try {
			if (STRINGTYPE.equals(classType.getName())) {
				if (step >= 0) {
					number = jedis.incrBy((String) key, step);
				} else {
					number = jedis.decrBy((String) key, step);
				}
			} else {
				if (step >= 0) {
					number = jedis.incrBy(RedisUtils.object2Bytes(key), step);
				} else {
					number = jedis.decrBy(RedisUtils.object2Bytes(key), step);
				}
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "increment");
			return number;
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return number;
	}

	/**
	 * 得到距离失效时间剩余秒 REDIS(TTL)
	 * 
	 * @param key
	 * @param classType
	 * @return
	 */
	public long destroyTime(Object key, Class<?> classType) {
		long time = 0;

		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());

		try {
			if (STRINGTYPE.equals(classType.getName())) {
				time = jedis.ttl((String) key);
			} else {
				time = jedis.ttl(RedisUtils.object2Bytes(key));
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "destroyTime");
			return time;
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return time;
	}

	/**
	 * 获得链接 （高级使用 ，一定要手动关闭链接）
	 * 
	 * @return
	 */
	public ShardedJedis getShardedJedis() {
		logger.info(RedisMessage.REDISMESSAGE_001 + "getShardedJedis");
		return getShardePool4Read().getResource(super.getPoolName());
	}

	/**
	 * 关闭链接
	 * 
	 * @return
	 */
	public void getShardedJedis(ShardedJedis jedis) {
		logger.info(RedisMessage.REDISMESSAGE_001 + "getShardedJedis");
		this.getShardePool4Read().returnResource(jedis);
	}

}
