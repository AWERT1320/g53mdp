package com.example.martinservice4client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ComponentName componentName = new ComponentName("com.example.martinservice4","com.example.martinservice4.MyRemoteBoundService");
        Intent intent = new Intent();
        intent.setComponent(componentName);

        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    private Messenger messenger;

    public void onClickCountUp(View v)
    {
        Message message = Message.obtain(null, 0, 0, 0);

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
        Message message = Message.obtain(null, 1, 0, 0);

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
            Log.d("g53mdp", "martinservice4client onServiceConnected");
            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            Log.d("g53mdp", "martinservice4client onServiceDisconnected");
            messenger = null;
        }
    };
}
