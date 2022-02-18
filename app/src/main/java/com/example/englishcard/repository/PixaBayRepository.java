package com.example.englishcard.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.englishcard.App;
import com.example.englishcard.network.model.Hit;
import com.example.englishcard.network.model.PixabayResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PixaBayRepository {

    static PixaBayRepository pixaBayRepository;

    public static PixaBayRepository getInstance(){
        if (pixaBayRepository == null){
            return new PixaBayRepository();
        }
        return pixaBayRepository; }


    public MutableLiveData<List<Hit>> getImage(String word){
        MutableLiveData<List<Hit>> listImages = new MutableLiveData<>();
        App.api.getImage(word).enqueue(new Callback<PixabayResponse>() {
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
