package com.humana.pharmacy.common.cache;

import com.humana.pharmacy.common.Helper;
import com.humana.pharmacy.common.cache.model.CCodeValue;
import com.humana.pharmacy.common.cache.model.CRule;
import com.humana.pharmacy.common.cache.model.CRxDefaults;
import com.humana.pharmacy.common.cache.model.CWorkflowQueue;
import com.humana.pharmacy.common.constants.CacheName;
import org.infinispan.Cache;
import org.infinispan.manager.CacheContainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class provides access to Infinispan cache.
 *
 * <p>Infinispan offers two alternative access methods: embedded mode and client-server mode. This
 * class supports both modes.
 *
 * <p>To use remote mode, pass an instance of RemoteCacheContainer to initialize() method. To use
 * embedded mode, pass an instance of EmbeddedCacheManager to initialize() method.
 *
 * <p>If the initialize() method is not called, this class still works as no-op without cache
 * feature.
 */
public class InfinispanCache {
    /**
     * Caches map. Key is CacheName, value is Infinispan cache. Populated in initialize() method.
     */
    private static final Map<CacheName, Cache<?, ?>> CACHES = new HashMap<>();

    private static boolean initialized = false;

    /**
     * Initialize, get caches from given cache container.
     *
     * <p>To use embedded mode, pass an instance of EmbeddedCacheManager.
     *
     * <p>To use client-server mode, pass an instance of RemoteCacheContainer.
     *
     * @param cacheContainer The cache container. Required
     */
    public static synchronized void initialize(CacheContainer cacheContainer) {
        // Get WORKFLOW_QUEUES, RULES, CODE_VALUES  caches
        if (!initialized) {
            Cache<Integer, CWorkflowQueue> workflowQueuesCache =
                    cacheContainer.getCache(CacheName.WORKFLOW_QUEUES.name());
            CACHES.put(CacheName.WORKFLOW_QUEUES, workflowQueuesCache);

            Cache<Integer, CRule> ruleCache =
                    cacheContainer.getCache(CacheName.RULES.name());
            CACHES.put(CacheName.RULES, ruleCache);

            Cache<Integer, CCodeValue> codeValueCache =
                    cacheContainer.getCache(CacheName.CODE_VALUES.name());
            CACHES.put(CacheName.CODE_VALUES, codeValueCache);

            // Get RX_DEFAULTS cache
            Cache<String, CRxDefaults> rxDefaultsCache = cacheContainer.getCache(CacheName.RX_DEFAULTS.name());
            CACHES.put(CacheName.RX_DEFAULTS, rxDefaultsCache);

            // Get CODE_VALUE_STATUS cache
            Cache<String, String> codeValueStatusCache = cacheContainer.getCache(CacheName.CODE_VALUE_STATUS.name());
            CACHES.put(CacheName.CODE_VALUE_STATUS, codeValueStatusCache);

            // Get TRADING_PARTNER_TYPES cache
            Cache<Long, String> tradingPartnerTypesCache = cacheContainer.getCache(CacheName.TRADING_PARTNER_TYPES.name());
            CACHES.put(CacheName.TRADING_PARTNER_TYPES, tradingPartnerTypesCache);

            // Get ORDER_DEFAULTS cache
            Cache<Long, String> orderDefaultsCache = cacheContainer.getCache(CacheName.ORDER_DEFAULTS.name());
            CACHES.put(CacheName.ORDER_DEFAULTS, orderDefaultsCache);

            // Get SHIPPING_METHODS cache
            Cache<Long, String> shippingMethodsCache = cacheContainer.getCache(CacheName.SHIPPING_METHODS.name());
            CACHES.put(CacheName.SHIPPING_METHODS, shippingMethodsCache);

            initialized = true;
        }
    }

    /**
     * Get data value from cache.
     *
     * <p>If given cache name is not found, this is a no-op method.
     *
     * <p>Generic types: K is data key type V is data value type
     *
     * @param cacheName The cache name. Required
     * @param key       The data key. Required
     * @return The data value. May be null if no cached value
     */
    public static <K, V> V get(CacheName cacheName, K key) {
        Cache<K, V> cache = (Cache<K, V>) CACHES.get(cacheName);
        return cache == null ? null : cache.get(key);
    }

