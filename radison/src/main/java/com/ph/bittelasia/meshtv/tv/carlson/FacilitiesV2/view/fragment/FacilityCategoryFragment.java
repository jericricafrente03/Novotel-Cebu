package com.ph.bittelasia.meshtv.tv.carlson.FacilitiesV2.view.fragment;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import com.ph.bittelasia.mesh.tv.libFragment.control.annotation.FragmentLayout;
import com.ph.bittelasia.mesh.tv.libFragment.view.fragment.HorizontalListViewFragment;
import com.ph.bittelasia.meshtv.tv.carlson.FacilitiesV2.control.CategoryListAdapter;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Listener.MeshDataListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshListItemClickedListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmEventListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacilityCategory;

import java.util.ArrayList;

@FragmentLayout(value = R.layout.recycler_category_list)
public class FacilityCategoryFragment extends HorizontalListViewFragment implements MeshDataListener, MeshRealmEventListener, MeshRealmListener, MeshListItemClickedListener {

   private MeshTVFragmentListener cb;

    //==========================HorizontalListViewFragment==========================================
    //----------------------------------------------------------------------------------------------
    @Override
    public int getRecyclerViewID() {
        return R.id.rv_category;
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //===================================Fragment===================================================
    //----------------------------------------------------------------------------------------------


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        cb=(MeshTVFragmentListener)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        cb=null;
    }

    @Override
    public void onStart() {
        super.onStart();
        MeshRealmManager.addListener(this);
        MeshRealmManager.retrieve(MeshFacilityCategory.class,this);
    }

    @Override
    public void onStop() {
        super.onStop();
        MeshRealmManager.removeListener(this);
    }

    //----------------------------------------------------------------------------------------------
    //==============================================================================================


    //============================MeshListItemClickedListener=======================================
    //----------------------------------------------------------------------------------------------
    @Override
    public void onClicked(Object o) {
       cb.onClicked(o);
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
        MeshRealmManager.retrieve(MeshFacilityCategory.class,this);
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
        setAdapter(new CategoryListAdapter(arrayList,this));
        updateDetails();
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
}
