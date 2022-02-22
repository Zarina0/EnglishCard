package com.example.englishcard.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.englishcard.boarding.data.Preferences;
import com.example.englishcard.network.models.Hit;
import com.example.englishcard.repository.PixaBayRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PixaBayViewModel extends ViewModel {

    public MutableLiveData<List<Hit>> hitMutableLiveData = new MutableLiveData<>();
    PixaBayRepository repository;
    Preferences preferences;
   @Inject
    public PixaBayViewModel(PixaBayRepository repository, Preferences preferences) {
       this.repository= repository;
       this.preferences=preferences;
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


}
