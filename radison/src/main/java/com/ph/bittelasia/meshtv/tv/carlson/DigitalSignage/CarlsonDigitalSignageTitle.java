package com.ph.bittelasia.meshtv.tv.carlson.DigitalSignage;

import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.DataSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.Model.MeshSignage;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.View.Default.MeshDigitalSignageText;

@Layout(R.layout.bittel_layout_1_title)
@DataSetting()
public class CarlsonDigitalSignageTitle extends MeshDigitalSignageText
{
    //=============================================Variable=========================================
    //---------------------------------------------Constant-----------------------------------------
    public static final String TAG = CarlsonDigitalSignageTitle.class.getSimpleName();
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------View--------------------------------------------
    @BindWidget(R.id.tv_title)
    public TextView tv_title;
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //====================================MeshDigitalSignageText====================================
    @Override
    public void updateDisplay(MeshSignage meshSignage)
    {
        if(tv_title!=null)
        {
            tv_title.setText(meshSignage.getDisplay_name());
        }

    }
    //==============================================================================================
}
