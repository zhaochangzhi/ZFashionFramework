package org.ZFashionFramework.CommonRedis.redis.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ZFashionFramework.CommonRedis.client.jedis.ShardedJedisPool;


public class ConstantData {
	public final static String _READ = "_read";
	public final static String ReidsDEF = "reids_def";
	public static Map<String, ShardedJedisPool> SHARDEDJEDISPOOLMAP = new HashMap<String, ShardedJedisPool>();
	//public static Map<Map<ShardedJedisPool, Boolean>, Map<ShardedJedisPool, Boolean>> SHARDEDJEDISPOOLBOOLEAN = new HashMap<Map<ShardedJedisPool, Boolean>, Map<ShardedJedisPool, Boolean>>();
	public static List <Map<ShardedJedisPool, Boolean>> SHARDEDJEDISPOOLBOOLEAN= new ArrayList<Map<ShardedJedisPool, Boolean>>();
	public static List<String> SHARDEDJEDISPOOLNAME = new ArrayList<String>();
	public static Map<String,Integer> REDISDB=new HashMap<String,Integer>();

}
