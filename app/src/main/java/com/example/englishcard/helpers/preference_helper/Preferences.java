package com.example.englishcard.helpers.preference_helper;

import android.content.SharedPreferences;

import javax.inject.Inject;


public class Preferences {
    private final SharedPreferences sharedPreferences;

    @Inject
    public Preferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public boolean getBoolean(){
        boolean preferencesBoolean = sharedPreferences.getBoolean("shared_preference",false);
        return preferencesBoolean;
    }

    public void setBoolean(boolean preferenceBoolean){
        sharedPreferences.edit().putBoolean("shared_preference",preferenceBoolean).apply();
    }

}
