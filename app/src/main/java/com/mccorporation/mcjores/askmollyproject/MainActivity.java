package com.mccorporation.mcjores.askmollyproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
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


import com.squareup.picasso.Picasso;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;
import com.vk.sdk.api.model.VKUsersArray;

import org.json.JSONException;
import org.json.JSONObject;

import static android.widget.Toast.LENGTH_SHORT;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DishPresenter {

    private final String NAME = "name";
    private final String CITY = "city";
    private final String PHONE = "phone";

    public static final String APP_PREFERENCE_USER_DATA = "User data";

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private boolean mtoken = false;

    private ImageView tool_hamburger;


    private String mAge;
    private String mFirstName;
    private String mLastName;
    private String mCity;
    private String mPhone;

    private TextView mDrawerName;
    private ImageView mDrawerPhoto;
    private View mHeader;

    private RestaurantFragment restaurantFragment;
    private PreferencesFragment preferencesFragment;
    private DishListFragment dishListFragment;
    private String mId;
    private String mPhoto;

    static {

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(
                APP_PREFERENCE_USER_DATA, Context.MODE_PRIVATE);

        if (sharedPreferences.getString("Name", null) == null) {
            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
            startActivityForResult(intent, 1);
        }

//        if (!mtoken) {
//            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
//            startActivityForResult(intent,1);
//        }

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

        restaurantFragment = new RestaurantFragment();
        preferencesFragment = new PreferencesFragment();
        dishListFragment = new DishListFragment();

        tool_hamburger = findViewById(R.id.tool_hamburger);

        mDrawerName = mHeader.findViewById(R.id.nav_name);
        mDrawerPhoto = mHeader.findViewById(R.id.nav_photo);
        if (sharedPreferences.getString("Name",null)!=null) {

            mDrawerName.setText(sharedPreferences.getString("Name", "Name"));
            Picasso.with(this)
                    .load(sharedPreferences.getString("UserPhoto", null))
                    .transform(new CircleTransform())
                    .placeholder(R.drawable.cat)
                    .into(mDrawerPhoto);

        }


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
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_main, preferencesFragment).commit();
//                    Toast.makeText(MainActivity.this, "Open", LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_restaurant:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_main, restaurantFragment).commit();
//                    getSupportFragmentManager().beginTransaction().replace(R.id.container_main, new RestaurantMenuFragment()).commit();
                    return true;

//                case R.id.navigation_notifications:
                case R.id.navigation_dishes:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_main, dishListFragment).commit();
//                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (data == null) return;

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Добро пожаловать", LENGTH_SHORT).show();
                getFullNameVK();
                SharedPreferences sharedPreferences = getSharedPreferences(
                        APP_PREFERENCE_USER_DATA, Context.MODE_PRIVATE);


                    mDrawerName.setText(mFirstName);
                    Picasso.with(this)
                            .load(mPhoto)
                            .transform(new CircleTransform())
                            .placeholder(R.drawable.cat)
                            .into(mDrawerPhoto);

            } else Toast.makeText(getApplicationContext(), "Вход не выполнен", LENGTH_SHORT).show();
        }
        //Если в профиле нажали выход то закрываем приложение
        if (requestCode == 2) {
            if (resultCode == RESULT_CANCELED) {
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getFullNameVK() {
        final VKRequest request = new VKRequest("account.getProfileInfo");
        final VKRequest requestGetPhoto = VKApi.users().get(VKParameters.from(VKApiConst.FIELDS, "photo_200", VKApiConst.USER_IDS, mId));


        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                String jsonObj = response.json.toString();
                parseJSON_VK_profile(jsonObj);
            }
        });

        requestGetPhoto.executeAfterRequest(request, new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                VKApiUser user = ((VKList<VKApiUser>) response.parsedModel).get(0);
                mPhoto = user.photo_200;
//                for (VKApiUserFull userFull : user)
                Log.i("USer photo url", "URL = " + user.photo_100);


                Picasso.with(getApplicationContext())
                        .load(mPhoto)
                        .transform(new CircleTransform())
                        .placeholder(R.drawable.cat)
                        .into(mDrawerPhoto);

                SharedPreferences sharedPreferences = getSharedPreferences(
                        APP_PREFERENCE_USER_DATA, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("UserPhoto", mPhoto);
//                editor.putString("Photo",photoURI);
                editor.apply();
            }
        });

        //схранение в sharedpref
        SharedPreferences sharedPreferences = getSharedPreferences(
                APP_PREFERENCE_USER_DATA, Context.MODE_PRIVATE);
        if (sharedPreferences.getString("Name",null)!=null) {

            mDrawerName.setText(sharedPreferences.getString("Name", "Name"));
            Picasso.with(this)
                    .load(sharedPreferences.getString("UserPhoto", null))
                    .transform(new CircleTransform())
                    .placeholder(R.drawable.cat)
                    .into(mDrawerPhoto);

        }
    }

    private void parseJSON_VK_profile(String jsonObj) {
        Log.i("MainFragmetn", "request = " + jsonObj);
        try {

            if (jsonObj != null) {
                JSONObject request = new JSONObject(jsonObj);
                JSONObject response = request.getJSONObject("response");
                Log.i("MainFragmetn", String.valueOf(response));
//                mId = response.getString("id");
                mFirstName = response.getString("first_name");
                mLastName = response.getString("last_name");
                mAge = response.getString("bdate");
                mPhone = response.getString("phone");
                JSONObject city = response.getJSONObject("city");
                mCity = city.getString("title");
                Log.i("MainFragmetn", "name " + mFirstName + " " + mLastName + " age " + mAge + " city " + mCity);
                mDrawerName.setText(mFirstName);

                SharedPreferences sharedPreferences = getSharedPreferences(
                        APP_PREFERENCE_USER_DATA, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("FullName", mFirstName + " " + mLastName);
                editor.putString("Name", mFirstName);
                editor.putString("Phone", mPhone);
                editor.apply();

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
            intent.putExtra(NAME, mFirstName + " " + mLastName);
            intent.putExtra(CITY, mCity);
//            intent.putExtra(PHONE, mPhone);
            startActivityForResult(intent, 2);
        } else if (id == R.id.nav_subscription) {

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

    @Override
    public void loadDish(DishMenu menu) {
//        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.container_main, new DishInfoFragment()).commit();
    }
}
