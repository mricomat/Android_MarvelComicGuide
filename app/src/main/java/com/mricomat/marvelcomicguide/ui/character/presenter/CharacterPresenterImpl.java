package com.mricomat.marvelcomicguide.ui.character.presenter;

import com.mricomat.marvelcomicguide.data.model.CharacterModel;
import com.mricomat.marvelcomicguide.data.model.ComicModel;
import com.mricomat.marvelcomicguide.data.model.DataWrapperModel;
import com.mricomat.marvelcomicguide.ui.character.interactor.CharacterInteractor;
import com.mricomat.marvelcomicguide.ui.character.view.CharacterView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mricomat on 23/05/2018.
 */

public class CharacterPresenterImpl implements CharacterPresenter {

    @Inject
    CharacterInteractor mInteractor;

    @Inject
    CharacterModel mCharacter;

    private CompositeDisposable mCompositeDisposable;//TODO Injectarlo para que se utilice en todos los presenters;
    private CharacterView mView;

    @Inject
    public CharacterPresenterImpl() {
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void takeView(CharacterView view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void loadComics(final String comicType) {
        mView.showLoading();
        mCompositeDisposable.add(mInteractor.getComics(mCharacter.getId(), comicType)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<DataWrapperModel<List<ComicModel>>>() {
                @Override
                public void accept(DataWrapperModel<List<ComicModel>> listDataWrapperModel) throws Exception {
                    switch (comicType) {
                        case "comics":
                            mView.showComics(listDataWrapperModel.getData().getResults());
                            break;
                        case "series":
                            mView.showSeries(listDataWrapperModel.getData().getResults());
                            break;
                        case "events":
                            mView.showEvents(listDataWrapperModel.getData().getResults());
                            break;
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    // TODO HANDLER ERROR
                    //mView.hideLoading();
                }
            }));
    }

    @Override
    public void fetchAllDataComics() {
        loadComics("comics");
        loadComics("series");
        loadComics("events");
    }
}
