package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;

import android.app.Activity;
import android.transition.Transition;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.marco.myfirstspotifyapp.MySpotify;
import java.util.HashMap;

public abstract class ActivityInterface {


// VARIABLES
    protected Activity mActivity;
    protected MySpotify mySpotify;
    protected HashMap<Integer, View> views;
    protected int[] VIEWS_ID;

// CONSTRUCTOR
    public ActivityInterface(Activity mActivity, MySpotify mySpotify){
        this.mActivity = mActivity;
        this.mySpotify = mySpotify;
        views = new HashMap<Integer, View>();
    }

// ABSTRACT METHODS
    public abstract void update();
    public abstract void onBackEvent();
    public abstract int getNextId(int mode);
    public abstract ActivityInterface getNextInterface(int mode);
    public abstract int getNextTransition(int mode);

// OTHER METHODS
    public void initButtons() {
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

}
