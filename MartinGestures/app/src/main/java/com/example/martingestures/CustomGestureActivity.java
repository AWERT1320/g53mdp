package com.example.martingestures;


import java.util.ArrayList;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CustomGestureActivity extends Activity implements OnGesturePerformedListener {

    private GestureLibrary gestureLib;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("g53mdp", "CustomGestureActivity");

        GestureOverlayView gestureOverlayView = new GestureOverlayView(this);

        View inflate = getLayoutInflater().inflate(R.layout.custom_main, null);

        gestureOverlayView.addView(inflate);
        gestureOverlayView.addOnGesturePerformedListener(this);

        gestureLib = GestureLibraries.fromFile(Environment.getExternalStorageDirectory()+"/gestures");

        if (!gestureLib.load()) {
            finish();
        }

        setContentView(gestureOverlayView);
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture)
    {
        ArrayList<Prediction> predictions = gestureLib.recognize(gesture);

        double max = 0;
        String name = "";

        for (Prediction prediction : predictions)
        {
            Toast.makeText(this, "gesture: " + prediction.name + " score: " + prediction.score, Toast.LENGTH_SHORT).show();

            if(prediction.score > max)
            {
                max = prediction.score;
                name = prediction.name;
            }
        }

        EditText et = (EditText) findViewById(R.id.editText);
        et.setText(name);

    }
}
