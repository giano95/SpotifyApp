package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;


import android.app.Activity;
import android.transition.Transition;
import android.view.View;
import android.widget.Button;

import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.Util;
import com.example.marco.myfirstspotifyapp.R;

public class LoginInterface extends ActivityInterface {

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

        ((Button)views.get(R.id.login_button)).setOnClickListener(onLoginButton);
    }

    @Override
    public void onBackEvent() {

    }

    @Override
    public int getNextId() {
        return R.layout.game_mode_choice;
    }

    @Override
    public ActivityInterface getNextInterface() {
        return new GameModeChoiceInterface(mActivity, mySpotify);
    }

    @Override
    public int getNextTransition() {
        return R.transition.transition_1;
    }


    @Override
    public void initButtons() {
        super.initButtons();
    }


    View.OnClickListener onLoginButton = new View.OnClickListener() {
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
}


