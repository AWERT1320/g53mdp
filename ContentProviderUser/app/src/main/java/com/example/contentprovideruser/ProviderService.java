package com.example.contentprovideruser;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;

import com.example.martincontentprovier.MyProviderContract;

public class ProviderService extends Service {

    Counter counter;

    public class Counter extends Thread implements Runnable
    {
        public boolean direction = true;
        public int count = 0;
        public boolean running = true;

        public Counter()
        {
            this.start();
        }

        public void run()
        {
            while(this.running)
            {
                ContentValues newValues = new ContentValues();
                newValues.put(MyProviderContract.NAME, "name" + count);
                newValues.put(MyProviderContract.EMAIL, "email" + count);
                newValues.put(MyProviderContract.FOOD, "food" + count);

                Log.d("g53mdp", "inserting data...");
                getContentResolver().insert(MyProviderContract.PEOPLE_URI, newValues);

                count++;

                try {Thread.sleep(10000);} catch(Exception e) {return;}
            }

            Log.d("g53mdp", "counter thread exiting");
        }
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "service onCreate");
        super.onCreate();
        counter = new Counter();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "service onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "service onStartCommand");
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "service onDestroy");
        counter.running = false;
        counter = null;
        super.onDestroy();
    }
}
