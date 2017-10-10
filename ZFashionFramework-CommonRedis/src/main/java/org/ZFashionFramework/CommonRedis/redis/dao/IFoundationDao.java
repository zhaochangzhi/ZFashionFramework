package org.ZFashionFramework.CommonRedis.redis.dao;

import org.ZFashionFramework.CommonRedis.client.jedis.ShardedJedis;



public interface IFoundationDao  {
	/**检查KEY是否存在
	 * REDIS(EXISTS)
	 * @param key
	 * @param classType
	 * @return
	 */
	public boolean exists(Object key, Class<?> classType);

	/** 设置超时时间 单位（秒）
	 * REDIS(EXPIRE)
	 * @param key
	 * @param classType
	 * @return
	 */
	public void expire(Object key, int timeOut, Class<?> classType);
	
	/**设置超时时间 
	 * （time 为日期类型转换的LONG值）
	 * REDIS(EXPIREAT)
	 * @param key
	 * @param timeOut
	 * @param classType
	 * @return
	 */
	public void expireAt(Object key,long time, Class<?> classType);

	/** 得到KEY对应的自动增量值 步长=1
	 * REDIS(INCRBY)
	 * @param step
	 * @param classType
	 * @return
	 */
	public long increment(Object key, Class<?> classType);
	
	/**
	 * 得到KEY对应的自动增量值 步长=-1
	 * REDIS(INCRBY)
	 * @param step
	 * @param classType
	 * @return
	 */
	public long incrementDesc(Object key, Class<?> classType);

	/**
	 * 得到KEY对应的自动增量值 (step 步长 step>0增长 step<0减少)
	 * REDIS(INCRBY)
	 * @param key
	 * @param step
	 * @param classType
	 * @return
	 */
	public long increment(Object key, int step, Class<?> classType);
	/**得到距离失效时间剩余秒
	 * REDIS(TTL)
	 * @param key
	 * @param classType
	 * @return
	 */
	public long destroyTime(Object key, Class<?> classType);
	
	/**获得链接
	 * （高级使用 ，一定要手动关闭链接）
	 * @return
	 */
	public ShardedJedis getShardedJedis() ;
	
	/**关闭链接
	 * 
	 * @return
	 */
	public void   getShardedJedis( ShardedJedis jedis);
}
