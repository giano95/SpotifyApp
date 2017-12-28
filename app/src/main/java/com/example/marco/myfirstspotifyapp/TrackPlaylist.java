package com.example.marco.myfirstspotifyapp;

import java.util.Random;

public class TrackPlaylist{

    private String mPlaylistUri;
    private int playlistLength;
    private boolean[] trackFlag;
    private Random randomGenerator;

    public TrackPlaylist(String playlistUri, int playlistLength){
        this.mPlaylistUri = playlistUri;
        this.playlistLength = playlistLength;
        this.trackFlag = new boolean[playlistLength];
        this.randomGenerator = new Random();

        // initialize trackFlag by setting all false (because no track has been played yet)
        reset(trackFlag);
    }

    public String getPlaylistUri(){
        return mPlaylistUri;
    }

    public int getRandomTrack(){

        // if all the artist has already been played, we reset the artistFlag in order to not end up in an infinite loop
        if(isArtistFull(trackFlag))
            reset(trackFlag);

        int randomInt = randomGenerator.nextInt(playlistLength);

        // as long as the selected track has already been played we take another track
        while(trackFlag[randomInt] == true || randomInt == 0 || randomInt == playlistLength - 1){
            randomInt = randomGenerator.nextInt(playlistLength);
        }

        // we set the track chosen as already played
        trackFlag[randomInt] = true;

        return randomInt;
    }

    private void reset(boolean[] trackFlag) {

        // set al the artist flag false
        for(int i = 0; i < playlistLength; i++){
            trackFlag[i] = false;
        }
    }


    private boolean isArtistFull(boolean[] trackFlag) {

        for(int i = 0; i < playlistLength; i++){
            if( trackFlag[i] == false)
                return false;
        }
        return true;
    }
}
