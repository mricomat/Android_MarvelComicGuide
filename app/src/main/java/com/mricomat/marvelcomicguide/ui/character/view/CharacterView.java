package com.mricomat.marvelcomicguide.ui.character.view;

import com.mricomat.marvelcomicguide.data.model.ComicModel;
import com.mricomat.marvelcomicguide.data.model.DataWrapperModel;

import java.util.List;

/**
 * Created by mricomat on 23/05/2018.
 */

public interface CharacterView {

    void showComics(List<ComicModel> comics);

    void showSeries(List<ComicModel> series);

    void showEvents(List<ComicModel> events);

    void showLoading();

    void hideLoading();

}
