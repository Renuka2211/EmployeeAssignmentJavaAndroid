package com.boda.renuka.employeeassignmentjavaandroid.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boda.renuka.employeeassignmentjavaandroid.R;
import com.boda.renuka.employeeassignmentjavaandroid.databinding.RegionLayoutBinding;
import com.boda.renuka.employeeassignmentjavaandroid.databinding.ZoneLayoutBinding;
import com.boda.renuka.employeeassignmentjavaandroid.model.Region;
import com.boda.renuka.employeeassignmentjavaandroid.model.Zone;
import com.boda.renuka.employeeassignmentjavaandroid.persistence.EmployeeDao;
import com.boda.renuka.employeeassignmentjavaandroid.persistence.EmployeeDatabaseClient;

import java.util.ArrayList;

import static com.boda.renuka.employeeassignmentjavaandroid.view.MainActivity.context;

public class RegionDataAdapter extends RecyclerView.Adapter<RegionDataAdapter.EmployeeViewHolder> {
    private ArrayList<Region> dataList;

    OnItemRegionClickListener listener;
    EmployeeDao employeeDao;
    String strType = "";

    public RegionDataAdapter(OnItemRegionClickListener listener)
    {
        this.listener = listener;
        employeeDao =  EmployeeDatabaseClient.getInstance(context).getEmployeeDataBase().employeeDao();
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RegionLayoutBinding employeeListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.region_layout, viewGroup, false);

        return new EmployeeViewHolder(employeeListItemBinding,i);
//        return null;

    }
    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder employeeViewHolder, int i) {



        if(dataList.get(i) instanceof Region) {
            Region region = (Region) dataList.get(i);
            employeeViewHolder.employeeListItemBinding.setRegion(region);
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
    public void setEmployeeList(ArrayList<Region> employees) {
        this.dataList = employees;
        notifyDataSetChanged();
    }

    public void setEmployeeList(ArrayList<Zone> employees, String strType) {
        notifyDataSetChanged();
        this.strType = strType;
    }


    class EmployeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int position = 0;

        private RegionLayoutBinding employeeListItemBinding;
        public EmployeeViewHolder(@NonNull RegionLayoutBinding employeetListItemBinding,int position) {
            super(employeetListItemBinding.getRoot());
            employeetListItemBinding.getRoot().setOnClickListener(this);
            this.employeeListItemBinding = employeetListItemBinding;
            this.position = position;
        }
        @Override
        public void onClick(View v) {
            listener.onItemRegionClick(dataList.get(position));
            //new GetZone((Country) dataList.get(position)).execute();
        }

    }
    public interface OnItemRegionClickListener {
        void onItemRegionClick(Object item);
    }

}
