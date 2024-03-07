package com.ph.bittelasia.meshtv.tv.carlson.DigitalSignage;

import android.widget.ImageView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.DataSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.View.Default.MeshDigitalSigangeImage;
@Layout(R.layout.bittel_layout_image)
@DataSetting()
public class CarlsonDigitalSignageImage extends MeshDigitalSigangeImage
{

    //=========================================Variable=============================================
    //-----------------------------------------Constant---------------------------------------------
    public static final String TAG = CarlsonDigitalSignageImage.class.getSimpleName();
    //----------------------------------------------------------------------------------------------
    //-------------------------------------------View-----------------------------------------------
    @BindWidget(R.id.iv_image)
    public ImageView iv_image;
    //----------------------------------------------------------------------------------------------
    //-----------------------------------------Instance---------------------------------------------

    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //==================================MeshDigitalSigangeImage=====================================
    @Override
    public ImageView getIv_image() {
        return iv_image;
    }
    //==============================================================================================
}
