package org.ZFashionFramework.CommonRedis.client.jedis;
/**   
* @标题: REDISJAR包中文件未做改动
* @编写人： 韩峥
* @创建日期： 2012-8-2 下午4:30:49
* @修改人： 
* @修改日期： 2012-8-2 下午4:30:49
* @版本：    
* @描述: TODO
*/
public abstract class JedisMonitor {
    protected Client client;

    public void proceed(Client client) {
        this.client = client;
        this.client.setTimeoutInfinite();
        do {
            String command = client.getBulkReply();
            onCommand(command);
        } while (client.isConnected());
    }

    public abstract void onCommand(String command);
}