package com.ph.bittelasia.meshtv.tv.carlson;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTimeFragment extends Fragment
{
    TextClock tc_clock;
    TextView tv_date;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.bittel_time_layout_2,container,false);
        tc_clock = (TextClock) view.findViewById(R.id.tc_clock);
        tv_date = (TextView) view.findViewById(R.id.tv_date);
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy");
            tv_date.setText(df.format(new Date()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return view;
    }
}
