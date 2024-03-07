package com.ph.bittelasia.meshtv.tv.carlson.Dining.View.Fragment;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ph.bittelasia.meshtv.tv.carlson.Dining.Model.CarlsonDiningListAdapter;
import com.ph.bittelasia.meshtv.tv.carlson.Dining.View.Activity.CarlsonDining;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshArrayListCallBack;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.DataSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Listener.MeshDataListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Manager.MeshTVDataManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.Query.MeshValuePair;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmEventListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Dining.MeshFood;

import java.util.ArrayList;

/**
 * Created by ramil on 1/23/18.
 */

@Layout(R.layout.carlson_gridview)
@DataSetting(listenToVOD = true)
public class CarlsonDiningList extends MeshTVFragment<MeshFood> implements MeshDataListener,MeshRealmListener, MeshRealmEventListener {

    //======================================Variable================================================
    public static final String TAG= CarlsonDiningList.class.getSimpleName()+"-steward";
    //--------------------------------------View----------------------------------------------------
    @BindWidget(R.id.gv_list)
    public GridView gv_grid;
    //--------------------------------------Instance------------------------------------------------
    MeshArrayListCallBack      cb;
    ArrayList<MeshFood>        lists;
    int                        category;
    MeshValuePair              filter;
    public ArrayList<MeshFood> vods;
    public CarlsonDiningListAdapter   adapter;
    //==============================================================================================


    //=====================================Life Cycle===============================================
    @Override
    public void onResume() {
        super.onResume();
        MeshRealmManager.addListener(this);
        Log.i(TAG,"din list 1");
    }

    @Override
    public void onPause() {
        super.onPause();
        MeshRealmManager.removeListener(this);
        Log.i(TAG,"din list 2");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cb=(MeshArrayListCallBack)context;
        Log.i(TAG,"din list 3");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        cb=null;
        Log.i(TAG,"din list 4");
    }
    //==============================================================================================


    //=====================================Method===================================================
    public void setCategory(int category) {

        this.category = category;
        if(category>1)
        {
            filter.setValue(category);
            MeshRealmManager.retrieve(MeshFood.class,this, filter);
        }
        else
        {
            MeshRealmManager.retrieve(MeshFood.class,this);
        }

    }
    //==============================================================================================



    //===================================MeshTVFragment=============================================
    @Override
    protected void onDrawDone(View v) {
        filter = new MeshValuePair(MeshFood.TAG_CATEGORY_ID,category);
        filter.setString(false);
        Log.i(TAG,"din list 5");
    }

    @Override
    protected void onDataUpdated(ArrayList<MeshFood> items) {
        Log.i(TAG,"din list 6");
    }

    @Override
    protected void onNewData(Object o) {
        Log.i(TAG,"din list 7");
    }

    @Override
    protected void onDataUpdated(Object o, int index) {
        Log.i(TAG,"din list 8");
    }

    @Override
    protected void onDeleteData(int index) {
        Log.i(TAG,"din list 9");
    }

    @Override
    protected void onClearData() {
        Log.i(TAG,"din list 10");
    }

    @Override
    protected void onDataNotFound(Class c) {
        Log.i(TAG,"din list 11");
    }

    @Override
    protected void refresh() {
        if(category<1)
        {
            MeshRealmManager.retrieve(MeshFood.class,this);
        }
        else
        {
            MeshRealmManager.retrieve(MeshFood.class,this,filter);
        }
        Log.i(TAG,"din list 12");
    }

    @Override
    protected void update(MeshFood item) {
        Log.i(TAG,"din list 13");
    }
    //==============================================================================================
    //================================MeshDataListener==============================================

    @Override
    public void onNoNetwork(Class c) {
        Log.i(TAG,"din list 14");
    }

    @Override
    public void onNoData(Class c) {
        MeshTVDataManager.requestData(MeshFood.class,this);
        Log.i(TAG,"din list 15");
    }

    @Override
    public void onParseFailed(Class c, String message) {
        Log.i(TAG,"din list 16");
    }

    @Override
    public void onFileNotFound(Class c, String message) {
        Log.i(TAG,"din list 17");
    }

    @Override
    public void onDataReceived(Class c, int size) {
        MeshRealmManager.retrieve(MeshFood.class,this);
        Log.i(TAG,"din list 18");
    }
    //==============================================================================================

    //===============================MeshRealmListener==============================================
    @Override
    public void onRetrieved(Class c, ArrayList<Object> results)
    {
        lists = new ArrayList<>();
        for(Object o:results)
        {
            lists.add((MeshFood)o);
        }
        getListener().onSelected(lists.get(0));
        cb.meshArrayList(lists);
        adapter=new CarlsonDiningListAdapter(getActivity(),gv_grid,R.layout.carlson_grid_list_item,lists);
        gv_grid.setAdapter(adapter);
        gv_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getListener().onClicked(lists.get(i));
            }
        });
        gv_grid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getListener().onSelected(lists.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                getListener().onSelected(lists.get(0));
            }
        });
        gv_grid.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View view, MotionEvent motionEvent) {
                view.requestFocus();
                return false;
            }
        });
        adapter.setSelected(0);
        adapter.notifyDataSetChanged();
        Log.i(TAG,"gv size: "+gv_grid.getChildCount());
    }

    @Override
    public void onFailed(Class c, String message) {
        Log.i(TAG,"din list 19");
    }

    @Override
    public void onEmpty(Class c, String message)
    {
        if(c==MeshFood.class) {
            MeshTVDataManager.requestData(MeshFood.class, this);
            Log.i(TAG, "din list 20");
        }
    }

    @Override
    public void onCleared(Class aClass) {

    }
    //==============================================================================================


    //================================MeshRealmEventListener========================================
    @Override
    public void onCreate(Object o) {
        Log.i(TAG,"din list 21");
    }

    @Override
    public void onCreateBulk(ArrayList<Object> arrayList) {
//        adapter=new CarlsonDiningListAdapter(getActivity(),gv_grid,R.layout.carlson_grid_list_item,lists);
        if(arrayList.get(0) instanceof MeshFood) {
            if(category>1)
            {
                filter.setValue(category);
                MeshRealmManager.retrieve(MeshFood.class,this, filter);
            }
            else
            {
                MeshRealmManager.retrieve(MeshFood.class,this);
            }
            Log.i(TAG, "din list 22");
        }
    }

    @Override
    public void onDelete(Class aClass) {
        Log.i(TAG,"din list 23");
    }

    @Override
    public void onClear(Class aClass) {
        Log.i(TAG,"din list 24");
    }
    //==============================================================================================
}