package com.ph.bittelasia.meshtv.tv.carlson.Weather.Model;

import android.widget.ImageView;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.VH.MeshTVVHolder;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.WeatherForecast.MeshWeatherForecastDay;

/**
 * Created by ramil on 1/25/18.
 */

public class CarlsonWeatherListViewHolder extends MeshTVVHolder<MeshWeatherForecastDay> {


    @BindWidget(R.id.tv_date)
    public TextView tv_date;

    @BindWidget(R.id.iv_icon)
    public ImageView iv_icon;

    @BindWidget(R.id.tv_temp)
    public TextView tv_temp;

    @BindWidget(R.id.tv_desc)
    public TextView tv_desc;



    public TextView getTv_date() {
        return tv_date;
    }

    public ImageView getIv_icon() {
        return iv_icon;
    }

    public TextView getTv_temp() {
        return tv_temp;
    }

    public TextView getTv_desc() {
        return tv_desc;
    }



}
