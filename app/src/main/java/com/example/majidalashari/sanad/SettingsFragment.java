package com.example.majidalashari.sanad;


//import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    // FragmentActivity getActivity() = getActivity();
    double latitude = 0;//21.6001;
    double longitude = 0;//39.136;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_settings, container, false);
        Button gpsBtn = (Button) v.findViewById(R.id.gpsBtn);

        gpsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showGPSDialog(v);

            }
        });
        return v;
    }


    //methods:


    public void showGPSDialog(View v){
        FragmentManager manager = getActivity().getSupportFragmentManager();
        GPSPopup gpsPopup = new GPSPopup();
        gpsPopup.show(manager,"GPSPopup");
        //this is used to get dialog from within a fragment
    }



}
