package com.bansi.news_api;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static APIClient apiClient;
    private static Retrofit retrofit;

    private APIClient(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized APIClient getInstance(){
        if(apiClient == null){
            apiClient =new APIClient();
        }
        return apiClient;
    }

    public APIInterface getAPI(){
        return retrofit.create(APIInterface.class);
    }
}
