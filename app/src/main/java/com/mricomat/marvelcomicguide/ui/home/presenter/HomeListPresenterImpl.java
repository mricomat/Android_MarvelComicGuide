package com.mricomat.marvelcomicguide.ui.home.presenter;

import com.mricomat.marvelcomicguide.data.model.CharacterModel;
import com.mricomat.marvelcomicguide.data.model.DataWrapperModel;
import com.mricomat.marvelcomicguide.ui.home.HomeListInteractor;
import com.mricomat.marvelcomicguide.ui.home.view.HomeListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mricomat on 15/05/2018.
 */

public class HomeListPresenterImpl implements HomeListPresenter {

    private static final int ITEM_REQUEST_INITIAL_OFFSET = 0;
    private static final int ITEM_REQUEST_LIMIT = 12;

    @Inject
    HomeListInteractor mInteractor;

    private HomeListView mView;
    private CompositeDisposable mCompositeDisposable;//TODO Injectarlo para que se utilice en todos los presenters;
    private List<CharacterModel> mCharacters;

    @Inject
    public HomeListPresenterImpl() {
        mCompositeDisposable = new CompositeDisposable();
        mCharacters = new ArrayList<>();
    }

    @Override
    public void takeView(HomeListView view) {
        this.mView = view;
        initialLoadCharacters();
    }

    @Override
    public void dropView() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
        mView = null;
    }

    @Override
    public void initialLoadCharacters() {
        loadCharacters(ITEM_REQUEST_INITIAL_OFFSET, ITEM_REQUEST_LIMIT, null);
    }

    @Override
    public void loadCharacters(final Integer offset, Integer limit, final String searchQuery) {
        if (mCharacters.isEmpty()) {
            mView.showLoading();
        }
        mCompositeDisposable.add(mInteractor.getCharacters(offset, limit, searchQuery)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) // TODO Have SchedulerProvider donde recoger los hilos
            .subscribe(new Consumer<DataWrapperModel<List<CharacterModel>>>() {
                @Override
                public void accept(DataWrapperModel<List<CharacterModel>> listDataWrapperModel) throws Exception {
                    mCharacters = listDataWrapperModel.getData().getResults();
                    if (searchQuery != null) {
                        mView.showSearchedCharacters(mCharacters);
                    } else {
                        mView.showCharacters(mCharacters);
                    }

                    if (mCharacters.isEmpty()) {
                        mView.showEmptyContainer();
                    } else {
                        mView.hideEmptyContainer();
                    }

                    mView.hideLoading();
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    mView.hideLoading();
                    // TODO ERROR HANDLER
                }
            }));
    }
}
