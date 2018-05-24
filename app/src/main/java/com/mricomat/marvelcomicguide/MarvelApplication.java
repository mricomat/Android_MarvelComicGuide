package com.mricomat.marvelcomicguide;

import com.mricomat.marvelcomicguide.di.component.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by mricomat on 15/05/2018.
 */

public class MarvelApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
