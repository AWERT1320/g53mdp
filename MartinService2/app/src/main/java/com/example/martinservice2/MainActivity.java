package com.example.martinservice2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("g53mdp", "MainActivity onCreate");
    }

    public void onClickStartService(View v) {
        this.startService(new Intent(MainActivity.this, MyService.class));
    }

    public void onClickStopService(View v) {
        this.stopService(new Intent(MainActivity.this, MyService.class));
    }
}