package org.ZFashionFramework.CommonRedis.client.jedis;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.ZFashionFramework.CommonRedis.client.jedis.BinaryClient.LIST_POSITION;
import org.ZFashionFramework.CommonRedis.client.util.Hashing;

/**
 * @标题: REDISJAR包中文件未做改动
 * @编写人： 韩峥
 * @创建日期： 2012-8-2 下午4:30:49
 * @修改人：
 * @修改日期： 2012-8-2 下午4:30:49
 * @版本：
 * @描述: TODO
 */
public class ShardedJedis extends BinaryShardedJedis implements JedisCommands {
	public ShardedJedis(List<JedisShardInfo> shards) {
		super(shards);
	}

	public ShardedJedis(List<JedisShardInfo> shards, Hashing algo) {
		super(shards, algo);
	}

	public ShardedJedis(List<JedisShardInfo> shards, Pattern keyTagPattern) {
		super(shards, keyTagPattern);
	}

	public ShardedJedis(List<JedisShardInfo> shards, Hashing algo,
			Pattern keyTagPattern) {
		super(shards, algo, keyTagPattern);
	}

	public void disconnect() {
		for (Jedis jedis : getAllShards()) {
			jedis.quit();
			jedis.disconnect();
		}
	}

	public String set(String key, String value) {
		Jedis j = getShard(key);
		return j.set(key, value);
	}

	public void select(int a) {
		Jedis j = getShard("");
		j.select(a);
	}

	public String get(String key) {
		Jedis j = getShard(key);
		return j.get(key);
	}

	public Boolean exists(String key) {
		Jedis j = getShard(key);

		return j.exists(key);
	}

	public String type(String key) {
		Jedis j = getShard(key);
		return j.type(key);
	}

	public Long expire(String key, int seconds) {
		Jedis j = getShard(key);
		return j.expire(key, seconds);
	}

	public Long expireAt(String key, long unixTime) {
		Jedis j = getShard(key);
		return j.expireAt(key, unixTime);
	}

	public Long ttl(String key) {
		Jedis j = getShard(key);
		return j.ttl(key);
	}

	public Set<String> keys(String key) {
		Jedis j = getShard(key);
		return j.keys(key);
	}

	public Boolean setbit(String key, long offset, boolean value) {
		Jedis j = getShard(key);
		return j.setbit(key, offset, value);
	}

	public Boolean getbit(String key, long offset) {
		Jedis j = getShard(key);
		return j.getbit(key, offset);
	}

	public Long setrange(String key, long offset, String value) {
		Jedis j = getShard(key);
		return j.setrange(key, offset, value);
	}

	public String getrange(String key, long startOffset, long endOffset) {
		Jedis j = getShard(key);
		return j.getrange(key, startOffset, endOffset);
	}

	public String getSet(String key, String value) {
		Jedis j = getShard(key);
		return j.getSet(key, value);
	}

	public Long setnx(String key, String value) {
		Jedis j = getShard(key);
		return j.setnx(key, value);
	}

	public String setex(String key, int seconds, String value) {
		Jedis j = getShard(key);
		return j.setex(key, seconds, value);
	}

	public Long decrBy(String key, long integer) {
		Jedis j = getShard(key);
		return j.decrBy(key, integer);
	}

	public Long decr(String key) {
		Jedis j = getShard(key);
		return j.decr(key);
	}

	public Long incrBy(String key, long integer) {
		Jedis j = getShard(key);
		return j.incrBy(key, integer);
	}

	public Long incr(String key) {
		Jedis j = getShard(key);
		return j.incr(key);
	}

	public Long append(String key, String value) {
		Jedis j = getShard(key);
		return j.append(key, value);
	}

	public String substr(String key, int start, int end) {
		Jedis j = getShard(key);
		return j.substr(key, start, end);
	}

	public Long hset(String key, String field, String value) {
		Jedis j = getShard(key);
		return j.hset(key, field, value);
	}

	public String hget(String key, String field) {
		Jedis j = getShard(key);
		return j.hget(key, field);
	}

	public Long hsetnx(String key, String field, String value) {
		Jedis j = getShard(key);
		return j.hsetnx(key, field, value);
	}

	public String hmset(String key, Map<String, String> hash) {
		Jedis j = getShard(key);
		return j.hmset(key, hash);
	}

	public List<String> hmget(String key, String... fields) {
		Jedis j = getShard(key);
		return j.hmget(key, fields);
	}

	public Long hincrBy(String key, String field, long value) {
		Jedis j = getShard(key);
		return j.hincrBy(key, field, value);
	}

	public Boolean hexists(String key, String field) {
		Jedis j = getShard(key);
		return j.hexists(key, field);
	}

	public Long del(String key) {
		Jedis j = getShard(key);
		return j.del(key);
	}

	public Long hdel(String key, String field) {
		Jedis j = getShard(key);
		return j.hdel(key, field);
	}

	public Long hlen(String key) {
		Jedis j = getShard(key);
		return j.hlen(key);
	}

	public Set<String> hkeys(String key) {
		Jedis j = getShard(key);
		return j.hkeys(key);
	}

	public List<String> hvals(String key) {
		Jedis j = getShard(key);
		return j.hvals(key);
	}

	public Map<String, String> hgetAll(String key) {
		Jedis j = getShard(key);
		return j.hgetAll(key);
	}

	public Long rpush(String key, String string) {
		Jedis j = getShard(key);
		return j.rpush(key, string);
	}

