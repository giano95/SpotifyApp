package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;

import android.app.Activity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.marco.myfirstspotifyapp.ActivityFactory;
import com.example.marco.myfirstspotifyapp.ActivityType;
import com.example.marco.myfirstspotifyapp.Event;
import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.R;
import java.util.ArrayDeque;

public class MyActivityManager implements Observer{

// VARIABLES    
    private ViewGroup mRootContainer;                       // container of all the scene of the various activities
    private Activity mActivity;                             // android  main activity
    private Transition mTransition;                         // transitions used to switch from one scene to another
    private AbstractActivityUI mRunningActivity;            // the running activity
    private ArrayDeque<AbstractActivityUI> mActivities;     // stack of activities managed under LIFO protocol
    private ActivityFactory mFactory;

    
// CONSTRUCTOR    
    public MyActivityManager(ViewGroup rootContainer, Activity activity){

        this.mRootContainer = rootContainer;
        this.mActivity = activity;
        this.mActivities = new ArrayDeque<AbstractActivityUI>();
        this.mTransition = TransitionInflater.from(mActivity).inflateTransition(R.transition.transition_1);
        this.mFactory = new ActivityFactory();
    }


// METHOD USED TO INITIALIZE AND DISPLAY THE FIRST ACTIVITY
    public void start(MySpotify mySpotify){

        // we push the login activity to the stack
        //mActivities.addLast(new LoginUI(mActivity, mySpotify, mRootContainer));
        mActivities.addLast(mFactory.create(ActivityType.LoginUI, mActivity, mySpotify, mRootContainer));

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
    private void next(){

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
    public void back(int num){

        // we go back num times
        for(int i = 0; i < num; i++){
            mRunningActivity.onDestroy();

            // we pop the previous activity from the stack
            mActivities.pollLast();

            // we set the activity we just added as the current one
            mRunningActivity = mActivities.getLast();
        }

        TransitionManager.go(mRunningActivity.getScene(), mTransition);

        //initialize and update the current activity
        mRunningActivity.onCreate();
        mRunningActivity.update();
    }


// METHOD USED TO PROPERLY HANDLE THE BACK BUTTON EVENT
    public boolean isLoginActivity(){

        if(mRunningActivity instanceof LoginUI)
            return true;
        else
            return false;
    }

    public boolean isGameOverActivity(){
        if(mRunningActivity instanceof GameOverUI)
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
    public void onEventListener(ActivityType activityType, MySpotify mySpotify, Object object) {

        // we push the GameOver activity to the stack
        mActivities.addLast(new GameOverUI(mActivity, mySpotify, mRootContainer, (Integer) object));
        next();
    }

    @Override
    public void onEventListener(ActivityType activityType, MySpotify mySpotify) {

        // we push the next activity to the stack
        mActivities.addLast(mFactory.create(activityType, mActivity, mySpotify, mRootContainer));
        next();
    }
}
