package com.sukhesh.scoutingapp;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class RapidReactDashboard extends Fragment {

    //You have to make shared preferences for all of these inc variables, the checkboxes, the stopwatch and the seekbar
    int inc = 0;
    int inc2 = 0;
    int inc3 = 0;
    int inc4 = 0;
    int inc5 = 0;
    int inc6 = 0;
    int inc7 = 0;
    int inc8 = 0;

    String leavesTarmacString = "N";
    String playedDefenceString = "N";
    String humanScoredString = "N";

    Chronometer stopwatch;
    ImageButton btStart;
    ImageButton btStop;
    boolean isResume;
    Handler handler;
    long tMillisec = 0L;
    long tStart = 0L;
    long tBuff = 0L;
    long tUpdate = 0L;
    int sec;
    int millisec;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_rapid_react_dashboard, container, false);
        ScrollView scrollView = rootView.findViewById(R.id.scrollView);

        AnimationDrawable animationDrawable = (AnimationDrawable) scrollView.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();



        //Move this to FinishTab.java thats where the qr is being created
        TextView text = rootView.findViewById(R.id.title_dashboard);
        Match currentMatch = Match.MatchFromSharedPreferences(getContext().getSharedPreferences("quals", Context.MODE_PRIVATE));
        text.setText(currentMatch.matchName());

        //Part where I absolutely violated Dashboard
        //Zayn you are absolutely going to have fun renaming all of these variables since you get code ocd
        // i hate you sm :)))
        ArrayList<View> finiteintViews = new ArrayList<View>();
        rootView.findViewsWithText(finiteintViews, "FiniteInt", View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
        ArrayList<FiniteInt> finiteInts = FiniteInt.generateArrayListFromViews(finiteintViews);

        for(FiniteInt f: finiteInts) {
            f.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    f.value++;
                    f.tally.setText(Integer.toString(f.value));
                }
            });
            f.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    f.value--;
                    f.tally.setText(Integer.toString(f.value));
                }
            });
        }


        //All of these have to go into shared preferences
        CheckBox leavesTarmac = rootView.findViewById(R.id.leavesTarmac);
        CheckBox humanScored = rootView.findViewById(R.id.humanScored);
        CheckBox playedDefence = rootView.findViewById(R.id.playedDefence);

        //Checking if the checkboxes are checked
        if(leavesTarmac.isChecked()) {
            leavesTarmacString = "Y";
        }

        if(humanScored.isChecked()) {
            humanScoredString = "Y";
        }

        if(playedDefence.isChecked()) {
            playedDefenceString = "Y";
        }

        //Stopwatch
        //somehow throw this into shared preferences
        stopwatch = rootView.findViewById(R.id.stopwatch);
        btStart = rootView.findViewById(R.id.play_button);
        btStop = rootView.findViewById(R.id.reset_button);

        handler = new Handler();

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isResume) {
                    tStart = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable, 0);
                    stopwatch.start();
                    isResume = true;
                    btStop.setVisibility(View.GONE);
                    btStart.setImageDrawable(getResources().getDrawable(R.drawable.pause_icon));
                } else {
                    tBuff += tMillisec;
                    handler.removeCallbacks(runnable);
                    stopwatch.stop();
                    isResume = false;
                    btStop.setVisibility(View.VISIBLE);
                    btStart.setImageDrawable(getResources().getDrawable(R.drawable.play_icon));
                }
            }
        });

        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isResume) {
                    btStart.setImageDrawable(getResources().getDrawable(R.drawable.play_icon));
                    tMillisec = 0L;
                    tStart = 0L;
                    tBuff = 0L;
                    tUpdate = 0L;
                    sec = 0;
                    millisec = 0;
                    stopwatch.setText("00:00");
                }
            }
        });

        //Seekbar, throw into shared preferences
        SeekBar seekBar = rootView.findViewById(R.id.seekBar);
        TextView tv = rootView.findViewById(R.id.tv);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv.setText("Bar Climbed: " + String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Finish Button
        Button finish = rootView.findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new QRPage();
                replaceFragment(fragment);
            }
        });

        return rootView;
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            tMillisec = SystemClock.uptimeMillis() - tStart;
            tUpdate = tBuff + tMillisec;
            sec = (int) (tUpdate/1000);
            sec = sec%60;
            millisec = (int) (tUpdate%100);
            stopwatch.setText(String.format("%02d", sec) + ":" + String.format("%02d", millisec));
            handler.postDelayed(this, 60);
        }
    };

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.body_container, someFragment);
        transaction.commit();
    }
}