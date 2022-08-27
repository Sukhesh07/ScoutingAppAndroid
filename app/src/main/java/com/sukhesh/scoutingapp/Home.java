package com.sukhesh.scoutingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class Home extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View dashboardView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        
        TextView title = dashboardView.findViewById(R.id.dashboard_title);
        TextView teamNum = dashboardView.findViewById(R.id.dashboard_teamNum);

        /*
         * Set up the RecyclerView (essentially a list of items) for the list of quals
         */
        // access to this view (see fragment_home.xml)
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);
        // access to the view's recycler view object
        RecyclerView qualsList = homeView.findViewById(R.id.rvQuals);
        // set the layout for the recycler
        qualsList.setLayoutManager(new LinearLayoutManager(getActivity()));

        /*
         * Instantiate Match list from quals.xml using Matches object
         */
        // collect the matches from strings.xml
        String[] rawStringValues = getResources().getStringArray(R.array.quals);
        Matches matches = new Matches(rawStringValues);

        /*
         * Set up what happens when a Qual is pressed
         */
        QualsRecyclerViewAdapter qualsAdapter = new QualsRecyclerViewAdapter(getActivity(), matches.listOfMatchTypeAndNumber());
        // use our adaptor, see QualsRecyclerViewAdapter, to interact with the Quals RecycleView
        qualsList.setAdapter(qualsAdapter);
        qualsList.setItemAnimator(new DefaultItemAnimator());

        qualsAdapter.setClickListener(position -> {
            String matchName = qualsAdapter.getItem(position);
            matches.storeMatchByMatchName(matchName, getContext().getSharedPreferences("quals", Context.MODE_PRIVATE));

            Toast.makeText(
                    getActivity(), // what to make the text for
                    "The matchName is " + matchName, // the text
                    Toast.LENGTH_SHORT // duration
            ).show(); // show it after creating it
        });
        return homeView;
    }
}
