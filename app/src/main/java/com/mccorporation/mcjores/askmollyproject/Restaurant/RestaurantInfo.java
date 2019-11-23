package com.mccorporation.mcjores.askmollyproject.Restaurant;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.mccorporation.mcjores.askmollyproject.Dish.DishMenu;
import com.mccorporation.mcjores.askmollyproject.R;

import java.util.ArrayList;
import java.util.List;

public class RestaurantInfo extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private int PhotoRestaurant;
    private String NameRestaurant;
    private String DescriptionRestaurant;
    private String AddressRestaurant;
    private int AverageCheckRestaurant;

    private RestaurantDescription restaurantDescription;
    private RestaurantMenuFragment restaurantMenuFragment;
    private ArrayList<DishMenu> dishMenus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.i("RestInfo","onCreateView before");
        View view = inflater.inflate(R.layout.restaurant_info,container,false);
        Log.i("RestInfo","onCreateView after");
        tabLayout = (TabLayout) view.findViewById(R.id.restaurant_info_tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.restaurant_info_view_pager);

        ImageView photo = view.findViewById(R.id.restaurant_info_photo);
        TextView nameCafe = view.findViewById(R.id.restaurant_info_name_cafe);
        photo.setImageResource(PhotoRestaurant);

        nameCafe.setText(NameRestaurant);

        restaurantDescription = new RestaurantDescription();
        restaurantMenuFragment = new RestaurantMenuFragment();
        restaurantMenuFragment.setMenu(dishMenus);
        setUpTabLayout();


        restaurantDescription.setParam(DescriptionRestaurant,"10:00 - 20:00",AddressRestaurant,AverageCheckRestaurant);

        return view;
    }


    public void setRestaurantInfo(String nameCafe, int PhotoCafe, String descriptionRestaurant,String addressRestaurant, int averageCheckRestaurant, ArrayList<DishMenu> menu){

        NameRestaurant = nameCafe;
        PhotoRestaurant = PhotoCafe;
        DescriptionRestaurant = descriptionRestaurant;
        AddressRestaurant = addressRestaurant;
        AverageCheckRestaurant = averageCheckRestaurant;
        dishMenus = menu;
    }
//    public void setMenuList(ArrayList<D>)


    private void setUpTabLayout() {
        RestaurantInfo.ViewPagerAdapter adapter = new RestaurantInfo.ViewPagerAdapter (getChildFragmentManager());
        adapter.add(restaurantDescription, "Описание");
        adapter.add(restaurantMenuFragment, "Меню");
        Log.i("ViewPager = " ," " + viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPageMargin((int) getResources().getDimension(R.dimen.view_pager_gap));
        viewPager.setPageMarginDrawable(R.color.colorGreen);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList = new ArrayList<>();
        private List<String> titleList = new ArrayList<>();

        private ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        void add(Fragment fragment, String title) {
            fragmentList.add(fragment);
            titleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
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

}
