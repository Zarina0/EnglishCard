package com.example.englishcard.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.englishcard.db.app_data_base.AppDataBase;
import com.example.englishcard.db.dao.CategoryDao;
import com.example.englishcard.db.dao.WordsDao;
import com.example.englishcard.helpers.preference_helper.Preferences;
import com.example.englishcard.helpers.room_helper.Room;
import com.example.englishcard.network.PixabyApi;
import com.example.englishcard.repository.PixaBayRepository;
import com.example.englishcard.viewmodel.PixaBayViewModel;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModul {


    @Provides
    public static PixaBayRepository provideRepository(PixabyApi api) {
        return new PixaBayRepository(api);
    }


    @Provides
        public static PixaBayViewModel provideViewModel(PixaBayRepository repository, Preferences preferences, Room roomHelper) {
        return new PixaBayViewModel(repository, preferences,roomHelper);
    }
    @Provides
    public static PixabyApi providePixabayApi(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().create(PixabyApi.class);
    }

    @Provides
    public static OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        return new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }


    @Provides
    public Interceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }


    @Provides
    public static SharedPreferences provideSharedPreferencesr(@ApplicationContext Context context){
        return context.getSharedPreferences("key",Context.MODE_PRIVATE);
    }

    public static AppDataBase appDataBase;
    public static Room roomHelper;


    @Singleton
    @Provides
    public static AppDataBase provideDatabase(@ApplicationContext Context context) {
        return roomHelper.createDatabase(context);

    }

    @Singleton
    @Provides
    public static WordsDao provideWordDao() {
        return appDataBase.wordsDao();
    }

    @Singleton
    @Provides
    public static CategoryDao provideCategoryDao() {
        return appDataBase.categoryDao();
    }
}
