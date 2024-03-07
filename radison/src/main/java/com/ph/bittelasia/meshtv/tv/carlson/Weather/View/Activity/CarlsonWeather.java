package com.ph.bittelasia.meshtv.tv.carlson.Weather.View.Activity;


import android.view.KeyEvent;

import androidx.fragment.app.Fragment;

import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Activity.CarlsonActivity;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment.CarlsonHotelWeather;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment.CarslonGuestInfo;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshArrayListCallBack;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Fragment.CarlsonButtons;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog.CarlsonSearch;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshTVButtons;
import com.ph.bittelasia.meshtv.tv.carlson.Weather.View.Fragment.CarlsonWeatherCountries;
import com.ph.bittelasia.meshtv.tv.carlson.Weather.View.Fragment.CarlsonWeatherForecast;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.ActivitySetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.AttachFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.RemoteControl.KR301KeyCode;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Activity.MeshTVActivity;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.WeatherForecast.MeshWeatherForecast;

import java.util.ArrayList;

/**
 * Created by ramil on 1/25/18.
 */
@Layout(R.layout.carlson_weather_layout)
@ActivitySetting()
public class CarlsonWeather extends CarlsonActivity implements MeshTVFragmentListener,MeshArrayListCallBack<MeshWeatherForecast> {

    CarlsonWeatherCountries countries;
    CarlsonWeatherForecast  weatherForecast;
    CarlsonButtons          buttons;
    ArrayList<MeshWeatherForecast> weatherForecasts;
    MeshWeatherForecast forecast;

    @AttachFragment(container = R.id.ll_display,tag = "forecast",order = 3)
    public Fragment attachWeatherForecast()
    {
        weatherForecast=new CarlsonWeatherForecast();
        return  weatherForecast;
    }

    @AttachFragment(container = R.id.ll_list,tag="list",order = 4)
    public Fragment attachList()
    {
        countries=new CarlsonWeatherCountries();
        return countries;
    }

    @AttachFragment(container = R.id.ll_bottom,tag="button",order = 5)
    public Fragment attachButton()
    {
        buttons=new CarlsonButtons();
        return buttons;
    }

    boolean isCelcius=true;


    @Override
    public void onResume() {
        super.onResume();
        try
        {
            if (buttons != null)
            {
                buttons.setVisibility(buttons.btn_temp, buttons.btn_filter2);
            }
            if (weatherForecast != null && weatherForecast.isVisible())
            {
                weatherForecast.setCountry("PH");
                weatherForecast.setCategory("PH");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {
        switch (event.getAction())
        {
            case KeyEvent.ACTION_DOWN:
                switch (KR301KeyCode.getEquivalent(event.getKeyCode()))
                {
//                    case 9:
//                        if(event.getAction()==KeyEvent.ACTION_UP)
//                        {
//                            CarlsonMessageCompose.dialog(.70,.70).show(getSupportFragmentManager(),"message");
//                            return true;
//                        }
                }
                break;
        }
        return super.dispatchKeyEvent(event);
    }


    @Override
    public void onClicked(Object o) {
        if(o instanceof String)
        {
            if(((String)o).equals(MeshTVButtons.FILTER.getButton()))
            {

                CarlsonSearch.dialog(weatherForecasts, .50, .55).show(getSupportFragmentManager(), "search");
            }
            else if(((String)o).equals(MeshTVButtons.TEMP.getButton()))
            {
                isCelcius=!isCelcius;
                weatherForecast.setCelcius(isCelcius);

            }
        }
        else if(o instanceof MeshWeatherForecast)
        {
           forecast=((MeshWeatherForecast)o);
           if(weatherForecast!=null)
           {
               weatherForecast.setCountry(forecast.getCountry());
               weatherForecast.setCategory(forecast.getCountry());
           }
        }
    }




    @Override
    public void onSelected(Object o) {

    }

    @Override
    public void meshArrayList(ArrayList<MeshWeatherForecast> list) {
        weatherForecasts=list;
    }
}
