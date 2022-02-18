package com.example.englishcard.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.englishcard.databinding.ItemListHolderBinding;
import com.example.englishcard.network.model.Hit;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {

    ArrayList<Hit> list = new ArrayList<>();

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageHolder(ItemListHolderBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(ArrayList<Hit> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ImageHolder extends RecyclerView.ViewHolder {
        ItemListHolderBinding binding;
        public ImageHolder( @NonNull ItemListHolderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Hit hit) {
            Glide.with(binding.imPhoto).load(hit.getLargeImageURL()).into(binding.imPhoto);
        }
    }
}
