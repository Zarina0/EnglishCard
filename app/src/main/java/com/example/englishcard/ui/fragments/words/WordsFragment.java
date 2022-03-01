package com.example.englishcard.ui.fragments.words;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.englishcard.adapters.words_adapter.WordsAdapter;
import com.example.englishcard.base.BaseFrafment;
import com.example.englishcard.databinding.FragmentWordsBinding;
import com.example.englishcard.viewmodel.PixaBayViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WordsFragment extends BaseFrafment<FragmentWordsBinding> {

    PixaBayViewModel viewModel;
    WordsAdapter wordsAdapter;
    WordsFragmentArgs wordsFragmentArgs;

    @Override
    public FragmentWordsBinding bind() {
        return FragmentWordsBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PixaBayViewModel.class);
        getArgs();
        initListeners();
        initObserver();
    }

    private void initObserver() {
        String category = wordsFragmentArgs.getFromCategoryToWords();
        viewModel.getWords(category).observe(getViewLifecycleOwner(), wordModels -> {
            if (wordModels != null) {
                wordsAdapter = new WordsAdapter(wordModels);
                binding.recyclerview.setAdapter(wordsAdapter);
            }
        });
    }

    private void getArgs() {
        if (getArguments() != null) {
            WordsFragmentArgs.fromBundle(getArguments());
            String category = wordsFragmentArgs.getFromCategoryToWords();
            binding.toolbar.setTitle(category);
        }
    }


    private void initListeners() {
        binding.btnAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WordsBottomSheetFragment wordsBottomSheetFragment = new WordsBottomSheetFragment();
                wordsBottomSheetFragment.show(requireActivity().getSupportFragmentManager(), "word opened");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}