package com.ph.bittelasia.meshtv.tv.carlson.FacilitiesV2.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment.CarlsonHotelWeather;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment.CarslonGuestInfo;
import com.ph.bittelasia.meshtv.tv.carlson.FacilitiesV2.view.fragment.FacilityCategoryFragment;
import com.ph.bittelasia.meshtv.tv.carlson.FacilitiesV2.view.fragment.FacilitySlideListFragment;
import com.ph.bittelasia.meshtv.tv.carlson.FacilitiesV2.view.fragment.FacilityVideoWatch;
import com.ph.bittelasia.meshtv.tv.carlson.Movies.View.Fragment.CarlsonVodPreview;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Fragment.CarlsonButtons;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.ActivitySetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.AttachFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Activity.MeshTVActivity;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacilityCategory;


@Layout(R.layout.carlson_vod)
@ActivitySetting()
public class FacilityActivity extends MeshTVActivity implements  MeshTVFragmentListener {


    //====================================Variable==================================================
    //--------------------------------------View----------------------------------------------------
    private CarlsonButtons buttons;
    @BindWidget(R.id.tv_maintitle)
    public TextView tv_sub;
    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //====================================Fragment==================================================
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        tv_sub.setText(("HOTEL INFO"));
        buttons.setVisibility(buttons.btn_watch);
        buttons.btn_watch.setText(("WATCH VIDEO"));
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //=================================MeshTVActivity===============================================
    //----------------------------------------------------------------------------------------------
    @AttachFragment(container = R.id.ll_location,tag="location",order = 2)
    public Fragment attachHotelWeather()
    {
        return new CarlsonHotelWeather();
    }

    @AttachFragment(container = R.id.ll_guest,tag = "guest",order=3)
    public Fragment attachHomeWeather()
    {
        return  new CarslonGuestInfo();
    }

    @AttachFragment(container = R.id.ll_categories,tag="category",order=4)
    public Fragment attachCategory()
    {
        return new FacilityCategoryFragment();
    }

    @AttachFragment(container = R.id.ll_bottom,tag = "bottom",order = 5)
    public Fragment attachButtons()
    {
        buttons=new CarlsonButtons();
        return buttons;
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //======================================MeshTVFragmentListener==================================
    //----------------------------------------------------------------------------------------------
    @Override
    public void onClicked(Object o) {
        try {
            if(o instanceof String){
                new FacilityVideoWatch().show(getSupportFragmentManager(),"watch");
            }
            else if(o instanceof MeshFacilityCategory)
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FacilitySlideListFragment fragment = new FacilitySlideListFragment();
                            fragment.setCategory(((MeshFacilityCategory) o).getId());
                            getSupportFragmentManager().beginTransaction().replace(R.id.ll_display, fragment, fragment.getClass().getSimpleName()).commitAllowingStateLoss();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onSelected(Object o) {

    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
}
