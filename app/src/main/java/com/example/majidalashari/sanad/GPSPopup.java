package com.example.majidalashari.sanad;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;


public class GPSPopup extends DialogFragment implements View.OnClickListener {
    @Nullable

    AppController appController = AppController.getInstance();
    Button automaticBtn, manualBtn;
//    private double longitude = 1;
//    private double latitude = 1;

    private String longitudeString = "1";
    private String latitudeString = "1";



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gps_popup_dialog,null);
        automaticBtn = (Button) view.findViewById(R.id.automaticBtn);
        manualBtn = (Button) view.findViewById(R.id.manualBtn);

        //for switch "Line 29" to work "line 20-21" must be added
        assert automaticBtn != null;
        automaticBtn.setOnClickListener(this);
        assert manualBtn != null;
        manualBtn.setOnClickListener(this);

//        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            @Override
//            public void onBackStackChanged() {
//                if (getView()!=null){
//                    getView().setFocusableInTouchMode(true);
//                    getView().requestFocus();
//                }
//            }
//        });



        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.automaticBtn):
                requestGPSFragment();
                getDialog().dismiss();
                break;

            case (R.id.manualBtn):
//                Toast.makeText(getActivity(),"Manual Location",Toast.LENGTH_LONG).show();
//                showManualGPSFragment();
//                showDialog();
                startActivity(new Intent(getActivity(),ManualGPSFragment.class));
                getDialog().dismiss();
                break;

            default:
                Toast.makeText(getActivity(),"Default ERROR",Toast.LENGTH_LONG).show();
                break;

        }

    }

    public void showManualGPSFragment(){
//        FragmentManager manager = getActivity().getSupportFragmentManager();
//        ManualGPSFragment manualGPSFragment = new ManualGPSFragment();
//        manualGPSFragment.show(manager,"ManualGPSFragment");

//        startActivity(new Intent(GPSPopup.this,ManualGPSFragment.class));

        //this is used to get dialog from within a fragment
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
    public void requestGPSFragment(){
        double longitude = 0.0;
        double latitude = 0.0;
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

            appController.longitude = longitude;
            appController.latitude = latitude;
        }
    }


}
