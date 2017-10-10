package org.ZFashionFramework.CommonRedis.redis.dao;

import java.util.Set;

import org.ZFashionFramework.CommonRedis.client.jedis.ShardedJedis;
import org.ZFashionFramework.CommonRedis.redis.utils.RedisMessage;
import org.ZFashionFramework.CommonRedis.redis.utils.RedisUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * REDIS SET处理类
 * 不支持使用以下命令 (jedis2.0版本未支持)
 * SMOVE; SINTER;◦SRANDMEMBER;SINTER;SINTERSTORE;SUNION;SUNIONSTORE;SDIFF;SDIFFSTORE
 * @author hanzheng
 * 
 */

public class JRedisSetDaoIMP  extends BaseRedisDao  implements IJRedisSetDao{
 
	private static final Log logger = LogFactory.getLog(JRedisSetDaoIMP.class);
	public JRedisSetDaoIMP(String poolName ){
		super.setPoolName(poolName);
	}
	public JRedisSetDaoIMP(){
		
	}
	/**向SET中添加对象
	 *  REDIS(SADD)
	 * @param key
	 * @param value
	 * @param classType 
	 * @return 
	 */
	public boolean addSet(Object key,Object value,Class<?> classType) {
		return addSet( key,value,0,classType);
	}
	
	/**向SET中添加对象
	 *  REDIS(SADD)
	 * @param key
	 * @param value
	 * @param outTime
	 * @param classType 
	 * @return 
	 */
	public boolean addSet(Object key,Object value, int outTime,Class<?> classType) {
		String types=classType.getName();
 
		boolean flag = false;
		if(key==null){
			logger.error(RedisMessage.REDIS_ERROR_001);
			return flag;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {
			if (STRINGTYPE.equals(types)) {
				jedis.sadd((String)key,  (String)value);
				
			}else{
				jedis.sadd(RedisUtils.object2Bytes(key),RedisUtils.object2Bytes(value));
			 
			}
			RedisUtils.setExpire(jedis, key, types, outTime);
			logger.debug(RedisMessage.REDISMESSAGE_001+"addSet");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);

		}
		return flag;
	}

	/**批量向SET中添加对象
     *  REDIS(SADD)
     * 对象长度应小于1000
	 * @param keys
	 * @param values
	 * @param classType 
	 * @return 
	 */
	public boolean addSets(Object[] keys,Object[] values,Class<?> classType) {
		return  addSets(keys,values,0, classType) ;
	}
	
	/**批量执行 HASH存储 域和对象   
	 * 对象长度应小于1000
	 *  REDIS(SADD)
	 * @param keys
	 * @param values
	 * @param outTime
	 * @param classType 
	 * @return 
	 */
	public boolean addSets(Object[] keys,Object[] values, int outTime,Class<?> classType) {
		String types=classType.getName();
		boolean flag = false;
		 
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {
			int i = 0;
			for (Object key : keys) {
				if (STRINGTYPE.equals(types)) {
					jedis.sadd((String) key,(String) values[i]);
				
				} else {
						jedis.sadd(RedisUtils.object2Bytes(key),
								RedisUtils.object2Bytes(values[i]));
					 
				}
				RedisUtils.setExpire(jedis, key, types, outTime);
				i = i + 1;
			}
			logger.debug(RedisMessage.REDISMESSAGE_001+"addSets");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}
	
	/** 删除SET方法
	 * 当KEY不是SET类型throw异常
	 * REDIS(SREM) 
	 * @param key 
	 * @param field
	 * @param classType
	 * @return
	 */
	public boolean delSet(Object key,Object value,Class<?> classType){
		String types=classType.getName();
		boolean flag = false;
		if(key==null){
			logger.error(RedisMessage.REDIS_ERROR_001);
			return flag;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
	  
		try {
			if (STRINGTYPE.equals(types)) {
				jedis.srem( (String)key, (String)value);
			}else{
				jedis.srem( RedisUtils.object2Bytes(key), RedisUtils.object2Bytes(value));
			}
			logger.debug(RedisMessage.REDISMESSAGE_001+"delSet");
		return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}
	
	/** 查看 是否存在SET 的值
	 * REDIS(SISMEMBER) 
	 * @param key 
	 * @param value
	 * @param classType
	 * @return
	 */
	public boolean exisSet(Object key,Object value,Class<?> classType){
		String types=classType.getName();	
		boolean flag = false;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return flag;
		}
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());

		try {
			if (STRINGTYPE.equals(types)) {
				flag = jedis.sismember((String) key, (String) value);
			} else {
				flag = jedis.sismember(RedisUtils.object2Bytes(key),
						RedisUtils.object2Bytes(value));
				logger.debug(RedisMessage.REDISMESSAGE_001+"exisSet");
				return flag;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return flag;
	}
	
	
	/** 获得指定SET key的集合长度
	 * REDIS(SCARD) 
	 * @param key 
	 * @param classType
	 * @return
	 */
	public long getSetLen(Object key,Object field,Class<?> classType){
		String types=classType.getName();	
       long len=0;
	 
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return len;
		}
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());

		try {
			if (STRINGTYPE.equals(types)) {
				len = jedis.scard((String) key);
			} else {
				len = jedis.scard(RedisUtils.object2Bytes(key));
				logger.debug(RedisMessage.REDISMESSAGE_001+"getSetLen");
				return len;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return len;
	}
	
	/** 通过key（主键） 获得SET的集合
	 *  注意 ：classType不为String时 需做二进制转换
	 *  REDIS(HGET) 
	 * @param key 
	 * @param classType
	 * @return
	 */
	public Set<?> getSetAll(Object key,Class<?> classType){
		String types=classType.getName();	
	 
		Set<?> rs = null;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return null;
		}
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		try {
			if (STRINGTYPE.equals(types)) {
				rs = jedis.smembers((String) key);
			} else {
				rs = jedis.smembers(RedisUtils.object2Bytes(key));
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "getSetAll");
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return null;
	}
	
	/** 随机删除 SET中的一个对象并返回此对象
	 *  注意 ：classType不为String时 需做二进制转换
	 *  REDIS(SPOP) 
	 * @param key 
	 * @return
	 */
	public Object delSetRandom(Object key,Class<?> classType){
		String types=classType.getName();	
		Object rs = null;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return null;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {
			if (STRINGTYPE.equals(types)) {
				rs = jedis.spop((String) key);
			} else {
				rs = jedis.spop(RedisUtils.object2Bytes(key));
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "delSetRandom");
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return null;
	}
	
	/** 随机获取 SET中的一个对象
	 *  注意 ：classType不为String时 需做二进制转换
	 *  REDIS(SRANDMEMBER) 
	 * @param key 
	 * @return
	 */
	public Object getSetRandom(Object key,Class<?> classType){
		String types=classType.getName();	
		Object rs = null;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return null;
		}
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		try {
			if (STRINGTYPE.equals(types)) {
				rs = jedis.srandmember((String) key);
			} else {
				rs = jedis.srandmember(RedisUtils.object2Bytes(key));
			}
			logger.debug(RedisMessage.REDISMESSAGE_001 + "getSetRandom");
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return null;
	}
	
	 
}

	