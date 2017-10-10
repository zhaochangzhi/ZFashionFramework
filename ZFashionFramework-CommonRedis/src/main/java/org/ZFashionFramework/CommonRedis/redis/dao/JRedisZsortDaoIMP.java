package org.ZFashionFramework.CommonRedis.redis.dao;


import java.util.Set;

import org.ZFashionFramework.CommonRedis.client.jedis.ShardedJedis;
import org.ZFashionFramework.CommonRedis.redis.utils.RedisMessage;
import org.ZFashionFramework.CommonRedis.redis.utils.RedisUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * REDIS ZSORT(有序集合)处理类
 * 
 * @author hanzheng
 * 
 */

public class JRedisZsortDaoIMP   extends BaseRedisDao implements IJRedisZsortDao{
    private final int GLOBALSTART=0;
    private final int GLOBALEND=-1;

	private static final String STRINGTYPE = "java.lang.String";
	private static final Log logger = LogFactory.getLog(JRedisZsortDaoIMP.class);
	public JRedisZsortDaoIMP(String poolName ){
		super.setPoolName(poolName);
	}
	public JRedisZsortDaoIMP(){
	 
	}

	/**保存一个ZSORT对象
	 * REDIS(ZADD)
	 * @param key 
	 * @param score double类型
	 * @param classType
	 * @return 
	 */
	public boolean setZsort(String key, double score, Object member,
			Class<?> classType) {
		return setZsort(key, score, member,0, classType);
	}
	
	/** 保存一个对象 (设置保存的时常 单位为妙)
	 * REDIS(ZADD)
	 * @param key
	 * @param score
	 * @param classType
	 * @return
	 */
	public boolean setZsort(String key, double score, Object member,
			int outTime, Class<?> classType) {
		String types=classType.getName();	
		boolean flag = false;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return flag;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());

		try {
			if (STRINGTYPE.equals(types)) {
				jedis.zadd((String) key, score, (String) member);
				
			} else {
				jedis.zadd(RedisUtils.object2Bytes(key), score,
						RedisUtils.object2Bytes(member));
			}
			RedisUtils.setExpire(jedis, key, types, outTime);
			logger.debug(RedisMessage.REDISMESSAGE_001+"setZsort");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}

	/** 批量保存ZSORT对象
	 * 对象长度应小于1000
	 *  REDIS(ZADD)
	 * @param key 
	 * @param scores double[]类型
	 * @param classType
	 * @return
	 */
	public boolean setZsorts(String key, double[] scores, Object[] member,
			Class<?> classType) {
		return setZsorts(key, scores, member,0, classType);
	}
	
