package com.ph.bittelasia.meshtv.tv.carlson.Message.Model;

import android.widget.ImageView;
import android.widget.TextView;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.VH.MeshTVVHolder;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Message.MeshMessage;

public class CarlsonMessageListViewHolder extends MeshTVVHolder<MeshMessage>
{

    @BindWidget(R.id.iv_status)
    public ImageView iv_status;

    @BindWidget(R.id.tv_from)
    public TextView tv_from;

    @BindWidget(R.id.tv_subject)
    public TextView tv_subject;

    @BindWidget(R.id.tv_date)
    public TextView tv_date;

    public ImageView getIv_status() {
        return iv_status;
    }
    public TextView getTv_from() {
        return tv_from;
    }
    public TextView getTv_subject() {
        return tv_subject;
    }
    public TextView getTv_date() {
        return tv_date;
    }

}
