package com.ph.bittelasia.meshtv.tv.carlson.Message.Model;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.ViewHolder.ViewHolderLayout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.Adapter.MeshTVAdapter;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.VH.MeshTVVHolder;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Message.MeshMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ramil on 12/22/17.
 */
@ViewHolderLayout(layout= R.layout.carlson_message_list_item)
public class CarlsonMessageListAdapter extends MeshTVAdapter<MeshMessage> {

    public CarlsonMessageListAdapter(Context context, ListView lv_list, int layoutResourceId, ArrayList<MeshMessage> data) {
        super(context, lv_list, layoutResourceId, data);
    }

    @Override
    public MeshTVVHolder setViewHolder() {
        return new CarlsonMessageListViewHolder();
    }

    @Override
    public void updateRow(MeshTVVHolder meshTVVHolder, MeshMessage meshMessage) {
        CarlsonMessageListViewHolder vh=(CarlsonMessageListViewHolder)meshTVVHolder;
        try
        {
            if (meshMessage.getMessg_status() == 2)
            {
                vh.getIv_status().setBackground(getContext().getResources().getDrawable(R.drawable.read_white));
                vh.getIv_status().getLayoutParams().height = 60;
                vh.getIv_status().requestLayout();
            }
            else
            {
                vh.getIv_status().setBackground(getContext().getResources().getDrawable(R.drawable.unread_black));
                vh.getIv_status().getLayoutParams().height = 45;
                vh.getIv_status().requestLayout();
            }
            vh.getTv_from().setText(meshMessage.getMessg_from());
            vh.getTv_subject().setText(meshMessage.getMessg_subject());
            vh.getTv_date().setText((meshMessage.getMessg_datetime()+""));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
