package com.example.englishcard.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.englishcard.models.db_models.WordsModel;

import java.util.List;

@Dao
public interface WordsDao  {
    @Insert
    void insert(WordsModel wordsModel);


    @Query("SELECT * FROM WordsModel WHERE category =:userCategory")
    LiveData<List<WordsModel>> getAll(String userCategory);}
