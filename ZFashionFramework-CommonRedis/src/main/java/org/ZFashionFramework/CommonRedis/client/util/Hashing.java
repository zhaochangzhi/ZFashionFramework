package org.ZFashionFramework.CommonRedis.client.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**   
* @标题: REDISJAR包中文件未做改动
* @编写人： 韩峥
* @创建日期： 2012-8-2 下午4:30:49
* @修改人： 
* @修改日期： 2012-8-2 下午4:30:49
* @版本：    
* @描述: TODO
*/
public interface Hashing {
    public static final Hashing MURMUR_HASH = new MurmurHash();
    public ThreadLocal<MessageDigest> md5Holder = new ThreadLocal<MessageDigest>();

    public static final Hashing MD5 = new Hashing() {
        public long hash(String key) {
            return hash(SafeEncoder.encode(key));
        }

        public long hash(byte[] key) {
            try {
                if (md5Holder.get() == null) {
                    md5Holder.set(MessageDigest.getInstance("MD5"));
                }
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException("++++ no md5 algorythm found");
            }
            MessageDigest md5 = md5Holder.get();

            md5.reset();
            md5.update(key);
            byte[] bKey = md5.digest();
            long res = ((long) (bKey[3] & 0xFF) << 24)
                    | ((long) (bKey[2] & 0xFF) << 16)
                    | ((long) (bKey[1] & 0xFF) << 8) | (long) (bKey[0] & 0xFF);
            return res;
        }
    };

    public long hash(String key);

    public long hash(byte[] key);
}