package com.nicholasdoglio.nycschools.di;

import com.nicholasdoglio.nycschools.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Nicholas Doglio
 */
@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = FragmentsModule.class)
    abstract MainActivity contributeMainActivity();
}