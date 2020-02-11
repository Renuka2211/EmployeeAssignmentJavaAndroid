package com.boda.renuka.employeeassignmentjavaandroid.persistence;

import android.arch.persistence.room.Room;
import android.content.Context;

public class EmployeeDatabaseClient {

    private Context mCtx;
    private static EmployeeDatabaseClient mInstance;

    //our app database object
    private EmployeeDataBase employeeDataBase;

    private EmployeeDatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        employeeDataBase = Room.databaseBuilder(mCtx, EmployeeDataBase.class, "EmployeeDb").build();
    }

    public static synchronized EmployeeDatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new EmployeeDatabaseClient(mCtx);
        }
        return mInstance;
    }

    public EmployeeDataBase getEmployeeDataBase() {
        return employeeDataBase;
    }
}
