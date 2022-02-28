package com.example.englishcard.adapters.image_adater;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.englishcard.databinding.ImageListHolderBinding;
import com.example.englishcard.listener.OnImageClickListener;
import com.example.englishcard.models.api_models.Hit;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {
    ArrayList<Hit> list = new ArrayList<>();
    OnImageClickListener onImageClickListener;

    public ImageAdapter(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }


    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageHolder(ImageListHolderBinding.inflate(LayoutInflater.from(parent.getContext())));
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
        ImageListHolderBinding binding;
        public ImageHolder( @NonNull ImageListHolderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Hit hit) {
            Glide.with(binding.imImage).load(hit.getLargeImageURL()).into(binding.imImage);
        }
    }
}
