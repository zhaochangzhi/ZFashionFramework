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
public class DebugParams {
    private String[] command;

    public String[] getCommand() {
	return command;
    }

    private DebugParams() {

    }

    public static DebugParams SEGFAULT() {
	DebugParams debugParams = new DebugParams();
	debugParams.command = new String[] { "SEGFAULT" };
	return debugParams;
    }

    public static DebugParams OBJECT(String key) {
	DebugParams debugParams = new DebugParams();
	debugParams.command = new String[] { "OBJECT", key };
	return debugParams;
    }

    public static DebugParams RELOAD() {
	DebugParams debugParams = new DebugParams();
	debugParams.command = new String[] { "RELOAD" };
	return debugParams;
    }
}
