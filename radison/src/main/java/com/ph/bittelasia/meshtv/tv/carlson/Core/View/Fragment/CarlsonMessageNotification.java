package com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.Message.View.Activity.CarlsonMessaging;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshMessageRead;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Listener.MeshDataListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Manager.MeshTVDataManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Message.MeshMessage;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by mars on 1/22/18.
 */
@Layout(R.layout.carlson_message_notification)
public class CarlsonMessageNotification extends MeshTVFragment {

    @BindWidget(R.id.iv_status)
    public ImageView iv_status;

    @BindWidget(R.id.tv_count)
    public TextView tv_count;

    @BindWidget(R.id.tv_message)
    public TextView tv_message;

    @BindWidget(R.id.ll_message)
    public LinearLayout ll_message;

    @BindWidget(R.id.ll_parent)
    public LinearLayout ll_parent;

    int                 unread;

    /*Todo:Remind sir Mars*/
    public void checkMessage(int c)
    {
        try
        {
            if (c > 0) {
                tv_count.setText((c + ""));
                tv_message.setText((c > 1 ? "MESSAGES" : "MESSAGE"));
                iv_status.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.unread_white));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDrawDone(View view) {
        unread=0;
        unread+= MeshMessageRead.getUnreadMessage();
        if (unread > 0)
        {
            tv_count.setText((unread + ""));
            tv_message.setText((unread > 1 ? "MESSAGES" : "MESSAGE"));
            iv_status.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.unread_white));
        }
        ll_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), CarlsonMessaging.class);
                startActivityForResult(i, 100);
            }
        });

        ll_message.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View view, MotionEvent motionEvent) {

                return false;
            }
        });
        ll_parent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                    view.setBackground(getActivity().getResources().getDrawable(R.drawable.higlight_border));
                else
                    view.setBackground(getActivity().getResources().getDrawable(R.drawable.white_border));
            }
        });
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
}
