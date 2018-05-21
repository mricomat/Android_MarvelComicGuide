package com.mricomat.marvelcomicguide.di.modules;

import com.mricomat.marvelcomicguide.ui.home.HomeListInteractor;
import com.mricomat.marvelcomicguide.ui.home.HomeListInteractorImpl;
import com.mricomat.marvelcomicguide.ui.home.presenter.HomeListPresenter;
import com.mricomat.marvelcomicguide.ui.home.presenter.HomeListPresenterImpl;
import com.mricomat.marvelcomicguide.ui.home.view.HomeListViewFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by mricomat on 15/05/2018.
 */

@Module
public abstract class HomeListModule {
    @ContributesAndroidInjector(modules = PictureDownloaderModule.class)
    abstract HomeListViewFragment provideHomeListFragment();

    @Binds
    abstract HomeListPresenter bindHomeListPresenter(HomeListPresenterImpl presenter);

    @Binds
    abstract HomeListInteractor bindHomeListInteractor(HomeListInteractorImpl interactor);
}
