package com.mricomat.marvelcomicguide.ui.character.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mricomat.marvelcomicguide.R;
import com.mricomat.marvelcomicguide.data.model.CharacterModel;
import com.mricomat.marvelcomicguide.data.model.ComicModel;
import com.mricomat.marvelcomicguide.ui.character.presenter.CharacterPresenter;
import com.mricomat.marvelcomicguide.ui.character.view.CharacterListListener;
import com.mricomat.marvelcomicguide.ui.character.view.CharacterView;
import com.mricomat.marvelcomicguide.utils.PictureDownloader;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

/**
 * Created by mricomat on 23/05/2018.
 */

public class CharacterViewFragment extends DaggerFragment implements CharacterView {

    @BindView(R.id.title_description)
    TextView mDescriptionTitle;
    @BindView(R.id.detail_description)
    TextView mDescriptionDetail;

    @BindView(R.id.series_title)
    TextView mSeriesTitle;
    @BindView(R.id.series_recycler_view)
    RecyclerView mSeriesRecycleView;

    @BindView(R.id.comics_title)
    TextView mComicTitle;
    @BindView(R.id.comics_recycler_view)
    RecyclerView mComicsRecycleView;

    @BindView(R.id.events_title)
    TextView mEventsTitle;
    @BindView(R.id.events_recycler_view)
    RecyclerView mEventsRecycleView;

    @BindView(R.id.progress_bar_container)
    RelativeLayout mProgressBar;

    @Inject
    PictureDownloader mPictureDownloader;

    @Inject
    CharacterModel mCharacter;

    @Inject
    CharacterPresenter mPresenter;

    private CharacterAdapter mComicsAdapter;
    private CharacterAdapter mSeriesAdapter;
    private CharacterAdapter mEventsAdapter;
    private CharacterListListener mListListener;
    private int mCallsCount = 0;

    @Inject
    public CharacterViewFragment() {
        // Requires empty public constructor
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.character_fragment, container, false);
        ButterKnife.bind(this, mRootView);

        mPresenter.takeView(this);
        fillViews();

        return mRootView;
    }

    private void fillViews() {
        setListeners();

        mComicsAdapter = new CharacterAdapter(mListListener, mPictureDownloader); // TODO Inject?
        mSeriesAdapter = new CharacterAdapter(mListListener, mPictureDownloader); // TODO Inject?
        mEventsAdapter = new CharacterAdapter(mListListener, mPictureDownloader); // TODO Inject?

        mPresenter.fetchAllDataComics();

        if (!mCharacter.getDescription().isEmpty()) {
            mDescriptionTitle.setVisibility(View.VISIBLE);
            mDescriptionDetail.setVisibility(View.VISIBLE);
            mDescriptionDetail.setText(mCharacter.getDescription());
        }
    }

    private void setListeners() {
        mListListener = new CharacterListListener() {
            @Override
            public void onCharacterClick(ComicModel comic) {
                // TODO
                String s;
            }
        };
    }

    @Override
    public void showComics(List<ComicModel> comics) {
        if (!comics.isEmpty()) {
            mComicTitle.setVisibility(View.VISIBLE);
            mComicsRecycleView.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mComicsRecycleView.setLayoutManager(linearLayoutManager);
            mComicsRecycleView.setAdapter(mComicsAdapter);
            mComicsAdapter.setComics(comics);
        }
        hideLoading();
    }

    @Override
    public void showSeries(List<ComicModel> series) {
        if (!series.isEmpty()) {
            mSeriesTitle.setVisibility(View.VISIBLE);
            mSeriesRecycleView.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mSeriesRecycleView.setLayoutManager(linearLayoutManager);
            mSeriesRecycleView.setAdapter(mSeriesAdapter);
            mSeriesAdapter.setComics(series);
        }
        hideLoading();
    }

    @Override
    public void showEvents(List<ComicModel> events) {
        if (!events.isEmpty()) {
            mEventsTitle.setVisibility(View.VISIBLE);
            mEventsRecycleView.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mEventsRecycleView.setLayoutManager(linearLayoutManager);
            mEventsRecycleView.setAdapter(mEventsAdapter);
            mEventsAdapter.setComics(events);
        }
        hideLoading();
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mCallsCount++; // TODO Diccionario booleans llamdas
        if (mCallsCount == 3) {
            mProgressBar.setVisibility(View.GONE);
            mCallsCount = 0;
        }
    }
}