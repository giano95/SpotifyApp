package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.R;


public class GameModeChoiceInterface extends ActivityInterface {

    public GameModeChoiceInterface(Activity mActivity, MySpotify mySpotify){
        super(mActivity,mySpotify);

        VIEWS_ID = new int[]{
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
    public void onBackEvent() {

    }

    @Override
    public void initButtons() {
        super.initButtons();
        ((Button)views.get(R.id.mode1)).setOnClickListener(onInTimeButtonClicked);
    }

    View.OnClickListener onInTimeButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mNextId = R.layout.in_time_mode;
            mNextActivityInterface = new InTimeInterface(mActivity, mySpotify);
            notifyObserver(1);
        }
    };


}
