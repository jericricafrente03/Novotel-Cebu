package com.ph.bittelasia.meshtv.tv.carlson.Facilities.Model;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.AdapterInterface;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.CarlsonCategoryViewHolder;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.CustomView.MeshTVCustomList.MeshTVCustomAdapter;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacilityCategory;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by ramil on 2/12/18.
 */

public class CarlsonFacilityCategoryAdapter extends MeshTVCustomAdapter<MeshFacilityCategory> {

    int x=0;
    AdapterInterface listener;

    private VelocityTracker mVelocityTracker = null;

    public CarlsonFacilityCategoryAdapter(ArrayList<MeshFacilityCategory> items, AdapterInterface listener) {
        super(items);
        this.listener=listener;
    }

    @Override
    public void bindView(View view, final MeshFacilityCategory meshFacilityCategory) {
        try
        {
            final CarlsonCategoryViewHolder vh = new CarlsonCategoryViewHolder();
            vh.inflate(view);
            vh.getTv_category().setText(meshFacilityCategory.getCategory_name());
            x++;
            if (x==getAllItems().size())
            {
               vh.getV_bar().setVisibility(View.GONE);
                x = 0;
            }
            vh.getTv_category().setOnHoverListener(new View.OnHoverListener() {
                @Override
                public boolean onHover(View view, MotionEvent motionEvent) {
                    vh.getTv_category().setTextColor(Color.RED);
                    return false;
                }
            });
            ViewGroup vg=(ViewGroup)vh.getV();
            vg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(meshFacilityCategory);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean mod(int n,int i)
    {
        int r;
        r=n/i;
        return r>=1;
    }
}
