package com.vk.utils;

import aquality.selenium.browser.AqualityServices;
import com.vk.api.APIRequestFields;
import com.vk.utils.configuration.ConfigValuesConfiguration;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

import java.net.*;
import java.util.List;

public class URLBuilder {

    public static URL urlBuilder(String methodName, List<NameValuePair> list) {
        URIBuilder builder = new URIBuilder();
        builder.setScheme(ConfigValuesConfiguration.urlScheme);
        builder.setHost(ConfigValuesConfiguration.urlHost);
        builder.setPath(String.format("%s%s", ConfigValuesConfiguration.urlPath, methodName));
        builder.addParameters(list);
        builder.addParameter(APIRequestFields.ACCESSS_TOKEN.getField(), ConfigValuesConfiguration.accessToken);
        builder.addParameter(APIRequestFields.V.getField(), ConfigValuesConfiguration.version);
        URL url = null;
        try {
            url = builder.build().toURL();
        } catch (MalformedURLException e) {
            AqualityServices.getLogger().error(String.valueOf(e));
        } catch (URISyntaxException e) {
            AqualityServices.getLogger().error(String.valueOf(e));
        }
        return url;
    }
}
