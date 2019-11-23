package com.mccorporation.mcjores.askmollyproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RestaurantFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RestaurantsListFragment DishList;
    private StopProductFragment StopProductFragment;
    private GoogleMapFragment googleMapFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.restaurant_fragment,container,false);

        tabLayout = (TabLayout) view.findViewById(R.id.restaurant_tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.restaurant_view_pager);
        DishList = new RestaurantsListFragment();
        StopProductFragment = new StopProductFragment();
        googleMapFragment = new GoogleMapFragment();
        setUpTabLayout();


        return view;
    }

    private void setUpTabLayout() {
        RestaurantFragment.ViewPagerAdapter adapter = new RestaurantFragment.ViewPagerAdapter(getChildFragmentManager());
        adapter.add(DishList, "СПИСОК");
        adapter.add( StopProductFragment, "КАРТА");
//
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

//        void add(GoogleMapFragment fragment, String title) {
//            fragmentList.add(fragment);
//            titleList.add(title);
//        }
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
