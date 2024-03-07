package com.ph.bittelasia.meshtv.tv.carlson.DigitalSignage;

import android.util.Log;
import android.view.KeyEvent;

import androidx.fragment.app.Fragment;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.ActivitySetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.AttachFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Activity.MeshTVActivity;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.Control.MeshDigitalSigangeVideoListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.Model.MeshSignage;

@Layout(R.layout.bittel_layout_1)
@ActivitySetting()
public class CarlsonDigitalSignageActivity extends MeshTVActivity implements MeshTVFragmentListener, MeshDigitalSigangeVideoListener
{
    //==========================================Variable============================================
    //------------------------------------------Constant--------------------------------------------
    public static final String TAG = CarlsonDigitalSignageActivity.class.getSimpleName();
    //----------------------------------------------------------------------------------------------
    //------------------------------------------Fragment--------------------------------------------
    CarlsonDigitalSignageScroll scrollFragment;
    CarlsonDigitalSignageList listFragment;
    CarlsonDigitalSignageImage imageFragment;
    CarlsonDigitalSignageWeather weatherFragment;
    CarlsonDigitalSignageDescription descriptionFragment;
    CarlsonDigitalSignageTitle digitalSignageTitle;
    CarlsonDigitalSignagePreview prev;
    //----------------------------------------------------------------------------------------------
    //==============================================================================================


    //==============================================================================================
    //=======================================AttachFragment=========================================
    @AttachFragment(container = R.id.ll_title,tag = "TITLE",order = 0)
    public Fragment attachTitle() {
        digitalSignageTitle = new CarlsonDigitalSignageTitle();
        return digitalSignageTitle;
    }
    @AttachFragment(container = R.id.ll_time,tag = "TIME",order = 1)
    public Fragment attachTime() {
        scrollFragment = new CarlsonDigitalSignageScroll();
        return scrollFragment;
    }
    @AttachFragment(container = R.id.ll_weather,tag = "WEATHER",order = 2)
    public Fragment attachWeather() {
        weatherFragment = new CarlsonDigitalSignageWeather();
        return weatherFragment;
    }
    @AttachFragment(container = R.id.ll_description,tag = "List",order = 3)
    public Fragment attachList() {
        listFragment = new CarlsonDigitalSignageList();
        return listFragment;
    }
    @AttachFragment(container = R.id.ll_list,tag = "DESC",order = 4)
    public Fragment attachDescription() {
        descriptionFragment = new CarlsonDigitalSignageDescription();
        return descriptionFragment;
    }
    //==============================================================================================
    //=========================================Event================================================

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {
        switch (event.getAction())
        {
            case KeyEvent.ACTION_UP:

                switch (event.getKeyCode())
                {
                    case 8:
                        MeshSignage signage = new MeshSignage();
                        signage.setDisplay_name("This is a test Signage");
                        digitalSignageTitle.setSignage(signage);
                        return true;
                }

                break;
        }
        return super.dispatchKeyEvent(event);

    }
    //==============================================================================================
    //====================================MeshTVFragmentListener====================================
    @Override
    public void onClicked(Object o)
    {



    }
    @Override
    public void onSelected(Object o)
    {
        if(o instanceof  MeshSignage)
        {
            MeshSignage signage = (MeshSignage) o;
            descriptionFragment.setSignage(signage);
            digitalSignageTitle.setSignage(signage);
            Log.i(TAG,"Type Image "+signage.getFileType());
            if(signage.getFileType().equals(MeshSignage.TYPE_IMAGE))
            {
                Log.i(TAG,"Type Image");
                if(getSupportFragmentManager().findFragmentById(R.id.ll_media)!=null)
                {
                    Log.i(TAG,"Removing Existing Fragment");
                    getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.ll_media)).commit();
                }
                else
                {
                    Log.i(TAG,"Adding a new fragment");
                }

                imageFragment= new CarlsonDigitalSignageImage();

                getSupportFragmentManager().beginTransaction().add(R.id.ll_media,imageFragment,"IMAGE").commit();
                imageFragment.setSignage(signage);

            }
            else if(signage.getFileType().equals(MeshSignage.TYPE_VIDEO))
            {
                Log.i(TAG,"Type Video");

                if(getSupportFragmentManager().findFragmentById(R.id.ll_media)!=null)
                {
                    Log.i(TAG,"Removing Existing Fragment");
                    getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.ll_media)).commit();
                }
                else
                {
                    Log.i(TAG,"Adding a new fragment");
                }

                prev= CarlsonDigitalSignagePreview.get(signage);
                prev.setListener(this);
                getSupportFragmentManager().beginTransaction().add(R.id.ll_media,prev,"IMAGE").commit();
            }
        }
    }
    //==============================================================================================


    @Override
    public void onBuffering() {

    }

    @Override
    public void onIdle() {

    }

    @Override
    public void onReady() {

    }

    @Override
    public void onEnd() {
        if(listFragment!=null)
        {
            listFragment.update();
        }
    }
}
