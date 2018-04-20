package com.nicholasdoglio.nycschools.data;

import com.nicholasdoglio.nycschools.TestUtil;
import com.nicholasdoglio.nycschools.data.model.SchoolListResponse;
import com.nicholasdoglio.nycschools.data.remote.SchoolService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.when;

/**
 * @author Nicholas Doglio
 */
public class SchoolRepositoryTest {

    @Mock
    SchoolService schoolService;

    private SchoolRepository schoolRepository;
    private TestObserver<List<SchoolListResponse>> testObserver;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        schoolRepository = new SchoolRepository(schoolService);
        testObserver = new TestObserver<>();
    }

    @Test
    public void getListOfSchools() throws Exception {
        List<SchoolListResponse> testList = TestUtil.testListData();

        when(schoolService.getSchoolList()).thenReturn(Single.just(testList));

        schoolRepository.getListOfSchools().subscribe(testObserver);

        testObserver
                .assertNoErrors()
                .assertValue(testList);
    }
}