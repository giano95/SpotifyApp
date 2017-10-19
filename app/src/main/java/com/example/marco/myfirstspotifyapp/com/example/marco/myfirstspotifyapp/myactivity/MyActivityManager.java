package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;

import android.app.Activity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.R;
import java.util.ArrayDeque;

public class MyActivityManager implements Observer{

// VARIABLES    
    private ViewGroup mRootContainer;               // container of all the scene of the various activities
    private Activity mActivity;                     // android  main activity
    private Transition mTransition;                 // transitions used to switch from one scene to another
    private MyActivity mRunningActivity;            // the running activity
    private ArrayDeque<MyActivity> mActivities;     // stack of activities managed under LIFO protocol

    
// CONSTRUCTOR    
    public MyActivityManager(ViewGroup rootContainer, Activity activity){

        this.mRootContainer = rootContainer;
        this.mActivity = activity;
        this.mActivities = new ArrayDeque<MyActivity>();
        this.mTransition = TransitionInflater.from(mActivity).inflateTransition(R.transition.transition_1);
    }


// METHOD USED TO INITIALIZE AND DISPLAY THE FIRST ACTIVITY
    public void start(MySpotify mySpotify){

        // we push the login activity to the stack
        mActivities.addLast(new MyActivity(
                R.layout.login,
                new LoginUI(mActivity, mySpotify),
                mRootContainer,
                mActivity));

        // we set the activity we just added as the current one
        mRunningActivity = mActivities.getLast();

        // we register this activity manager as an observer of the current activity
        mRunningActivity.registerObserver(this);

        // recall the enter methods of the current scene (USE ONLY ON THE FIRST SCENE OF THE ROOT CONTAINER)
        mRunningActivity.enter();

        //initialize and update the current activity
        mRunningActivity.onCreate();
        mRunningActivity.update();
    }


// METHOD USED TO MOVE TO THE NEXT ACTIVITY
    private void next(int nextId, AbstractActivityUI nextAbstractActivityUI){

        // we push the next activity to the stack
        mActivities.addLast(new MyActivity(
                nextId,
                nextAbstractActivityUI,
                mRootContainer,
                mActivity));

        // we set the activity we just added as the current one
        mRunningActivity = mActivities.getLast();

        // we register this activity manager as an observer of the current activity
        mRunningActivity.registerObserver(this);

        TransitionManager.go(mRunningActivity.getScene(), mTransition);

        //initialize and update the current activity
        mRunningActivity.onCreate();
        mRunningActivity.update();
    }


// METHOD USED TO MOVE TO THE PREVIOUS ACTIVITY
    public void back(){

        mRunningActivity.onDestroy();

        // we pop the previous activity from the stack
        mActivities.pollLast();

        // we set the activity we just added as the current one
        mRunningActivity = mActivities.getLast();

        TransitionManager.go(mRunningActivity.getScene(), mTransition);

        //initialize and update the current activity
        mRunningActivity.onCreate();
        mRunningActivity.update();
    }


// METHOD USED TO PROPERLY HANDLE THE BACK BUTTON EVENT
    public boolean isFirstActivity(){

        if(mActivities.size() <= 1)
            return true;
        else
            return false;
    }


// RECALL METHODS
    public void update(){

        // recalls the running activity update
        mRunningActivity.update();
    }


    // LISTENER METHODS
    @Override
    public void onEventListener() {
        Toast toast = Toast.makeText(mActivity.getApplicationContext(), "ehi zio guarda come ti implemento il pattern Observer", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onNextActivityListener(int nextId, AbstractActivityUI nextAbstractActivityUI) {
        next(nextId, nextAbstractActivityUI);
    }
}
