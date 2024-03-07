package com.ph.bittelasia.meshtv.tv.carlson.Weather.View.Fragment;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.ContentCallBack;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshArrayListCallBack;
import com.ph.bittelasia.meshtv.tv.carlson.Weather.Model.CarlsonWeatherListAdapter;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.DataSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Listener.MeshDataListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Manager.MeshTVDataManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.Query.MeshValuePair;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.WeatherForecast.MeshWeatherForecast;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.WeatherForecast.MeshWeatherForecastDay;

import java.util.ArrayList;

/**
 * Created by ramil on 1/30/18.
 */
@Layout(R.layout.carlson_global_layout)
@DataSetting(listenToWeatherForecast = true)
public class CarlsonWeatherCountries extends MeshTVFragment<MeshWeatherForecast> implements MeshDataListener,MeshRealmListener,ContentCallBack {

    String code;
    MeshValuePair filter;
    MeshWeatherForecast forecast;
    CarlsonWeatherListAdapter adapter;
    ArrayList<MeshWeatherForecast> items;
    ArrayList<MeshWeatherForecastDay> days;
    MeshArrayListCallBack cb;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cb=(MeshArrayListCallBack)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        cb=null;
    }

    public static CarlsonWeatherForecast f;


    public static CarlsonWeatherForecast get(String code)
    {
        f=new CarlsonWeatherForecast();
        f.code=code;
        return f;
    }


    public static final String UNIT_CELCIUS = " °C";
    public static final String UNIT_FAREN   = " °F";


    @Override
    protected void onDrawDone(View view) {
        MeshRealmManager.retrieve(MeshWeatherForecast.class, this);
        filter = new MeshValuePair(MeshWeatherForecast.TAG_COUNTRY,code);
        filter.setString(true);
        Log.i("steward","1");
    }

    @Override
    protected void onDataUpdated(ArrayList<MeshWeatherForecast> arrayList) {
        Log.i("steward","2");
    }

    @Override
    protected void onNewData(Object o) {
        Log.i("steward","3");
    }

    @Override
    protected void onDataUpdated(Object o, int i) {
        Log.i("steward","4");
    }

    @Override
    protected void onDeleteData(int i) {
        Log.i("steward","5");
    }

    @Override
    protected void onClearData() {
        Log.i("steward","6");
    }

    @Override
    protected void onDataNotFound(Class aClass) {
        Log.i("steward","7");
    }

    @Override
    protected void refresh() {
        MeshRealmManager.retrieve(MeshWeatherForecast.class,this,filter);
        Log.i("steward","8");
    }

    @Override
    protected void update(MeshWeatherForecast meshWeatherForecast) {
        Log.i("steward","9");
    }

    @Override
    public void onNoNetwork(Class aClass) {
        Log.i("steward","10");
    }

    @Override
    public void onNoData(Class aClass) {
        Log.i("steward","11");
    }

    @Override
    public void onParseFailed(Class aClass, String s) {
        Log.i("steward","12");
    }

    @Override
    public void onFileNotFound(Class aClass, String s) {
        Log.i("steward","13");
    }

    @Override
    public void onDataReceived(Class aClass, int i) {
        MeshRealmManager.retrieve(MeshWeatherForecast.class,this);
        Log.i("steward","14");
    }

    @Override
    public void onRetrieved(Class aClass, ArrayList<Object> arrayList) {
        try {
            items = new ArrayList<>();
            for (Object o : arrayList) {
                items.add((MeshWeatherForecast) o);
            }
            cb.meshArrayList(items);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        Log.i("steward","15");
    }

    @Override
    public void onFailed(Class aClass, String s) {
        Log.i("steward","16");
    }

    @Override
    public void onEmpty(Class aClass, String s) {
        MeshTVDataManager.requestData(MeshWeatherForecast.class,this);
        Log.i("steward","17");
    }

    @Override
    public void onCleared(Class aClass) {

    }

    @Override
    public void setContent() {

    }
}
