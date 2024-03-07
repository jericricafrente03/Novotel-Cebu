package com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.CarlsonSearchListAdapter;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.CarlsonViewCartListAdapter;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.Items;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Flight.MeshArrivalFlight;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Flight.MeshDepartureFlight;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Geography.MeshCity;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVOD;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.WeatherForecast.MeshWeatherForecast;
import com.ph.bittelasia.meshtv.tv.mtvlib.Transaction.Model.MeshCartItem;

import java.util.ArrayList;

/**
 * Created by ramil on 1/29/18.
 */

public class CarlsonSearch<T> extends MeshTVDialog implements MeshTVFragmentListener {

    MeshTVFragmentListener cb;

    AutoCompleteTextView et_keyword;
    TextView tv_search;
    ListView lv_search;
    Button btn_back;

    Animation zoomIn;
    CarlsonSearchListAdapter   searchAdapter;
    CarlsonViewCartListAdapter cartAdapter;
    ArrayList<T>                         objectList;
    ArrayList<Items>                     itemsList;



    double percentageWidth;
    double percentageHeight;


    public static CarlsonSearch s;

    public static <T>  CarlsonSearch dialog(ArrayList<T> objectList,double percentageWidth,double percentageHeight)
    {
        s=new CarlsonSearch<T>();
        s.objectList =objectList;
        s.percentageWidth=percentageWidth;
        s.percentageHeight=percentageHeight;
        return  s;
    }
    final View.OnFocusChangeListener focus=new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            switch (view.getId())
            {
            }
        }
    };
    public void checkImage(final View view, Boolean b, Animation animation)
    {
        try
        {
            if (b)
            {
                view.startAnimation(animation);
            }
            else
            {
                view.clearAnimation();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cb=(MeshTVFragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        cb=null;
    }

    @Override
    public void setIDs(View view) {
        et_keyword=(AutoCompleteTextView) view.findViewById(R.id.et_keyword);
        lv_search =(ListView)view.findViewById(R.id.lv_search);
        btn_back  =(Button)view.findViewById(R.id.btn_back);
        tv_search =(TextView)view.findViewById(R.id.tv_search);
    }

    @Override
    public void setContent() {
        itemsList =new ArrayList<>();
        if(objectList.size()>0)
        {
            if (objectList.get(0) instanceof MeshCartItem)
            {
                final ArrayList<MeshCartItem> cartItems = (ArrayList<MeshCartItem>) objectList;
                cartAdapter = new CarlsonViewCartListAdapter(getContext(), R.layout.carlson_tab_item_list, cartItems);
                lv_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        cb.onClicked(adapterView.getItemAtPosition(i));
                    }
                });
                lv_search.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
                    {
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView)
                    {

                    }
                });
                et_keyword.setAdapter(cartAdapter);
            } else {
                for (T object : objectList)
                {
                    if (object instanceof MeshVOD)
                    {
                        tv_search.setText(("Searh Movies"));
                        itemsList.add(new Items(((MeshVOD) object).getId(), ((MeshVOD) object).getTitle()));
                    }
                    if (object instanceof MeshCity)
                    {
                        tv_search.setText(("Search Time Zones"));
                        itemsList.add(new Items(((MeshCity) object).getId(), ((MeshCity) object).getCntry(), ((MeshCity) object).getCntry_code()));
                    }
                    if (object instanceof MeshWeatherForecast)
                    {
                        tv_search.setText(("Search Countries"));
                        itemsList.add(new Items(((MeshWeatherForecast) object).getCountry(), ((MeshWeatherForecast) object).getCity()));
                    }
                    if (object instanceof MeshArrivalFlight)
                    {
                        tv_search.setText(("Search Flights"));

                        Items items = new Items();
                        items.setNumber(((MeshArrivalFlight) object).getFlight_number());
                        items.setCarrier(((MeshArrivalFlight) object).getCarrier());
                        items.setCountry(((MeshArrivalFlight) object).getOrigin());
                        items.setDate(((MeshArrivalFlight) object).getArrivalDate());
                        items.setStatus(((MeshArrivalFlight) object).getStatus());
                        items.setMin(((MeshArrivalFlight) object).getMin_off());
                        itemsList.add(items);
                    }
                    if (object instanceof MeshDepartureFlight)
                    {
                        tv_search.setText(("Search Flights"));

                        Items items = new Items();
                        items.setNumber(((MeshDepartureFlight) object).getFlight_number());
                        items.setCarrier(((MeshDepartureFlight) object).getCarrier());
                        items.setCountry(((MeshDepartureFlight) object).getDeparture_datetime());
                        items.setDate(((MeshDepartureFlight) object).getDepartureDate());
                        items.setStatus(((MeshDepartureFlight) object).getStatus());
                        items.setMin(((MeshDepartureFlight) object).getMin_off());
                        itemsList.add(items);
                    }
                }
                searchAdapter = new CarlsonSearchListAdapter(getContext(), R.layout.carlson_tab_item_list, itemsList);
                lv_search.setAdapter(searchAdapter);
                lv_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                    {
                        et_keyword.clearFocus();
                        if (objectList.size() > 0) {
                            if (objectList.get(0) instanceof MeshVOD)
                            {
                                CarlsonConfirm.dialog(((MeshVOD) objectList.get(i)), .35, .30).show(getFragmentManager(), "confirm");
                            }
                            else if (objectList.get(0) instanceof MeshWeatherForecast)
                            {
                                cb.onClicked(((MeshWeatherForecast) objectList.get(i)));
                                dismiss();
                            }
                            else if (objectList.get(0) instanceof MeshCity)
                            {
                                cb.onClicked(((MeshCity) objectList.get(i)));
                                dismiss();
                            }
                        }
                    }
                });
                et_keyword.setAdapter(searchAdapter);
            }
        }
        lv_search.setTextFilterEnabled(true);
        et_keyword.setThreshold(1);
        et_keyword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setFocusable(true);
                showSoftKeyboard(view);
            }
        });
        et_keyword.enoughToFilter();
        et_keyword.setDropDownHeight(0);
        lv_search.setItemsCanFocus(true);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        et_keyword.clearFocus();
        lv_search.requestFocus();
    }

    @Override
    public int setLayout() {
        return R.layout.carlson_search_dialog;
    }

    @Override
    public int setConstraintLayout() {
        return R.id.cl_layout;
    }

    @Override
    public double setPercentageWidth() {
        return percentageWidth;
    }

    @Override
    public double setPercentageHeight() {
        return percentageHeight;
    }

    @Override
    public void onClicked(Object o) {
        if(o instanceof String )
        {
            if(((String)o).equals(getActivity().getResources().getString(R.string.yes)))
            {
                getDialog().dismiss();
            }
        }
    }

    @Override
    public void onSelected(Object o) {

    }
}
