package com.ph.bittelasia.meshtv.tv.carlson.Weather.View.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.ContentCallBack;
import com.ph.bittelasia.meshtv.tv.carlson.Weather.Model.CarlsonWeatherListAdapter;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.DataSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Listener.MeshDataListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Manager.MeshTVDataManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.ResourceManager.MeshResourceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.Query.MeshValuePair;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.WeatherForecast.MeshWeatherForecast;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.WeatherForecast.MeshWeatherForecastDay;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

@Layout(R.layout.carlson_weather_forecast_layout)
@DataSetting(listenToWeatherForecast = true)
public class CarlsonWeatherForecast extends MeshTVFragment<MeshWeatherForecast> implements MeshDataListener,MeshRealmListener,ContentCallBack {

    String code;
    private String country;
    private MeshValuePair filter;
    private MeshWeatherForecast forecast;
    private ArrayList<MeshWeatherForecast>    items;
    private ArrayList<MeshWeatherForecastDay> days;
    private MeshWeatherForecastDay day;
    public CarlsonWeatherListAdapter adapter;
    public static final String UNIT_CELCIUS = " °C";
    public static final String UNIT_FAREN   = " °F";
    public static boolean isCelcius = true;

    @BindWidget(R.id.gv_weather)
    public GridView gv_weather;

    @BindWidget(R.id.iv_icon)
    public ImageView iv_icon;

    @BindWidget(R.id.tv_temp)
    public TextView tv_temp;

    @BindWidget(R.id.tv_content)
    public TextView tv_content;

    public static CarlsonWeatherForecast f;


    public static CarlsonWeatherForecast get(String code)
    {
        f=new CarlsonWeatherForecast();
        f.code=code;
        return f;
    }




    public void setCelcius(boolean celcius)
    {
        isCelcius = celcius;
        if(country!=null)
        {
            setCountry(country);
        }
        else
        {
            update();
        }
        gv_weather.setAdapter(new CarlsonWeatherListAdapter(getContext(), gv_weather, R.layout.carlson_weather_item_layout, days));
    }

    public void setCountry(String country)
    {
        this.country = country;
        Realm r = Realm.getDefaultInstance();
        RealmResults<MeshWeatherForecast> results = r.where(MeshWeatherForecast.class).equalTo(MeshWeatherForecast.TAG_COUNTRY,country).findAllAsync();
        results.addChangeListener(new RealmChangeListener<RealmResults<MeshWeatherForecast>>() {
            @Override
            public void onChange(RealmResults<MeshWeatherForecast> element) {
                forecast = element.first();
                day = MeshWeatherForecastDay.parse(forecast.getForecast()).get(0);
                update();
            }
        });
    }

    public void update()
    {
        try
        {
            if(day!=null)
            {
                //MeshResourceManager.displayLiveImageFor(getActivity(),iv_icon, day.getIcon());
                Picasso.get().load( day.getIcon()).into(iv_icon);
                tv_temp.setText(((isCelcius?String.format("%.1f",day.getTemp())+UNIT_CELCIUS:(String.format("%.1f",((days.get(0).getTemp()*9)/5)+32))+UNIT_FAREN)+" |"));
                String date=new SimpleDateFormat("EEEE, d MMMM yyyy", Locale.US).format(new Date());// it should be day.getDate()
                tv_content.setText(("TODAY"+"\n"+date+"\n"+forecast.getCity()+"\n"+day.getDescription()));
            }
            else
            {
                tv_temp.setText(((isCelcius?String.format("%.1f",days.get(0).getTemp())+UNIT_CELCIUS:(String.format("%.1f",((days.get(0).getTemp()*9)/5)+32))+UNIT_FAREN)+" |"));
                String date=new SimpleDateFormat("EEEE, d MMMM yyyy", Locale.US).format(new Date());// it should be day.getDate()
                tv_content.setText(("TODAY"+"\n"+date+"\n"+forecast.getCity()+"\n"+day.getDescription()));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public void setCategory(String country_code)
    {
        if(!country_code.isEmpty())
        {

            filter.setValue(country_code);

            MeshRealmManager.retrieve(MeshWeatherForecast.class,this, filter);
        }
        else
        {
            MeshRealmManager.retrieve(MeshWeatherForecast.class, this);
        }
    }


    @Override
    protected void onDrawDone(View view) {
        MeshRealmManager.retrieve(MeshWeatherForecast.class, this);
        filter = new MeshValuePair(MeshWeatherForecast.TAG_COUNTRY,code);
        filter.setString(true);
        Log.i("steward","1");
    }

    @Override
    protected void onDataUpdated(ArrayList<MeshWeatherForecast> arrayList) {

        days = MeshWeatherForecastDay.parse(((MeshWeatherForecast) arrayList.get(0)).getForecast());
        days.remove(0);
        adapter = new CarlsonWeatherListAdapter(getContext(), gv_weather, R.layout.carlson_weather_item_layout, days);
        if(gv_weather!=null)
        {
            gv_weather.setAdapter(adapter);
        }
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
    protected void update(MeshWeatherForecast meshWeatherForecast)
    {
        Log.i("steward","9");
    }

    //===============================MeshDataListener===============================================
    //----------------------------------------------------------------------------------------------
    @Override
    public void onNoNetwork(Class aClass)
    {
        Log.i("steward","10");
    }

    @Override
    public void onNoData(Class aClass)
    {
        Log.i("steward","11");
    }

    @Override
    public void onParseFailed(Class aClass, String s) {
        Log.i("steward","12");
    }

    @Override
    public void onFileNotFound(Class aClass, String s)
    {
        Log.i("steward","13");
    }


    @Override
    public void onDataReceived(Class aClass, int i)
    {
        MeshRealmManager.retrieve(MeshWeatherForecast.class,this);
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //==================================MeshRealmListener===========================================
    //----------------------------------------------------------------------------------------------

    @Override
    public void onRetrieved(Class aClass, ArrayList<Object> arrayList)
    {
        try
        {

            items = new ArrayList<>();
            for (Object o : arrayList)
            {
                items.add((MeshWeatherForecast) o);
            }
            days = MeshWeatherForecastDay.parse(((MeshWeatherForecast) items.get(0)).getForecast());
            day = days.get(0);
            days.remove(0);
            gv_weather.setAdapter(new CarlsonWeatherListAdapter(getContext(), gv_weather, R.layout.carlson_weather_item_layout, days));
           // MeshResourceManager.displayLiveImageFor(getActivity(),iv_icon, days.get(0).getIcon());
            Picasso.get().load(  days.get(0).getIcon()).into(iv_icon);
            tv_temp.setText(((isCelcius?String.format("%.1f",days.get(0).getTemp())+UNIT_CELCIUS:(String.format("%.1f",((days.get(0).getTemp()*9)/5)+32))+UNIT_FAREN)+" |"));
            String date=new SimpleDateFormat("EEEE, d MMMM yyyy", Locale.US).format(new Date());
            tv_content.setText(("TODAY"+"\n"+date+"\n"+items.get(0).getCity()+"\n"+days.get(0).getDescription()));


        }
        catch (Exception e)
        {

            e.printStackTrace();

        }
    }

    @Override
    public void onFailed(Class aClass, String s)
    {

    }

    @Override
    public void onEmpty(Class aClass, String s)
    {
        MeshTVDataManager.requestData(MeshWeatherForecast.class,this);
    }

    @Override
    public void onCleared(Class aClass)
    {

    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================


    @Override
    public void setContent()
    {

    }
}
