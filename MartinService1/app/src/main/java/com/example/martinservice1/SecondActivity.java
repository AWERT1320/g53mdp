package com.example.martinservice1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

public class SecondActivity extends Activity {

    int jobNumber = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void onClickButton(View v)
    {
        Intent msgIntent = new Intent(this, MyIntentService.class);
        msgIntent.putExtra("jobNumber", jobNumber);
        startService(msgIntent);
        jobNumber++;
    }

}
