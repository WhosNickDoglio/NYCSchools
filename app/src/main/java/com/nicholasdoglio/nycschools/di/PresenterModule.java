package com.nicholasdoglio.nycschools.di;

import com.nicholasdoglio.nycschools.ui.detail.SchoolDetailContract;
import com.nicholasdoglio.nycschools.ui.detail.SchoolDetailPresenter;
import com.nicholasdoglio.nycschools.ui.list.SchoolListContract;
import com.nicholasdoglio.nycschools.ui.list.SchoolListPresenter;

import dagger.Binds;
import dagger.Module;

/**
 * @author Nicholas Doglio
 */

@Module
public abstract class PresenterModule {

    @Binds
    abstract SchoolListContract.Presenter schoolListPresenter(SchoolListPresenter schoolListPresenter);


    @Binds
    abstract SchoolDetailContract.Presenter schoolDetailPresenter(SchoolDetailPresenter schoolDetailPresenter);
}