package com.example.englishcard.helpers.room_helper;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.englishcard.db.app_data_base.AppDataBase;
import com.example.englishcard.db.dao.CategoryDao;
import com.example.englishcard.db.dao.WordsDao;
import com.example.englishcard.models.db_models.CategoryModel;
import com.example.englishcard.models.db_models.WordsModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;

@Singleton
public class Room {
    AppDataBase appDataBase;
    public WordsDao wordsDao;
    public CategoryDao categoryDao;

    @Inject
    public Room(WordsDao wordsDao, CategoryDao categoryDao) {
        this.wordsDao = wordsDao;
        this.categoryDao = categoryDao;
    }

    public AppDataBase createDatabase(@ApplicationContext Context context) {
        appDataBase = androidx.room.Room.databaseBuilder(context, AppDataBase.class, "database").allowMainThreadQueries().build();
        return appDataBase.getDatabase();


    }


    public CategoryModel insertCategory(CategoryModel categoryModel) {
        categoryDao.insert(categoryModel);
        return categoryModel;
    }

    public LiveData<List<CategoryModel>> getAllCategories(LiveData<List<CategoryModel>> categoryList) {
        return categoryList = categoryDao.getAll();

    }

    public WordsModel insertWord(WordsModel wordModel) {
        wordsDao.insert(wordModel);
        return wordModel;
    }
    public LiveData<List<WordsModel>> getAllWords(String userCategory) {
        return wordsDao.getAll(userCategory);
    }
}
