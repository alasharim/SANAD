package com.example.majidalashari.sanad;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    double longitude = 0;
    double latitude = 0;
    String longitudeString = null;
    String latitudeString = null;

    boolean bundleCheck = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Bundle bundle = getArguments();
        if (bundle != null){
            longitudeString = bundle.getString("Longitude");
            latitudeString = bundle.getString("Latitude");
            Toast.makeText(getContext(),"Lat: " + latitudeString + ", Lon: " + longitudeString,Toast.LENGTH_LONG).show();
        }



//        if (!bundleCheck){
//            requestGPSFragment(longitude,latitude);
//        }else {


            //getting GPS coords from "SettingsFragment/GPSPopup"
//            Bundle extras = get
//            Bundle bundle = getArguments();
//        if (bundle == null){
//            Toast.makeText(getActivity(),"bundle is NULL",Toast.LENGTH_LONG).show();
//        }else{
//            Toast.makeText(getActivity(),"bundle is NOT NULL",Toast.LENGTH_LONG).show();
//
//            longitudeString = bundle.getString("Longitude");
//            latitudeString = bundle.getString("Latitude");
//
//            longitude = Double.parseDouble(longitudeString);
//            latitude = Double.parseDouble(latitudeString);
//        }
//        ////////////////////////////////////////////////////
//        bundleCheck = true;
        // Inflate the layout for this fragment
        return view;
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.

                if (grantResults.length > 0

                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    GPSTracker gps = new GPSTracker(getActivity(), (FragmentActivity) getActivity());

                    // Check if GPS enabled

                    if (gps.canGetLocation()) {

                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();

                        // \n is for new line

                        Toast.makeText(getActivity().getApplicationContext(),
                                "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    } else {
                        // Can't get location.

                        // GPS or network is not enabled.

                        // Ask user to enable GPS/network in settings.

                        gps.showSettingsAlert();
                    }

                } else {

                    // permission denied, boo! Disable the

                    // functionality that depends on this permission.
                    Toast.makeText(getActivity(), "You need to grant permission", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestGPSFragment(double longitude, double latitude){
        if (checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED

                && checkSelfPermission(getActivity(),Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


        } else {
            Toast.makeText(getActivity(), "You need have granted permission", Toast.LENGTH_SHORT).show();
            GPSTracker gps = new GPSTracker(getActivity(), (FragmentActivity) getActivity());

            // Check if GPS enabled
            if (gps.canGetLocation()) {

                latitude = gps.getLatitude();
                longitude = gps.getLongitude();

                // \n is for new line

                Toast.makeText(getActivity().getApplicationContext(),
                        "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            } else {

                // Can't get location.

                // GPS or network is not enabled.

                // Ask user to enable GPS/network in settings.

                gps.showSettingsAlert();
            }

            this.longitude = longitude;
            this.latitude = latitude;
        }
    }

}
