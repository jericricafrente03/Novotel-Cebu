package com.ph.bittelasia.meshtv.tv.carlson.HotelInfo.View.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.ResourceManager.MeshResourceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacility;

public class FacilityAdapterFragment  extends Fragment
{

    MeshFacility facility;
    ImageView iv_image;
    TextView tv_label;

    public FacilityAdapterFragment(MeshFacility facility)
    {
        this. facility = facility;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.aa_facility_fragment_adapter,container,false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iv_image = view.findViewById(R.id.iv_image);
        tv_label = view.findViewById(R.id.tv_label);
        Drawable res = getResources().getDrawable(getDrawable());
        iv_image.setImageDrawable(res);
        tv_label.setText(facility.getItem_name());

    }


    private int getDrawable()
    {
        switch (facility.getImg_uri())
        {
            case "novotel_lobby":
                return R.drawable.novotel_lobby;
            case "novotel_living_room_bar":
                return R.drawable.novotel_living_room_bar;
            case "novotel_library":
                return R.drawable.novotel_library;
            case "novotel_pool":
                return R.drawable.novotel_pool;
        }

        return R.drawable.novotel_crop;
    }

}
