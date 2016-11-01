package com.example.martinservice3;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    private MyBoundService.MyBinder myService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.startService(new Intent(this, MyBoundService.class));
        this.bindService(new Intent(this, MyBoundService.class), serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void onClickStopService(View v)
    {
        this.stopService(new Intent(this, MyBoundService.class));
    }

    public void onClickCountUp(View v)
    {
        myService.countUp();
    }

    public void onClickCountDown(View v)
    {
        myService.countDown();
    }

    private ServiceConnection serviceConnection = new ServiceConnection()
    {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            Log.d("g53mdp", "MainActivity onServiceConnected");
            myService = (MyBoundService.MyBinder) service;
            myService.registerCallback(callback);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            Log.d("g53mdp", "MainActivity onServiceDisconnected");
            myService.unregisterCallback(callback);
            myService = null;
        }
    };

    CallbackInterface callback = new CallbackInterface() {
        @Override
        public void counterEvent(final int counter) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView tv = (TextView) findViewById(R.id.textView);
                    tv.setText("counter: " + counter);
                }
            });
        }
    };

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.d("g53mdp", "MainActivity onDestroy");

        if(serviceConnection!=null) {
            unbindService(serviceConnection);
            serviceConnection = null;
        }
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Log.d("g53mdp", "MainActivity onPause");
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.d("g53mdp", "MainActivity onResume");
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Log.d("g53mdp", "MainActivity onStart");
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        Log.d("g53mdp", "MainActivity onStop");
    }
}
