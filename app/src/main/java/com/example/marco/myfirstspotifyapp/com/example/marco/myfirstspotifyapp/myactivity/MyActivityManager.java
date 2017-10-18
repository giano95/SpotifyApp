package com.example.marco.myfirstspotifyapp.com.example.marco.myfirstspotifyapp.myactivity;

import android.app.Activity;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.marco.myfirstspotifyapp.MySpotify;
import com.example.marco.myfirstspotifyapp.R;
import java.util.ArrayDeque;

public class MyActivityManager implements Observer{

    private ViewGroup mRootContainer;
    private Activity mActivity;
    private Transition transition;
    private MyActivity current;
    private ArrayDeque<MyActivity> Activitys;

    public MyActivityManager(ViewGroup lRootContainer, Activity lActivity){
        this.mRootContainer = lRootContainer;
        this.mActivity = lActivity;
        Activitys = new ArrayDeque<MyActivity>();
        transition = TransitionInflater.from(mActivity).inflateTransition(R.transition.transition_1);
    }

    public void begin(MySpotify mySpotify){
        Activitys.addLast(new MyActivity(
                R.layout.login,
                new LoginInterface(mActivity, mySpotify),
                mRootContainer,
                mActivity));


        current = Activitys.getLast();
        current.registerObserver(this);

        current.enter();
        current.initButtons();
        current.update();
    }

    public void back(){
        current.onBackEvent();

        Activitys.pollLast();
        current = Activitys.getLast();

        TransitionManager.go(current.getScene(), transition);

        current.initButtons();
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

    @Override
    public void onEventListener() {
        Toast toast = Toast.makeText(mActivity.getApplicationContext(), "ehi zio guarda come ti implemento il pattern Observer", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onNextActivityListener(int nextId, ActivityInterface nextActivityInterface) {
        Activitys.addLast(new MyActivity(
                nextId,
                nextActivityInterface,
                mRootContainer,
                mActivity));

        current = Activitys.getLast();

        current.registerObserver(this);

        TransitionManager.go(current.getScene(), transition);

        current.initButtons();
    }
}
