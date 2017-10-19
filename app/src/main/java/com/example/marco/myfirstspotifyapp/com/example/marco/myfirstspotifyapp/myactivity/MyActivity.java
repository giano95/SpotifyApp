package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;


import android.app.Activity;
import android.transition.Scene;
import android.view.ViewGroup;

import java.io.Serializable;

public class MyActivity {

// VARIABLES
    private Scene mScene;
    private AbstractActivityUI mAbstractActivityUI;


// CONSTRUCTOR
    public MyActivity(int layoutID, AbstractActivityUI abstractActivityUI, ViewGroup rootContainer, Activity activity) {
        this.mScene = Scene.getSceneForLayout(rootContainer, layoutID, activity);
        this.mAbstractActivityUI = abstractActivityUI;
    }

    public Scene getScene() {
        return mScene;
    }

    public void registerObserver(Observer observer){
        mAbstractActivityUI.registerObserver(observer);
    }

    public void enter(){
        mScene.enter();
    }

    public void update(){
        mAbstractActivityUI.update();
    }

    public void onCreate(){
        mAbstractActivityUI.onCreate();
    }

    public void onDestroy(){
        mAbstractActivityUI.onDestroy();
    }
}
