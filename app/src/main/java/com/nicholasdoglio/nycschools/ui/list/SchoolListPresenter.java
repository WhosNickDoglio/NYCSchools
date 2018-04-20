package com.nicholasdoglio.nycschools.ui.list;

import com.nicholasdoglio.nycschools.data.SchoolRepository;
import com.nicholasdoglio.nycschools.data.model.SchoolListResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Nicholas Doglio
 */
public class SchoolListPresenter implements SchoolListContract.Presenter {

    private SchoolListContract.View schoolListView;
    private Disposable disposable;
    private SchoolRepository schoolRepository;

    @Inject
    public SchoolListPresenter(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public void getSchoolList() {
        disposable = schoolRepository.getListOfSchools()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> schoolListView.startingLoadingBar())
                .subscribeWith(new DisposableSingleObserver<List<SchoolListResponse>>() {
                    @Override
                    public void onSuccess(List<SchoolListResponse> schoolListResponses) {
                        schoolListView.hideLoadingBar();
                        schoolListView.setList(schoolListResponses);
                    }

                    @Override
                    public void onError(Throwable e) {
                        schoolListView.hideLoadingBar();
                        schoolListView.showError();
                        e.printStackTrace();

                    }
                });
    }

    @Override
    public void clear() {
        disposable.dispose();
    }

    @Override
    public void takeView(SchoolListContract.View view) {
        this.schoolListView = view;
    }

    @Override
    public void dropView() {
        schoolListView = null;
    }
}
