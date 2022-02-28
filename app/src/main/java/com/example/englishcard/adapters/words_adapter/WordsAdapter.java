package com.example.englishcard.adapters.words_adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishcard.databinding.WordsListHolderBinding;
import com.example.englishcard.models.db_models.WordsModel;

import java.util.List;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.WordsHolder> {
    List<WordsModel> list;

    public WordsAdapter(List<WordsModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public WordsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WordsHolder(WordsListHolderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull WordsHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WordsHolder extends RecyclerView.ViewHolder {
        WordsListHolderBinding binding;
        public WordsHolder(@NonNull WordsListHolderBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void onBind(WordsModel wordsModel) {
            binding.tvWords.setText(wordsModel.getWord());
            binding.imgWords.setImageResource(wordsModel.getImage());
        }
    }
}
