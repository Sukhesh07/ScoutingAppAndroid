package com.sukhesh.scoutingapp;

import android.content.SharedPreferences;

import java.lang.reflect.*;
import java.util.ArrayList;


public class Match {
    String matchType;
    int matchNumber;
    int teamNumber;
    String teamName;
    String robotAllianceInfo;

    Match(String matchType, int matchNumber, int teamNumber, String teamName, String robotAllianceInfo) {
        this.matchType = matchType;
        this.matchNumber = matchNumber;
        this.teamNumber = teamNumber;
        this.teamName = teamName;
        this.robotAllianceInfo = robotAllianceInfo;
    }

    public String matchName() {
        return this.matchType + " " + this.matchNumber;
    }

    public static Match MatchFromSharedPreferences(SharedPreferences sp) {
        String matchType = sp.getString("TYPE", "Qualification");
        int matchNumber = sp.getInt("MATCH_NUMBER", 1);
        int teamNumber = sp.getInt("TEAM_NUMBER", 201);
        String teamName = sp.getString("TEAM_NAME", "The FEDS");
        String robotAllianceInfo = sp.getString("ROBOT_ALLIANCE_INFO", "Red 1");
        return new Match(matchType, matchNumber, teamNumber, teamName, robotAllianceInfo);
    }

    public void storeMatch(SharedPreferences sp) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("TYPE", this.matchType);
        editor.putInt("MATCH_NUMBER", this.matchNumber);
        editor.putInt("TEAM_NUMBER", this.teamNumber);
        editor.putString("TEAM_NAME", this.teamName);
        editor.putString("ROBOT_ALLIANCE_INFO", this.robotAllianceInfo);
        editor.apply();
    }


    // TODO: extract this to class citrusCiruitStyleMessage and pass in fields in initializer
    public String citrusCircuitStyleMessage() {
        Field[] fieldList = this.getClass().getDeclaredFields();
        String returnString = "";
        for(int i = 0; i < fieldList.length; i++) {
            Field fld = fieldList[i];
            try {
                String s = numToTwoDigitCode(i) + fld.get(this).toString() + "$";
                returnString += s;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return returnString;
    }


    public String encodeVariableName(String varName) {
        Field[] fieldList = this.getClass().getDeclaredFields();
        for (int i = 0; i < fieldList.length; i++) {
            if (fieldList[i].getName().equals(varName)) {
                return numToTwoDigitCode(i);
            }
        }
        return null;
    }

    private String numToTwoDigitCode(int num) {
        char secondChar = (char)((int)'A' + num % 26);
        char firstChar = (char)((int)'A' + num / 26);
        return "" + firstChar + secondChar;
    }

    private int twoDigitCodeToNum(String code){
        if(code.length() != 2) {
            return -1;
        }
        return (26 * ((int)Character.toUpperCase(code.charAt(0)) - (int)'A')) +
                     ((int)Character.toUpperCase(code.charAt(1)) - (int)'A');
    }

    public String decodeVariableNames(String encodedVarName) {
        int num = twoDigitCodeToNum(encodedVarName);
        Field[] fieldList = this.getClass().getDeclaredFields();
        return fieldList[num].getName();
    }
}
