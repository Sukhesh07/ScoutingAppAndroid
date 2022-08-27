package com.sukhesh.scoutingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class Home extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView1 = inflater.inflate(R.layout.fragment_dashboard, container, false);
        
        TextView title = rootView1.findViewById(R.id.dashboard_title);
        TextView teamNum = rootView1.findViewById(R.id.dashboard_teamNum);

        /*
         * Set up the RecyclerView (essentially a list of items)
         */
        // access to this view (see fragment_home.xml)
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        // access to the view's recycler view object
        RecyclerView recyclerView = rootView.findViewById(R.id.rvQuals);
        // set the layout for the recycler
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        /*
         * Collect each match name from resources, and add to matchNames ArrayListHardcoding each Qual Match
         */
        ArrayList<String> matchNames = new ArrayList<>();
        // collect the matches from strings.xml
        String[] rawStringValues = getResources().getStringArray(R.array.Match_List);
        // String array --> more usable ArrayList
        Collections.addAll(matchNames, rawStringValues);

        /*
         * Set up how the RecyclerView is interacted with in the GUI
         */
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), matchNames);
        // use our adaptor, see RecycleViewAdapter, to interact with the RecycleView
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //If one of the quals are clicked
        adapter.setClickListener(() -> {
            title.setText("Hellow");
            teamNum.setText("sup");
        });

        return rootView;
    }
}
