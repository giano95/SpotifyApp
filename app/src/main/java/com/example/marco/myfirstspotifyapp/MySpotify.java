package com.example.marco.myfirstspotifyapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.ImageView;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Connectivity;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.PlaybackState;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;
import com.spotify.sdk.android.player.Metadata;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;


public final class MySpotify{

// CONSTANTS
    private static final int REQUEST_CODE = 1337;
    private static final String CLIENT_ID = "d94d1735c863475294f4f5d99bb63dd9";
    private static final String REDIRECT_URI = "my-first-spotify-app://callback";


// VARIABLES
    private Activity mActivity;
    private Context mContext;
    private SpotifyPlayer mPlayer;
    private Metadata mMetadata;
    private PlaybackState mCurrentPlaybackState;
    private BroadcastReceiver mNetworkStateReceiver;
    private Playlist mPlaylist;


// CONSTRUCTOR
    public MySpotify(Activity mActivity, Context mContext){
        this.mActivity = mActivity;
        this.mContext = mContext;
    }


//PRIVATE METHODS USED INSIDE MYSPOTIFY
    private Connectivity getNetworkConnectivity(Context context) {
        ConnectivityManager connectivityManager;
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return Connectivity.fromNetworkType(activeNetwork.getType());
        } else {
            return Connectivity.OFFLINE;
        }
    }

    private final Player.OperationCallback mOperationCallback = new Player.OperationCallback() {
        @Override
        public void onSuccess() {
            Util.logStatus("OK!");
        }

        @Override
        public void onError(Error error) {
            Util.logStatus("ERROR:" + error);
        }
    };


// LOGIN METHODS
    public void openLoginWindow() {
        final AuthenticationRequest request = new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)
                .setScopes(new String[]{"user-read-private", "playlist-read", "playlist-read-private", "streaming"})
                .build();

        AuthenticationClient.openLoginActivity(mActivity, REQUEST_CODE, request);
    }


// AUTHENTICATION METHODS
    public void authentication(int requestCode, int resultCode, Intent intent){
        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
                    onAuthenticationComplete(response);
                    break;
                // Auth flow returned an error
                case ERROR:
                    Util.logStatus("Auth error: " + response.getError());
                    break;
                // Most likely auth flow was cancelled
                default:
                    Util.logStatus("Auth result: " + response.getType());
            }
        }
    }

    private void onAuthenticationComplete(AuthenticationResponse authResponse) {
        // Once we have obtained an authorization token, we can proceed with creating a Player.
        Util.logStatus("Got authentication token");
        if (mPlayer == null) {
            Config playerConfig = new Config(mActivity.getApplicationContext(), authResponse.getAccessToken(), CLIENT_ID);
            // Since the Player is a static singleton owned by the Spotify class, we pass "this" as
            // the second argument in order to refcount it properly.
            mPlayer = Spotify.getPlayer(playerConfig, mActivity, new SpotifyPlayer.InitializationObserver() {
                @Override
                public void onInitialized(SpotifyPlayer player) {
                    Util.logStatus("-- Player initialized --");
                    mPlayer.setConnectivityStatus(mOperationCallback, getNetworkConnectivity(mContext));
                    mPlayer.addNotificationCallback((Player.NotificationCallback) mContext);
                    mPlayer.addConnectionStateCallback((ConnectionStateCallback) mContext);
                }

                @Override
                public void onError(Throwable error) {
                    Util.logStatus("Error in initialization: " + error.getMessage());
                }
            });
        } else {
            mPlayer.login(authResponse.getAccessToken());
        }
    }


// RESUME METHODS
    public void onResume(){
        // Set up the broadcast receiver for network events. Note that we also unregister
        // this receiver again in onPause().
        mNetworkStateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (mPlayer != null) {
                    Connectivity connectivity = getNetworkConnectivity(mActivity.getBaseContext());
                    Util.logStatus("Network state changed: " + connectivity.toString());
                    mPlayer.setConnectivityStatus(mOperationCallback, connectivity);
                }
            }
        };

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        mActivity.registerReceiver(mNetworkStateReceiver, filter);

        if (mPlayer != null) {
            mPlayer.addNotificationCallback((Player.NotificationCallback) mContext);
            mPlayer.addConnectionStateCallback((ConnectionStateCallback) mContext);
            resume();
        }
    }


