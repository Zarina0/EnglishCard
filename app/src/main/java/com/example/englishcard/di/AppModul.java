package com.example.englishcard.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.englishcard.boarding.data.Preferences;
import com.example.englishcard.network.PixabyApi;
import com.example.englishcard.repository.PixaBayRepository;
import com.example.englishcard.viewmodel.PixaBayViewModel;

import java.util.concurrent.TimeUnit;

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
        public static PixaBayViewModel provideViewModel(PixaBayRepository repository, Preferences preferences) {
        return new PixaBayViewModel(repository, preferences);
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

    @ApplicationContext
    Context context;

    @Provides
    public static SharedPreferences provideSharedPreferencesr(@ApplicationContext Context context){
        return context.getSharedPreferences("key",Context.MODE_PRIVATE);
    }

}
