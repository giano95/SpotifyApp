package com.example.marco.myfirstspotifyapp;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.view.ViewGroup;

import com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity.MyActivity;
import com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity.MyActivityManager;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.SpotifyPlayer;


public class MainActivity extends Activity implements SpotifyPlayer.NotificationCallback, ConnectionStateCallback
{

// VARIABLES
    private MyActivityManager myActivityManager;    // used to manage the virtual activities
    private MySpotify mySpotify;                    // used to handle the Spotify Android SDK


// ANDROID ACTIVITY METHODS
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // we call the father method and we set the root container background
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate the objects
        mySpotify = new MySpotify(this, MainActivity.this);
        myActivityManager = new MyActivityManager((ViewGroup) findViewById(R.id.rootContainer), this);

        // the manager initializes the first virtual activity and displays it
        myActivityManager.start(mySpotify);


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

        // we handle the resume event by recording possible network change
        mySpotify.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // we handle the pause event by destroying the network change receiver
        mySpotify.onPause();
        myActivityManager.update();
    }

    @Override
    protected void onDestroy() {

        // we handle the destory event by also destroyng all the Spotify stuff
        mySpotify.destroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

        if(myActivityManager.isFirstActivity()) {
            // if it is the first activity (login) we use native backButton functionality
            super.onBackPressed();
        } else {
            // else we handle the backButton pressed event by returning into the last activity
            myActivityManager.back();
        }
    }


// METHODS USED TO CATCH SPOTIFY EVENTs
    @Override
    public void onLoggedIn() {
        Util.logStatus("Login complete");

        // if the user logged in the Spotify system we have to update the current activity
        myActivityManager.update();
    }

    @Override
    public void onLoggedOut() {
        Util.logStatus("Logout complete");

        // if the user logged out the Spotify system we have to update the current activity
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

        // we handle the Spotify playback event properly
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

        // TODO: implement a error handler
        switch (error) {
            default:
                break;
        }
    }
}