	public Long lpush(String key, String string) {
		Jedis j = getShard(key);
		return j.lpush(key, string);
	}

	public Long llen(String key) {
		Jedis j = getShard(key);
		return j.llen(key);
	}

	public List<String> lrange(String key, long start, long end) {
		Jedis j = getShard(key);
		return j.lrange(key, start, end);
	}

	public String ltrim(String key, long start, long end) {
		Jedis j = getShard(key);
		return j.ltrim(key, start, end);
	}

	public String lindex(String key, long index) {
		Jedis j = getShard(key);
		return j.lindex(key, index);
	}

	public String lset(String key, long index, String value) {
		Jedis j = getShard(key);
		return j.lset(key, index, value);
	}

	public Long lrem(String key, long count, String value) {
		Jedis j = getShard(key);
		return j.lrem(key, count, value);
	}

	public String lpop(String key) {
		Jedis j = getShard(key);
		return j.lpop(key);
	}

	public String rpop(String key) {
		Jedis j = getShard(key);
		return j.rpop(key);
	}

	public Long sadd(String key, String member) {
		Jedis j = getShard(key);
		return j.sadd(key, member);
	}

	public Set<String> smembers(String key) {
		Jedis j = getShard(key);
		return j.smembers(key);
	}

	public Long srem(String key, String member) {
		Jedis j = getShard(key);
		return j.srem(key, member);
	}

	public String spop(String key) {
		Jedis j = getShard(key);
		return j.spop(key);
	}

	public Long scard(String key) {
		Jedis j = getShard(key);
		return j.scard(key);
	}

	public Boolean sismember(String key, String member) {
		Jedis j = getShard(key);
		return j.sismember(key, member);
	}

	public String srandmember(String key) {
		Jedis j = getShard(key);
		return j.srandmember(key);
	}

	public Long zadd(String key, double score, String member) {
		Jedis j = getShard(key);
		return j.zadd(key, score, member);
	}

	public Set<String> zrange(String key, int start, int end) {
		Jedis j = getShard(key);
		return j.zrange(key, start, end);
	}

	public Long zrem(String key, String member) {
		Jedis j = getShard(key);
		return j.zrem(key, member);
	}

	public Double zincrby(String key, double score, String member) {
		Jedis j = getShard(key);
		return j.zincrby(key, score, member);
	}

	public Long zrank(String key, String member) {
		Jedis j = getShard(key);
		return j.zrank(key, member);
	}

	public Long zrevrank(String key, String member) {
		Jedis j = getShard(key);
		return j.zrevrank(key, member);
	}

	public Set<String> zrevrange(String key, int start, int end) {
		Jedis j = getShard(key);
		return j.zrevrange(key, start, end);
	}

	public Set<Tuple> zrangeWithScores(String key, int start, int end) {
		Jedis j = getShard(key);
		return j.zrangeWithScores(key, start, end);
	}

	public Set<Tuple> zrevrangeWithScores(String key, int start, int end) {
		Jedis j = getShard(key);
		return j.zrevrangeWithScores(key, start, end);
	}

	public Long zcard(String key) {
		Jedis j = getShard(key);
		return j.zcard(key);
	}

	public Double zscore(String key, String member) {
		Jedis j = getShard(key);
		return j.zscore(key, member);
	}

	public List<String> sort(String key) {
		Jedis j = getShard(key);
		return j.sort(key);
	}

	public List<String> sort(String key, SortingParams sortingParameters) {
		Jedis j = getShard(key);
		return j.sort(key, sortingParameters);
	}

	public Long zcount(String key, double min, double max) {
		Jedis j = getShard(key);
		return j.zcount(key, min, max);
	}

	public Set<String> zrangeByScore(String key, double min, double max) {
		Jedis j = getShard(key);
		return j.zrangeByScore(key, min, max);
	}

	public Set<String> zrevrangeByScore(String key, double max, double min) {
		Jedis j = getShard(key);
		return j.zrevrangeByScore(key, max, min);
	}

	public Set<String> zrangeByScore(String key, double min, double max,
			int offset, int count) {
		Jedis j = getShard(key);
		return j.zrangeByScore(key, min, max, offset, count);
	}

	public Set<String> zrevrangeByScore(String key, double max, double min,
			int offset, int count) {
		Jedis j = getShard(key);
		return j.zrevrangeByScore(key, max, min, offset, count);
	}

	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
		Jedis j = getShard(key);
		return j.zrangeByScoreWithScores(key, min, max);
	}

	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max,
			double min) {
		Jedis j = getShard(key);
		return j.zrevrangeByScoreWithScores(key, max, min);
	}

	public Set<Tuple> zrangeByScoreWithScores(String key, double min,
			double max, int offset, int count) {
		Jedis j = getShard(key);
		return j.zrangeByScoreWithScores(key, min, max, offset, count);
	}

	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max,
			double min, int offset, int count) {
		Jedis j = getShard(key);
		return j.zrevrangeByScoreWithScores(key, max, min, offset, count);
	}

	public Long zremrangeByRank(String key, int start, int end) {
		Jedis j = getShard(key);
		return j.zremrangeByRank(key, start, end);
	}

	public Long zremrangeByScore(String key, double start, double end) {
		Jedis j = getShard(key);
		return j.zremrangeByScore(key, start, end);
	}

	public Long linsert(String key, LIST_POSITION where, String pivot,
			String value) {
		Jedis j = getShard(key);
		return j.linsert(key, where, pivot, value);
	}
}
