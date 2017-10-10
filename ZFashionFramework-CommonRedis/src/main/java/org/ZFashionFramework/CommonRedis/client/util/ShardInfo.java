package org.ZFashionFramework.CommonRedis.client.util;
/**   
* @标题: REDISJAR包中文件未做改动
* @编写人： 韩峥
* @创建日期： 2012-8-2 下午4:30:49
* @修改人： 
* @修改日期： 2012-8-2 下午4:30:49
* @版本：    
* @描述: TODO
*/
public abstract class ShardInfo<T> {
    private int weight;

    public ShardInfo() {
    }

    public ShardInfo(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

    protected abstract T createResource();
    
    public abstract String getName();
}
