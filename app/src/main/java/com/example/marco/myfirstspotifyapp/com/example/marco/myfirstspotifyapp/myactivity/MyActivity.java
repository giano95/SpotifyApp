package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;


import android.app.Activity;
import android.transition.Scene;
import android.transition.Transition;
import android.view.ViewGroup;

public class MyActivity {

// VARIABLES
    Scene mScene;
    ActivityInterface mActivityInterface;
    Transition mTransition;


    // CONSTRUCTOR
    public MyActivity(int ID, ViewGroup lRootContainer, Activity lActivity, ActivityInterface lActivityInterface, Transition lTransition) {
        mScene = Scene.getSceneForLayout(lRootContainer, ID, lActivity);
        this.mActivityInterface = lActivityInterface;
        this.mTransition = lTransition;
    }

    public Scene getScene() {
        return mScene;
    }

    public Transition getTransition() {
        return mTransition;
    }

    public int getNextId(int mode){
        return mActivityInterface.getNextId(mode);
    }

    public ActivityInterface getNextActivityInterface(int mode){
        return mActivityInterface.getNextInterface(mode);
    }

    public int getNextTransition(int mode){
        return mActivityInterface.getNextTransition(mode);
    }

    public void enter(){
        mScene.enter();
    }

    public void update(){
        mActivityInterface.update();
    }

    public void initButtons(){
        mActivityInterface.initButtons();
    }

    public void onBackEvent(){
        mActivityInterface.onBackEvent();
    }
}
