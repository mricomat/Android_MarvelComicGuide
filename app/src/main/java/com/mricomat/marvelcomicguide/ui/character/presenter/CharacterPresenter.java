package com.mricomat.marvelcomicguide.ui.character.presenter;

import com.mricomat.marvelcomicguide.BasePresenter;
import com.mricomat.marvelcomicguide.ui.character.view.CharacterView;

/**
 * Created by mricomat on 23/05/2018.
 */

public interface CharacterPresenter extends BasePresenter<CharacterView> {

    void loadComics(String comicType);

    void fetchAllDataComics();
}
