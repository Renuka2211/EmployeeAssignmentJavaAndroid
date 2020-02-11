package com.boda.renuka.employeeassignmentjavaandroid.persistence;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import com.boda.renuka.employeeassignmentjavaandroid.model.Area;
import com.boda.renuka.employeeassignmentjavaandroid.model.Country;
import com.boda.renuka.employeeassignmentjavaandroid.model.Employee;
import com.boda.renuka.employeeassignmentjavaandroid.model.Region;
import com.boda.renuka.employeeassignmentjavaandroid.model.Zone;

@Database(entities = {Employee.class,Zone.class,Region.class,Area.class,Country.class}, version = 1, exportSchema = false)
public abstract class EmployeeDataBase extends RoomDatabase {
    public abstract EmployeeDao employeeDao();
}
