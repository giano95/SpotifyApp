package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;


import android.app.Activity;
import android.transition.Transition;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.R;
import java.util.Random;

public class InTimeInterface extends ActivityInterface {

    private Random mRandomGenerator;
    private Integer mScore;

    public InTimeInterface(Activity mActivity, MySpotify mySpotify){
        super(mActivity,mySpotify);
        mRandomGenerator = new Random();
        mScore = 0;
        VIEWS_ID = new int[]{
                R.id.in_time_song1,
                R.id.in_time_song2,
                R.id.in_time_song3,
                R.id.score_text_view,
                R.id.chronometer,
                R.id.cover_art,
        };
    }

    @Override
    public void update() {
        int a,b,c;

        switch (mRandomGenerator.nextInt(3)) {
            case 0: a = R.id.in_time_song1;  b = R.id.in_time_song2;    c = R.id.in_time_song3;  break;
            case 1: a = R.id.in_time_song3;  b = R.id.in_time_song1;    c = R.id.in_time_song2;  break;
            case 2: a = R.id.in_time_song2;  b = R.id.in_time_song3;    c = R.id.in_time_song1;  break;
            default:    a=0;    b=0;    c=0;    break; // default case only for compiler error
        }

        ((Button)views.get(a)).setText(mySpotify.getCurrentTrackName());
        ((Button)views.get(b)).setText(mySpotify.getNextTrackName());
        ((Button)views.get(c)).setText(mySpotify.getPrevTrackName());


        ((TextView)views.get(R.id.score_text_view)).setText("Punteggio: " + mScore);

        ((Button)views.get(a)).setOnClickListener(RightSong);
        ((Button)views.get(b)).setOnClickListener(WrongSong);
        ((Button)views.get(c)).setOnClickListener(WrongSong);

        mySpotify.getCoverArt((ImageView) views.get(R.id.cover_art));
    }

    @Override
    public void onBackEvent() {
        mySpotify.pause();
    }

    @Override
    public int getNextId() {
        return 0;
    }

    @Override
    public ActivityInterface getNextInterface() {
        return null;
    }

    @Override
    public int getNextTransition() {
        return 0;
    }

    @Override
    public void initButtons() {
        super.initButtons();
        mySpotify.playRandomSong();
    }


    View.OnClickListener RightSong = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mScore += 10;
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
