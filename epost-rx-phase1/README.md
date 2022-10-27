# ePost RX Modernisation phase1 module

## Technology Stack

- JDK 8
- Maven 3.6
- SpringBoot 2.6.2
- Infinispan 5.2.11.Final-redhat-1
- Junit v5
- lombok (for IDE : [setup guide](https://projectlombok.org/setup/overview))

## Prerequisites

- epost-rx-common installed

## Install

1. Run `mvn clean install` to install `epost-rx-phase1` to local repository.

## Usage

1. Add a dependency to maven pom file

```
        <dependency>
            <groupId>com.humana.pharmacy</groupId>
            <artifactId>epost-rx-phase1</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
```

2. Configure/Create a
   DuplicateGPIService/ExceedMaxPVDaysService/OrderSanityService/FunctionsService/RxSanityService/AnsRxSanityService (
   refer to <b>DemoApplication</b> and <b>Phase1Configure</b> class).

```
    @Bean(name = "dataSourceEpostrx")
    @ConfigurationProperties(prefix = "spring.datasource-epostrx")
    public DataSource dataSourceEpostrx() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataSourceWorkflow")
    @ConfigurationProperties(prefix = "spring.datasource-workflow")
    public DataSource dataSourceWorkflow() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "duplicateGPIService")
    public DuplicateGPIService duplicateGPIService(@Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
                                                   @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow) {
        return new DuplicateGPIServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
    }
    @Bean(name = "functionsService")
    public FunctionsService functionsService(@Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
                                             @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow) {
        return new FunctionsServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
    }
    @Bean(name = "exceedMaxPVDaysService")
    public ExceedMaxPVDaysService exceedMaxPVDaysService(@Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
                                                         @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow) {
        return new ExceedMaxPVDaysServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
    }
    @Bean(name = "orderSanityService")
    public OrderSanityService orderSanityService(@Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
                                                 @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow) {
        return new OrderSanityServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
    }
    @Bean(name = "rxSanityService")
    public RxSanityService rxSanityService(@Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
                                           @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
                                           @Qualifier("functionsService") FunctionsService functionsService) {
        RxSanityServiceImpl rxSanityService = new RxSanityServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
        rxSanityService.setFunctionsService(functionsService);

        return rxSanityService;
    }
    @Bean(name = "ansRxSanityService")
    public AnsRxSanityService ansRxSanityService(@Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
                                                 @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
                                                 @Qualifier("functionsService") FunctionsService functionsService) {
        AnsRxSanityServiceImpl ansRxSanityService = new AnsRxSanityServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
        ansRxSanityService.setFunctionsService(functionsService);

        return ansRxSanityService;
    }
    @Bean(name = "ansRxNarcCodeService")
    public AnsRxNarcCodeService ansRxNarcCodeService(@Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
                                                     @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
                                                     @Qualifier("functionsService") FunctionsService functionsService) {
        AnsRxNarcCodeServiceImpl ansRxNarcCodeService = new AnsRxNarcCodeServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
        ansRxNarcCodeService.setFunctionsService(functionsService);

        return ansRxNarcCodeService;
    }
    @Bean(name = "ansValidateHippaAddressService")
    public AnsValidateHippaAddressService ansValidateHippaAddressService(@Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
                                                               @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
                                                               @Qualifier("functionsService") FunctionsService functionsService) {
        AnsValidateHippaAddressServiceImpl ansValidateHippaAddressService = new AnsValidateHippaAddressServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
        ansValidateHippaAddressService.setFunctionsService(functionsService);

        return ansValidateHippaAddressService;
    }
    @Bean(name = "rsRxLinkingSubstitutionService")
    public RsRxLinkingSubstitutionService rsRxLinkingSubstitutionService(
            @Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
            @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
            @Qualifier("functionsService") FunctionsService functionsService) {
        RsRxLinkingSubstitutionServiceImpl rsRxLinkingSubstitutionService =
                new RsRxLinkingSubstitutionServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
        rsRxLinkingSubstitutionService.setFunctionsService(functionsService);
        return rsRxLinkingSubstitutionService;
    }
```

3.
Call `checkDuplicateGPI/getPrecheckRequired/getFirstTimeFill/getExceedMaxPVDays/getPrimaryEcsPaid/checkExceedMaxPVDays/checkOrderSanity/getRemainingRefills/getRxTotalQty/getRxTotalQtyFilled/getRxTotalORTFQtyFilled/getRxTotalFills/checkRxSanity`
method (refer to <b>Phase1Controller</b> class).

```
  service.checkDuplicateGPI(patientNum, orderInvoiceNum)
  functionsService.getPrecheckRequired(escriptIds)
  functionsService.getFirstTimeFill(escriptIds)
  functionsService.getExceedMaxPVDays(escriptIds)
  functionsService.getPrimaryEcsPaid(escriptIds)
  exceedMaxPVDaysService.checkExceedMaxPVDays(orderNum)
  orderSanityService.checkOrderSanity(orderNum, queueToBeUsed)
  functionsService.getRemainingRefills(rxNumber, rxEScriptDTOs)
  functionsService.getRxTotalQty(scriptId, rxEScriptDTOs)
  functionsService.getRxTotalQtyFilled(rxNumber, rxEScriptDTOs)
  functionsService.getRxTotalORTFQtyFilled(rxNumber, rxEScriptDTOs)
  functionsService.getRxTotalFills(scriptId, rxEScriptDTOs)
  functionsService.getRxQtyRemaining(scriptId, rxEScriptDTOs)
  functionsService.getRsRxLinkingParentChild(rxNumber, active)
  rxSanityService.checkRxSanity(scriptId, rxNumber)
  ansRxSanityService.checkAnsRxSanity(scriptId, rxNumber)
  ansRxNarcCodeService.checkAnsRxNarcCode(scriptId, rxNumber)
  ansValidateHippaAddressService.checkAnsValidateHippaAddress(orderNum)
  rsRxLinkingSubstitutionService.getSubEScriptMsgAttributeSeq(scriptId)
```

4. Add infinispan dependency to maven pom file

```
		<dependency>
			<groupId>org.infinispan</groupId>
			<artifactId>infinispan-core</artifactId>
			<version>5.2.11.Final-redhat-1</version>
		</dependency>
		<dependency>
			<groupId>org.infinispan</groupId>
			<artifactId>infinispan-client-hotrod</artifactId>
			<version>5.2.11.Final-redhat-1</version>
		</dependency>
```

5. Configure embeddedCacheManager

```
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
```

6. Initialize Cache

```
    @Bean(name = "duplicateGPIService")
    public DuplicateGPIService duplicateGPIService(@Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
                                                   @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
                                                   EmbeddedCacheManager cacheManager) {
        InfinispanCache.initialize(cacheManager);
        return new DuplicateGPIServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
    }
```

> NOTE: You don't need executing step 4,5,6 if you don't want to use infinispan cache, and the lib still works.

## Unit Tests and coverage

- Run `mvn clean test` to execute unit tests.
- Open `target/jacoco-ut/index.html` to see generated coverage report.

## Generate Java Document

- Under root directory, run `mvn javadoc:javadoc`.
- The document will be generated under `target/site/apidocs`.
