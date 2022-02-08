package com.mitra.creatiocrm.headers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.apache.synapse.MessageContext;

public class BPMCSRFCookie extends AbstractMediator {

    private static final Log log = LogFactory.getLog(BPMCSRFCookie.class);
    private static String COOKIE_NAME = "Set-Cookie";
    private static String COOKIE_BPMCSRF = "BPMCSRF=";
    private static String AUTH_PROPERTY = "authCookie";
    private static String AUTH_COMPLETE_PROPERTY = "authCompleteCookie";
    private static String BPMSESSIONID = "BPMSESSIONID";
    private static String USERNAME = "UserName";
    private static String BPMLOADER = "BPMLOADER";
    private static String ASPXAUTH = ".ASPXAUTH";
    private static String BPMCSRF = "BPMCSRF";

    public boolean mediate(MessageContext context) {

        String allCookies = "";
        Map<String, String> cookiesMap = new HashMap<String, String>();

        Object headers = ((Axis2MessageContext) context)
                .getAxis2MessageContext().getProperty(org.apache.axis2.context.MessageContext.TRANSPORT_HEADERS);
        Map<String, String> headerMap = (Map) headers;
        if (headerMap.containsKey(COOKIE_NAME)) {
            String cookies = headerMap.get(COOKIE_NAME);
            String authCookiesVal = cookies.split(";")[0];
            if (cookies.contains(COOKIE_BPMCSRF)) {
                context.setProperty(AUTH_PROPERTY, authCookiesVal.split(":")[1]);
            }
            headerMap.remove(COOKIE_NAME);
            cookiesMap.put( authCookiesVal.split("=")[0], cookies.split(";")[0]);
        }
        Map<String, List<String>> excessHeadersMap = (Map) ((Axis2MessageContext) context).getAxis2MessageContext().getProperty("EXCESS_TRANSPORT_HEADERS");
        if (excessHeadersMap.containsKey(COOKIE_NAME)) {
            List<String> cookies = excessHeadersMap.get(COOKIE_NAME);
            for (String cookie : cookies) {
                String authCookiesVal = cookie.split(";")[0];
                cookiesMap.put( authCookiesVal.split("=")[0], cookie.split(";")[0]);
                if (cookie.contains(COOKIE_BPMCSRF)) {
                    context.setProperty(AUTH_PROPERTY, authCookiesVal.split("=")[1]);
                }
            }
            excessHeadersMap.remove(COOKIE_NAME);
        }
        allCookies = setCookieVal(cookiesMap, BPMSESSIONID, allCookies);
        allCookies = setCookieVal(cookiesMap, USERNAME, allCookies);
        allCookies = setCookieVal(cookiesMap, BPMLOADER, allCookies);
        allCookies = setCookieVal(cookiesMap, ASPXAUTH, allCookies);
        allCookies = setCookieVal(cookiesMap, BPMCSRF, allCookies);

        context.setProperty(AUTH_COMPLETE_PROPERTY, allCookies);
        return true;
    }

    private String setCookieVal(Map<String, String> cookieMap, String cookieName, String totalCookies){
        if (cookieMap.containsKey(cookieName)){
            if(totalCookies.isEmpty()) {
                totalCookies = cookieMap.get(cookieName);
            } else {
                totalCookies = totalCookies + "; " + cookieMap.get(cookieName);
            }
        }
        return totalCookies;
    }
}
