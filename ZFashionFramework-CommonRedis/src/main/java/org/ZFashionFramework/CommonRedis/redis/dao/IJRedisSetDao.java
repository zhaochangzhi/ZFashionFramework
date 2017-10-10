 package org.ZFashionFramework.CommonRedis.redis.dao;
import java.util.Set;

/**
 * REDIS SET处理类
 * 不支持使用以下命令 (jedis2.0版本未支持)
 * SMOVE; SINTER;◦SRANDMEMBER;SINTER;SINTERSTORE;SUNION;SUNIONSTORE;SDIFF;SDIFFSTORE
 * @author hanzheng
 * 
 */
public interface IJRedisSetDao  {
	/**向SET中添加对象
	 *  REDIS(SADD)
	 * @param key
	 * @param value
	 * @param classType 
	 * @return 
	 */
	public boolean addSet(Object key,Object value,Class<?> classType);
	
	/**向SET中添加对象
	 *  REDIS(SADD)
	 * @param key
	 * @param value
	 * @param outTime
	 * @param classType 
	 * @return 
	 */
	public boolean addSet(Object key,Object value, int outTime,Class<?> classType);

	/**批量向SET中添加对象
     *  REDIS(SADD)
     * 对象长度应小于1000
	 * @param keys
	 * @param values
	 * @param classType 
	 * @return 
	 */
	public boolean addSets(Object[] keys,Object[] values,Class<?> classType);
	
	/**批量执行 HASH存储 域和对象   
	 * 对象长度应小于1000
	 *  REDIS(SADD)
	 * @param keys
	 * @param values
	 * @param outTime
	 * @param classType 
	 * @return 
	 */
	public boolean addSets(Object[] keys,Object[] values, int outTime,Class<?> classType) ;
	
	/** 删除SET方法
	 * 当KEY不是SET类型throw异常
	 * REDIS(SREM) 
	 * @param key 
	 * @param field
	 * @param classType
	 * @return
	 */
	public boolean delSet(Object key,Object value,Class<?> classType);
	
	/** 查看 是否存在SET 的值
	 * REDIS(SISMEMBER) 
	 * @param key 
	 * @param value
	 * @param classType
	 * @return
	 */
	public boolean exisSet(Object key,Object value,Class<?> classType);
	
	/** 获得指定SET key的集合长度
	 * REDIS(SCARD) 
	 * @param key 
	 * @param classType
	 * @return
	 */
	public long getSetLen(Object key,Object field,Class<?> classType);
	
	/** 通过key（主键） 获得SET的集合
	 *  注意 ：classType不为String时 需做二进制转换
	 *  REDIS(HGET) 
	 * @param key 
	 * @param classType
	 * @return
	 */
	public Set<?> getSetAll(Object key,Class<?> classType);
	
	/** 随机删除 SET中的一个对象并返回此对象
	 *  注意 ：classType不为String时 需做二进制转换
	 *  REDIS(SPOP) 
	 * @param key 
	 * @return
	 */
	public Object delSetRandom(Object key,Class<?> classType);
	
	/** 随机获取 SET中的一个对象
	 *  注意 ：classType不为String时 需做二进制转换
	 *  REDIS(SRANDMEMBER) 
	 * @param key 
	 * @return
	 */
	public Object getSetRandom(Object key,Class<?> classType);
	
	 
}
