package com.example.englishcard.network;

import com.example.englishcard.network.models.PixabayResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabyApi {
    @GET("/api?key=25686965-0239caa31958b6bb6157f5e9b")
    Call<PixabayResponse> getImage(@Query("q")String keyWord );



}
