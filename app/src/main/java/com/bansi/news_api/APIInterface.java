package com.bansi.news_api;

import com.bansi.news_api.model.Headlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("top-headlines")
    Call<Headlines> getHeadlines(
        @Query("country") String country,
        @Query("apiKey") String apiKey
    );


}
