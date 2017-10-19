package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.R;

import java.util.HashMap;
import java.util.Random;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marco.myfirstspotifyapp.MainActivity;
import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.R;
import com.example.marco.myfirstspotifyapp.Util;

import java.util.HashMap;
import java.util.Random;

import static android.R.attr.duration;

public class FindArtistUI extends AbstractActivityUI {
    Random randomGenerator;
    private boolean[] trackFlag;
    HashMap<Integer,String> songs;
    Context context;
    private int numero_skip=3;

    //  Toast toast=  Toast.makeText(context, "Artista sbagliato", Toast.LENGTH_SHORT);


    public FindArtistUI(Activity mActivity, MySpotify mySpotify) {
        super(mActivity, mySpotify);
        randomGenerator = new Random();
        //Context c;

        mViewsId = new int[]{

                R.id.find_singer_chronometer,
                R.id.find_singer_song1,
                R.id.find_singer_skip,
                R.id.singer_name,

        };

        songs = new HashMap<Integer,String>(){{
            put(0,"spotify:artist:5dXlc7MnpaTeUIsHLVe3n4");
            put(1,"spotify:artist:5l4nhyz8876RhgE2d18h65");
            put(2,"spotify:artist:7H8ZC8uHJMPZGLMApRRNIz");
            put(3,"spotify:artist:5mXMQJHLLfym1KyNcDrhoZ");
        }};

        trackFlag = new boolean[songs.size()];
        for(int i = 0; i < songs.size(); i++){
            trackFlag[i] = false;
        }

    }
    View.OnClickListener  onSkipPressed = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(numero_skip>0) {
                mySpotify.playRandomSong();
                numero_skip--;
            }
            if(numero_skip==0){
                ((Button)mViews.get(R.id.find_singer_skip)).setText("Skip finiti");

            }
        }
    };

    View.OnClickListener checkTheName = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = (mySpotify.getCurrentTrackArtist());
            String name2 = new String ((((EditText)mViews.get(R.id.singer_name)).getText()).toString());


            if(name.equals(name2)){
                mySpotify.setPlaylist(songs.get(getRandomPlaylist()), 15);
                mySpotify.playRandomSong();
            }
            else {
                //toast.show();
            }
        }
    };

    public int getRandomPlaylist(){

        int randomInt = randomGenerator.nextInt(songs.size());

        // as long as the selected track has already been played we take another track
        while(trackFlag[randomInt] == true){
            randomInt = randomGenerator.nextInt(songs.size());
        }

        // we set the track chosen as already played
        trackFlag[randomInt] = true;

        return randomInt;
    }

    @Override
    public void onCreate() {
        super.initViews();

        mySpotify.setPlaylist(songs.get(getRandomPlaylist()), 15);
        mySpotify.playRandomSong();
        //   mTimer.start();   gestito nell'interfaccia
    }

    @Override
    public void update() {
        ((Button)mViews.get(R.id.find_singer_skip)).setOnClickListener(onSkipPressed);
        ((Button)mViews.get(R.id.find_singer_song1)).setOnClickListener(checkTheName);
    }

    @Override
    public void onDestroy() {
        mySpotify.pause();
    }
}