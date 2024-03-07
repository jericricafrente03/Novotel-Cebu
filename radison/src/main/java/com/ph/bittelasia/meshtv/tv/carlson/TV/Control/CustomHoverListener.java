package com.ph.bittelasia.meshtv.tv.carlson.TV.Control;

import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class CustomHoverListener implements View.OnHoverListener {

    public VelocityTracker tracker;


    @Override
    public boolean onHover(View view, MotionEvent motionEvent) {
        int action = motionEvent.getActionMasked();
        switch (action)
        {
            case MotionEvent.ACTION_HOVER_ENTER:
                if (tracker == null) {
                    tracker = VelocityTracker.obtain();
                } else {
                    tracker.clear();
                }
                tracker.addMovement(motionEvent);
                break;
            case MotionEvent.ACTION_HOVER_MOVE:
                tracker.addMovement(motionEvent);
                tracker.computeCurrentVelocity(1000);
                break;
            case MotionEvent.ACTION_HOVER_EXIT:
                view.setSelected(false);
                view.setFocusable(false);
                break;
            case MotionEvent.ACTION_CANCEL:
                tracker.recycle();
                break;
        }
        return false;
    }
}
