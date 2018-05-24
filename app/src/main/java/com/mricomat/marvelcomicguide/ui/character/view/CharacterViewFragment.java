package com.mricomat.marvelcomicguide.ui.character.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mricomat.marvelcomicguide.R;
import com.mricomat.marvelcomicguide.data.model.CharacterModel;
import com.mricomat.marvelcomicguide.data.model.ComicModel;
import com.mricomat.marvelcomicguide.ui.character.adapter.CharacterAdapter;
import com.mricomat.marvelcomicguide.ui.character.presenter.CharacterPresenter;
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
    private CharacterListComicListener mListListener;

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
        if (!mCharacter.getDescription().isEmpty()) {
            mDescriptionTitle.setVisibility(View.VISIBLE);
            mDescriptionDetail.setVisibility(View.VISIBLE);
            mDescriptionDetail.setText(mCharacter.getDescription());
        }
        setListeners();
        initializeRecyclersViews();
        mPresenter.fetchAllDataComics();
    }

    private void setListeners() {
        mListListener = new CharacterListComicListener() {
            @Override
            public void onCharacterClick(ComicModel comic) {
                // TODO
            }
        };
    }

    private void initializeRecyclersViews() {
        mComicsAdapter = new CharacterAdapter(mListListener, mPictureDownloader); // TODO Inject?
        mSeriesAdapter = new CharacterAdapter(mListListener, mPictureDownloader); // TODO Inject?
        mEventsAdapter = new CharacterAdapter(mListListener, mPictureDownloader); // TODO Inject?

        if (!mCharacter.getComicsList().getComics().isEmpty()) {
            mComicTitle.setVisibility(View.VISIBLE);
            mComicsRecycleView.setVisibility(View.VISIBLE);
            mComicsRecycleView.setLayoutManager(newLinearLayoutManager());
            mComicsRecycleView.setAdapter(mComicsAdapter);
            mComicsAdapter.setComics(mCharacter.getComicsList().getComics());
        }

        if (!mCharacter.getSeriesList().getComics().isEmpty()) {
            mSeriesTitle.setVisibility(View.VISIBLE);
            mSeriesRecycleView.setVisibility(View.VISIBLE);
            mSeriesRecycleView.setLayoutManager(newLinearLayoutManager());
            mSeriesRecycleView.setAdapter(mSeriesAdapter);
            mSeriesAdapter.setComics(mCharacter.getSeriesList().getComics());
        }

        if (!mCharacter.getEventsList().getComics().isEmpty()) {
            mEventsTitle.setVisibility(View.VISIBLE);
            mEventsRecycleView.setVisibility(View.VISIBLE);
            mEventsRecycleView.setLayoutManager(newLinearLayoutManager());
            mEventsRecycleView.setAdapter(mEventsAdapter);
            mEventsAdapter.setComics(mCharacter.getEventsList().getComics());
        }
    }

    @Override
    public void showComics(List<ComicModel> comics) {
        if (!comics.isEmpty()) {
            mComicsAdapter.setComics(comics);
        }
        hideLoading();
    }

    @Override
    public void showSeries(List<ComicModel> series) {
        if (!series.isEmpty()) {
            mSeriesAdapter.setComics(series);
        }
        hideLoading();
    }

    @Override
    public void showEvents(List<ComicModel> events) {
        if (!events.isEmpty()) {
            mEventsAdapter.setComics(events);
        }
        hideLoading();
    }

    private LinearLayoutManager newLinearLayoutManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        return linearLayoutManager;
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        if (mPresenter.haveDoneAllCalls()) {
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
