package com.ph.bittelasia.meshtv.tv.carlson.DigitalSignageMarsV2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.DataSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.TimedMethod;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Listener.MeshConfigListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Listener.MeshWeatherListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Manager.MeshTVPreferenceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Model.MeshConfig;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Model.MeshWeatherLocal;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.ResourceManager.MeshResourceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.Query.MeshValuePair;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Weather.MeshWeatherV2;

import java.util.ArrayList;

@Layout(R.layout.bittel_layout_mars_weather)
@DataSetting()
public class CarlsonDigitalSignageWeather2 extends MeshTVFragment<MeshWeatherV2> implements
        MeshWeatherListener,
        MeshRealmListener,
        MeshConfigListener
{
    //============================================Variable==========================================
    //--------------------------------------------Constant------------------------------------------
    public static final String TAG = CarlsonDigitalSignageWeather2.class.getSimpleName();
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------View--------------------------------------------
    //----------------------------------------------------------------------------------------------
    //---------------------------------------------Instance-----------------------------------------
    MeshWeatherLocal local;
    boolean acceptUpdate = true;
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //============================================LifeCycle=========================================

    @Override
    public void onResume() {
        super.onResume();
//        acceptUpdate = true;
//        update();
//        MeshValuePair vp = new MeshValuePair(MeshWeatherV2.TAG_COUNTRY, MeshTVPreferenceManager.getHotelCountry(getActivity()));
//        vp.setString(true);
//        MeshRealmManager.retrieve(MeshWeatherV2.class,this,vp);
    }

    //==============================================================================================
    //==============================================Method==========================================
    public void update()
    {
//        if(acceptUpdate)
//        {
//
//            if(tv_description!=null&&tv_temperature!=null&&iv_weather!=null)
//            {
//
//                try
//                {
//                    MeshResourceManager.displayLiveImageFor(getActivity(),iv_weather, MeshTVPreferenceManager.getHotelWeatherWeatherIcon(getActivity()));
//                    tv_description.setText(MeshTVPreferenceManager.getHotelWeatherWeatherDesc(getActivity()));
//                    tv_temperature.setText(MeshTVPreferenceManager.getHotelWeatherMainTemp(getActivity())+" °C");
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//                acceptUpdate = false;
//
//            }
//        }

    }
    //==============================================================================================
    //==============================================Timed===========================================
    @TimedMethod(delay = 30000)
    public void timeTicker()
    {
        update();
    }
    //==============================================================================================
    //==========================================MeshTVFragment======================================
    @Override
    protected void onDrawDone(View view) {

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
    protected void update(MeshWeatherV2 meshWeatherV2) {

    }
    //==============================================================================================
    //=======================================MeshWeatherListener====================================
    @Override
    public void onUpdateForWeather(MeshWeatherLocal meshWeatherLocal) {
        try
        {
            local = meshWeatherLocal;
            acceptUpdate = true;
            update();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //==============================================================================================
    //========================================MeshRealmListener=====================================

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


    //==============================================================================================
    //=======================================MeshConfigListener=====================================
    @Override
    public void onHotelConfigurationChange(MeshConfig meshConfig) {

    }
    //==============================================================================================

}
