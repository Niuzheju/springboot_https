package com.niuzj.springboot_https;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sun.security.ssl.SSLSocketFactoryImpl;

import java.io.IOException;

/**
 * http://www.leftso.com/blog/396.html
 * @author niuzheju
 * @date 2018/11/21 21:58
 */
public class Test01 {
    private String url = "https://localhost:8080";

    @Test
    public void test01() {
        ResponseEntity<String> entity = new RestTemplate().exchange(url, HttpMethod.GET, null, String.class);
        System.out.println(entity.getBody());
    }

    @Test
    public void test02() throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse execute = client.execute(get);
        System.out.println(execute.getEntity());
    }

    @Test
    public void test03() throws Exception {
        TrustStrategy strategy = (cert, authType) -> true;
        SSLSocketFactory factory = new SSLSocketFactory(strategy, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("https", 8080, factory));
        ClientConnectionManager manager = new PoolingClientConnectionManager(registry);
        DefaultHttpClient client = new DefaultHttpClient(manager);
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse execute = client.execute(get);
        System.out.println(execute.getStatusLine().getStatusCode());
    }

}
