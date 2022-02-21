# Creatio REST EI Connector

The Creatio REST Connector allows you to work with below CRM functinalities

Contact CRUD operation
Account CRUD operation
Case CRUD operation

## Compatibility

| Connector version | Supported Creatio REST API version | Supported WSO2 ESB/EI version |
| ------------- | ------------- | ------------- |
| [1.0.0]| v32.0 | EI 6.5.0, EI 6.6.0, EI 7.0.X, EI 7.1.0 |

## Getting started

## Building From the Source


Follow the steps given below to build the Class mediation whihc is dependecy library:

1. Get a clone or download the source from [Github](https://github.com/MitraInnovationRepo/wso2-connectors/).
2. Run the following Maven command from the `CookieMediator` directory: `mvn clean install`.
3. Copy the CookieMediator-1.0.0-SNAPSHOT.jar into <EI-HOME>/micro-integrator/dropins directory


## Configure EI_Home

Add the configurtaion in deployment.toml for Creatio login infomrtaion
[system.parameter]
isEncryptedCreatioConfig = "false"
creatioCRMUName = "xxxxxxx"
creatioCRMPWord = "xxxxxxxxx"
creatioCRMEP = "hostname"

Ex:
[system.parameter]
isEncryptedCreatioConfig = "false"
creatioCRMUName = "Supervisor"
creatioCRMPWord = "Supervisor"
creatioCRMEP = "https://112744-crm-bundle.creatio.com"

## Configure Intregation Studio

Follow the steps given below to Configure the Intregation Studio

1. Run Intregation Studio

2. Add "CreatioConnectorTestSuit" to the Intregation Studio Workspace

3. Add WSO2 Enterprise MI 1.2.0 server in to the intregation studio and setup the CARBON_HOME as <EI-HOME>/micro-integrator and add "CretioConnectorTestSuitCompositeExporter" to the server

## Configure the Intregation Studio embeded server

Follow the steps given below to Configure the Intregation Studio embeded server

1. Click server configuration button to open the embeded server configuration window

2. Add the configurtaion in deployment.toml for Creatio login infomrtaion
[system.parameter]
isEncryptedCreatioConfig = "false"
creatioCRMUName = "xxxxxxx"
creatioCRMPWord = "xxxxxxxxx"
creatioCRMEP = "hostname"

Ex:

[system.parameter]
isEncryptedCreatioConfig = "false"
creatioCRMUName = "Supervisor"
creatioCRMPWord = "Supervisor"
creatioCRMEP = "https://112744-crm-bundle.creatio.com"

3. Add CookieMediator-1.0.0-SNAPSHOT.jar to the Dropins directory

4. Save the changes

## Run Unit Test Suit

Follow the steps given below to run the unit test suit

1. Deploy the car app to <EI-HOME>/micro-integrator/repository/deployment/server/carbonapps folder in WSO2 EI

2. Run the CretioConnectorTestSuitCompositeExporter on the WSO2 Enterprise MI 1.2.0 server

3. Run Unit test suit 


