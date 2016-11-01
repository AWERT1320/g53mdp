package com.example.martinaidlclient;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.martinaidlservice.IStringReverser;

public class MainActivity extends ActionBarActivity {

    EditText inputTextBox;
    TextView resultTextBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTextBox = (EditText) findViewById(R.id.editText2);
        resultTextBox = (TextView) findViewById(R.id.editText);

        ComponentName componentName = new ComponentName("com.example.martinaidlservice","com.example.martinaidlservice.StringReverserImpl");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        bindService(intent, this.connection, Context.BIND_AUTO_CREATE);
    }

    private IStringReverser service;

    private ServiceConnection connection = new ServiceConnection()
    {
        public void onServiceConnected(ComponentName className, IBinder iservice)
        {
            service = IStringReverser.Stub.asInterface(iservice);
            Log.d("g53mdp", "connected");
        }

        public void onServiceDisconnected(ComponentName className)
        {
            service = null;
        }
    };

    public void onClickButtonIn(View v)
    {
        try
        {
            Bundle b = new Bundle();
            b.putString("myKey", inputTextBox.getText().toString());

            service.modifyBundle(b);

            String s = b.getString("myKey");
            resultTextBox.setText(s);
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }

    public void onClickButtonInOut(View v)
    {
        try
        {
            Bundle b = new Bundle();
            b.putString("myKey", inputTextBox.getText().toString());

            service.modifyBundleReference(b);

            String s = b.getString("myKey");
            resultTextBox.setText(s);
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
    }


    public void onClickButton(View v)
    {
        try
        {
            String toReverse = inputTextBox.getText().toString();
            String reversed = service.reverseString(toReverse);
            resultTextBox.setText(reversed);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }
}
