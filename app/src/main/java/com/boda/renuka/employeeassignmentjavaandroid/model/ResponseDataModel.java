package com.boda.renuka.employeeassignmentjavaandroid.model;

import org.json.JSONArray;

public class ResponseDataModel {

    private JSONArray country = new JSONArray() ;

    private JSONArray zone = new JSONArray() ;

    private JSONArray region = new JSONArray() ;

    private JSONArray area = new JSONArray() ;

    private JSONArray employee = new JSONArray() ;

    public JSONArray getCountry() {
        return country;
    }

    public void setCountry(JSONArray country) {
        this.country = country;
    }

    public JSONArray getZone() {
        return zone;
    }

    public void setZone(JSONArray zone) {
        this.zone = zone;
    }

    public JSONArray getRegion() {
        return region;
    }

    public void setRegion(JSONArray region) {
        this.region = region;
    }

    public JSONArray getArea() {
        return area;
    }

    public void setArea(JSONArray area) {
        this.area = area;
    }

    public JSONArray getEmployee() {
        return employee;
    }

    public void setEmployee(JSONArray employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "ResponseDataModel{" +
                "country=" + country +
                ", zone=" + zone +
                ", region=" + region +
                ", area=" + area +
                ", employee=" + employee +
                '}';
    }
}
