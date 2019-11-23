package com.mccorporation.mcjores.askmollyproject.Dish;

import android.content.Context;
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
import android.widget.TextView;

import com.mccorporation.mcjores.askmollyproject.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DishInfoFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView dishName;
    private DishInfoDescriptionFragment descriptionFragmen;
    private DishInfoCompositionFragment compositionFragment;
    private DishInfoEnergyValueFragment energyValueFragment;

    private DishMenu menu;

    private DishPresenter dishPresenter;

    @Override
    public void onAttach(Context context) {
        dishPresenter = (DishPresenter) context;
//        dishPresenter = new DishPresenter();
        super.onAttach(context);
        Log.i("onAttach", "presenter = " + dishPresenter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dish_info, container, false);
        Log.i("onCreate", "presenter = " + dishPresenter);
        initUI(view);

        setUpTabLayout();

        descriptionFragmen.setMenuDescription(menu.getDescription());
        compositionFragment.setMenuComposition(menu.getComposition());
        energyValueFragment.setMenuEnergyValue(menu.getFat(), menu.getProteins(), menu.getCarbohydrates(), menu.getKcal(), menu.getWeight());
        dishName.setText(menu.getName());

        return view;
    }

    private void initUI(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.dish_info_tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.dish_info_view_pager);
        dishName = view.findViewById(R.id.dish_info_dish_name);

        descriptionFragmen = new DishInfoDescriptionFragment();
        compositionFragment = new DishInfoCompositionFragment();
        energyValueFragment = new DishInfoEnergyValueFragment();
    }

    private void setUpTabLayout() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.add(descriptionFragmen, "Описание");
        adapter.add(compositionFragment, "Состав");
        adapter.add(energyValueFragment, "Эн. ценность");

        viewPager.setAdapter(adapter);
        viewPager.setPageMargin((int) getResources().getDimension(R.dimen.view_pager_gap));
        viewPager.setPageMarginDrawable(R.color.colorAskMolly);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setMenu(DishMenu menu) {
        Log.i("setMenu", "presenter = " + dishPresenter);
        this.menu = menu;
//        compositionFragment;
//        dishPresenter.loadDish(menu);
    }

    ;


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


    public static class DishInfoDescriptionFragment extends Fragment implements DishPresenter {
        protected TextView tvDescription;
        private String menuDescription = "";

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.content_dish_info_description, container, false);
            tvDescription = view.findViewById(R.id.tv_dish_info_description);
            tvDescription.setText(menuDescription);
            return view;
        }

        @Override
        public void loadDish(DishMenu menu) {

            tvDescription.setText(menu.getDescription() + "P");
            Log.i("Description", menu.getDescription());
        }

        public void setMenuDescription(String menuDescription) {
            this.menuDescription = menuDescription;
        }
    }


    public static class DishInfoCompositionFragment extends Fragment implements DishPresenter {

        private TextView tvComposition;
        private String[] composition;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.content_dish_info_composition, container, false);
            tvComposition = view.findViewById(R.id.tv_dish_info_composition);


//            String mComposution = "";
//            StringBuffer buffer = new StringBuffer(mComposution);
//
//            for (int i = 0; i< composition.length; i++){
//                buffer.append( " " + composition[i] +"," );
//            }
            String result = Arrays.toString(composition)
                    .replace("[", "")
                    .replace("]", "");
            tvComposition.setText(result);
            return view;
        }

        @Override
        public void loadDish(DishMenu menu) {
            tvComposition.setText(menu.getComposition() + " p");
        }


        public void setMenuComposition(String[] composition) {

            this.composition = composition;
        }
    }


    public static class DishInfoEnergyValueFragment extends Fragment implements DishPresenter {
        private TextView tvFat;
        private TextView tvProteins;
        private TextView tvCarbons;
        private TextView tvCcal;
        private TextView tvWeight;

        private double fat;
        private double proteins;
        private double carbons;
        private double kcal;
        private double weight;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.content_dish_info_energy_value, container, false);

            tvFat = view.findViewById(R.id.tv_dish_info_composition_fat);
            tvProteins = view.findViewById(R.id.tv_dish_info_composition_proteins);
            tvCarbons = view.findViewById(R.id.tv_dish_info_composition_carbohudrates);
            tvCcal = view.findViewById(R.id.tv_dish_info_composition_ccal);
            tvWeight = view.findViewById(R.id.tv_dish_info_composition_weight);

            tvFat.setText(fat + " г");
            tvProteins.setText(proteins + " г");
            tvCarbons.setText(carbons + " г");
            tvCcal.setText(kcal + " г");
            tvWeight.setText(weight + " г");
            return view;
        }

        @Override
        public void loadDish(DishMenu menu) {
            tvFat.setText(menu.getFat() + " г");
            tvProteins.setText(menu.getProteins() + " г");
            tvCarbons.setText(menu.getCarbohydrates() + " г");
            tvCcal.setText(menu.getKcal() + " г");
            tvWeight.setText(menu.getWeight() + " г");
        }

//        public void setMenuEnergyValue(String fat, String proteins, String carbons, String kcal, String weight){
//            tvFat.setText( fat + " г");
//            tvProteins.setText(proteins + " г");
//            tvCarbons.setText(carbons+ " г");
//            tvCcal.setText(kcal + " г");
//            tvWeight.setText(weight + " г");
//        }

        public void setMenuEnergyValue(double fat, double proteins, double carbohydrates, double kcal, double weight) {
            this.fat = fat;
            this.proteins = proteins;
            this.carbons = carbohydrates;
            this.kcal = kcal;
            this.weight = weight;
        }
    }
}
