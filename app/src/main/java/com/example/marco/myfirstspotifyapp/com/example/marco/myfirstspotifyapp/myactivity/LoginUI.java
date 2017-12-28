package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;


import android.app.Activity;
import android.transition.Scene;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.marco.myfirstspotifyapp.ActivityType;
import com.example.marco.myfirstspotifyapp.Event;
import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.Util;
import com.example.marco.myfirstspotifyapp.R;

public class LoginUI extends AbstractActivityUI implements Observable{

    private boolean mLoggedIn;

    public LoginUI(Activity activity, MySpotify mySpotify, ViewGroup rootContainer){
        super(activity,mySpotify,rootContainer);
        super.mScene = Scene.getSceneForLayout(mRootContainer, R.layout.login, mActivity);
        super.mViewsId = new int[]{
                R.id.login_button,
                R.id.play_button,
                R.id.show_scores_button,
        };
    }

    @Override
    public void update() {

        mLoggedIn = mySpotify.isLoggedIn();

        ((Button)mViews.get(R.id.play_button)).setEnabled(mLoggedIn);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCreate() {
        super.initViews();

        //auto-login
        if (!mySpotify.isLoggedIn()) {
            Util.logStatus("Logging in");
            mySpotify.openLoginWindow();
        }

        ((Button)mViews.get(R.id.play_button)).setOnClickListener(onPlayButtonClicked);
        ((ImageButton)mViews.get(R.id.login_button)).setOnClickListener(onLoginButtonClicked);
        ((ImageButton)mViews.get(R.id.show_scores_button)).setOnClickListener(onShowScoresButtonClicked);

    }


    View.OnClickListener onLoginButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!mySpotify.isLoggedIn()) {
                Util.logStatus("Logging in");
                mySpotify.openLoginWindow();
            } else {
                mySpotify.logout();
            }
        }
    };

    View.OnClickListener onPlayButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            mActivityType = ActivityType.GameModeChoiceUI;
            notifyObserver(Event.NextActivity);
        }
    };

    View.OnClickListener onShowScoresButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            mActivityType = ActivityType.ShowBestScore;
            notifyObserver(Event.NextActivity);

        }
    };
}


