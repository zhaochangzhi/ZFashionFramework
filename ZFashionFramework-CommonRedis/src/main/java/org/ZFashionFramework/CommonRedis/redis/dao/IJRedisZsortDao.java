package org.ZFashionFramework.CommonRedis.redis.dao;

import java.util.Set;


public interface IJRedisZsortDao {
	/**保存一个ZSORT对象
	 * REDIS(ZADD)
	 * @param key 
	 * @param score double类型
	 * @param classType
	 * @return 
	 */
	public boolean setZsort(String key, double score, Object member,
			Class<?> classType) ;
	
	/** 保存一个对象 (设置保存的时常 单位为妙)
	 * REDIS(ZADD)
	 * @param key
	 * @param score
	 * @param classType
	 * @return
	 */
	public boolean setZsort(String key, double score, Object member,
			int outTime, Class<?> classType) ;

	/** 批量保存ZSORT对象
	 * 对象长度应小于1000
	 *  REDIS(ZADD)
	 * @param key 
	 * @param scores double[]类型
	 * @param classType
	 * @return
	 */
	public boolean setZsorts(String key, double[] scores, Object[] member,
			Class<?> classType) ;
	
	/**批量保存ZSORT对象（ 单位为妙)
	 * 对象长度应小于1000
	 *  REDIS(ZADD)
	 * @param key
	 * @param scores double[]类型
	 * @param classType
	 * @return
	 */
	public boolean setZsorts(String key, double[] scores, Object[] members,
			int outTime, Class<?> classType);
	
	/** 通过member域移除ZSORT中对象
	 * REDIS（ZREM）
	 * @param key
	 * @param classType
	 * @return
	 */
	public boolean remZSortByMember(String key,Object member,Class<?> classType);
	
	/** 删除ZSORT指定KEY的所有对象
	 * REDIS（ZREMRANGEBYRANK） 
	 * @param key
	 * @return
	 */
	public boolean remZSort(String key,Class<?> classType);
	
	/** 删除ZSORT指定域区间内的对象
	 *  REDIS（ZREMRANGEBYRANK） 
	 * @param key
	 * @return
	 */
	public boolean remZSortByIndex(String key,int start,int end,Class<?> classType);
	
	/** 删除ZSORT的域取值范围内的对象
	 * REDIS（ZREMRANGEBYSCORE） 
	 * @param key
	 * @return
	 */
	public boolean remZSortByScore(String key,int min,int max,Class<?> classType);
	
	/** 获得ZSORT的对象总数
	 * REDIS（ZCARD） 
	 * @param key
	 * @param classType
	 * @return
	 */
	public long  getZSortLen(Object key,Class<?> classType);
	
	/** 获得ZSORT的所有MEMBER（正序）
	 * MEMBER数据量大时慎用
	 * REDIS（ZRANGE） 
	 * @param key
	 * @param classType
	 * @return
	 */
	public Set<?> rangeAllZSort(Object key,Class<?> classType);
	
	/** 获得ZSORT中 所有MEMBER（正序）
	 * MEMBER数据量大时慎用
	 * REDIS（ZRANGE） 
	 * @param key
	 * @param classType
	 * @return
	 */
	public Set<?> rangeZSort(Object key,int start,int end,Class<?> classType);
	
	/**通过ZSORT的域取值范围获得MEMBER对象(正序)
	 * REDIS（ZRANGE） 
	 * @param key
	 * @param min
	 * @param max
	 * @param classType
	 * @return
	 */
	public Set<?> rangeZSortByScore(Object key,double min,double max,Class<?> classType);
	
	/**通过ZSORT的域取值范围 并截取长度获得MEMBER对象(正序)
	 * REDIS（ZRANGEBYSCORE） 
	 * @param key
	 * @param min
	 * @param max
	 * @param offset
	 * @param count
	 * @param classType
	 * @return
	 */
	public Set<?> rangeZSortByScore(Object key,double min,double max,int offset,int count,Class<?> classType);
	
	 
	/**获得KEY 的所有对象倒叙排列
	 * MEMBER数据量大时慎用
	 * REDIS（ZREVRANGE） 
	 * @param key
	 * @param classType
	 * @return
	 */
	public Set<?> rangeDescAllZSort(Object key,Class<?> classType);
	
	/**获得KEY 的所有对象（倒序）
	 * REDIS（ZREVRANGE） 
	 * zrevrange
	 * @param key
	 * @param start
	 * @param end
	 * @param classType
	 * @return
	 */
	public Set<?> rangeDescZSort(Object key,int start,int end,Class<?> classType);
	
	/**通过score的值范围获得对象 (倒序)
	 * REDIS（ZREVRANGEBYSCORE） 
	 * @param key  
	 * @param max   这是最大值  （接口如此 不换序了）
	 * @param min   这是最小值    
	 * @param classType
	 * @return
	 */
	public Set<?> rangeDescZSortByScore(Object key,double max,double min,Class<?> classType);
}
