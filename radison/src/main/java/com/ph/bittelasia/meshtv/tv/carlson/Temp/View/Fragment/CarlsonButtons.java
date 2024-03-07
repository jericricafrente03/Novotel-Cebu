package com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.core.view.VelocityTrackerCompat;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshTVButtons;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.RemoteControl.KR301KeyCode;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;

import java.util.ArrayList;

/**
 * Created by ramil on 2/6/18.
 */
@Layout(R.layout.carlson_buttons)
public class CarlsonButtons extends MeshTVFragment {

    VelocityTracker mVelocityTracker = null;
    Animation zoomIn;

    Button[] btn;
    public static CarlsonButtons b;

    public static CarlsonButtons get(Button ...btn)
    {
        b=new CarlsonButtons();
        b.btn=btn;
        return  b;
    }

    @BindWidget(R.id.btn_filter2)
    public Button btn_filter2;

    @BindWidget(R.id.btn_temp)
    public Button btn_temp;

    @BindWidget(R.id.btn_view)
    public Button btn_view;

    @BindWidget(R.id.btn_myvideos)
    public Button btn_myvideos;

    @BindWidget(R.id.btn_watch)
    public Button btn_watch;

    @BindWidget(R.id.btn_delete)
    public Button btn_delete;

    @BindWidget(R.id.btn_checkout)
    public Button btn_checkout;

    View[] views;
    int index=0;

    int count=0;

    final View.OnHoverListener hover=new View.OnHoverListener() {
        @Override
        public boolean onHover(View view, MotionEvent motionEvent) {
            int index = motionEvent.getActionIndex();
            int action = motionEvent.getActionMasked();
            int pointerId = motionEvent.getPointerId(index);
            switch(action) {
                case MotionEvent.ACTION_HOVER_ENTER:
                    if(mVelocityTracker == null) {
                        mVelocityTracker = VelocityTracker.obtain();
                    }
                    else {
                        mVelocityTracker.clear();
                    }
                    mVelocityTracker.addMovement(motionEvent);
                    return true;
                case MotionEvent.ACTION_HOVER_MOVE:
                    mVelocityTracker.addMovement(motionEvent);
                    mVelocityTracker.computeCurrentVelocity(1000);
                    Log.i("steward","view id: "+view.getId()+"");
                    Log.i("steward","pointer id: "+pointerId+"");
                    Log.d("steward", "X velocity: " + VelocityTrackerCompat.getXVelocity(mVelocityTracker, pointerId));
                    Log.d("steward", "Y velocity: " + VelocityTrackerCompat.getYVelocity(mVelocityTracker, pointerId));
                    view.requestFocus();
                    return true;
                case MotionEvent.ACTION_HOVER_EXIT:
                    view.clearFocus();
                    return true;
                case MotionEvent.ACTION_UP:
                    return true;
                case MotionEvent.ACTION_CANCEL:
                    mVelocityTracker.recycle();
                    return true;
            }
            return false;
        }
    };


    final View.OnClickListener click=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.btn_filter2:
                    getListener().onClicked(MeshTVButtons.FILTER.getButton());
                    break;
                case R.id.btn_temp:
                    getListener().onClicked(MeshTVButtons.TEMP.getButton());
                    break;
                case R.id.btn_view:
                    getListener().onClicked(MeshTVButtons.VIEWORDERS.getButton());
                    break;
                case R.id.btn_myvideos:
                    getListener().onClicked(MeshTVButtons.MYVIDEOS.getButton());
                    break;
                case R.id.btn_watch:
                    getListener().onClicked(MeshTVButtons.PREVIEW.getButton());
                    break;
                case R.id.btn_delete:
                    getListener().onClicked(MeshTVButtons.DELETE.getButton());
                case R.id.btn_checkout:
                    getListener().onClicked(MeshTVButtons.CHECKOUT.getButton());
                    break;
            }
        }
    };

    final View.OnFocusChangeListener focus=new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            switch (view.getId())
            {
                case R.id.btn_filter2:
                    checkImage(view,b,zoomIn);
                    break;
                case R.id.btn_temp:
                    checkImage(view,b,zoomIn);
                    break;
                case R.id.btn_view:
                    checkImage(view,b,zoomIn);
                    break;
                case R.id.btn_myvideos:
                    checkImage(view,b,zoomIn);
                    break;
                case R.id.btn_watch:
                    checkImage(view,b,zoomIn);
                    break;
                case R.id.btn_delete:
                    checkImage(view,b,zoomIn);
                    break;
                case R.id.btn_checkout:
                    checkImage(view,b,zoomIn);
                    break;

            }
        }
    };

    final View.OnKeyListener keyListener=new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            switch (KR301KeyCode.getEquivalent(keyEvent.getKeyCode()))
            {
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    if(keyEvent.getAction()==KeyEvent.ACTION_UP) {
                        if(count==0) {
                            views[index].requestFocus();
                            count=1;
                        }else
                        {
                            if(index < views.length)
                                views[index++].requestFocus();
                        }
                        return true;
                    }
                    return true;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    if(keyEvent.getAction()==KeyEvent.ACTION_UP) {
                        if(count==0) {
                            views[index].requestFocus();
                            count=1;
                        }else
                        {
                            if(index != 0)
                                views[index--].requestFocus();
                        }
                        return true;
                    }
                    return true;
            }
            return false;
        }
    };

    public void checkImage(final View view, Boolean b, Animation animation)
    {
        if(b)
        {
            view.startAnimation(animation);
        }
        else
        {
            view.clearAnimation();
        }

    }

    public void setVisibility(Button ... btn)
    {
        try
        {
            for (int x = 0; x < btn.length; x++) {
                btn[x].setVisibility(View.VISIBLE);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void onDrawDone(View view)
    {
        zoomIn = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
        btn_filter2.setOnClickListener(click);
        btn_temp.setOnClickListener(click);
        btn_view.setOnClickListener(click);
        btn_myvideos.setOnClickListener(click);
        btn_watch.setOnClickListener(click);
        btn_delete.setOnClickListener(click);
        btn_checkout.setOnClickListener(click);

        btn_filter2.setOnFocusChangeListener(focus);
        btn_temp.setOnFocusChangeListener(focus);
        btn_view.setOnFocusChangeListener(focus);
        btn_myvideos.setOnFocusChangeListener(focus);
        btn_watch.setOnFocusChangeListener(focus);
        btn_delete.setOnFocusChangeListener(focus);
        btn_checkout.setOnFocusChangeListener(focus);

        btn_filter2.setOnHoverListener(hover);
        btn_temp.setOnHoverListener(hover);
        btn_view.setOnHoverListener(hover);
        btn_myvideos.setOnHoverListener(hover);
        btn_myvideos.setOnHoverListener(hover);
        btn_delete.setOnHoverListener(hover);
        btn_checkout.setOnHoverListener(hover);

        if(btn!=null)
        {
            if (btn.length > 0) {
                setVisibility(btn);
            }
        }

    }

    @Override
    protected void onDataUpdated(ArrayList arrayList) {

    }

    @Override
    protected void onNewData(Object o) {

    }

    @Override
    protected void onDataUpdated(Object o, int i) {

    }

    @Override
    protected void onDeleteData(int i) {

    }

    @Override
    protected void onClearData() {

    }

    @Override
    protected void onDataNotFound(Class aClass) {

    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void update(Object o) {

    }
}
