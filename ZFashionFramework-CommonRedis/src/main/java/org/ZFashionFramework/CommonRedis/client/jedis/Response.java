package org.ZFashionFramework.CommonRedis.client.jedis;

import org.ZFashionFramework.CommonRedis.client.jedis.exceptions.JedisDataException;

/**   
* @标题: REDISJAR包中文件未做改动
* @编写人： 韩峥
* @创建日期： 2012-8-2 下午4:30:49
* @修改人： 
* @修改日期： 2012-8-2 下午4:30:49
* @版本：    
* @描述: TODO
*/
public class Response<T> {
    protected T response = null;
    private boolean built = false;
    private boolean set = false;
    private Builder<T> builder;
    private Object data;

    public Response(Builder<T> b) {
        this.builder = b;
    }

    public void set(Object data) {
        this.data = data;
        set = true;
    }

    public T get() {
        if (!set) {
            throw new JedisDataException(
                    "Please close pipeline or multi block before calling this method.");
        }
        if (!built) {
        	if(data != null ){
	        	if (data instanceof JedisDataException){
	        		throw new JedisDataException((JedisDataException)data);
	        	}
	            response = builder.build(data);
        	}
            this.data = null;
            built = true;
        }
        return response;
    }

    public String toString() {
        return "Response " + builder.toString();
    }

}
