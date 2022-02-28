package com.example.englishcard.adapters.on_board_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishcard.models.board_model.BoardModel;
import com.example.englishcard.databinding.FragmentBoardBinding;
import com.example.englishcard.listener.OnItemClickListener;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardHoler> {
    ArrayList<BoardModel> list;
    OnItemClickListener listener;

    public BoardAdapter(ArrayList<BoardModel> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }


    @NonNull
    @Override
    public BoardHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BoardHoler(FragmentBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BoardHoler holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BoardHoler extends RecyclerView.ViewHolder {
        FragmentBoardBinding binding;

        public BoardHoler(@NonNull FragmentBoardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(BoardModel boardModel) {
            binding.tvCategory.setText(boardModel.getTitle());
            binding.tvDiscription.setText(boardModel.getDescription());
            binding.imBoardingImage.setImageResource(boardModel.getImage());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.itemListener(boardModel);

                }
            });

        }
    }
}
