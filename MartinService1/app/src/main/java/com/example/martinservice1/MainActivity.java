package com.example.martinservice1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    int jobNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButton(View v)
    {
        Intent msgIntent = new Intent(this, MyIntentService.class);
        msgIntent.putExtra("jobNumber", jobNumber);
        startService(msgIntent);
        jobNumber++;
    }

    public void onClickButton2(View v)
    {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.d("g54mdp", "MainActivity onDestroy");
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Log.d("g54mdp", "MainActivity onPause");
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.d("g54mdp", "MainActivity onResume");
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Log.d("g54mdp", "MainActivity onStart");
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        Log.d("g54mdp", "MainActivity onStop");
    }
}
