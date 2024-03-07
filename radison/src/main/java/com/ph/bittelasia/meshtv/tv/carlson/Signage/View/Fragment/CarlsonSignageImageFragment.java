package com.ph.bittelasia.meshtv.tv.carlson.Signage.View.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.DataSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.ResourceManager.MeshResourceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.Model.MeshSignage;

import java.util.ArrayList;

@Layout(R.layout.carlson_signage_image_layout)
@DataSetting()
public class CarlsonSignageImageFragment extends MeshTVFragment{

    //======================================Variable================================================
    //--------------------------------------Constants-----------------------------------------------
    public static final String TAG=CarlsonSignageImageFragment.class.getSimpleName();
    public static final String TYPE_IMAGE="image";
    public static final int    STATE_FINISH=5;
    public static CarlsonSignageImageFragment signageImageFragment;
    //----------------------------------------------------------------------------------------------

    //--------------------------------------Views---------------------------------------------------
    @BindWidget(R.id.iv_image)
    public ImageView imageView;
    //----------------------------------------------------------------------------------------------

    //---------------------------------------Instance-----------------------------------------------
    MeshSignage         signage;
    int                 position;
    boolean             isVisible=false;

    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //====================================Constructor===============================================
    //----------------------------------------------------------------------------------------------
    public static CarlsonSignageImageFragment get(MeshSignage signage,int position)
    {
        signageImageFragment=new CarlsonSignageImageFragment();
        signageImageFragment.setPosition(position);
        signageImageFragment.setSignage(signage);
        Bundle args = new Bundle();
        args.putInt("pos",position);
        args.putString("type",TYPE_IMAGE);
        signageImageFragment.setArguments(args);
        return signageImageFragment;
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //=======================================Getter=================================================
    //----------------------------------------------------------------------------------------------

    public int getPosition() {
        return position;
    }

    public MeshSignage getSignage() {
        return signage;
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //=======================================Setter=================================================
    //----------------------------------------------------------------------------------------------

    public void setPosition(int position) {
        this.position = position;
    }

    public void setSignage(MeshSignage signage) {
        this.signage = signage;
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //========================================Method================================================
    //----------------------------------------------------------------------------------------------
    public void setContent(MeshSignage signage)
    {
        if(signage!=null) {
            if(isVisible)
                MeshResourceManager.displayLiveImageFor(getContext(), imageView, signage.getMedia());
        }else
        {
            Log.i(TAG+"-image","signage is null");
        }
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================


    //====================================android.support.v4.app.Fragment===========================

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser)
        {
            isVisible=true;
            setContent(getSignage());
        }
    }

    //==============================================================================================

    //=====================================MeshTVFragment===========================================
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onDrawDone(View view) {
        setContent(getSignage());
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
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
}
