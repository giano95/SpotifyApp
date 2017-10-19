package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import com.example.marco.myfirstspotifyapp.MySpotify;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class AbstractActivityUI implements Observable{


// VARIABLES
    protected int mNextID;
    protected int[] mViewsId;
    protected Activity mActivity;
    protected MySpotify mySpotify;
    protected HashMap<Integer, View> mViews;
    protected ArrayList<Observer> mObservers;
    protected AbstractActivityUI mNextAbstractActivityUI;

// CONSTRUCTOR
    public AbstractActivityUI(Activity activity, MySpotify mySpotify){
        this.mActivity = activity;
        this.mySpotify = mySpotify;
        this.mViews = new HashMap<Integer, View>();
        this.mObservers = new ArrayList<Observer>();
    }

// ABSTRACT METHODS
    public abstract void update();
    public abstract void onCreate();
    public abstract void onDestroy();


// USEFUL METHOD ONLY FOR THE CHILD
    protected void initViews() {
        for(int id: mViewsId){
            if(mActivity.findViewById(id) instanceof Button){
                mViews.put(id, (Button) mActivity.findViewById(id));
            }
            if(mActivity.findViewById(id) instanceof TextView){
                mViews.put(id, (TextView) mActivity.findViewById(id));
            }
            if(mActivity.findViewById(id) instanceof ImageView){
                mViews.put(id, (ImageView) mActivity.findViewById(id));
            }
            if(mActivity.findViewById(id) instanceof EditText){
                mViews.put(id, (EditText) mActivity.findViewById(id));
            }
        }
    }


// OBSERVER METHODS FOR THE CHILD LISTENING
    @Override
    public void registerObserver(Observer observer) {
        mObservers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if(mObservers.contains(observer))
            mObservers.remove(observer);
    }

    @Override
    public void notifyObserver(int event) {
        switch(event){
            case 0:
                for(Observer observer: mObservers){
                    observer.onEventListener();
                }
                break;
            case 1:
                for(Observer observer: mObservers){
                    observer.onNextActivityListener(mNextID, mNextAbstractActivityUI);
                }
                break;
            default:
                break;
        }
    }
}
