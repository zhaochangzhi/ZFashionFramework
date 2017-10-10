package org.ZFashionFramework.CommonRedis.redis.dao;


import java.util.List;


public interface IJRedisListDao  {
	/** 清空LIST
	 *  REDIS(LTRIM+RPOP)
	 * @param key
	 * @param object
	 * @param classType
	 * @return
	 */
	public boolean cleanList(Object key,Class<?> classType);
	
	/**  删除下标start-end以外的值
	 * REDIS(LTRIM)
	 * @param key
	 * @param start
	 * @param end
	 * @param classType
	 * @return
	 */
	public boolean trimList(Object key, int start,int end, Class<?> classType);
	
	/**在List首元素前添加对象
	 * REDIS(LPUSH)
	 * @param key
	 * @param object
	 * @param classType
	 * @return
	 */
	public boolean addBeforeList(Object key, Object object, Class<?> classType);
	
	/** 在指定元素前添加对象 包含超时（秒）
	 * REDIS(LPUSH)
	 * @param key
	 * @param object
	 * @param outTime
	 * @param classType
	 * @return
	 */
	public boolean addBeforeList(Object key, Object object, int outTime,
			Class<?> classType);
	
	/**批量添加LIST 在指定元素前添加对象 
     * 对象长度应小于1000
	 * REDIS(LPUSH)
	 * @param keys
	 * @param objects
	 * @param classType
	 * @return
	 */
	public boolean addBeforeLists(Object[] keys, Object[] objects,
			Class<?> classType) ;
	
	/** 批量添加LIST 在指定元素前添加对象 包含超时（秒）
	 * 对象长度应小于1000
	 * REDIS(LPUSH)
	 * @param keys
	 * @param objects
	 * @param outTime
	 * @param classType
	 * @return
	 */
	public boolean addBeforeLists(Object[] keys, Object[] objects, int outTime,
			Class<?> classType) ;
	/** 在指定元素后添加对象
	 * REDIS(RPUSH)
	 * @param key
	 * @param object
	 * @param classType
	 * @return
	 */
	public boolean appendList(Object key, Object object, Class<?> classType) ;
	
	/**在指定元素后添加对象 包含超时（秒）
	 * REDIS(RPUSH)
	 * @param key
	 * @param object
	 * @param outTime
	 * @param classType
	 * @return
	 */
	public boolean appendList(Object key, Object object, int outTime,
			Class<?> classType);
	
	/** 批量执行LIST 在指定元素后添加对象 
	 * 对象长度应小于1000
	 * REDIS(RPUSH)
	 * @param key
	 * @param object
	 * @param classType
	 * @return
	 */
	public boolean appendLists(Object[] keys, Object[] objects,
			Class<?> classType);
	
	/** 批量执行LIST 在指定值后添加对象 包含超时（秒）
     * 对象长度应小于1000
	 * @param key
	 * @param object
	 * @param outTime
	 * @param classType
	 * @return
	 */
	public boolean appendLists(Object[] keys, Object[] objects, int outTime,
			Class<?> classType);
	
	/**获得key的全部集合
	 * 集合数据量大时慎用
	 * REDIS(LRANG) 下标为0，-1
	 * @param key
	 * @param classType
	 * @return
	 */
	public List<?> getListAll(Object key ,Class<?> classType);
	/**获得指定下标范围的集合
	 * REDIS(LRANG) 
	 * @param key
	 * @param start
	 * @param end
	 * @param classType
	 * @return
	 */
	public List<?> getList(Object key ,int start,int end,Class<?> classType);

	/** 移除List头部元素
	 * REDIS(RPOP)
	 * @param key
	 * @param object
	 * @param classType
	 * @return
	 */
	public boolean removeHeadPop(Object key,Class<?> classType);
	
	/**移除List尾部元素
	 * REDIS(LPOP)
	 * @param key
	 * @param classType
	 * @return
	 */
	public boolean removeTailPop(Object key,Class<?> classType);
	
	 /** 根据下标值获得元素
	  * REDIS(LINDEX)
	 * @param key
	 * @param index
	 * @param classType
	 * @return
	 */
	public Object getListByIndex(Object key,long index, Class<?> classType);
	
	 /** 获得List的长度
	  * REDIS(LLEN)
	 * @param key
	 * @param classType
	 * @return
	 */
	public long getListLen(Object key, Class<?> classType);
	/**按照下标范围删除 元素等于object的元素 返回删除数量
	 * （count  为正数 从首-尾 ,count为负数  从尾到首）
	 * 
	 * @param key
	 * @param count
	 * @param object
	 * @param classType
	 * @return
	 */
	public boolean moveListByIndex (Object key,long count ,Object object,Class<?> classType);
}
