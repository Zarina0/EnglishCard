package com.example.englishcard.boarding.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.example.englishcard.R;
import com.example.englishcard.adapters.on_board_adapter.BoardAdapter;
import com.example.englishcard.base.BaseFrafment;
import com.example.englishcard.boarding.data.client.DataClient;
import com.example.englishcard.databinding.FragmentMainBoardBinding;
import com.example.englishcard.listener.OnItemClickListener;
import com.example.englishcard.models.board_model.BoardModel;
import com.example.englishcard.viewmodel.PixaBayViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainBoardFragment extends BaseFrafment<FragmentMainBoardBinding> implements OnItemClickListener {
    BoardAdapter boardAdapter;
    PixaBayViewModel viewModel;
    ArrayList<BoardModel> list;

    @Override
    public FragmentMainBoardBinding bind() {
        return FragmentMainBoardBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PixaBayViewModel.class);
        initAdapter();
        checkIfShown();
        initListeners();
        enableButton();
    }

    private void initAdapter() {
        list = DataClient.getData();
        boardAdapter = new BoardAdapter(list, this);
        binding.viewpager.setAdapter(boardAdapter);
        binding.wormDotsIndicator.setViewPager2(binding.viewpager);
    }


    private void checkIfShown() {
        if (viewModel.getBoolean()) {
            Navigation.findNavController(requireView()).navigate(R.id.categoryFragment);
        } else {
            viewModel.setBoolean(false);
        }
    }


    private void initListeners() {
        binding.btnGo.setOnClickListener( view -> {
            viewModel.setBoolean(true);
            Navigation.findNavController(requireView()).navigate(R.id.wordsFragment);
        } );
    }

    private void enableButton() {
        binding.viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (position == 4) {
                    binding.btnGo.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @Override
    public void itemListener(BoardModel boardModel) {
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}