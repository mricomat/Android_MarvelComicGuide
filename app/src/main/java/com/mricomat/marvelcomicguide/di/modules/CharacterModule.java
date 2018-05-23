package com.mricomat.marvelcomicguide.di.modules;

import com.mricomat.marvelcomicguide.data.model.CharacterModel;
import com.mricomat.marvelcomicguide.ui.character.CharacterActivity;
import com.mricomat.marvelcomicguide.ui.character.interactor.CharacterInteractor;
import com.mricomat.marvelcomicguide.ui.character.interactor.CharacterInteractorImpl;
import com.mricomat.marvelcomicguide.ui.character.presenter.CharacterPresenter;
import com.mricomat.marvelcomicguide.ui.character.presenter.CharacterPresenterImpl;
import com.mricomat.marvelcomicguide.ui.character.adapter.CharacterViewFragment;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by mricomat on 23/05/2018.
 */

@Module
public abstract class CharacterModule {
    @ContributesAndroidInjector(modules = PictureDownloaderModule.class)
    abstract CharacterViewFragment provideHomeListFragment();

    @Provides
    static CharacterModel provideCharacter(CharacterActivity activity) {
        return activity.getIntent().getParcelableExtra("123");
    }

    @Binds
    abstract CharacterPresenter provideCharacterPresenter(CharacterPresenterImpl presenter);

    @Binds
    abstract CharacterInteractor provideCharacterInteractor(CharacterInteractorImpl presenter);
}
