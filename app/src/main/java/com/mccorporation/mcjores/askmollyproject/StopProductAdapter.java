package com.mccorporation.mcjores.askmollyproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;


//
//    @Override
//    public Object getItem(int position) {
//        return super.getItem(position);
//    }
//
//    @Override
//    public boolean isEnabled(int position) {
//        return super.isEnabled(position);
//    }
//}
            //Если понадобится при прокрутке сохранять состояние чекбокса в скроле - чекнуть код
//    public class StopProductAdapter extends ArrayAdapter {
//        private SparseBooleanArray mCheckStates;
//        private LayoutInflater mLayoutInflater;
//        private CheckBox checkBox;
//        private Context context;
//        private ArrayList<String> stopList;
//
//        public StopProductAdapter(Context context) {
//            super(context, R.layout.stop_eat_item);
//            this.context = context;
//            mCheckStates = new SparseBooleanArray();
//            mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return position;
//        }
//
//
//        @Override
//        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
////            CheckBox bu = (CheckBox) getItem(position);
////
////            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////            View view = inflater.inflate(R.layout.stop_eat_item, parent, false);
////            CheckBox checkBox = view.findViewById(R.id.stop_checkbox);
////            if (bu != null)
////                checkBox.setText(stopListArray[position]);
////
////            return view;
//            Log.i("StopProduct", " checkbox " + checkBox);
//
//            if (convertView == null) {
//                convertView = mLayoutInflater.inflate(R.layout.stop_eat_item, null);
//            }
//            checkBox = convertView.findViewById(R.id.stop_checkbox);
//            checkBox.setTag(position);
//            checkBox.setChecked(mCheckStates.get(position, false));
//
//            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                    mCheckStates.put((Integer) compoundButton.getTag(), isChecked);
//                }
//            });
//            checkBox.setText(stopListArray[position]);
//
//            Log.i("StopProduct", " checkbox " + checkBox + " view " + convertView);
//
//            return convertView;
//        }
//
//        public boolean isChecked(int pos) {
//            return mCheckStates.get(pos, false);
//        }
//
//        public void setChecked(int position, boolean isChecked) {
//            mCheckStates.put(position, isChecked);
//        }
//
//        public void toogle(int position) {
//            setChecked(position, !isChecked(position));
//        }
//
//
//    }
//}

