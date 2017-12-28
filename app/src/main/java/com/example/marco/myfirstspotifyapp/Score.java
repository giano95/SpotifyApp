package com.example.marco.myfirstspotifyapp;

import android.support.annotation.NonNull;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;


public class Score extends RealmObject implements  Comparable<Score> {

    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int compareTo(@NonNull Score compareScore) {

        //descending order
        return compareScore.getAmount() - this.amount;
    }
}
