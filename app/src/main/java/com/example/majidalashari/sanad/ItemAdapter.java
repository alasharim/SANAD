package com.example.majidalashari.sanad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by MajidAlashari on 10/15/2017.
 */

class ItemAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private String[] prayerNamez;
    private String[] prayerTimez;

    ItemAdapter(Context c, String[] N, String[] T){
        prayerNamez = N;
        prayerTimez = T;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

//    ItemAdapter(View.OnClickListener c, String[] N, String[] T) {
//
//        prayerNamez = N;
//        prayerTimez = T;             /*vvvvvvvvvvvvvvvv*/
//        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                                     /*^^^^^^^^^^^^^^^^*/
//    }

    @Override
    public int getCount() {
        return prayerNamez.length;
    }

    @Override
    public Object getItem(int i) {
        return prayerNamez[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View View, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.my_prayer_detail,null);
        TextView prayerNameTextView = v.findViewById(R.id.prayerNameTextView);
        TextView prayerTimeTextView = v.findViewById(R.id.prayerTimeTextView);

        String name = prayerNamez[i];
        String time = prayerTimez[i];

        prayerNameTextView.setText(name);
        prayerTimeTextView.setText(time);

        return v;
    }
}
