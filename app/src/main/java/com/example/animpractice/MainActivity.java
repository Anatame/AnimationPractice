package com.example.animpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.TransitionManager;

import android.content.Context;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class MainActivity extends AppCompatActivity {

    Context context = this;
    Scene scene1;
    Scene scene2;
    Scene currentScene;
    Transition transition;
    TransitionSet transitionSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout cs = findViewById(R.id.rootConstraint);
        scene1 = Scene.getSceneForLayout(cs, R.layout.scene1, context);
        scene2 = Scene.getSceneForLayout(cs, R.layout.scene2, context);

        scene1.enter();
        currentScene = scene1;

//        transition = TransitionInflater.from(context).inflateTransition(R.transition.example2);

        ChangeBounds cb = new ChangeBounds();
        cb.setDuration(500);
        cb.setInterpolator(new LinearInterpolator());

        Fade fadeInTransition = new Fade();
        fadeInTransition.setMode(Fade.MODE_IN);
        fadeInTransition.setDuration(250);
        fadeInTransition.setStartDelay(400);
        fadeInTransition.addTarget(R.id.textView);

        Fade fadeOutTransition = new Fade();
        fadeOutTransition.setMode(Fade.MODE_OUT);
        fadeOutTransition.setDuration(50);
        fadeOutTransition.addTarget(R.id.textView);

        transitionSet = new TransitionSet();
        transitionSet.setOrdering(TransitionSet.ORDERING_TOGETHER);
        transitionSet.addTransition(cb);
        transitionSet.addTransition(fadeInTransition);
        transitionSet.addTransition(fadeOutTransition);

        cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(v);
            }
        });

    }

    public void start(View v){
        if(currentScene == scene1){
            TransitionManager.go(scene2, transitionSet);
            currentScene = scene2;
        } else{
            TransitionManager.go(scene1, transitionSet);
            currentScene = scene1;
        }
    }
}