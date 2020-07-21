package com.kyny.api;

import android.util.Log;

import com.kyny.studyretrofit.BuildConfig;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: guoxuxiong
 * 时间:2019/6/19
 * 邮箱:553605867@qq.com
 * 描述:
 */
public class RetrofitWrapper {
    private Retrofit mRetrofit;
    private final OkHttpClient.Builder builder;
    HttpLoggingInterceptor loggingInterceptor;
    private  String mAddress="http://172.16.3.231:9998";
    public static RetrofitWrapper getInstance()
    {
        RetrofitWrapper instance;
        synchronized (RetrofitWrapper.class)
        {
        instance=new RetrofitWrapper();
        }
        return  instance;
    }

    private  RetrofitWrapper()
    {   builder = new OkHttpClient.Builder();
        //打印网络请求相关日志
        loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("retrofitLog: ", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);//
        OkHttpClient build = builder.build();
        initBuilder(mAddress,build);
        }

    private void initBuilder(String mAddress, OkHttpClient build) {
        initSSL();
        initTimeOut();

        if(BuildConfig.DEBUG){
            //不需要错误重连
            builder.retryOnConnectionFailure(false);
        }else {
     //       错误重连
            builder.retryOnConnectionFailure(true);
        }
        //获取实例
        mRetrofit = new Retrofit
                //设置OKHttpClient,如果不设置会提供一个默认的
                .Builder()
                //设置baseUrl
                .baseUrl(mAddress)
                //添加转换器Converter(将json 转为JavaBean)，用来进行响应数据转化(反序列化)的ConvertFactory
                .addConverterFactory(GsonConverterFactory.create())
                //添加自定义转换器
//                .addConverterFactory(JsonConverterFactory.create())
                //添加rx转换器，用来生成对应"Call"的CallAdapter的CallAdapterFactory
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .client(build)
                .build();
    }
    /**
     * 初始化完全信任的信任管理器
     */
    @SuppressWarnings("deprecation")
    private void initSSL() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            }};

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置读取超时时间，连接超时时间，写入超时时间值
     */
    private void initTimeOut() {
        builder.readTimeout(2000, TimeUnit.SECONDS);
        builder.connectTimeout(1000, TimeUnit.SECONDS);
        builder.writeTimeout(2000, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
    }
    public <T> T create(final  Class<T> tClass)
    {
        return mRetrofit.create(tClass);
    }
}
