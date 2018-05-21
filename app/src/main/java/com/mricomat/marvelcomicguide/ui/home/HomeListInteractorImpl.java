package com.mricomat.marvelcomicguide.ui.home;

import com.mricomat.marvelcomicguide.data.model.CharacterModel;
import com.mricomat.marvelcomicguide.data.model.DataWrapperModel;
import com.mricomat.marvelcomicguide.data.network.MarvelApi;
import com.mricomat.marvelcomicguide.utils.Constants;
import com.mricomat.marvelcomicguide.utils.HashGenerator;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mricomat on 18/05/2018.
 */

public class HomeListInteractorImpl implements HomeListInteractor {

    private MarvelApi mMarvelApi;

    @Inject
    public HomeListInteractorImpl(MarvelApi marvelApi) {
        this.mMarvelApi = marvelApi;
    }

    @Override
    public Observable<DataWrapperModel<List<CharacterModel>>> getCharacters(Integer offset, Integer limit, String searchQuery) {
        Long timestamp = System.currentTimeMillis() / 1000;
        String hashMd5 = HashGenerator.generate(timestamp, Constants.PRIVATE_KEY, Constants.PUBLIC_KEY);
        return mMarvelApi.getCharacters(Constants.PUBLIC_KEY, hashMd5, timestamp, offset, limit, searchQuery);
    }
}
