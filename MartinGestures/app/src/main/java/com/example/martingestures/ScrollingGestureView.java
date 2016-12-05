package com.example.martingestures;


import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by pszmdf on 28/11/2016.
 */

public class ScrollingGestureView extends BaseGestureView {

    GestureDetector gestures;

    public ScrollingGestureView(Context context)
    {
        super(context);
        this.gestures = new GestureDetector(context, new ScrollGestureListener(this));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestures.onTouchEvent(event);
    }

    private class ScrollGestureListener implements GestureDetector.OnGestureListener,
            GestureDetector.OnDoubleTapListener {

        ScrollingGestureView view;

        public ScrollGestureListener(ScrollingGestureView view) {
            this.view = view;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d("g53mdp", "onDown");
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               final float velocityX, final float velocityY) {
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.v("g53mdp", "onDoubleTap");
            view.onResetLocation();
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.d("g53mdp", "onLongPress");
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            Log.d("g53mdp", "onScroll");
            view.onMove(0, -distanceY);
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.d("g53mdp", "onShowPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d("g53mdp", "onSingleTapUp");
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.d("g53mdp", "onDoubleTapEvent");
            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.d("g53mdp", "onSingleTapConfirmed");
            return false;
        }
    }
}
