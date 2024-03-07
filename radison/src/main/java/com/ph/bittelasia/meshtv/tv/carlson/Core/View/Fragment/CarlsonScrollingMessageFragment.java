package com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment;

import android.view.View;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.CustomMessage.ScrollingSettings;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshNotificationListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.CustomMessage.ScrollingFragment.MeshTVScrollingFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Message.MeshMessage;

@Layout(R.layout.toledo_scrolling_fragment)
@ScrollingSettings(scrollingTextId = R.id.tv_notification)
public class CarlsonScrollingMessageFragment extends MeshTVScrollingFragment implements MeshNotificationListener
{
    //==========================================Variable============================================
    //------------------------------------------Constant--------------------------------------------
    public static final String TAG = CarlsonScrollingMessageFragment.class.getSimpleName();
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //=========================================MeshTVFragment=======================================
    @Override
    protected void onDrawDone(View view)
    {
        super.onDrawDone(view);

    }

    @Override
    public void onNotify(final MeshMessage meshMessage)
    {
        if(meshMessage.getMessg_type()== MeshMessage.TYPE_SCROLLING)
        {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setMSG(meshMessage);
                }
            });
        }
    }
    //==============================================================================================
}
