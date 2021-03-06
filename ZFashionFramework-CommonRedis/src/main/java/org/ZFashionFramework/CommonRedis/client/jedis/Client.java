package org.ZFashionFramework.CommonRedis.client.jedis;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static org.ZFashionFramework.CommonRedis.client.jedis.Protocol.toByteArray;
import org.ZFashionFramework.CommonRedis.client.util.SafeEncoder;

/**   
* @标题: REDISJAR包中文件未做改动
* @编写人： 韩峥
* @创建日期： 2012-8-2 下午4:30:49
* @修改人： 
* @修改日期： 2012-8-2 下午4:30:49
* @版本：    
* @描述: TODO
*/
public class Client extends BinaryClient implements Commands {
    public Client(final String host) {
        super(host);
    }

    public Client(final String host, final int port) {
        super(host, port);
    }

    public void set(final String key, final String value) {
        set(SafeEncoder.encode(key), SafeEncoder.encode(value));
    }

    public void get(final String key) {
        get(SafeEncoder.encode(key));
    }

    public void exists(final String key) {
        exists(SafeEncoder.encode(key));
    }

    public void del(final String... keys) {
        final byte[][] bkeys = new byte[keys.length][];
        for (int i = 0; i < keys.length; i++) {
            bkeys[i] = SafeEncoder.encode(keys[i]);
        }
        del(bkeys);
    }

    public void type(final String key) {
        type(SafeEncoder.encode(key));
    }

    public void keys(final String pattern) {
        keys(SafeEncoder.encode(pattern));
    }

    public void rename(final String oldkey, final String newkey) {
        rename(SafeEncoder.encode(oldkey), SafeEncoder.encode(newkey));
    }

    public void renamenx(final String oldkey, final String newkey) {
        renamenx(SafeEncoder.encode(oldkey), SafeEncoder.encode(newkey));
    }

    public void expire(final String key, final int seconds) {
        expire(SafeEncoder.encode(key), seconds);
    }

    public void expireAt(final String key, final long unixTime) {
        expireAt(SafeEncoder.encode(key), unixTime);
    }

    public void ttl(final String key) {
        ttl(SafeEncoder.encode(key));
    }

    public void move(final String key, final int dbIndex) {
        move(SafeEncoder.encode(key), dbIndex);
    }

    public void getSet(final String key, final String value) {
        getSet(SafeEncoder.encode(key), SafeEncoder.encode(value));
    }

    public void mget(final String... keys) {
        final byte[][] bkeys = new byte[keys.length][];
        for (int i = 0; i < bkeys.length; i++) {
            bkeys[i] = SafeEncoder.encode(keys[i]);
        }
        mget(bkeys);
    }

    public void setnx(final String key, final String value) {
        setnx(SafeEncoder.encode(key), SafeEncoder.encode(value));
    }

    public void setex(final String key, final int seconds, final String value) {
        setex(SafeEncoder.encode(key), seconds, SafeEncoder.encode(value));
    }

    public void mset(final String... keysvalues) {
        final byte[][] bkeysvalues = new byte[keysvalues.length][];
        for (int i = 0; i < keysvalues.length; i++) {
            bkeysvalues[i] = SafeEncoder.encode(keysvalues[i]);
        }
        mset(bkeysvalues);
    }

    public void msetnx(final String... keysvalues) {
        final byte[][] bkeysvalues = new byte[keysvalues.length][];
        for (int i = 0; i < keysvalues.length; i++) {
            bkeysvalues[i] = SafeEncoder.encode(keysvalues[i]);
        }
        msetnx(bkeysvalues);
    }

    public void decrBy(final String key, final long integer) {
        decrBy(SafeEncoder.encode(key), integer);
    }

    public void decr(final String key) {
        decr(SafeEncoder.encode(key));
    }

    public void incrBy(final String key, final long integer) {
        incrBy(SafeEncoder.encode(key), integer);
    }

