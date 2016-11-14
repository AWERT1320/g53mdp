package com.example.martinbroadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class LocalBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("g53mdp", "LocalBroadcastReceiver onReceive custom");
        Toast.makeText(context, "custom local broadcast received", Toast.LENGTH_LONG).show();
    }
}