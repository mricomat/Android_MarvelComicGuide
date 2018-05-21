package com.mricomat.marvelcomicguide.ui.home.view;

import com.mricomat.marvelcomicguide.data.model.CharacterModel;

import java.util.List;

/**
 * Created by mricomat on 15/05/2018.
 */

public interface HomeListView {

    void showCharacters(List<CharacterModel> characterModels);

    void refreshCharacters();

    void showLoading();

    void hideLoading();

}
