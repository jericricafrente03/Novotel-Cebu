package com.ph.bittelasia.meshtv.tv.carlson.DigitalSignage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.TextClock;

import com.ph.bittelasia.meshtv.tv.carlson.Core.App.Carlson;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.DataSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.CustomView.CustomScrollingTextView.MeshTVScrollingTextView;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.Control.SignagePreference;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.View.Default.MeshDigitalSignageScroll;
@Layout(R.layout.bittel_layout_1_time_scroll)
@DataSetting()
public class CarlsonDigitalSignageScroll extends MeshDigitalSignageScroll
{
    //===========================================Variable===========================================
    //-------------------------------------------Constant-------------------------------------------
    public static final String TAG = CarlsonDigitalSignageScroll.class.getSimpleName();
    //----------------------------------------------------------------------------------------------
    //---------------------------------------------View---------------------------------------------
    @BindWidget(R.id.tc_time)
    public TextClock tc_time;
    @BindWidget(R.id.tv_scroll)
    public MeshTVScrollingTextView tv_scroll;
    //----------------------------------------------------------------------------------------------
    //-------------------------------------------Instance-------------------------------------------
    BroadcastReceiver update = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(tv_scroll!=null)
            {
                tv_scroll.setText(SignagePreference.getMessage());
            }
        }
    };
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //==========================================LifeCYcle===========================================

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(update, new IntentFilter(Carlson.TAG_RELOAD_SCROLL));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(update);
    }

    //==============================================================================================
    //==========================================MeshDigitalSignageScroll============================
    @Override
    public MeshTVScrollingTextView getScrollingTextView()
    {
        tv_scroll.setText(SignagePreference.getMessage());
        return tv_scroll;
    }

    @Override
    public TextClock getTextClock() {
        return tc_time;
    }
    //==============================================================================================
}
