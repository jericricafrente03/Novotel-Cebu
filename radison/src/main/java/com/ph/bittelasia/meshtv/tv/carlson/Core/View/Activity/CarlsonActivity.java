package com.ph.bittelasia.meshtv.tv.carlson.Core.View.Activity;

import android.Manifest;
import android.os.Build;
import android.os.Handler;


import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment.CarlsonHotelWeather;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment.CarlsonMessageNotification;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment.CarslonGuestInfo;
import com.ph.bittelasia.meshtv.tv.carlson.Launcher.View.Fragment.CarlsonConfigFragment;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshMessageRead;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.ActivitySetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.AttachFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Activity.MeshTVActivity;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Message.MeshMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;

/**
 * Created by ramil on 2/6/18.
 */
@ActivitySetting()
public class CarlsonActivity extends MeshTVActivity{

    public CarslonGuestInfo    guestInfo;
    public CarlsonHotelWeather hotelWeather;
    public CarlsonMessageNotification notification=null;

    public Handler handler;
    public Runnable                             runnable;
    public int                                  unread;



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
    public Fragment attachGuest()
    {
        guestInfo=new CarslonGuestInfo();
        return  guestInfo;
    }


}
