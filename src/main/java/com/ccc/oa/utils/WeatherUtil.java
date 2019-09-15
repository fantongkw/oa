package com.ccc.oa.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @ClassName: WeatherUtil
 * @Author: Administrator
 * @Description: 获取天气工具
 * @Date: 2018/12/25 19:43
 * @Version: 1.0
 **/

public class WeatherUtil {

    /**
     * @return java.net.URI
     * @Description 构建url请求接口
     * @Date 2018/12/25 19:44
     * @Param [city]
     **/

    private static URI weatherUrl(String city) {
        String key = "70ed382c4a6ada2b78b0415affc6b43a";
        URI uri = null;
        try {
            uri = new URI("https://restapi.amap.com/v3/weather/weatherInfo?key=" + key + "&city=" + city);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return uri;
    }

    /**
     * @return org.apache.http.impl.client.CloseableHttpClient
     * @Description 创建一个http请求
     * @Date 2018/12/25 19:44
     * @Param []
     **/

    private static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (x509Certificates, s) -> true).build();
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    /**
     * @return java.lang.String
     * @Description 获取天气信息
     * @Date 2018/12/25 19:44
     * @Param [city]
     **/

    public static String request(String city) {
        CloseableHttpClient httpClient = createSSLClientDefault();
        HttpGet httpGet = new HttpGet(weatherUrl(city));
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setRedirectsEnabled(true) //默认允许自动重定向
                .build();
        httpGet.setConfig(requestConfig);
        String result = "";
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
