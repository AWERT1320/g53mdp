package com.example.martingestures;

import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickActivityTwo(View v)
    {
        Intent intent = new Intent(MainActivity.this, GestureActivity.class);
        startActivity(intent);
    }

    public void onClickActivityThree(View v)
    {
        Intent intent = new Intent(MainActivity.this, CustomGestureActivity.class);
        startActivity(intent);
    }

    public void onClickActivityFour(View v)
    {
        Intent intent = new Intent(MainActivity.this, MultiTouchActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                Log.d("g53mdp","Action was DOWN");
                return true;
            case (MotionEvent.ACTION_MOVE) :
                float x = event.getX();
                float y = event.getY();
                Log.d("g53mdp", "Action was MOVE " + x + " " + y);
                return true;
            case (MotionEvent.ACTION_UP) :
                Log.d("g53mdp","Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                Log.d("g53mdp","Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                Log.d("g53mdp","Movement occurred outside bounds of current screen element");
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }
}