    public void incr(final String key) {
        incr(SafeEncoder.encode(key));
    }

    public void append(final String key, final String value) {
        append(SafeEncoder.encode(key), SafeEncoder.encode(value));
    }

    public void substr(final String key, final int start, final int end) {
        substr(SafeEncoder.encode(key), start, end);
    }

    public void hset(final String key, final String field, final String value) {
        hset(SafeEncoder.encode(key), SafeEncoder.encode(field), SafeEncoder
                .encode(value));
    }

    public void hget(final String key, final String field) {
        hget(SafeEncoder.encode(key), SafeEncoder.encode(field));
    }

    public void hsetnx(final String key, final String field, final String value) {
        hsetnx(SafeEncoder.encode(key), SafeEncoder.encode(field), SafeEncoder
                .encode(value));
    }

    public void hmset(final String key, final Map<String, String> hash) {
        final Map<byte[], byte[]> bhash = new HashMap<byte[], byte[]>(hash
                .size());
        for (final Entry<String, String> entry : hash.entrySet()) {
            bhash.put(SafeEncoder.encode(entry.getKey()), SafeEncoder
                    .encode(entry.getValue()));
        }
        hmset(SafeEncoder.encode(key), bhash);
    }

    public void hmget(final String key, final String... fields) {
        final byte[][] bfields = new byte[fields.length][];
        for (int i = 0; i < bfields.length; i++) {
            bfields[i] = SafeEncoder.encode(fields[i]);
        }
        hmget(SafeEncoder.encode(key), bfields);
    }

    public void hincrBy(final String key, final String field, final long value) {
        hincrBy(SafeEncoder.encode(key), SafeEncoder.encode(field), value);
    }

    public void hexists(final String key, final String field) {
        hexists(SafeEncoder.encode(key), SafeEncoder.encode(field));
    }

    public void hdel(final String key, final String field) {
        hdel(SafeEncoder.encode(key), SafeEncoder.encode(field));
    }

    public void hlen(final String key) {
        hlen(SafeEncoder.encode(key));
    }

    public void hkeys(final String key) {
        hkeys(SafeEncoder.encode(key));
    }

    public void hvals(final String key) {
        hvals(SafeEncoder.encode(key));
    }

    public void hgetAll(final String key) {
        hgetAll(SafeEncoder.encode(key));
    }

    public void rpush(final String key, final String string) {
        rpush(SafeEncoder.encode(key), SafeEncoder.encode(string));
    }

    public void lpush(final String key, final String string) {
        lpush(SafeEncoder.encode(key), SafeEncoder.encode(string));
    }

    public void llen(final String key) {
        llen(SafeEncoder.encode(key));
    }

    public void lrange(final String key, final long start, final long end) {
        lrange(SafeEncoder.encode(key), start, end);
    }

    public void ltrim(final String key, final long start, final long end) {
        ltrim(SafeEncoder.encode(key), start, end);
    }

    public void lindex(final String key, final long index) {
        lindex(SafeEncoder.encode(key), index);
    }

    public void lset(final String key, final long index, final String value) {
        lset(SafeEncoder.encode(key), index, SafeEncoder.encode(value));
    }

    public void lrem(final String key, long count, final String value) {
        lrem(SafeEncoder.encode(key), count, SafeEncoder.encode(value));
    }

    public void lpop(final String key) {
        lpop(SafeEncoder.encode(key));
    }

    public void rpop(final String key) {
        rpop(SafeEncoder.encode(key));
    }

    public void rpoplpush(final String srckey, final String dstkey) {
        rpoplpush(SafeEncoder.encode(srckey), SafeEncoder.encode(dstkey));
    }

    public void sadd(final String key, final String member) {
        sadd(SafeEncoder.encode(key), SafeEncoder.encode(member));
    }

    public void smembers(final String key) {
        smembers(SafeEncoder.encode(key));
    }

