package com.mccorporation.mcjores.askmollyproject.Restaurant;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mccorporation.mcjores.askmollyproject.CarouselFragment;
import com.mccorporation.mcjores.askmollyproject.Dish.DishMenu;
import com.mccorporation.mcjores.askmollyproject.R;
import com.synnapps.carouselview.CarouselView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mccorporation.mcjores.askmollyproject.StopProduct.StopProductFragment.APP_PREFERENCE_STOP_PRODUCT;

public class RestaurantsListFragment extends Fragment {
    private Context mContext;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    ArrayList<Restaurants> restaurantsArrayList;
    ArrayList<DishMenu> menu1;
    ArrayList<DishMenu> menu2;
    ArrayList<DishMenu> menu3;
    RestaurantInfo restaurantInfo;
    private CarouselView carouselView;
    private int[] PhotoDishForCarusel;

    private final int CAROUSEL_PAGE_COUNT =3;
    private CarouselFragment carouselDish1;
    private CarouselFragment carouselDish2;
    private CarouselFragment carouselDish3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_fragment, container, false);
        ListView listView = view.findViewById(R.id.dish_list);
//        carouselView = view.findViewById(R.id.carouselView);

        tabLayout = (TabLayout) view.findViewById(R.id.carousel_tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.carousel_view_pager);

        restaurantInfo = new RestaurantInfo();
        carouselDish1 = new CarouselFragment();
        carouselDish2 = new CarouselFragment();
        carouselDish3 = new CarouselFragment();
//        carouselView.setPageCount(CAROUSEL_PAGE_COUNT);

        final RestaurantsListAdapter restaurantsListAdapter = new RestaurantsListAdapter(inflater.getContext(), setDish());
        listView.setAdapter(restaurantsListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurants cafe = restaurantsArrayList.get(position);
                restaurantInfo.setRestaurantInfo(cafe.getNameCafe(), cafe.getPhotoRest(),
                        cafe.getRestaurantDescription(), cafe.getAddress(), cafe.getAverageCheck(), cafe.getMenus());
                Log.i("RestList", "onCreateView after");

                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container_main, restaurantInfo).commit();

            }
        });


        //установка картинок в карусель блюд
        setPhotoDishForCarusel();
        setUpTabLayout();
