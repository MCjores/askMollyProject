package com.mccorporation.mcjores.askmollyproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vk.sdk.VKSdk;

import static com.mccorporation.mcjores.askmollyproject.MainActivity.APP_PREFERENCE_USER_DATA;

public class ProfileFragment extends AppCompatActivity {

    private SharedPreferences sharedPreferences ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_fragment);

        sharedPreferences = getSharedPreferences(
                APP_PREFERENCE_USER_DATA, Context.MODE_PRIVATE);
        TextView name = (TextView) findViewById(R.id.profile_name);
        TextView phone = (TextView) findViewById(R.id.profile_phone);
        ImageView photo = (ImageView) findViewById(R.id.profile_photo);
        final Button exit = (Button) findViewById(R.id.btn_profile_exit);
//        Log.i("profileframent",sharedPreferences.getString("Name",null));
        if (sharedPreferences.getString("Name",null)!=null){
            name.setText(sharedPreferences.getString("FullName",""));
            phone.setText(sharedPreferences.getString("Phone",""));
            Picasso.with(this)
                    .load(sharedPreferences.getString("UserPhoto",null))
                    .transform(new CircleTransform())
                    .into(photo);

        }
//        name.setText(getIntent().getStringExtra("name"));
//        phone.setText(getIntent().getStringExtra("phone"));


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VKSdk.logout();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Name", null);
                editor.putString("Phone", null);
                editor.putString("UserPhoto",null);
                editor.apply();
                Intent result = new Intent();
                setResult(RESULT_CANCELED,result);
                finish();
            }
        });
    }
}
