package com.sukhesh.scoutingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set the content view to the layout file activity_main
        setContentView(R.layout.activity_main);

        //Set variable bottom nav view to the design in activity_main
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Call fragment manager which will start replacing the frame layout of the design based on what the user clicked
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new Home()).commit();
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Deprecated method, On icon selected listener
        bottomNavigationView.setOnItemSelectedListener(item -> {

            Fragment fragment = null;
            //If the case of a certain icon press is met, switch the view to the other view
            switch (item.getItemId()) {

                case R.id.home:
                    fragment = new Home();
                    break;

                case R.id.dashboard:
                    fragment = new Dashboard();
                    break;

                case R.id.history:
                    fragment = new History();
                    break;

                case R.id.settings:
                    fragment = new Settings();
                    break;

            }
            assert fragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();

            return true;
        });
    }
}