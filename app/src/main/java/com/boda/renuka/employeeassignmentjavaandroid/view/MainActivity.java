package com.boda.renuka.employeeassignmentjavaandroid.view;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.boda.renuka.employeeassignmentjavaandroid.R;
import com.boda.renuka.employeeassignmentjavaandroid.adapter.EmployeeDataAdapter;
import com.boda.renuka.employeeassignmentjavaandroid.adapter.RegionDataAdapter;
import com.boda.renuka.employeeassignmentjavaandroid.adapter.ZoneDataAdapter;
import com.boda.renuka.employeeassignmentjavaandroid.databinding.ActivityMainBinding;
import com.boda.renuka.employeeassignmentjavaandroid.model.Area;
import com.boda.renuka.employeeassignmentjavaandroid.model.Country;
import com.boda.renuka.employeeassignmentjavaandroid.model.Employee;
import com.boda.renuka.employeeassignmentjavaandroid.model.Region;
import com.boda.renuka.employeeassignmentjavaandroid.model.Zone;
import com.boda.renuka.employeeassignmentjavaandroid.persistence.EmployeeDataBase;
import com.boda.renuka.employeeassignmentjavaandroid.persistence.EmployeeDatabaseClient;
import com.boda.renuka.employeeassignmentjavaandroid.view_model.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EmployeeDataAdapter.OnItemClickListener,ZoneDataAdapter.OnItemZoneClickListener
,RegionDataAdapter.OnItemRegionClickListener {
    private MainViewModel mainViewModel;
    private EmployeeDataAdapter employeeDataAdapter;
    public static Context context;
    RecyclerView recyclerView;


//    ActivityMainBinding
    @Override
    protected void onStart() {
        super.onStart();
//        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();

        ActivityMainBinding activityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        // bind RecyclerView
        recyclerView = activityMainBinding.viewEmployees;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        employeeDataAdapter = new EmployeeDataAdapter(this);
        recyclerView.setAdapter(employeeDataAdapter);

        getAllEmployee();


    }
    private void getAllEmployee() {
        mainViewModel.getAllEmployee().observe(this, new Observer<List<Object>>() {
            @Override
            public void onChanged(@Nullable List<Object> employees) {
                employeeDataAdapter.setEmployeeList((ArrayList<Object>) employees);
            }
        });
    }

    @Override
    public void onItemClick(Object item) {
        Toast.makeText(MainActivity.this,"item Selected:: "+((Country)item).getTerritory(),Toast.LENGTH_SHORT).show();
        String strTytpe = "";
        if(item instanceof Country)
        {
           final ZoneDataAdapter zoneDataAdapter = new ZoneDataAdapter(this);
            recyclerView.setAdapter(zoneDataAdapter);
            strTytpe = "country";

            final String finalStrTytpe = strTytpe;
            mainViewModel.getAllEmployee(strTytpe,((Country)item).getTerritory()).observe(this, new Observer<List<Zone>>() {
                @Override
                public void onChanged(@Nullable List<Zone> employees) {
                    zoneDataAdapter.setEmployeeList((ArrayList<Zone>) employees);
                }
            });
        }
        else if(item instanceof Zone){
            strTytpe = "zone";

        }

        else if(item instanceof Region){
            strTytpe = "region";

        }

        else if(item instanceof Area){
            strTytpe = "area";

        }

        else if(item instanceof Employee){
            strTytpe = "employee";

        }





    }

    @Override
    public void onItemZoneClick(Object item) {
        String strTytpe = "";

        if(item instanceof Zone){
            strTytpe = "zone";
            final RegionDataAdapter regionDataAdapter = new RegionDataAdapter(this);
            recyclerView.setAdapter(regionDataAdapter);

            final String finalStrTytpe = strTytpe;
            mainViewModel.getAllEmployeeRegion(strTytpe,((Zone)item).getTerritory()).observe(this, new Observer<List<Region>>() {
                @Override
                public void onChanged(@Nullable List<Region> employees) {
                    regionDataAdapter.setEmployeeList((ArrayList<Region>) employees);
                }
            });

        }
    }

    @Override
    public void onItemRegionClick(Object item) {

    }

    class GetData extends AsyncTask
    {

        @Override
        protected Object doInBackground(Object[] objects) {
            EmployeeDatabaseClient.getInstance(getApplicationContext()).getEmployeeDataBase()
                    .employeeDao().getAll();
            return null;
        }
    }

}
