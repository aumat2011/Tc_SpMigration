# ePost RX Modernisation common module

## Technology Stack

- JDK 8
- Maven 3.6
- Infinispan 5.2.11.Final-redhat-1
- Junit v5
- **MS SQL Server 2019** (For 2014 Host specific installation required)
- Docker **20.10+**  (Please make sure your docker version is 20.10 or higher)
- lombok (for IDE : [setup guide](https://projectlombok.org/setup/overview))

<b>warning:</b> From MS no official docker image for SQL Server 2014 is available. To verify the submission, either host
specific installation or can be pulled from 3rd
party [mirrored](https://hub.docker.com/r/octopusdeploy/mssql-server-2014-express-windows) docker image.

## Running The Application with DB

1. **Start the database service** :

   Run the database from `docker` folder :

   ```bash
   docker-compose up -d --build db
   ```

   It will run a sql server 2019 instance in a container and create databases `epostrx` and `epostrx_workflow` and
   insert into demo data.

2. **Generate model and entity classes** :

    * Must follow step 1 to start the database service
    * Update `src/main/resources/db_config.properties` file if needed
    * Run the following command

       ```bash
       mvn compile exec:java@generate -Dexec.args="src/main/resources/db_config.properties"
       ```
    * The classes will be generated to below packages under source directory (`src/main/java`)

        ```bash
        com.humana.pharmacy.common.entity
        com.humana.pharmacy.common.model
        ```
3. **Install the lib to local repository** : (you may need to install the parent pom first)

    * Run the following command

        ```bash
        mvn clean install
        ```

## Usage

1. Add a dependency to maven pom file

```
        <dependency>
            <groupId>com.humana.pharmacy</groupId>
            <artifactId>epost-rx-common</artifactId>
            <version>1.0.0-SNAPSHOT</version>
        </dependency>
```

## Unit Tests and coverage

- Follow step 1 of `Running The Application with DB` to generate the classes
- Run `mvn clean test` to execute unit tests.
- Open `target/jacoco-ut/index.html` to see generated coverage report.

## Generate Java Document

- Under root directory, run `mvn javadoc:javadoc`.
- The document will be generated under `target/site/apidocs`.
