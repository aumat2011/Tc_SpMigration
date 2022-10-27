# ePost RX Modernisation

## Technology Stack

- JDK 8
- Maven 3.6
- SpringBoot 2.6.2
- Infinispan 5.2.11.Final-redhat-1
- Junit v5
- **MS SQL Server 2019** (For 2014 Host specific installation required)
- Docker **20.10+**  (Please make sure your docker version is 20.10 or higher)
- lombok (for IDE : [setup guide](https://projectlombok.org/setup/overview))

<b>warning:</b> From MS no official docker image for SQL Server 2014 is available. To verify the submission, either host
specific installation or can be pulled from 3rd
party [mirrored](https://hub.docker.com/r/octopusdeploy/mssql-server-2014-express-windows) docker image.

## Maven modules

- epost-rx-common (entities, models and common services)
- epost-rx-phase1 (services that developed by phase1)
- epost-rx-phase2 (services that developed by phase2)
- epost-rx-demo (application that shows how to use these libs and to verify these services)
