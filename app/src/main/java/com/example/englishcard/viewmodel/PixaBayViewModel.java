package com.example.englishcard.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.englishcard.helpers.preference_helper.Preferences;
import com.example.englishcard.helpers.room_helper.Room;
import com.example.englishcard.models.api_models.Hit;
import com.example.englishcard.models.db_models.CategoryModel;
import com.example.englishcard.models.db_models.WordsModel;
import com.example.englishcard.repository.PixaBayRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PixaBayViewModel extends ViewModel {
    public MutableLiveData<List<Hit>> hitMutableLiveData = new MutableLiveData<>();
    public LiveData<List<CategoryModel>> categoryList = new MutableLiveData<>();
    public LiveData<List<WordsModel>> wordsList = new MutableLiveData<>();
    PixaBayRepository repository;
    Preferences preferences;
    Room roomHelper;

   @Inject
    public PixaBayViewModel(PixaBayRepository repository, Preferences preferences, Room roomHelper) {
       this.repository= repository;
       this.preferences=preferences;
       this.roomHelper = roomHelper;
    }


    public MutableLiveData<List<Hit>> getImages(String word){
        hitMutableLiveData = repository.getImage(word);
        return hitMutableLiveData;
    }
    public boolean getBoolean() {
        return preferences.getBoolean();
    }

    public void setBoolean(boolean isShown) {
        preferences.setBoolean(isShown);
    }
    public void insertCategory(CategoryModel categoryModel) {
        roomHelper.insertCategory(categoryModel);
    }

    public LiveData<List<CategoryModel>> getCategories() {
        return categoryList = repository.getCategories();
    }

    public void insertWord(WordsModel wordsModel) {
        roomHelper.insertWord(wordsModel);

    }

    public LiveData<List<WordsModel>> getWords(String userCategory) {
        return wordsList = repository.getWords(userCategory);
    }

}
