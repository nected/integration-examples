package com.nected.loan.example.configuration;

import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

@Configuration
public class RestTemplateConfig {

  @Bean
  public RestTemplate restTemplate() {
    // Create a RestTemplate instance
    RestTemplate restTemplate = new RestTemplate();

    // Configure SSL
    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
    requestFactory.setHttpClient(HttpClientUtil.getHttpClient());

    // Set the request factory
    restTemplate.setRequestFactory(requestFactory);
    return restTemplate;
  }
}

@Bean
public HttpComponentsClientHttpRequestFactory sslFactory() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
    final TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

    final SSLContext sslContext =SSLContexts.custom()
                    .loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();

    final SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

    final CloseableHttpClient httpClient = HttpClients.custom()
                    // .setMaxConnPerRoute(250)
                    // .setMaxConnTotal(250)
                    .setSSLSocketFactory(csf)
                    // during the SSL check we have also the verification of the host name,
                    // this can be skipped to like this:
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();

    final HttpComponentsClientHttpRequestFactory requestFactory =
                    new HttpComponentsClientHttpRequestFactory();

    requestFactory.setHttpClient(httpClient);

    return requestFactory;
}
