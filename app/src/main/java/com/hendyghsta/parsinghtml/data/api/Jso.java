package com.hendyghsta.parsinghtml.data.api;

import com.github.florent37.retrojsoup.RetroJsoup;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by hendyghsta on 08/14/2018.
 */
public class Jso {
    private static OkHttpClient getClient(){
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS);
        httpBuilder.interceptors().add(chain -> chain.proceed(chain.request()));
        return httpBuilder.build();
    }


    public static LinkWeb web(String url){
        RetroJsoup jsoup = new RetroJsoup.Builder()
                .url(url)
                .client(getClient())
                .build();
        return jsoup.create(LinkWeb.class);
    }




}
