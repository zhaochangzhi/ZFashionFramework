package org.ZFashionFramework.CommonRedis.client.jedis;

import java.util.List;
import java.util.regex.Pattern;

import org.ZFashionFramework.CommonRedis.client.util.Hashing;
import org.ZFashionFramework.CommonRedis.client.util.Pool;
import org.apache.commons.pool.BasePoolableObjectFactory;
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
public class ShardedJedisPool extends Pool<ShardedJedis> {
    public ShardedJedisPool(final GenericObjectPool.Config poolConfig,
            List<JedisShardInfo> shards) {
        this(poolConfig, shards, Hashing.MURMUR_HASH);
    }

    public ShardedJedisPool(final GenericObjectPool.Config poolConfig,
            List<JedisShardInfo> shards, Hashing algo) {
        this(poolConfig, shards, algo, null);
    }

    public ShardedJedisPool(final GenericObjectPool.Config poolConfig,
            List<JedisShardInfo> shards, Pattern keyTagPattern) {
        this(poolConfig, shards, Hashing.MURMUR_HASH, keyTagPattern);
    }

    public ShardedJedisPool(final GenericObjectPool.Config poolConfig,
            List<JedisShardInfo> shards, Hashing algo, Pattern keyTagPattern) {
        super(poolConfig, new ShardedJedisFactory(shards, algo, keyTagPattern));
    }

    /**
     * PoolableObjectFactory custom impl.
     */
    private static class ShardedJedisFactory extends BasePoolableObjectFactory {
        private List<JedisShardInfo> shards;
        private Hashing algo;
        private Pattern keyTagPattern;

        public ShardedJedisFactory(List<JedisShardInfo> shards, Hashing algo,
                Pattern keyTagPattern) {
            this.shards = shards;
            this.algo = algo;
            this.keyTagPattern = keyTagPattern;
        }

        public Object makeObject() throws Exception {
            ShardedJedis jedis = new ShardedJedis(shards, algo, keyTagPattern);
            return jedis;
        }

        public void destroyObject(final Object obj) throws Exception {
            if ((obj != null) && (obj instanceof ShardedJedis)) {
                ShardedJedis shardedJedis = (ShardedJedis) obj;
                for (Jedis jedis : shardedJedis.getAllShards()) {
                    try {
                   		try {
                   			jedis.quit();
                        } catch (Exception e) {

                        }
                        jedis.disconnect();
                    } catch (Exception e) {

                    }
                }
            }
        }

        public boolean validateObject(final Object obj) {
        	try {
                ShardedJedis jedis = (ShardedJedis) obj;
                for (Jedis shard : jedis.getAllShards()) {
                    if (!shard.ping().equals("PONG")) {
                        return false;
                    }
                }
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }
}