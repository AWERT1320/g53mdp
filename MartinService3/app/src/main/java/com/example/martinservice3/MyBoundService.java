package com.example.martinservice3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteCallbackList;
import android.util.Log;

public class MyBoundService extends Service {

    RemoteCallbackList<MyBinder> remoteCallbackList = new RemoteCallbackList<MyBinder>();

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
                try {Thread.sleep(2000);} catch(Exception e) {return;}

                if(direction)
                    count++;
                else
                    count--;

                doCallbacks(count);
                Log.d("g53mdp", "counter " + count);
            }

            Log.d("g53mdp", "counter thread exiting");
        }
    }

    public void doCallbacks(int count)
    {
        final int n = remoteCallbackList.beginBroadcast();
        for (int i=0; i<n; i++)
        {
            remoteCallbackList.getBroadcastItem(i).callback.counterEvent(count);
        }
        remoteCallbackList.finishBroadcast();
    }


    public class MyBinder extends Binder implements IInterface
    {
        @Override
        public IBinder asBinder() {
            return this;
        }

        void countUp()
        {
            MyBoundService.this.countUp();
        }

        void countDown()
        {
            MyBoundService.this.countDown();
        }

        public void registerCallback(CallbackInterface callback) {
            this.callback = callback;
            remoteCallbackList.register(MyBinder.this);
        }

        public void unregisterCallback(CallbackInterface callback) {
            remoteCallbackList.unregister(MyBinder.this);
        }

        CallbackInterface callback;
    }

    public void countUp()
    {
        counter.direction = true;
    }

    public void countDown()
    {
        counter.direction = false;
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
        return new MyBinder();
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


}
