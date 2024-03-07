package com.ph.bittelasia.meshtv.tv.carlson.DigitalSignage;

import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.DataSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.Model.MeshSignage;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.View.Default.MeshDigitalSignageText;

@Layout(R.layout.bittel_layout_1_description)
@DataSetting()
public class CarlsonDigitalSignageDescription extends MeshDigitalSignageText
{
    //=============================================Variable=========================================
    //---------------------------------------------Constant-----------------------------------------
    public static final String TAG = CarlsonDigitalSignageDescription.class.getSimpleName();
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------View--------------------------------------------
    @BindWidget(R.id.tv_desc)
    public TextView tv_desc;
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //====================================MeshDigitalSignageText====================================
    @Override
    public void updateDisplay(MeshSignage meshSignage)
    {
        if(tv_desc!=null)
        {
            tv_desc.setText(meshSignage.getDescription());
        }

    }
    //==============================================================================================
}
