package com.example.marco.myfirstspotifyapp;


import android.app.Activity;
import android.view.ViewGroup;

import com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity.AbstractActivityUI;
import com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity.DifficultyChoiceUI;
import com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity.FindArtistNameEasyUI;
import com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity.FindArtistNameHardUI;
import com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity.FindTrackNameHardUI;
import com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity.GameModeChoiceUI;
import com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity.FindTrackNameEasyUI;
import com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity.LoginUI;
import com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity.PlaylistChoiceUI;

public class ActivityFactory {

    private ActivityType difficulty;
    private ActivityType mode;

    public  AbstractActivityUI create(ActivityType onSwitch, Activity activity, MySpotify mySpotify, ViewGroup rootContainer){

        switch(onSwitch){
            case LoginUI:               return new LoginUI(activity, mySpotify, rootContainer);
            case GameModeChoiceUI:      return new GameModeChoiceUI(activity, mySpotify, rootContainer);
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

