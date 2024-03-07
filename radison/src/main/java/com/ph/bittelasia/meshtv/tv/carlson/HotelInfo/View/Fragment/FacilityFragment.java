package com.ph.bittelasia.meshtv.tv.carlson.HotelInfo.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.google.android.exoplayer2.video.spherical.Projection;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Parser.MeshParser;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Table.MeshHeader;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacility;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class FacilityFragment extends Fragment
{
    ViewPager pager;
    ArrayList<MeshFacility> facilities;
    int item = 0;
    Handler h;
    Runnable r = new Runnable() {
        @Override
        public void run() {
            item++;
            if(item>=facilities.size())
            {
                item = 0;
            }
            pager.setCurrentItem(item);
            h.postDelayed(this,5000);
        }
    };

    public static String readRawTextFile(Context ctx, int resId)
    {
        InputStream inputStream = ctx.getResources().openRawResource(resId);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();

        try {
            while (( line = buffreader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            return null;
        }
        return text.toString();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        h = new Handler();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.aa_facility_fragment,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        pager = view.findViewById(R.id.vp_facility);
        facilities = new ArrayList<>();
        try
        {
            facilities.addAll(MeshParser.parseFacilities(readRawTextFile(getActivity(),R.raw.get_all_facilities)));
            pager.setAdapter(new FacilityAdapter(getChildFragmentManager(),facilities));
            h.postDelayed(r,5000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


}
