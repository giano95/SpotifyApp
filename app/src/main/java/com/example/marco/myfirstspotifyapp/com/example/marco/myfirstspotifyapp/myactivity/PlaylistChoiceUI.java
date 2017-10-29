package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;


import android.app.Activity;
import android.transition.Scene;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.marco.myfirstspotifyapp.Event;
import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.R;

public class PlaylistChoiceUI extends AbstractActivityUI {

    public PlaylistChoiceUI(Activity activity, MySpotify mySpotify, ViewGroup rootContainer){
        super(activity, mySpotify, rootContainer);
        super.mScene = Scene.getSceneForLayout(mRootContainer, R.layout.playlist_choice, mActivity);
        super.mViewsId = new int[]{
                R.id.playlist_choice_textview,
                R.id.playlist_1_button,
                R.id.playlist_2_button,
                R.id.playlist_3_button,
                R.id.playlist_4_button,
                R.id.playlist_5_button,
                R.id.playlist_6_button,
                R.id.playlist_7_button,
                R.id.playlist_8_button,
                R.id.playlist_9_button,
        };
    }

    @Override
    public void update() {

    }

    @Override
    public void onCreate() {
        super.initViews();

        ((Button)mViews.get(R.id.playlist_1_button)).setOnClickListener(onPlaylistOneButtonClicked);
        ((Button)mViews.get(R.id.playlist_2_button)).setOnClickListener(onPlaylistTwoButtonClicked);
        ((Button)mViews.get(R.id.playlist_3_button)).setOnClickListener(onPlaylistThreeButtonClicked);
        ((Button)mViews.get(R.id.playlist_4_button)).setOnClickListener(onPlaylistFourButtonClicked);
        ((Button)mViews.get(R.id.playlist_5_button)).setOnClickListener(onPlaylistFiveButtonClicked);
        ((Button)mViews.get(R.id.playlist_6_button)).setOnClickListener(onPlaylistSixButtonClicked);
        ((Button)mViews.get(R.id.playlist_7_button)).setOnClickListener(onPlaylistSevenButtonClicked);
        ((Button)mViews.get(R.id.playlist_8_button)).setOnClickListener(onPlaylistEightButtonClicked);
        ((Button)mViews.get(R.id.playlist_9_button)).setOnClickListener(onPlaylistNineButtonClicked);
    }

    @Override
    public void onDestroy() {

    }

    View.OnClickListener onPlaylistOneButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mySpotify.setPlaylist("spotify:user:spotifycharts:playlist:37i9dQZEVXbMDoHDwVN2tF", 50);

            mActivityType = mActivityType.GameUI;
            notifyObserver(Event.NextActivity);
        }
    };

    View.OnClickListener onPlaylistTwoButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mySpotify.setPlaylist("spotify:user:spotify:playlist:37i9dQZF1DWXnfI9qTCXnT", 50);

            mActivityType = mActivityType.GameUI;
            notifyObserver(Event.NextActivity);
        }
    };

    View.OnClickListener onPlaylistThreeButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mySpotify.setPlaylist("spotify:user:spotify:playlist:37i9dQZF1DX6PSDDh80gxI", 50);

            mActivityType = mActivityType.GameUI;
            notifyObserver(Event.NextActivity);
        }
    };

    View.OnClickListener onPlaylistFourButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mySpotify.setPlaylist("spotify:user:spotify:playlist:37i9dQZF1DX6mWRaog94SQ", 50);

            mActivityType = mActivityType.GameUI;
            notifyObserver(Event.NextActivity);
        }
    };

    View.OnClickListener onPlaylistFiveButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mySpotify.setPlaylist("spotify:user:spotify:playlist:37i9dQZF1DWXRqgorJj26U", 129);

            mActivityType = mActivityType.GameUI;
            notifyObserver(Event.NextActivity);
        }
    };

    View.OnClickListener onPlaylistSixButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mySpotify.setPlaylist("spotify:user:spotify:playlist:37i9dQZF1DX2LTcinqsO68", 57);

            mActivityType = mActivityType.GameUI;
            notifyObserver(Event.NextActivity);
        }
    };

    View.OnClickListener onPlaylistSevenButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mySpotify.setPlaylist("spotify:user:spotify:playlist:37i9dQZF1DX14EWeH2Pwf3", 50);

            mActivityType = mActivityType.GameUI;
            notifyObserver(Event.NextActivity);
        }
    };

    View.OnClickListener onPlaylistEightButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mySpotify.setPlaylist("spotify:user:spotify:playlist:37i9dQZF1DX2ENAPP1Tyed", 50);

            mActivityType = mActivityType.GameUI;
            notifyObserver(Event.NextActivity);
        }
    };

    View.OnClickListener onPlaylistNineButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mySpotify.setPlaylist("spotify:user:spotify:playlist:37i9dQZF1DX7YCknf2jT6s", 50);

            mActivityType = mActivityType.GameUI;
            notifyObserver(Event.NextActivity);
        }
    };
}
