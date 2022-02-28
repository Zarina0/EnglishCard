package com.example.englishcard.db.app_data_base;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.englishcard.db.dao.CategoryDao;
import com.example.englishcard.db.dao.WordsDao;
import com.example.englishcard.models.db_models.CategoryModel;
import com.example.englishcard.models.db_models.WordsModel;

@Database(entities = {CategoryModel.class, WordsModel.class}, version = 1, exportSchema = false)

public abstract class AppDataBase extends RoomDatabase {
    AppDataBase appdatabase;

    public AppDataBase getDatabase() {
        return appdatabase;
    }

    public abstract CategoryDao categoryDao();

    public abstract WordsDao wordsDao();
}
