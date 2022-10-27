package com.humana.pharmacy.demo.config;

import com.humana.pharmacy.common.cache.InfinispanCache;
import com.humana.pharmacy.common.service.FunctionsService;
import com.humana.pharmacy.service.AnsDefaultRxPlanParamsService;
import com.humana.pharmacy.service.AnsRxProductService;
import com.humana.pharmacy.service.RsRxLinkWorkflowMinUtilizationCheckService;
import com.humana.pharmacy.service.RsRxLinkingInsertService;
import com.humana.pharmacy.service.impl.AnsDefaultRxPlanParamsServiceImpl;
import com.humana.pharmacy.service.impl.AnsRxProductServiceImpl;
import com.humana.pharmacy.service.impl.RsRxLinkWorkflowMinUtilizationCheckServiceImpl;
import com.humana.pharmacy.service.impl.RsRxLinkingInsertServiceImpl;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * phase2 config.
 */
@Configuration
public class Phase2Configure {

    @Bean(name = "ansRxProductService")
    public AnsRxProductService ansRxProductService(
            @Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
            @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
            EmbeddedCacheManager cacheManager) {
        InfinispanCache.initialize(cacheManager);
        return new AnsRxProductServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
    }

    @Bean(name = "ansDefaultRxPlanParamsService")
    public AnsDefaultRxPlanParamsService ansDefaultRxPlanParamsService(
            @Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
            @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
            EmbeddedCacheManager cacheManager) {
        InfinispanCache.initialize(cacheManager);
        return new AnsDefaultRxPlanParamsServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
    }

    @Bean(name = "rsRxLinkWorkflowMinUtilizationCheckService")
    public RsRxLinkWorkflowMinUtilizationCheckService rsRxLinkWorkflowMinUtilizationCheckService(
            @Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
            @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
            EmbeddedCacheManager cacheManager) {
        InfinispanCache.initialize(cacheManager);
        RsRxLinkWorkflowMinUtilizationCheckServiceImpl rsRxLinkWorkflowMinUtilizationCheckService =
                new RsRxLinkWorkflowMinUtilizationCheckServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
        return rsRxLinkWorkflowMinUtilizationCheckService;
    }

    /**
     * Create RsRxLinkingInsertService
     *
     * @param dataSourceEpostrx  the epostrx datasource
     * @param dataSourceWorkflow the workflow datasource
     * @param functionsService   the function service
     * @param cacheManager       the cache manager
     * @return the RsRxLinkingInsertService
     */
    @Bean(name = "rsRxLinkingInsertService")
    public RsRxLinkingInsertService rsRxLinkingInsertService(
            @Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
            @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
            @Qualifier("functionsService") FunctionsService functionsService,
            EmbeddedCacheManager cacheManager) {
        InfinispanCache.initialize(cacheManager);
        RsRxLinkingInsertServiceImpl rsRxLinkingInsertService =
                new RsRxLinkingInsertServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
        rsRxLinkingInsertService.setFunctionsService(functionsService);
        return rsRxLinkingInsertService;
    }
}
