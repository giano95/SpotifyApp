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

import java.util.Random;

public class FindTrackNameEasyUI extends AbstractActivityUI {

    private Random mRandomGenerator;
    private Integer mScore;
    private CountDownTimer mTimer;

    public FindTrackNameEasyUI(Activity mActivity, MySpotify mySpotify, ViewGroup rootContainer){
        super(mActivity,mySpotify,rootContainer);
        super.mScene = Scene.getSceneForLayout(mRootContainer, R.layout.in_time_mode, mActivity);
        super.mViewsId = new int[]{
                R.id.in_time_song1,
                R.id.in_time_song2,
                R.id.in_time_song3,
                R.id.score_text_view,
                R.id.chronometer,
                R.id.cover_art,
        };

        this.mRandomGenerator = new Random();
        this.mScore = 0;
        this.mTimer = new CountDownTimer(45000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                ((TextView)mViews.get(R.id.chronometer)).setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                ((TextView)mViews.get(R.id.chronometer)).setText("finito!");
                notifyObserver(Event.EndOfGame);
            }
        };
    }

    @Override
    public void update() {
        int a,b,c;

        switch (mRandomGenerator.nextInt(3)) {
            case 0: a = R.id.in_time_song1;  b = R.id.in_time_song2;    c = R.id.in_time_song3;  break;
            case 1: a = R.id.in_time_song3;  b = R.id.in_time_song1;    c = R.id.in_time_song2;  break;
            case 2: a = R.id.in_time_song2;  b = R.id.in_time_song3;    c = R.id.in_time_song1;  break;
            default:    a=0;    b=0;    c=0;    break; // only for compiler error
        }

        mySpotify.getCoverArt((ImageView) mViews.get(R.id.cover_art));

        ((Button)mViews.get(a)).setText(mySpotify.getCurrentTrackName());
        ((Button)mViews.get(b)).setText(mySpotify.getNextTrackName());
        ((Button)mViews.get(c)).setText(mySpotify.getPrevTrackName());


        ((TextView)mViews.get(R.id.score_text_view)).setText("Punteggio: " + mScore);

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
            Toast toast = Toast.makeText(mActivity.getApplicationContext(), "+10!", Toast.LENGTH_SHORT);
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
