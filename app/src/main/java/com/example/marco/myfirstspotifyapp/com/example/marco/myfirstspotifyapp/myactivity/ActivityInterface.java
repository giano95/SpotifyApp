package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.marco.myfirstspotifyapp.MySpotify;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ActivityInterface implements Observable{


// VARIABLES
    protected Activity mActivity;
    protected MySpotify mySpotify;
    protected int[] VIEWS_ID;
    protected int mNextId;
    protected ActivityInterface mNextActivityInterface;
    protected HashMap<Integer, View> views;
    protected ArrayList<Observer> observers;

// CONSTRUCTOR
    public ActivityInterface(Activity mActivity, MySpotify mySpotify){
        this.mActivity = mActivity;
        this.mySpotify = mySpotify;
        views = new HashMap<Integer, View>();
        observers = new ArrayList<Observer>();
    }

// ABSTRACT METHODS
    public abstract void update();
    public abstract void onBackEvent();

// OTHER METHODS
    protected void initButtons() {
        for(int id: VIEWS_ID){
            if(mActivity.findViewById(id) instanceof Button){
                views.put(id, (Button) mActivity.findViewById(id));
            }
            if(mActivity.findViewById(id) instanceof TextView){
                views.put(id, (TextView) mActivity.findViewById(id));
            }
            if(mActivity.findViewById(id) instanceof ImageView){
                views.put(id, (ImageView) mActivity.findViewById(id));
            }
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if(observers.contains(observer))
            observers.remove(observer);
    }

    @Override
    public void notifyObserver(int event) {
        switch(event){
            case 0:
                for(Observer observer: observers){
                    observer.onEventListener();
                }
                break;
            case 1:
                for(Observer observer: observers){
                    observer.onNextActivityListener(mNextId, mNextActivityInterface);
                }
                break;
            default:
                break;
        }
    }
}
