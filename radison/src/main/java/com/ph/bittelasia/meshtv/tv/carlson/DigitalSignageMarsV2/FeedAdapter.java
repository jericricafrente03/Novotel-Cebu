package com.ph.bittelasia.meshtv.tv.carlson.DigitalSignageMarsV2;

import android.content.Context;
import android.widget.ListView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.ViewHolder.ViewHolderLayout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.Adapter.MeshTVAdapter;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.VH.MeshTVVHolder;
import com.ph.bittelasia.meshtv.tv.mtvlib.SignageV2.MeshTVFeed;

import java.util.ArrayList;

@ViewHolderLayout(layout= R.layout.carlson_feed_item_layout)
public class FeedAdapter extends MeshTVAdapter<MeshTVFeed>
{
    //============================================Variable==========================================
    //--------------------------------------------Constant------------------------------------------
    public static final String TAG = FeedAdapter.class.getSimpleName();
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //===========================================Constructor========================================
    public FeedAdapter(Context context, ListView lv_list, int layoutResourceId, ArrayList<MeshTVFeed> data)
    {
        super(context, lv_list, layoutResourceId, data);
    }
    //==============================================================================================
    //=========================================MeshTVAdapter========================================
    @Override
    public MeshTVVHolder setViewHolder() {
        return new FeedViewHolder();
    }
    @Override
    public void updateRow(MeshTVVHolder meshTVVHolder, MeshTVFeed meshTVFeed) {
        ((FeedViewHolder)meshTVVHolder).getTv_feed_title().setText(meshTVFeed.getTitle());
    }
    //==============================================================================================
}
