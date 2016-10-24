package com.example.martinactivities;

import android.os.Bundle;
import android.app.Activity;
import android.widget.EditText;
import android.net.Uri;

public class ForthActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forth);

		Uri url = getIntent().getData();
		final EditText textBox = (EditText) findViewById(R.id.editText);
		textBox.setText(url.toString());
	}

}
