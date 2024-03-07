package com.ph.bittelasia.meshtv.tv.carlson;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.CustomView.CustomScrollingTextView.MeshTVScrollingTextView;

public class MeshOverlayService2 extends Service
{
    //===============================================Variable=======================================
    //-----------------------------------------------Constant---------------------------------------
    public static final String TAG = com.ph.bittelasia.meshtv.tv.mtvlib.MeshOverlay.MeshOverlayService.class.getSimpleName();
    public static final String STOP = com.ph.bittelasia.meshtv.tv.mtvlib.MeshOverlay.MeshOverlayService.class.getCanonicalName()+"_STOP";
    public static final String TIMEOUT = com.ph.bittelasia.meshtv.tv.mtvlib.MeshOverlay.MeshOverlayService.class.getCanonicalName()+"_TIMEOUT";
    public static final String MESSAGE = com.ph.bittelasia.meshtv.tv.mtvlib.MeshOverlay.MeshOverlayService.class.getCanonicalName()+"_MESSAGE";
    //----------------------------------------------------------------------------------------------
    //-----------------------------------------------Instance---------------------------------------
    WindowManager manager = null;
    LinearLayout ll_container = null;
    LinearLayout.LayoutParams ll_param = null;
    WindowManager.LayoutParams parameters = null;
    BroadcastReceiver stop = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            close();
        }
    };
    Handler h;
    int timeout;
    String message;
    MeshTVScrollingTextView tv_scroll;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            close();
        }
    };
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //==============================================LifeCycle=======================================
    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.i(TAG,"onCreate()");
        h = new Handler();
        registerReceiver(stop,new IntentFilter(STOP));
        manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        ll_container = new LinearLayout(this);
        ll_param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        ll_container.setLayoutParams(ll_param);
        parameters = new WindowManager.LayoutParams
                (
                        WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_PHONE,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        PixelFormat.TRANSLUCENT
                );
        parameters.x = WindowManager.LayoutParams.MATCH_PARENT;
        parameters.y = WindowManager.LayoutParams.WRAP_CONTENT;
        parameters.gravity = Gravity.BOTTOM | Gravity.LEFT;
        parameters.type = WindowManager.LayoutParams.TYPE_TOAST;


//        manager.addView(ll_container,parameters);

        View v = LayoutInflater.from(ll_container.getContext()).inflate(R.layout.carlson_window_layout,ll_container);

        init();
        draw(v);
//        timeout = intent.getIntExtra(TIMEOUT,10);
//        message = intent.getStringExtra(MESSAGE);

        timeout = 10000;
        message = "Test Mdashd uhsau hdsa hddsadsadsadsadsadsadsadsadsa dsadsa dsa dsa dsa dsad sad asd sad sad sad sad assh udsha hd u hu huh uh uhu hu hu hu hu hu huh uh uh uh uhu hu hu huh uh uh uh uh uh uh uh uh uh uh uh uh uh uh uh uh uh uh uh uh uh uh uh uh uhu hu hu h";

         tv_scroll = new MeshTVScrollingTextView(v.getContext(),null);
        tv_scroll.setBackgroundColor(Color.parseColor("#aa000000"));

//        ll_container.addView(tv_scroll,parameters);
        manager.addView(tv_scroll,parameters);
        tv_scroll.setDisplayTime(timeout);
        tv_scroll.setText(message);
        h.postDelayed(runnable,timeout);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
    //==============================================================================================
    //===============================================Method=========================================
    private final void init()
    {

    }
    private final void draw(View v)
    {

    }
    private void close()
    {
        manager.removeView(tv_scroll);
        unregisterReceiver(stop);
        stopSelf();
    }
    //==============================================================================================
    //===============================================Service========================================
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        Log.i(TAG,"bind()");
        return null;
    }
    //==============================================================================================
}
