package com.boda.renuka.employeeassignmentjavaandroid.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.boda.renuka.employeeassignmentjavaandroid.model.Area;
import com.boda.renuka.employeeassignmentjavaandroid.model.Country;
import com.boda.renuka.employeeassignmentjavaandroid.model.Employee;
import com.boda.renuka.employeeassignmentjavaandroid.model.Region;
import com.boda.renuka.employeeassignmentjavaandroid.model.Zone;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Query("SELECT * FROM Employee")
    List<Employee> getAll();
    @Insert
    void insert(Employee employee);

    @Insert
    void insert(Area area);
    @Insert
    void insert(Country country);
    @Insert
    void insert(Region region);

    @Insert
    void insert(Zone zone);

    @Query("SELECT * FROM Zone where territory like :territory")
    List<Zone> getZone(String territory);

    @Query("SELECT * FROM Region where territory like :territory")
    List<Region> getRegion(String territory);


}
