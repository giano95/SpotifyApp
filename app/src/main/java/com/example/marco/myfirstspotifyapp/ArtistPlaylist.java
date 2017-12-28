package com.example.marco.myfirstspotifyapp;


import java.util.Random;

public class ArtistPlaylist {

    private static final int NUM_ARTIST_PLAYLIST = 5;

    private TrackPlaylist[] playlists;
    private boolean[] artistFlag;
    private Random randomGenerator;
    private int currentArtist;

    public ArtistPlaylist(Genre genre) {

        this.playlists = new TrackPlaylist[NUM_ARTIST_PLAYLIST];
        this.artistFlag = new boolean[NUM_ARTIST_PLAYLIST];
        this.randomGenerator = new Random();

        switch(genre) {
            case Commercial:
                playlists[0] = new TrackPlaylist("spotify:artist:0C8ZW7ezQVs4URX5aX7Kqx", 50);
                playlists[1] = new TrackPlaylist("spotify:artist:246dkjvS1zLTtiykXe5h60", 50);
                playlists[2] = new TrackPlaylist("spotify:artist:64KEffDW9EtZ1y2vBYgq8T", 50);
                playlists[3] = new TrackPlaylist("spotify:artist:2YZyLoL8N0Wb9xBt1NhZWg", 50);
                playlists[4] = new TrackPlaylist("spotify:artist:6eUKZXaKkcviH0Ku9w2n3V", 50);
                break;
            case italian_90s_song:
            case Indie:
            case Pop:
            case Rock:
            case Metal:
            case Italian_Rap:
            case Dance:
            case Jazz:
        }

        // initialize artist flag by setting all false (because no artist has been played yet)
        reset(artistFlag);
    }

    public TrackPlaylist getRandomArtist() {

        // if all the artist has already been played, we reset the artistFlag in order to not end up in an infinite loop
        if(isArtistFull(artistFlag))
            reset(artistFlag);

        currentArtist = randomGenerator.nextInt(NUM_ARTIST_PLAYLIST);

        // as long as the selected artist has already been played we take another artist
        while(artistFlag[currentArtist] == true){
            currentArtist = randomGenerator.nextInt(NUM_ARTIST_PLAYLIST);
        }

        // we set the artist chosen as already played
        artistFlag[currentArtist] = true;

        return playlists[currentArtist];
    }

    private void reset(boolean[] artistFlag) {

        // set al the artist flag false
        for(int i = 0; i < NUM_ARTIST_PLAYLIST; i++){
            artistFlag[i] = false;
        }
    }


    private boolean isArtistFull(boolean[] artistFlag) {

        for(int i = 0; i < NUM_ARTIST_PLAYLIST; i++){
            if( artistFlag[i] == false)
                return false;
        }
        return true;
    }
}
