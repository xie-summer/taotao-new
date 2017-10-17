package com.taotao.untrans;

import com.taotao.model.BaseObject;
import com.taotao.support.CachePair;

import java.util.Collection;
import java.util.Map;

public interface CacheService extends CacheTools {
    // 固定区域
    String REGION_TENMIN = "tenMin";
    String REGION_ONEHOUR = "oneHour";
    String REGION_LOGINAUTH = "loginAuth"; // 2hour
    String REGION_SERVICE = "service"; // 12 hour
    String REGION_FIVEDAY = "fiveDay"; // 5day
    String REGION_LOGINKEY = "loginKey";

    /**
     * @param claszz
     * @param key
     * @param mpid
     */
    <T extends BaseObject> void cleanUkey(Class<T> claszz, String key, Long mpid);

    /**
     * 清理缓存
     *
     * @param claszz
     * @param key
     * @param ukey
     * @param <T>
     */
    <T extends BaseObject> void cleanUkey(Class<T> claszz, String key, String ukey);

    /**
     * @function 多个key
     */
    /**
     * @param regionName
     * @param keys
     * @return
     */
    Map<String, Object> getBulk(String regionName, Collection<String> keys);

    /**
     * 使用独立的超时时间
     *
     * @param regionName
     * @param key
     * @param value
     * @param timeoutSecond 超时秒数
     */
    void set(String regionName, String key, Object value, int timeoutSecond);

    /**
     * 直接更新缓存
     */
    void updateValue(String regionName, String key, String newvalue);

    /**
     * 刷新版本
     */
    void refreshVersion();

    /**
     * 获取时间
     *
     * @param region
     * @return
     */
    Integer getCacheTime(String region);

    /**
     * 增加计数，非原子操作
     *
     * @param key
     * @param by       添加值
     * @param defvalue 默认值
     * @return
     */
    int incr(String regionName, String key, int by, int defvalue);

    /**
     * 原子加
     *
     * @param regionName
     * @param key
     * @param by         增加
     * @param def        默认值
     * @return the new value, or -1 if we were unable to increment or add
     */
    int incrementAndGet(String regionName, String key, int by, int def);

    /**
     * @param regionName
     * @param key
     * @return
     */
    CachePair getCachePair(String regionName, String key);

    /**
     * @param regionName
     * @param key
     * @param version
     * @param value
     * @param expSeconds
     * @return
     */
    boolean setCachePair(String regionName, String key, long version, Object value, int expSeconds);

    /**
     * @param regionName
     * @param key
     * @param value
     * @param expSeconds
     */
    void add(String regionName, String key, Object value, int expSeconds);

    /**
     * 原子减
     *
     * @param regionName
     * @param key
     * @param by
     * @param def        the default value (if the counter does not exist)
     * @return the new value, or -1 if we were unable to decrement or add
     */
    int decrAndGet(String regionName, String key, int by, int def);

    /**
     * 原子加
     *
     * @param regionName
     * @param key
     * @param by
     * @param def
     * @param exp        超时时间,秒
     * @return
     */
    int incrementAndGet(String regionName, String key, int by, int def, int exp);

    /**
     * @param regionVersion
     */
    void refreshVersion(Map<String, String> regionVersion);
}
