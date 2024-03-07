package com.ph.bittelasia.meshtv.tv.carlson;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TestFragment extends Fragment
{
    private int resId = -1;
    private TextView tv_zone;
    public static TestFragment getFragment(int resId)
    {
        TestFragment tf = new TestFragment();
        tf.setResId(resId);
        return tf;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(resId,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_zone= (TextView) view.findViewById(R.id.tv_zone);
        tv_zone.setRotation(270);

    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
