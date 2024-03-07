package com.ph.bittelasia.meshtv.tv.carlson.DigitalSignage;

import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.ScrollingTextView;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.VH.MeshTVVHolder;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.Model.MeshSignage;

public class CarlsonDigitalSigangeV1ViewHolder extends MeshTVVHolder<MeshSignage>
{

    @BindWidget(R.id.tv_display_name)
    public ScrollingTextView tv_display_name;
    @BindWidget(R.id.tv_date_start)
    public TextView tv_date_start;
    public ScrollingTextView getTv_display_name()
    {
        return tv_display_name;
    }
    public TextView getTv_date_start()
    {
        return tv_date_start;
    }

}
