package com.mccorporation.mcjores.askmollyproject.SignIn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mccorporation.mcjores.askmollyproject.R;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;




public class RegistrationActivity extends AppCompatActivity  {


    private String[] scope = new String[]{VKScope.EMAIL, VKScope.WALL, VKScope.PHOTOS};


    private ImageView imageVK;
    private ImageView imageGOOGLE;
    private ImageView imageFACEBOOK;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_registration);


//        imageVK = findViewById(R.id.reg_iv_vk);
//
//
//        imageVK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
    }







}



