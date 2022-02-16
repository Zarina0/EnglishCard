package com.example.englishcard.ui.fragments;

import com.example.englishcard.base.BaseFrafment;
import com.example.englishcard.databinding.FragmentCategoryBinding;


public class CategoryFragment extends BaseFrafment<FragmentCategoryBinding> {


    @Override
    public FragmentCategoryBinding bind() {
        return FragmentCategoryBinding.inflate(getLayoutInflater());
    }
}