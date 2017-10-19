package com.example.marco.myfirstspotifyapp;

import java.util.Random;

public class Playlist {

    private String mPlaylistUri;
    private int playlistLength;
    private boolean[] trackFlag;
    private Random randomGenerator;

    public Playlist(String playlistUri, int playlistLength){
        this.mPlaylistUri = playlistUri;
        this.playlistLength = playlistLength;
        this.trackFlag = new boolean[playlistLength];
        this.randomGenerator = new Random();

        // initialize track flag (all false because no song has been played yet)
        for(int i = 0; i < playlistLength; i++){
            trackFlag[i] = false;
        }
    }

    public String getPlaylistUri(){
        return mPlaylistUri;
    }

    public int getRandomTrack(){

        int randomInt = randomGenerator.nextInt(playlistLength);

        // as long as the selected track has already been played we take another track
        while(trackFlag[randomInt] == true || randomInt == 0 || randomInt == playlistLength - 1){
            randomInt = randomGenerator.nextInt(playlistLength);
        }

        // we set the track chosen as already played
        trackFlag[randomInt] = true;

        return randomInt;
    }
}
