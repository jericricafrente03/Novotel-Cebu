package com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment;

import android.view.View;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Listener.MeshGuestListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Manager.MeshTVPreferenceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Model.MeshGuest;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;

import java.util.ArrayList;

/**
 * Created by ramil on 2/6/18.
 */
@Layout(R.layout.carlson_guest_info_layout_home)
public class CarslonGuestInfo extends MeshTVFragment<MeshGuest> implements MeshGuestListener {


    @BindWidget(R.id.tv_guest)
    public TextView tv_guest;

    @BindWidget(R.id.tv_roomno)
    public TextView tv_roomno;


    @Override
    public void checkIn(MeshGuest guest)
    {
        setSelectedItem(guest);
        if(tv_guest!=null)
        {
           // tv_guest.setText("Guest: "+getSelectedItem().getFirstname()+ " "+ getSelectedItem().getLastname());
            tv_guest.setText("Guest: Pavan Gidwani");
        }
    }
    @Override
    public void onGuestUpdate(MeshGuest guest)
    {
        setSelectedItem(guest);
        if(tv_guest!=null)
        {
            tv_guest.setText("Guest: Pavan Gidwani");
        }
    }

    public void update()
    {
        try
        {
            //tv_roomno.setText("Room no: " + MeshTVPreferenceManager.getRoom(getActivity()));
            //tv_guest.setText("Guest: " + getSelectedItem().getName());
            tv_roomno.setText("Room no: 629");
            tv_guest.setText("Guest: Pavan Gidwani");

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDrawDone(View view) {
        if(tv_roomno!=null&&tv_guest!=null)
        {
            MeshGuest guest = new MeshGuest();
            guest.update();
//            tv_roomno.setText("Room no: "+MeshTVPreferenceManager.getRoom(getActivity()));
//            tv_guest.setText("Guest: Welcome");
            tv_roomno.setText("Room no: 629");
            tv_guest.setText("Guest: Pavan Gidwani");
        }
    }

    @Override
    protected void onDataUpdated(ArrayList<MeshGuest> arrayList) {

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
    protected void update(MeshGuest meshWeatherLocal) {

    }
}
