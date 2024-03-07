package com.ph.bittelasia.meshtv.tv.carlson.Weather.Model;


import android.content.Context;
import android.widget.GridView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Weather.View.Fragment.CarlsonWeatherForecast;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.ViewHolder.ViewHolderLayout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.ResourceManager.MeshResourceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.Adapter.MeshTVAdapter;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.VH.MeshTVVHolder;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.WeatherForecast.MeshWeatherForecastDay;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ramil on 1/25/18.
 */
@ViewHolderLayout(layout= R.layout.carlson_weather_item_layout)
public class CarlsonWeatherListAdapter extends MeshTVAdapter<MeshWeatherForecastDay> {

    public CarlsonWeatherListAdapter(Context context, GridView gv_view, int layoutResourceId, ArrayList<MeshWeatherForecastDay> data) {
        super(context, gv_view, layoutResourceId, data);
    }

    @Override
    public MeshTVVHolder setViewHolder() {
        return new CarlsonWeatherListViewHolder();
    }

    @Override
    public void updateRow(MeshTVVHolder meshTVVHolder, MeshWeatherForecastDay meshWeatherForecastDay) {
        try
        {
            CarlsonWeatherListViewHolder vh = (CarlsonWeatherListViewHolder) meshTVVHolder;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, meshWeatherForecastDay.getDay() - 1);
           // MeshResourceManager.displayLiveImageFor(getContext(), vh.getIv_icon(), meshWeatherForecastDay.getIcon());
            Picasso.get().load(meshWeatherForecastDay.getIcon()).into(vh.getIv_icon());
            vh.getTv_date().setText(new SimpleDateFormat("d E yyyy", Locale.US).format(calendar.getTime()));
            vh.getTv_temp().setText(
                    CarlsonWeatherForecast.isCelcius ? String.format("%.1f", meshWeatherForecastDay.getTemp()) + CarlsonWeatherForecast.UNIT_CELCIUS : (String.format("%.1f", ((meshWeatherForecastDay.getTemp() * 9) / 5) + 32)) + CarlsonWeatherForecast.UNIT_FAREN
            );
            vh.getTv_desc().setText(meshWeatherForecastDay.getDescription());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
