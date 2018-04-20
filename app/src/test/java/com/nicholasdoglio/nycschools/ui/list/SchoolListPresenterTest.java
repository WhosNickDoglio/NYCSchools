package com.nicholasdoglio.nycschools.ui.list;

import com.nicholasdoglio.nycschools.TestUtil;
import com.nicholasdoglio.nycschools.data.SchoolRepository;
import com.nicholasdoglio.nycschools.data.model.SchoolListResponse;

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
public class SchoolListPresenterTest {

    @Mock
    SchoolListContract.View view;

    @Mock
    SchoolRepository schoolRepository;

    private SchoolListPresenter schoolListPresenter;

    @Before
    public void setUp() throws Exception {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());

        MockitoAnnotations.initMocks(this);

        schoolListPresenter = new SchoolListPresenter(schoolRepository);
        schoolListPresenter.takeView(view);
    }

    @Test
    public void getSchoolList() throws Exception {
        List<SchoolListResponse> testData = TestUtil.testListData();

        when(schoolRepository.getListOfSchools()).thenReturn(Single.just(testData));

        schoolListPresenter.getSchoolList();

        verify(view).hideLoadingBar();
        verify(view).setList(testData);
    }
}