    /**
     * Put data value into cache.
     *
     * <p>If given cache name is not found, this is a no-op method.
     *
     * <p>Generic types: K is data key type V is data value type
     *
     * @param cacheName The cache name. Required
     * @param value     The data value. May be null, in which case nothing happens
     * @param key       The data key. Required
     * @return Previous cache value if any. May be null
     */
    public static <K, V> V put(CacheName cacheName, K key, V value) {
        if (value == null) {
            return null;
        }
        Cache<K, V> cache = (Cache<K, V>) CACHES.get(cacheName);
        return cache == null ? null : cache.put(key, value);
    }

    /**
     * Put all values from given map into cache.
     *
     * <p>If given cache name is not found, this is a no-op method.
     *
     * <p>Generic types: K is data key type V is data value type
     *
     * @param cacheName The cache name. Required
     * @param values    The data values map. May be null or empty, in which case nothing happens
     */
    public static <K, V> void putAll(CacheName cacheName, Map<K, V> values) {
        if (values == null || values.isEmpty()) {
            return;
        }

        Cache<K, V> cache = (Cache<K, V>) CACHES.get(cacheName);
        if (cache != null) {
            cache.putAll(values);
        }
    }

    /**
     * Remove data from cache.
     *
     * <p>If given cache name is not found, this is a no-op method.
     *
     * <p>Generic types: K is data key type V is data value type
     *
     * @param cacheName The cache name. Required
     * @param key       The data key. Required
     * @return Previous cache value if any. May be null
     */
    public static <K, V> V remove(CacheName cacheName, K key) {
        Cache<K, V> cache = (Cache<K, V>) CACHES.get(cacheName);
        return cache == null ? null : cache.remove(key);
    }

    /**
     * Get cached workflow queues by given queue numbers.
     *
     * <p>This method will use Infinispan QueryFactory to search cache.
     *
     * @param queueNums List of workflow queue numbers. Required to be non-empty
     * @return List of cached workflow queues
     * @throws RuntimeException if the queueNums is null/empty
     */
    public static List<CWorkflowQueue> getWorkflowQueues(List<Integer> queueNums) {
        Helper.checkList(queueNums, "queueNums");

        Cache<?, ?> cache = CACHES.get(CacheName.WORKFLOW_QUEUES);
        if (cache == null) {
            return null;
        }

        return (List<CWorkflowQueue>) cache.values().stream().filter(value -> queueNums.contains(((CWorkflowQueue) value).getWorkflowQueueNum())).collect(Collectors.toList());
    }

    /**
     * Get cached workflow queues by given queue names and trading partner number.
     * <p>
     * This method will use Infinispan QueryFactory to search cache.
     *
     * @param queueNames List of workflow queue names. Required to be non-empty
     * @param tpNum      Trading partner number. Optional
     * @return List of cached workflow queues
     * @throws RuntimeException if the queueNames is null/empty
     */
    public static List<CWorkflowQueue> getWorkflowQueues(List<String> queueNames, Long tpNum) {
        Helper.checkList(queueNames, "queueNames");

        Cache<?, ?> cache = CACHES.get(CacheName.WORKFLOW_QUEUES);
        if (cache == null) {
            return null;
        }

        return (List<CWorkflowQueue>) cache.values().stream().filter(value -> {
            CWorkflowQueue workflowQueue = (CWorkflowQueue) value;
            return queueNames.contains(workflowQueue.getWorkflowQueueName())
                    && ((tpNum == null) || (tpNum.equals(workflowQueue.getTradingPartnerNum())));
        }).collect(Collectors.toList());
    }

    /**
     * Get cached rules.
     *
     * @param orderBasedRule the order based rule
     * @return the rules
     */
    public static List<CRule> getRules(String orderBasedRule) {
        Cache<?, ?> cache = CACHES.get(CacheName.RULES);
        if (cache == null) {
            return null;
        }

        return (List<CRule>) cache.values().stream().filter(value -> Objects.equals(((CRule) value).getOrderBasedRule(), orderBasedRule)).collect(Collectors.toList());
    }

    /**
     * Gets cached code values.
     *
     * @param fkCodeCatId the fk code cat id
     * @return the code values
     */
    public static List<CCodeValue> getCodeValues(String fkCodeCatId) {
        Cache<?, ?> cache = CACHES.get(CacheName.CODE_VALUES);
        if (cache == null) {
            return null;
        }
        return (List<CCodeValue>) cache.values().stream().filter(value -> Objects.equals(((CCodeValue) value).getFkCodeCatId(), fkCodeCatId)).collect(Collectors.toList());
    }
}
