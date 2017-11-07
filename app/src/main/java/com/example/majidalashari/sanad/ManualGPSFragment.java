package com.example.majidalashari.sanad;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;


public class ManualGPSFragment extends FragmentActivity {

//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_manual_gps,null);
//
//        getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
//        PlaceAutocompleteFragment autocompleteFragment = new PlaceAutocompleteFragment();
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//                // TODO: Get info about the selected place.
//                Log.i("FOUND", "PLACE: " + place.getName());
//            }
//
//            @Override
//
//            public void onError(Status status) {
//                // TODO: Handle the error.
//                Log.i("ERROR", "An error occurred: " + status);
//            }
//        });
//
//
//
//        // Inflate the layout for this fragment
//        return view;
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_manual_gps);


        PlaceAutocompleteFragment autocompleteFragment = new PlaceAutocompleteFragment();
        getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();
        autocompleteFragment.setFilter(typeFilter);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("FOUND", "PLACE: " + place.getName());
            }

            @Override

            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("ERROR", "An error occurred: " + status);
            }
        });


    }





}
