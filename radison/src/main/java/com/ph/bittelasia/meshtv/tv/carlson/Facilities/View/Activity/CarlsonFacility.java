package com.ph.bittelasia.meshtv.tv.carlson.Facilities.View.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Activity.CarlsonActivity;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment.CarlsonHotelWeather;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment.CarlsonMessageNotification;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment.CarslonGuestInfo;
import com.ph.bittelasia.meshtv.tv.carlson.Facilities.View.Fragment.CarlsonFacilityCategory;
import com.ph.bittelasia.meshtv.tv.carlson.Facilities.View.Fragment.CarlsonFacilityList;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshArrayListCallBack;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshMessageRead;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshTVButtons;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog.CarlsonAddToCart;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog.CarlsonViewCart;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Fragment.CarlsonButtons;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.ActivitySetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.AttachFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Activity.MeshTVActivity;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacility;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacilityCategory;
import com.ph.bittelasia.meshtv.tv.mtvlib.Transaction.Model.MeshTVCart;

import java.util.ArrayList;

/**
 * Created by ramil on 2/12/18.
 */
@Layout(R.layout.carlson_vod)
@ActivitySetting()
public class CarlsonFacility extends MeshTVActivity implements MeshTVFragmentListener,MeshArrayListCallBack<MeshFacility> {

    CarlsonFacilityCategory   category;
    CarlsonFacilityList       list;
    CarlsonButtons            buttons;

    MeshFacilityCategory      meshCategory;
    MeshFacility              item;


    /*------------pansamantala-----------------------------------------------------------------------*/

    CarslonGuestInfo guestInfo;
    CarlsonHotelWeather hotelWeather;
    CarlsonMessageNotification notification=null;


    Handler handler;
    Runnable                             runnable;
    int                                  unread;


    @Override
    public void onResume() {
        super.onResume();
        checkMessage();
    }


    public void checkMessage()
    {
        try {
            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        unread=0;
                        unread+= MeshMessageRead.getUnreadMessage();
                        //Log.i("steward","messages: "+unread);
                        if (unread > 0) {
                            if (getSupportFragmentManager().findFragmentById(R.id.ll_notification) == null) {
                                if (notification == null) {
                                    notification = new CarlsonMessageNotification();
                                    getSupportFragmentManager().beginTransaction().add(R.id.ll_notification, notification, "notification").commitAllowingStateLoss();
                                    // Log.i("steward", "yes 1");
                                }
                            } else {
                                getSupportFragmentManager().beginTransaction().show(notification).commitAllowingStateLoss();
                                notification.checkMessage(unread);
                                //Log.i("steward", "unread");
                            }
                        } else {
                            if (notification != null) {
                                getSupportFragmentManager().beginTransaction().hide(notification).commitAllowingStateLoss();
                                //Log.i("steward", "no");
                            }
                        }
                        handler.postDelayed(this, 2000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            handler.post(runnable);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
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


    /*-----------------------------------------------------------------------------------------------*/




    @BindWidget(R.id.tv_maintitle)
    public TextView tv_sub;

    @AttachFragment(container = R.id.ll_categories,tag="category",order=3)
    public Fragment attachCategory()
    {
        category=new CarlsonFacilityCategory();
        return category;
    }

    @AttachFragment(container = R.id.ll_display,tag="list",order = 4)
    public Fragment attachList()
    {
        list =new CarlsonFacilityList();
        list.setFacilityContext(this);
        return list;
    }

    @AttachFragment(container = R.id.ll_bottom,tag = "bottom",order = 5)
    public Fragment attachButtons()
    {
        buttons=new CarlsonButtons();
        return buttons;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        tv_sub.setText(("HOTEL INFO"));
        buttons.setVisibility(buttons.btn_watch);
        buttons.btn_watch.setText(("WATCH VIDEO"));
    }

    @Override
    public void onClicked(Object o) {

        try
        {
            if (o instanceof String)
            {
                if (((String) o).equals(MeshTVButtons.PREVIEW.getButton()))
                {
                   // CarlsonViewCart.dialog(MeshTVCart.display(MeshFacility.class), .70, .70).show(getSupportFragmentManager(), "view");
                }
            }
            if(o instanceof MeshFacility)
            {
                item = (MeshFacility) o;
                CarlsonAddToCart.dialog(item, .60, .60).show(getSupportFragmentManager(), "dialog");
            }
            if (o instanceof MeshFacilityCategory)
            {
                meshCategory = (MeshFacilityCategory) o;
                list.setCategory(meshCategory.getId());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onSelected(Object o) {
        try
        {
            if (o instanceof MeshFacilityCategory)
            {
                meshCategory = (MeshFacilityCategory) o;
                list.setCategory(meshCategory.getId());
            }
            if (o instanceof Integer)
            {
               // list.gv_grid.setSelection(0);
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void meshArrayList(ArrayList<MeshFacility> list) {

    }
}
