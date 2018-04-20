package com.nicholasdoglio.nycschools;

import com.nicholasdoglio.nycschools.data.model.SchoolDetailResponse;
import com.nicholasdoglio.nycschools.data.model.SchoolListResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicholas Doglio
 * Test Utility to provide test data
 */
public class TestUtil {

    public static List<SchoolListResponse> testListData() {
        List<SchoolListResponse> schoolListResponses = new ArrayList<>();
        SchoolListResponse firstSchool = new SchoolListResponse();
        firstSchool.setSchoolName("Division Avenue High School");
        firstSchool.setDbn("10000");
        SchoolListResponse secondSchool = new SchoolListResponse();
        secondSchool.setSchoolName("High School");
        secondSchool.setDbn("2000");
        SchoolListResponse thirdSchool = new SchoolListResponse();
        thirdSchool.setSchoolName("Levittown High School");
        thirdSchool.setDbn("3000");
        schoolListResponses.add(firstSchool);
        schoolListResponses.add(secondSchool);

        return schoolListResponses;
    }

    public static List<SchoolDetailResponse> testData() {
        List<SchoolDetailResponse> schoolDetailResponseList = new ArrayList<>();
        SchoolDetailResponse schoolDetailResponse = new SchoolDetailResponse();
        schoolDetailResponse.setDbn("1000");
        schoolDetailResponse.setSchoolName("Division Avenue High School");
        schoolDetailResponseList.add(schoolDetailResponse);

        return schoolDetailResponseList;
    }
}