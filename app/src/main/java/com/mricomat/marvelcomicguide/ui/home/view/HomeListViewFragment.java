package com.mricomat.marvelcomicguide.ui.home.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.mricomat.marvelcomicguide.R;
import com.mricomat.marvelcomicguide.data.model.CharacterModel;
import com.mricomat.marvelcomicguide.ui.home.adapter.EndlessRecyclerViewOnScrollListener;
import com.mricomat.marvelcomicguide.ui.home.adapter.HomeListAdapter;
import com.mricomat.marvelcomicguide.ui.home.adapter.HomeListListener;
import com.mricomat.marvelcomicguide.ui.home.presenter.HomeListPresenter;
import com.mricomat.marvelcomicguide.utils.PictureDownloader;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

/**
 * Created by mricomat on 15/05/2018.
 */

public class HomeListViewFragment extends DaggerFragment implements HomeListView, HomeListListener {

    private static final int ITEM_REQUEST_INITIAL_OFFSET = 0;
    private static final int ITEM_REQUEST_LIMIT = 12;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.recycler_view)
    RecyclerView mHomeRecyclerView;

    @BindView(R.id.empty_container)
    RelativeLayout mEmptyContainer;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @Inject
    HomeListPresenter mPresenter;

    @Inject
    PictureDownloader mPictureDownloader;

    private HomeListAdapter mAdapter;

    @Inject
    public HomeListViewFragment() {
        // Requires empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.takeView(this); // TODO o en onAttach?
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dropView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.home_list_fragment, container, false);
        ButterKnife.bind(this, mRootView);
        fillViews();
        return mRootView;
    }

    private void fillViews() {
        initSwipeRefreshLayout();
        initRecyclerView();
        setListeners();
    }

    private void setListeners() {

    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red_tomato);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshCharacters();
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new HomeListAdapter(this, mPictureDownloader);
        mHomeRecyclerView.setAdapter(mAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(mAdapter.getNewSpanSizeLookUp());
        mHomeRecyclerView.setLayoutManager(gridLayoutManager);
        mHomeRecyclerView.addOnScrollListener(new EndlessRecyclerViewOnScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                mAdapter.addCharacter(null);
                mPresenter.loadCharacters(totalItemsCount, null, null);
            }
        });
    }

    @Override
    public void onCharacterClick(CharacterModel character) {
        // TODO
    }

    @Override
    public void showCharacters(List<CharacterModel> characterModels) {
        mAdapter.addCharacters(characterModels);
    }

    @Override
    public void refreshCharacters() {
        mAdapter.removeAll();
        mPresenter.loadCharacters(ITEM_REQUEST_INITIAL_OFFSET, ITEM_REQUEST_LIMIT, null);
    }

    @Override
    public void showLoading() {
        if (!mSwipeRefreshLayout.isRefreshing()) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
