package com.example.martinservice4;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bindService(new Intent(this, MyRemoteBoundService.class), serviceConnection, Context.BIND_AUTO_CREATE);

        replyMessenger = new Messenger(new MyHandler());
    }

    private Messenger messenger;
    private Messenger replyMessenger;


    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Log.d("g53mdp", "reply received");
        }
    }

    public void onClickCountUp(View v)
    {
        Message message = Message.obtain(null, MyRemoteBoundService.COUNT_UP, 0, 0);

        try
        {
            messenger.send(message);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    public void onClickCountDown(View v)
    {
        Message message = Message.obtain(null, MyRemoteBoundService.COUNT_DOWN, 0, 0);

        try
        {
            messenger.send(message);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    public void sendStuff(View v)
    {
        Message message = Message.obtain(null, MyRemoteBoundService.SEND_STUFF, 0, 0);

        MyParcelable p = new MyParcelable();
        p.x = 5;
        p.y = 10;
        p.name = "martin";

        Bundle b = new Bundle();
        b.putParcelable("myParcel", (Parcelable) p);
        message.setData(b);
        message.replyTo = replyMessenger;

        try
        {
            messenger.send(message);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }

    }

    private ServiceConnection serviceConnection = new ServiceConnection()
    {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            Log.d("g53mdp", "onServiceConnected");
            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            Log.d("g53mdp", "onServiceDisconnected");
            messenger = null;
        }
    };
}
