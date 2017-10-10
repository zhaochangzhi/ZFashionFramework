 package org.ZFashionFramework.CommonRedis.redis.dao;
import java.util.List;
import java.util.Map;
public interface IJRedisHashDao  {
	/** HASH存储 域和对象   
	 *  REDIS(HSET)
	 * @param key
	 * @param field
	 * @param value
	 * @param classType 
	 * @return 
	 */
	public boolean setHash(Object key, Object field,Object value,Class<?> classType) ;
	
	/** HASH存储 域和对象   带超时（秒） 
	 *  REDIS(HSET)
	 * @param key
	 * @param field
	 * @param value
	 * @param outTime
	 * @param classType 
	 * @return 
	 */
	public boolean setHash(Object key, Object field,Object value, int outTime,Class<?> classType);

	/**批量执行 HASH存储 域和对象   
	 * 对象长度应小于1000
	 *  REDIS(HSET)
	 * @param keys
	 * @param fields
	 * @param values
	 * @param classType 
	 * @return 
	 */
	public boolean setHashs(Object[] keys, Object[] fields,Object[] values,Class<?> classType);
	
	
	/**批量执行 HASH存储 域和对象   
	 * 对象长度应小于1000
	 *  REDIS(HSET)
	 * @param keys
	 * @param fields
	 * @param values
	 * @param outTime
	 * @param classType 
	 * @return 
	 */
	public boolean setHashs(Object[] keys, Object[] fields,Object[] values, int outTime,Class<?> classType);
	
	/** 删除方法
	 * REDIS(HDEL) 
	 * @param key 
	 * @param field
	 * @param classType
	 * @return
	 */
	public boolean delHashKey(Object key,Object field,Class<?> classType);
	
	/** 查看 是否存在HASH 的值
	 * REDIS(HEXISTS) 
	 * @param key 
	 * @param field
	 * @param classType
	 * @return
	 */
	public boolean exisHash(Object key,Object field,Class<?> classType);
	
	
	/** 获得HASH key的集合长度
	 * REDIS(HLEN) 
	 * @param key 
	 * @param classType
	 * @return
	 */
//	public long getHashLen(Object key,Object field,Class<?> classType);
	
	
	
	/** 通过key（主键） 和field（域）获得HASH的对象
	 *  注意 ：classType不为String时 需做二进制转换
	 *  REDIS(HGET) 
	 * @param key 
	 * @param field
	 * @param classType
	 * @return
	 */
	public Object getHashByField(Object key,Object field,Class<?> classType);
	
	/** 通过key（主键） 和field数组（域）获得HASH的对象集合
	 *  注意 ：classType不为String时 需做二进制转换
	 *  数组长度不宜大于1000
	 *  REDIS(HGET) 
	 * @param key 
	 * @param field
	 * @param classType
	 * @return
	 */
	public List<Object> getHashByFields(Object key,Object[] fields,Class<?> classType);
	
	/** 通过key（主键） 和field（域）获得HASH的对象
	 * 注意 ：classType不为String时 需做二进制转换
	 * REDIS(HGETALL) 
	 * @param key 
	 * @param classType
	 * @return
	 */
	public Map<?,?> getHashAll(Object key,Class<?> classType);
	
	/** 获得HASH表中KEY的所有域名
	 *  注意 ：classType不为String时 需做二进制转换
	 *  REDIS(HKEYS) 
	 * @param key 
	 * @param classType
	 * @return
	 */
	public Object getHashField(Object key,Class<?> classType);
	
	
	/** 获得HASH表中KEY的所有对象
	 *  注意 ：classType不为String时 需做二进制转换
	 *  REDIS(HVALS) 
	 * @param key 
	 * @param classType
	 * @return
	 */
	public Object getHashValues(Object key,Class<?> classType);
}
