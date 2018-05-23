package com.mricomat.marvelcomicguide.ui.character.interactor;

import com.mricomat.marvelcomicguide.data.model.ComicModel;
import com.mricomat.marvelcomicguide.data.model.DataWrapperModel;
import com.mricomat.marvelcomicguide.data.network.MarvelApi;
import com.mricomat.marvelcomicguide.utils.Constants;
import com.mricomat.marvelcomicguide.utils.HashGenerator;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by mricomat on 23/05/2018.
 */

public class CharacterInteractorImpl implements CharacterInteractor {

    private MarvelApi mMarvelApi;

    @Inject
    public CharacterInteractorImpl(MarvelApi marvelApi) {
        this.mMarvelApi = marvelApi;
    }

    @Override
    public Observable<DataWrapperModel<List<ComicModel>>> getComics(long id, String comicType) {
        Long timestamp = System.currentTimeMillis() / 1000;
        String hashMd5 = HashGenerator.generate(timestamp, Constants.PRIVATE_KEY, Constants.PUBLIC_KEY);
        return mMarvelApi.getCharacterComics(id, comicType, null, null, Constants.PUBLIC_KEY, hashMd5, timestamp);
    }
}
