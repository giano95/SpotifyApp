package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;


import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.Playlist;
import com.example.marco.myfirstspotifyapp.R;

public class PlaylistChoiceUI extends AbstractActivityUI {

    public PlaylistChoiceUI(Activity activity, MySpotify mySpotify){
        super(activity, mySpotify);
        super.mViewsId = new int[]{
                R.id.playlist_1,
                R.id.playlist_2,
                R.id.playlist_3,
                R.id.playlist_4,
        };
    }

    @Override
    public void update() {

    }

    @Override
    public void onCreate() {
        super.initViews();

        ((Button)mViews.get(R.id.playlist_1)).setOnClickListener(onTop50ButtonClicked);
        ((Button)mViews.get(R.id.playlist_2)).setOnClickListener(on90sButtonClicked);
        ((Button)mViews.get(R.id.playlist_3)).setOnClickListener(onIndieButtonClicked);
        ((Button)mViews.get(R.id.playlist_4)).setOnClickListener(onMyPlaylistButtonClicked);
    }

    @Override
    public void onDestroy() {

    }

    View.OnClickListener onTop50ButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mySpotify.setPlaylist("spotify:user:spotifycharts:playlist:37i9dQZEVXbMDoHDwVN2tF", 50);

            mNextID = R.layout.in_time_mode;
            mNextAbstractActivityUI = new InTimeModeUI(mActivity, mySpotify);
            notifyObserver(1);
        }
    };

    View.OnClickListener on90sButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mySpotify.setPlaylist("spotify:user:spotify:playlist:37i9dQZF1DWXnfI9qTCXnT", 50);

            mNextID = R.layout.in_time_mode;
            mNextAbstractActivityUI = new InTimeModeUI(mActivity, mySpotify);
            notifyObserver(1);
        }
    };

    View.OnClickListener onIndieButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mySpotify.setPlaylist("spotify:user:spotify:playlist:37i9dQZF1DX6PSDDh80gxI", 50);

            mNextID = R.layout.in_time_mode;
            mNextAbstractActivityUI = new InTimeModeUI(mActivity, mySpotify);
            notifyObserver(1);
        }
    };

    View.OnClickListener onMyPlaylistButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mySpotify.setPlaylist("spotify:user:marco.gianelli.95:playlist:0IA9QC2yVK6yq4gdZD57p9", 18);

            mNextID = R.layout.in_time_mode;
            mNextAbstractActivityUI = new InTimeModeUI(mActivity, mySpotify);
            notifyObserver(1);
        }
    };
}
