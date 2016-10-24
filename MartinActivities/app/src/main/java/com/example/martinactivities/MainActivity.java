package com.example.martinactivities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.net.Uri;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

	static final int ACTIVITY_THREE_REQUEST_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("g53mdp", "MainActivity onCreate " + id);
        setContentView(R.layout.activity_main);
    }

	public void onClickActivityTwo(View v)
    {
    	Intent intent = new Intent(MainActivity.this, SecondActivity.class);
    	startActivity(intent);
    }

    public void onClickPhonecall(View v)
    {
		Uri number = Uri.parse("tel:01151234567");
		Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
		startActivity(callIntent);
    }

	public void onClickBrowseTo(View v)
	{
		Uri url = Uri.parse("http://www.example.com");
		Intent i = new Intent(Intent.ACTION_VIEW, url);
		startActivity(i);
	}

	public void onClickPickAttachment(View v)
	{
		Intent i = new Intent();
		i.setType("*/*");
		i.setAction(Intent.ACTION_GET_CONTENT);
		i.addCategory(Intent.CATEGORY_OPENABLE);
		startActivity(i);
	}

    public void onClickActivityThree(View v)
    {
    	Intent intent = new Intent(MainActivity.this, ThirdActivity.class);

        final EditText textBox = (EditText) findViewById(R.id.editText1);
        String text = textBox.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("myString", text);

    	intent.putExtras(bundle);

    	startActivityForResult(intent, ACTIVITY_THREE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == ACTIVITY_THREE_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK)
            {
        		Bundle bundle = data.getExtras();
        		String result = bundle.getString("myResult");
        		Log.d("g53mdp", "******* MainActivity ok " + result);

                final EditText resultField = (EditText) findViewById(R.id.editText2);
                resultField.setText(result);

            }
            else if(resultCode == RESULT_CANCELED)
            {
        		Log.d("g53mdp", "******* MainActivity canceled");
            }
        }
    }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("g53mdp", "MainActivity onDestroy");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("g53mdp", "MainActivity onPause");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d("g53mdp", "MainActivity onResume");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d("g53mdp", "MainActivity onStart");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("g53mdp", "MainActivity onStop");
	}

	double id = Math.random();

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d("g53mdp", "MainActivity onSaveInstanceState " + id);
		outState.putDouble("myDouble", id);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		id = savedInstanceState.getDouble("myDouble");
		Log.d("g53mdp", "MainActivity onRestoreInstanceState " + id);
	}

}
