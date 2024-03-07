package com.ph.bittelasia.meshtv.tv.carlson.Billing.Model;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.ViewHolder.ViewHolderLayout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Manager.MeshTVPreferenceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.Adapter.MeshTVAdapter;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.VH.MeshTVVHolder;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Bill.MeshBillV2;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ramil on 1/11/18.
 */
@ViewHolderLayout(layout = R.layout.carlson_billing_items)
public class CarlsonBillingListAdapter extends MeshTVAdapter<MeshBillV2> {

    public CarlsonBillingListAdapter(Context context, ListView lv_list, int layoutResourceId, ArrayList<MeshBillV2> data) {
        super(context, lv_list, layoutResourceId, data);
    }

    @Override
    public MeshTVVHolder setViewHolder() {
        return new CarlsonBillingListViewHolder();
    }

    @Override
    public void updateRow(MeshTVVHolder meshTVVHolder, MeshBillV2 meshBillV2) {
        try {
            CarlsonBillingListViewHolder vh = (CarlsonBillingListViewHolder) meshTVVHolder;
            if(meshBillV2.getItem().length()> 40)
            {
                vh.getTv_name().setSingleLine(false);
                vh.getTv_name().getLayoutParams().width=800;
                vh.getTv_name().requestLayout();
            }
            vh.getTv_name().setText(meshBillV2.getItem());
            DecimalFormat df = new DecimalFormat(MeshTVPreferenceManager.getHotelCurrency(getContext()) + " ###,##0.00");
            vh.getTv_price().setText(meshBillV2.getPrice()>0?df.format(meshBillV2.getPrice()):"FREE");
            Date d = new Date();
            d.setTime((long)(meshBillV2.getDa()) * 10000);
            vh.getTc_date().setText(new SimpleDateFormat("dd MMM yyyy", Locale.US).format(d));
            vh.getTc_date().setVisibility(View.GONE);
            Log.i("steward", meshBillV2.getDa() + "");
            Log.i("steward", meshBillV2.getDc() + "");
            Log.i("steward", meshBillV2.getFn() + "");
            Log.i("steward", meshBillV2.getTi() + "");
            Log.i("steward", meshBillV2.getTi() + "");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
