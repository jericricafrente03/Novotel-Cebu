package com.ph.bittelasia.meshtv.tv.carlson.Temp.Model;

import android.view.View;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.VH.MeshTVVHolder;

/**
 * Created by ramil on 2/7/18.
 */

public class CarlsonCategoryViewHolder extends MeshTVVHolder {

    @BindWidget(R.id.tv_category)
    public TextView tv_category;

    @BindWidget(R.id.v_bar)
    public View v_bar;

    public TextView getTv_category() {
        return tv_category;
    }

    public View getV_bar() {
        return v_bar;
    }
}
