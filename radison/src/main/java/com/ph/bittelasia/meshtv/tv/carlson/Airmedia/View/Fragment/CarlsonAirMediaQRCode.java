package com.ph.bittelasia.meshtv.tv.carlson.Airmedia.View.Fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog.MeshTVDialog;


/**
 * Created by ramil on 12/27/17.
 */

public class CarlsonAirMediaQRCode extends MeshTVDialog {

    ImageView iv_qr;
    TextView  tv_text;

    @Override
    public void setIDs(View view)
    {
      iv_qr  =(ImageView)view.findViewById(R.id.iv_qr);
      tv_text=(TextView)view.findViewById(R.id.tv_text);
    }

    @Override
    public void setContent()
    {
        try
        {
            iv_qr.setImageDrawable(getContext().getResources().getDrawable(R.drawable.qr_code));
            tv_text.setText(getContext().getResources().getString(R.string.qr));
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
    }


    @Override
    public int setLayout()
    {
        return R.layout.carlson_airmedia_qr_layout;
    }

    @Override
    public int setConstraintLayout()
    {
        return R.id.cl_layout;
    }

    @Override
    public double setPercentageWidth() {
        return 0.20;
    }

    @Override
    public double setPercentageHeight() {
        return 0.45;
    }
}

