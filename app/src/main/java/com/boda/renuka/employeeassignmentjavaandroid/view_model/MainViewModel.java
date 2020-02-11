package com.boda.renuka.employeeassignmentjavaandroid.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.boda.renuka.employeeassignmentjavaandroid.model.Country;
import com.boda.renuka.employeeassignmentjavaandroid.model.Employee;
import com.boda.renuka.employeeassignmentjavaandroid.model.EmployeeRepository;
import com.boda.renuka.employeeassignmentjavaandroid.model.Region;
import com.boda.renuka.employeeassignmentjavaandroid.model.Zone;


import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private EmployeeRepository employeeRepository;
    public MainViewModel(@NonNull Application application) {
        super(application);
        employeeRepository = new EmployeeRepository();
    }
    public LiveData<List<Object>> getAllEmployee() {
        return employeeRepository.getMutableLiveData();
    }
    public LiveData<List<Zone>> getAllEmployee(String strType, String territory) {
        return employeeRepository.getMutableLiveData(strType,territory);
    }

    public LiveData<List<Region>> getAllEmployeeRegion(String strType, String territory) {
        return employeeRepository.getMutableLiveDataRegion(strType,territory);
    }
}