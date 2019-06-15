package com.mccorporation.mcjores.askmollyproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RestaurantsListFragment extends Fragment {
    Context mContext;
    ArrayList<Restaurants> restaurantsArrayList;
    ArrayList<DishMenu> menu1;
    ArrayList<DishMenu> menu2;
    ArrayList<DishMenu> menu3;
    RestaurantInfo restaurantInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_fragment,container,false);
        ListView listView = view.findViewById(R.id.dish_list);
        restaurantInfo = new RestaurantInfo();
        final RestaurantsListAdapter restaurantsListAdapter = new RestaurantsListAdapter(inflater.getContext(),setDish());
        listView.setAdapter(restaurantsListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurants cafe = restaurantsArrayList.get(position);
                restaurantInfo.setRestaurantInfo(cafe.getNameCafe(), cafe.getPhotoRest(),
                        cafe.getRestaurantDescription(),cafe.getAddress(),cafe.getAverageCheck(), cafe.getMenus());
                Log.i("RestList","onCreateView after");

                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container_main,restaurantInfo).commit();
            }
        });


        return view;
    }
    //ЗАГЛУШКА ДЛЯ БЭКА(создание ресторанов)
    private ArrayList<Restaurants> setDish(){
        restaurantsArrayList = new ArrayList<>();
        restaurantsArrayList.add(new Restaurants("Zктория","Лубянка",5.0,R.drawable.png_mohito,
                "Уютная атмосфера, разнообразие японской кужни",1700,menu2));
        restaurantsArrayList.add(new Restaurants("Good Day Cafe","Лубянка",4.1,R.drawable.png_salad,
                "Уютная атмосфера, разнообразие кухни и мнооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооогого другого",1500,menu1));
        restaurantsArrayList.add(new Restaurants("Loving Soup","ул. Московская 35",2.1,R.drawable.png_mohito,
                "Уютная атмосфера, разнообразие японской завтраков",1000,menu3));

        return restaurantsArrayList;
    }

    //ЗАГЛУШКА ДЛЯ БЭКА(добавление меню)
    private void setMenus(){
        menu1.add(new DishMenu("Яичница с Авокадо","Питательный и очень полезный завтрак для хорошего настроения",
                new String[]{"Яйца,авокадо,помидоры,мука пшеничная, молоко , лук,соль,черный перец,оливковое масло,укроп"},
                151,33,4.7,277,250,150));
        menu1.add(new DishMenu("Ананас и анис","Питательный и очень полезный завтрак из ананаса и аниса",
                new String[]{"Ананас, Анис"},
                151,33,4.7,277,250,150));
        menu1.add(new DishMenu("Яичница с брусникой","Питательный и очень полезный завтрак с брусникой",
                new String[]{"Яйца, Брусника"},
                151,33,4.7,277,250,150));

        menu2.add(new DishMenu("Ролл с грибами","Питательный и очень полезный ролл с грибами",
                new String[]{"Рис, Грибы, Укроп"},
                151,33,4.7,277,250,150));
        menu2.add(new DishMenu("Ролл с крабом","Питательный и очень вкусный ролл с крабом. Подается с арахисовым соусом",
                new String[]{"Рис,Краб, Арахис"},
                151,33,4.7,277,250,150));
        menu2.add(new DishMenu("Ролл *Драгон*","Питательный и очень вкусный ролл с запеченым лососем",
                new String[]{"Рис,Лосось"},
                151,33,4.7,277,250,150));

        menu3.add(new DishMenu("Яичница с Вишней","Питательный и очень полезный завтрак для хорошего настроения",
                new String[]{"Яйца,вишня,помидоры,мука пшеничная, молоко , лук,соль,черный перец,оливковое масло,укроп"},
                151,33,4.7,277,250,150));
        menu3.add(new DishMenu("Яичница с грейпфрутом","Питательный и очень полезный завтрак для хорошего настроения",
                new String[]{"Яйца,грейпфрут,помидоры,мука пшеничная, молоко , лук,соль,черный перец,оливковое масло,укроп"},
                151,33,4.7,277,250,150));
        menu3.add(new DishMenu("Яичница","Питательный и очень полезный завтрак для хорошего настроения",
                new String[]{"Яйца,,помидоры,мука пшеничная, молоко , лук,соль,черный перец,оливковое масло,укроп"},
                151,33,4.7,277,250,150));
    }
    private class RestaurantsListAdapter extends ArrayAdapter<Restaurants>{

        public RestaurantsListAdapter(Context context, ArrayList<Restaurants> listRestaurants) {
            super(context, R.layout.list_dish_item, listRestaurants);
            mContext = context;
        }


        @Override
        public View getView(int position, View convertView,  ViewGroup parent) {

            Restaurants restaurants = getItem(position);

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row = inflater.inflate(R.layout.list_dish_item, parent, false);

            ImageView DishPhoto = row.findViewById(R.id.dish_photo);
            TextView NameCafe = row.findViewById(R.id.dish_name_cafe);
            TextView OpeningHour = row.findViewById(R.id.dish_time);
            TextView DishLocation = row.findViewById(R.id.dish_location);

            if (restaurants != null) {
                DishPhoto.setImageResource(restaurants.getPhotoRest());
                NameCafe.setText(restaurants.getNameCafe());
                OpeningHour.setText(restaurants.getTimeHours());
                DishLocation.setText(restaurants.getAddress());

            }
            return row;
        }
    }
}


