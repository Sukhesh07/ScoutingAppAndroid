package com.sukhesh.scoutingapp;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.SystemClock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class RapidReactDashboard extends Fragment {
    long tMillis = 0L;
    long tStart = 0L;
    long tBuff = 0L;
    long tUpdate = 0L;
    boolean isResume = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rapid_react_dashboard, container, false);

        // Scrolling of the page
        ScrollView scrollView = rootView.findViewById(R.id.scrollView);
        AnimationDrawable animationDrawable = (AnimationDrawable) scrollView.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();


        //Move this to FinishTab.java that's where the qr is being created
        TextView text = rootView.findViewById(R.id.title_dashboard);
        Match currentMatch = Match.MatchFromSharedPreferences(requireContext().getSharedPreferences("quals", Context.MODE_PRIVATE));
        text.setText(currentMatch.matchName());

        ArrayList<View> rawViews = new ArrayList<>();
        rootView.findViewsWithText(rawViews, "FiniteInt", View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
        ArrayList<FiniteInt> finiteInts = FiniteInt.generateArrayListFromViews(rawViews);

        for(FiniteInt f: finiteInts) {
            f.plus.setOnClickListener(view -> {
                f.value++;
                f.tally.setText(f.value);
            });
            f.minus.setOnClickListener(view -> {
                f.value--;
                f.tally.setText(f.value);
            });
        }

        rawViews.clear();
        rootView.findViewsWithText(rawViews, "ClosedQuestion", View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
        ArrayList<ClosedQuestion> checkboxes = ClosedQuestion.generateArrayListFromViews(rawViews);
        for(ClosedQuestion c: checkboxes) {
            c.check.setOnClickListener(view -> c.value = c.check.isChecked());
        }

        //Seekbar, throw into shared preferences
        SeekBar seekBar = rootView.findViewById(R.id.seekBar);
        TextView tv = rootView.findViewById(R.id.tv);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv.setText("Bar Climbed: " + i);
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
        finish.setOnClickListener(view -> getParentFragmentManager().beginTransaction().replace(R.id.body_container, new QRPage()).commit());

        //Stopwatch
        //BIG JANK, fix and make into class l8r
        Chronometer stopwatch = rootView.findViewById(R.id.stopwatch);
        ImageButton btStart = rootView.findViewById(R.id.play_button);
        ImageButton btStop = rootView.findViewById(R.id.reset_button);
        Handler handler = new Handler();


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                tMillis = SystemClock.uptimeMillis() - tStart;
                tUpdate = tBuff + tMillis;
                int sec = (int) (tUpdate/1000) % 60;
                int millis = (int) (tUpdate%100);
                stopwatch.setText(String.format("%02d", sec) + ":" + String.format("%02d", millis));
                handler.postDelayed(this, 60);
            }
        };


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
                    tBuff += tMillis;
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
                    tMillis = 0L;
                    tStart = 0L;
                    tBuff = 0L;
                    tUpdate = 0L;
                    stopwatch.setText("00:00");
                }
            }
        });

        return rootView;
    }
}
