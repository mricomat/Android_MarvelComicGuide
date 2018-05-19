package com.mricomat.marvelcomicguide.ui.home;

import com.mricomat.marvelcomicguide.data.network.MarvelApi;
import com.mricomat.marvelcomicguide.utils.Constants;
import com.mricomat.marvelcomicguide.utils.HashGenerator;

import javax.inject.Inject;

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
    public void getCharacters(Integer offset, Integer limit, String searchQuery) {
        Long timestamp = System.currentTimeMillis() / 1000;
        String hashMd5 = HashGenerator.generate(timestamp, Constants.PRIVATE_KEY, Constants.PUBLIC_KEY);
        mMarvelApi.getCharacters(Constants.PUBLIC_KEY, hashMd5, timestamp, offset, limit, searchQuery);

    }


}
