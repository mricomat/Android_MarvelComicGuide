package com.mricomat.marvelcomicguide.ui.home.presenter;

import com.mricomat.marvelcomicguide.BasePresenter;
import com.mricomat.marvelcomicguide.data.model.CharacterModel;
import com.mricomat.marvelcomicguide.ui.home.view.HomeListView;

/**
 * Created by mricomat on 15/05/2018.
 */

public interface HomeListPresenter extends BasePresenter<HomeListView> {

    void loadCharacters(Integer offset, Integer limit, String searchQuery);

    void initialLoadCharacters();

}

