package com.ph.bittelasia.meshtv.tv.carlson.Movies.View.Fragment;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ph.bittelasia.meshtv.tv.carlson.Movies.Model.CarlsonVodListAdapter;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshArrayListCallBack;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Fragment.CarlsonPreview;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.DataSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Listener.MeshDataListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Manager.MeshTVDataManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.ResourceManager.MeshResourceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.Query.MeshValuePair;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmEventListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshGenre;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVOD;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by ramil on 1/23/18.
 */

@Layout(R.layout.carlson_gridview)
@DataSetting(listenToVOD = true)
public class CarlsonVodList extends MeshTVFragment<MeshVOD> implements MeshDataListener,MeshRealmListener, MeshRealmEventListener {

    //======================================Variable================================================
    //--------------------------------------View----------------------------------------------------
    @BindWidget(R.id.gv_list)
    public GridView gv_grid;
    //--------------------------------------Instance------------------------------------------------
    MeshArrayListCallBack        cb;
    ArrayList<MeshVOD>           lists;
    public ArrayList<MeshVOD>    vods;
    public CarlsonVodListAdapter adapter;
    String category=null;
    //==============================================================================================


    //=====================================Life Cycle===============================================
    @Override
    public void onResume() {
        super.onResume();
        MeshRealmManager.addListener(this);
        Log.i("steward","vod list 1");
    }

    @Override
    public void onPause() {
        super.onPause();
        MeshRealmManager.removeListener(this);
        Log.i("steward","vod list 2");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cb=(MeshArrayListCallBack)context;
        Log.i("steward","vod list 3");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        cb=null;
        Log.i("steward","vod list 4");
    }
    //==============================================================================================


    //=====================================Method===================================================

    public void setCategory(String category) {
        this.category = category;
        MeshRealmManager.retrieve(MeshVOD.class,this);
    }
    //==============================================================================================



    //===================================MeshTVFragment=============================================
    @Override
    protected void onDrawDone(View v) {
        Log.i("steward","vod list 5");
    }

    @Override
    protected void onDataUpdated(ArrayList<MeshVOD> items) {
        Log.i("steward","vod list 6");
    }

    @Override
    protected void onNewData(Object o) {
        Log.i("steward","vod list 7");
    }

    @Override
    protected void onDataUpdated(Object o, int index) {
        if(adapter!=null)
        {
            adapter.notifyDataSetChanged();
            gv_grid.invalidateViews();
        }
        Log.i("steward","vod list 8");
    }

    @Override
    protected void onDeleteData(int index) {
        Log.i("steward","vod list 9");
    }

    @Override
    protected void onClearData() {
        Log.i("steward","vod list 10");
    }

    @Override
    protected void onDataNotFound(Class c) {
        Log.i("steward","vod list 11");
    }

    @Override
    protected void refresh() {
        MeshRealmManager.retrieve(MeshVOD.class,this);
        Log.i("steward","vod list 12");
    }

    @Override
    protected void update(MeshVOD item) {
        Log.i("steward","vod list 13");
    }
    //==============================================================================================
    //================================MeshDataListener==============================================

    @Override
    public void onNoNetwork(Class c) {
        Log.i("steward","vod list 14");
    }

    @Override
    public void onNoData(Class c) {
        MeshTVDataManager.requestData(MeshVOD.class,this);
        Log.i("steward","vod list 15");
    }

    @Override
    public void onParseFailed(Class c, String message) {
        Log.i("steward","vod list 16");
    }

    @Override
    public void onFileNotFound(Class c, String message) {
        Log.i("steward","vod list 17");
    }

    @Override
    public void onDataReceived(Class c, int size) {
        MeshRealmManager.retrieve(MeshVOD.class,this);
        Log.i("steward","vod list 18");
    }
    //==============================================================================================

    //===============================MeshRealmListener==============================================
    @Override
    public void onRetrieved(Class c, ArrayList<Object> results)
    {
        lists = new ArrayList<>();
        for(Object o:results)
        {
            MeshVOD vod = (MeshVOD) o;
            if (category != null) {
                if (MeshGenre.checkGenre(vod, category)) {
                    lists.add(vod);
                }
            } else {
                lists.add(vod);
            }
        }
        getListener().onSelected(lists.get(0));
        cb.meshArrayList(lists);
        adapter=new CarlsonVodListAdapter(getActivity(),gv_grid,R.layout.carlson_grid_list_item,lists);
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
        Log.i("steward","gv size: "+gv_grid.getChildCount());
    }

    @Override
    public void onFailed(Class c, String message) {
        Log.i("steward","vod list 19");
    }

    @Override
    public void onEmpty(Class c, String message)
    {
        MeshTVDataManager.requestData(MeshVOD.class,this);
        Log.i("steward","vod list 20");
    }

    @Override
    public void onCleared(Class aClass) {

    }
    //==============================================================================================


    //================================MeshRealmEventListener========================================
    @Override
    public void onCreate(Object o) {
        Log.i("steward","vod list 21");
    }

    @Override
    public void onCreateBulk(ArrayList<Object> arrayList) {
        MeshRealmManager.retrieve(MeshVOD.class,this);
        Log.i("steward","vod list 22");
    }

    @Override
    public void onDelete(Class aClass) {
        Log.i("steward","vod list 23");
    }

    @Override
    public void onClear(Class aClass) {
        Log.i("steward","vod list 24");
    }
    //==============================================================================================
}