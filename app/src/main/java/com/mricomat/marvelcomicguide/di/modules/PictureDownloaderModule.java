package com.mricomat.marvelcomicguide.di.modules;

import android.content.Context;
import android.view.View;

import com.mricomat.marvelcomicguide.ui.home.adapter.HomeListListener;
import com.mricomat.marvelcomicguide.ui.home.adapter.HomeListViewHolder;
import com.mricomat.marvelcomicguide.utils.CacheImageHandler;
import com.mricomat.marvelcomicguide.utils.PictureDownloader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mricomat on 17/05/2018.
 */

@Module
public class PictureDownloaderModule {
    @Provides
    @Singleton
    CacheImageHandler providesCacheImageHandler(Context context) {
        return new CacheImageHandler(context);
    }
}
