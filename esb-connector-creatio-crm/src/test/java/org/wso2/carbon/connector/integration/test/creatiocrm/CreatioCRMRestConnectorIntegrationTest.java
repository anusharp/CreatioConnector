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


    @Test(groups = {"wso2.ei"}, description = "Creatio Contact integration test for CRUD API call")
    public void contactTestWithMandatoryParameters() throws Exception {

        String methodName = "postContact";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_contactPost_optional.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 201);

        String cId = esbRestResponse.getBody().getString("Id");

        methodName = "getContact";
        String endpoint = getProxyServiceURLHttp(methodName) + "?contactId=" + cId;
        esbRestResponse = sendJsonRestRequest(endpoint, "GET",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);

        eiRequestHeadersMap.put("Accept-Charset", "UTF-8");
        eiRequestHeadersMap.put("Content-Type", "application/json");

        methodName = "updateContact";
        endpoint = getProxyServiceURLHttp(methodName) + "?contactId=" + cId;
        esbRestResponse = sendJsonRestRequest(endpoint, "PUT",
                apiRequestHeadersMap, "esb_contactUpdate_optional.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 204);

        methodName = "deleteContact";
        endpoint = getProxyServiceURLHttp(methodName) + "?contactId=" + cId;
        esbRestResponse = sendJsonRestRequest(endpoint, "DELETE",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 204);
    }

     @Test(groups = {"wso2.ei"}, description = "Creatio Case integration test for CRUD API call")
     public void caseTestWithMandatoryParameters() throws Exception {

     String methodName = "postCase";

     RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
     "POST", eiRequestHeadersMap, "esb_casePost_optional.json");
     Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 201);

     String cId = esbRestResponse.getBody().getString("Id");

     methodName = "getCase";
     String endpoint = getProxyServiceURLHttp(methodName) + "?caseId=" +cId;
     esbRestResponse = sendJsonRestRequest(endpoint, "GET",
     apiRequestHeadersMap);
     Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);

     methodName = "updateCase";
     endpoint = getProxyServiceURLHttp(methodName) + "?caseId=" +cId;
     esbRestResponse = sendJsonRestRequest(endpoint, "PUT",
             apiRequestHeadersMap, "esb_caseUpdate_optional.json");
     Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 204);

     methodName = "deleteCase";
     endpoint = getProxyServiceURLHttp(methodName) + "?caseId=" +cId;
     esbRestResponse = sendJsonRestRequest(endpoint, "DELETE",
     apiRequestHeadersMap);
     Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 204);
     }


    @Test(groups = {"wso2.ei"}, description = "Creatio Account integration test for CRUD API call")
    public void accountTestWithMandatoryParameters() throws Exception {

        String methodName = "postAccount";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_accountPost_optional.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 201);

        String cId = esbRestResponse.getBody().getString("Id");
        methodName = "getAccount";
        String endpoint = getProxyServiceURLHttp(methodName) + "?accountId=" + cId;
        esbRestResponse = sendJsonRestRequest(endpoint, "GET", eiRequestHeadersMap);

        methodName = "updateAccount";
        endpoint = getProxyServiceURLHttp(methodName) + "?accountId=" + cId;
        esbRestResponse = sendJsonRestRequest(endpoint, "PUT",
                apiRequestHeadersMap, "esb_accountUpdate_optional.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 204);

        methodName = "deleteAccount";
        endpoint = getProxyServiceURLHttp(methodName) + "?accountId=" + cId;
        esbRestResponse = sendJsonRestRequest(endpoint, "DELETE",
                apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 204);
    }

}