    public void srem(final String key, final String member) {
        srem(SafeEncoder.encode(key), SafeEncoder.encode(member));
    }

    public void spop(final String key) {
        spop(SafeEncoder.encode(key));
    }

    public void smove(final String srckey, final String dstkey,
            final String member) {
        smove(SafeEncoder.encode(srckey), SafeEncoder.encode(dstkey),
                SafeEncoder.encode(member));
    }

    public void scard(final String key) {
        scard(SafeEncoder.encode(key));
    }

    public void sismember(final String key, final String member) {
        sismember(SafeEncoder.encode(key), SafeEncoder.encode(member));
    }

    public void sinter(final String... keys) {
        final byte[][] bkeys = new byte[keys.length][];
        for (int i = 0; i < bkeys.length; i++) {
            bkeys[i] = SafeEncoder.encode(keys[i]);
        }
        sinter(bkeys);
    }

    public void sinterstore(final String dstkey, final String... keys) {
        final byte[][] bkeys = new byte[keys.length][];
        for (int i = 0; i < bkeys.length; i++) {
            bkeys[i] = SafeEncoder.encode(keys[i]);
        }
        sinterstore(SafeEncoder.encode(dstkey), bkeys);
    }

    public void sunion(final String... keys) {
        final byte[][] bkeys = new byte[keys.length][];
        for (int i = 0; i < bkeys.length; i++) {
            bkeys[i] = SafeEncoder.encode(keys[i]);
        }
        sunion(bkeys);
    }

    public void sunionstore(final String dstkey, final String... keys) {
        final byte[][] bkeys = new byte[keys.length][];
        for (int i = 0; i < bkeys.length; i++) {
            bkeys[i] = SafeEncoder.encode(keys[i]);
        }
        sunionstore(SafeEncoder.encode(dstkey), bkeys);
    }

    public void sdiff(final String... keys) {
        final byte[][] bkeys = new byte[keys.length][];
        for (int i = 0; i < bkeys.length; i++) {
            bkeys[i] = SafeEncoder.encode(keys[i]);
        }
        sdiff(bkeys);
    }

    public void sdiffstore(final String dstkey, final String... keys) {
        final byte[][] bkeys = new byte[keys.length][];
        for (int i = 0; i < bkeys.length; i++) {
            bkeys[i] = SafeEncoder.encode(keys[i]);
        }
        sdiffstore(SafeEncoder.encode(dstkey), bkeys);
    }

    public void srandmember(final String key) {
        srandmember(SafeEncoder.encode(key));
    }

    public void zadd(final String key, final double score, final String member) {
        zadd(SafeEncoder.encode(key), score, SafeEncoder.encode(member));
    }

    public void zrange(final String key, final int start, final int end) {
        zrange(SafeEncoder.encode(key), start, end);
    }

    public void zrem(final String key, final String member) {
        zrem(SafeEncoder.encode(key), SafeEncoder.encode(member));
    }

    public void zincrby(final String key, final double score,
            final String member) {
        zincrby(SafeEncoder.encode(key), score, SafeEncoder.encode(member));
    }

    public void zrank(final String key, final String member) {
        zrank(SafeEncoder.encode(key), SafeEncoder.encode(member));
    }

    public void zrevrank(final String key, final String member) {
        zrevrank(SafeEncoder.encode(key), SafeEncoder.encode(member));
    }

    public void zrevrange(final String key, final int start, final int end) {
        zrevrange(SafeEncoder.encode(key), start, end);
    }

    public void zrangeWithScores(final String key, final int start,
            final int end) {
        zrangeWithScores(SafeEncoder.encode(key), start, end);
    }

    public void zrevrangeWithScores(final String key, final int start,
            final int end) {
        zrevrangeWithScores(SafeEncoder.encode(key), start, end);
    }

    public void zcard(final String key) {
        zcard(SafeEncoder.encode(key));
    }

