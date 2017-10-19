package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.R;


public class GameModeChoiceUI extends AbstractActivityUI {

    public GameModeChoiceUI(Activity mActivity, MySpotify mySpotify){
        super(mActivity,mySpotify);
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
            mNextID = R.layout.playlist_choice;
            mNextAbstractActivityUI = new PlaylistChoiceUI(mActivity, mySpotify);
            notifyObserver(1);
        }
    };

    View.OnClickListener onFindArtistButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mNextID = R.layout.find_artist;
            mNextAbstractActivityUI = new FindArtistUI(mActivity, mySpotify);
            notifyObserver(1);
        }
    };
}
