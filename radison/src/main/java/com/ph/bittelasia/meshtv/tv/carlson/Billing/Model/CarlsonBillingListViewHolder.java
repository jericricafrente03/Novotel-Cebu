package com.ph.bittelasia.meshtv.tv.carlson.Billing.Model;

import android.widget.TextView;


import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.VH.MeshTVVHolder;
import com.ph.bittelasia.meshtv.tv.mtvlib.Transaction.Model.MeshCartItem;

/**
 * Created by ramil on 1/11/18.
 */

public class CarlsonBillingListViewHolder extends MeshTVVHolder<MeshCartItem> {

    @BindWidget(R.id.tv_name)
    public TextView tv_name;
    @BindWidget(R.id.tv_price)
    public TextView tv_price;
    @BindWidget(R.id.tc_date)
    public TextView tc_date;

    public TextView getTv_name() {
        return tv_name;
    }

    public TextView getTv_price() {
        return tv_price;
    }
    public TextView getTc_date() {
        return tc_date;
    }


}
