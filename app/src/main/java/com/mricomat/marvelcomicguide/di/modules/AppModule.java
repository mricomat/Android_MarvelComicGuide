package com.mricomat.marvelcomicguide.di.modules;

import android.app.Application;
import android.content.Context;

import com.mricomat.marvelcomicguide.utils.Constants;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by mricomat on 15/05/2018.
 */

@Module
public abstract class AppModule {
    @Binds
    abstract Context bindContext(Application application);

}
