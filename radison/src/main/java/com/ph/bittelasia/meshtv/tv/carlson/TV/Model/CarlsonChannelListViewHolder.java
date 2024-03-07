package com.ph.bittelasia.meshtv.tv.carlson.TV.Model;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.VH.MeshTVVHolder;

/**
 * Created by ramil on 2/7/18.
 */

public class CarlsonChannelListViewHolder extends MeshTVVHolder {


    @BindWidget(R.id.iv_list)
    public ImageView iv_list;



    public ImageView getIv_list() {
        return iv_list;
    }

}
