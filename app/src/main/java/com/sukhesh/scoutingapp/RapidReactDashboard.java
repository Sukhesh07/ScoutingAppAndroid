package com.sukhesh.scoutingapp;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.SystemClock;
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
        ArrayList<View> plusButtons = new ArrayList<View>();

        //rootView.findViewsWithText(plusButtons, "+");
        Button btnPlus = rootView.findViewById(R.id.button_plus);
        Button btnPlus2 = rootView.findViewById(R.id.button_plus2);
        Button btnPlus3 = rootView.findViewById(R.id.button_plus3);
        Button btnPlus4 = rootView.findViewById(R.id.button_plus4);
        Button btnPlus5 = rootView.findViewById(R.id.button_plus5);
        Button btnPlus6 = rootView.findViewById(R.id.button_plus6);
        Button btnPlus7 = rootView.findViewById(R.id.button_plus7);
        Button btnPlus8 = rootView.findViewById(R.id.button_plus8);

        Button btnMinus = rootView.findViewById(R.id.button_minus);
        Button btnMinus2 = rootView.findViewById(R.id.button_minus2);
        Button btnMinus3 = rootView.findViewById(R.id.button_minus3);
        Button btnMinus4 = rootView.findViewById(R.id.button_minus4);
        Button btnMinus5 = rootView.findViewById(R.id.button_minus5);
        Button btnMinus6 = rootView.findViewById(R.id.button_minus6);
        Button btnMinus7 = rootView.findViewById(R.id.button_minus7);
        Button btnMinus8 = rootView.findViewById(R.id.button_minus8);

        TextView tally = rootView.findViewById(R.id.tally);
        TextView tally2 = rootView.findViewById(R.id.tally2);
        TextView tally3 = rootView.findViewById(R.id.tally3);
        TextView tally4 = rootView.findViewById(R.id.tally4);
        TextView tally5 = rootView.findViewById(R.id.tally5);
        TextView tally6 = rootView.findViewById(R.id.tally6);
        TextView tally7 = rootView.findViewById(R.id.tally7);
        TextView tally8 = rootView.findViewById(R.id.tally8);

        //All of these have to go into shared preferences
        CheckBox leavesTarmac = rootView.findViewById(R.id.leavesTarmac);
        CheckBox humanScored = rootView.findViewById(R.id.humanScored);
        CheckBox playedDefence = rootView.findViewById(R.id.playedDefence);

        //Auton High Make + Button
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inc++;
                tally.setText(Integer.toString(inc));
            }
        });

        //Auton High Make - Button
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inc--;
                tally.setText(Integer.toString(inc));
            }
        });

        //Auton High Miss + Button
        btnPlus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inc2++;
                tally2.setText(Integer.toString(inc2));
            }
        });

        //Auton High Miss - Button
        btnMinus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inc2--;
                tally2.setText(Integer.toString(inc2));
            }
        });

        //Auton Low Make + Button
        btnPlus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inc3++;
                tally3.setText(Integer.toString(inc3));
            }
        });

        //Auton Low Make - Button
        btnMinus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inc3--;
                tally3.setText(Integer.toString(inc3));
            }
        });

        //Auton Low Miss + Button
        btnPlus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inc4++;
                tally4.setText(Integer.toString(inc4));
            }
        });

        //Auton Low Miss - Button
        btnMinus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inc4--;
                tally4.setText(Integer.toString(inc4));
            }
        });

        //Teleop High Make + Button
        btnPlus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inc5++;
                tally5.setText(Integer.toString(inc5));
            }
        });

        //Teleop High Make - Button
        btnMinus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inc5--;
                tally5.setText(Integer.toString(inc5));
            }
        });

        //Teleop High Miss + Button
        btnPlus6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inc6++;
                tally6.setText(Integer.toString(inc6));
            }
        });

        //Teleop High Miss - Button
        btnMinus6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inc6--;
                tally6.setText(Integer.toString(inc6));
            }
        });

        //Teleop Low Make + Button
        btnPlus7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inc7++;
                tally7.setText(Integer.toString(inc7));
            }
        });

        //Teleop Low Make - Button
        btnMinus7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inc7--;
                tally7.setText(Integer.toString(inc7));
            }
        });

        //Teleop Low Miss + Button
        btnPlus8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inc8++;
                tally8.setText(Integer.toString(inc8));
            }
        });

        //Teleop Low Miss - Button
        btnMinus8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inc8--;
                tally8.setText(Integer.toString(inc8));
            }
        });

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
                Fragment fragment = new FinishTab();
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