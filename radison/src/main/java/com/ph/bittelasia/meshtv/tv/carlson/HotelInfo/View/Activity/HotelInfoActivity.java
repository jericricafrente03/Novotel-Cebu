package com.ph.bittelasia.meshtv.tv.carlson.HotelInfo.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Activity.CarlsonActivity;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment.CarlsonHotelWeather;
import com.ph.bittelasia.meshtv.tv.carlson.FacilitiesV2.view.fragment.FacilityVideoWatch;
import com.ph.bittelasia.meshtv.tv.carlson.HotelInfo.View.Fragment.FacilityFragment;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshArrayListCallBack;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.ActivitySetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.AttachFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Activity.MeshTVActivity;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Dining.MeshFood;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacility;

import java.util.ArrayList;


@Layout(R.layout.carlson_vod)
@ActivitySetting()
public class HotelInfoActivity extends CarlsonActivity implements MeshTVFragmentListener, MeshArrayListCallBack<MeshFacility>
{

    FacilityFragment facilityFragment;

    @AttachFragment(container = R.id.ll_display,tag="location",order = 1)
    public Fragment attachFacilityFragment()
    {
        Log.i("Mars1102","attachFacilityFragment");
        facilityFragment=new FacilityFragment();
        return facilityFragment;
    }



    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {
        Log.i("Mars1102","dispatchKeyEvent");

        try
        {
            switch (event.getAction())
            {
                case KeyEvent.ACTION_DOWN:
                    switch (event.getKeyCode()) {
                        case KeyEvent.KEYCODE_BACK:
                            return true;
                    }
                    break;
                case KeyEvent.ACTION_UP:
                    switch (event.getKeyCode()) {
                        case KeyEvent.KEYCODE_BACK:
                            super.onBackPressed();
                            return true;
                    }
                    break;
                default:
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Mars1102","onStart");

        new FacilityVideoWatch().show(getSupportFragmentManager(),"watch");
    }

    @Override
    public void onClicked(Object o) {
        Log.i("Mars1102","onClicked");

    }

    @Override
    public void onSelected(Object o) {
        Log.i("Mars1102","onSelected");

    }

    @Override
    public void meshArrayList(ArrayList<MeshFacility> list) {
        Log.i("Mars1102","meshArrayList");

    }
}
