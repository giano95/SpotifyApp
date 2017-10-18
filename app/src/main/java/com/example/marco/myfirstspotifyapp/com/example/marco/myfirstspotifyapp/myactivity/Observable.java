package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;

public interface Observable {
    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObserver(int event);
}
