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


public class GameModeChoiceUI extends AbstractActivityUI {

    public GameModeChoiceUI(Activity mActivity, MySpotify mySpotify, ViewGroup rootContainer){
        super(mActivity,mySpotify,rootContainer);
        super.mScene = Scene.getSceneForLayout(mRootContainer, R.layout.game_mode_choice, mActivity);
        super.mViewsId = new int[]{
                R.id.game_mode_choice_textview,
                R.id.find_track_name_button,
                R.id.find_artist_name_button,
        };
    }

    @Override
    public void update() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCreate() {
        super.initViews();
        ((Button)mViews.get(R.id.find_track_name_button)).setOnClickListener(onFindTrackNameButtonClicked);
        ((Button)mViews.get(R.id.find_artist_name_button)).setOnClickListener(onFindArtistNameButtonClicked);
    }

    View.OnClickListener onFindTrackNameButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            mActivityType = ActivityType.findTrackName;
            notifyObserver(Event.NextActivity);
        }
    };

    View.OnClickListener onFindArtistNameButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            mActivityType = ActivityType.findArtistName;
            notifyObserver(Event.NextActivity);
        }
    };
}
