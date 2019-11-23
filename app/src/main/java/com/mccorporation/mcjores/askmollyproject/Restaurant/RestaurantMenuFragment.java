package com.mccorporation.mcjores.askmollyproject.Restaurant;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.mccorporation.mcjores.askmollyproject.Dish.DishInfoFragment;
import com.mccorporation.mcjores.askmollyproject.Dish.DishMenu;
import com.mccorporation.mcjores.askmollyproject.Dish.DishPresenter;
import com.mccorporation.mcjores.askmollyproject.R;

import java.util.ArrayList;

public class RestaurantMenuFragment extends Fragment   {

    private int DishPhoto;
    private String DishName;
    private int Weight;
    private int Price;
    private ArrayList<DishMenu> menus;
    private Context context;
    private ListView listView;

    private DishPresenter dishPresenter;


    @Override
    public void onAttach(Context context) {
//        this.context = context;
        dishPresenter = (DishPresenter) context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.restaurant_menu, container, false);
        listView = view.findViewById(R.id.restaurant_menu_list);
        context = inflater.getContext();
//        menus = new ArrayList<>();
//        menus.add(new DishMenu("Яичница с Авокадо","Питательный и очень полезный завтрак для хорошего настроения",
//                new String[]{"Яйца","авокадо","помидоры","мука пшеничная", "молоко", "лук","соль","черный перец","оливковое масло","укроп"},
//                151,33,4.7,277,250,350));

        RestaurantMenuAdapter adapter = new RestaurantMenuAdapter(context, menus);

        listView.setAdapter(adapter);


        return view;

    }

    //Заглушка для бэка
    public void setMenu(ArrayList<DishMenu> dishMenus) {
        menus = dishMenus;
//        Log.i("RestMenuFragment", "setMenu. Menu.size() = " + menus.size());

    }




    private class RestaurantMenuAdapter extends ArrayAdapter<DishMenu> {

        private Context context;

        public RestaurantMenuAdapter(Context context, ArrayList<DishMenu> menu) {
            super(context, R.layout.restaurant_menu_item, menu);
            this.context = context;
        }


        @Override
        public int getCount() {
            return menus.size();
        }


        @Override
        public DishMenu getItem(int position) {
            return menus.get(position);
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.i("getView", "position " + position);
            final DishMenu menu = getItem(position);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.restaurant_menu_item, parent, false);

            ImageView photoDish = view.findViewById(R.id.iv_menu_photo_dish);
            TextView nameDish = view.findViewById(R.id.tv_menu_name);
            TextView weightDish = view.findViewById(R.id.tv_menu_weight);
            Button addToBuyList = view.findViewById(R.id.btn_menu_add);

            photoDish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceFragment(menu);
                }
            });
            nameDish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    replaceFragment(menu);
                }
            });

            if (menu != null) {
                //setting menu
                photoDish.setImageResource(menu.getDishPhoto());
                nameDish.setText(menu.getName());
                weightDish.setText(String.valueOf(menu.getWeight()));
                addToBuyList.setText("Добавить     " + menu.getPrice() + " Р");
            }
            Log.i("getView", "return view  " + position);
            return view;
        }

        private void replaceFragment(DishMenu menu){
            DishInfoFragment dishInfoFragment = new DishInfoFragment();
            dishInfoFragment.setMenu(menu);
            dishPresenter.loadDish(menu);

            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container_main,dishInfoFragment).commit();
        }

    }
}
