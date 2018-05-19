package com.mricomat.marvelcomicguide.data.network;

import com.mricomat.marvelcomicguide.data.network.MarvelApi;
import com.mricomat.marvelcomicguide.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
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
        return new Retrofit.Builder()
            .baseUrl(Constants.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }
}
