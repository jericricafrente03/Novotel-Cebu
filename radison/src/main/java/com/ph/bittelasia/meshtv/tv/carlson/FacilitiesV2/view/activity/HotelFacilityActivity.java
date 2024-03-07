package com.ph.bittelasia.meshtv.tv.carlson.FacilitiesV2.view.activity;

import android.util.Log;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ph.bittelasia.mesh.tv.libFragment.control.annotation.ActivityLayout;
import com.ph.bittelasia.mesh.tv.libFragment.control.annotation.AttachActivityFragment;
import com.ph.bittelasia.mesh.tv.libFragment.view.activity.HotelActivity;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment.CarlsonHotelWeather;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment.CarslonGuestInfo;
import com.ph.bittelasia.meshtv.tv.carlson.FacilitiesV2.view.fragment.FacilityCategoryFragment;
import com.ph.bittelasia.meshtv.tv.carlson.FacilitiesV2.view.fragment.FacilitySlideListFragment;
import com.ph.bittelasia.meshtv.tv.carlson.FacilitiesV2.view.fragment.FacilityVideoWatch;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Fragment.CarlsonButtons;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.AttachFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacilityCategory;

@ActivityLayout(value = R.layout.carlson_vod)
public class HotelFacilityActivity extends HotelActivity  implements MeshTVFragmentListener {

    @AttachActivityFragment(containerID = R.id.ll_location)
    public  CarlsonHotelWeather weather = new CarlsonHotelWeather();

    @AttachActivityFragment(containerID = R.id.ll_guest)
    public  CarslonGuestInfo guestInfo = new CarslonGuestInfo();

    @AttachActivityFragment(containerID = R.id.ll_categories)
    public FacilityCategoryFragment category =  new FacilityCategoryFragment();

    @AttachActivityFragment(containerID = R.id.ll_display)
    public FacilitySlideListFragment facility =new FacilitySlideListFragment();

    @AttachActivityFragment(containerID = R.id.ll_bottom)
    public CarlsonButtons buttons = new CarlsonButtons();

    @Override
    public void initialize() {
        TextView title = findViewById(R.id.tv_maintitle);
        title.setText(("HOTEL INFO"));
        buttons.setVisibility(buttons.btn_watch);
        buttons.btn_watch.setText(("WATCH VIDEO"));
    }


    @Override
    public void onClicked(Object o) {
        try {
            Log.e("steward", "onClicked: "+o.getClass());
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
}
