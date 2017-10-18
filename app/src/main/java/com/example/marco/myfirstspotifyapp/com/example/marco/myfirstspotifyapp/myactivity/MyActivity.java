package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;


import android.app.Activity;
import android.transition.Scene;
import android.transition.Transition;
import android.view.ViewGroup;

public class MyActivity {

// VARIABLES
    private Scene mScene;
    private ActivityInterface mActivityInterface;


// CONSTRUCTOR
    public MyActivity(int ID, ActivityInterface lActivityInterface, ViewGroup lRootContainer, Activity lActivity) {
        mScene = Scene.getSceneForLayout(lRootContainer, ID, lActivity);
        this.mActivityInterface = lActivityInterface;
    }

    public Scene getScene() {
        return mScene;
    }

    public void registerObserver(Observer observer){
        mActivityInterface.registerObserver(observer);
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
