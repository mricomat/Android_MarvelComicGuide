package com.mricomat.marvelcomicguide.di.modules;

import com.mricomat.marvelcomicguide.ui.home.HomeListActivity;
import com.mricomat.marvelcomicguide.ui.home.adapter.HomeListViewHolder;
import com.mricomat.marvelcomicguide.utils.PictureDownloader;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by mricomat on 15/05/2018.
 */

@Module
public abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = HomeListModule.class)
    abstract HomeListActivity providesHomeListActivity();
}
