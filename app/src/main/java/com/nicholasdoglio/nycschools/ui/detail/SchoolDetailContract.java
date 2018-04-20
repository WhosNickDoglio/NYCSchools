package com.nicholasdoglio.nycschools.ui.detail;

import com.nicholasdoglio.nycschools.data.model.SchoolDetailResponse;
import com.nicholasdoglio.nycschools.ui.base.BasePresenter;
import com.nicholasdoglio.nycschools.ui.base.BaseView;

/**
 * @author Nicholas Doglio
 * Outlines the relationship between SchoolDetailContract.View and SchoolDetailContract.Presenter
 */
public interface SchoolDetailContract {
    interface View extends BaseView<Presenter> {
        /**
         * Provides data to the TextViews
         *
         * @param schoolDetailResponse
         */
        void displayData(SchoolDetailResponse schoolDetailResponse);

        /**
         * Shows noDataFoundTextView when SAT query fails
         */
        void noDataFoundTextView();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * @param dbn:        Unique id for each School to query for SAT records
         * @param schoolName: Name of school in SAT query in case query fails
         * @param unknown:    String resource passed to TextViews in case query fails
         */
        void getSchoolDetails(String dbn, String schoolName, String unknown);

    }
}
