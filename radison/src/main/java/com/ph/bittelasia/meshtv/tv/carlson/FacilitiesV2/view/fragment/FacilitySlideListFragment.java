package com.ph.bittelasia.meshtv.tv.carlson.FacilitiesV2.view.fragment;


import android.util.Log;
import com.ph.bittelasia.mesh.tv.libFragment.control.annotation.FragmentLayout;
import com.ph.bittelasia.mesh.tv.libFragment.control.annotation.UpdateContents;
import com.ph.bittelasia.mesh.tv.libFragment.control.model.Slide;
import com.ph.bittelasia.mesh.tv.libFragment.view.fragment.SlideFragment;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Listener.MeshDataListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmEventListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacility;
import java.util.ArrayList;

@FragmentLayout(value = R.layout.facility_display)
public class FacilitySlideListFragment extends SlideFragment implements MeshDataListener, MeshRealmEventListener, MeshRealmListener {

    //======================================Variable================================================
    //----------------------------------------------------------------------------------------------
    private ArrayList<MeshFacility> facilities;
    private int category = 1;
    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //====================================Fragment==================================================
    //----------------------------------------------------------------------------------------------
    @Override
    public void onStart() {
        super.onStart();
        MeshRealmManager.addListener(this);
        MeshRealmManager.retrieve(MeshFacility.class,this);
    }

    @Override
    public void onStop() {
        super.onStop();
        MeshRealmManager.removeListener(this);
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //==================================SlideFragment===============================================
    //----------------------------------------------------------------------------------------------

    @Override
    public int getInterval() {
        return 5;
    }

    @Override
    public int getSlideViewFlipperID() {
        return R.id.vp_slide;
    }

    @Override
    public int getNextViewID() {
        return R.id.btn_right;
    }

    @Override
    public int getPrevViewID() {
        return  R.id.btn_left;
    }

    @Override
    public int getHeadViewID() {
        return R.id.tv_head;
    }

    @Override
    public int getContentViewID() {
        return R.id.tv_content;
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //======================================MeshDataListener========================================
    //----------------------------------------------------------------------------------------------
    @Override
    public void onNoNetwork(Class aClass) {

    }

    @Override
    public void onNoData(Class aClass) {

    }

    @Override
    public void onParseFailed(Class aClass, String s) {

    }

    @Override
    public void onFileNotFound(Class aClass, String s) {

    }

    @Override
    public void onDataReceived(Class aClass, int i) {

    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //================================MeshRealmEventListener========================================
    //----------------------------------------------------------------------------------------------
    @Override
    public void onCreate(Object o) {

    }

    @Override
    public void onCreateBulk(ArrayList<Object> arrayList) {
        MeshRealmManager.retrieve(MeshFacility.class,this);
    }

    @Override
    public void onDelete(Class aClass) {

    }

    @Override
    public void onClear(Class aClass) {

    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //================================MeshRealmListener=============================================
    //----------------------------------------------------------------------------------------------
    @Override
    public void onRetrieved(Class aClass, ArrayList<Object> arrayList) {
        facilities = new ArrayList<>();
        ArrayList<Slide> slides = new ArrayList<>();
        for(Object o: arrayList){
            if(o instanceof MeshFacility)
            {
                MeshFacility facility = (MeshFacility)o;
                if(facility.getCategory_id() == category) {
                    slides.add(new Slide.Builder()
                            .name(facility.getItem_name())
                            .path(facility.getImg_uri())
                            .content(facility.getItem_description())
                            .buildSlide());
                }
            }
            if(slides.size()>0) {
                setList(slides);
                updateDetails();
            }
        }
    }

    @Override
    public void onFailed(Class aClass, String s) {

    }

    @Override
    public void onEmpty(Class aClass, String s) {

    }

    @Override
    public void onCleared(Class aClass) {

    }

    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //=====================================Method===================================================
    //----------------------------------------------------------------------------------------------
    public void setCategory(int category){
        this.category = category;
    }


}
