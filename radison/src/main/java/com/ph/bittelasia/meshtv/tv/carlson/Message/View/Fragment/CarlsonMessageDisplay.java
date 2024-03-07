package com.ph.bittelasia.meshtv.tv.carlson.Message.View.Fragment;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Listener.MeshDataListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Message.MeshMessage;

import java.util.ArrayList;

/**
 * Created by ramil on 12/22/17.
 */
@Layout(R.layout.carlson_message_display)
public class CarlsonMessageDisplay extends MeshTVFragment implements MeshDataListener,MeshRealmListener {

    @BindWidget(R.id.tv_from)
    public TextView tv_from;

    @BindWidget(R.id.tv_subject)
    public TextView tv_subject;

    @BindWidget(R.id.tv_datetime)
    public TextView tv_datetime;

    @BindWidget(R.id.tv_message)
    public TextView tv_message;

    MeshMessage meshMessage;

    public static CarlsonMessageDisplay d;
    public static CarlsonMessageDisplay get(MeshMessage message)
    {
        d=new CarlsonMessageDisplay();
        d.meshMessage=message;
        return d;
    }

    public void showMessage(MeshMessage message)
    {
        try
        {
            tv_from.setText(message.getMessg_from());
            tv_subject.setText(message.getMessg_subject());
            tv_datetime.setText(message.getMessg_datetime());
            tv_message.setText(Html.fromHtml(message.getMessg_text()));
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public void onNoNetwork(Class aClass) {

    }

    @Override
    protected void update(Object o) {

    }

    @Override
    public void onNoData(Class aClass) {

    }

    @Override
    protected void onDrawDone(View view) {
        try
        {
            showMessage(meshMessage);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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
    public void onParseFailed(Class aClass, String s) {

    }

    @Override
    public void onFileNotFound(Class aClass, String s) {

    }

    @Override
    public void onDataReceived(Class aClass, int i) {

    }

    @Override
    public void onRetrieved(Class aClass, ArrayList<Object> arrayList) {

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
}
