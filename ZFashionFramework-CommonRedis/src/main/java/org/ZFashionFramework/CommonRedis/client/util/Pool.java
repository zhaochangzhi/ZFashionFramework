package org.ZFashionFramework.CommonRedis.client.util;

import java.util.Map;

import org.ZFashionFramework.CommonRedis.client.jedis.ShardedJedis;
import org.ZFashionFramework.CommonRedis.client.jedis.ShardedJedisPool;
import org.ZFashionFramework.CommonRedis.client.jedis.exceptions.JedisConnectionException;
import org.ZFashionFramework.CommonRedis.client.jedis.exceptions.JedisException;
import org.ZFashionFramework.CommonRedis.redis.constant.ConstantData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
/**   
* @标题: REDISJAR包中文件未做改动
* @编写人： 韩峥
* @创建日期： 2012-8-2 下午4:30:49
* @修改人： 
* @修改日期： 2012-8-2 下午4:30:49
* @版本：    
* @描述: TODO
*/
public abstract class Pool<T> {
    protected final GenericObjectPool internalPool;

    public Pool(final GenericObjectPool.Config poolConfig,
            PoolableObjectFactory factory) {
        this.internalPool = new GenericObjectPool(factory, poolConfig);
    }

    @SuppressWarnings("unchecked")
    public T getResource(String poolName) {
    	Pool<ShardedJedis> pool =  (Pool<ShardedJedis>) this;
    	while(pool != null){
    		try {
				Object resource = pool.internalPool.borrowObject();
			//如果是选择数据库 去查询选择的DB数据库
				Integer db=ConstantData.REDISDB.get(poolName);
				if( db!=null ){
					ShardedJedis dbselect=(ShardedJedis)resource;
					dbselect.select(db);
				}
				
				setActive((ShardedJedisPool) pool, true);
				return (T) resource;
    		} catch (Exception e) {
    			//TODO 获取连接异常 切换服务器
    			setActive((ShardedJedisPool) pool, false);
    			pool = getActivePool((ShardedJedisPool) this);
    			
    			if(pool != null){
    				log.warn("redis 服务器异常，切换服务器到" + pool);
    			}else{
    				log.warn("redis 服务器异常，没有可用的服务器");
    				throw new JedisConnectionException(
    						"Could not get a resource from the pool", e);
    			}
    		}
    	}
    	throw new JedisConnectionException(
				"Could not get a resource from the pool");
    }

    private final static Log log = LogFactory.getLog("pool");

    public void returnResource(final T resource) {
        try {
            internalPool.returnObject(resource);
        } catch (Exception e) {
            throw new JedisException(
                    "Could not return the resource to the pool", e);
        }
    }

    public void returnBrokenResource(final T resource) {
        try {
            internalPool.invalidateObject(resource);
        } catch (Exception e) {
            throw new JedisException(
                    "Could not return the resource to the pool", e);
        }
    }

    public void destroy() {
        try {
            internalPool.close();
        } catch (Exception e) {
            throw new JedisException("Could not destroy the pool", e);
        }
    }
    
	/**
	 * 当一个连接池不可用时再取出一个可用的 获取除指定连接池外的可用连接池
	 * 
	 * @param pool 指定连接池
	 * @return 可用连接池，没有的话返回null
	 */
	public static ShardedJedisPool getActivePool(ShardedJedisPool pool) {
		Map<ShardedJedisPool, Boolean> pools=getActiveMap(pool);
		for (Map.Entry<ShardedJedisPool, Boolean> item : pools.entrySet()) {
			if (!item.getKey().equals(pool) && item.getValue()) {
				return item.getKey();
			}
		}
		return null;
	}
	
	/**
	 * @param pool
	 */
	public static void setActive(ShardedJedisPool pool,boolean flag) {
		Map<ShardedJedisPool, Boolean> pools=getActiveMap(pool);
		if(pools!=null&&pools.size()>0){
			if(pools.get(pool)!=null){
				if(!pools.get(pool) == flag){
					pools.put(pool, flag);
				}
			}
			
		}
		
	}
    /**获取shard所属连接池
     * @param pool
     */
    public static Map<ShardedJedisPool, Boolean> getActiveMap(ShardedJedisPool pool){
    	//Map<ShardedJedisPool, Boolean> pools
    	for(Map<ShardedJedisPool, Boolean>  pools:ConstantData.SHARDEDJEDISPOOLBOOLEAN){
    		if(pools.containsKey(pool)){
    			return pools;
    		}
    	}
    	return null ;
    }
    
  
}