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
                R.id.mode1,
                R.id.mode2,
                R.id.mode3,
                R.id.mode_textView,
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
        ((Button)mViews.get(R.id.mode1)).setOnClickListener(onInTimeButtonClicked);
        ((Button)mViews.get(R.id.mode2)).setOnClickListener(onFindArtistButtonClicked);
    }

    View.OnClickListener onInTimeButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //mNextID = R.layout.playlist_choice;
            //mNextAbstractActivityUI = new InTimeModeUI(mActivity, mySpotify, mRootContainer);
            mActivityType = ActivityType.findTrackName;
            notifyObserver(Event.NextActivity);
        }
    };

    View.OnClickListener onFindArtistButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //mNextID = R.layout.find_artist;
            // mNextAbstractActivityUI = new FindArtistUI(mActivity, mySpotify, mRootContainer);
            mActivityType = ActivityType.findArtistName;
            notifyObserver(Event.NextActivity);
        }
    };
}
