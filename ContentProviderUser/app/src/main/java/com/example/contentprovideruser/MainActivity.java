package com.example.contentprovideruser;

import android.content.Intent;
import android.database.Cursor;
import android.database.ContentObserver;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.martincontentprovier.MyProviderContract;

public class MainActivity extends AppCompatActivity {

    SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] projection = new String[] {
                MyProviderContract._ID,
                MyProviderContract.NAME,
                MyProviderContract.FOOD
        };

        int[] colResIds = new int[] {
                R.id.value1,
                R.id.value2,
                R.id.value3
        };

        Cursor cursor = getContentResolver().query(MyProviderContract.FOOD_URI, projection, null, null, null);

        dataAdapter = new SimpleCursorAdapter(
                this,
                R.layout.db_item_layout,
                cursor,
                projection,
                colResIds,
                0);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(dataAdapter);

        MyContentObserver observer = new MyContentObserver(new Handler());
        getContentResolver().registerContentObserver(MyProviderContract.ALL_URI, true, observer);

        queryProvider();

        startService(new Intent(this, ProviderService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, ProviderService.class));
    }

    class MyContentObserver extends ContentObserver
    {
        MyContentObserver(Handler handler)
        {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            queryProvider();
        }
    }

    public void queryProvider()
    {
        Log.d("g53mdp", "data changed");

        String[] projection = new String[] {
                MyProviderContract._ID,
                MyProviderContract.NAME,
                MyProviderContract.FOOD
        };

        Cursor cursor = getContentResolver().query(MyProviderContract.FOOD_URI, projection, null, null, null);
        dataAdapter.changeCursor(cursor);
    }
}
