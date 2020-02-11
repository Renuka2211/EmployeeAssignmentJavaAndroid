package com.boda.renuka.employeeassignmentjavaandroid.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.boda.renuka.employeeassignmentjavaandroid.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "Employee")
public class Employee implements Serializable{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "territory")
    private String territory;
    @ColumnInfo(name = "area")
    private String area;
    @ColumnInfo(name = "name")
    private String name;

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", area='" + area + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
