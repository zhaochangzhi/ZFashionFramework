package org.ZFashionFramework.CommonRedis.redis.dao;

import org.ZFashionFramework.CommonRedis.client.jedis.ShardedJedisPool;
import org.ZFashionFramework.CommonRedis.redis.constant.ConstantData;



/**
 * @标题: BaseRedisDao.java
 * @包名： cn.com.carapp.redis.dao
 * @编写人： 韩峥
 * @创建日期： 2012-8-2 上午11:48:53
 * @修改人：
 * @修改日期： 2012-8-2 上午11:48:53
 * @版本：
 * @描述: 基本REDISf方法
 */
public class BaseRedisDao {
	protected ShardedJedisPool shardedJedisPool;
	private String poolName;
	public String getPoolName() {
		if(poolName==null &&ConstantData.SHARDEDJEDISPOOLNAME.size()>0){
			poolName=ConstantData.SHARDEDJEDISPOOLNAME.get(0);
		}
		return poolName;
	}

	public void setPoolName(String poolName) {
		
		this.poolName = poolName;
	}

	public static String getStringtype() {
		return STRINGTYPE;
	}

	protected Integer db ;
	public Integer getDb() {
		return db;
	}

	public void setDb(Integer db) {
		this.db = db;
	}

	public ShardedJedisPool getShardedJedisPool() {
		return this.shardedJedisPool;
	}

	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	// 常量类型定义
	public static final String STRINGTYPE = "java.lang.String";

	
	/**
	 * 获得REDIS连接池的方法
	 * 
	 * @return 连接池
	 */
	public ShardedJedisPool getShardePool() {
		//非SPRING方式连接带主从分离
		if(shardedJedisPool==null){
			return ConstantData.SHARDEDJEDISPOOLMAP.get(this.getPoolName());
		}else{
			return shardedJedisPool;
		}
	}
	
	/**
	 * 获得REDIS只读连接池的方法
	 * 
	 * @return 连接池
	 */
	public ShardedJedisPool getShardePool4Read() {
		//非SPRING方式连接带主从分离
		if(shardedJedisPool==null){
			return ConstantData.SHARDEDJEDISPOOLMAP.get(this.getPoolName().concat(ConstantData._READ));
		
		}else{
			return shardedJedisPool;
		}
	}
	
}
