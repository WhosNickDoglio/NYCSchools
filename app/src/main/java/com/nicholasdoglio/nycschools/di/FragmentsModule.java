package com.nicholasdoglio.nycschools.di;

import com.nicholasdoglio.nycschools.ui.detail.SchoolDetailFragment;
import com.nicholasdoglio.nycschools.ui.list.SchoolListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Nicholas Doglio
 */
@Module
public abstract class FragmentsModule {
    @ContributesAndroidInjector
    abstract SchoolListFragment contributeSchoolListFragment();

    @ContributesAndroidInjector
    abstract SchoolDetailFragment contributeSchoolDetailFragment();
}