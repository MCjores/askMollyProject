package com.mccorporation.mcjores.askmollyproject;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.widget.Toast.LENGTH_SHORT;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String NAME = "name";
    private final String CITY = "city";
    private final String PHONE = "phone";

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private boolean mtoken = false;

    private ImageView tool_hamburger;


    static {

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private String mAge;
    private String mFirstName  ;
    private String mLastName;
    private String mCity;
    private String mPhone;

    private TextView mDrawerName;
    private View mHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (!mtoken) {
            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
            startActivityForResult(intent,1);
        }

        Toolbar toolbar = findViewById(R.id.toolbar_main);

//        toolbar.setLogo(R.drawable.ic_ask_molly);

//        toolbar.setNavigationIcon(R.drawable.hamburger);
//        toolbar.getNavigationIcon();

        setSupportActionBar(toolbar);


        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mHeader = navigationView.getHeaderView(0);
        toggle.setDrawerIndicatorEnabled(false);
//        toggle.setHomeAsUpIndicator(R.drawable.hamburger);


        drawer.addDrawerListener(toggle);

        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        BottomNavigationView bottomNavigationView = findViewById(R.id.Bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        tool_hamburger = findViewById(R.id.tool_hamburger);
        mDrawerName = mHeader.findViewById(R.id.nav_name);
        tool_hamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.START);
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_preferences:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new RestourantFragment()).commit();
                    Toast.makeText(MainActivity.this, "Open", LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_restaurant:

                    return true;
//                case R.id.navigation_notifications:
//
//                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            if (data==null) return;

            if (requestCode ==1){
                if (resultCode == RESULT_OK){
                    Toast.makeText(getApplicationContext(),"Добро пожаловать", LENGTH_SHORT).show();
                    getFullNameVK();
                    mDrawerName.setText(mFirstName);
                } else Toast.makeText(getApplicationContext(),"Вход не выполнен", LENGTH_SHORT).show();
            }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void getFullNameVK(){
        final VKRequest request = new VKRequest("account.getProfileInfo");

        request.executeSyncWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                String jsonObj = response.json.toString();
                parseJSON_VK_profile(jsonObj);
            }
        });
    }

    private void parseJSON_VK_profile(String jsonObj)  {
        Log.i("MainFragmetn","request = " + jsonObj);
        try {

            if (jsonObj != null) {
                JSONObject request = new JSONObject(jsonObj);
                JSONObject response = request.getJSONObject("response");
                Log.i("MainFragmetn", String.valueOf(response));

                mFirstName = response.getString("first_name");
                mLastName = response.getString("last_name");
                mAge = response.getString("bdate");
                mPhone = response.getString("phone");
                JSONObject city = response.getJSONObject("city");
                mCity = city.getString("title");
                Log.i("MainFragmetn","name " + mFirstName + " " + mLastName + " age " + mAge + " city " + mCity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent = new Intent(this, ProfileFragment.class);
            intent.putExtra(NAME,mFirstName+ " " + mLastName);
            intent.putExtra(CITY, mCity);
            intent.putExtra(PHONE,mPhone);
            startActivity(intent);
        } else if (id == R.id.nav_subscription) {

        } else if (id == R.id.nav_app_info) {
        }
//        } else if (id == R.id.nav_tools) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
