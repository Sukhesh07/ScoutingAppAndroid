package com.sukhesh.scoutingapp;

import android.view.View;
import android.widget.CheckBox;
import java.util.ArrayList;

public class ClosedQuestion {
    String name;
    CheckBox check;

    boolean value;

    ClosedQuestion(String name, CheckBox check) {
        this.name = name;
        this.check = check;
        this.value = false;
    }

    public static ArrayList<ClosedQuestion> generateArrayListFromViews(ArrayList<View> views) {
        ArrayList<ClosedQuestion> closedQuestions = new ArrayList<>();
        for(int i = 0; i < views.size(); i++) {
            ClosedQuestion f = new ClosedQuestion(
                    views.get(i).getContentDescription().toString().split(" ")[1],
                    (CheckBox)views.get(i));
            closedQuestions.add(f);
        }

        return closedQuestions;
    }
}
