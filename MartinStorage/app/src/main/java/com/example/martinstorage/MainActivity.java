package com.example.martinstorage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {

    DatabaseAdapter dbAdapter;
    SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPreference();

        writeLocalFile();

        dbAdapter = new DatabaseAdapter(this);
        dbAdapter.open();

        queryDBListView();
        queryDBTextView();

    }

    public void writeLocalFile()
    {
        String filename = "hello_file";
        String string = "hello world!";

        try {
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(string.getBytes());
            fos.close();
        } catch(IOException e)
        {
            Log.d("g53mdp", e.toString());
        }
    }

    public static String CONFIG_STORAGE_NAME = "my preferences";
    public static String CONFIG_PREFERENCE_1 = "preference 1";

    public void getPreference()
    {
        SharedPreferences settings = getSharedPreferences(CONFIG_STORAGE_NAME, 0);

        String pref = settings.getString(CONFIG_PREFERENCE_1, "not set");

        final EditText textBox = (EditText) findViewById(R.id.editText3);
        textBox.setText(pref);

    }

    public void setPreference(View v)
    {
        SharedPreferences settings = getSharedPreferences(CONFIG_STORAGE_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        final EditText textBox = (EditText) findViewById(R.id.editText3);

        editor.putString(CONFIG_PREFERENCE_1, textBox.getText()+"");

        editor.commit();
    }

    public void queryDBTextView() {

        StringBuilder sb = new StringBuilder();
        TextView tv = (TextView)findViewById(R.id.textView);

        //String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
        Cursor c = dbAdapter.db.query("myList", new String[] { "_id", "name", "colour" }, null, null, null, null, null);

        if(c.moveToFirst())
        {
            do
            {
                int id = c.getInt(0);
                String name = c.getString(1);
                String colour = c.getString(2);

                sb.append(""+id+ ": " + name + ", " + colour);
                sb.append("\n");

                Log.d("g53mdp", id + " " + name);
            }
            while(c.moveToNext());
        }

        tv.setText(sb);
    }

    public void queryDBListView() {
        Cursor cursor = dbAdapter.db.query("myList", new String[] { "_id", "name", "colour" }, null, null, null, null, null);

        String[] columns = new String[] {
                DatabaseAdapter.KEY_NAME,
                DatabaseAdapter.KEY_COLOUR
        };

        int[] to = new int[] {
                R.id.nameView,
                R.id.colourView,
        };

        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.db_item_layout,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(dataAdapter);

    }

    public void add(View v) {

        final EditText inputFieldColour = (EditText) findViewById(R.id.editTextColour);
        final EditText inputFieldName = (EditText) findViewById(R.id.editTextName);

        dbAdapter.addNameColour(inputFieldName.getText().toString(), inputFieldColour.getText().toString());

        queryDBListView();
        queryDBTextView();
    }

}
