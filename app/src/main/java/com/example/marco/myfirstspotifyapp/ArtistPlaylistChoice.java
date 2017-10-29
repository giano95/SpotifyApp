package com.example.marco.myfirstspotifyapp;

import android.content.Context;

import java.util.HashMap;
import java.util.Random;

public class ArtistPlaylistChoice {
    Random randomGenerator;
    private boolean[] trackFlag;
    private boolean[] trackFlagControl;
    HashMap<Integer,String> italianArtists;
    HashMap<Integer,String> otherArtists;
    private String playlistReturn;
    private String type;

    public ArtistPlaylistChoice(String type) {
        randomGenerator = new Random();
        this.type=type;

        italianArtists = new HashMap<Integer,String>(){{
            put(0,"spotify:artist:5dXlc7MnpaTeUIsHLVe3n4");
            put(1,"spotify:artist:5l4nhyz8876RhgE2d18h65");
            //put(2,"spotify:artist:7H8ZC8uHJMPZGLMApRRNIz");
            // put(3,"spotify:artist:5mXMQJHLLfym1KyNcDrhoZ");
        }};


        otherArtists = new HashMap<Integer,String>(){{
            put(0,"spotify:artist:1uNFoZAHBGtllmzznpCI3s");
            put(1,"spotify:artist:0hCNtLu0JehylgoiP8L4Gh");
            // put(2,"spotify:artist:7H8ZC8uHJMPZGLMApRRNIz");
            // put(3,"spotify:artist:5mXMQJHLLfym1KyNcDrhoZ");
        }};
        trackFlagControl = new boolean[italianArtists.size()];
        trackFlag = new boolean[italianArtists.size()];
        istantiateArray();

    }




    public void istantiateArray(){

        for(int i = 0; i < italianArtists.size(); i++){
            trackFlagControl[i] = true;
        }

        for(int i = 0; i < italianArtists.size(); i++){
            trackFlag[i] = false;
        }

    }


    public String getRandomPlaylist(){
        if(type.equals("italian"))
            return getRandomPlaylistItalian();
        if(type.equals("other"))
            return getRandomPlaylistOther();
        else
            return "spotify:artist:5dXlc7MnpaTeUIsHLVe3n4"; //return a casual uri
    }



    public String getRandomPlaylistOther(){

        int randomInt = randomGenerator.nextInt(otherArtists.size());
        if(trackFlag.equals(trackFlagControl)){

            trackFlag=trackFlagControl.clone();
            // istantiateArray();
            // getRandomPlaylist();
        }
        // as long as the selected track has already been played we take another track
        while(trackFlag[randomInt] == true){
            randomInt = randomGenerator.nextInt(otherArtists.size());
        }


        // we set the track chosen as already played
        trackFlag[randomInt] = true;


        return otherArtists.get(randomInt);
    }




    public String getRandomPlaylistItalian(){

        int randomInt = randomGenerator.nextInt(italianArtists.size());

        if(trackFlag.equals(trackFlagControl)){

            //trackFlag=trackFlagControl.clone();
            // istantiateArray();
            // getRandomPlaylist();
        }

        // as long as the selected track has already been played we take another track
        while(trackFlag[randomInt] == true){
            randomInt = randomGenerator.nextInt(italianArtists.size());
        }


        // we set the track chosen as already played
        trackFlag[randomInt] = true;


        return italianArtists.get(randomInt);
    }
}