    public void zscore(final String key, final String member) {
        zscore(SafeEncoder.encode(key), SafeEncoder.encode(member));
    }

    public void watch(final String... keys) {
        final byte[][] bargs = new byte[keys.length][];
        for (int i = 0; i < bargs.length; i++) {
            bargs[i] = SafeEncoder.encode(keys[i]);
        }
        watch(bargs);
    }

    public void sort(final String key) {
        sort(SafeEncoder.encode(key));
    }

    public void sort(final String key, final SortingParams sortingParameters) {
        sort(SafeEncoder.encode(key), sortingParameters);
    }

    public void blpop(final String[] args) {
        final byte[][] bargs = new byte[args.length][];
        for (int i = 0; i < bargs.length; i++) {
            bargs[i] = SafeEncoder.encode(args[i]);
        }
        blpop(bargs);
    }

    public void sort(final String key, final SortingParams sortingParameters,
            final String dstkey) {
        sort(SafeEncoder.encode(key), sortingParameters, SafeEncoder
                .encode(dstkey));
    }

    public void sort(final String key, final String dstkey) {
        sort(SafeEncoder.encode(key), SafeEncoder.encode(dstkey));
    }

    public void brpop(final String[] args) {
        final byte[][] bargs = new byte[args.length][];
        for (int i = 0; i < bargs.length; i++) {
            bargs[i] = SafeEncoder.encode(args[i]);
        }
        brpop(bargs);
    }

    public void zcount(final String key, final double min, final double max) {
        zcount(SafeEncoder.encode(key), min, max);
    }

    public void zrangeByScore(final String key, final double min,
            final double max) {
        zrangeByScore(SafeEncoder.encode(key), min, max);
    }

    public void zrangeByScore(final String key, final String min,
            final String max) {
        zrangeByScore(SafeEncoder.encode(key), SafeEncoder.encode(min),
                SafeEncoder.encode(max));
    }

    public void zrangeByScore(final String key, final double min,
            final double max, final int offset, int count) {
        zrangeByScore(SafeEncoder.encode(key), min, max, offset, count);
    }

    public void zrangeByScoreWithScores(final String key, final double min,
            final double max) {
        zrangeByScoreWithScores(SafeEncoder.encode(key), min, max);
    }

    public void zrangeByScoreWithScores(final String key, final double min,
            final double max, final int offset, final int count) {
        zrangeByScoreWithScores(SafeEncoder.encode(key), min, max, offset,
                count);
    }

    public void zrevrangeByScore(final String key, final double max,
            final double min) {
        zrevrangeByScore(SafeEncoder.encode(key), max, min);
    }

    public void zrevrangeByScore(final String key, final String max,
            final String min) {
        zrevrangeByScore(SafeEncoder.encode(key), SafeEncoder.encode(max),
                SafeEncoder.encode(min));
    }

    public void zrevrangeByScore(final String key, final double max,
            final double min, final int offset, int count) {
        zrevrangeByScore(SafeEncoder.encode(key), max, min, offset, count);
    }

    public void zrevrangeByScoreWithScores(final String key, final double max,
            final double min) {
        zrevrangeByScoreWithScores(SafeEncoder.encode(key), max, min);
    }

    public void zrevrangeByScoreWithScores(final String key, final double max,
            final double min, final int offset, final int count) {
        zrevrangeByScoreWithScores(SafeEncoder.encode(key), max, min, offset,
                count);
    }

    public void zremrangeByRank(final String key, final int start, final int end) {
        zremrangeByRank(SafeEncoder.encode(key), start, end);
    }

    public void zremrangeByScore(final String key, final double start,
            final double end) {
        zremrangeByScore(SafeEncoder.encode(key), start, end);
    }

    public void zunionstore(final String dstkey, final String... sets) {
        final byte[][] bsets = new byte[sets.length][];
        for (int i = 0; i < bsets.length; i++) {
            bsets[i] = SafeEncoder.encode(sets[i]);
        }
        zunionstore(SafeEncoder.encode(dstkey), bsets);
    }

