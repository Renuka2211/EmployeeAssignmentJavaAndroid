package com.boda.renuka.employeeassignmentjavaandroid.model;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


import com.boda.renuka.employeeassignmentjavaandroid.network.EmployeeDataService;
import com.boda.renuka.employeeassignmentjavaandroid.network.RetrofitClient;
import com.boda.renuka.employeeassignmentjavaandroid.persistence.EmployeeDao;
import com.boda.renuka.employeeassignmentjavaandroid.persistence.EmployeeDatabaseClient;
import com.boda.renuka.employeeassignmentjavaandroid.view.MainActivity;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.boda.renuka.employeeassignmentjavaandroid.view.MainActivity.context;

public class EmployeeRepository {
    private static final String TAG = "EmployeeRepository";//
    private ArrayList<Object> objCountryList = new ArrayList<>();
    private List<Zone> objZoneList = new ArrayList<>();
    private List<Region> objRegionList = new ArrayList<>();
    private ArrayList<Object> objAreaList = new ArrayList<>();
    private ArrayList<Object> objEmployeeList = new ArrayList<>();




    private MutableLiveData<List<Object>> mutableLiveData = new MutableLiveData<>();

     EmployeeDao employeeDao;
    public EmployeeRepository() {
        employeeDao =  EmployeeDatabaseClient.getInstance(context).getEmployeeDataBase().employeeDao();
    }
    public MutableLiveData<List<Object>> getMutableLiveData() {
        final EmployeeDataService userDataService = RetrofitClient.getService();
        Call<EmployeeDBResponse> call = userDataService.getEmployees();
        call.enqueue(new Callback<EmployeeDBResponse>() {
            @Override
            public void onResponse(Call<EmployeeDBResponse> call, Response<EmployeeDBResponse> response) {
                EmployeeDBResponse employeeDBResponse = response.body();
                Log.d(TAG,"employeeDBResponse:: "+employeeDBResponse);
                Log.d(TAG,"employeeDBResponse:: "+employeeDBResponse.getResponseData());


                /*if (employeeDBResponse != null && employeeDBResponse.getEmployee() != null) {
                    employees = (ArrayList<Employee>) employeeDBResponse.getEmployee();
                    mutableLiveData.setValue(employees);
                }*/

                try{

                    if(employeeDBResponse.isSuccess()) {
                        if (employeeDBResponse.getResponseData().containsKey("employee")) {
                            Log.d(TAG, "employeeDBResponse:: employee:: " + employeeDBResponse.getResponseData().get("employee"));

                            ArrayList<LinkedTreeMap<String,Object>> empArray = new ArrayList<>();
                            empArray = (ArrayList<LinkedTreeMap<String,Object>>)employeeDBResponse.getResponseData().get("employee");
                            Employee employee;

                            for (int k = 0; k < empArray.size(); k++)
                            {
                                Log.d(TAG, "employeeDBResponse:: loop:: employee:: " + empArray.get(k));

                                employee = new Employee();
                                employee.setArea(empArray.get(k).get("area")!= null ? empArray.get(k).get("area").toString() : "");
                                employee.setName(empArray.get(k).get("name")!= null ? empArray.get(k).get("name").toString() : "");
                                employee.setTerritory(empArray.get(k).get("territory")!= null ? empArray.get(k).get("territory").toString() : "");
                                //employees.add(employee);
                                new SaveData(employee).execute();


                            }

                        }
                        if (employeeDBResponse.getResponseData().containsKey("zone")) {
                            Log.d(TAG, "employeeDBResponse:: zone:: " + employeeDBResponse.getResponseData().get("zone"));
                            ArrayList<LinkedTreeMap<String,Object>> zoneArray = new ArrayList<>();
                            zoneArray = (ArrayList<LinkedTreeMap<String,Object>>)employeeDBResponse.getResponseData().get("zone");
                            Zone zone;

                            for (int k = 0; k < zoneArray.size(); k++)
                            {
                                Log.d(TAG, "employeeDBResponse:: loop:: employee:: " + zoneArray.get(k));

                                zone = new Zone();
                                zone.setZone(zoneArray.get(k).get("zone")!= null ? zoneArray.get(k).get("zone").toString() : "");
                                zone.setTerritory(zoneArray.get(k).get("territory")!= null ? zoneArray.get(k).get("territory").toString() : "");
                                new SaveData(zone).execute();
                            }

                        }
                        if (employeeDBResponse.getResponseData().containsKey("country")) {
                            Log.d(TAG, "employeeDBResponse:: country:: " + employeeDBResponse.getResponseData().get("country"));

                            ArrayList<LinkedTreeMap<String,Object>> countryArray = new ArrayList<>();
                            countryArray = (ArrayList<LinkedTreeMap<String,Object>>)employeeDBResponse.getResponseData().get("country");
                            Country country;

                            for (int k = 0; k < countryArray.size(); k++)
                            {
                                Log.d(TAG, "employeeDBResponse:: loop:: employee:: " + countryArray.get(k));

                                country = new Country();
                                country.setCountry(countryArray.get(k).get("country")!= null ? countryArray.get(k).get("country").toString() : "");
                                country.setTerritory(countryArray.get(k).get("territory")!= null ? countryArray.get(k).get("territory").toString() : "");
                                objCountryList.add(country);

                                new SaveData(country).execute();
                            }
                            mutableLiveData.setValue(objCountryList);

                        }
                        if (employeeDBResponse.getResponseData().containsKey("region")) {
                            Log.d(TAG, "employeeDBResponse:: region:: " + employeeDBResponse.getResponseData().get("region"));

                            ArrayList<LinkedTreeMap<String,Object>> regionArray = new ArrayList<>();
                            regionArray = (ArrayList<LinkedTreeMap<String,Object>>)employeeDBResponse.getResponseData().get("region");
                            Region region;

                            for (int k = 0; k < regionArray.size(); k++)
                            {
                                Log.d(TAG, "employeeDBResponse:: loop:: employee:: " + regionArray.get(k));

                                region = new Region();
                                region.setRegion(regionArray.get(k).get("region")!= null ? regionArray.get(k).get("region").toString() : "");
                                region.setTerritory(regionArray.get(k).get("territory")!= null ? regionArray.get(k).get("territory").toString() : "");
                                new SaveData(region).execute();
                            }


                        }
                        if (employeeDBResponse.getResponseData().containsKey("area")) {
                            Log.d(TAG, "employeeDBResponse:: area:: " + employeeDBResponse.getResponseData().get("area"));

                            ArrayList<LinkedTreeMap<String,Object>> areaArray = new ArrayList<>();
                            areaArray = (ArrayList<LinkedTreeMap<String,Object>>)employeeDBResponse.getResponseData().get("area");
                            Area area;

                            for (int k = 0; k < areaArray.size(); k++)
                            {
                                Log.d(TAG, "employeeDBResponse:: loop:: employee:: " + areaArray.get(k));

                                area = new Area();
                                area.setArea(areaArray.get(k).get("area")!= null ? areaArray.get(k).get("area").toString() : "");
                                area.setTerritory(areaArray.get(k).get("territory")!= null ? areaArray.get(k).get("territory").toString() : "");
                                new SaveData(area).execute();
                            }

                        }
                    }
                    else {
                        Log.d(TAG, "employeeDBResponse:: failure");
                    }


                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(Call<EmployeeDBResponse> call, Throwable t) {
            }
        });
        return mutableLiveData;
    }



    public MutableLiveData<List<Zone>> getMutableLiveData(String strType,String territory) {

        if(strType.equalsIgnoreCase("") || strType.equalsIgnoreCase("country")) {
            MutableLiveData<List<Zone>> mutableLiveData = new MutableLiveData<>();

            new GetZone(territory).execute();
            mutableLiveData.setValue(objZoneList);
            return mutableLiveData;
        }
        return null;
    }

    public MutableLiveData<List<Region>> getMutableLiveDataRegion(String strType,String territory) {

        if(strType.equalsIgnoreCase("") || strType.equalsIgnoreCase("zone")) {
            MutableLiveData<List<Region>> mutableLiveData = new MutableLiveData<>();

            new GetRegion(territory).execute();
            mutableLiveData.setValue(objRegionList);
            return mutableLiveData;
        }
        return null;
    }

    class SaveData extends AsyncTask
    {
        Object obj;
        SaveData(Object obj)
        {
            this.obj = obj;

        }

        @Override
        protected Object doInBackground(Object[] objects) {

            if(obj instanceof Employee)
            {
                employeeDao.insert((Employee) obj);
            }
            if(obj instanceof Country)
            {
                employeeDao.insert((Country) obj);
            }
            if(obj instanceof Zone)
            {
                employeeDao.insert((Zone) obj);
            }
            if(obj instanceof Region)
            {
                employeeDao.insert((Region) obj);
            }
            if(obj instanceof Area)
            {
                employeeDao.insert((Area) obj);
            }


            return null;
        }
    }
    class GetZone extends AsyncTask
    {
        String territory;
        Country country;
        public GetZone(String territory)
        {
           this.territory = territory;



        }

        @Override
        protected Object doInBackground(Object[] objects) {
           // Log.d("EmployeeDataAdapter","EmployeeViewHolder:: onClick:: "+employeeDao.getZone(country.getTerritory()+"%"));
            objZoneList = employeeDao.getZone(territory+"%");
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }

    class GetRegion extends AsyncTask
    {
        String territory;
        Country country;
        public GetRegion(String territory)
        {
            this.territory = territory;



        }

        @Override
        protected Object doInBackground(Object[] objects) {
            // Log.d("EmployeeDataAdapter","EmployeeViewHolder:: onClick:: "+employeeDao.getZone(country.getTerritory()+"%"));
            Log.d("EmployeeDataAdapter","EmployeeViewHolder:: territory:: "+territory);
            objRegionList = employeeDao.getRegion(territory+"%");
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }

}
