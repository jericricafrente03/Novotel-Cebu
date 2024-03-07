package com.ph.bittelasia.meshtv.tv.carlson.DigitalSignageMarsV2;

import android.widget.ListView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Exoplayer.MeshTVPlayerSettings;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.SignageV2.Control.MediaFragmentSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.SignageV2.MeshMediaV2;
import com.ph.bittelasia.meshtv.tv.mtvlib.SignageV2.MeshTVFeed;
import com.ph.bittelasia.meshtv.tv.mtvlib.SignageV2.View.MeshMediaFragment;

import java.util.ArrayList;
@MediaFragmentSetting(imageId = R.id.iv_logo)
@Layout(R.layout.carlson_image_mediafragment)
@MeshTVPlayerSettings(hasControls=false)
public class CarlsonLogoMediaFragment extends MeshMediaFragment
{



    @Override
    public void displayList(ListView listView, ArrayList<MeshTVFeed> arrayList, MeshMediaV2 meshMediaV2) {

    }
}
