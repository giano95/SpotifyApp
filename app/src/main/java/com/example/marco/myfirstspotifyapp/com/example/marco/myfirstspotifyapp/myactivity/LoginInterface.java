package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;


import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.Util;
import com.example.marco.myfirstspotifyapp.R;

public class LoginInterface extends ActivityInterface implements Observable{

    public LoginInterface(Activity mActivity, MySpotify mySpotify){
        super(mActivity,mySpotify);
        VIEWS_ID = new int[]{
                R.id.login_button,
                R.id.play_button,
        };
    }

    @Override
    public void update() {

        boolean loggedIn = mySpotify.isLoggedIn();

        ((Button)views.get(R.id.login_button)).setText(loggedIn ? R.string.logout_button_label : R.string.login_button_label);
        ((Button)views.get(R.id.play_button)).setEnabled(loggedIn);
    }

    @Override
    public void onBackEvent() {

    }

    @Override
    public void initButtons() {
        super.initButtons();
        ((Button)views.get(R.id.login_button)).setOnClickListener(onLoginButtonClicked);
        ((Button)views.get(R.id.play_button)).setOnClickListener(onPlayButtonClicked);
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
            mNextId = R.layout.game_mode_choice;
            mNextActivityInterface = new GameModeChoiceInterface(mActivity, mySpotify);
            notifyObserver(1);
        }
    };
}


