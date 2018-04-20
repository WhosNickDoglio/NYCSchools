package com.nicholasdoglio.nycschools.ui.detail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nicholasdoglio.nycschools.R;
import com.nicholasdoglio.nycschools.data.model.SchoolDetailResponse;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * @author Nicholas Doglio
 */
public class SchoolDetailFragment extends Fragment implements SchoolDetailContract.View {

    private static final String SCHOOL_DBN = "SCHOOL_DBN";
    private static final String SCHOOL_NAME = "SCHOOL_NAME";

    @BindView(R.id.detail_loading_progress_bar)
    ContentLoadingProgressBar detailLoadingProgressBar;

    @BindView(R.id.school_name)
    TextView schoolNameTextView;

    @BindView(R.id.writing_score)
    TextView writingScoreTextView;

    @BindView(R.id.reading_score)
    TextView readingScoreTextView;

    @BindView(R.id.math_score)
    TextView mathScoreTextView;

    @BindView(R.id.num_test_takers)
    TextView numOfTestTakersTextView;

    @BindView(R.id.no_data_found)
    TextView noDataFoundTextView;

    @BindString(R.string.average_writing_score)
    String averageWritingScore;

    @BindString(R.string.average_reading_score)
    String averageReadingScore;

    @BindString(R.string.average_math_score)
    String averageMathScore;

    @BindString(R.string.num_of_test_takers)
    String numberOfTestTakers;

    @BindString(R.string.network_error)
    String networkError;

    @BindString(R.string.unknown)
    String unknown;

    @Inject
    SchoolDetailPresenter schoolDetailPresenter;

    private Unbinder unbinder;

    public SchoolDetailFragment() {
    }

    public static SchoolDetailFragment newInstance(String dbn, String schoolName) {
        SchoolDetailFragment fragment = new SchoolDetailFragment();
        Bundle args = new Bundle();
        args.putString(SCHOOL_DBN, dbn);
        args.putString(SCHOOL_NAME, schoolName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onActivityCreated(savedInstanceState);
        schoolDetailPresenter.takeView(this);
        schoolDetailPresenter.getSchoolDetails(getArguments().getString(SCHOOL_DBN), getArguments().getString(SCHOOL_NAME), unknown);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_school_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        schoolDetailPresenter.clear();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        schoolDetailPresenter.dropView();
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), networkError, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startingLoadingBar() {
        detailLoadingProgressBar.show();
    }

    @Override
    public void hideLoadingBar() {
        detailLoadingProgressBar.hide();
    }

    @Override
    public void displayData(SchoolDetailResponse schoolDetailResponse) {
        String writingText = averageWritingScore + schoolDetailResponse.getSatWritingAvgScore();
        String readingText = averageReadingScore + schoolDetailResponse.getSatCriticalReadingAvgScore();
        String mathText = averageMathScore + schoolDetailResponse.getSatMathAvgScore();
        String testTakersText = numberOfTestTakers + schoolDetailResponse.getNumOfSatTestTakers();

        schoolNameTextView.setText(schoolDetailResponse.getSchoolName());
        writingScoreTextView.setText(writingText);
        readingScoreTextView.setText(readingText);
        mathScoreTextView.setText(mathText);
        numOfTestTakersTextView.setText(testTakersText);
    }

    @Override
    public void noDataFoundTextView() {
        noDataFoundTextView.setVisibility(View.VISIBLE);
    }
}