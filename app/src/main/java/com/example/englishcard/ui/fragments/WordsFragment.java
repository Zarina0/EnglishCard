package com.example.englishcard.ui.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.englishcard.App;
import com.example.englishcard.adapter.ImageAdapter;
import com.example.englishcard.base.BaseFrafment;
import com.example.englishcard.databinding.FragmentWordsBinding;
import com.example.englishcard.network.model.PixabayResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class WordsFragment extends BaseFrafment<FragmentWordsBinding> {
    private static final String TAG = "WordsFragment";
    private ImageAdapter imageAdapter;



    @Override
    public FragmentWordsBinding bind() {
        return FragmentWordsBinding.inflate(getLayoutInflater());
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageAdapter= new ImageAdapter();
        listener();
    }

    private void listener() {
        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = binding.tvWords.getText().toString();
                App.retrofitClient.providePixabayApi().getImage("25686965-0239caa31958b6bb6157f5e9b", data).enqueue(new Callback<PixabayResponse>() {
                    @Override
                    public void onResponse(Call<PixabayResponse> call, Response<PixabayResponse> response) {
                        imageAdapter.setData(response.body().getHits());
                        binding.recyclerView.setAdapter(imageAdapter);
                    }

                    @Override
                    public void onFailure(Call<PixabayResponse> call, Throwable t) {
                        Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}