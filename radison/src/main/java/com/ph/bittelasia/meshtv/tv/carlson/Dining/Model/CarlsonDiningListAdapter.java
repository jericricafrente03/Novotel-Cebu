package com.ph.bittelasia.meshtv.tv.carlson.Dining.Model;


import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.ViewHolder.ViewHolderLayout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.ResourceManager.MeshResourceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.Adapter.MeshTVAdapter;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.VH.MeshTVVHolder;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Dining.MeshFood;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

/**
 * Created by ramil on 1/23/18.
 */
@ViewHolderLayout(layout = R.layout.carlson_grid_list_item)
public class CarlsonDiningListAdapter extends MeshTVAdapter<MeshFood> {

    private VelocityTracker mVelocityTracker = null;


    public CarlsonDiningListAdapter(Context context, GridView gv_view, int layoutResourceId, ArrayList<MeshFood> data) {
        super(context, gv_view, layoutResourceId, data);
    }

    @Override
    public MeshTVVHolder setViewHolder() {
        return new CarlsonDiningListViewHolder();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void updateRow(MeshTVVHolder meshTVVHolder, final MeshFood meshFood) {
        CarlsonDiningListViewHolder vh=(CarlsonDiningListViewHolder)meshTVVHolder;
        try
        {
            // MeshResourceManager.displayLiveImageFor(getContext(),vh.getIv_icon(),meshFood.getImg_uri());
            Picasso.get().load(meshFood.getImg_uri()).into(vh.getIv_icon());

            vh.getTv_name().setText(meshFood.getItem_name());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
