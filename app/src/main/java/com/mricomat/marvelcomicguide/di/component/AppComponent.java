package com.mricomat.marvelcomicguide.di.component;

import android.app.Application;

import com.mricomat.marvelcomicguide.MarvelApplication;
import com.mricomat.marvelcomicguide.di.modules.ActivityBindingModule;
import com.mricomat.marvelcomicguide.di.modules.AppModule;
import com.mricomat.marvelcomicguide.di.modules.MarvelApiModule;
import com.mricomat.marvelcomicguide.di.modules.PictureDownloaderModule;
import com.mricomat.marvelcomicguide.utils.PictureDownloader;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by mricomat on 15/05/2018.
 */

@Singleton
@Component(modules = {ActivityBindingModule.class,
    AppModule.class,
    MarvelApiModule.class,
    PictureDownloaderModule.class,
    AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<MarvelApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(Application application);
        AppComponent build();
    }
}
