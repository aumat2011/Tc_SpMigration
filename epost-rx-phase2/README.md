# ePost RX Modernisation phase2 module

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

1. Run `mvn clean install` to install `epost-rx-phase2` to local repository.

## Usage

1. Add a dependency to maven pom file

```
        <dependency>
            <groupId>com.humana.pharmacy</groupId>
            <artifactId>epost-rx-phase2</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
```

2. Configure/Create a RsRxLinkingInsertService/AnsRxProductService (refer to <b>DemoApplication</b> and <b>
   Phase2Configure</b> class).

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

    @Bean(name = "functionsService")
    public FunctionsService functionsService(@Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
                                             @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow) {
        return new FunctionsServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
    }
    
   
    @Bean(name = "rsRxLinkingInsertService")
    public RsRxLinkingInsertService rsRxLinkingInsertService(
          @Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
          @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
          @Qualifier("functionsService") FunctionsService functionsService) {
        RsRxLinkingInsertServiceImpl rsRxLinkingInsertService =
            new RsRxLinkingInsertServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
        rsRxLinkingInsertService.setFunctionsService(functionsService);
        return rsRxLinkingInsertService;
    }

    @Bean(name = "ansRxProductService")
    public AnsRxProductService ansRxProductService(
        @Qualifier("dataSourceEpostrx") DataSource dataSourceEpostrx,
        @Qualifier("dataSourceWorkflow") DataSource dataSourceWorkflow,
        EmbeddedCacheManager cacheManager) {
      InfinispanCache.initialize(cacheManager);
      return new AnsRxProductServiceImpl(dataSourceEpostrx, dataSourceWorkflow);
    }
```

3. Call `insertRsRxLinking/checkAnsRxProduct` method (refer to <b>Phase2Controller</b> class).

```
  rsRxLinkingInsertService.insertRsRxLinking(scriptId, userNum, mode, deactivationComment, flag);
  ansRxProductService.checkAnsRxProduct(scriptId);
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
```

> NOTE: You don't need executing step 4,5,6 if you don't want to use infinispan cache, and the lib still works.

## Unit Tests and coverage

- Run `mvn clean test` to execute unit tests.
- Open `target/jacoco-ut/index.html` to see generated coverage report.

## Generate Java Document

- Under root directory, run `mvn javadoc:javadoc`.
- The document will be generated under `target/site/apidocs`.
