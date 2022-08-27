package com.sukhesh.scoutingapp;

import android.content.SharedPreferences;

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
        return this.matchType + " " + Integer.toString(this.matchNumber);
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
        editor.commit();
    }
}
