package com.example.englishcard.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.englishcard.network.PixabyApi;
import com.example.englishcard.network.models.Hit;
import com.example.englishcard.network.models.PixabayResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PixaBayRepository {
    PixabyApi api;

    @Inject
    public PixaBayRepository(PixabyApi api) {
        this.api = api;
    }

    public MutableLiveData<List<Hit>> getImage(String word){
        MutableLiveData<List<Hit>> listImages = new MutableLiveData<>();
        api.getImage(word).enqueue(new Callback<PixabayResponse>() {
            @Override
            public void onResponse(@NonNull Call<PixabayResponse> call, @NonNull Response<PixabayResponse> response) {
                if (response.isSuccessful()){
                    listImages.postValue(response.body().getHits());
                }
            }

            @Override
            public void onFailure(Call<PixabayResponse> call, Throwable t) {
                Log.e("ololo", t.getMessage());
            }
        });
        return listImages;
    }
}
