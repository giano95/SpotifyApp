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

import com.example.marco.myfirstspotifyapp.Event;
import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.R;


public class FindTrackNameHardUI extends AbstractActivityUI {

    private int mSkipCount;
    private CountDownTimer mTimer;
    private Integer mScore;

    public FindTrackNameHardUI(Activity activity, MySpotify mySpotify , ViewGroup rootContainer) {
        super(activity, mySpotify, rootContainer);
        super.mScene = Scene.getSceneForLayout(mRootContainer, R.layout.find_track_name_hard, mActivity);
        super.mViewsId = new int[]{
                R.id.find_name_songs,
                R.id.find_name_skip,
                R.id.edit_name,
                R.id.ftnh_score,
                R.id.ftnh_chronometer,
        };
        this.mSkipCount = 3;
        this.mScore = 0;
        this.mTimer = new CountDownTimer(45000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                ((TextView)mViews.get(R.id.ftnh_chronometer)).setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                ((TextView)mViews.get(R.id.ftnh_chronometer)).setText("finito!");
                notifyObserver(Event.EndOfGame);
            }
        };
    }

    View.OnClickListener  onSkipPressed = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(mSkipCount >0) {
                mySpotify.playRandomSong();
                mSkipCount--;
            }

            //this method could help to found the song
            if(mSkipCount ==0){
                ((Button)mViews.get(R.id.find_name_skip)).setText("Skip finiti");
                ((Button)mViews.get(R.id.find_name_skip)).setEnabled(false);

            }
        }
    };


    View.OnClickListener checkTheName = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = (mySpotify.getCurrentTrackName());
            String name2 = new String ((((EditText)mViews.get(R.id.edit_name)).getText()).toString());


            if(name.equals(name2)){
                mScore += 10;
                ((EditText)mViews.get(R.id.edit_name)).setText("");
                mySpotify.playRandomSong();

            } else {
                Toast toast = Toast.makeText(mActivity.getApplicationContext(), "nome sbagliato", Toast.LENGTH_SHORT);
                toast.show();

                mScore -= 10;
                update();
            }
        }
    };




    @Override
    public void update() {

        ((TextView)mViews.get(R.id.ftnh_score)).setText("Punteggio: " + mScore);
    }

    @Override
    public void onCreate() {
        super.initViews();
        mTimer.start();
        ((Button)mViews.get(R.id.find_name_skip)).setOnClickListener(onSkipPressed);
        ((Button)mViews.get(R.id.find_name_songs)).setOnClickListener(checkTheName);
        mySpotify.playRandomSong();
    }

    @Override
    public void onDestroy() {
        mySpotify.pause();
        mTimer.cancel();
    }
}
