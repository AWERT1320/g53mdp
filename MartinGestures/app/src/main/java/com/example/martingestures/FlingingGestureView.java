package com.example.martingestures;


import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by pszmdf on 28/11/2016.
 */

public class FlingingGestureView extends BaseGestureView {

    GestureDetector gestures;

    public FlingingGestureView(Context context)
    {
        super(context);
        this.gestures = new GestureDetector(context, new FlingGestureListener(this));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestures.onTouchEvent(event);
    }

    private class FlingGestureListener implements GestureDetector.OnGestureListener,
            GestureDetector.OnDoubleTapListener {

        FlingingGestureView view;

        public FlingGestureListener(FlingingGestureView view) {
            this.view = view;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               final float velocityX, final float velocityY) {
            Log.d("g53mdp", "onFling: " + velocityX + " " + velocityY);
            final float distanceTimeFactor = 0.4f;
            final float totalDx = (distanceTimeFactor * velocityX / 2);
            final float totalDy = (distanceTimeFactor * velocityY / 2);

            view.onAnimateMove(totalDx, totalDy, (long) (1000 * distanceTimeFactor));
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            view.onResetLocation();
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            Log.d("g53mdp", "onScroll");
            view.onMove(-distanceX, -distanceY);
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }
    }
}
