package com.humana.pharmacy.demo;

import com.humana.pharmacy.common.cache.InfinispanCache;
import com.humana.pharmacy.common.service.FunctionsService;
import com.humana.pharmacy.common.service.impl.FunctionsServiceImpl;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLServer2012Templates;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * The demo application
 */
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * Create epostrx datasource from "spring.datasource-epostrx" properties
     *
     * @return epostrx datasource
     */
    @Bean(name = "dataSourceEpostrx")
    @ConfigurationProperties(prefix = "spring.datasource-epostrx")
    public DataSource dataSourceEpostrx() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Create workflow datasource from "spring.datasource-workflow" properties
     *
     * @return workflow datasource
     */
    @Bean(name = "dataSourceWorkflow")
    @ConfigurationProperties(prefix = "spring.datasource-workflow")
    public DataSource dataSourceWorkflow() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Create epostrxQueryFactory
     *
     * @param dataSourceEpostrx the epostrx datasource
     * @return epostrxQueryFactory
     */
    @Bean(name = "epostrxQueryFactory")
    public SQLQueryFactory epostrxQueryFactory(
            @Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx) {
        return new SQLQueryFactory(
                new Configuration(SQLServer2012Templates.DEFAULT), dataSourceEpostrx);
    }

    /**
     * Create function service
     *
     * @param dataSourceEpostrx  the epostrx datasource
     * @param dataSourceWorkflow the workflow datasource
     * @param cacheManager       the cache manager
     * @return the function service
     */
    @Bean(name = "functionsService")
    public FunctionsService functionsService(
            @Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
            @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
            EmbeddedCacheManager cacheManager) {
        InfinispanCache.initialize(cacheManager);
        return new FunctionsServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
    }

    /**
     * Create CacheManager
     *
     * @return the DefaultCacheManager
     */
    @Bean
    public DefaultCacheManager getCacheManager() {
        GlobalConfigurationBuilder global = GlobalConfigurationBuilder.defaultClusteredBuilder();
        global.globalJmxStatistics().allowDuplicateDomains(true);
        DefaultCacheManager cacheManager = new DefaultCacheManager();
        ConfigurationBuilder builder = new ConfigurationBuilder();

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
}
