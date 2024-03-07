package com.ph.bittelasia.meshtv.tv.carlson.DigitalSignageMarsV2;

import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.VH.MeshTVVHolder;
import com.ph.bittelasia.meshtv.tv.mtvlib.SignageV2.MeshTVFeed;

public class FeedViewHolder extends MeshTVVHolder<MeshTVFeed>
{

    //==========================================Variable============================================
    //--------------------------------------------View----------------------------------------------
    @BindWidget(R.id.tv_feed_title)
    public TextView tv_feed_title;
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //============================================Method============================================
    //--------------------------------------------Getter--------------------------------------------
    public TextView getTv_feed_title() {
        return tv_feed_title;
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
}
