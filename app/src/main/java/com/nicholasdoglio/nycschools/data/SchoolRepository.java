package com.nicholasdoglio.nycschools.data;

import com.nicholasdoglio.nycschools.data.model.SchoolListResponse;
import com.nicholasdoglio.nycschools.data.remote.SchoolService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * @author Nicholas Doglio
 * Repository layer for caching data to avoid unnecessary network calls
 */
public class SchoolRepository {

    private final SchoolService schoolService;
    private List<SchoolListResponse> listOfSchools;

    @Inject
    public SchoolRepository(SchoolService schoolService) {
        this.schoolService = schoolService;
        listOfSchools = new ArrayList<>();
    }

    /**
     * Makes a network call if data set is empty otherwise returns data
     *
     * @return listOfSchools: list of school information
     */
    public Single<List<SchoolListResponse>> getListOfSchools() {
        //This would need to be more complex if the data was frequently changing
        if (listOfSchools.isEmpty()) {
            return schoolService.getSchoolList()
                    .map(schoolListResponses -> {
                        listOfSchools.addAll(schoolListResponses);

                        return listOfSchools;
                    });
        } else {
            return Single.just(listOfSchools);
        }
    }
}