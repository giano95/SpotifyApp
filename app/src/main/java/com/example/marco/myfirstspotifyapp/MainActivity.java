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

// VARIABLES
    private MyActivityManager myActivityManager;    // used to manage the virtual activities
    private MySpotify mySpotify;                    // used to handle the Spotify Android SDK


// ANDROID ACTIVITY METHODS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate the objects
        mySpotify = new MySpotify(this, MainActivity.this);
        myActivityManager = new MyActivityManager((ViewGroup) findViewById(R.id.rootContainer), this);

        // the manager initializes the first virtual activity and displays it
        myActivityManager.begin(mySpotify);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        // after the login window has been opened, we handle the user authentication on the Spotify system
        mySpotify.authentication(requestCode, resultCode,intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // we handle the resume event by recording possibe network change
        mySpotify.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // we handle the pause event by destroying the network change receiver
        mySpotify.onPause();
    }

    @Override
    protected void onDestroy() {
        // we handle the destory event by also destroyng all the Spotify stuff
        mySpotify.destroy();
        super.onDestroy();
    }


// ANDROID FRAGMENT METHODS
    @Override
    public void onBackPressed() {
        if(myActivityManager.getSize() > 1){
            // handle the backButton pressed event by return to the last activity
            myActivityManager.back();
        } else {
            // if it is the login activity then we use native backButton functionality
            super.onBackPressed();
        }
    }


// METHODS USED TO CATCH SPOTIFY EVENTs
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

    @Override
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
            // TODO: implement a error handler
            default:
                break;
        }
    }
}