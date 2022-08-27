package com.sukhesh.scoutingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Dashboard extends Fragment {

    private View rootView;
    Button btn;
    TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        btn = (Button) rootView.findViewById(R.id.button);
        text = (TextView) rootView.findViewById(R.id.dashboard_title);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("Hello");
            }
        });

        return rootView;
    }
}