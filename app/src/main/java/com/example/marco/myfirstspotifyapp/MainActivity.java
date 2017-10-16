package com.example.marco.myfirstspotifyapp;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity.MyActivityManager;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.SpotifyPlayer;


public class MainActivity extends AppCompatActivity implements SpotifyPlayer.NotificationCallback, ConnectionStateCallback
{
    MyActivityManager myActivityManager;
    MySpotify mySpotify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySpotify = new MySpotify(this, MainActivity.this);
        myActivityManager = new MyActivityManager((ViewGroup) findViewById(R.id.rootContainer), this);

        myActivityManager.begin(mySpotify);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mySpotify.authentication(requestCode, resultCode,intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mySpotify.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mySpotify.onPause();
    }

    @Override
    public void onBackPressed() {
        if(myActivityManager.getSize() > 1){
            myActivityManager.back();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        mySpotify.destroy();
        super.onDestroy();
    }

    public void onNextPressed(View view){
        myActivityManager.next();
    }


    @Override
    public void onLoggedIn() {
        Util.logStatus("Login complete");
        myActivityManager.update();
    }

    @Override
    public void onLoggedOut() {
        Util.logStatus("Logout complete");
        myActivityManager.update();
    }

    public void onLoginFailed(Error error) {
        Util.logStatus("Login error "+ error);
    }

    @Override
    public void onTemporaryError() {
        Util.logStatus("Temporary error occurred");
    }

    @Override
    public void onConnectionMessage(final String message) {
        Util.logStatus("Incoming connection message: " + message);
    }

    @Override
    public void onPlaybackEvent(PlayerEvent event) {
        Util.logStatus("Playback event received: " + event.name());
        switch (event) {
            case kSpPlaybackNotifyPlay:
                // Playback has started or has resumed
            case kSpPlaybackNotifyTrackChanged:
                // The current track or its metadata has changed This event occurs when playback of a new/different track starts.
            case kSpPlaybackNotifyMetadataChanged:
                // Metadata is changed This event occurs when playback starts or changes to a different context, when a track switch occurs, etc.
                mySpotify.updatePlaybackState();
                mySpotify.updateMetadata();
                myActivityManager.update();
                break;
            default:
                break;
        }
    }

    @Override
    public void onPlaybackError(Error error) {
        Util.logStatus("Playback error received: " + error.name());
        switch (error) {
            // Handle error type as necessary
            default:
                break;
        }
    }

}