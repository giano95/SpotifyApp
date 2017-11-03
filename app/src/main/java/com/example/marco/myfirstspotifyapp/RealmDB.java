package com.example.marco.myfirstspotifyapp;


import android.app.Activity;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmDB {

    private Activity mActivity;
    private Realm mRealm;

    public RealmDB(Activity activity){
        this.mActivity = activity;

        Realm.init(activity);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);

        mRealm = Realm.getDefaultInstance();
    }

    public void onDestroy(){
        mRealm.close();
    }

}
