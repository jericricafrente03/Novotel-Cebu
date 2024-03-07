package com.ph.bittelasia.meshtv.tv.carlson.Launcher.View.Fragment;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.view.VelocityTrackerCompat;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.Packages;
import com.ph.bittelasia.meshtv.tv.mtvlib.AirMedia.Control.MeshTVAirmediaControl;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.RemoteControl.KR301KeyCode;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;

import java.util.ArrayList;


/**
 * Created by ramil on 11/20/17.
 */
@Layout(R.layout.carlson_launcher_grid)
public class CarlsonGridFragment extends MeshTVFragment {

    Animation animZoomIn;

    public static final String TAG=CarlsonGridFragment.class.getSimpleName();
    private VelocityTracker     mVelocityTracker = null;


    public ProgressDialog dialog;



    @BindWidget(R.id.rl_airmedia)
    public RelativeLayout rl_airmedia;
    @BindWidget(R.id.rl_weather)
    public RelativeLayout rl_weather;
    @BindWidget(R.id.rl_website)
    public RelativeLayout rl_website;
    @BindWidget(R.id.rl_enews)
    public RelativeLayout rl_enews;
    @BindWidget(R.id.iv_netflix)
    public RelativeLayout iv_netflix;
    @BindWidget(R.id.iv_facebook)
    public RelativeLayout iv_facebook;
    @BindWidget(R.id.iv_twitter)
    public RelativeLayout iv_twitter;
    @BindWidget(R.id.iv_spotify)
    public RelativeLayout iv_spotify;

    ProgressBar    pb_news;
    ProgressBar    pb_netflix;
    ProgressBar    pb_facebook;
    ProgressBar    pb_twitter;
    ProgressBar    pb_spotify;

    @BindWidget(R.id.tv_enews)
    public TextView       tv_enews;
    @BindWidget(R.id.tv_netflix)
    public TextView       tv_netflix;
    @BindWidget(R.id.tv_facebook)
    public TextView       tv_facebook;
    @BindWidget(R.id.tv_spotify)
    public TextView       tv_spotify;
    @BindWidget(R.id.tv_twitter)
    public TextView       tv_twitter;

    Handler        handler;
    Runnable       checkInstallation;
    int            control=0;



    //-----------------Set Events----------------------------------------------------------
    public void setEvents()
    {
        animZoomIn = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
        try
        {
            rl_airmedia.setOnClickListener(clickListener);
            rl_airmedia.setOnFocusChangeListener(focusChangeListener);
            rl_airmedia.setOnTouchListener(touchListener);
            rl_airmedia.setOnHoverListener(hover);

            rl_weather.setOnClickListener(clickListener);
            rl_weather.setOnFocusChangeListener(focusChangeListener);
            rl_weather.setOnTouchListener(touchListener);
            rl_weather.setOnHoverListener(hover);

            rl_website.setOnClickListener(clickListener);
            rl_website.setOnFocusChangeListener(focusChangeListener);
            rl_website.setOnTouchListener(touchListener);
            rl_website.setOnHoverListener(hover);

            rl_enews.setOnClickListener(clickListener);
            rl_enews.setOnFocusChangeListener(focusChangeListener);
            rl_enews.setOnTouchListener(touchListener);
            rl_enews.setOnHoverListener(hover);

            iv_netflix.setOnClickListener(clickListener);
            iv_netflix.setOnFocusChangeListener(focusChangeListener);
            iv_netflix.setOnTouchListener(touchListener);
            iv_netflix.setOnHoverListener(hover);

            iv_facebook.setOnClickListener(clickListener);
            iv_facebook.setOnFocusChangeListener(focusChangeListener);
            iv_facebook.setOnTouchListener(touchListener);
            iv_facebook.setOnHoverListener(hover);


            iv_twitter.setOnClickListener(clickListener);
            iv_twitter.setOnFocusChangeListener(focusChangeListener);
            iv_twitter.setOnTouchListener(touchListener);
            iv_twitter.setOnHoverListener(hover);

            iv_spotify.setOnClickListener(clickListener);
            iv_spotify.setOnFocusChangeListener(focusChangeListener);
            iv_spotify.setOnTouchListener(touchListener);
            iv_spotify.setOnHoverListener(hover);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }



    @Override
    protected void update(Object o) {

    }

    //-------------------------Animate-----------------------------------------------------------

    public void checkImage(final View view, Boolean b, Animation animation)
    {
        if(b)
        {
            view.setForeground(ContextCompat.getDrawable(getContext(),R.drawable.carlson_image_item_selectedv1));
            view.startAnimation(animation);
            view.setTranslationZ(1);
        }
        else
        {
            view.setForeground(null);
            view.setPadding(0,0,0,0 );
            view.setTranslationZ(0);
            view.clearAnimation();
        }

    }

    public void setTextInstall(TextView tv)
    {
        if(!tv.getText().toString().equals("Installing...."))
        {
            tv.setText((tv.getText()+"."));
        }
        else
        {
           tv.setText("Installing");
        }
    }


    public  boolean appInstalledOrNot(String uri) {
        PackageManager pm = getActivity().getPackageManager();
        try
        {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    //----------------global onfocuschangelistener------------------------------------------------

    final  View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            checkImage(view,b,animZoomIn);
        }
    };

    //---------------global onHoverListener------------------------------------------------------
    final View.OnHoverListener hover=new View.OnHoverListener() {
        @Override
        public boolean onHover(View view, MotionEvent motionEvent) {
            int index = motionEvent.getActionIndex();
            int action = motionEvent.getActionMasked();
            int pointerId = motionEvent.getPointerId(index);
            view.requestFocus();
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

    //----------------global ontouchlistener-----------------------------------------------------

    final View.OnTouchListener touchListener=new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.performClick();
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    view.requestFocus();
            }
            return false;
        }
    };

    //----------------global onClicklistener-----------------------------------------------------
    final View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id=view.getId();
            switch (id)
            {
                case R.id.rl_airmedia:
                    dialog=ProgressDialog.show(getContext(), "Loading", "Preparing the Airmedia app...", true);
                    dialog.setCancelable(true);
                    MeshTVAirmediaControl.install(getContext());
                    break;
                case R.id.rl_weather:
                    getListener().onClicked(Packages.DISPLAY_WEATHER);
                    break;
                case R.id.rl_website:
                    getListener().onClicked(Packages.DISPLAY_WEBSITE);
                    break;
                case R.id.rl_enews:
                    getListener().onClicked(Packages.DISPLAY_ENEWS);
                    break;
                case R.id.iv_facebook:
                    getListener().onClicked(Packages.DISPLAY_FACEBOOK);
                    break;
                case R.id.iv_netflix:
                    getListener().onClicked(Packages.DISPLAY_NETFLIX);
                    break;
                case R.id.iv_twitter:
                    getListener().onClicked(Packages.DISPLAY_TWITTER);
                    break;
                case R.id.iv_spotify:
                    getListener().onClicked(Packages.DISPLAY_SPOTIFY);
                    break;
                default:
                    break;
            }

        }
    };

    @Override
    protected void onDrawDone(View view) {
        setEvents();
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
}
