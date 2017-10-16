package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;

import android.app.Activity;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.R;
import java.util.ArrayDeque;

public class MyActivityManager {

    ViewGroup mRootContainer;
    Activity mActivity;
    ArrayDeque<MyActivity> Activitys;
    MyActivity current;

    public MyActivityManager(ViewGroup lRootContainer, Activity lActivity){
        this.mRootContainer = lRootContainer;
        this.mActivity = lActivity;
        Activitys = new ArrayDeque<MyActivity>();
    }

    public void begin(MySpotify mySpotify){
        Activitys.addLast(new MyActivity(R.layout.login,
                mRootContainer,
                mActivity,
                new LoginInterface(mActivity, mySpotify),
                TransitionInflater.from(mActivity).inflateTransition(R.transition.transition_1)));

        current = Activitys.getLast();

        current.enter();
        current.initButtons();
        current.update();
    }

    public void next(int mode){
        Activitys.addLast(new MyActivity(current.getNextId(mode),
                mRootContainer,
                mActivity,
                current.getNextActivityInterface(mode),
                TransitionInflater.from(mActivity).inflateTransition(current.getNextTransition(mode))));

        current = Activitys.getLast();

        TransitionManager.go(current.getScene(), current.getTransition());

        current.initButtons();
    }

    public void back(){
        current.onBackEvent();

        Activitys.pollLast();
        current = Activitys.getLast();

        TransitionManager.go(current.getScene(), current.getTransition());

        current.update();
    }

    public int getSize(){
        return Activitys.size();
    }

    public void initButtons(){
        current.initButtons();
    }

    public void update(){
        current.update();
    }
}
