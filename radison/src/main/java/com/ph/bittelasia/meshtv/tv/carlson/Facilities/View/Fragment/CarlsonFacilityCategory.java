package com.ph.bittelasia.meshtv.tv.carlson.Facilities.View.Fragment;

import android.view.View;

import com.ph.bittelasia.meshtv.tv.carlson.Facilities.Model.CarlsonFacilityCategoryAdapter;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.AdapterInterface;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.DataSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Listener.MeshDataListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Manager.MeshTVDataManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Parser.MeshParser;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshListItemClickedListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshListItemSelectedListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.Util.MeshRawReader;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.CustomView.MeshTVCustomList.MeshTVCustomListView;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Data.Model.MeshData;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacilityCategory;

import java.util.ArrayList;

/**
 * Created by ramil on 2/12/18.
 */
@Layout(R.layout.carlson_category_layout)
@DataSetting(listenToFacilityCategory = true)
public class CarlsonFacilityCategory extends MeshTVFragment<MeshFacilityCategory> implements MeshRealmListener,MeshDataListener,AdapterInterface {
    
    ArrayList<MeshFacilityCategory>      categories;
    CarlsonFacilityCategoryAdapter       adapter;
    @BindWidget(R.id.lv_tab)
    public MeshTVCustomListView lv_categories;


    protected void onDrawDone(View view) {
        try {
            String category=MeshRawReader.read(R.raw.get_facility_category);
            categories = new ArrayList<>();
            categories.addAll(MeshParser.parseFacilityCategories(category));
            adapter = new CarlsonFacilityCategoryAdapter(categories,this);
            if (lv_categories != null) {
                lv_categories.setAdapter(adapter);
            }
            if (categories.size() > 0) {
                setSelectedItem(categories.get(0));
            }
            if(categories.get(0)!=null)
             getListener().onSelected(categories.get(0));


//            MeshRealmManager.retrieve(MeshFacilityCategory.class, this);
            lv_categories.setSelectedListener(new MeshListItemSelectedListener() {
                @Override
                public void onSelected(Object o) {
                    if(getListener()!=null)
                    {
                        getListener().onSelected(o);
                    }
                }
            });
            lv_categories.setClickListener(new MeshListItemClickedListener() {
                @Override
                public void onClicked(Object o) {
                    if(getListener()!=null)
                    {
                        getListener().onClicked(o);
                    }
                }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDataUpdated(ArrayList<MeshFacilityCategory> arrayList) {
        adapter = new CarlsonFacilityCategoryAdapter(arrayList,this);
        if(lv_categories!=null)
        {
            lv_categories.setAdapter(adapter);
        }
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
    protected void update(MeshFacilityCategory meshShoppingCategory) {

    }

    //----------------------MeshDataListener----------------------------------------------
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
//        MeshRealmManager.retrieve(MeshFacilityCategory.class,this);
    }
    //---------------------MeshRealmListener--------------------------------------------------
    @Override
    public void onRetrieved(Class aClass, ArrayList<Object> arrayList) {
        try {
            categories = new ArrayList<>();
            for (Object o : arrayList) {
                categories.add((MeshFacilityCategory) o);
            }
            adapter = new CarlsonFacilityCategoryAdapter(categories,this);
            if (lv_categories != null) {
                lv_categories.setAdapter(adapter);
            }
            if (categories.size() > 0) {
                setSelectedItem(categories.get(0));
            }
            getListener().onSelected(categories.get(0));
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailed(Class aClass, String s) {


    }

    @Override
    public void onEmpty(Class aClass, String s) {
//        MeshTVDataManager.requestData(MeshFacilityCategory.class,this);
    }

    @Override
    public void onCleared(Class aClass) {

    }

    @Override
    public void onClick(Object o) {
        getListener().onClicked(o);
    }

    @Override
    public void onSelected(Object o) {

    }
}
