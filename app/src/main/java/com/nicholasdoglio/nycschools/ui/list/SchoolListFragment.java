package com.nicholasdoglio.nycschools.ui.list;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nicholasdoglio.nycschools.R;
import com.nicholasdoglio.nycschools.data.model.SchoolListResponse;
import com.nicholasdoglio.nycschools.ui.common.NavigationController;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * @author Nicholas Doglio
 */
public class SchoolListFragment extends Fragment implements SchoolListContract.View {

    @BindView(R.id.schools_list)
    RecyclerView schoolListRecyclerView;

    @BindView(R.id.list_loading_progress_bar)
    ContentLoadingProgressBar listLoadingProgressBar;

    @BindString(R.string.network_error)
    String networkError;

    @Inject
    NavigationController navigationController;

    @Inject
    SchoolListPresenter schoolListPresenter;

    private SchoolListAdapter schoolListAdapter;
    private Unbinder unbinder;

    public SchoolListFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onActivityCreated(savedInstanceState);
        schoolListPresenter.takeView(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());

        schoolListAdapter = new SchoolListAdapter(navigationController);
        schoolListRecyclerView.setAdapter(schoolListAdapter);
        schoolListRecyclerView.setLayoutManager(linearLayoutManager);
        schoolListRecyclerView.addItemDecoration(dividerItemDecoration);
        schoolListRecyclerView.setHasFixedSize(true);

        schoolListPresenter.getSchoolList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_school_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        schoolListPresenter.clear();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        schoolListPresenter.dropView();
        unbinder.unbind();
    }

    @Override
    public void setList(List<SchoolListResponse> schoolListResponses) {
        schoolListAdapter.setList(schoolListResponses);
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), networkError, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startingLoadingBar() {
        listLoadingProgressBar.show();
    }

    @Override
    public void hideLoadingBar() {
        listLoadingProgressBar.hide();
    }
}