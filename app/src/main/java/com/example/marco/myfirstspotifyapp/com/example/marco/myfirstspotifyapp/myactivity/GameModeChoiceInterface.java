package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;

import android.app.Activity;

import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.R;


public class GameModeChoiceInterface extends ActivityInterface {

    public GameModeChoiceInterface(Activity mActivity, MySpotify mySpotify){
        super(mActivity,mySpotify);

        VIEWS_ID = new int[]{
                R.id.mode1,
                R.id.mode2,
                R.id.mode3,
                R.id.mode_textView,
        };
    }

    @Override
    public void update() {

    }

    @Override
    public void onBackEvent() {

    }

    @Override
    public int getNextId(int mode) {

        switch(mode){
            case 0: return R.layout.in_time_mode;
            case 1: // manu inserisci qui il layout della nuova modalità
            default:    return R.layout.in_time_mode;
        }
    }

    @Override
    public ActivityInterface getNextInterface(int mode) {

        switch(mode){
            case 0: return new InTimeInterface(mActivity, mySpotify);
            case 1: // manu inserisci qui il costruttore della nuova interfaccia della nuova modalità
            default:    return new InTimeInterface(mActivity, mySpotify);
        }
    }

    @Override
    public int getNextTransition(int mode) {

        switch(mode){
            case 0: return R.transition.transition_1;
            case 1: // manu inserisci qui la transizione della nuova interfaccia(opzionale)
            default:    return R.transition.transition_1;
        }
    }
}
