package com.witch.pan.utils;


import com.witch.common.pojo.BizException;
import com.witch.common.pojo.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Yuuki
 * 处理HTTP请求和响应
 */
public class OKHttpUtils {
    private static final int TIME_OUT_SECONDS = 8;

    private static OkHttpClient.Builder getClientBuilder() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder().followRedirects(false).retryOnConnectionFailure(false);
        clientBuilder.connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS).readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS);
        return clientBuilder;
    }

    private static Request.Builder getRequestBuilder(Map<String, String> header) {
        Request.Builder requestBuilder = new Request.Builder();
        if(header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                String key = entry.getKey();
                String value;
                if(entry.getValue() == null) {
                    value = "";
                } else {
                    value = entry.getValue();
                }
                requestBuilder.addHeader(key, value);
            }
        }
        return requestBuilder;
    }

    public static String getRequest(String url) {
        ResponseBody responseBody = null;
        try {
            OkHttpClient.Builder clientBuilder = getClientBuilder();
            Request.Builder requestBuilder = getRequestBuilder(null);
            OkHttpClient client = clientBuilder.build();
            Request request = requestBuilder.url(url).build();
            Response response = client.newCall(request).execute();
            responseBody = response.body();
            String responseStr = responseBody.string();
            return  responseStr;
        }catch (SocketTimeoutException | ConnectException e){
            throw new BizException("请求超时");
        } catch (IOException e) {
            return null;
        } finally {
            if(responseBody != null) {
                responseBody.close();
            }
        }
    }
}
