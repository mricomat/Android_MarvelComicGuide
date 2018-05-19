package com.mricomat.marvelcomicguide.ui.home.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mricomat.marvelcomicguide.R;
import com.mricomat.marvelcomicguide.data.model.CharacterModel;
import com.mricomat.marvelcomicguide.ui.home.adapter.HomeListAdapter;
import com.mricomat.marvelcomicguide.ui.home.adapter.HomeListListener;
import com.mricomat.marvelcomicguide.ui.home.presenter.HomeListPresenter;
import com.mricomat.marvelcomicguide.utils.PictureDownloader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

/**
 * Created by mricomat on 15/05/2018.
 */

public class HomeListViewFragment extends DaggerFragment implements HomeListView, HomeListListener {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.recycler_view)
    RecyclerView mHomeRecyclerView;

    @BindView(R.id.empty_container)
    RelativeLayout mEmptyContainer;

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
        mPresenter.takeView(this);
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

            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new HomeListAdapter(this, mPictureDownloader);
        mHomeRecyclerView.setAdapter(mAdapter);
        mHomeRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    public void onCharacterClick(CharacterModel character) {

    }
}
