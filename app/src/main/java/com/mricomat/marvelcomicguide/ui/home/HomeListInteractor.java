package com.mricomat.marvelcomicguide.ui.home;

/**
 * Created by mricomat on 18/05/2018.
 */

public interface HomeListInteractor {

    void getCharacters(Integer offset, Integer limit, String searchQuery);

}
