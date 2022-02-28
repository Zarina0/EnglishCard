package com.example.englishcard.models.db_models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CategoryModel {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String name;

    public CategoryModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
