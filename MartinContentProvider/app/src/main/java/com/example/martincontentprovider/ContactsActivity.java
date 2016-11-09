package com.example.martincontentprovider;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;


public class ContactsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        String columns[] = new String[]
                {
                        ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.DISPLAY_NAME,
                        ContactsContract.Contacts.STARRED
                };

        String colsToDisplay [] = new String[]
                {
                        ContactsContract.Contacts.DISPLAY_NAME
                };

        int[] colResIds = new int[]
                {
                        R.id.name
                };

        ContentResolver cr = getContentResolver();

        Cursor c = cr.query(ContactsContract.Contacts.CONTENT_URI,
                columns,
                ContactsContract.Contacts.STARRED + "=0",
                null,
                null);

        setListAdapter(new SimpleCursorAdapter(this,
                R.layout.activity_contacts_activity,
                c, colsToDisplay,
                colResIds, 0));
    }
}
