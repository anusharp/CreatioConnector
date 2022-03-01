# Creatio REST EI Connector

The Creatio REST Connector allows you to work with below CRM functionalities

Contact CRUD operation
Account CRUD operation
Case CRUD operation

## Compatibility

| Connector version | Supported Creatio REST API version | Supported WSO2 ESB/EI version |
| ------------- | ------------- | ------------- |
| [1.0.0]| v32.0 | EI 6.5.0, EI 7.0.X, EI 7.1.0 |

## Getting started

## Building From the Source

Follow the steps given below to build the Creatio REST connector from the source code:

1. Get a clone or download the source from [Github](https://github.com/anusharp/CreatioConnector).
2. Run the following Maven command from the `esb-connector-creatiocrm` directory: `mvn clean install`.
3. The Creatio connector zip file is created in the `esb-connector-creatiocrm/target` directory

Follow the steps given below to build the Class mediation which is dependency library:

1. Get a clone or download the source from [Github](https://github.com/anusharp/CreatioConnector).
2. Run the following Maven command from the `CookieMediator` directory: `mvn clean install`.
3. Copy the CookieMediator-1.0.0-SNAPSHOT.jar into <EI-HOME>/micro-integrator/dropins directory

#### Deploy connector with mediator implemented CAR file

1. Add connector creatiocrm-connector-1.1.0.zip to Intergration Studio inorder to work with EI tooling, see [Working with Connectors via Tooling](https://docs.wso2.com/display/EI650/Working+with+Connectors+via+Tooling) and create the mediation

2. Import the creatio server certificate to the EI client keystore using either the following command or the EI Management Console.

```
keytool -importcert -file <certificate_file> -keystore <EI>/repository/resources/security/client-truststore.jks -alias "creatio"

```
3. Deploy the car app to <EI-HOME>/micro-integrator/repository/deployment/server/carbonapps folder in WSO2 EI

4. Add the configurataion in deployment.toml for Creatio login information. This is from the Creatio CRM server admin privilege user account 

[system.parameter]
isEncryptedCreatioConfig = "false"
creatioCRMUName = "xxxxxxx"
creatioCRMPWord = "xxxxxxxxx"
creatioCRMEP = "hostname"

ex:
[system.parameter]
isEncryptedCreatioConfig = "false"
creatioCRMUName = "admin"
creatioCRMPWord = "admin"
creatioCRMEP = "https://112755-crm-bundle.creatio.com"

If the above configuration want to encrypt, use below configuration

[system.parameter]
isEncryptedCreatioConfig = "true"

[secrets]
creatioCRMUName  = "[user_name]"
creatioCRMPWord  = "[pasword]"
creatioCRMEP = "[hostname]"
Ex:

[system.parameter]
isEncryptedCreatioConfig = "true"

[secrets]
creatioCRMUName  = "[admin]"
creatioCRMPWord  = "[1qaz2wsx&]"
creatioCRMEP = "[https://112744-crm-bundle.creatio.com]"

If you are going to use in EI 6.5.0, you need to update below format as system properties in <product_location>/bin/integrator.sh 
   
    -DisEncryptedCreatioConfig="false" \
    -DcreatioCRMUName="AdminUser" \
    -DcreatioCRMPWord="AdminUser" \
    -DcreatioCRMEP="https://creatio.com" \

5. isEncryptedCreatioConfig = true", then execute the below command encrypt the above config values (at first time only)./ciphertool.sh -Dconfigure, provide the password of wso2carbon.jks, default value is 'wso2carbon' if encrypted values need to be configured.

6. Start the WSO2 EI instance like this. (you need to provide the password of wso2carbon.jks at the execution, default value is 'wso2carbon' if values are encrypted )

  ./micro-integrator.sh
7. Send the sample request to created mediation using CreatioCRM connector.

## Headers

Headers :
Content-Type: application/json
Content-Length :


8. ## Contact Operations


8.1. Create Contact

Creatio Contact operation used in EI mediation. The contact payload format display in below

<creatiocrm.contactCreate/>


Body Format:
{
   "Name": String,
   "AccountId": String,
   "JobTitle": String,
   "BirthDate": String
   "Phone": String,
   "MobilePhone": String,
   "Email": String,
   "Completeness": Int,
   "Age":Int
}
Sample Request:
{
   "Name": "API Test",
   "AccountId": "e6574af1-3e92-4099-958e-e798f52ee016",
   "JobTitle": "Marketing manager",
   "BirthDate": "0001-01-01T00:00:00Z",
   "Phone": "",
   "MobilePhone": "+1 213 566 34 22",
   "Email": "test@gmail",
   "Completeness": 30,
   "Age": 19
}

8.2. Get Contact

Ex:
   <creatiocrm.contactGet>
       <id>{$ctx:contactId}</id>
   </creatiocrm.contactGet>
  
8.3. Patch Contact

<creatiocrm.contactUpdate>
<id>{$ctx:contactId}</id>
</creatiocrm.contactUpdate>

8.4. Delete Contact

<creatiocrm.contactDelete>
<id>{$ctx:contactId}</id>
</creatiocrm.contactDelete>

9. ## Case Operations
9.1. Create Case

  <creatiocrm.caseCreate/>
Creatio case operation used in EI mediation. The Case payload format display in below

Body Format:
{
   "Number": String,
   "RegisteredOn": Datetime,
   "Subject": String,
   "Symptoms": String,
   "Notes": String,
   "AccountId": String,
   "ContactId": String,
   "SolutionRemains": Double
}

Body Example:
{
   "Number": "SR00000045",
   "RegisteredOn": "2021-07-16T11:00:00Z",
   "Subject": "Consultation on functionality",
   "Symptoms": "Comparison of different DBMS in accordance with the customer's needs",
   "Notes": "",
   "AccountId": "ff7e089f-1fe9-4ca9-bc30-2d76ad39d178",
   "ContactId": "00b34750-2feb-4545-b233-153502326f3c",
   "SolutionRemains": 0.0
}

9.2. Get Case

   <creatiocrm.caseGet>
       <id>{$ctx:caseId}</id>
   </creatiocrm.caseGet>
  
9.3. Patch Case

<creatiocrm.caseUpdate>
<id>{$ctx:caseId}</id>
</creatiocrm.caseUpdate>

9.4. Delete Case

<creatiocrm.caseDelete>
<id>{$ctx:caseId}</id>
</creatiocrm.caseDelete>


10. ## Account Operations


10.1. Account Contact

  <creatiocrm.accountCreate/>
Creatio Account operation used in EI mediation. The Account payload format display in below

Body Format:
{
   "Name": String,
   "ProcessListeners": integer,
   "Code": String,
   "Phone": Timestamp,
   "AdditionalPhone": String,
   "Fax": String,
   "Web": String,
   "Address": String,
   "Notes":String
}
Sample Request:
{
   "Name": "API Test",
   "ProcessListeners": 0,
   "Code": "73",
   "Phone": "+1 206 480 3801",
   "AdditionalPhone": "+1 206 480 4495",
   "Fax": "",
   "Web": "www.infocom-global.com",
   "Address": "48 Pilgrim Street",
   "Notes": ""
}


10.2. Get Account

   <creatiocrm.accountGet>
       <id>{$ctx:accountId}</id>
   </creatiocrm.accountGet>
  
10.3. Patch Account

<creatiocrm.accountUpdate>
<id>{$ctx:accountId}</id>
</creatiocrm.accountUpdate>

10.4. Delete Account

<creatiocrm.accountDelete>
<id>{$ctx:accountId}</id>
</creatiocrm.accountDelete>







