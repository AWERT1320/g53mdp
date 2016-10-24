package com.example.martinactivities;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ThirdActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_third);
	
		Bundle bundle = getIntent().getExtras();
		String text = bundle.getString("myString");

		final EditText textField = (EditText) findViewById(R.id.editText2);
		textField.setText(text);
	}
	
    public void onClickOk(View v)
    {
        final EditText inputField = (EditText) findViewById(R.id.editText1);
		String input = inputField.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("myResult", input);
    	
    	Intent result = new Intent();
    	result.putExtras(bundle);
    	setResult(Activity.RESULT_OK, result);
    	finish();
    }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("g53mdp", "ThirdActivity onDestroy");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("g53mdp", "ThirdActivity onPause");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d("g53mdp", "ThirdActivity onResume");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d("g53mdp", "ThirdActivity onStart");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("g53mdp", "ThirdActivity onStop");
	}

}
