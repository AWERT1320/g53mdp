package com.example.martinstorage;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by pszmdf on 05/11/2017.
 */

public class DatabaseActivity extends Activity {

    DatabaseAdapter dbAdapter;
    SimpleCursorAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        dbAdapter = new DatabaseAdapter(this);
        dbAdapter.open();

        queryDBListView();

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
                this, R.layout.db_item,
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

        dbAdapter.addNameColour(inputFieldName.getText().toString(),
                inputFieldColour.getText().toString());

        queryDBListView();
    }
}
