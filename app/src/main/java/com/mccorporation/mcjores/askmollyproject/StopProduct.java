package com.mccorporation.mcjores.askmollyproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.Toast;

//import androidx.annotation.RecentlyNonNull;

import java.util.ArrayList;

public class StopProduct extends Fragment {
    private String[] stopListArray = {"Глютен", "Клуюника", "Черный перец", "Яблоки", "Орехи", "Рыба", "Моллюски", "Арахис", "Сахар", "Лактоза", "Цитрус", "Яйца", "Соя", "Глюкоза"};
    private ArrayList<String> saveList;
    private SharedPreferences spStopProducts;
    private Button btnSave;
    private StopProductAdapter stopProductAdapter;
    private final String APP_PREFERENCE_STOP_PRODUCT = "StopProduct";
    private Context context;
    private SparseBooleanArray mCheckStates;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stop_eat, container, false);


        GridView gridView = view.findViewById(R.id.stop_eat_grid);


//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),android.R.layout.simple_list_item_1,stopListArray);
        saveList = new ArrayList<>();
        LoadPreferences();
        stopProductAdapter = new StopProductAdapter(inflater.getContext(), R.layout.stop_eat_item, stopListArray);
        context = inflater.getContext();

        gridView.setAdapter(stopProductAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("StopProduct", " adapter " + adapterView.getItemAtPosition(i));
            }
        });


        spStopProducts = this.getActivity().getSharedPreferences(APP_PREFERENCE_STOP_PRODUCT, Context.MODE_PRIVATE);

        btnSave = view.findViewById(R.id.stop_eat_btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //shared pref
                SavePreferences();
            }
        });
        return view;
    }

    private void SavePreferences() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(
                APP_PREFERENCE_STOP_PRODUCT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Status_size", saveList.size());
        for (int i = 0; i < saveList.size(); i++){
            editor.remove("savelist_" + i);
            Log.i("save","clearPosition" + i);}

        for (int i = 0; i < saveList.size(); i++){
            editor.putString("savelist_" + i, saveList.get(i));
            Log.i("save","savePosition" + i);}
        editor.apply();
        Toast.makeText(context,"Сохранено",Toast.LENGTH_SHORT).show();
    }


    private void LoadPreferences() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(
                APP_PREFERENCE_STOP_PRODUCT, Context.MODE_PRIVATE);
        int size = sharedPreferences.getInt("Status_size", 0);
        saveList.clear();
        for (int i = 0; i < size; i++){
            saveList.add(sharedPreferences.getString("savelist_" + i, null));
            Log.i("save","LoadPosition" + i);}
    }
//_____________________________________________________________

    public class StopProductAdapter extends ArrayAdapter {
        private Context context;
        private ArrayList<String> stopList;

        private int i = 0;
        private String[] stopListArray = {"Глютен", "Клуюника", "Черный перец", "Яблоки", "Орехи", "Рыба", "Моллюски", "Арахис", "Сахар",
                "Лактоза", "Цитрус", "Яйца", "Соя", "Глюкоза"};

        public StopProductAdapter(Context context, int resource, String[] data) {
            super(context, resource, data);
            this.context = context;
            mCheckStates = new SparseBooleanArray();
        }


        @Override
        public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
//        CheckBox bu = (CheckBox) getItem(position);

            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null)
                convertView = inflater.inflate(R.layout.stop_eat_item, parent, false);

            final CheckBox checkBox = convertView.findViewById(R.id.stop_checkbox);

            checkBox.setChecked(saveList.contains(stopListArray[position]));    // если чекбокс был отмечен то мы восстанавливаем состояние
            checkBox.setText(stopListArray[position]);
            checkBox.setTag(position);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Log.i("StopProduct", "i = " + i + " " + compoundButton.getTag() + "bool = " + b);
//                 checkBox.setChecked(mCheckStates.get(position,false));
                    mCheckStates.put((Integer) compoundButton.getTag(), b);
                    addToSave(position);
                }
            });

            return convertView;
        }



        private void addToSave(int position) {
            if (isChecked(position)){
                Log.i("save","isChecked = " + true);
                saveList.add(stopListArray[position]);
            }
            else saveList.remove(stopListArray[position]);

        }

        public boolean isChecked(int pos) {
            return mCheckStates.get(pos, false);
        }

        public void setChecked(int position, boolean isChecked) {
            mCheckStates.put(position, isChecked);
        }

        public void toogle(int position) {
            setChecked(position, !isChecked(position));
        }
    }
}