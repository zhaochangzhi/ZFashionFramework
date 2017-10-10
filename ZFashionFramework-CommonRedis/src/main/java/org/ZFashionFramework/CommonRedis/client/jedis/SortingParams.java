package org.ZFashionFramework.CommonRedis.client.jedis;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.ZFashionFramework.CommonRedis.client.jedis.Protocol.Keyword.LIMIT;
import static org.ZFashionFramework.CommonRedis.client.jedis.Protocol.Keyword.ALPHA;
import static org.ZFashionFramework.CommonRedis.client.jedis.Protocol.Keyword.ASC;
import static org.ZFashionFramework.CommonRedis.client.jedis.Protocol.Keyword.BY;
import static org.ZFashionFramework.CommonRedis.client.jedis.Protocol.Keyword.DESC;
import static org.ZFashionFramework.CommonRedis.client.jedis.Protocol.Keyword.GET;
import static org.ZFashionFramework.CommonRedis.client.jedis.Protocol.Keyword.NOSORT;

import org.ZFashionFramework.CommonRedis.client.util.SafeEncoder;


/**
 * Builder Class for {@link Jedis#sort(String, SortingParams) SORT} Parameters.
 * 
 */
/**   
* @标题: REDISJAR包中文件未做改动
* @编写人： 韩峥
* @创建日期： 2012-8-2 下午4:30:49
* @修改人： 
* @修改日期： 2012-8-2 下午4:30:49
* @版本：    
* @描述: TODO
*/
public class SortingParams {
    private List<byte[]> params = new ArrayList<byte[]>();

    /**
     * Sort by weight in keys.
     * <p>
     * Takes a pattern that is used in order to generate the key names of the
     * weights used for sorting. Weight key names are obtained substituting the
     * first occurrence of * with the actual value of the elements on the list.
     * <p>
     * The pattern for a normal key/value pair is "keyname*" and for a value in
     * a hash "keyname*->fieldname".
     * 
     * @param pattern
     * @return the SortingParams Object
     */
    public SortingParams by(final String pattern) {
        return by(SafeEncoder.encode(pattern));
    }

    /**
     * Sort by weight in keys.
     * <p>
     * Takes a pattern that is used in order to generate the key names of the
     * weights used for sorting. Weight key names are obtained substituting the
     * first occurrence of * with the actual value of the elements on the list.
     * <p>
     * The pattern for a normal key/value pair is "keyname*" and for a value in
     * a hash "keyname*->fieldname".
     * 
     * @param pattern
     * @return the SortingParams Object
     */
    public SortingParams by(final byte[] pattern) {
        params.add(BY.raw);
        params.add(pattern);
        return this;
    }

    /**
     * No sorting.
     * <p>
     * This is useful if you want to retrieve a external key (using
     * {@link #get(String...) GET}) but you don't want the sorting overhead.
     * 
     * @return the SortingParams Object
     */
    public SortingParams nosort() {
        params.add(BY.raw);
        params.add(NOSORT.raw);
        return this;
    }

    public Collection<byte[]> getParams() {
        return Collections.unmodifiableCollection(params);
    }

    /**
     * Get the Sorting in Descending Order.
     * 
     * @return the sortingParams Object
     */
    public SortingParams desc() {
        params.add(DESC.raw);
        return this;
    }

    /**
     * Get the Sorting in Ascending Order. This is the default order.
     * 
     * @return the SortingParams Object
     */
    public SortingParams asc() {
        params.add(ASC.raw);
        return this;
    }

    /**
     * Limit the Numbers of returned Elements.
     * 
     * @param start
     *            is zero based
     * @param count
     * @return the SortingParams Object
     */
    public SortingParams limit(final int start, final int count) {
        params.add(LIMIT.raw);
        params.add(Protocol.toByteArray(start));
        params.add(Protocol.toByteArray(count));
        return this;
    }

    /**
     * Sort lexicographicaly. Note that Redis is utf-8 aware assuming you set
     * the right value for the LC_COLLATE environment variable.
     * 
     * @return the SortingParams Object
     */
    public SortingParams alpha() {
        params.add(ALPHA.raw);
        return this;
    }

    /**
     * Retrieving external keys from the result of the search.
     * <p>
     * Takes a pattern that is used in order to generate the key names of the
     * result of sorting. The key names are obtained substituting the first
     * occurrence of * with the actual value of the elements on the list.
     * <p>
     * The pattern for a normal key/value pair is "keyname*" and for a value in
     * a hash "keyname*->fieldname".
     * <p>
     * To get the list itself use the char # as pattern.
     * 
     * @param patterns
     * @return the SortingParams Object
     */
    public SortingParams get(String... patterns) {
        for (final String pattern : patterns) {
            params.add(GET.raw);
            params.add(SafeEncoder.encode(pattern));
        }
        return this;
    }

    /**
     * Retrieving external keys from the result of the search.
     * <p>
     * Takes a pattern that is used in order to generate the key names of the
     * result of sorting. The key names are obtained substituting the first
     * occurrence of * with the actual value of the elements on the list.
     * <p>
     * The pattern for a normal key/value pair is "keyname*" and for a value in
     * a hash "keyname*->fieldname".
     * <p>
     * To get the list itself use the char # as pattern.
     * 
     * @param patterns
     * @return the SortingParams Object
     */
    public SortingParams get(byte[]... patterns) {
        for (final byte[] pattern : patterns) {
            params.add(GET.raw);
            params.add(pattern);
        }
        return this;
    }
}