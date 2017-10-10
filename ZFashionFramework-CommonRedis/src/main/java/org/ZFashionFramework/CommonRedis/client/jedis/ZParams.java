package org.ZFashionFramework.CommonRedis.client.jedis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.ZFashionFramework.CommonRedis.client.jedis.Protocol.Keyword.AGGREGATE;
import static org.ZFashionFramework.CommonRedis.client.jedis.Protocol.Keyword.WEIGHTS;
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
public class ZParams {
    public enum Aggregate {
        SUM, MIN, MAX;

        public final byte[] raw;

        Aggregate() {
            raw = SafeEncoder.encode(name());
        }
    }

    private List<byte[]> params = new ArrayList<byte[]>();

    public ZParams weights(final int... weights) {
        params.add(WEIGHTS.raw);
        for (final int weight : weights) {
            params.add(Protocol.toByteArray(weight));
        }

        return this;
    }

    public Collection<byte[]> getParams() {
        return Collections.unmodifiableCollection(params);
    }

    public ZParams aggregate(final Aggregate aggregate) {
        params.add(AGGREGATE.raw);
        params.add(aggregate.raw);
        return this;
    }
}
