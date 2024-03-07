package com.ph.bittelasia.meshtv.tv.carlson.TV.View.Fragment;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.TV.Model.CarlsonChannelListAdapter;
import com.ph.bittelasia.meshtv.tv.carlson.TV.Model.CarlsonChannelListAdapterV2;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.AdapterInterface;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshArrayListCallBack;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.DataSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Listener.MeshDataListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Manager.MeshTVDataManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshListItemClickedListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshListItemSelectedListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.Query.MeshValuePair;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.CustomView.MeshTVCustomList.MeshTVCustomListView;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmEventListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Channel.MeshChannel;

import java.util.ArrayList;

/**
 * Created by ramil on 2/7/18.
 */
@Layout(R.layout.carlson_tv_list_layout)
@DataSetting(listenToChannels= true)
public class CarlsonTVList extends MeshTVFragment<MeshChannel> implements MeshDataListener,MeshRealmListener,MeshRealmEventListener
{

    @BindWidget(R.id.lv_list)
    public GridView gv_grid;

    boolean allow = true;
    MeshArrayListCallBack cb;
    public CarlsonChannelListAdapter   adapter;
    public CarlsonChannelListAdapterV2 adapterV2;

    //=====================================Lyfe Cycle===============================================

    @Override
    public void onResume() {
        super.onResume();
        MeshRealmManager.addListener(this);
        Log.i("steward","channel cat 1");
    }

    @Override
    public void onPause() {
        super.onPause();
        MeshRealmManager.removeListener(this);
        Log.i("steward","channel cat 2");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cb=(MeshArrayListCallBack)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        cb=null;
    }

    //==============================================================================================
    public ArrayList<MeshChannel> channels;

    int category = -1;
    MeshValuePair filter;


    public void setCategory(int category) {

        this.category = category;
        if(category>1)
        {
            filter.setValue(category);
            MeshRealmManager.retrieve(MeshChannel.class,this, filter);
        }
        else
        {
            MeshRealmManager.retrieve(MeshChannel.class,this);
        }
    }

    @Override
    protected void onDrawDone(View v) {
        try {
            filter = new MeshValuePair(MeshChannel.TAG_CATEGORY_ID, category);
            filter.setString(false);
            gv_grid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        Log.i("steward", "before meaured height: " + view.getHeight());
//                    if (view.getHeight()==120) {
//                        view.getLayoutParams().height = 156;
//                        view.requestLayout();
//                    } else {
//                        view.getLayoutParams().height = 120;
//                        view.requestLayout();
//                    }
                        Log.i("steward", "after meaured height: " + view.getHeight());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            gv_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    getListener().onClicked(channels.get(i));
                }
            });
            gv_grid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    view.requestFocus();
                    getListener().onSelected(adapterView.getItemAtPosition(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            MeshTVDataManager.requestData(MeshChannel.class, this);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDataUpdated(ArrayList<MeshChannel> items) {
//        adapter=new CarlsonChannelListAdapter(gv_grid.getContext(),gv_grid,R.layout.carlson_list_item_layout,items);
//        if(gv_grid!=null)
//        {
//            gv_grid.setAdapter(adapter);
//        }
        adapterV2=new CarlsonChannelListAdapterV2(getContext(),items);
        if(gv_grid!=null)
        {
            gv_grid.setAdapter(adapterV2);
        }
    }

    @Override
    protected void onNewData(Object o) {
    }

    @Override
    protected void onDataUpdated(Object o, int index) {

    }

    @Override
    protected void onDeleteData(int index) {

    }

    @Override
    protected void onClearData() {

    }

    @Override
    protected void onDataNotFound(Class c) {

    }

    @Override
    protected void refresh() {
        if(category<1)
        {
            MeshRealmManager.retrieve(MeshChannel.class,this);
        }
        else
        {
            MeshRealmManager.retrieve(MeshChannel.class,this,filter);
        }
    }

    @Override
    protected void update(MeshChannel item) {

    }

    @Override
    public void onNoNetwork(Class c) {
        allow = true;
    }

    @Override
    public void onNoData(Class c) {
        if(allow)
        {
            allow = false;
            MeshTVDataManager.requestData(MeshChannel.class,this);
        }

    }

    @Override
    public void onParseFailed(Class c, String message) {
        allow = true;
    }

    @Override
    public void onFileNotFound(Class c, String message) {
        allow = true;
    }

    @Override
    public void onDataReceived(Class c, int size) {
        allow = true;
        MeshRealmManager.retrieve(MeshChannel.class,this);
    }

    @Override
    public void onRetrieved(Class c, ArrayList<Object> results)
    {
        try {
            channels = new ArrayList<>();
            for (Object o : results) {
                channels.add((MeshChannel) o);
            }
            getListener().onSelected(channels.get(0));
            cb.meshArrayList(channels);
//        adapter=new CarlsonChannelListAdapter(gv_grid.getContext(),gv_grid,R.layout.carlson_list_item_layout,channels);
//        gv_grid.setAdapter(adapter);
            adapterV2 = new CarlsonChannelListAdapterV2(getContext(), channels);
            gv_grid.setAdapter(adapterV2);

            gv_grid.setSelection(0);
            gv_grid.setItemChecked(0,true);
            gv_grid.requestFocus();

            if (channels.size() == 1) {
                gv_grid.setNumColumns(1);
            } else {
                gv_grid.setNumColumns(channels.size());
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailed(Class c, String message) {

    }

    @Override
    public void onEmpty(Class c, String message)
    {
        if(allow)
        {
            allow = false;
//            MeshTVDataManager.requestData(MeshChannel.class,this);
        }

    }

    @Override
    public void onCleared(Class aClass) {

    }
//
//    @Override
//    public void onClick(Object o) {
//        getListener().onClicked(o);
//    }
//
//    @Override
//    public void onSelected(Object o) {
//       // getListener().onSelected(o);
//    }


    //=========================MeshRealmEventListener===============================================
    @Override
    public void onCreate(Object o) {

    }

    @Override
    public void onCreateBulk(ArrayList<Object> arrayList)
    {

        MeshRealmManager.retrieve(MeshChannel.class,this,filter);
    }

    @Override
    public void onDelete(Class aClass) {

    }

    @Override
    public void onClear(Class aClass) {

    }

    //==============================================================================================
}