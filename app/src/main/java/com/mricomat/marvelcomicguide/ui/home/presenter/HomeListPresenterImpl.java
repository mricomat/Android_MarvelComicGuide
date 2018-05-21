package com.mricomat.marvelcomicguide.ui.home.presenter;

import com.mricomat.marvelcomicguide.data.model.CharacterModel;
import com.mricomat.marvelcomicguide.data.model.DataWrapperModel;
import com.mricomat.marvelcomicguide.ui.home.HomeListInteractor;
import com.mricomat.marvelcomicguide.ui.home.HomeListInteractorImpl;
import com.mricomat.marvelcomicguide.ui.home.view.HomeListView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.single.SingleDoOnDispose;
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

    @Inject
    public HomeListPresenterImpl() {
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void takeView(HomeListView view) {
        this.mView = view;
        loadCharacters(ITEM_REQUEST_INITIAL_OFFSET, ITEM_REQUEST_LIMIT, null);
    }

    @Override
    public void dropView() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
        mView = null;
    }

    @Override
    public void loadCharacters(final Integer offset, Integer limit, String searchQuery) {
        mView.showLoading();
        mCompositeDisposable.add(mInteractor.getCharacters(offset, limit, searchQuery)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) // TODO Have SchedulerProvider donde recoger los hilos
            .subscribe(new Consumer<DataWrapperModel<List<CharacterModel>>>() {
                @Override
                public void accept(DataWrapperModel<List<CharacterModel>> listDataWrapperModel) throws Exception {
                    mView.showCharacters(listDataWrapperModel.getData().getResults());
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
