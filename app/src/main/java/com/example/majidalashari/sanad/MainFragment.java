package com.example.majidalashari.sanad;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    AppController appController = AppController.getInstance();
    ListView myPrayerList;
    MainFragment mContext;
    String[] prayerNamez = new String[5];
    String[] prayerTimez = new String[5];



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        myPrayerList = (ListView) view.findViewById(R.id.myPrayerList);
//        mContext = this;
        setMyPrayerList(appController.latitude,appController.longitude,prayerNamez,prayerTimez);


        return view;
    }


    public void setMyPrayerList(double latitude, double longitude,String[] prayerNamez, String[] prayerTimez){
//        String[] prayerNamez;
//        String[] prayerTimez;

        double timezone = (Calendar.getInstance().getTimeZone()
                .getOffset(Calendar.getInstance().getTimeInMillis()))
                / (1000 * 60 * 60);
        PrayTime prayers = new PrayTime();



        prayers.setTimeFormat(appController.timeFormat);
        prayers.setCalcMethod(appController.calculationMethod);
        prayers.setAsrJuristic(appController.juristicMethod);
        prayers.setAdjustHighLats(appController.adjustMethodForHigherLat);



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
        ItemAdapter itemAdapter = new ItemAdapter(getActivity(),prayerNamez,prayerTimez);
        myPrayerList.setAdapter(itemAdapter);

    };




}
