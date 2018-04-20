package com.nicholasdoglio.nycschools.ui.detail;

import com.nicholasdoglio.nycschools.TestUtil;
import com.nicholasdoglio.nycschools.data.model.SchoolDetailResponse;
import com.nicholasdoglio.nycschools.data.remote.SchoolService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Nicholas Doglio
 */
public class SchoolDetailPresenterTest {

    @Mock
    SchoolDetailContract.View view;

    @Mock
    SchoolService schoolService;

    private SchoolDetailPresenter schoolDetailPresenter;

    @Before
    public void setUp() throws Exception {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
        MockitoAnnotations.initMocks(this);

        schoolDetailPresenter = new SchoolDetailPresenter(schoolService);
        schoolDetailPresenter.takeView(view);
    }

    @Test
    public void getSchoolDetails() throws Exception {
        List<SchoolDetailResponse> schoolDetailResponse = TestUtil.testData();

        when(schoolService.getSchoolDetails("1000")).thenReturn(Single.just(schoolDetailResponse));

        schoolDetailPresenter.getSchoolDetails("1000", "Division Avenue High School", "Unknown");
        verify(view).hideLoadingBar();
        verify(view).displayData(schoolDetailResponse.get(0));
    }
}