package org.ZFashionFramework.CommonRedis.redis.dao;


import java.util.List;

import org.ZFashionFramework.CommonRedis.client.jedis.ShardedJedis;
import org.ZFashionFramework.CommonRedis.redis.utils.RedisMessage;
import org.ZFashionFramework.CommonRedis.redis.utils.RedisUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**操作LIST方法
 * 未实现的方法
 * LPUSHX ;RPUSHX;RPOPLPUSH;BRPOPLPUSH
 * @author hanzheng
 *
 */

public class JRedisListDaoIMP  extends BaseRedisDao implements IJRedisListDao{
	private final int GLOBALSTART=0;
	private final int GLOBALEND=-1;
	private static final Log logger = LogFactory.getLog(JRedisListDaoIMP.class);
	private static final String STRINGTYPE = "java.lang.String";
	public JRedisListDaoIMP(String poolName ){
		super.setPoolName(poolName);
	}
	public JRedisListDaoIMP(){
		
	}
	
	/** 清空LIST
	 *  REDIS(LTRIM+RPOP)
	 * @param key
	 * @param object
	 * @param classType
	 * @return
	 */
	public boolean cleanList(Object key,Class<?> classType){
		         trimList( key,0,0, classType);
		   return removeHeadPop( key, classType);
	}
	/**  删除下标start-end以外的值
	 * REDIS(LTRIM)
	 * @param key
	 * @param start
	 * @param end
	 * @param classType
	 * @return
	 */
	public boolean trimList(Object key, int start,int end, Class<?> classType) {
		String types=classType.getName();
		boolean flag = false;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return false;
		}
		 
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {
			if (STRINGTYPE.equals(types)) {
				jedis.ltrim((String)key, start, end);
				
			} else {
				jedis.ltrim(RedisUtils.object2Bytes(key), start, end);
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "trimList");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}
	/**在List首元素前添加对象
	 * REDIS(LPUSH)
	 * @param key
	 * @param object
	 * @param classType
	 * @return
	 */
	public boolean addBeforeList(Object key, Object object, Class<?> classType) {
		return addBeforeList(key, object, 0, classType);
	}
	
