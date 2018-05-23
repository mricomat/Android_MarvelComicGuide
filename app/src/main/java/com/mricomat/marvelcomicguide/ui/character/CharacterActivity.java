package com.mricomat.marvelcomicguide.ui.character;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mricomat.marvelcomicguide.R;
import com.mricomat.marvelcomicguide.data.model.CharacterModel;
import com.mricomat.marvelcomicguide.ui.character.adapter.CharacterViewFragment;
import com.mricomat.marvelcomicguide.utils.CacheImageHandler;
import com.mricomat.marvelcomicguide.utils.PictureDownloader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by mricomat on 15/05/2018.
 */

public class CharacterActivity extends DaggerAppCompatActivity {

    private final static int VIEW_FRAME = R.id.contentFrame;

    @BindView(R.id.image_header)
    AppCompatImageView mImageHeader;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Inject
    CharacterViewFragment mCharacterViewFragment;

    @Inject
    CharacterModel mCharacter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_activity);
        ButterKnife.bind(this);

        mCharacter = getIntent().getParcelableExtra("123");

        fillImageCharacter();
        setupToolbar();

        navigateToCharacterFragment();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(mCharacter.getName());
            actionBar.setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    private void fillImageCharacter() {
        CacheImageHandler cacheImageHandler = new CacheImageHandler(this);
        PictureDownloader mp = new PictureDownloader(cacheImageHandler);
        mp.download(mCharacter.getThumbnail().getCompleteImagePath(), mImageHeader);
    }

    private void navigateToCharacterFragment() {
        // TODO No neecesito pasarlo por agumento porque lo injecto
        Bundle args = new Bundle();
        args.putParcelable("arg_character", mCharacter);
        mCharacterViewFragment.setArguments(args);
        getSupportFragmentManager()
            .beginTransaction()
            .replace(VIEW_FRAME, mCharacterViewFragment)
            .commit();
    }
}
