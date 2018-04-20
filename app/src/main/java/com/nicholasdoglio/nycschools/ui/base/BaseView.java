package com.nicholasdoglio.nycschools.ui.base;

/**
 * @author Nicholas Doglio
 */
public interface BaseView<T> {
    /**
     * Shows network error on loading failure
     */
    void showError();

    /**
     * initialized loading bar
     */
    void startingLoadingBar();

    /**
     * hides loading bar from screen
     */
    void hideLoadingBar();
}
