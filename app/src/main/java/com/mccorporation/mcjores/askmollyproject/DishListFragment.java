package com.mccorporation.mcjores.askmollyproject;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DishListFragment extends RestaurantsListFragment {

    private RestaurantInfo restaurantInfo;
    private ArrayList<MenuInfo> menuInfos ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_dish, container, false);
        restaurantInfo = new RestaurantInfo();
        final DishInfoFragment dishInfoFragment = new DishInfoFragment();
        ListView listView = view.findViewById(R.id.list_dish);

        DishListAdapter adapter = new DishListAdapter(inflater.getContext(), getMenuIntoRest( setDish() ));

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //cvjnhtnm c.lf thiiss
                dishInfoFragment.setMenu(menuInfos.get(position).getMenu());

                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container_main,dishInfoFragment).commit();
            }
        });
        return view;
    }

    private class DishListAdapter extends ArrayAdapter<MenuInfo> {

        private Context mContext;

        public DishListAdapter(Context context, ArrayList<MenuInfo> listMenu) {
            super(context, R.layout.list_dish, listMenu);
            mContext = context;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.i("RestList", "Position " + position);
            MenuInfo dishMenu = getItem(position);

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row = inflater.inflate(R.layout.list_dish_item, parent, false);

            ImageView DishPhoto = row.findViewById(R.id.dish_photo);
            TextView NameDish = row.findViewById(R.id.dish_name_cafe);
            TextView OpeningHour = row.findViewById(R.id.dish_time);
            TextView DishLocation = row.findViewById(R.id.dish_location);

            if (dishMenu != null) {
                DishPhoto.setImageResource(dishMenu.getDishPhoto());
                NameDish.setText(dishMenu.getDishName());
                OpeningHour.setText(dishMenu.getDishOpeningHour());
                DishLocation.setText(dishMenu.getDishLocation());

            }
            return row;
        }
    }

    private class MenuInfo{
        private int dishPhoto;
        private String dishName;
        private String dishOpeningHour;
        private String dishLocation;
        private DishMenu menu;

        public MenuInfo(int dishPhoto, String dishName, String dishOpeningHour, String dishLocation) {
            this.dishPhoto = dishPhoto;
            this.dishName = dishName;
            this.dishOpeningHour = dishOpeningHour;
            this.dishLocation = dishLocation;
        }

        public MenuInfo(String dishOpeningHour, String dishLocation,DishMenu menu){
            this.dishPhoto = menu.getDishPhoto();
            this.dishName = menu.getName();
            this.dishOpeningHour = dishOpeningHour;
            this.dishLocation = dishLocation;
            this.menu = menu;
        }
        public DishMenu getMenu(){
            return menu;
        }

        public int getDishPhoto() {
            return dishPhoto;
        }

        public String getDishName() {
            return dishName;
        }

        public String getDishOpeningHour() {
            return dishOpeningHour;
        }

        public String getDishLocation() {
            return dishLocation;
        }

    }
    //ЗАГЛУШКА ДЛЯ БЭКА
    //достаем меню из ресторанов
    private ArrayList<MenuInfo> getMenuIntoRest(ArrayList<Restaurants> restaurants){
//        ArrayList<DishMenu> menus = new ArrayList<>();
        menuInfos = new ArrayList<>();

        for (Restaurants dishMenu: restaurants) {
            for (DishMenu menu : dishMenu.getMenus()) {
                menuInfos.add(new MenuInfo(dishMenu.getTimeHours(),dishMenu.getAddress(),menu));
//                menus.addAll(dishMenu.getMenus());
            }
        }
        return menuInfos;
    }
}
