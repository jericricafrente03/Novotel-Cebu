package com.ph.bittelasia.meshtv.tv.carlson.TV.Model;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.AdapterInterface;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.CarlsonCategoryViewHolder;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.CustomView.MeshTVCustomList.MeshTVCustomAdapter;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Channel.MeshChannelCategory;


import java.util.ArrayList;

/**
 * Created by ramil on 2/7/18.
 */

public class CarlsonChannelCategoryAdapter extends MeshTVCustomAdapter<MeshChannelCategory> {
    int x=0;
    AdapterInterface listener;
    ArrayList<TextView> views;



    public CarlsonChannelCategoryAdapter(ArrayList<MeshChannelCategory> items, AdapterInterface listener) {
        super(items);
        this.listener=listener;
        this.views=new ArrayList<>();
    }

    @Override
    public void bindView(View view, final MeshChannelCategory meshChannelCategory) {
        final CarlsonCategoryViewHolder vh = new CarlsonCategoryViewHolder();
        vh.inflate(view);
        vh.getTv_category().setText(meshChannelCategory.getCategory_name());
        views.clear();
        views.add(vh.getTv_category());
        try {
            x++;
            if (x==getAllItems().size()) {
                vh.getV_bar().setVisibility(View.GONE);
                x = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        vh.getTv_category().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(meshChannelCategory);
                vh.getTv_category().setTextColor(Color.RED);
            }
        });
        vh.getTv_category().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    listener.onSelected(meshChannelCategory);
                }
            }
        });

    }

    boolean mod(int n,int i)
    {
        int r;
        r=n/i;
        return r>=1;
    }
}
