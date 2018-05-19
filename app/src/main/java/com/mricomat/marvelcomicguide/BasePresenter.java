package com.mricomat.marvelcomicguide;

/**
 * Created by mricomat on 15/05/2018.
 */

public interface BasePresenter<T> {

    void takeView(T view);

    void dropView();

}
