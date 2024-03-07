package com.ph.bittelasia.meshtv.tv.carlson.Airmedia.Model;

import android.content.Context;
import android.text.Html;
import android.widget.ListView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.AirMedia.Model.MeshAirmediaInstructions;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.ViewHolder.ViewHolderLayout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.Adapter.MeshTVAdapter;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.VH.MeshTVVHolder;

import java.util.ArrayList;

/**
 * Created by ramil on 12/26/17.
 */
@ViewHolderLayout(layout = R.layout.carlson_airmedia_list_item_layout)
public class CarlsonAirMediaListAdapter extends MeshTVAdapter<MeshAirmediaInstructions> {

    public CarlsonAirMediaListAdapter(Context context, ListView lv_list, int layoutResourceId, ArrayList<MeshAirmediaInstructions> data) {
        super(context, lv_list, layoutResourceId, data);
    }

    @Override
    public MeshTVVHolder setViewHolder() {
        return new CarlsonAirMediaListViewHolder();
    }

    @Override
    public void updateRow(MeshTVVHolder meshTVVHolder, MeshAirmediaInstructions meshAirmediaInstructions) {
        try
        {
            CarlsonAirMediaListViewHolder vh = (CarlsonAirMediaListViewHolder) meshTVVHolder;
            vh.getTv_number().setText(meshAirmediaInstructions.getNo() + "");
            vh.getTv_req().setText(Html.fromHtml(meshAirmediaInstructions.getText()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
