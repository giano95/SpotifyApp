package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;


import android.app.Activity;
import android.os.CountDownTimer;
import android.transition.Scene;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marco.myfirstspotifyapp.ArtistPlaylistChoice;
import com.example.marco.myfirstspotifyapp.Event;
import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.R;

public class FindArtistNameHardUI extends AbstractActivityUI {

    private int mSkipCount;
    private ArtistPlaylistChoice artistPlaylistChoice;
    private CountDownTimer mTimer;
    private Integer mScore;

    public FindArtistNameHardUI(Activity mActivity, MySpotify mySpotify, ViewGroup rootContainer) {
        super(mActivity, mySpotify, rootContainer);
        super.mScene = Scene.getSceneForLayout(mRootContainer, R.layout.hard_mode, mActivity);
        super.mViewsId = new int[]{
                R.id.edit_name_edittext,
                R.id.enter_button,
                R.id.skip_button,
                R.id.score_hard_textview,
                R.id.chronometer_hard_textview,
        };

        this.artistPlaylistChoice = new ArtistPlaylistChoice("italian");
        this.mSkipCount = 3;
        this.mScore = 0;
        this.mTimer = new CountDownTimer(45000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                ((TextView)mViews.get(R.id.chronometer_hard_textview)).setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                ((TextView)mViews.get(R.id.chronometer_hard_textview)).setText("finito!");
                notifyObserver(Event.EndOfGame);
            }
        };

    }
    View.OnClickListener  onSkipButtonPressed = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(mSkipCount >0) {
                mySpotify.playRandomSong();
                mSkipCount--;
            }
            if(mSkipCount ==0){
                ((Button)mViews.get(R.id.skip_button)).setText(R.string.skip_finished_button);
                ((Button)mViews.get(R.id.skip_button)).setEnabled(false);

            }
        }
    };

    View.OnClickListener onEnterButtonPressed = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = (mySpotify.getCurrentTrackArtist());
            String name2 = new String ((((EditText)mViews.get(R.id.edit_name_edittext)).getText()).toString());


            if(name.equals(name2)){
                mScore += 10;
                ((EditText)mViews.get(R.id.edit_name_edittext)).setText("");
                mySpotify.setPlaylist((artistPlaylistChoice.getRandomPlaylist()), 15);
                mySpotify.playRandomSong();
            }
            else {
                Toast toast = Toast.makeText(mActivity.getApplicationContext(), R.string.wrong_name_toast, Toast.LENGTH_SHORT);
                toast.show();

                mScore -= 10;
                update();
            }
        }
    };


    @Override
    public void update() {

        ((TextView)mViews.get(R.id.score_hard_textview)).setText(mActivity.getString(R.string.score_textview) + " " + mScore);
    }

    @Override
    public void onCreate() {
        super.initViews();
        ((EditText)mViews.get(R.id.edit_name_edittext)).setHint(R.string.edit_name_artist_edittext);
        ((Button)mViews.get(R.id.skip_button)).setOnClickListener(onSkipButtonPressed);
        ((Button)mViews.get(R.id.enter_button)).setOnClickListener(onEnterButtonPressed);

        mTimer.start();
        mySpotify.playRandomSong();
    }

    @Override
    public void onDestroy() {
        mySpotify.pause();
    }
}
