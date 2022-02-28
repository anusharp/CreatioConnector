/**
 *
 */
package org.wso2.carbon.connector.integration.test.creatiocrm;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.connector.integration.test.base.ConnectorIntegrationTestBase;
import org.wso2.connector.integration.test.base.RestResponse;

import java.util.HashMap;
import java.util.Map;

public class CreatioCRMRestConnectorIntegrationTest extends ConnectorIntegrationTestBase {
    private Map<String, String> eiRequestHeadersMap = new HashMap<String, String>();

    private Map<String, String> apiRequestHeadersMap = new HashMap<String, String>();

    private String apiUrlEndPoint;

    /**
     * Set up the environment.
     */
    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {

        String connectorName = System.getProperty("connector_name") + "-connector-" +
                System.getProperty("connector_version") + ".zip";
        addCertificatesToEIKeyStore("client-truststore.jks", "wso2carbon");
        init(connectorName);
        eiRequestHeadersMap.put("Accept-Charset", "UTF-8");
        eiRequestHeadersMap.put("Content-Type", "application/json");
    }


    @Test(groups = {"wso2.ei"}, description = "Creatio Contact integration test for POST API call")
    public void contactTestWithMandatoryParameters() throws Exception {

        String methodName = "postContact";

        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_contactPost_optional.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);

        String cId = esbRestResponse.getBody().getJSONObject("contact").getString("Id");
        methodName = "getContact";
        String apiEndPoint = apiUrlEndPoint + "/getContact?contactId=" +cId;
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET",
                apiRequestHeadersMap);
        apiEndPoint = apiUrlEndPoint + "/updateContact?contactId=" +cId;
        apiRestResponse = sendJsonRestRequest(apiEndPoint, "PUT",
                apiRequestHeadersMap, "esb_contactUpdate_optional.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);

        apiEndPoint = apiUrlEndPoint + "/deleteContact?contactId=" +cId;
        apiRestResponse = sendJsonRestRequest(apiEndPoint, "DELETE",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
    }


    @Test(groups = {"wso2.ei"}, description = "Creatio Case integration test for POST API call")
    public void caseTestWithMandatoryParameters() throws Exception {

        String methodName = "postCase";

        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_casePost_optional.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        String cId = esbRestResponse.getBody().getJSONObject("case").getString("Id");
        methodName = "getCase";
        String apiEndPoint = apiUrlEndPoint + "/getCase?caseId=" +cId;
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET",
                apiRequestHeadersMap);
        methodName = "updateCase";
        apiEndPoint = apiUrlEndPoint + "/updateCase?contactId=" +cId;
        apiRestResponse = sendJsonRestRequest(apiEndPoint, "PUT",
                apiRequestHeadersMap, "esb_caseUpdate_optional.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        methodName = "deleteCase";
        apiEndPoint = apiUrlEndPoint + "/deleteCase?contactId=" +cId;
        apiRestResponse = sendJsonRestRequest(apiEndPoint, "DELETE",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
    }


    @Test(groups = {"wso2.ei"}, description = "Creatio Account integration test for POST API call")
    public void accountTestWithMandatoryParameters() throws Exception {

        String methodName = "postAccount";

        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_accountPost_optional.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);

        String cId = esbRestResponse.getBody().getJSONObject("account").getString("Id");
        methodName = "getAccount";
        String apiEndPoint = apiUrlEndPoint + "/getAccount?contactId=" +cId;
        esbRestResponse = sendJsonRestRequest(apiEndPoint,"GET", eiRequestHeadersMap);

        apiEndPoint = apiUrlEndPoint + "/updateAccount?contactId=" +cId;
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "PUT",
                apiRequestHeadersMap, "esb_accountUpdate_optional.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);

        apiEndPoint = apiUrlEndPoint + "/deleteAccount?contactId=" +cId;
        apiRestResponse = sendJsonRestRequest(apiEndPoint, "DELETE",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
    }

}
