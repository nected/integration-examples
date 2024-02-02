package com.nected.loan.example.configuration;

import javax.net.ssl.SSLContext;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.ssl.SSLContexts;

public class HttpClientUtil {

    public static HttpClient getHttpClient() {
        try {
            // Create SSL context that allows all certificates
            SSLContextBuilder sslContextBuilder = SSLContexts.custom().loadTrustMaterial(new TrustAllStrategy());
            SSLContext sslContext = sslContextBuilder.build();

            // Create HttpClient with the custom SSL context
            HttpClient httpClient = HttpClients.custom().setSSLContext(sslContext).build();

            return httpClient;
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
            return null;
        }
    }
}
