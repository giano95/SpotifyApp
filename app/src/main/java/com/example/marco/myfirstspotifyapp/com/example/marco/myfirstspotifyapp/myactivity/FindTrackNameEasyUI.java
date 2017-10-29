package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;


import android.app.Activity;
import android.os.CountDownTimer;
import android.transition.Scene;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marco.myfirstspotifyapp.Event;
import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.R;
import com.example.marco.myfirstspotifyapp.StringHandler;

import java.util.Random;

public class FindTrackNameEasyUI extends AbstractActivityUI {

    private Random mRandomGenerator;
    private Integer mScore;
    private CountDownTimer mTimer;

    public FindTrackNameEasyUI(Activity mActivity, MySpotify mySpotify, ViewGroup rootContainer){
        super(mActivity,mySpotify,rootContainer);
        super.mScene = Scene.getSceneForLayout(mRootContainer, R.layout.easy_mode, mActivity);
        super.mViewsId = new int[]{
                R.id.song_1_button,
                R.id.song_2_button,
                R.id.song_3_button,
                R.id.score_easy_textview,
                R.id.chronometer_easy_textview,
                R.id.coverart_imageview,
        };

        this.mRandomGenerator = new Random();
        this.mScore = 0;
        this.mTimer = new CountDownTimer(45000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                ((TextView)mViews.get(R.id.chronometer_easy_textview)).setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                ((TextView)mViews.get(R.id.chronometer_easy_textview)).setText("finito!");
                notifyObserver(Event.EndOfGame);
            }
        };
    }

    @Override
    public void update() {
        int a,b,c;

        switch (mRandomGenerator.nextInt(3)) {
            case 0: a = R.id.song_1_button;  b = R.id.song_2_button;    c = R.id.song_3_button;  break;
            case 1: a = R.id.song_3_button;  b = R.id.song_1_button;    c = R.id.song_2_button;  break;
            case 2: a = R.id.song_2_button;  b = R.id.song_3_button;    c = R.id.song_1_button;  break;
            default:    a=0;    b=0;    c=0;    break; // only for compiler error
        }

        mySpotify.getCoverArt((ImageView) mViews.get(R.id.coverart_imageview));

        ((Button)mViews.get(a)).setText(StringHandler.setProperly(mySpotify.getCurrentTrackName()));
        ((Button)mViews.get(b)).setText(StringHandler.setProperly(mySpotify.getNextTrackName()));
        ((Button)mViews.get(c)).setText(StringHandler.setProperly(mySpotify.getPrevTrackName()));


        ((TextView)mViews.get(R.id.score_easy_textview)).setText(mActivity.getString(R.string.score_textview) + " " + mScore);

        ((Button)mViews.get(a)).setOnClickListener(RightSong);
        ((Button)mViews.get(b)).setOnClickListener(WrongSong);
        ((Button)mViews.get(c)).setOnClickListener(WrongSong);
    }

    @Override
    public void onDestroy() {
        mySpotify.pause();
        mTimer.cancel();
    }

    @Override
    public void onCreate() {
        super.initViews();
        mTimer.start();
        mySpotify.playRandomSong();
    }


    View.OnClickListener RightSong = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mScore += 10;
            Toast toast = Toast.makeText(mActivity.getApplicationContext(), R.string.right_name_toast, Toast.LENGTH_SHORT);
            toast.show();
            mySpotify.playRandomSong();
        }
    };

    View.OnClickListener WrongSong = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mScore -= 10;
            mySpotify.playRandomSong();
        }
    };
}
