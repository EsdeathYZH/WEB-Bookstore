package com.esdeath.androidbookstore.network;

import com.esdeath.androidbookstore.utils.GsonDateTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL="http://47.106.75.68:8080/";

    private static BookStoreApi bookStoreApi;

    public static BookStoreApi getBookStoreApi(){
        if(bookStoreApi == null){
            Executor executor = Executors.newCachedThreadPool();
            final Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Date.class,new GsonDateTypeAdapter())
                    .create();

            OkHttpClient client = new OkHttpClient.Builder()
                    //添加应用拦截器
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .callbackExecutor(executor)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();

            bookStoreApi = retrofit.create(BookStoreApi.class);
        }
        return bookStoreApi;
    }
}
