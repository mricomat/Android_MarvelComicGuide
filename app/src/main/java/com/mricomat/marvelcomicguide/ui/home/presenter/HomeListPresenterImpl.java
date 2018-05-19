package com.mricomat.marvelcomicguide.ui.home.presenter;

import com.mricomat.marvelcomicguide.ui.home.view.HomeListView;

import javax.inject.Inject;

/**
 * Created by mricomat on 15/05/2018.
 */

public class HomeListPresenterImpl implements HomeListPresenter {

    private HomeListView mView;

    @Inject
    public HomeListPresenterImpl() {

    }

    @Override
    public void takeView(HomeListView view) {
        this.mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
