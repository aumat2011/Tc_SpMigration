package com.humana.pharmacy.common;

import com.humana.pharmacy.common.cache.InfinispanCache;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * The helper class of tests.
 */
public class TestsHelper {
    /**
     * Private constructor.
     */
    private TestsHelper() {
        // empty
    }

    /**
     * Get the cache manager.
     *
     * @return the cache manager.
     */
    public static DefaultCacheManager getCacheManager() {
        GlobalConfigurationBuilder global = GlobalConfigurationBuilder.defaultClusteredBuilder();
        global.globalJmxStatistics().allowDuplicateDomains(true);
        DefaultCacheManager cacheManager = new DefaultCacheManager(global.build());
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.clustering().cacheMode(CacheMode.LOCAL);

        cacheManager.defineConfiguration("WORKFLOW_QUEUES", builder.build());
        cacheManager.defineConfiguration("RULES", builder.build());
        cacheManager.defineConfiguration("CODE_VALUES", builder.build());
        cacheManager.defineConfiguration("RX_DEFAULTS", builder.build());
        cacheManager.defineConfiguration("CODE_VALUE_STATUS", builder.build());
        cacheManager.defineConfiguration("TRADING_PARTNER_TYPES", builder.build());
        cacheManager.defineConfiguration("RX_STATUS_CODE", builder.build());
        cacheManager.defineConfiguration("ORDER_DEFAULTS", builder.build());
        cacheManager.defineConfiguration("SHIPPING_METHODS", builder.build());

        return cacheManager;
    }

    /**
     * Clear the cache.
     *
     * @throws Exception if any error occurs
     */
    public static void clearCache() throws Exception {
        Field field = InfinispanCache.class.getDeclaredField("CACHES");
        field.setAccessible(true);
        Map cacheMap = (Map) field.get(null);
        cacheMap.clear();
        field.setAccessible(false);

        Field flag = InfinispanCache.class.getDeclaredField("initialized");
        flag.setAccessible(true);
        flag.setBoolean(null, false);
        flag.setAccessible(false);
    }

    /**
     * Set field of the given object.
     *
     * @param obj   the given object
     * @param field the field name
     * @param value the field value
     */
    public static void setFieldValue(Object obj, String field, Object value) {
        try {
            Field privateField = null;
            try {
                // Create Field object
                privateField = obj.getClass().getDeclaredField(field);
            } catch (Exception e) {
                // Ignore
            }
            if (privateField == null) {
                privateField = obj.getClass().getSuperclass().getDeclaredField(field);
            }

            // Set the accessibility as true
            privateField.setAccessible(true);

            // Store the value of private field in variable
            privateField.set(obj, value);

            // Set the accessibility as false
            privateField.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
