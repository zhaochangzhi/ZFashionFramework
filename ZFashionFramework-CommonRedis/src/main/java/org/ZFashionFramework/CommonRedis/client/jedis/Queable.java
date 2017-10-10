package org.ZFashionFramework.CommonRedis.client.jedis;

import java.util.LinkedList;
import java.util.Queue;
/**   
* @标题: REDISJAR包中文件未做改动
* @编写人： 韩峥
* @创建日期： 2012-8-2 下午4:30:49
* @修改人： 
* @修改日期： 2012-8-2 下午4:30:49
* @版本：    
* @描述: TODO
*/
public class Queable {
    private Queue<Response<?>> pipelinedResponses = new LinkedList<Response<?>>();

    protected void clean() {
        pipelinedResponses.clear();
    }

    protected Response<?> generateResponse(Object data) {
        Response<?> response = pipelinedResponses.poll();
        if (response != null) {
            response.set(data);
        }
        return response;
    }

    protected <T> Response<T> getResponse(Builder<T> builder) {
        Response<T> lr = new Response<T>(builder);
        pipelinedResponses.add(lr);
        return lr;
    }

}
