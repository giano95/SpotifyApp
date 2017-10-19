package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;


import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.Util;
import com.example.marco.myfirstspotifyapp.R;

public class LoginUI extends AbstractActivityUI implements Observable{

    private boolean mLoggedIn;

    public LoginUI(Activity mActivity, MySpotify mySpotify){
        super(mActivity,mySpotify);
        super.mViewsId = new int[]{
                R.id.login_button,
                R.id.play_button,
        };
    }

    @Override
    public void update() {

        mLoggedIn = mySpotify.isLoggedIn();

        ((Button)mViews.get(R.id.login_button)).setText(mLoggedIn ? R.string.logout_button_label : R.string.login_button_label);
        ((Button)mViews.get(R.id.play_button)).setEnabled(mLoggedIn);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCreate() {
        super.initViews();

        ((Button)mViews.get(R.id.login_button)).setOnClickListener(onLoginButtonClicked);
        ((Button)mViews.get(R.id.play_button)).setOnClickListener(onPlayButtonClicked);
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
            mNextID = R.layout.game_mode_choice;
            mNextAbstractActivityUI = new GameModeChoiceUI(mActivity, mySpotify);
            notifyObserver(1);
        }
    };
}