    public void zunionstore(final String dstkey, final ZParams params,
            final String... sets) {
        final byte[][] bsets = new byte[sets.length][];
        for (int i = 0; i < bsets.length; i++) {
            bsets[i] = SafeEncoder.encode(sets[i]);
        }
        zunionstore(SafeEncoder.encode(dstkey), params, bsets);
    }

    public void zinterstore(final String dstkey, final String... sets) {
        final byte[][] bsets = new byte[sets.length][];
        for (int i = 0; i < bsets.length; i++) {
            bsets[i] = SafeEncoder.encode(sets[i]);
        }
        zinterstore(SafeEncoder.encode(dstkey), bsets);
    }

    public void zinterstore(final String dstkey, final ZParams params,
            final String... sets) {
        final byte[][] bsets = new byte[sets.length][];
        for (int i = 0; i < bsets.length; i++) {
            bsets[i] = SafeEncoder.encode(sets[i]);
        }
        zinterstore(SafeEncoder.encode(dstkey), params, bsets);
    }

    public void strlen(final String key) {
        strlen(SafeEncoder.encode(key));
    }

    public void lpushx(final String key, final String string) {
        lpushx(SafeEncoder.encode(key), SafeEncoder.encode(string));
    }

    public void persist(final String key) {
        persist(SafeEncoder.encode(key));
    }

    public void rpushx(final String key, final String string) {
        rpushx(SafeEncoder.encode(key), SafeEncoder.encode(string));
    }

    public void echo(final String string) {
        echo(SafeEncoder.encode(string));
    }

    public void linsert(final String key, final LIST_POSITION where,
            final String pivot, final String value) {
        linsert(SafeEncoder.encode(key), where, SafeEncoder.encode(pivot),
                SafeEncoder.encode(value));
    }

    public void brpoplpush(String source, String destination, int timeout) {
        brpoplpush(SafeEncoder.encode(source), SafeEncoder.encode(destination),
                timeout);
    }

    public void setbit(final String key, final long offset, final boolean value) {
        setbit(SafeEncoder.encode(key), offset, toByteArray(value ? 1 : 0));
    }

    public void getbit(String key, long offset) {
        getbit(SafeEncoder.encode(key), offset);
    }

    public void setrange(String key, long offset, String value) {
        setrange(SafeEncoder.encode(key), offset, SafeEncoder.encode(value));
    }

    public void getrange(String key, long startOffset, long endOffset) {
        getrange(SafeEncoder.encode(key), startOffset, endOffset);
    }

    public void publish(final String channel, final String message) {
        publish(SafeEncoder.encode(channel), SafeEncoder.encode(message));
    }

    public void unsubscribe(final String... channels) {
        final byte[][] cs = new byte[channels.length][];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = SafeEncoder.encode(channels[i]);
        }
        unsubscribe(cs);
    }

    public void psubscribe(final String... patterns) {
        final byte[][] ps = new byte[patterns.length][];
        for (int i = 0; i < ps.length; i++) {
            ps[i] = SafeEncoder.encode(patterns[i]);
        }
        psubscribe(ps);
    }

    public void punsubscribe(final String... patterns) {
        final byte[][] ps = new byte[patterns.length][];
        for (int i = 0; i < ps.length; i++) {
            ps[i] = SafeEncoder.encode(patterns[i]);
        }
        punsubscribe(ps);
    }

    public void subscribe(final String... channels) {
        final byte[][] cs = new byte[channels.length][];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = SafeEncoder.encode(channels[i]);
        }
        subscribe(cs);
    }

    public void configSet(String parameter, String value) {
        configSet(SafeEncoder.encode(parameter), SafeEncoder.encode(value));
    }

    public void configGet(String pattern) {
        configGet(SafeEncoder.encode(pattern));
    }
}
