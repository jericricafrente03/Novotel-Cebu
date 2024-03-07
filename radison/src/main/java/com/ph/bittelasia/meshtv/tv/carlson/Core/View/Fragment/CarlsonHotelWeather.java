package com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;


import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.TimedMethod;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Listener.MeshConfigListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Listener.MeshWeatherListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Manager.MeshTVPreferenceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Model.MeshConfig;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Model.MeshWeatherLocal;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.ResourceManager.MeshResourceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.Util.MeshCountryConverter;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.Util.MeshTVTimeManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.Query.MeshValuePair;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Weather.MeshWeatherV2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mars on 1/22/18.
 */
@Layout(R.layout.carlson_weather_info_layout)
public class CarlsonHotelWeather extends MeshTVFragment<MeshWeatherV2> implements MeshWeatherListener,MeshRealmListener,MeshConfigListener {

    public static final String DATE_FORMAT = "MM/dd/yyyy";
    public static final String TIME_FORMAT = "hh:mm aa";

    private Thread thread;
    private Activity activity;

    @BindWidget(R.id.tv_weather_place)
    public TextView tv_weather_place;

    @BindWidget(R.id.tv_time)
    public TextClock tv_time;

    @BindWidget(R.id.tv_date)
    public TextView tv_date;

    @BindWidget(R.id.tv_temp)
    public TextView tv_temp;

    @BindWidget(R.id.iv_weather_icon)
    public ImageView iv_weather_icon;


    MeshWeatherLocal local;


    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //==============================================Timed===========================================
    @TimedMethod(delay = 30000)
    public void timeTicker()
    {
        update();
    }
    //==============================================================================================
    //==============================================LifeCycle=======================================

    @Override
    public void onResume() {
        super.onResume();
        try {
            MeshValuePair vp = new MeshValuePair(MeshWeatherV2.TAG_COUNTRY, MeshTVPreferenceManager.getHotelCountry(getActivity()));
            vp.setString(true);
            MeshRealmManager.retrieve(MeshWeatherV2.class, this, vp);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //==============================================================================================
    //==============================================Method==========================================
    public void update()
    {
        try {
            if(tv_date!=null&&tv_time!=null)
            {
                try
                {
                 //   MeshResourceManager.displayLiveImageFor(getActivity(),iv_weather_icon,MeshTVPreferenceManager.getHotelWeatherWeatherIcon(getActivity()));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //==============================================================================================
    //=============================================MeshTVFragment===================================
    @Override
    protected void onDrawDone(View view)
    {
        update();
        activity=getActivity();
    }
    @Override
    protected void onDataUpdated(ArrayList<MeshWeatherV2> arrayList) {

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
    protected void update(MeshWeatherV2 meshWeatherLocal)
    {

    }
    //==============================================================================================
    //=====================================MeshWeatherListener========================================
    @Override
    public void onUpdateForWeather(MeshWeatherLocal meshWeatherLocal)
    {
        try {
            if (tv_weather_place != null && iv_weather_icon != null && tv_temp != null) {
                //tv_weather_place.setText((MeshTVPreferenceManager.getHotelCity(getActivity())+", "+ MeshCountryConverter.getCountryFullname(MeshTVPreferenceManager.getHotelCountry(getActivity()))));
                tv_weather_place.setText("Mactan, Cebu");
                tv_date.setText((new SimpleDateFormat(DATE_FORMAT).format(new Date()).toUpperCase()));
                tv_time.setTimeZone(MeshTVTimeManager.getTimeZoneName(meshWeatherLocal.getCountry_code()));
                if(local!=null) {
                    //tv_temp.setText((local.getTemp_cur() + " °C"));
                    tv_temp.setText("");
                }
                thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            while (!thread.isInterrupted()) {
                                Thread.sleep(1000);
                                if(activity!=null)
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
                                        String time = sdf.format(new Date());
                                        tv_date.setText((new SimpleDateFormat(DATE_FORMAT).format(new Date()).toUpperCase()));
                                        tv_time.setText(time+"");
                                    }
                                });
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                };

                thread.start();

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onRetrieved(Class aClass, ArrayList<Object> arrayList) {


        local = new MeshWeatherLocal((MeshWeatherV2)arrayList.get(0));
        local.update();
        local.display();
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

    @Override
    public void onHotelConfigurationChange(MeshConfig meshConfig)
    {
        if(tv_weather_place!=null)
        {
            tv_weather_place.setText("Mactan, Cebu");
            //tv_weather_place.setText(meshConfig.getHotel_city()+", "+MeshCountryConverter.getCountryFullname(meshConfig.getHotel_country()));
        }

    }
    //==============================================================================================
}
