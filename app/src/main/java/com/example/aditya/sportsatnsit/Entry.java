package com.example.aditya.sportsatnsit;

public class Entry {
    public String date;
    public String time;
    public Long timeInMiliSec;
    public String team1;
    public String team2;
    public String score1;
    public String score2;

    Entry() {

    }

    Entry(String date, String time, Long timeInMiliSec, String team1, String team2, String score1, String score2) {
        this.date = date;
        this.time = time;
        this.timeInMiliSec = timeInMiliSec;
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
    }
}
