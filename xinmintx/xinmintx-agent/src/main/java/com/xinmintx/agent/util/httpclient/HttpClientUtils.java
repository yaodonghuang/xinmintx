package com.xinmintx.agent.util.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

public class HttpClientUtils {

    protected static HttpClient httpClient = HttpClientFactory.createHttpClient(100, 10);

    private static Map<String, HttpClient> httpClient_mchKeyStore = new HashMap<String, HttpClient>();

    public static void init(int maxTotal, int maxPerRoute) {
        httpClient = HttpClientFactory.createHttpClient(maxTotal, maxPerRoute);
    }

    /**
     * 初始化 MCH HttpClient KeyStore
     *
     * @param mch_id
     * @param keyStoreFilePath
     */
    public static void initMchKeyStore(String mch_id, String keyStoreFilePath) {
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream instream = new FileInputStream(new File(keyStoreFilePath));
            keyStore.load(instream, mch_id.toCharArray());
            instream.close();
            HttpClient httpClient = HttpClientFactory.createKeyMaterialHttpClient(keyStore, mch_id,
                    new String[]{"TLSv1"});
            httpClient_mchKeyStore.put(mch_id, httpClient);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HttpResponse execute(HttpUriRequest request) {
        try {
            return httpClient.execute(request);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T execute(HttpUriRequest request, ResponseHandler<T> responseHandler) {
        try {
            return httpClient.execute(request, responseHandler);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 数据返回自动JSON对象解析
     *
     * @param request
     * @param clazz
     * @return
     */
    public static <T> T executeJsonResult(HttpUriRequest request, Class<T> clazz) {
        return execute(request, JsonResponseHandler.createResponseHandler(clazz));
    }

    /**
     * 数据返回自动XML对象解析
     *
     * @param request
     * @param clazz
     * @return
     */
    public static <T> T executeXmlResult(HttpUriRequest request, Class<T> clazz) {
        return execute(request, XmlResponseHandler.createResponseHandler(clazz));
    }

    /**
     * MCH keyStore 请求 数据返回自动XML对象解析
     *
     * @param mch_id
     * @param request
     * @param clazz
     * @return
     */
    public static <T> T keyStoreExecuteXmlResult(String mch_id, HttpUriRequest request, byte[] keyStoreData,
                                                 Class<T> clazz) {
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            InputStream instream = new ByteArrayInputStream(keyStoreData);
            keyStore.load(instream, mch_id.toCharArray());
            instream.close();
            HttpClient httpClient = HttpClientFactory.createKeyMaterialHttpClient(keyStore, mch_id,
                    new String[]{"TLSv1"});
            return httpClient.execute(request, XmlResponseHandler.createResponseHandler(clazz));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T ExecuteXmlResultWithKeyStore(String CertPassword, HttpUriRequest request,
                                                     Class<T> clazz, FileInputStream instream) {
//		FileInputStream instream =null;
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
//			String filepath= HttpClientUtils.class.getClassLoader().getResource("apiclient_cert.p12").getFile();
//		     instream = new FileInputStream(new File(filepath));
            keyStore.load(instream, CertPassword.toCharArray());
            // 创建sslcontext
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, CertPassword.toCharArray()).build();
            // 只支持  TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

            httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            return httpClient.execute(request, XmlResponseHandler.createResponseHandler(clazz));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}