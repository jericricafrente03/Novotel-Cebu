package com.ph.bittelasia.meshtv.tv.carlson.Signage.View.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextClock;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Signage.Model.CarlsonViewPagerAdapter;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.DataSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Listener.MeshDataListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.Util.MeshTVTimeManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmEventListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.Control.CustomViewPagerEventListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.Control.MeshSignageRuntimeListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.Control.MeshSignageTimer;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.Model.MeshCompany;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.Model.MeshSignage;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.View.CustomViewPager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Layout(R.layout.carlson_signage_parent_layout)
@DataSetting()
public class CarlsonSignageParentFragment  extends MeshTVFragment<MeshSignage> implements
        MeshDataListener,MeshRealmListener,MeshRealmEventListener,CustomViewPagerEventListener,
        MeshSignageRuntimeListener{

    //=======================================Variables==============================================
    //---------------------------------------Constants----------------------------------------------
    public static final String TAG=CarlsonSignageParentFragment.class.getSimpleName();
    //----------------------------------------------------------------------------------------------

    //-----------------------------------------Views------------------------------------------------
    @BindWidget(R.id.vp)
    public View vp;

    @BindWidget(R.id.tv_time)
    public TextClock tv_time;
    //----------------------------------------------------------------------------------------------

    //----------------------------------------Instance----------------------------------------------
    CustomViewPager                      viewPager;
    CarlsonViewPagerAdapter              viewPagerAdapter;
    ArrayList<MeshSignage>               sinagelist;
    ArrayList<Fragment>                  fragments;
    CarlsonSignageImageFragment          signageImageFragment;
    CarlsonSignageVideoFragment          signageVideoFragment;
    MeshSignageTimer                     meshSignageTimer;
    //----------------------------------------------------------------------------------------------

    //==============================================================================================


    @Override
    public void onResume() {
        super.onResume();
        meshSignageTimer=new MeshSignageTimer(Long.MAX_VALUE,6000,this);
        meshSignageTimer.start();
    }

    //==================================CarlsonSignageParentFragment================================
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onDrawDone(View view) {
        viewPager           =(CustomViewPager)vp;
        viewPager.setPagingEnabled(true);
        viewPager.setListener(this);
        Log.i(TAG,"onDrawDone");
        MeshRealmManager.retrieve(MeshSignage.class,this);
    }

    @Override
    protected void onDataUpdated(ArrayList<MeshSignage> arrayList) {
        Log.i(TAG,"onDataUpdated size: "+arrayList.size());
    }

    @Override
    protected void onNewData(Object o) {
        Log.i(TAG,"onNewObject o:"+o);
    }

    @Override
    protected void onDataUpdated(Object o, int i) {
        Log.i(TAG,"onDataUpdated o: "+o+" -> i: "+i);
    }

    @Override
    protected void onDeleteData(int i) {
        Log.i(TAG,"onDeleteData i: "+i);
    }

    @Override
    protected void onClearData() {
        Log.i(TAG,"onClearData");
    }

    @Override
    protected void onDataNotFound(Class aClass) {
        Log.i(TAG,"onDataNotFound aClass: "+aClass.getSimpleName());
    }

    @Override
    protected void refresh() {
        Log.i(TAG,"onRefresh");
    }

    @Override
    protected void update(MeshSignage signage) {
        Log.i(TAG,"update: signage: "+signage.getMedia());
    }

    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //=========================================MeshDataListener=====================================
    //----------------------------------------------------------------------------------------------
    @Override
    public void onNoNetwork(Class aClass) {
        Log.i(TAG,"onNoNetwork aClass: "+aClass.getSimpleName());
    }

    @Override
    public void onNoData(Class aClass) {
        Log.i(TAG,"onNoData aClass: "+aClass.getSimpleName());
    }

    @Override
    public void onParseFailed(Class aClass, String s) {
        Log.i(TAG,"onParseFailed: aClass: "+aClass.getSimpleName()+" ->s:"+s);
    }

    @Override
    public void onFileNotFound(Class aClass, String s) {
        Log.i(TAG,"onFileNotFound aClass: "+aClass.getSimpleName()+" ->s:"+s);
    }

    @Override
    public void onDataReceived(Class aClass, int i) {
        Log.i(TAG,"onDataReceived aClass: "+aClass.getSimpleName()+" -> i:"+i);
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //========================================MeshRealmListener=====================================
    //----------------------------------------------------------------------------------------------
    @Override
    public void onRetrieved(Class aClass, ArrayList<Object> arrayList) {
        if(aClass==MeshSignage.class)
        {
            Log.i(TAG,"onRetrieved aClass: "+aClass.getSimpleName()+" -> size: "+arrayList.size());
            sinagelist          = new ArrayList<>();
            fragments           = new ArrayList<>();
            for(Object o:arrayList)
            {
                sinagelist.add((MeshSignage)o);
            }
            for(int x=0;x<sinagelist.size();x++){
                if(sinagelist.get(x).getFileType().toLowerCase().equals(MeshSignage.TYPE_IMAGE.toLowerCase())) {
                    signageImageFragment     = CarlsonSignageImageFragment.get(sinagelist.get(x),x);
                    fragments.add(signageImageFragment);
                }
                else {
                    signageVideoFragment = CarlsonSignageVideoFragment.get(sinagelist.get(x),x);
                    fragments.add(signageVideoFragment);
                }
            }

            if(fragments.size()>0)
            {
                viewPagerAdapter=new CarlsonViewPagerAdapter(getChildFragmentManager(),fragments);
                viewPager.setAdapter(viewPagerAdapter);
            }
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    Log.i(TAG,"onPageScrolled: position: "+position);
                    if(fragments.size()>0 && sinagelist.size()>0) {
                        if (fragments.get(position) instanceof CarlsonSignageVideoFragment) {
                            CarlsonSignageVideoFragment preview = (CarlsonSignageVideoFragment) fragments.get(position);
                            preview.setSignage(sinagelist.get(position));
                            preview.play();
                        }
                        else if(fragments.get(position) instanceof CarlsonSignageImageFragment)
                        {
                            CarlsonSignageImageFragment signage=(CarlsonSignageImageFragment)fragments.get(position);
                            signage.setSignage(sinagelist.get(position));
                        }
                    }
                }

                @Override
                public void onPageSelected(int position) {
                    Log.i(TAG,"page selected: position: "+position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    Log.i(TAG,"onPageScrollStateChanged: state: "+state);
                }
            });
        }
    }

    @Override
    public void onFailed(Class aClass, String s) {
        Log.i(TAG,"onFailed aClass: "+aClass.getSimpleName()+" -> s: "+s);
    }

    @Override
    public void onEmpty(Class aClass, String s) {
        Log.i(TAG,"onEmpty aClass:"+aClass.getSimpleName()+" -> s: "+s);
    }

    @Override
    public void onCleared(Class aClass) {
        Log.i(TAG,"onCleared aClass: "+aClass.getSimpleName());
    }

    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //===========================================MeshRealmEventListener=============================
    //----------------------------------------------------------------------------------------------
    @Override
    public void onCreate(Object o) {
        Log.i(TAG,"onCreate o: "+o);
    }

    @Override
    public void onCreateBulk(ArrayList<Object> arrayList) {
        Log.i(TAG,"onCreateBulk size:"+arrayList.size());
    }

    @Override
    public void onDelete(Class aClass) {
        Log.i(TAG,"onDeleteData aClass: "+aClass.getSimpleName());
    }

    @Override
    public void onClear(Class aClass) {
        Log.i(TAG,"onClear aClass: "+aClass.getSimpleName());
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //======================================CustomViewPagerEvenListener=============================
    //----------------------------------------------------------------------------------------------

    @Override
    public void isClicked(boolean b) {
        if(b)
        {
            Log.i(TAG,"clicked: true");
            getListener().onClicked(b);
        }
    }

    @Override
    public void isHovered(boolean b) {
        if(b)
        {
            Log.i(TAG,"hovered: true");
        }
    }

    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //=======================================MeshSignageRuntimeListener=============================
    //----------------------------------------------------------------------------------------------
    @Override
    public ArrayList<MeshSignage> getSignageList() {
        if(sinagelist!=null && sinagelist.size()>0)
           return sinagelist;
        return null;
    }

    @Override
    public ArrayList<Fragment> getFragments() {
        if(fragments!=null && fragments.size()>0)
            return fragments;
        return null;
    }

    @Override
    public String getDateFormat() {
        return "yyyy-MMM-dd HH:mm";
    }

    @Override
    public String getTimeZone() {
        return MeshTVTimeManager.getTimeZoneName("PH");
    }

    @Override
    public ViewPager getViewPager() {
        if(viewPager!=null)
            return viewPager;
        return null;
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
}
