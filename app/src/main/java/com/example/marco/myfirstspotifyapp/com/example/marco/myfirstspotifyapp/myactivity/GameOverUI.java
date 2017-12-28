package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.transition.Scene;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.marco.myfirstspotifyapp.MainActivity;
import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.R;
import com.example.marco.myfirstspotifyapp.RealmDB;
import com.example.marco.myfirstspotifyapp.Score;

import io.realm.Realm;

public class GameOverUI extends AbstractActivityUI {

    private Integer mReachedScore;
    private RealmDB realmDB;

    public GameOverUI(Activity activity, MySpotify mySpotify, ViewGroup rootContainer, Integer reachedScore) {
        super(activity, mySpotify, rootContainer);
        super.mScene = Scene.getSceneForLayout(mRootContainer, R.layout.game_over, mActivity);
        super.mViewsId = new int[]{
                R.id.game_over_textview,
                R.id.game_over_2_textview,
                R.id.game_over_score_textview,
                R.id.score_ratingBar,
        };

        this.mReachedScore = reachedScore;
        this.realmDB = RealmDB.getInstance();
    }

    @Override
    public void update() {

    }

    @Override
    public void onCreate() {
        super.initViews();

        mySpotify.pause();

        realmDB.addScore(mReachedScore);

        ((TextView)mViews.get(R.id.game_over_score_textview)).setText("" + mReachedScore);
        ((RatingBar)mViews.get(R.id.score_ratingBar)).setNumStars(getNumStars(mReachedScore));

        //set tint white
        Drawable stars = (LayerDrawable) ((RatingBar)mViews.get(R.id.score_ratingBar)).getProgressDrawable();
        DrawableCompat.setTint(stars, Color.WHITE);

        //set color yellow
        LayerDrawable stars2 = (LayerDrawable) ((RatingBar)mViews.get(R.id.score_ratingBar)).getProgressDrawable();
        stars2.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        ((RatingBar)mViews.get(R.id.score_ratingBar)).setIsIndicator(true);
    }

    @Override
    public void onDestroy() {

    }

    private int getNumStars(Integer reachedScore){
        if(reachedScore <= 20)
            return 1;
        else if(reachedScore > 20 && reachedScore <= 40)
            return 2;
        else if(reachedScore > 40 && reachedScore <= 60)
            return 3;
        else if(reachedScore > 60 && reachedScore <= 80)
            return 4;
        else if(reachedScore > 80)
            return 5;
        else
            return 0;   // only for compiler error
    }
}
