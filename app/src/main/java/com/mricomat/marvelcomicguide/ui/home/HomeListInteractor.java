package com.mricomat.marvelcomicguide.ui.home;

import com.mricomat.marvelcomicguide.data.model.CharacterModel;
import com.mricomat.marvelcomicguide.data.model.DataWrapperModel;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by mricomat on 18/05/2018.
 */

public interface HomeListInteractor {

    Observable<DataWrapperModel<List<CharacterModel>>> getCharacters(Integer offset, Integer limit, String searchQuery);

}
