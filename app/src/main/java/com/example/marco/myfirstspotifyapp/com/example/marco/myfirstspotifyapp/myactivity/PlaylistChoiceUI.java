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
                R.id.playlist_1,
                R.id.playlist_2,
                R.id.playlist_3,
                R.id.playlist_4,
                R.id.playlist_5,
                R.id.playlist_6,
                R.id.playlist_7,
                R.id.playlist_8,
                R.id.playlist_9,
                R.id.playlist_10,
        };
    }

    @Override
    public void update() {

    }

    @Override
    public void onCreate() {
        super.initViews();

        ((Button)mViews.get(R.id.playlist_1)).setOnClickListener(onPlaylistOneButtonClicked);
        ((Button)mViews.get(R.id.playlist_2)).setOnClickListener(onPlaylistTwoButtonClicked);
        ((Button)mViews.get(R.id.playlist_3)).setOnClickListener(onPlaylistThreeButtonClicked);
        ((Button)mViews.get(R.id.playlist_4)).setOnClickListener(onPlaylistFourButtonClicked);
        ((Button)mViews.get(R.id.playlist_5)).setOnClickListener(onPlaylistFiveButtonClicked);
        ((Button)mViews.get(R.id.playlist_6)).setOnClickListener(onPlaylistSixButtonClicked);
        ((Button)mViews.get(R.id.playlist_7)).setOnClickListener(onPlaylistSevenButtonClicked);
        ((Button)mViews.get(R.id.playlist_8)).setOnClickListener(onPlaylistEightButtonClicked);
        ((Button)mViews.get(R.id.playlist_9)).setOnClickListener(onPlaylistNineButtonClicked);
        ((Button)mViews.get(R.id.playlist_10)).setOnClickListener(onPlaylistTenButtonClicked);
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

    View.OnClickListener onPlaylistTenButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mySpotify.setPlaylist("spotify:user:spotify:playlist:37i9dQZF1DWWavShqgIPsw", 50);

            mActivityType = mActivityType.GameUI;
            notifyObserver(Event.NextActivity);
        }
    };
}
