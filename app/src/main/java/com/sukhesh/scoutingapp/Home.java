package com.sukhesh.scoutingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class Home extends Fragment {

    MyRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Set up the RecyclerView
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rvQuals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Hardcoding each Qual Match
        ArrayList<String> animalNames = new ArrayList<>();
        String[] arr = getResources().getStringArray(R.array.Match_List);
        Collections.addAll(animalNames, arr);

        adapter = new MyRecyclerViewAdapter(getActivity(), animalNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //If one of the quals are clicked
        adapter.setClickListener(position -> Toast.makeText(getActivity(), "You clicked " + adapter.getItem(position) + " on row number " + (position + 1), Toast.LENGTH_SHORT).show());
        return rootView;
    }
}
