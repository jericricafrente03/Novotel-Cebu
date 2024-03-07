package com.ph.bittelasia.meshtv.tv.carlson.Message.View.Activity;


import android.os.Bundle;

import android.view.KeyEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment.CarlsonHotelWeather;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment.CarslonGuestInfo;
import com.ph.bittelasia.meshtv.tv.carlson.Message.View.Fragment.CarlsonMessageCompose;
import com.ph.bittelasia.meshtv.tv.carlson.Message.View.Fragment.CarlsonMessageDisplay;
import com.ph.bittelasia.meshtv.tv.carlson.Message.View.Fragment.CarlsonMessageInbox;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshTVButtons;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog.CarlsonConfirm;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Fragment.CarlsonButtons;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.ActivitySetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.AttachFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.RemoteControl.KR301KeyCode;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Activity.MeshTVActivity;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Message.MeshMessage;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Message.MeshMessageManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Message.MeshMessageReadListener;

import io.realm.Realm;
import io.realm.RealmResults;

@Layout(R.layout.carlson_vod)
@ActivitySetting()
public class CarlsonMessaging extends MeshTVActivity implements MeshTVFragmentListener {

    CarlsonMessageInbox messageInbox;
    CarlsonMessageDisplay messageDisplay;
    CarlsonButtons      messageButton;
    CarslonGuestInfo    guestInfo;
    CarlsonHotelWeather hotelWeather;

    MeshMessage meshMessage;

    @BindWidget(R.id.tv_maintitle)
    public TextView tv_sub;

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        switch (KR301KeyCode.getEquivalent(event.getKeyCode()))
        {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if(event.getAction()==KeyEvent.ACTION_UP) {
                    return true;
                }
                return true;
            case KeyEvent.KEYCODE_PROG_BLUE:
                if(event.getAction()==KeyEvent.ACTION_UP)
                {
                    CarlsonMessageCompose.dialog(.70,.70).show(getSupportFragmentManager(),"message");
                    return true;
                }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        tv_sub.setText(("MESSAGE"));
    }

    @AttachFragment(container = R.id.ll_location,tag="location",order = 2)
    public Fragment attachHotelWeather()
    {
        hotelWeather=new CarlsonHotelWeather();
        return hotelWeather;
    }

    @AttachFragment(container = R.id.ll_guest,tag = "guest",order=3)
    public Fragment attachHomeWeather()
    {
        guestInfo=new CarslonGuestInfo();
        return  guestInfo;
    }


    @AttachFragment(container = R.id.ll_display,tag="inbox",order=2)
    public Fragment attachInbox()
    {
        messageInbox=new CarlsonMessageInbox();
        return messageInbox;
    }


    @AttachFragment(container = R.id.ll_bottom,tag="button",order=3)
    public Fragment attachButton()
    {
        messageButton=new CarlsonButtons();
        return  messageButton;
    }


    @Override
    public void onClicked(Object o) {
        try
        {
            if (o instanceof MeshMessage) {

                meshMessage = (MeshMessage) o;
//                setMessageStatus(meshMessage.getId(), 2);  -->original
                MeshMessageManager.readMessage(this, meshMessage, new MeshMessageReadListener() {
                    @Override
                    public void isRead(boolean b) {

                    }
                });
                messageInbox.updateMessage(meshMessage);
                messageDisplay=CarlsonMessageDisplay.get(meshMessage);
                getSupportFragmentManager().beginTransaction().replace( R.id.ll_display,messageDisplay,"display").addToBackStack(null).commit();
            } else if (o instanceof String) {
                if (((String) o).equals(MeshTVButtons.DELETE.getButton())) {
                    if(messageInbox.message_size>0)
                    {
                        CarlsonConfirm.dialog(meshMessage, .35, .30).show(getSupportFragmentManager(), "deleteconfirm");
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onSelected(Object o) {
        if(o instanceof MeshMessage)
        {
            meshMessage=(MeshMessage)o;
        }
        messageButton.btn_delete.setText(("DELETE"));
    }

    /*Todo:Remind about message*/
    public void setMessageStatus(final int id,final int status)
    {
        Realm r = Realm.getDefaultInstance();
        r.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<MeshMessage> result = realm.where(MeshMessage.class).equalTo(MeshMessage.TAG_ID,id).findAll();
                MeshMessage message = result.get(0);
                message.setMessg_status(status);
                realm.copyToRealm(message);
                realm.close();
            }
        });
    }
}
