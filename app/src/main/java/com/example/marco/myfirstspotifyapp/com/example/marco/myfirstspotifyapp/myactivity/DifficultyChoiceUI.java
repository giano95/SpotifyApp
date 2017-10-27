package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;

import android.app.Activity;
import android.transition.Scene;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.marco.myfirstspotifyapp.ActivityType;
import com.example.marco.myfirstspotifyapp.Event;
import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.R;

public class DifficultyChoiceUI extends AbstractActivityUI {

    public DifficultyChoiceUI(Activity mActivity, MySpotify mySpotify, ViewGroup rootContainer){
        super(mActivity,mySpotify,rootContainer);
        super.mScene = Scene.getSceneForLayout(mRootContainer, R.layout.difficulty_choice, mActivity);
        super.mViewsId = new int[]{
                R.id.in_time_level1,
                R.id.in_time_level2,
                R.id.in_time_texview_choice,
        };
    }
    @Override
    public void update() {

    }

    @Override
    public void onCreate() {

        super.initViews();
        ((Button)mViews.get(R.id.in_time_level1)).setOnClickListener(onEasyButtonClicked);
        ((Button)mViews.get(R.id.in_time_level2)).setOnClickListener(onHardButtonClicked);

    }

    View.OnClickListener onEasyButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mActivityType = ActivityType.easy;
            notifyObserver(Event.NextActivity);
        }
    };

    View.OnClickListener onHardButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mActivityType = ActivityType.hard;
            notifyObserver(Event.NextActivity);

        }
    };


    @Override
    public void onDestroy() {

    }
}
