package com.ph.bittelasia.meshtv.tv.carlson.Movies.Model;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.VH.MeshTVVHolder;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVOD;


/**
 * Created by ramil on 1/23/18.
 */

public class CarlsonVodListViewHolder extends MeshTVVHolder<MeshVOD>
{
    @BindWidget(R.id.ll_grid)
    public LinearLayout ll_grid;

    @BindWidget(R.id.iv_image)
    public ImageView iv_icon;


    @BindWidget(R.id.tv_name)
    public TextView tv_name;


    public TextView getTv_name() {
        return tv_name;
    }

    public ImageView getIv_icon()
    {
        return iv_icon;
    }

    public LinearLayout getLl_grid() {
        return ll_grid;
    }
}

