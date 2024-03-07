package com.ph.bittelasia.meshtv.tv.carlson.DigitalSignageMarsV2;

import android.util.Log;

import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Listener.MeshDataListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Manager.MeshTVDataManager;

import com.ph.bittelasia.meshtv.tv.mtvlib.SignageV2.Control.AttachSignageFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.SignageV2.MeshMediaV2;
import com.ph.bittelasia.meshtv.tv.mtvlib.SignageV2.MeshMediaZoneAssignment;
import com.ph.bittelasia.meshtv.tv.mtvlib.SignageV2.View.MeshMediaFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.SignageV2.View.MeshSignageActivity;

public class CarlsonSignageActivity extends MeshSignageActivity
{
    //=============================================Variable=========================================
    //---------------------------------------------Constant-----------------------------------------
    public static final String TAG = CarlsonSignageActivity.class.getSimpleName();
    //----------------------------------------------------------------------------------------------
    //---------------------------------------------Fragment-----------------------------------------
    MeshMediaFragment zone1;
    MeshMediaFragment zone2;
    MeshMediaFragment zone3;
    MeshMediaFragment zone4;
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //======================================AttachSignageFragment===================================
    @AttachSignageFragment(zone = 1)
    public MeshMediaFragment getZone1(MeshMediaZoneAssignment assignment,int layout)
    {
        Log.i(TAG,"Zone 1:");
        Log.i(TAG,"Layout ("+layout+")");

        if(layout==10)
        {
            zone1 = new CarlsonMediaFragment();
            zone1.setZone(assignment);
        }

        return zone1;
    }
    @AttachSignageFragment(zone = 2)
    public MeshMediaFragment getZone2(MeshMediaZoneAssignment assignment,int layout)
    {
        Log.i(TAG,"Zone 2:");
        Log.i(TAG,"Layout ("+layout+")");
        if(layout==10)
        {
            zone2 = new CarlsonMediaFragment();
            zone2.setZone(assignment);
        }
        return zone2;
    }
    @AttachSignageFragment(zone = 3)
    public MeshMediaFragment getZone3(MeshMediaZoneAssignment assignment,int layout)
    {
        Log.i(TAG,"Zone 3:");
        Log.i(TAG,"Layout ("+layout+")");
        if(layout==10)
        {
            zone3 = new CarlsonMediaFragment();
            zone3.setZone(assignment);
        }

        return zone3;
    }
    @AttachSignageFragment(zone = 4)
    public MeshMediaFragment getZone4(MeshMediaZoneAssignment assignment,int layout)
    {
        Log.i(TAG,"Zone 4:");
        Log.i(TAG,"Layout ("+layout+")");

        if(layout==10)
        {
            zone4 = new CarlsonLogoMediaFragment();
            zone4.setZone(assignment);
        }

        return zone4;
    }

    @Override
    public void attachSpecialFragment(MeshMediaZoneAssignment meshMediaZoneAssignment, MeshMediaV2 meshMediaV2) {

    }
    //==============================================================================================
}
