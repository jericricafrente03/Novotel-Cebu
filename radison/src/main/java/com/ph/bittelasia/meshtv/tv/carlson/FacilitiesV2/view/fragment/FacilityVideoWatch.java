package com.ph.bittelasia.meshtv.tv.carlson.FacilitiesV2.view.fragment;

import android.view.View;

import com.ph.bittelasia.mesh.tv.libFragment.view.fragment.CustomDialog;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Fragment.CarlsonPreview;

public class FacilityVideoWatch extends CustomDialog {


    @Override
    public void setIDs(View view) {

    }

    @Override
    public void setContent() {
        CarlsonPreview  preview = CarlsonPreview.preview("file:///storage/emulated/0/Android/novotel_vid.mp4", 1);
        getChildFragmentManager().beginTransaction().add(R.id.ll_preview, preview, "container").commitAllowingStateLoss();
    }

    @Override
    public int setLayout() {
        return R.layout.facility_video_watch_layout;
    }

    @Override
    public int setConstraintLayout() {
        return R.id.cl_layout;
    }

    @Override
    public double setPercentageWidth() {
        return 1;
    }

    @Override
    public double setPercentageHeight() {
        return 1;
    }

}
