package com.mccorporation.mcjores.askmollyproject.Restaurant;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mccorporation.mcjores.askmollyproject.R;

public class RestaurantDescription extends Fragment {


    private TextView tv_description;
    private TextView tv_adress;
    private TextView tv_average_check;
    private TextView tv_working_hours;

    private String WorkingHours;
    private String Address;
    private String Description;
    private int AverageCheck;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.restaurant_description,container,false);

         tv_description = view.findViewById(R.id.descr_description);
         tv_working_hours = view.findViewById(R.id.descr_working_hours);
         tv_adress = view.findViewById(R.id.descr_address);
         tv_average_check = view.findViewById(R.id.descr_average_chech);

         tv_description.setText(Description);
         tv_working_hours.setText(WorkingHours);
         tv_adress.setText(Address);
         tv_average_check.setText(AverageCheck + " P");

         return view;
    }

    public void setParam(String description, String workingHours, String address, int averageCheck){
     WorkingHours = workingHours;
     Address = address;
     AverageCheck = averageCheck;
     Description = description;
    }
}
