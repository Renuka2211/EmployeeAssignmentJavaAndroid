package com.boda.renuka.employeeassignmentjavaandroid.adapter;

import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boda.renuka.employeeassignmentjavaandroid.R;
import com.boda.renuka.employeeassignmentjavaandroid.databinding.RowLayoutBinding;
import com.boda.renuka.employeeassignmentjavaandroid.model.Country;
import com.boda.renuka.employeeassignmentjavaandroid.model.Employee;
import com.boda.renuka.employeeassignmentjavaandroid.model.Zone;
import com.boda.renuka.employeeassignmentjavaandroid.persistence.EmployeeDao;
import com.boda.renuka.employeeassignmentjavaandroid.persistence.EmployeeDatabaseClient;

import java.util.ArrayList;

import static com.boda.renuka.employeeassignmentjavaandroid.view.MainActivity.context;

public class EmployeeDataAdapter extends RecyclerView.Adapter<EmployeeDataAdapter.EmployeeViewHolder> {
    private ArrayList<Object> dataList;

    OnItemClickListener listener;
    EmployeeDao employeeDao;
    String strType = "";

    public EmployeeDataAdapter(OnItemClickListener listener)
    {
        this.listener = listener;
        employeeDao =  EmployeeDatabaseClient.getInstance(context).getEmployeeDataBase().employeeDao();
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RowLayoutBinding employeeListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.row_layout, viewGroup, false);

        return new EmployeeViewHolder(employeeListItemBinding,i);
//        return null;

    }
    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder employeeViewHolder, int i) {



        if(dataList.get(i) instanceof Country) {
            Country country = (Country) dataList.get(i);
            employeeViewHolder.employeeListItemBinding.setEmployee(country);
        }



    }
    @Override
    public int getItemCount() {

        if (dataList != null) {
            return dataList.size();
        } else {
            return 0;
        }
    }
    public void setEmployeeList(ArrayList<Object> employees) {
        this.dataList = employees;
        notifyDataSetChanged();
    }

    public void setEmployeeList(ArrayList<Zone> employees,String strType) {
        notifyDataSetChanged();
        this.strType = strType;
    }


    class EmployeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int position = 0;

         private RowLayoutBinding employeeListItemBinding;
        public EmployeeViewHolder(@NonNull RowLayoutBinding employeetListItemBinding,int position) {
            super(employeetListItemBinding.getRoot());
            employeetListItemBinding.getRoot().setOnClickListener(this);
            this.employeeListItemBinding = employeetListItemBinding;
            this.position = position;
        }
        @Override
        public void onClick(View v) {
            listener.onItemClick(dataList.get(position));
            //new GetZone((Country) dataList.get(position)).execute();
        }

    }
    public interface OnItemClickListener {
        void onItemClick(Object item);
    }

}