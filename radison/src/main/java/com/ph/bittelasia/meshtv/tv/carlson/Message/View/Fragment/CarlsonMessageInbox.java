package com.ph.bittelasia.meshtv.tv.carlson.Message.View.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.ph.bittelasia.meshtv.tv.carlson.Message.Model.CarlsonMessageListAdapter;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.DataSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Listener.MeshDataListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Message.MeshMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by ramil on 12/22/17.
 */
@Layout(R.layout.carlson_message_inbox)
@DataSetting(listenToMessage = true)
public class CarlsonMessageInbox extends MeshTVFragment<MeshMessage> implements MeshDataListener,MeshRealmListener
{
    
    public static final String TAG=CarlsonMessageInbox.class.getSimpleName()+"-steward";

    public CarlsonMessageListAdapter adapter;
    public int message_size=0;


    ArrayList<MeshMessage> meshMessages;
    int pos=0;

    @BindWidget(R.id.lv_list)
    public ListView lv_message;

    public void updateMessage(MeshMessage m)
    {
        MeshRealmManager.retrieve(MeshMessage.class,this);
    }


    @Override
    protected void onDrawDone(View view) {
        try
        {
            MeshRealmManager.retrieve(MeshMessage.class, this);
            lv_message.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    pos=i;
                    getListener().onClicked(meshMessages.get(i));
                }
            });
            lv_message.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    getListener().onSelected(meshMessages.get(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDataUpdated(ArrayList<MeshMessage> arrayList) {
        MeshRealmManager.retrieve(MeshMessage.class,this);
        Log.i(TAG,"onDataUpdated 1");
    }


    @Override
    protected void onNewData(Object o) {
        Log.i(TAG,"onNewData");
        MeshRealmManager.retrieve(MeshMessage.class,this);
    }

    @Override
    protected void onDataUpdated(Object o, int i) {
        lv_message.setSelection(i);
        Log.i(TAG,"onDataUpdated 2");
    }

    @Override
    protected void onDeleteData(int i) {
        meshMessages.remove(i);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onClearData() {
        Log.i(TAG,"onClearData");
    }

    @Override
    protected void onDataNotFound(Class aClass) {
        Log.i(TAG,"onDataNotFound");
    }

    @Override
    protected void refresh() {
        MeshRealmManager.retrieve(MeshMessage.class,this);
        Log.i(TAG,"refresh");
    }

    @Override
    protected void update(MeshMessage meshMessage) {
        Log.i(TAG,"update");
    }

    @Override
    public void onNoNetwork(Class aClass) {
        Log.i(TAG,"onNoNetwork");
    }

    @Override
    public void onNoData(Class aClass) {
        MeshRealmManager.retrieve(MeshMessage.class,this);
        Log.i(TAG,"onNoData");
    }

    @Override
    public void onParseFailed(Class aClass, String s) {
        Log.i(TAG,"onParseFailed");
    }
    @Override
    public void onFileNotFound(Class aClass, String s) {
        Log.i(TAG,"onFileNotFound");
    }

    @Override
    public void onDataReceived(Class aClass, int i) {
        MeshRealmManager.retrieve(MeshMessage.class,this);
        Log.i(TAG,"onDataReceived");
    }

    @Override
    public void onRetrieved(Class aClass, ArrayList<Object> arrayList) {
        meshMessages=new ArrayList<>();
        for(Object o:arrayList)
        {
            meshMessages.add((MeshMessage)o);
        }
        if(getContext()!=null)
        {
            try {
                Comparator<MeshMessage> messages = new Comparator<MeshMessage>() {
                    @Override
                    public int compare(MeshMessage o1, MeshMessage o2) {
                        Date d1 = null;
                        Date d2 = null;
                        try {
                            SimpleDateFormat df = new SimpleDateFormat(MeshMessage.DATE_FORMAT);
                            d1 = df.parse(o1.getMessg_datetime());
                            d2 = df.parse(o2.getMessg_datetime());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return d1.before(d2) ? 1 : -1;
                    }
                };
                Collections.sort(meshMessages,messages);
                adapter = new CarlsonMessageListAdapter(getContext(), lv_message, R.layout.carlson_message_list_item, meshMessages);
                lv_message.setAdapter(adapter);
                lv_message.setSelection(pos);
                message_size=meshMessages.size();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onFailed(Class aClass, String s) {
        Log.i(TAG,"onFailed");
    }

    @Override
    public void onEmpty(Class aClass, String s) {
        MeshRealmManager.retrieve(MeshMessage.class, this);
    }

    @Override
    public void onCleared(Class aClass) {

    }
}
