## Integration tests for WSO2 EI Kafka connector

### Pre-requisites:

- Maven 3.x
- Java 1.8

### Tested Platform:

- Ubuntu 16.04
- WSO2 EI 6.5.0

STEPS:

1. Download EI 6.5.0 by navigating the following the URL: https://wso2.com/integration/.

2. Get a clone or download the source from [Github](https://github.com/MitraInnovationRepo/wso2-connectors/).
   Run the following Maven command from the `CookieMediator` directory: `mvn clean install`.
   Copy the CookieMediator-1.0.0-SNAPSHOT.jar into <EI-HOME>/dropins directory
3. Create new file in extracted zip file <product_location>/conf/file.properties updated the below information.

isEncryptedCreatioConfig = "false" 
creatioCRMUName = "<creatio_admin_username>"
creatioCRMPWord = "<creatio_admin_username>"
creatioCRMEP = "<creatio_servername>" ex :  "https://112755-crm-bundle.creatio.com"

4. Compress the EI zip and place it in "{CONNECTOR_HOME}/esb-connector-creatio-crm/repository/"

5. Go to "{CONNECTOR_HOME}/esb-connector-creatio-crm/" and type "mvn clean install -Dskip-tests=false" to test and build.
