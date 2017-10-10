package org.ZFashionFramework.CommonRedis.client.jedis;

import java.util.Arrays;

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
public class Tuple implements Comparable<Tuple> {
    private byte[] element;
    private Double score;

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result;
        if (null != element) {
            for (final byte b : element) {
                result = prime * result + b;
            }
        }
        long temp;
        temp = Double.doubleToLongBits(score);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tuple other = (Tuple) obj;
        if (element == null) {
            if (other.element != null)
                return false;
        } else if (!Arrays.equals(element, other.element))
            return false;
        return true;
    }

    public int compareTo(Tuple other) {
        if (Arrays.equals(this.element, other.element))
            return 0;
        else
            return this.score < other.getScore() ? -1 : 1;
    }

    public Tuple(String element, Double score) {
        super();
        this.element = SafeEncoder.encode(element);
        this.score = score;
    }

    public Tuple(byte[] element, Double score) {
        super();
        this.element = element;
        this.score = score;
    }

    public String getElement() {
        if (null != element) {
            return SafeEncoder.encode(element);
        } else {
            return null;
        }
    }

    public byte[] getBinaryElement() {
        return element;
    }

    public double getScore() {
        return score;
    }

    public String toString() {
        return '[' + Arrays.toString(element) + ',' + score + ']';
    }
}