package com.digitahouse.tercerentregablenicolasetchemaite.model.DAO;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApp {

    protected Retrofit retrofit;

    public RetrofitApp(String baseUrl) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder();

        builder.addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl);

        retrofit = builder.client(okHttpClient.build()).build();
    }
}

