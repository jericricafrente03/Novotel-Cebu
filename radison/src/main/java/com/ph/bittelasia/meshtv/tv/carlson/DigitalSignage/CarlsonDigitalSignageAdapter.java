package com.ph.bittelasia.meshtv.tv.carlson.DigitalSignage;

import android.content.Context;
import android.widget.ListView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.ViewHolder.ViewHolderLayout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.Adapter.MeshTVAdapter;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.VH.MeshTVVHolder;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.Model.MeshSignage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@ViewHolderLayout(layout = R.layout.bittel_layout_1_signage_item)
public class CarlsonDigitalSignageAdapter extends MeshTVAdapter<MeshSignage>
{

    public CarlsonDigitalSignageAdapter(Context context, ListView lv_list, int layoutResourceId, ArrayList<MeshSignage> data) {
        super(context, lv_list, layoutResourceId, data);
    }

    @Override
    public MeshTVVHolder setViewHolder() {
        return new CarlsonDigitalSigangeV1ViewHolder();
    }

    @Override
    public void updateRow(MeshTVVHolder meshTVVHolder, MeshSignage meshSignage) {
        CarlsonDigitalSigangeV1ViewHolder vh=(CarlsonDigitalSigangeV1ViewHolder)meshTVVHolder;
        try
        {
//            vh.getTv_name().setText(meshFood.getItem_name());
            vh.getTv_display_name().setText(meshSignage.getDisplay_name());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd kk:mm:ss");
            Date start = df.parse(meshSignage.getSchedule_start());
            Date end = df.parse(meshSignage.getSchedule_end());
            SimpleDateFormat df2 = new SimpleDateFormat("MMM dd @ hh:mm aa");
            vh.getTv_date_start().setText(df2.format(start)+" ~ "+df2.format(end));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
