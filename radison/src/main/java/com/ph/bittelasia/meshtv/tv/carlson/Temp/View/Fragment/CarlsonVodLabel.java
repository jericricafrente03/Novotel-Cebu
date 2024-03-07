package com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.DataSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Constant.AppDataSource;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Listener.MeshDataListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Manager.MeshTVDataManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.App.MeshTVApp;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmEventListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVOD;

import java.util.ArrayList;

/**
 * Created by ramil on 2/8/18.
 */
@Layout(R.layout.carlson_tv_label)
@DataSetting(listenToVOD= true)
public class CarlsonVodLabel  extends MeshTVFragment<MeshVOD> implements MeshDataListener,MeshRealmListener,MeshRealmEventListener {


    @BindWidget(R.id.tv_label)
    public TextView tv_channel;


    int selected = 0;
    int id = 0;
    ArrayList<MeshVOD> vods = null;



    public void setVOD(int id)
    {
        try {
            if (tv_channel != null) {
                if(vods!=null && vods.size()>0) {
                    for(MeshVOD vod: vods) {
                        if(vod.id==id) {
                            tv_channel.setText(vod.getTitle());
                            getListener().onSelected(vod);
                        }
                    }
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void next()
    {
        selected++;
        if(selected== vods.size())
        {
            selected = 0;
        }
        update();
    }
    public void prev()
    {
        selected--;
        if(selected==-1)
        {
            selected = vods.size()-1;
        }
        update();
    }
    public void update()
    {
        try {
            if (tv_channel != null) {
                if (vods != null) {
//                    if(MeshTVApp.get().getDataSourceSetting()== AppDataSource.SERVER)
//                    {
                        for(MeshVOD vod: vods)
                        {
                            if(vod.getId()==selected)
                            {
                                tv_channel.setText(vod.getTitle());
                                getListener().onSelected(vod);
                            }
                        }
//                    }
//                    else
//                    {
//                        MeshVOD vod = vods.get(selected);
//                        id = vod.getId();
//                        tv_channel.setText(vod.getTitle());
//                        getListener().onSelected(vod);

//                    }
                }

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public MeshVOD getSelected()
    {
        return vods.get(selected);
    }

    public void setSelected(int index)
    {
        selected = index;
        update();
    }

    public void setId(int id) {
        Log.i(TAG,"ID:"+id);
        this.id = id;
        if(vods !=null)
        {
            int ctr = 0;
            for(MeshVOD ch: vods)
            {
                if(ch.getId()==id)
                {
                    selected= ctr;
                }
                ctr++;
            }
        }
        update();
        Log.i(TAG,"Index:"+selected);
    }



    @Override
    public void onNoNetwork(Class aClass) {

    }

    @Override
    protected void update(MeshVOD meshChannel) {

    }

    @Override
    public void onNoData(Class aClass) {
        MeshTVDataManager.requestData(MeshVOD.class,this);
    }

    @Override
    protected void onDrawDone(View view) {
        MeshRealmManager.retrieve(MeshVOD.class,this);
    }

    @Override
    protected void onDataUpdated(ArrayList<MeshVOD> arrayList) {

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
        MeshRealmManager.retrieve(MeshVOD.class,this);
    }

    @Override
    public void onParseFailed(Class aClass, String s) {

    }

    @Override
    public void onFileNotFound(Class aClass, String s) {

    }

    @Override
    public void onDataReceived(Class aClass, int i) {
        MeshRealmManager.retrieve(MeshVOD.class,this);
    }

    @Override
    public void onRetrieved(Class aClass, ArrayList<Object> arrayList) {
        vods = new ArrayList<>();
        int ctr =  0;
        for(Object o:arrayList)
        {
            MeshVOD ch = (MeshVOD)o;
            vods.add(ch);
            if(ch.getId()==id)
            {
                selected = ctr;
            }
            ctr++;

        }

        update();

    }

    @Override
    public void onFailed(Class aClass, String s) {

    }

    @Override
    public void onEmpty(Class aClass, String s) {
        MeshTVDataManager.requestData(MeshVOD.class,this);
    }

    @Override
    public void onCleared(Class aClass) {

    }

    @Override
    public void onResume() {
        super.onResume();
        MeshRealmManager.addListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MeshRealmManager.removeListener(this);
    }
    @Override
    public void onCreate(Object o) {

    }

    @Override
    public void onCreateBulk(ArrayList<Object> arrayList) {
        MeshRealmManager.retrieve(MeshVOD.class,this);
    }

    @Override
    public void onDelete(Class aClass) {

    }

    @Override
    public void onClear(Class aClass) {

    }
}
