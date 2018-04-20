package com.nicholasdoglio.nycschools.ui.base;

/**
 * @author Nicholas Doglio
 */
public interface BasePresenter<T> {

    /**
     * Binds the view to the presenter
     *
     * @param view: takes in whatever view the presenter is being connected to, Activity or Fragment
     */
    void takeView(T view);

    /**
     * Drops any reference to the view to avoid memory leaks
     */
    void dropView();

    /**
     * clears out any disposables
     */
    void clear();
}