package com.mricomat.marvelcomicguide.di.modules;

import android.support.annotation.Nullable;

import com.mricomat.marvelcomicguide.data.model.CharacterModel;
import com.mricomat.marvelcomicguide.ui.character.CharacterActivity;
import com.mricomat.marvelcomicguide.ui.character.CharacterViewFragment;

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
}
