package com.example.englishcard.boarding.data.client;

import com.example.englishcard.R;
import com.example.englishcard.models.board_model.BoardModel;

import java.util.ArrayList;

public class DataClient {
    public static ArrayList<BoardModel> list = new ArrayList<>();

    public static ArrayList<BoardModel> getData() {
        list.add(new BoardModel("Категория", "Создавайте собственные категории для слов", R.drawable.category));
        list.add(new BoardModel("Слова", "Создавайте слова на английском и " +
                "кликайте на карточку чтобы увидеть его " +
                "перевод и картинку для ассоциации", R.drawable.word));
        list.add(new BoardModel("Изучение ", "Свайпайте карточку вправо если вы " +
                "запомнили,влево если пока ещё не уверены", R.drawable.learning));
        list.add(new BoardModel("Личный кабинет", "Следите за своими ачивками и количеством очков опыта", R.drawable.personalcabinet));
        list.add(new BoardModel("Давайте начнём !", "", R.drawable.start));
        return list;
    }
}
