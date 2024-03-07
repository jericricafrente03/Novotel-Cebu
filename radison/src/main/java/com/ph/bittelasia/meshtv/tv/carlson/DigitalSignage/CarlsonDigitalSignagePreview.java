package com.ph.bittelasia.meshtv.tv.carlson.DigitalSignage;

import com.ph.bittelasia.meshtv.tv.carlson.Movies.View.Fragment.CarlsonVodPreview;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.ActivitySetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Exoplayer.MeshTVPlayerSettings;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.Model.MeshSignage;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.View.Default.MeshDigitalSignageVideo;

@MeshTVPlayerSettings(layout = R.layout.carlson_player, exoplayer = R.id.exo_prev,hasControls=true)
public class CarlsonDigitalSignagePreview extends MeshDigitalSignageVideo
{
    public static CarlsonDigitalSignagePreview get(MeshSignage signage)
    {
        CarlsonDigitalSignagePreview preview = new CarlsonDigitalSignagePreview();
        preview.setMeshSignage(signage);
        return preview;
    }
    @Override
    public int setSimpleExoplayerView()
    {

        return R.id.exo_prev;
    }
}
