package com.example.martingestures;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

/**
 * Created by pszmdf on 28/11/2016.
 */

public class GestureActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesture_main);

        FrameLayout frameA = (FrameLayout) findViewById(R.id.frameA);
        FrameLayout frameB = (FrameLayout) findViewById(R.id.frameB);

        ScrollingGestureView sgv = new ScrollingGestureView(this);
        frameA.addView(sgv);

        FlingingGestureView fgv = new FlingingGestureView(this);
        frameB.addView(fgv);
    }
}
