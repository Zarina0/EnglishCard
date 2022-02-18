package com.example.englishcard.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishcard.adapter.ImageAdapter;
import com.example.englishcard.base.BaseFrafment;
import com.example.englishcard.databinding.FragmentWordsBinding;
import com.example.englishcard.network.model.Hit;
import com.example.englishcard.viewmodel.PixaBayViewModel;

import java.util.ArrayList;


public class WordsFragment extends BaseFrafment<FragmentWordsBinding> {
    private ImageAdapter imageAdapter;
    PixaBayViewModel viewModel;
    private Handler handler;


    @Override
    public FragmentWordsBinding bind() {
        return FragmentWordsBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PixaBayViewModel.class);
        initAdapter();
        listener();
     }


    private void listener() {
        binding.edWords.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (handler != null){
                    handler= null;
                }

            }

            @Override
            public void afterTextChanged(  Editable editable) {
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.progressBar.setVisibility(View.VISIBLE);
                        viewModel.getImages(binding.edWords.getText().toString()).observe(getViewLifecycleOwner(), hits -> {
                            if (hits != null) {
                                binding.progressBar.setVisibility(View.GONE);
                                binding.recyclerView.setAdapter(imageAdapter);
                                imageAdapter.setData((ArrayList<Hit>) hits);
                            }
                        });
                    }
                }, 2000);
            }
        });
    }


    private void initAdapter() {
        imageAdapter= new ImageAdapter();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding= null;
     }
}