//        carouselView.setImageListener(new ImageListener() {
//            @Override
//            public void setImageForPosition(int position, ImageView imageView) {
//                imageView.setImageResource(PhotoDishForCarusel[position]);
//            }
//        });
//        carouselView.setBackgroundResource(R.drawable.carousel_shape);
//        carouselView.setImageClickListener(new ImageClickListener() {
//            @Override
//            public void onClick(int position) {
//                Toast.makeText(getContext(),"clic item "+ position,Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;
    }
        //PAGER VIEW FOR CAROUSEL DISH. use 3 fragments for dish photo. будет проще обработать клики.
    private void setUpTabLayout() {
        ViewPagerAdapter adapter = new ViewPagerAdapter (getChildFragmentManager());
        adapter.add(carouselDish1);
        adapter.add(carouselDish2);
        adapter.add(carouselDish3);
        Log.i("ViewPager = " ," " + viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList = new ArrayList<>();


        private ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        void add(Fragment fragment) {
            fragmentList.add(fragment);

        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }



        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    private void setPhotoDishForCarusel(){
        final Random random = new Random();
        int size = setDish().size() -1;

        int rand1 = random.nextInt(size);
        int rand2 = random.nextInt(size);
        int rand3 = random.nextInt(size);

        int sizeMenu1 = setDish().get(rand1).getMenus().size()-1;
        int sizeMenu2 = setDish().get(rand2).getMenus().size()-1;
        int sizeMenu3 = setDish().get(rand3).getMenus().size()-1;
//        setDish().get(rand1).getMenus().get(  );
        Log.i("Random  ","rand = " + rand1 + " " + rand2 + " " + rand3);
        Log.i("Random  ","sizeMenu = " + sizeMenu1 + " " + sizeMenu2 + " " + sizeMenu3);

        DishMenu dishMenu1 = setDish().get(rand1).getMenus().get(0);
        DishMenu dishMenu2 = setDish().get(rand2).getMenus().get(0);
        DishMenu dishMenu3 = setDish().get(rand3).getMenus().get(0);

        carouselDish1.setDishInfo(dishMenu1);
        carouselDish2.setDishInfo(dishMenu2);
        carouselDish3.setDishInfo(dishMenu3);
    }

    //ЗАГЛУШКА ДЛЯ БЭКА(создание ресторанов)
    protected ArrayList<Restaurants> setDish() {
        setMenus();
        restaurantsArrayList = new ArrayList<>();

        restaurantsArrayList.add(new Restaurants("Якитория", "Лубянка", 5.0, R.drawable.png_mohito,
                "Уютная атмосфера, разнообразие японской кужни", 1700, menu2));
        restaurantsArrayList.add(new Restaurants("Good Day Cafe", "Лубянка", 4.1, R.drawable.png_salad,
                "Уютная атмосфера, разнообразие кухни и мноооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооооогого другого", 1500, menu1));
        restaurantsArrayList.add(new Restaurants("Loving Soup", "ул. Московская 35", 2.1, R.drawable.png_mohito,
                "Уютная атмосфера, разнообразие японской завтраков", 1000, menu3));
        Log.i("setDish ", "restArrayList =" + restaurantsArrayList);
        return sortRestaurants(restaurantsArrayList);
    }

    //фильтрация ресторанов по стоп продуктам
    protected ArrayList<Restaurants> sortRestaurants(ArrayList<Restaurants> restaurants) {
        ArrayList<Restaurants> sortList = restaurants;
        ArrayList<Restaurants> listForRemoveCafe = new ArrayList<>();
        ArrayList<DishMenu> listForRemoveMenu = new ArrayList<>();
        ArrayList<String> stopProductList = new ArrayList<>();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(
                APP_PREFERENCE_STOP_PRODUCT, Context.MODE_PRIVATE);

        int size = sharedPreferences.getInt("Status_size", 0);
        if (size != 0) {
            for (int i = 0; i < size; i++)
                stopProductList.add(sharedPreferences.getString("savelist_" + i, null));

            for (int r = 0; r < restaurants.size(); r++) {
                Restaurants restaurant = sortList.get(r);

                if (!restaurant.getMenus().isEmpty()) {
                    int restaurantSize = restaurant.getMenus().size();
                    //количесвто блюд в меню
                    for (int i = 0; i < restaurantSize; i++) {
                        String[] composition = restaurant.getMenus().get(i).getComposition();
                        for (int j = 0; j < composition.length; j++) {
                            for (int k = 0; k < stopProductList.size(); k++) {

                                if (composition[j].contains(stopProductList.get(k))) {//contains(stopProductList.get(k))) {  //.equals(stopProductList.get(k))) {   //если в составе есть стоп продукт, то удаляем блюдо из меню
                                    listForRemoveMenu.add(restaurant.getMenus().get(i));
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!listForRemoveMenu.isEmpty())
            for (int r = 0; r < restaurants.size(); r++) {
                sortList.get(r).getMenus().removeAll(listForRemoveMenu);

                if (sortList.get(r).getMenus().isEmpty()) {
                    listForRemoveCafe.add(sortList.get(r));
                    Log.i("sor", "remove ");
                }
            }
        if (!listForRemoveCafe.isEmpty())
            sortList.removeAll(listForRemoveCafe);


        return sortList;
    }

    //ЗАГЛУШКА ДЛЯ БЭКА(добавление меню)
    private void setMenus() {
        menu1 = new ArrayList<DishMenu>();
        menu2 = new ArrayList<>();
        ;
        menu3 = new ArrayList<>();
        //в составе массив из 1 элемента, нужно ли будет делить на множество? вопрос открытый т.к. мб лучьше держать все в виде 1 строкию.
        menu1.add(new DishMenu("Яичница с Авокадо", "Питательный и очень полезный завтрак для хорошего настроения", R.drawable.png_salad,
                new String[]{"Яйца,авокадо,помидоры,мука пшеничная, молоко , лук,соль,черный перец,оливковое масло,укроп"},
                151, 33, 4.7, 277, 250, 350));
        menu1.add(new DishMenu("Ананас и анис", "Питательный и очень полезный завтрак из ананаса и аниса", R.drawable.png_salad,
                new String[]{"Ананас, Анис"},
                151, 33, 4.7, 277, 250, 1500));
        menu1.add(new DishMenu("Яичница с брусникой", "Питательный и очень полезный завтрак с брусникой", R.drawable.png_mohito,
                new String[]{"Яйца, Брусника"},
                151, 33, 4.7, 277, 250, 154));

        menu2.add(new DishMenu("Ролл с грибами", "Питательный и очень полезный ролл с грибами", R.drawable.png_salad,
                new String[]{"Рис, Грибы, Укроп"},
                151, 33, 4.7, 277, 250, 150));
        menu2.add(new DishMenu("Ролл с крабом", "Питательный и очень вкусный ролл с крабом. Подается с арахисовым соусом", R.drawable.png_salad,
                new String[]{"Рис,Краб, Арахис"},
                151, 33, 4.7, 277, 250, 250));
        menu2.add(new DishMenu("Ролл *Драгон*", "Питательный и очень вкусный ролл с запеченым лососем", R.drawable.png_mohito,
                new String[]{"Рис,Лосось"},
                151, 33, 4.7, 277, 250, 550));

        menu3.add(new DishMenu("Яичница с Вишней", "Питательный и очень полезный завтрак для хорошего настроения", R.drawable.png_salad,
                new String[]{"Яйца,вишня,помидоры,мука пшеничная, молоко , лук,соль,черный перец,оливковое масло,укроп"},
                151, 33, 4.7, 277, 250, 350));
        menu3.add(new DishMenu("Яичница с грейпфрутом", "Питательный и очень полезный завтрак для хорошего настроения", R.drawable.png_mohito,
                new String[]{"Яйца,грейпфрут,помидоры,мука пшеничная, молоко , лук,соль,черный перец,оливковое масло,укроп"},
                151, 33, 4.7, 277, 250, 250));
        menu3.add(new DishMenu("Яичница с Яблоком", "Питательный и очень полезный завтрак для хорошего настроения", R.drawable.png_mohito,
                new String[]{"Яйца,яблоко,помидоры,мука пшеничная, молоко , лук,соль,черный перец,оливковое масло,укроп"},
                151, 33, 4.7, 277, 250, 250));
        menu3.add(new DishMenu("Яичница", "Питательный и очень полезный завтрак для хорошего настроения", R.drawable.png_salad,
                new String[]{"Яйца,помидоры,мука пшеничная, молоко , лук,соль,черный перец,оливковое масло,укроп"},
                151, 33, 4.7, 277, 250, 450));
    }

    private class RestaurantsListAdapter extends ArrayAdapter<Restaurants> {

        public RestaurantsListAdapter(Context context, ArrayList<Restaurants> listRestaurants) {
            super(context, R.layout.list_dish_item, listRestaurants);
            mContext = context;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.i("RestList", "Position " + position);
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


