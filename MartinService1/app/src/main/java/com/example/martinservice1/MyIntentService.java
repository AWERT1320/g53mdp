package com.example.martinservice1;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService
{
    public MyIntentService()
    {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        Log.d("g53mdp", "service onCreate");

        //try{Thread.sleep(5000);}catch(Exception e){;}
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d("g53mdp", "service onStart");
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.d("g53mdp", "service onDestroy");
    }

    @Override
    protected void onHandleIntent(Intent arg0) {
        // TODO Auto-generated method stub
        Log.d("g54mdp", "service onHandleIntent");

        int jobNumber = arg0.getIntExtra("jobNumber", 0);

        for(int i=0; i<5; i++)
        {
            try{Thread.sleep(1000);}catch(Exception e){;}
            Log.d("g53mdp", "working on " + jobNumber + " step " + i);
        }
    }
}
