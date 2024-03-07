package com.ph.bittelasia.meshtv.tv.carlson.Airmedia.View.Fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.ResourceManager.MeshResourceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;

import java.util.ArrayList;

/**
 * Created by ramil on 12/27/17.
 */
@Layout(R.layout.carlson_airmedia_status)
public class CarlsonAirMediaStatus extends MeshTVFragment {

    @BindWidget(R.id.tv_status)
    public TextView tv_status;

    @BindWidget(R.id.iv_status)
    public ImageView iv_status;

    public void setStatus(boolean ready)
    {
        if(ready)
        {
            tv_status.setText(getContext().getResources().getString(R.string.ready));
            iv_status.setImageDrawable(getContext().getResources().getDrawable(R.drawable.bt_on));
        }
        else
        {
            tv_status.setText(getContext().getResources().getString(R.string.notready));
            iv_status.setImageDrawable(getContext().getResources().getDrawable(R.drawable.bt_off));
        }
    }

    @Override
    protected void onDrawDone(View view) {
        setStatus(false);
    }

    @Override
    protected void onDataUpdated(ArrayList arrayList) {

    }

    @Override
    protected void onNewData(Object o) {

    }

    @Override
    protected void onDataUpdated(Object o, int i) {

    }

    @Override
    protected void onDeleteData(int i) {

    }

    @Override
    protected void onClearData() {

    }

    @Override
    protected void onDataNotFound(Class aClass) {

    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void update(Object o) {

    }
}
