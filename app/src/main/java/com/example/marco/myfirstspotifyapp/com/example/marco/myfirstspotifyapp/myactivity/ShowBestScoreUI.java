package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;

import android.app.Activity;
import android.transition.Scene;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.R;
import com.example.marco.myfirstspotifyapp.RealmDB;
import com.example.marco.myfirstspotifyapp.Score;

import java.util.Arrays;
import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmResults;


public class ShowBestScoreUI extends AbstractActivityUI {

    private Score[] scores;
    private RealmDB realmDB;

    private static final int NUM_SCORE_DISPLAYED = 5;

    public ShowBestScoreUI(Activity activity, MySpotify mySpotify, ViewGroup rootContainer){
        super(activity,mySpotify,rootContainer);
        super.mScene = Scene.getSceneForLayout(mRootContainer, R.layout.show_best_score, mActivity);
        super.mViewsId = new int[]{
                R.id.score_1_button,
                R.id.score_2_button,
                R.id.score_3_button,
                R.id.score_4_button,
                R.id.score_5_button,
                R.id.show_best_score_textview,
        };

        this.realmDB = RealmDB.getInstance();
    }

    @Override
    public void update() {

    }

    @Override
    public void onCreate() {
        super.initViews();

        scores = realmDB.getSortedScore();

        if( scores == null) {

            for (int i = 0; i < NUM_SCORE_DISPLAYED; i++)
                ((Button) mViews.get(mViewsId[i])).setText("- - -");

            return;
        }

        for (int i = 0; i < NUM_SCORE_DISPLAYED; i++) {
            if(i < scores.length)
                ((Button) mViews.get(mViewsId[i])).setText(String.valueOf(scores[i].getAmount()));
            else
                ((Button) mViews.get(mViewsId[i])).setText("- - -");
        }
    }

    @Override
    public void onDestroy() {

    }
}
