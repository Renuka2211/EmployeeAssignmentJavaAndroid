package com.boda.renuka.employeeassignmentjavaandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;


public class EmployeeDBResponse {
   private int ResponseStatus = 0;

   private HashMap<String,Object> ResponseData = new HashMap<String,Object>();

   private boolean Success = false;

    public int getResponseStatus() {
        return ResponseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        ResponseStatus = responseStatus;
    }

    public HashMap<String, Object> getResponseData() {
        return ResponseData;
    }

    public void setResponseData(HashMap<String, Object> responseData) {
        ResponseData = responseData;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    @Override
    public String toString() {
        return "EmployeeDBResponse{" +
                "ResponseStatus=" + ResponseStatus +
                ", ResponseData=" + ResponseData +
                ", Success=" + Success +
                '}';
    }
}
