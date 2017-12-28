package com.example.marco.myfirstspotifyapp;


import android.app.Activity;

import java.util.Arrays;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmDB {

    private static RealmDB instance;
    private final Realm realm;

    private RealmDB(Activity activity){
        realm = Realm.getDefaultInstance();
    }

    public static RealmDB create(Activity activity) {

        if( instance == null) {
            instance = new RealmDB(activity);
        }
        return instance;
    }

    public static RealmDB getInstance() {
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }

    public static void init(Activity activity){

        Realm.init(activity);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("realm_SpotifyApp")
                .schemaVersion(0)
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public void addScore(int amount) {

        if (isScoreAlreadyIn(amount))
            return;

        realm.beginTransaction();

        Score score = realm.createObject(Score.class);
        score.setAmount(amount);

        realm.commitTransaction();
    }

    public RealmResults<Score> getAllScore(){

        return realm.where(Score.class).findAll();
    }

    public Score[] getSortedScore(){

        RealmResults<Score> results = realm.where(Score.class).findAll();
        int length = results.size();

        if( length == 0)
            return null;

        Score[] scores = new Score[length];

        for (int i = 0; i < length; i++) {
            scores[i] = results.get(i);
        }

        Arrays.sort(scores);

        return scores;
    }

    private boolean isScoreAlreadyIn(int amount) {

        Score score = realm.where(Score.class).equalTo("amount", amount).findFirst();

        if( score != null)
            return true;
        else
            return false;
    }

}
