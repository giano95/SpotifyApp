package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;


import android.app.Activity;
import android.os.CountDownTimer;
import android.transition.Scene;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marco.myfirstspotifyapp.Event;
import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.R;
import com.example.marco.myfirstspotifyapp.StringHandler;


public class FindArtistNameHardUI extends AbstractActivityUI {

    private int mSkipCount;
    private CountDownTimer mTimer;
    private Integer mScore;
    private String mCurrentName;
    private long countDown;

    public FindArtistNameHardUI(Activity activity, MySpotify mySpotify, ViewGroup rootContainer) {
        super(activity, mySpotify, rootContainer);
        super.mScene = Scene.getSceneForLayout(mRootContainer, R.layout.hard_mode, mActivity);
        super.mViewsId = new int[]{
                R.id.tips_bar_textview,
                R.id.edit_name_edittext,
                R.id.enter_button,
                R.id.skip_button,
                R.id.score_hard_textview,
                R.id.chronometer_hard_textview,
        };

        this.mSkipCount = 3;
        this.mScore = 0;

        createTimer(30000);
    }

    View.OnClickListener  onSkipButtonPressed = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(mSkipCount >0) {
                mySpotify.playRandomSong();
                mSkipCount--;
            }

            //this method could help to found the song
            if(mSkipCount == 0){
                ((Button)mViews.get(R.id.skip_button)).setText(R.string.skip_finished_button);
                ((Button)mViews.get(R.id.skip_button)).setEnabled(false);
            }
        }
    };


    View.OnClickListener onEnterButtonPressed = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String editName = new String ((((EditText)mViews.get(R.id.edit_name_edittext)).getText()).toString());

            if(StringHandler.compare(mCurrentName, editName)){
                mScore += 10;

                ((EditText)mViews.get(R.id.edit_name_edittext)).setText("");

                // add 10 seconds on the countDown and set the timer
                countDown += 10000;
                setTimer(countDown);

                mySpotify.nextArtist();
                mySpotify.playRandomSong();

            } else {
                Toast toast = Toast.makeText(mActivity.getApplicationContext(), R.string.wrong_name_toast, Toast.LENGTH_SHORT);
                toast.show();

                mScore -= 10;
                ((TextView)mViews.get(R.id.score_hard_textview)).setText(mActivity.getString(R.string.score_textview) + " " + mScore);
            }
        }
    };




    @Override
    public void update() {

        mCurrentName = StringHandler.setProperly(mySpotify.getCurrentTrackArtist());

        ((TextView)mViews.get(R.id.score_hard_textview)).setText(mActivity.getString(R.string.score_textview) + " " + mScore);
    }

    @Override
    public void onCreate() {
        super.initViews();
        ((EditText)mViews.get(R.id.edit_name_edittext)).setHint(R.string.edit_name_track_edittext);
        ((Button)mViews.get(R.id.skip_button)).setOnClickListener(onSkipButtonPressed);
        ((Button)mViews.get(R.id.enter_button)).setOnClickListener(onEnterButtonPressed);

        ((ViewManager)mViews.get(R.id.tips_bar_textview).getParent()).removeView(mViews.get(R.id.tips_bar_textview));

        mTimer.start();
        mySpotify.playRandomSong();
    }

    @Override
    public void onDestroy() {
        mySpotify.pause();
        mTimer.cancel();
    }

    private void setTimer(long countDownTime) {
        mTimer.cancel();
        createTimer(countDownTime);
        mTimer.start();
    }

    private void createTimer(long countDownTime) {

        mTimer = new CountDownTimer(countDownTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                ((TextView)mViews.get(R.id.chronometer_hard_textview)).setText("" + millisUntilFinished / 1000);

                countDown = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                ((TextView)mViews.get(R.id.chronometer_hard_textview)).setText("finito!");
                notifyObserver(Event.EndOfGame, new Integer(mScore));
            }
        };
    }
}