	/** 在指定元素前添加对象 包含超时（秒）
	 * REDIS(LPUSH)
	 * @param key
	 * @param object
	 * @param outTime
	 * @param classType
	 * @return
	 */
	public boolean addBeforeList(Object key, Object object, int outTime,
			Class<?> classType) {
		String types=classType.getName();
		boolean flag = false;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return false;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {
			if (STRINGTYPE.equals(types)) {
				jedis.lpush((String) key, (String) object);
			
			} else {
				jedis.lpush(RedisUtils.object2Bytes(key),
						RedisUtils.object2Bytes(object));
			}
			RedisUtils.setExpire(jedis, key, types, outTime);
			logger.debug(RedisMessage.REDISMESSAGE_001 + "addBeforeList");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}
	
	
	
	/**批量添加LIST 在指定元素前添加对象 
     * 对象长度应小于1000
	 * REDIS(LPUSH)
	 * @param keys
	 * @param objects
	 * @param classType
	 * @return
	 */
	public boolean addBeforeLists(Object[] keys, Object[] objects,
			Class<?> classType) {
		return  addBeforeLists(keys, objects,0, classType) ;
	}
	
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
			Class<?> classType) {
		String types=classType.getName();
		boolean flag = false;
		if (keys == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return false;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {
			int i = 0;
			for (Object key : keys) {
				if (STRINGTYPE.equals(types)) {
					jedis.lpush((String) key, (String) objects[i]);
				} else {
					jedis.lpush(RedisUtils.object2Bytes(key),
							RedisUtils.object2Bytes(objects[i]));
				}
				RedisUtils.setExpire(jedis, key, types, outTime);
				i = i + 1;
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "addBeforeList");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}

	/** 在指定元素后添加对象
	 * REDIS(RPUSH)
	 * @param key
	 * @param object
	 * @param classType
	 * @return
	 */
	public boolean appendList(Object key, Object object, Class<?> classType) {
		return appendList(key, object, 0, classType);
	}
	
	/**在指定元素后添加对象 包含超时（秒）
	 * REDIS(RPUSH)
	 * @param key
	 * @param object
	 * @param outTime
	 * @param classType
	 * @return
	 */
	public boolean appendList(Object key, Object object, int outTime,
			Class<?> classType) {
		String types=classType.getName();
		boolean flag = false;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return false;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {
			if (STRINGTYPE.equals(types)) {
				jedis.rpush((String) key, (String) object);
			
			} else {
				jedis.rpush(RedisUtils.object2Bytes(key),
						RedisUtils.object2Bytes(object));
			}
			RedisUtils.setExpire(jedis, key, types, outTime);
			logger.debug(RedisMessage.REDISMESSAGE_001 + "appendList");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}
	
	/** 批量执行LIST 在指定元素后添加对象 
	 * 对象长度应小于1000
	 * REDIS(RPUSH)
	 * @param key
	 * @param object
	 * @param classType
	 * @return
	 */
	public boolean appendLists(Object[] keys, Object[] objects,
			Class<?> classType) {
		return  appendLists(keys, objects,0, classType) ;
	}
	/** 批量执行LIST 在指定值后添加对象 包含超时（秒）
     * 对象长度应小于1000
	 * @param key
	 * @param object
	 * @param outTime
	 * @param classType
	 * @return
	 */
	public boolean appendLists(Object[] keys, Object[] objects, int outTime,
			Class<?> classType) {
		String types=classType.getName();
		boolean flag = false;
		if (keys == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return false;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {
			int i = 0;
			for (Object key : keys) {
				if (STRINGTYPE.equals(types)) {
					jedis.rpush((String) key, (String) objects[i]);
				} else {
					jedis.rpush(RedisUtils.object2Bytes(key),
							RedisUtils.object2Bytes(objects[i]));
				}
				RedisUtils.setExpire(jedis, key, types, outTime);
				i = i + 1;
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "appendLists");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}
	
	/**获得key的全部集合
	 * 集合数据量大时慎用
	 * REDIS(LRANG) 下标为0，-1
	 * @param key
	 * @param classType
	 * @return
	 */
	public List<?> getListAll(Object key ,Class<?> classType){
		 return getList( key ,GLOBALSTART,GLOBALEND, classType);
	}
	/**获得指定下标范围的集合
	 * REDIS(LRANG) 
	 * @param key
	 * @param start
	 * @param end
	 * @param classType
	 * @return
	 */
	public List<?> getList(Object key ,int start,int end,Class<?> classType){
		String types=classType.getName();
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		List<?> list=null;
		try {
			if (STRINGTYPE.equals(types)) {
				list=jedis.lrange((String)key, start, end); 
			} else {
				list=jedis.lrange(RedisUtils.object2Bytes(key), start, end); 
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "getList");
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return list;
	}

	/** 移除List头部元素
	 * REDIS(RPOP)
	 * @param key
	 * @param object
	 * @param classType
	 * @return
	 */
	public boolean removeHeadPop(Object key,Class<?> classType){
		String types=classType.getName();
		boolean flag = false;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return false;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {
			if (STRINGTYPE.equals(types)) {
				jedis.rpop((String)key);
			} else {
				jedis.rpop(RedisUtils.object2Bytes(key));
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "removeHeadPop");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}
	/**移除List尾部元素
	 * REDIS(LPOP)
	 * @param key
	 * @param classType
	 * @return
	 */
	public boolean removeTailPop(Object key,Class<?> classType){
		String types=classType.getName();
		boolean flag = false;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return false;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {
			if (STRINGTYPE.equals(types)) {
				jedis.lpop((String) key);
			} else {
				jedis.lpop(RedisUtils.object2Bytes(key));
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "removeTailPop");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}
	
	 /** 根据下标值获得元素
	  * REDIS(LINDEX)
	 * @param key
	 * @param index
	 * @param classType
	 * @return
	 */
	public Object getListByIndex(Object key,long index, Class<?> classType){
		String types=classType.getName();
		Object object = null;
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		try {
			if (STRINGTYPE.equals(types)) {
				object = jedis.lindex((String) key, index);
			} else {
				object = jedis.lindex(RedisUtils.object2Bytes(key), (int) index);
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "getListByIndex");
			return object;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return object;
	 }
	
	 /** 获得List的长度
	 * REDIS(LLEN)
	 * @param key
	 * @param classType
	 * @return
	 */
	public long getListLen(Object key, Class<?> classType){
		String types=classType.getName();
		  long count=0;
			ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
			try {
				if (STRINGTYPE.equals(types)) {
					count=jedis.llen((String)key);
				} else {
					count=jedis.llen(RedisUtils.object2Bytes(key));
				}
				logger.debug(RedisMessage.REDISMESSAGE_001 + "getListLen");
				return count;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				this.getShardePool4Read().returnResource(jedis);
			}
			return count;
	 }
	
	/**按照下标范围删除 元素等于object的元素 返回删除数量
	 * （count  为正数 从首-尾 ,count为负数  从尾到首）
	 *  REDIS(LREM)
	 * @param key
	 * @param count
	 * @param object
	 * @param classType
	 * @return
	 */
	public boolean moveListByIndex (Object key,long count ,Object object,Class<?> classType){
		String types=classType.getName();
		boolean flag = false;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return false;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {
			if (STRINGTYPE.equals(types)) {
				jedis.lrem((String) key, count, (String) object);
			} else {
				jedis.lrem(RedisUtils.object2Bytes(key), (int) count,
						RedisUtils.object2Bytes(object));
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "moveListByIndex");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}
}
