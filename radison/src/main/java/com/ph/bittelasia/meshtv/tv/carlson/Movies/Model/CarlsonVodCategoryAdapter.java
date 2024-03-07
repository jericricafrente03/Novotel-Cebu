package com.ph.bittelasia.meshtv.tv.carlson.Movies.Model;


import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.AdapterInterface;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.CarlsonCategoryViewHolder;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.CustomView.MeshTVCustomList.MeshTVCustomAdapter;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshGenre;

import java.util.ArrayList;

/**
 * Created by ramil on 1/23/18.
 */

public class CarlsonVodCategoryAdapter extends MeshTVCustomAdapter<MeshGenre> {
    int x=0;
    AdapterInterface listener;
    ArrayList<TextView> views;



    public CarlsonVodCategoryAdapter(ArrayList<MeshGenre> items, AdapterInterface listener) {
        super(items);
        this.listener=listener;
        this.views=new ArrayList<>();
    }

    @Override
    public void bindView(View view, final MeshGenre meshGenre) {
        final CarlsonCategoryViewHolder vh = new CarlsonCategoryViewHolder();
        vh.inflate(view);
        vh.getTv_category().setText(meshGenre.getGenre());
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
                listener.onClick(meshGenre);
                listener.onSelected(vh.getTv_category());
            }
        });
        vh.getTv_category().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    listener.onSelected(meshGenre);
                }
            }
        });
        vh.getTv_category().setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View view, MotionEvent motionEvent) {
                vh.getTv_category().setTextColor(Color.RED);
                listener.onSelected(meshGenre);
                return false;
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
