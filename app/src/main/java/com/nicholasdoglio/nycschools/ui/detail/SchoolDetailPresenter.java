package com.nicholasdoglio.nycschools.ui.detail;


import com.nicholasdoglio.nycschools.data.model.SchoolDetailResponse;
import com.nicholasdoglio.nycschools.data.remote.SchoolService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Nicholas Doglio
 */
public class SchoolDetailPresenter implements SchoolDetailContract.Presenter {

    private SchoolDetailContract.View schoolDetailView;
    private SchoolService schoolService;
    private Disposable disposable;

    @Inject
    public SchoolDetailPresenter(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @Override
    public void getSchoolDetails(String dbn, String schoolName, String unknown) {
        disposable = schoolService.getSchoolDetails(dbn)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> schoolDetailView.startingLoadingBar())
                .subscribeWith(new DisposableSingleObserver<List<SchoolDetailResponse>>() {

                    @Override
                    public void onSuccess(List<SchoolDetailResponse> schoolDetailResponses) {
                        schoolDetailView.hideLoadingBar();

                        if (schoolDetailResponses.isEmpty()) {
                            SchoolDetailResponse schoolDetailResponse = new SchoolDetailResponse();
                            schoolDetailResponse.setSchoolName(schoolName);
                            schoolDetailResponse.setSatWritingAvgScore(unknown);
                            schoolDetailResponse.setSatCriticalReadingAvgScore(unknown);
                            schoolDetailResponse.setSatMathAvgScore(unknown);
                            schoolDetailResponse.setNumOfSatTestTakers(unknown);

                            schoolDetailView.displayData(schoolDetailResponse);
                            schoolDetailView.noDataFoundTextView();

                        } else {
                            schoolDetailView.displayData(schoolDetailResponses.get(0));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        schoolDetailView.hideLoadingBar();
                        schoolDetailView.showError();
                    }
                });
    }

    @Override
    public void takeView(SchoolDetailContract.View view) {
        schoolDetailView = view;
    }

    @Override
    public void dropView() {
        schoolDetailView = null;
    }

    @Override
    public void clear() {
        disposable.dispose();
    }
}