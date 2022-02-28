package com.example.englishcard.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.englishcard.models.db_models.CategoryModel;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    void insert(CategoryModel categoryModels);


    @Query("SELECT * FROM categorymodel")
    LiveData<List<CategoryModel>> getAll();
}
