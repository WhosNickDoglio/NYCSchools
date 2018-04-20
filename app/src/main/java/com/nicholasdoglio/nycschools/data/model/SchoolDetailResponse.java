package com.nicholasdoglio.nycschools.data.model;


import com.squareup.moshi.Json;

/**
 * @author Nicholas Doglio
 */
public class SchoolDetailResponse {

    @Json(name = "dbn")
    private String dbn;

    @Json(name = "sat_writing_avg_score")
    private String satWritingAvgScore;

    @Json(name = "sat_critical_reading_avg_score")
    private String satCriticalReadingAvgScore;

    @Json(name = "sat_math_avg_score")
    private String satMathAvgScore;

    @Json(name = "school_name")
    private String schoolName;

    @Json(name = "num_of_sat_test_takers")
    private String numOfSatTestTakers;

    public String getDbn() {
        return dbn;
    }

    public void setDbn(String dbn) {
        this.dbn = dbn;
    }

    public String getSatWritingAvgScore() {
        return satWritingAvgScore;
    }

    public void setSatWritingAvgScore(String satWritingAvgScore) {
        this.satWritingAvgScore = satWritingAvgScore;
    }

    public String getSatCriticalReadingAvgScore() {
        return satCriticalReadingAvgScore;
    }

    public void setSatCriticalReadingAvgScore(String satCriticalReadingAvgScore) {
        this.satCriticalReadingAvgScore = satCriticalReadingAvgScore;
    }

    public String getSatMathAvgScore() {
        return satMathAvgScore;
    }

    public void setSatMathAvgScore(String satMathAvgScore) {
        this.satMathAvgScore = satMathAvgScore;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getNumOfSatTestTakers() {
        return numOfSatTestTakers;
    }

    public void setNumOfSatTestTakers(String numOfSatTestTakers) {
        this.numOfSatTestTakers = numOfSatTestTakers;
    }
}