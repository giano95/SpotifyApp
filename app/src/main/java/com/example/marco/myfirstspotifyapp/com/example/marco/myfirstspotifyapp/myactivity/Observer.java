package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;


import com.example.marco.myfirstspotifyapp.ActivityType;
import com.example.marco.myfirstspotifyapp.Event;
import com.example.marco.myfirstspotifyapp.MySpotify;

public interface Observer {
    public void onEventListener(ActivityType activityType, MySpotify mySpotify, Event event);
}
