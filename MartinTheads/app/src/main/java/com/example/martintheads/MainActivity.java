package com.example.martintheads;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    EditText textField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textField = (EditText) findViewById(R.id.editText1);
    }

    public String doWork(long time) {
        try{Thread.sleep(time);}catch(Exception e){;}
        return "" + Math.random();
    }


    public void onClickDoSomething(View v) {
        textField.setText("do something " + doWork(0));
    }

    public void onClickTakeTimeBadly(View v) {
        textField.setText("something badly " + doWork(10000));
    }

    public void onClickPostRunnable(View v) {

        new Thread(new Runnable() {

            public void run() {
                final String myData = doWork(5000);

                runOnUiThread(new Runnable() {

                    public void run() {
                        textField.setText("runnable " + myData);
                    }
                });

            }
        }).start();

    }

    private Handler h;

    public void onClickPostHandler(View v) {

        h = new Handler();

        new Thread(new Runnable() {

            public void run() {

                final String myData = doWork(5000);

                h.post(new Runnable() {

                    public void run() {
                        textField.setText("handler " +myData);
                    }
                });

            }
        }).start();
    }

    public void onClickPostMessage(View v)
    {
        new Thread(new Runnable() {

            public void run() {

                String myData = doWork(5000);

                Message msg = new Message();
                Bundle b = new Bundle();
                b.putString("data", ""+myData);
                msg.setData(b);
                h2.sendMessage(msg);
            }
        }).start();

    }

    private Handler h2 = new Handler() {
        public void handleMessage(Message msg) {
            Bundle b = msg.getData();
            String data = b.getString("data");
            textField.setText("worker: " + data);
        }
    };


    public void onClickAsyncTask(View v) {
        new MyAsyncTask().execute(5000L);
    }

    private class MyAsyncTask extends AsyncTask<Long, Void, String> {

        protected String doInBackground(Long... time) {
            return doWork(time[0]);
        }

        protected void onPostExecute(String result) {
            textField.setText("asynctask: " + result);
        }
    }
}
