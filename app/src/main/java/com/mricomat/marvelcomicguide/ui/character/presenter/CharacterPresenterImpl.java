package com.mricomat.marvelcomicguide.ui.character.presenter;

import com.mricomat.marvelcomicguide.data.model.CharacterModel;
import com.mricomat.marvelcomicguide.data.model.ComicModel;
import com.mricomat.marvelcomicguide.data.model.DataWrapperModel;
import com.mricomat.marvelcomicguide.ui.character.interactor.CharacterInteractor;
import com.mricomat.marvelcomicguide.ui.character.view.CharacterView;

import java.util.Collections;
import java.util.HashMap;
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

    private static final String COMICS_TYPE = "comics";
    private static final String SERIES_TYPE = "series";
    private static final String EVENTS_TYPE = "events";

    @Inject
    CharacterInteractor mInteractor;

    @Inject
    CharacterModel mCharacter;

    private CompositeDisposable mCompositeDisposable;//TODO Injectarlo para que se utilice en todos los presenters;
    private CharacterView mView;
    private HashMap<String, Boolean> mCallsDoneMap;

    @Inject
    public CharacterPresenterImpl() {
        mCompositeDisposable = new CompositeDisposable();
        mCallsDoneMap = new HashMap<>();
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
                    if(mView != null) {
                        switch (comicType) {
                            case COMICS_TYPE:
                                mCallsDoneMap.put(COMICS_TYPE, true);
                                mView.showComics(listDataWrapperModel.getData().getResults());

                                break;
                            case SERIES_TYPE:
                                mCallsDoneMap.put(SERIES_TYPE, true);
                                mView.showSeries(listDataWrapperModel.getData().getResults());

                                break;
                            case EVENTS_TYPE:
                                mCallsDoneMap.put(EVENTS_TYPE, true);
                                mView.showEvents(listDataWrapperModel.getData().getResults());
                                break;
                        }
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    // TODO HANDLER ERROR
                    if (mView != null) {
                        mView.hideLoading();
                    }
                }
            }));
    }

    @Override
    public void fetchAllDataComics() {
        loadComics(COMICS_TYPE);
        loadComics(SERIES_TYPE);
        loadComics(EVENTS_TYPE);
    }

    @Override
    public boolean haveDoneAllCalls() {
        return Collections.frequency(mCallsDoneMap.values(), true) == mCallsDoneMap.size();
    }
}
