package com.mricomat.marvelcomicguide.di.modules;

import com.mricomat.marvelcomicguide.data.network.MarvelApi;
import com.mricomat.marvelcomicguide.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mricomat on 18/05/2018.
 */

@Module
public class MarvelApiModule {

    @Provides
    @Singleton
    public MarvelApi provideMarvelApiService(Retrofit retrofit) {
        return retrofit.create(MarvelApi.class);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build();
        return new Retrofit.Builder()
            .baseUrl(Constants.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build();
    }
}
