package com.example.majidalashari.sanad;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    Toolbar toolbar = null;


    ListView myPrayerList;
    Context mContext;
//    double latitude = 0;//21.6001;
//    double longitude = 0;//39.136;
    String[] prayerNamez = new String[5];
    String[] prayerTimez = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SANAD//
        myPrayerList = (ListView) findViewById(R.id.myPrayerList);
        mContext = this;
//        setMyPrayerList(latitude,longitude,prayerNamez,prayerTimez);
        /////////


        //GPS_BUTTON//
//        Button gpsBtn = (Button) findViewById(R.id.gpsBtn);
//        gpsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (ContextCompat.checkSelfPermission(mContext,
//                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//
//                        && ActivityCompat.checkSelfPermission(mContext,
//                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(MainActivity.this,
//                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//
//                } else {
//                    Toast.makeText(mContext, "You need have granted permission", Toast.LENGTH_SHORT).show();
//                    GPSTracker gps = new GPSTracker(mContext, MainActivity.this);
//
//                    // Check if GPS enabled
//
//                    if (gps.canGetLocation()) {
//
//                        latitude = gps.getLatitude();
//                        longitude = gps.getLongitude();
//
//                        // \n is for new line
//
//                        Toast.makeText(getApplicationContext(),
//                                "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
//                    } else {
//
//                        // Can't get location.
//
//                        // GPS or network is not enabled.
//
//                        // Ask user to enable GPS/network in settings.
//
//                        gps.showSettingsAlert();
//                    }
//                }
//                //setMyPrayerList(latitude,longitude,prayerNamez,prayerTimez);
//
//            }
//        });
        //////////////



        //Initialize MainFragment//
        MainFragment fragment = new MainFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
        ///////////////////////////



        //Navigation Drawer//
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //setMyPrayerList(latitude,longitude,prayerNamez,prayerTimez);
        /////////////////////
    }


    public void setMyPrayerList(double latitude, double longitude,String[] prayerNamez, String[] prayerTimez){
//        String[] prayerNamez;
//        String[] prayerTimez;

        double timezone = (Calendar.getInstance().getTimeZone()
                .getOffset(Calendar.getInstance().getTimeInMillis()))
                / (1000 * 60 * 60);
        PrayTime prayers = new PrayTime();

        prayers.setTimeFormat(prayers.Time12);
        prayers.setCalcMethod(prayers.Makkah);
        prayers.setAsrJuristic(prayers.Shafii);
        prayers.setAdjustHighLats(prayers.AngleBased);
        int[] offsets = { 0, 0, 0, 0, 0, 0, 0 }; // {Fajr,Sunrise,Dhuhr,Asr,Sunset,Maghrib,Isha}
        prayers.tune(offsets);

        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);

        ArrayList prayerTimes = prayers.getPrayerTimes(cal, latitude,
                longitude, timezone);
        ArrayList prayerNames = prayers.getTimeNames();

                /* **********ListView********** */
//        prayerNamez = new String[5];
//        prayerTimez = new String[5];

        for (int i = 0,j = 0;(i+j) < prayerNames.size();i++){
            if ((i + j) == 1 ||  (i + j) == 4)
                j++;
            prayerNamez[i] = (String) prayerNames.get(i+j);
            prayerTimez[i] = (String) prayerTimes.get(i+j);
        }
        ///ADAPTER
        ItemAdapter itemAdapter = new ItemAdapter(mContext,prayerNamez,prayerTimez);
        myPrayerList.setAdapter(itemAdapter);

    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        if (id == R.id.nav_camera) {
            //Initialize MainFragment
            MainFragment fragment = new MainFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
            //
        } else if (id == R.id.nav_gallery) {
            //Initialize MainFragment
            GalleryFragment fragment = new GalleryFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
            //
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
