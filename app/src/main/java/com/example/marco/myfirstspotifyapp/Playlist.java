package com.example.marco.myfirstspotifyapp;

import java.util.Random;

public class Playlist {

    private String playlistUri;
    private int playlistLength;
    private boolean[] trackFlag;
    Random randomGenerator;

    public Playlist(String playlistUri, int playlistLength){
        this.playlistUri = playlistUri;
        this.playlistLength = playlistLength;
        trackFlag = new boolean[playlistLength];
        randomGenerator = new Random();

        // initialize track flag (all false because no song has been played yet)
        for(int i = 0; i < playlistLength; i++){
            trackFlag[i] = false;
        }
    }

    public String getPlaylistUri(){
        return playlistUri;
    }

    public int getRandomTrack(){

        int randomInt = randomGenerator.nextInt(playlistLength);

        // as long as the selected track has already been played we take another track
        while(trackFlag[randomInt] == true){
            randomInt = randomGenerator.nextInt(playlistLength);
        }

        // we set the track chosen as already played
        trackFlag[randomInt] = true;

        return randomInt;
    }
}
