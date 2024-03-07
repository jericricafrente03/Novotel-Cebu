package com.ph.bittelasia.meshtv.tv.carlson.DigitalSignageMarsV2;

import android.widget.ListView;

import com.ph.bittelasia.meshtv.tv.carlson.Core.App.Carlson;
import com.ph.bittelasia.meshtv.tv.carlson.Core.App.CarlsonScrollingMessageUpdateListener;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Exoplayer.MeshTVPlayerSettings;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.App.MeshTVApp;
import com.ph.bittelasia.meshtv.tv.mtvlib.SignageV2.Control.MediaFragmentSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.SignageV2.MeshMediaV2;
import com.ph.bittelasia.meshtv.tv.mtvlib.SignageV2.MeshScrollingMessage;
import com.ph.bittelasia.meshtv.tv.mtvlib.SignageV2.MeshTVFeed;
import com.ph.bittelasia.meshtv.tv.mtvlib.SignageV2.View.MeshMediaFragment;

import java.util.ArrayList;

@MediaFragmentSetting(imageId = R.id.iv_image,videoId = R.id.exo_player,blockerId = R.id.ll_blocker,tickerId = R.id.tv_notification,feedListId = R.id.lv_list
,timeId = R.id.tc_clock
,dateId = R.id.tv_date)
@Layout(R.layout.carlson_signage)
@MeshTVPlayerSettings(hasControls=false)
public class CarlsonMediaFragment extends MeshMediaFragment implements CarlsonScrollingMessageUpdateListener
{
    //============================================Variable==========================================
    //--------------------------------------------Constant------------------------------------------
    public static final String TAG = CarlsonMediaFragment.class.getSimpleName();
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //============================================LifeCYcle=========================================

    @Override
    public void onResume() {
        super.onResume();
        ((Carlson) MeshTVApp.get()).addListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        ((Carlson) MeshTVApp.get()).removeListener(this);
    }

    @Override
    public void displayList(ListView lv, ArrayList<MeshTVFeed> feeds, MeshMediaV2 media) {
        FeedAdapter adapter = new FeedAdapter(getActivity(),lv,R.layout.carlson_feed_item_layout,feeds);
        lv.setAdapter(adapter);
    }

    //==============================================================================================
    //===============================CarlsonScrollingMessageUpdateListener==========================
    @Override
    public void updateScrollingMessages(final MeshScrollingMessage meshScrollingMessage)
    {
        getActivity().runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                loadScrollingMessage(meshScrollingMessage);
            }
        });
    }

    @Override
    public int getZone()
    {
        return zone;
    }
    //==============================================================================================
}
