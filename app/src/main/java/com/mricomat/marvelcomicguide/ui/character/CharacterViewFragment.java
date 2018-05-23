package com.mricomat.marvelcomicguide.ui.character;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mricomat.marvelcomicguide.R;
import com.mricomat.marvelcomicguide.data.model.CharacterModel;
import com.mricomat.marvelcomicguide.utils.PictureDownloader;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

/**
 * Created by mricomat on 23/05/2018.
 */

public class CharacterViewFragment extends DaggerFragment {

    @Inject
    PictureDownloader mPictureDownloader;

    @Inject
    CharacterModel mCharacter;

    @Inject
    public CharacterViewFragment() {
        // Requires empty public constructor
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.home_list_fragment, container, false);
        ButterKnife.bind(this, mRootView);
        fillViews();
        return mRootView;
    }

    private void fillViews() {

    }
}
