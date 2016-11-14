package com.example.martinbroadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyCustomBroadcastReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("g53mdp", "MyCustomBroadcastReceiver2 onReceive custom");
        Toast.makeText(context, "custom broadcast received", Toast.LENGTH_LONG).show();
        abortBroadcast();
        //setResultCode();
    }
}