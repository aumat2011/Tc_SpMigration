package com.humana.pharmacy.demo.config;

import com.humana.pharmacy.common.cache.InfinispanCache;
import com.humana.pharmacy.common.service.FunctionsService;
import com.humana.pharmacy.service.AnsRxNarcCodeService;
import com.humana.pharmacy.service.AnsRxSanityService;
import com.humana.pharmacy.service.AnsValidateHippaAddressService;
import com.humana.pharmacy.service.DuplicateGPIService;
import com.humana.pharmacy.service.ExceedMaxPVDaysService;
import com.humana.pharmacy.service.OrderSanityService;
import com.humana.pharmacy.service.RsRxLinkingSubstitutionService;
import com.humana.pharmacy.service.RxSanityService;
import com.humana.pharmacy.service.impl.AnsRxNarcCodeServiceImpl;
import com.humana.pharmacy.service.impl.AnsRxSanityServiceImpl;
import com.humana.pharmacy.service.impl.AnsValidateHippaAddressServiceImpl;
import com.humana.pharmacy.service.impl.DuplicateGPIServiceImpl;
import com.humana.pharmacy.service.impl.ExceedMaxPVDaysServiceImpl;
import com.humana.pharmacy.service.impl.OrderSanityServiceImpl;
import com.humana.pharmacy.service.impl.RsRxLinkingSubstitutionServiceImpl;
import com.humana.pharmacy.service.impl.RxSanityServiceImpl;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * The phase1 config
 */
@Configuration
public class Phase1Configure {

    /**
     * Create DuplicateGPIService
     *
     * @param dataSourceEpostrx  the epostrx datasource
     * @param dataSourceWorkflow the workflow datasource
     * @param cacheManager       the cache manager
     * @return the DuplicateGPIService
     */
    @Bean(name = "duplicateGPIService")
    public DuplicateGPIService duplicateGPIService(
            @Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
            @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
            EmbeddedCacheManager cacheManager) {
        InfinispanCache.initialize(cacheManager);
        return new DuplicateGPIServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
    }

    /**
     * Create ExceedMaxPVDaysService
     *
     * @param dataSourceEpostrx  the epostrx datasource
     * @param dataSourceWorkflow the workflow datasource
     * @param cacheManager       the cache manager
     * @return the ExceedMaxPVDaysService
     */
    @Bean(name = "exceedMaxPVDaysService")
    public ExceedMaxPVDaysService exceedMaxPVDaysService(
            @Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
            @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
            EmbeddedCacheManager cacheManager) {
        InfinispanCache.initialize(cacheManager);
        return new ExceedMaxPVDaysServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
    }

    /**
     * Create OrderSanityService
     *
     * @param dataSourceEpostrx  the epostrx datasource
     * @param dataSourceWorkflow the workflow datasource
     * @param cacheManager       the cache manager
     * @return the OrderSanityService
     */
    @Bean(name = "orderSanityService")
    public OrderSanityService orderSanityService(
            @Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
            @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
            EmbeddedCacheManager cacheManager) {
        InfinispanCache.initialize(cacheManager);
        return new OrderSanityServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
    }

    /**
     * Create RxSanityService
     *
     * @param dataSourceEpostrx  the epostrx datasource
     * @param dataSourceWorkflow the workflow datasource
     * @param functionsService   the function service
     * @param cacheManager       the cache manager
     * @return the RxSanityService
     */
    @Bean(name = "rxSanityService")
    public RxSanityService rxSanityService(
            @Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
            @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
            @Qualifier("functionsService") FunctionsService functionsService,
            EmbeddedCacheManager cacheManager) {
        InfinispanCache.initialize(cacheManager);
        RxSanityServiceImpl rxSanityService =
                new RxSanityServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
        rxSanityService.setFunctionsService(functionsService);

        return rxSanityService;
    }

    /**
     * Create AnsRxSanityService
     *
     * @param dataSourceEpostrx  the epostrx datasource
     * @param dataSourceWorkflow the workflow datasource
     * @param functionsService   the function service
     * @param cacheManager       the cache manager
     * @return the AnsRxSanityService
     */
    @Bean(name = "ansRxSanityService")
    public AnsRxSanityService ansRxSanityService(
            @Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
            @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
            @Qualifier("functionsService") FunctionsService functionsService,
            EmbeddedCacheManager cacheManager) {
        InfinispanCache.initialize(cacheManager);
        AnsRxSanityServiceImpl ansRxSanityService =
                new AnsRxSanityServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
        ansRxSanityService.setFunctionsService(functionsService);

        return ansRxSanityService;
    }

    /**
     * Create AnsRxNarcCodeService
     *
     * @param dataSourceEpostrx  the epostrx datasource
     * @param dataSourceWorkflow the workflow datasource
     * @param functionsService   the function service
     * @param cacheManager       the cache manager
     * @return the AnsRxNarcCodeService
     */
    @Bean(name = "ansRxNarcCodeService")
    public AnsRxNarcCodeService ansRxNarcCodeService(
            @Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
            @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
            @Qualifier("functionsService") FunctionsService functionsService,
            EmbeddedCacheManager cacheManager) {
        InfinispanCache.initialize(cacheManager);
        AnsRxNarcCodeServiceImpl ansRxNarcCodeService =
                new AnsRxNarcCodeServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
        ansRxNarcCodeService.setFunctionsService(functionsService);

        return ansRxNarcCodeService;
    }

    /**
     * Create AnsValidateHippaAddressService
     *
     * @param dataSourceEpostrx  the epostrx datasource
     * @param dataSourceWorkflow the workflow datasource
     * @param functionsService   the function service
     * @param cacheManager       the cache manager
     * @return the AnsValidateHippaAddressService
     */
    @Bean(name = "ansValidateHippaAddressService")
    public AnsValidateHippaAddressService ansValidateHippaAddressService(
            @Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
            @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
            @Qualifier("functionsService") FunctionsService functionsService,
            EmbeddedCacheManager cacheManager) {
        InfinispanCache.initialize(cacheManager);
        AnsValidateHippaAddressServiceImpl ansValidateHippaAddressService =
                new AnsValidateHippaAddressServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
        ansValidateHippaAddressService.setFunctionsService(functionsService);

        return ansValidateHippaAddressService;
    }

    /**
     * Create RsRxLinkingSubstitutionService
     *
     * @param dataSourceEpostrx  the epostrx datasource
     * @param dataSourceWorkflow the workflow datasource
     * @param functionsService   the function service
     * @param cacheManager       the cache manager
     * @return the RsRxLinkingSubstitutionService
     */
    @Bean(name = "rsRxLinkingSubstitutionService")
    public RsRxLinkingSubstitutionService rsRxLinkingSubstitutionService(
            @Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
            @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
            @Qualifier("functionsService") FunctionsService functionsService,
            EmbeddedCacheManager cacheManager) {
        InfinispanCache.initialize(cacheManager);
        RsRxLinkingSubstitutionServiceImpl rsRxLinkingSubstitutionService =
                new RsRxLinkingSubstitutionServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
        rsRxLinkingSubstitutionService.setFunctionsService(functionsService);
        return rsRxLinkingSubstitutionService;
    }
}
