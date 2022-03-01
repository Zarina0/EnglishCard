package com.example.englishcard.ui.fragments.words;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishcard.adapters.image_adater.ImageAdapter;
import com.example.englishcard.databinding.FragmentWordsBottomSheetBinding;
import com.example.englishcard.listener.OnImageClickListener;
import com.example.englishcard.models.api_models.Hit;
import com.example.englishcard.models.db_models.WordsModel;
import com.example.englishcard.viewmodel.PixaBayViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;


public class WordsBottomSheetFragment extends BottomSheetDialogFragment implements OnImageClickListener {
    FragmentWordsBottomSheetBinding binding;
    WordsFragmentArgs args;
    PixaBayViewModel viewModel;
    Handler handler;
    ImageAdapter imageAdapter;
    String word;
    String category;
    int image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWordsBottomSheetBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PixaBayViewModel.class);
        initListeners();
        initAdapter();
        getArgs();

    }

    private void getArgs() {
        if (getArguments() != null) {
            args = WordsFragmentArgs.fromBundle(getArguments());
            category = args.getFromCategoryToWords();
        }
    }

    private void initAdapter() {
        imageAdapter = new ImageAdapter(this);
    }

    private void initListeners() {
        binding.btnCreate.setOnClickListener( view -> binding.edWords.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (handler != null) {
                    handler = null;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                new Handler().postDelayed( () -> {
                    word = binding.edWords.getText().toString();
                    viewModel.getImages(word).observe(getViewLifecycleOwner(), images -> {
                        if (images != null) {
                            binding.tvNewCategory.setText(word);
                            imageAdapter.setData((ArrayList<Hit>) images);
                            binding.recyclerview.setAdapter(imageAdapter);
                            WordsModel wordModel = new WordsModel(word, category, image);
                            viewModel.insertWord(wordModel);
                        }
                    });
                }, 2000);
            }
        }) );
    }

    @Override
    public void onImageClick(WordsModel wordsModel) {
        image = wordsModel.getImage();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}