package com.sukhesh.scoutingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Dashboard extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        TextView text = rootView.findViewById(R.id.dashboard_title);

        SharedPreferences qualsData = getContext().getSharedPreferences("quals", Context.MODE_PRIVATE);
        text.setText(qualsData.getString("Match", "Quals -1"));

        return rootView;
    }
}