// PAUSE METHODS
    public void onPause(){
        mActivity.unregisterReceiver(mNetworkStateReceiver);

        // Note that calling Spotify.destroyPlayer() will also remove any callbacks on whatever
        // instance was passed as the refcounted owner. So in the case of this particular example,
        // it's not strictly necessary to call these methods, however it is generally good practice
        // and also will prevent your application from doing extra work in the background when
        // paused.
        if (mPlayer != null) {
            mPlayer.addNotificationCallback((Player.NotificationCallback) mContext);
            mPlayer.addConnectionStateCallback((ConnectionStateCallback) mContext);
            pause();
        }
    }


// DESTROY METHODS
    public void destroy(){
        Spotify.destroyPlayer(this);
    }


// GETTER METHODS
    public String getCurrentTrackName(){
        if(mMetadata != null && mMetadata.currentTrack != null)
            return mMetadata.currentTrack.name;
        else
            return "no_name_found";
    }

    public String getNextTrackName(){
        if(mMetadata != null && mMetadata.nextTrack != null)
            return mMetadata.nextTrack.name;
        else
            return "no_name_found";
    }

    public String getPrevTrackName(){
        if(mMetadata != null && mMetadata.prevTrack != null)
            return mMetadata.prevTrack.name;
        else
            return "no_name_found";
    }

    public String getCurrentTrackArtist(){
        if(mMetadata != null && mMetadata.currentTrack != null)
            return mMetadata.currentTrack.artistName;
        else
            return "no_artist_found";
    }

    public String getNextTrackArtist(){
        if(mMetadata != null && mMetadata.nextTrack != null)
            return mMetadata.nextTrack.artistName;
        else
            return "no_name_found";
    }

    public String getPrevTrackArtist(){
        if(mMetadata != null && mMetadata.prevTrack != null)
            return mMetadata.prevTrack.artistName;
        else
            return "no_name_found";
    }

    public void getCoverArt(ImageView coverArtView){
        if (mMetadata != null && mMetadata.currentTrack != null) {

            Picasso.with(mActivity)
                    .load(mMetadata.currentTrack.albumCoverWebUrl)
                    .transform(new Transformation() {
                        @Override
                        public Bitmap transform(Bitmap source) {
                            // really ugly darkening trick
                            final Bitmap copy = source.copy(source.getConfig(), true);
                            source.recycle();
                            final Canvas canvas = new Canvas(copy);
                            return copy;
                        }

                        @Override
                        public String key() {
                            return "darken";
                        }
                    })
                    .into(coverArtView);
        } else {
            coverArtView.setBackground(null);
        }
    }


// USEFUL METHODS
    public void setPlaylist(String playlistUri, int playlistLength){
        mPlaylist = new Playlist(playlistUri, playlistLength);
    }

    public void setPlaylist(Playlist playlist){
        mPlaylist = playlist;
    }

    public void playRandomSong(){
        mPlayer.playUri(mOperationCallback, mPlaylist.getPlaylistUri(), mPlaylist.getRandomTrack(), 0);
    }

    public void skipToNext(){
        mPlayer.skipToNext(mOperationCallback);
    }

    public void skipToPrev(){
        mPlayer.skipToPrevious(mOperationCallback);
    }

    public void resume(){
        mPlayer.resume(mOperationCallback);
    }

    public void pause(){
        mPlayer.pause(mOperationCallback);
    }

    public boolean isLoggedIn() {
        return (mPlayer != null && mPlayer.isLoggedIn());
    }

    public void logout(){
        mPlayer.logout();
    }

    public void updatePlaybackState(){
        mCurrentPlaybackState = mPlayer.getPlaybackState();
    }

    public void updateMetadata(){
        mMetadata = mPlayer.getMetadata();
    }



}
