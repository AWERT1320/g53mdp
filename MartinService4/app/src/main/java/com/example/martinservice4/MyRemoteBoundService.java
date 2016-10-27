package com.example.martinservice4;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MyRemoteBoundService extends Service {

    private Messenger messenger;
    private Counter counter = new Counter();

    public static final int COUNT_UP = 0;
    public static final int COUNT_DOWN = 1;
    public static final int SEND_STUFF = 2;

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case COUNT_UP:
                    countUp();
                    break;
                case COUNT_DOWN:
                    countDown();
                    break;
                case SEND_STUFF:
                    MyParcelable p = msg.getData().getParcelable("myParcel");
                    Log.d("g53mdp", p.x + " " + p.y + " " + p.name);
                    Messenger replyto = msg.replyTo;
                    Message reply = Message.obtain();
                    try {
                        replyto.send(reply);
                    } catch (RemoteException e) {
                        Log.d("g53mdp", e.toString());
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    @Override
    public void onCreate()
    {
        messenger = new Messenger(new MyHandler());
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return messenger.getBinder();
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
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "onStartCommand");
        throw new RuntimeException("You should not start this service");
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "onDestroy");
        counter.running = false;
        counter = null;
        super.onDestroy();
    }

    @Override
    public void onRebind(Intent intent) {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        Log.d("g53mdp", "onUnbind");
        return super.onUnbind(intent);
    }
}
