package org.ZFashionFramework.CommonRedis.redis.dao;


import java.util.Set;

import org.ZFashionFramework.CommonRedis.client.jedis.ShardedJedis;
import org.ZFashionFramework.CommonRedis.redis.utils.RedisMessage;
import org.ZFashionFramework.CommonRedis.redis.utils.RedisUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * REDIS OBJECT处理类
 * 
 * @author hanzheng
 * 
 */

public class JRedisObjDaoIMP   extends BaseRedisDao  implements IJRedisObjDao{
	private static final Log logger = LogFactory.getLog(JRedisObjDaoIMP.class);
	public JRedisObjDaoIMP(String poolName ){
		super.setPoolName(poolName);
	}

	public JRedisObjDaoIMP() {
		// TODO Auto-generated constructor stub
	}

	/**存入对象 
	 * REDIS (SET)
	 * @param key
	 * @param value
	 * @param outTime
	 * @return
	 */
	public boolean setObject(Object key, Object value, Class<?> classType) {
		return setObject(key, value, 0, classType);
	}
	
	/**通过正则匹配关键KEY
	 * REDIS (SET)
	 * @param key
	 * @param value
	 * @param outTime
	 * @return
	 */
	public Set<String> keys(String keyPatten) {
		Set<String> keys=null;
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		try {
			keys=jedis.keys(keyPatten);
		logger.debug(RedisMessage.REDISMESSAGE_001 + "keys");
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} finally  {
			this.getShardePool4Read().returnResource(jedis);
		}
		return keys;
	}

	/**存入一个对象 带超时（秒） 
	 * REDIS (SET)
	 * @param key
	 * @param value
	 * @param outTime
	 * @param classType
	 * @return
	 */
	public boolean setObject(Object key, Object value, int outTime,
			Class<?> classType) {
		boolean flag = false;
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		String types=classType.getName();	
		if(super.getDb()!=null){
			jedis.select(super.getDb());
		}
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return flag;
		}
		try {
			if (STRINGTYPE.equals(types)) {
				jedis.set((String) key, (String) value);
			
			} else {
				jedis.set(RedisUtils.object2Bytes(key),
						RedisUtils.object2Bytes(value));
				 
			}
			RedisUtils.setExpire(jedis, key, types, outTime);
			logger.debug(RedisMessage.REDISMESSAGE_001 + "setObject");
			return true;
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}

	/** 存入一组对象 带超时（秒） 
	 * REDIS (SET)
	 * @param keys
	 * @param values
	 * @param outTime
	 * @param classType
	 * @return
	 */
	public boolean setObjects(Object[] keys, Object[] values, Class<?> classType) {
		//TODO BUG
		return setObjects(keys, values, classType);
	}

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
			Class<?> classType) {
		boolean flag = false;
		if (keys == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return flag;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		String types=classType.getName();	
		try {
			int i = 0;
			for (Object key : keys) {
				if (STRINGTYPE.equals(types)) {
					jedis.set((String) key, (String) values[i]);
					
				} else {
					jedis.set(RedisUtils.object2Bytes(key),
							RedisUtils.object2Bytes(values[i]));
				}
				RedisUtils.setExpire(jedis, key, types, outTime);
				i = i + 1;
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "setObjects");
			return true;
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}
	
	/** 存入一个对象 (如果数据库中有此KEY 不做修改操作） 
     * REDIS (SETNX)
	 * @param keys
	 * @param values
	 * @param classType 
	 * @return
	 */
	public long setNXObject(Object key, Object value,
			Class<?> classType) {
		return  setNXObject( key,value,0,
				 classType);
	}
	/** 存入一个对象 (如果数据库中有此KEY 不做修改操作)带超时（秒） 
     * REDIS (SETNX)
	 * @param keys
	 * @param values
	 * @param outTime
	 * @param classType 
	 * @return
	 */
	public long setNXObject(Object key, Object value, int outTime,
			Class<?> classType) {
		long flag = 0;
		String types=classType.getName();	
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		 
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return -1;
		}
		try {
			if (STRINGTYPE.equals(types)) {
				flag=jedis.setnx((String) key, (String) value);
				
			} else {
				flag=jedis.setnx(RedisUtils.object2Bytes(key),
						RedisUtils.object2Bytes(value));
			}
			if(flag==1){
				RedisUtils.setExpire(jedis, key, types, outTime);
			}
			
			logger.debug(RedisMessage.REDISMESSAGE_001 + "setNXObjects");
			 
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	
	}
	
	/**获取KEY对应的对象
	 * REDIS (GET)
	 * @param key
	 * @param classType
	 */
	public Object getObject(Object key, Class<?> classType) {
		Object obj = null;
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		String types=classType.getName();	
		if(super.getDb()!=null){
			jedis.select(super.getDb());
		}
		try {
			if (STRINGTYPE.equals(types)) {
				obj = jedis.get((String) key);
			} else {
				obj = jedis.get(RedisUtils.object2Bytes(key));
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "getObject");
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return null;
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}

		return obj;
	}

	/** 删除对象 
	 * REDIS (DEL)
	 * @param key
	 * @param classType
	 * @return
	 */
	public boolean delObject(Object key, Class<?> classType) {
		boolean flag = false;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return flag;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		
		String types=classType.getName();	
		try {
			if (STRINGTYPE.equals(types)) {
				jedis.del((String) key);
			} else {
				jedis.del("" + RedisUtils.object2Bytes(key));
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "delObject");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}

	/** 批量删除对象
	 * REDIS (DEL)
	 * @param keys
	 * @param classType
	 * @return
	 */
	public boolean delObjects(Object[] keys, Class<?> classType) {
		boolean flag = false;
		if (keys == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return flag;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		String types=classType.getName();	
		try {
			int i = 0;
			for (Object key : keys) {
				if (STRINGTYPE.equals(types)) {
					jedis.del((String) key);
				} else {
					jedis.del("" + RedisUtils.object2Bytes(key));
				}
				i = i + 1;
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "delObjects");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}

	/** 向KEY对应的元素尾部追加值
	 *  REDIS (APPEND)
	 * （添加OBJECT慎用）
	 * @param key 
	 * @param classType
	 * @return
	 */
	public boolean appendObject(Object key, Object object, Class<?> classType) {
		boolean flag = false;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return flag;
		}
		String types=classType.getName();	
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {

			if (STRINGTYPE.equals(types)) {
				 jedis.append((String) key, (String) object);
			} else {
				 jedis.append(RedisUtils.object2Bytes(key),
						RedisUtils.object2Bytes(object));
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "appendObject");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return false;
	}
	
	/** 位偏移指令
	 *  REDIS (SETBIT)
	 * @param key 
	 * @param classType
	 * @return
	 */
	public boolean setBit(String key, long offset,boolean value) {
		boolean flag = false;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return flag;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {
			jedis.setbit(key, offset, value);
		 
			logger.debug(RedisMessage.REDISMESSAGE_001 + "setBit");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return false;
	}
	
	/** 获得位偏移指令结果
	 *  REDIS (GETBIT)
	 * @param key 
	 * @param classType
	 * @return
	 */
	public boolean getBit(String key,long offset) {
		boolean flag = false;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return flag;
		}
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		try {
			flag=jedis.getbit(key, offset);
			logger.debug(RedisMessage.REDISMESSAGE_001 + "getBit");
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return false;
	}
}
