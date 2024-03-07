package com.ph.bittelasia.meshtv.tv.carlson.Launcher.View.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ph.bittelasia.mesh.tv.libFragment.view.fragment.CustomDialog;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Listener.MeshConfigListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Model.MeshConfig;
import com.squareup.picasso.Picasso;

public class WelcomeDialogFragment  extends CustomDialog implements MeshConfigListener {

    private static final String TAG = "WelcomeDialogFragment";
    ImageView iv_config;

    @Override
    public void setIDs(View view) {
        iv_config = view.findViewById(R.id.iv_logo);
    }

    @Override
    public void setContent() {
        try {
            MeshConfig config = new MeshConfig();
            config.update();
            Picasso.get().load(config.getHotel_logo()).into(iv_config);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int setLayout() {
        return R.layout.facility_video_watch_layout;
    }

    @Override
    public int setConstraintLayout() {
        return R.id.cl_layout;
    }

    @Override
    public double setPercentageWidth() {
        return .70;
    }

    @Override
    public double setPercentageHeight() {
        return .90;
    }

    @Override
    public void onHotelConfigurationChange(MeshConfig meshConfig) {
        Log.e(TAG, "onHotelConfigurationChange: "+meshConfig.toString());
    }
}
