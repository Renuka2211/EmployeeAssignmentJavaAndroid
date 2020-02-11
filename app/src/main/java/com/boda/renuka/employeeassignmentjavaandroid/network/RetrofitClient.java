package com.boda.renuka.employeeassignmentjavaandroid.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
//    private static final String BASE_URL = "https://reqres.in/api/";
private static final String BASE_URL = "http://demo1929804.mockable.io";

    public static EmployeeDataService getService() {
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(EmployeeDataService.class);
    }
}
