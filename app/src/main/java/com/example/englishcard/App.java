package com.example.englishcard;

import android.app.Application;

import com.example.englishcard.network.PixabyApi;
import com.example.englishcard.network.RetrofitClient;

public class App extends Application {
    public static RetrofitClient retrofitClient;
    public  static PixabyApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        api= retrofitClient.providePixabayApi();
    }
}
