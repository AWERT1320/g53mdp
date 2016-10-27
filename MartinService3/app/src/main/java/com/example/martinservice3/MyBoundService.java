package com.example.martinservice3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBoundService extends Service {

    private final IBinder binder = new MyBinder();

    private Counter counter;

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
        return binder;
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

    @Override
    public void onRebind(Intent intent) {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "service onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "service onUnbind");
        return super.onUnbind(intent);
    }


    public class MyBinder extends Binder
    {
        void countUp()
        {
            MyBoundService.this.countUp();
        }

        void countDown()
        {
            MyBoundService.this.countDown();
        }

        MyBoundService getService()
        {
            return MyBoundService.this;
        }
    }

    public void countUp()
    {
        counter.direction = true;
    }

    public void countDown()
    {
        counter.direction = false;
    }

}
