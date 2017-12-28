package com.example.marco.myfirstspotifyapp;


import android.app.Activity;
import android.view.ViewGroup;

import com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity.*;


public class ActivityFactory {


    private static ActivityFactory instance;
    public ActivityType difficulty;
    public ActivityType mode;

    private ActivityFactory(){}

    public static ActivityFactory init() {

        if( instance == null) {
            instance = new ActivityFactory();
        }
        return instance;
    }

    public static ActivityFactory getInstance() {
        return instance;
    }


    public  AbstractActivityUI create(ActivityType onSwitch, Activity activity, MySpotify mySpotify, ViewGroup rootContainer){

        switch(onSwitch){
            case LoginUI:               return new LoginUI(activity, mySpotify, rootContainer);
            case GameModeChoiceUI:      return new GameModeChoiceUI(activity, mySpotify, rootContainer);
            case ShowBestScore:         return new ShowBestScoreUI(activity, mySpotify, rootContainer);
            case findTrackName:
                mode = ActivityType.findTrackName;
                return new DifficultyChoiceUI(activity, mySpotify, rootContainer);
            case findArtistName:
                mode = ActivityType.findArtistName;
                return new DifficultyChoiceUI(activity, mySpotify, rootContainer);
            case easy:
                difficulty = ActivityType.easy;
                return new PlaylistChoiceUI(activity, mySpotify, rootContainer);
            case hard:
                difficulty = ActivityType.hard;
                return new PlaylistChoiceUI(activity, mySpotify, rootContainer);
            case GameUI:
                switch(mode){
                    case findTrackName:
                        switch(difficulty){
                            case easy:      return new FindTrackNameEasyUI(activity, mySpotify, rootContainer);
                            case hard:      return new FindTrackNameHardUI(activity, mySpotify, rootContainer);
                        }
                    case findArtistName:
                        switch(difficulty){
                            case easy:      return new FindArtistNameEasyUI(activity, mySpotify, rootContainer);
                            case hard:      return new FindArtistNameHardUI(activity, mySpotify, rootContainer);
                        }
                }
            default:
                // only for the compiler error
                return null;
        }
    }
}

