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
         * Collect each match name from resources, and add to matchNames ArrayListHardcoding each Qual Match
         */
        ArrayList<String> matchNames = new ArrayList<>();
        // collect the matches from strings.xml
        String[] rawStringValues = getResources().getStringArray(R.array.Match_List);
        // String array --> more usable ArrayList
        Collections.addAll(matchNames, rawStringValues);

        /*
         * Set up what happens when a Qual is pressed
         */
        QualsRecyclerViewAdapter qualsAdapter = new QualsRecyclerViewAdapter(getActivity(), matchNames);
        // use our adaptor, see QualsRecyclerViewAdapter, to interact with the Quals RecycleView
        qualsList.setAdapter(qualsAdapter);
        qualsList.setItemAnimator(new DefaultItemAnimator());

        qualsAdapter.setClickListener(position -> {
            // this needs a considerable amount of improvement... BUT IT FREAKING WORKS HELL YEAH
            SharedPreferences thisQualsMatch = getContext().getSharedPreferences("quals", Context.MODE_PRIVATE);
            SharedPreferences.Editor thisQualsMatchEditor = thisQualsMatch.edit();
            thisQualsMatchEditor.putString("Match", qualsAdapter.getItem(position));
            thisQualsMatchEditor.commit();

            Toast.makeText(
                    getActivity(), // what to make the text for
                    "The value of Match in SharedPreferences is " + thisQualsMatch.getString("Match", "Quals -1"), // the text
                    Toast.LENGTH_SHORT // duration
            ).show(); // show it after creating it
        });
        return homeView;
    }
}
