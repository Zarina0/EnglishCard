package com.example.englishcard.adapters.cartegory_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishcard.databinding.CategoryListHolderBinding;
import com.example.englishcard.listener.OnCategoryClickListener;
import com.example.englishcard.models.db_models.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {
    List<CategoryModel> list;
    OnCategoryClickListener onCategoryClickListener;

    public CategoryAdapter(List<CategoryModel> list, OnCategoryClickListener onCategoryClickListener) {
        this.list = list;
        this.onCategoryClickListener = onCategoryClickListener;
    }


    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryHolder(CategoryListHolderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
            holder.OnBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {
        CategoryListHolderBinding binding;
        public CategoryHolder(@NonNull CategoryListHolderBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        public void OnBind(CategoryModel categoryModel) {
            binding.tvCategory.setText(categoryModel.getName());
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCategoryClickListener.onCategoryClick(categoryModel.getName());

                }
            });
        }
    }
}
