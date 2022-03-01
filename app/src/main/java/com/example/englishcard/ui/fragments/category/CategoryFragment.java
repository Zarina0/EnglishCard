package com.example.englishcard.ui.fragments.category;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.englishcard.adapters.cartegory_adapter.CategoryAdapter;
import com.example.englishcard.base.BaseFrafment;
import com.example.englishcard.databinding.FragmentCategoryBinding;
import com.example.englishcard.listener.OnCategoryClickListener;
import com.example.englishcard.viewmodel.PixaBayViewModel;


public class CategoryFragment extends BaseFrafment<FragmentCategoryBinding> implements OnCategoryClickListener {
    PixaBayViewModel viewModel;
    CategoryAdapter categoryAdapter;


    @Override
    public FragmentCategoryBinding bind() {
        return FragmentCategoryBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PixaBayViewModel.class);
        initListeners();
        initObserver();
    }


    private void initListeners() {
        binding.btnAddCategory.setOnClickListener( view -> {
            CategoryBottomSheetFragment createCategoryBottomSheetFragment = new CategoryBottomSheetFragment();
            createCategoryBottomSheetFragment.show(requireActivity().getSupportFragmentManager(), "category opened");
        } );
    }

    private void initObserver() {
        viewModel.getCategories().observe(getViewLifecycleOwner(), categoryModels -> {
            if (categoryModels != null) {
                categoryAdapter = new CategoryAdapter(this,categoryModels);
                binding.recyclerview.setAdapter(categoryAdapter);
            }
        });
    }
    @Override
    public void onCategoryClick(String category) {
       Navigation.findNavController(requireView()).navigate(CategoryFragmentDirections.actionCategoryFragmentToWordsFragment(category));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}