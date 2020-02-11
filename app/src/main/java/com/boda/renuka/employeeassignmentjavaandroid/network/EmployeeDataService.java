package com.boda.renuka.employeeassignmentjavaandroid.network;

import com.boda.renuka.employeeassignmentjavaandroid.model.EmployeeDBResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeDataService {
    @GET("/assignment")
    Call<EmployeeDBResponse> getEmployees();
}
