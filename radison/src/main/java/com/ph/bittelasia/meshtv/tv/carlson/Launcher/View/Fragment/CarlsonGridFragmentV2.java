package com.ph.bittelasia.meshtv.tv.carlson.Launcher.View.Fragment;


import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.Packages;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Model.MeshGuest;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;

import java.util.ArrayList;

/**
 * Created by ramil on 2/6/18.
 */
@Layout(R.layout.carlson_launcher_gridv2)
public class CarlsonGridFragmentV2 extends MeshTVFragment {

    Animation animZoomIn;

    @BindWidget(R.id.rl_airmedia)
    public RelativeLayout airmedia;
    @BindWidget(R.id.rl_message)
    public RelativeLayout message;
    @BindWidget(R.id.rl_weather)
    public RelativeLayout weather;
    @BindWidget(R.id.rl_hotelinfo)
    public RelativeLayout hotelinfo;
    @BindWidget(R.id.rl_bill)
    public RelativeLayout bill;
    @BindWidget(R.id.rl_shopping)
    public RelativeLayout shopping;
    @BindWidget(R.id.rl_dining)
    public RelativeLayout dining;
    @BindWidget(R.id.rl_vod)
    public RelativeLayout vod;
    @BindWidget(R.id.rl_tv)
    public RelativeLayout tv;


    @Override
    public void onResume() {
        super.onResume();
        WifiManager wifiManager = (WifiManager) getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(false);
    }

    public void setEvents() {
        try
        {
            animZoomIn = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
            airmedia.setOnFocusChangeListener(focusChangeListener);
            message.setOnFocusChangeListener(focusChangeListener);
            weather.setOnFocusChangeListener(focusChangeListener);
            hotelinfo.setOnFocusChangeListener(focusChangeListener);
            bill.setOnFocusChangeListener(focusChangeListener);
            shopping.setOnFocusChangeListener(focusChangeListener);
            dining.setOnFocusChangeListener(focusChangeListener);
            vod.setOnFocusChangeListener(focusChangeListener);
            tv.setOnFocusChangeListener(focusChangeListener);

            airmedia.setOnHoverListener(hover);
            message.setOnHoverListener(hover);
            weather.setOnHoverListener(hover);
            hotelinfo.setOnHoverListener(hover);
            bill.setOnHoverListener(hover);
            shopping.setOnHoverListener(hover);
            dining.setOnHoverListener(hover);
            vod.setOnHoverListener(hover);
            tv.setOnHoverListener(hover);

            airmedia.setOnTouchListener(touchListener);
            message.setOnTouchListener(touchListener);
            weather.setOnTouchListener(touchListener);
            hotelinfo.setOnTouchListener(touchListener);
            bill.setOnTouchListener(touchListener);
            shopping.setOnTouchListener(touchListener);
            dining.setOnTouchListener(touchListener);
            vod.setOnTouchListener(touchListener);
            tv.setOnTouchListener(touchListener);

            airmedia.setOnClickListener(clickListener);
            message.setOnClickListener(clickListener);
            weather.setOnClickListener(clickListener);
            hotelinfo.setOnClickListener(clickListener);
            bill.setOnClickListener(clickListener);
            shopping.setOnClickListener(clickListener);
            dining.setOnClickListener(clickListener);
            vod.setOnClickListener(clickListener);
            tv.setOnClickListener(clickListener);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void checkImage(final View view, Boolean b, Animation animation)
    {
        Log.e(TAG, "checkImage: child 1"+((ViewGroup)view).getChildAt(1).getClass().getSimpleName() );
        Log.e(TAG, "checkImage: child 2"+((ViewGroup)view).getChildAt(2).getClass().getSimpleName() );
        Log.e(TAG, "checkImage: child 3"+((ViewGroup)view).getChildAt(3).getClass().getSimpleName() );

        View first_child = ((ViewGroup)view).getChildAt(1);
        View second_child = ((ViewGroup)view).getChildAt(2);
        View third_child = ((ViewGroup)view).getChildAt(3);
        if(b)
        {
            first_child.startAnimation(animation);
            first_child.setForeground(ContextCompat.getDrawable(getContext(),R.drawable.carlson_image_item_selectedv1));
            second_child.setTranslationZ(4);
            first_child.setTranslationY(3);
            third_child.setTranslationZ(2);
            view.setTranslationZ(1);
        }
        else
        {
            view.setBackground(null);
            view.setPadding(0,0,0,0 );
            view.setTranslationZ(0);
            first_child.setForeground(null);
            second_child.setBackgroundTintMode(null);
            second_child.setTranslationZ(0);
            third_child.setTranslationZ(0);
            first_child.setTranslationY(0);
            first_child.clearAnimation();
        }

    }

    final  View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            checkImage(view,b,animZoomIn);
        }
    };

    final View.OnHoverListener hover=new View.OnHoverListener() {
        @Override
        public boolean onHover(View view, MotionEvent motionEvent) {
            view.requestFocus();
            return false;
        }
    };

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
    //Todo: Temporary
    private boolean checkGuest(){
        MeshGuest guest = new MeshGuest(getActivity());
        return !guest.getName().equals("Welcome Guest");
    }
    //----------------global onClicklistener-----------------------------------------------------

    final View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id=view.getId();
            switch (id)
            {
                case R.id.rl_airmedia:
                   // if(checkGuest())
                      getListener().onClicked(Packages.DISPLAY_AIRMEDIA);
                    break;
                case R.id.rl_message:
                   // if(checkGuest())
                       getListener().onClicked(Packages.DISPLAY_MESSAGE);
                    break;
                case R.id.rl_hotelinfo:
                   // if(checkGuest())
                       getListener().onClicked(Packages.DISPLAY_NETFLIX);
                    break;
                case R.id.rl_weather:
                   // if(checkGuest())
                       getListener().onClicked(Packages.DISPLAY_FACILITYV3);
                    break;
                case R.id.rl_bill:
                   // if(checkGuest())
                      getListener().onClicked(Packages.DISPLAY_BILL);
                    break;
                case R.id.rl_shopping:
                   // if(checkGuest())
                      getListener().onClicked(Packages.DISPLAY_SHOPPING);
                    break;
                case R.id.rl_dining:
                   // if(checkGuest())
                       getListener().onClicked(Packages.DISPLAY_DINING);
                    break;
                case R.id.rl_vod:
                   // if(checkGuest())
                      getListener().onClicked(Packages.DISPLAY_VOD);
                    break;
                case R.id.rl_tv:
                    getListener().onClicked(Packages.DISPLAY_TV2);
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

    @Override
    protected void update(Object o) {

    }
}
