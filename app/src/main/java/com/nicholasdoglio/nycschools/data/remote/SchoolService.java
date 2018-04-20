package com.nicholasdoglio.nycschools.data.remote;

import com.nicholasdoglio.nycschools.data.model.SchoolDetailResponse;
import com.nicholasdoglio.nycschools.data.model.SchoolListResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Nicholas Doglio
 * <p>
 * Retrofit client for https://data.cityofnewyork.us/resource/
 */
public interface SchoolService {
    /**
     * https://data.cityofnewyork.us/resource/97mf-9njv.json
     *
     * @return
     */
    @GET("97mf-9njv.json")
    Single<List<SchoolListResponse>> getSchoolList();

    /**
     * https://data.cityofnewyork.us/resource/734v-jeq5.json
     *
     * @param dbn
     * @return
     */
    @GET("734v-jeq5.json")
    Single<List<SchoolDetailResponse>> getSchoolDetails(@Query("dbn") String dbn);
}