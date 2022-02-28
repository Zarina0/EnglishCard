package com.example.englishcard.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.englishcard.db.app_data_base.AppDataBase;
import com.example.englishcard.models.api_models.Hit;
import com.example.englishcard.models.api_models.PixabayResponse;
import com.example.englishcard.models.db_models.CategoryModel;
import com.example.englishcard.models.db_models.WordsModel;
import com.example.englishcard.network.PixabyApi;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PixaBayRepository {
    PixabyApi api;
    AppDataBase appDataBase;
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

    public LiveData<List<CategoryModel>> getCategories() {
        MutableLiveData<List<CategoryModel>> categoryList = new MutableLiveData<>();
        categoryList.setValue((List<CategoryModel>) appDataBase.getDatabase().categoryDao().getAll());
        return categoryList;
    }

    public LiveData<List<WordsModel>> getWords(String userCategory) {
        MutableLiveData<List<WordsModel>> wordsList = new MutableLiveData<>();
        wordsList.setValue((List<WordsModel>) appDataBase.getDatabase().wordsDao().getAll(userCategory));
        return wordsList;
    }
}
