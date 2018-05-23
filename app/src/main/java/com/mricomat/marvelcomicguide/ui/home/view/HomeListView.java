package com.mricomat.marvelcomicguide.ui.home.view;

import android.os.Bundle;

import com.mricomat.marvelcomicguide.data.model.CharacterModel;

import java.util.List;

/**
 * Created by mricomat on 15/05/2018.
 */

public interface HomeListView {

    void showCharacters(List<CharacterModel> characterModels);

    void showSearchedCharacters(List<CharacterModel> characterModels);

    void refreshCharacters();

    void showLoading();

    void hideLoading();

    void showEmptyContainer();

    void hideEmptyContainer();

}
