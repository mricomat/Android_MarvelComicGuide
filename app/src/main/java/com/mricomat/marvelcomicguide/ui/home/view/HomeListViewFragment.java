package com.mricomat.marvelcomicguide.ui.home.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class HomeListViewFragment extends DaggerFragment implements HomeListView, HomeListListener,
    SearchView.OnQueryTextListener {

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
    private String mSearchQuery;
    private String mLastQuery;

    @Inject
    public HomeListViewFragment() {
        // Requires empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

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

        mPresenter.takeView(this);
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
                if(TextUtils.isEmpty(mSearchQuery)){
                    mAdapter.addCharacter(null);
                    mPresenter.loadCharacters(totalItemsCount, ITEM_REQUEST_LIMIT, mSearchQuery);
                }
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
    public void showSearchedCharacters(List<CharacterModel> characterModels) {
        if (!mLastQuery.equals(mSearchQuery)) {
            mAdapter.removeAll();
        }
        mAdapter.addCharacters(characterModels);
    }

    @Override
    public void refreshCharacters() {
        mAdapter.removeAll();
        mPresenter.initialLoadCharacters();
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

    @Override
    public void showEmptyContainer() {
        mEmptyContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyContainer() {
        mEmptyContainer.setVisibility(View.GONE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setOnQueryTextListener(this);
        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.action_search),
            new MenuItemCompat.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    mSwipeRefreshLayout.setEnabled(false);
                    return true;
                }

                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    mAdapter.setType(HomeListAdapter.ITEM_VIEW_TYPE_BASIC);
                    if (mSearchQuery != null) {
                        showLoading();
                        mAdapter.removeAll();
                        mPresenter.initialLoadCharacters();
                    }
                    mSearchQuery = "";
                    mSwipeRefreshLayout.setEnabled(true);
                    return true;
                }
            });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String searchQuery) {
        mSearchQuery = searchQuery;
        if (!TextUtils.isEmpty(searchQuery)) {
            mPresenter.loadCharacters(ITEM_REQUEST_INITIAL_OFFSET, ITEM_REQUEST_LIMIT, searchQuery);
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String searchQuery) {
        mLastQuery = mSearchQuery;
        mSearchQuery = searchQuery;
        if (!TextUtils.isEmpty(searchQuery)) {
            if (!mLastQuery.equals(mSearchQuery)) {
                showLoading();
            }
            mAdapter.setType(HomeListAdapter.ITEM_VIEW_TYPE_SEARCH);
            mPresenter.loadCharacters(ITEM_REQUEST_INITIAL_OFFSET, null, searchQuery); // TODO Arreglar el endlessScroll
            return true;
        }
        return false;
    }
}
