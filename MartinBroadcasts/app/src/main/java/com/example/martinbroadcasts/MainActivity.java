package com.example.martinbroadcasts;

import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalBroadcastReceiver receiver = new LocalBroadcastReceiver();
        IntentFilter filter = new IntentFilter("com.example.martinbroadcasts.MY_LOCAL_CUSTOM_BROADCAST");
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
    }

    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setAction("com.example.martinbroadcasts.MY_CUSTOM_BROADCAST");
        sendOrderedBroadcast(intent, null);
        //sendBroadcast(intent);
    }

    public void onClick2(View v) {
        Intent intent = new Intent();
        intent.setAction("com.example.martinbroadcasts.MY_LOCAL_CUSTOM_BROADCAST");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