	/**批量保存ZSORT对象（ 单位为妙)
	 * 对象长度应小于1000
	 *  REDIS(ZADD)
	 * @param key
	 * @param scores double[]类型
	 * @param classType
	 * @return
	 */
	public boolean setZsorts(String key, double[] scores, Object[] members,
			int outTime, Class<?> classType) {
		String types=classType.getName();	
		boolean flag = false;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return flag;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {
			int i=0;
		 for(double score:scores){
				if (STRINGTYPE.equals(types)) {
					jedis.zadd((String) key, score, (String) members[i]);
					
				} else {
						jedis.zadd(RedisUtils.object2Bytes(key), score,
								RedisUtils.object2Bytes(members[i]));
				}	
				RedisUtils.setExpire(jedis, key, types, outTime);
				i=i+1;
		  }
			logger.debug(RedisMessage.REDISMESSAGE_001+"setZsorts");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}
	
	/** 通过member域移除ZSORT中对象
	 * REDIS（ZREM）
	 * @param key
	 * @param classType
	 * @return
	 */
	public boolean remZSortByMember(String key,Object member,Class<?> classType) {
		boolean flag = false;
		String types=classType.getName();	
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return flag;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {
			if(STRINGTYPE.equals(types)){
				jedis.zrem((String)key, (String)member);
			}else{
				jedis.zrem(RedisUtils.object2Bytes(key),RedisUtils.object2Bytes(member));
			}
			logger.debug(RedisMessage.REDISMESSAGE_001+"remZSortByMember");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}
	
	/** 删除ZSORT指定KEY的所有对象
	 * REDIS（ZREMRANGEBYRANK） 
	 * @param key
	 * @return
	 */
	public boolean remZSort(String key,Class<?> classType) {
		return remZSortByIndex(key,GLOBALSTART,GLOBALEND,classType);
	}
	
	/** 删除ZSORT指定域区间内的对象
	 *  REDIS（ZREMRANGEBYRANK） 
	 * @param key
	 * @return
	 */
	public boolean remZSortByIndex(String key,int start,int end,Class<?> classType) {
		boolean flag = false;
		String types=classType.getName();	
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return flag;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {
			if(STRINGTYPE.equals(types)){
				jedis.zremrangeByRank((String)key,start,end);
				
			}else{
				jedis.zremrangeByRank(RedisUtils.object2Bytes(key),start,end);
			}
			logger.debug(RedisMessage.REDISMESSAGE_001+"remZSortByIndex");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}
	
	/** 删除ZSORT的域取值范围内的对象
	 * REDIS（ZREMRANGEBYSCORE） 
	 * @param key
	 * @return
	 */
	public boolean remZSortByScore(String key,int min,int max,Class<?> classType) {
		boolean flag = false;
		String types=classType.getName();	
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
			return flag;
		}
		ShardedJedis jedis = getShardePool().getResource(super.getPoolName());
		try {
			if(STRINGTYPE.equals(types)){
				jedis.zremrangeByScore((String)key, min, max);
			}else{
				jedis.zremrangeByScore(RedisUtils.object2Bytes(key),min,max);
			}
			logger.debug(RedisMessage.REDISMESSAGE_001+"remZSortByScore");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool().returnResource(jedis);
		}
		return flag;
	}
	
	/** 获得ZSORT的对象总数
	 * REDIS（ZCARD） 
	 * @param key
	 * @param classType
	 * @return
	 */
	public long  getZSortLen(Object key,Class<?> classType){
		long count=0;
		String types=classType.getName();	
		
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
		 return 0;
		}
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		try {
			if(STRINGTYPE.equals(types)){
				count= jedis.zcard((String)key);
			}else{
				count= jedis.zcard(RedisUtils.object2Bytes(key));
			}
			logger.debug(RedisMessage.REDISMESSAGE_001+"getZSortLen");
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return count;
	}
	
	/** 获得ZSORT的所有MEMBER（正序）
	 * MEMBER数据量大时慎用
	 * REDIS（ZRANGE） 
	 * @param key
	 * @param classType
	 * @return
	 */
	public Set<?> rangeAllZSort(Object key,Class<?> classType){
		return rangeZSort(key,0,-1,classType);
	}
	
	/** 获得ZSORT中 所有MEMBER（正序）
	 * MEMBER数据量大时慎用
	 * REDIS（ZRANGE） 
	 * @param key
	 * @param classType
	 * @return
	 */
	public Set<?> rangeZSort(Object key,int start,int end,Class<?> classType){
		String types=classType.getName();	
		Set<?> set=null;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
		 
		}
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		try {
			if(STRINGTYPE.equals(types)){
				 set=jedis.zrange((String)key, start, end);
			}else{
				set=jedis.zrange(RedisUtils.object2Bytes(key),start, end);
			}
			logger.debug(RedisMessage.REDISMESSAGE_001+"rangeZSort");
			return set;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return set;
	}
	
	/**通过ZSORT的域取值范围获得MEMBER对象(正序)
	 * REDIS（ZRANGE） 
	 * @param key
	 * @param min
	 * @param max
	 * @param classType
	 * @return
	 */
	public Set<?> rangeZSortByScore(Object key,double min,double max,Class<?> classType){
		String types=classType.getName();	
		Set<?> set=null;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
		}
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		try {
			if(STRINGTYPE.equals(types)){	
				 set=jedis.zrangeByScore((String)key, min, max);
			}else{
				set=jedis.zrangeByScore(RedisUtils.object2Bytes(key), min, max);
			}
			logger.debug(RedisMessage.REDISMESSAGE_001+"rangeZSortByScore");
			return set;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return set;
	}
	
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
	public Set<?> rangeZSortByScore(Object key,double min,double max,int offset,int count,Class<?> classType){
		String types=classType.getName();	
		Set<?> set=null;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
		}
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		try {
			if(STRINGTYPE.equals(types)){	
				 set=jedis.zrangeByScore((String)key, min, max, offset, count);
			}else{
				set=jedis.zrangeByScore(RedisUtils.object2Bytes(key), min, max, offset, count);
			}
			logger.debug(RedisMessage.REDISMESSAGE_001+"rangeZSortByScore");
			return set;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return set;
	}
	 
	/**获得KEY 的所有对象倒叙排列
	 * MEMBER数据量大时慎用
	 * REDIS（ZREVRANGE） 
	 * @param key
	 * @param classType
	 * @return
	 */
	public Set<?> rangeDescAllZSort(Object key,Class<?> classType){
		return   rangeDescZSort( key,GLOBALSTART,GLOBALEND, classType);
	}
	
	/**获得KEY 的所有对象（倒序）
	 * REDIS（ZREVRANGE） 
	 * zrevrange
	 * @param key
	 * @param start
	 * @param end
	 * @param classType
	 * @return
	 */
	public Set<?> rangeDescZSort(Object key,int start,int end,Class<?> classType){
		String types=classType.getName();	
		Set<?> set=null;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
		}
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		try {
			if(STRINGTYPE.equals(types)){
				 set=jedis.zrevrange((String)key, start, end);
			}else{
				set=jedis.zrevrange(RedisUtils.object2Bytes(key),start, end);
			}
			logger.debug(RedisMessage.REDISMESSAGE_001+"rangeDescZSort");
			return set;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return set;
	}
	
	/**通过score的值范围获得对象 (倒序)
	 * REDIS（ZREVRANGEBYSCORE） 
	 * @param key  
	 * @param max   这是最大值  （接口如此 不换序了）
	 * @param min   这是最小值    
	 * @param classType
	 * @return
	 */
	public Set<?> rangeDescZSortByScore(Object key,double max,double min,Class<?> classType){
		String types=classType.getName();	
		Set<?> set=null;
		if (key == null) {
			logger.error(RedisMessage.REDIS_ERROR_001);
		}
		ShardedJedis jedis = getShardePool4Read().getResource(super.getPoolName());
		try {
			if(STRINGTYPE.equals(types)){
				 set=jedis.zrevrangeByScore((String)key,  max, min);
			}else{
				set=jedis.zrevrangeByScore(RedisUtils.object2Bytes(key),max, min);
			}
			logger.debug(RedisMessage.REDISMESSAGE_001+"rangeDescZSortByScore");
			return set;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.getShardePool4Read().returnResource(jedis);
		}
		return set;
	}
	
	 
}
