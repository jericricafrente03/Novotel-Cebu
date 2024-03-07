package com.ph.bittelasia.meshtv.tv.carlson.HotelInfo.View.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacility;

import java.util.ArrayList;

public class FacilityAdapter extends FragmentStatePagerAdapter
{

    ArrayList<MeshFacility> facilities;

    public FacilityAdapter(@NonNull FragmentManager fm, ArrayList<MeshFacility> facilities) {
        super(fm);
        this.facilities = new ArrayList<>();
        this.facilities.addAll(facilities);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new FacilityAdapterFragment(this.facilities.get(position));
    }

    @Override
    public int getCount() {
        return this.facilities.size();
    }
}
