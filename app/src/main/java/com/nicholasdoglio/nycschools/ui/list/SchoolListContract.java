package com.nicholasdoglio.nycschools.ui.list;

import com.nicholasdoglio.nycschools.data.model.SchoolListResponse;
import com.nicholasdoglio.nycschools.ui.base.BasePresenter;
import com.nicholasdoglio.nycschools.ui.base.BaseView;

import java.util.List;

/**
 * @author Nicholas Doglio
 * Outlines the relationship between SchoolListContract.View and SchoolListContract.Presenter
 */
public interface SchoolListContract {

    interface View extends BaseView<Presenter> {

        /**
         * Provides data to the RecyclerView Adapter
         *
         * @param schoolListResponses:
         */
        void setList(List<SchoolListResponse> schoolListResponses);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * Makes request to SchoolRepository for data
         */
        void getSchoolList();
    }

}
