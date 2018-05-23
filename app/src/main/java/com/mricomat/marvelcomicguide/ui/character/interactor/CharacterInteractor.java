package com.mricomat.marvelcomicguide.ui.character.interactor;

import com.mricomat.marvelcomicguide.data.model.ComicModel;
import com.mricomat.marvelcomicguide.data.model.DataWrapperModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by mricomat on 23/05/2018.
 */

public interface CharacterInteractor {

    Observable<DataWrapperModel<List<ComicModel>>> getComics(long id, String comicType);

}
