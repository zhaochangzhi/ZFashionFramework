 package org.ZFashionFramework.CommonRedis.redis.dao;

import java.util.Set;

public interface IJRedisObjDao {
	/**存入对象 
	 * REDIS (SET)
	 * @param key
	 * @param value
	 * @param outTime
	 * @return
	 */
	public boolean setObject(Object key, Object value, Class<?> classType);
	/**通过正则匹配关键KEY
	 * REDIS (SET)
	 * @param key
	 * @param value
	 * @param outTime
	 * @return
	 */
	public Set<String> keys(String keyPatten) ;
	/**存入一个对象 带超时（秒） 
	 * REDIS (SET)
	 * @param key
	 * @param value
	 * @param outTime
	 * @param classType
	 * @return
	 */
	public boolean setObject(Object key, Object value, int outTime,
			Class<?> classType) ;

	/** 存入一组对象 带超时（秒） 
	 * REDIS (SET)
	 * @param keys
	 * @param values
	 * @param outTime
	 * @param classType
	 * @return
	 */
	public boolean setObjects(Object[] keys, Object[] values, Class<?> classType) ;
	/** 批量存入对象 带超时（秒） 
     * 建议一次批量插入不要超过1000条）
     * REDIS (SET)
	 * @param keys
	 * @param values
	 * @param outTime
	 * @param classType 
	 * @return
	 */
	public boolean setObjects(Object[] keys, Object[] values, int outTime,
			Class<?> classType) ;
	/** 存入一个对象 (如果数据库中有此KEY 不做修改操作） 
     * REDIS (SETNX)
	 * @param keys
	 * @param values
	 * @param classType 
	 * @return
	 */
	public long setNXObject(Object key, Object value,
			Class<?> classType);
	/** 存入一个对象 (如果数据库中有此KEY 不做修改操作)带超时（秒） 
     * REDIS (SETNX)
	 * @param keys
	 * @param values
	 * @param outTime
	 * @param classType 
	 * @return
	 */
	public long setNXObject(Object key, Object value, int outTime,
			Class<?> classType) ;
	
	/**获取KEY对应的对象
	 * REDIS (GET)
	 * @param key
	 * @param classType
	 */
	public Object getObject(Object key, Class<?> classType);

	/** 删除对象 
	 * REDIS (DEL)
	 * @param key
	 * @param classType
	 * @return
	 */
	public boolean delObject(Object key, Class<?> classType);

	/** 批量删除对象
	 * REDIS (DEL)
	 * @param keys
	 * @param classType
	 * @return
	 */
	public boolean delObjects(Object[] keys, Class<?> classType);
	/** 向KEY对应的元素尾部追加值
	 *  REDIS (APPEND)
	 * （添加OBJECT慎用）
	 * @param key 
	 * @param classType
	 * @return
	 */
	public boolean appendObject(Object key, Object object, Class<?> classType) ;
	/** 位偏移指令
	 *  REDIS (SETBIT)
	 * @param key 
	 * @param classType
	 * @return
	 */
	public boolean setBit(String key, long offset,boolean value);
	
	/** 获得位偏移指令结果
	 *  REDIS (GETBIT)
	 * @param key 
	 * @param classType
	 * @return
	 */
	public boolean getBit(String key,long offset);
}
