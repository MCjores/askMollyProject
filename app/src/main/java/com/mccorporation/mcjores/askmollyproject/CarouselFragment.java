package com.mccorporation.mcjores.askmollyproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mccorporation.mcjores.askmollyproject.Dish.DishInfoFragment;
import com.mccorporation.mcjores.askmollyproject.Dish.DishMenu;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class CarouselFragment extends Fragment {
    private ImageView dishPhoto;
    private DishMenu menu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.carousel_photo_fragment, container, false);
        dishPhoto = view.findViewById(R.id.carousel_photo);

        Picasso.with(inflater.getContext()).load(menu.getDishPhoto())
                .transform(new RoundedCornersTransformation(50,0))
                .into(dishPhoto);
        dishPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DishInfoFragment dishInfoFragment = new DishInfoFragment();
                dishInfoFragment.setMenu(menu);

                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container_main, dishInfoFragment).commit();
            }
        });
        return view;

    }

    public void setDishInfo(DishMenu menu) {
        this.menu = menu;

    }

}
