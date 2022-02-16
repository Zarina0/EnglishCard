package com.example.englishcard.network;

import com.example.englishcard.network.model.PixabayResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabyApi {
    @GET("/api")
    Call<PixabayResponse> getImage(@Query("key")String key,@Query("q")String keyWord );



}
