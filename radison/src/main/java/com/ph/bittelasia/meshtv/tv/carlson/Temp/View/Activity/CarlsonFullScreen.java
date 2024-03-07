package com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.PlayBackState;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Fragment.CarlsonChannelLabel;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Fragment.CarlsonPreview;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Fragment.CarlsonVodLabel;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.ActivitySetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Constant.AppDataSource;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.RemoteControl.KR301KeyCode;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Activity.MeshTVActivity;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.App.MeshTVApp;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Channel.MeshChannel;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVOD;

/**
 * Created by ramil on 1/29/18.
 */
@Layout(R.layout.carlson_fullscreen)
@ActivitySetting()
public class CarlsonFullScreen extends MeshTVActivity implements MeshTVFragmentListener {

    CarlsonPreview        tvPreview;
    CarlsonVodLabel       vodLabel;
    CarlsonChannelLabel   tvLabel;

    MeshVOD     vod;
    MeshChannel channel;

    public boolean tv=false;

    String media;
    int vodId;
    public int tvId;
    public  int lastTvId;


    @Override
    public void onBackPressed() {
        Intent i=new Intent();
        setResult(Activity.RESULT_OK,i);
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        try
        {
            switch (event.getAction()) {
                case KeyEvent.ACTION_DOWN:
                    switch (KR301KeyCode.getEquivalent(event.getKeyCode())) {
                        case KeyEvent.KEYCODE_BACK:
                            tvPreview.getPosition();
                            finish();
                            super.onBackPressed();
                            return true;
                        case 92: //channel up
                            if (tv) {
                                try {
                                    if (tvLabel != null) {
                                        tvId=tvId+1;
                                        if(tvId > lastTvId)
                                        {
                                            tvId=1;
                                            Log.i("steward", "tv id channel up greater:  " + tvId + "  ->lastTvId:"+lastTvId);
                                        }
                                        tvLabel.setSelected(tvId);
                                    }
                                } catch (Exception e) {
                                    if (tvLabel != null) {
                                        tvLabel.setSelected(1);
                                        tvId = 1;
                                    }
                                }
                            }
                            Log.i("steward", "tv id channel up:  " + tvId);
                            return true;
                        case 93: //channel down
                            if (tv) {
                                try {
                                    if (tvLabel != null) {
                                        tvId=tvId-1;
                                        if(tvId==0)
                                        {
                                            tvId=lastTvId;
                                            Log.i("steward", "tv id channel down greater:  " + tvId + "  ->lastTvId:"+lastTvId);
                                        }
                                        tvLabel.setSelected(tvId);
                                    }
                                } catch (Exception e) {
                                    if (tvLabel != null) {
                                        tvLabel.setSelected(lastTvId);
                                        tvId = lastTvId;
                                    }
                                }
                                Log.i("steward", "tv id channel down:  " + tvId);
                            }
                            return true;

                    }
                    break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return super.dispatchKeyEvent(event);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        vodId=getIntent().getIntExtra("VOD",1);
        tvId=getIntent().getIntExtra("CHANNEL",1);
        lastTvId=getIntent().getIntExtra("ID",1);
        media=getIntent().getStringExtra("MEDIA");
        if(media.equals("CHANNEL"))
        {
            tvLabel = new CarlsonChannelLabel();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.ll_label, tvLabel, "tvLabel")
                    .commit();
            if(MeshTVApp.get().getDataSourceSetting()== AppDataSource.SERVER) {
                tvLabel.setSelected(tvId);
                Log.i("steward","server: tv_id"+tvId);
            }
            else
            {
                tvLabel.setSelected(tvId-1);
                Log.i("steward","local: tv_id"+tvId);
            }
            tv=true;
        }
        else if(media.equals("VOD"))
        {
            vodLabel = new CarlsonVodLabel();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.ll_label, vodLabel, "vodLabel")
                    .commit();
            vodLabel.setSelected(vodId);
//            if(MeshTVApp.get().getDataSourceSetting()== AppDataSource.SERVER) {
//                vodLabel.setSelected(vodId);
//                Log.i("steward","server: vod_id->"+vodId);
//            }
//            else
//            {
//                vodLabel.setSelected(vodId);
//                Log.i("steward","local: vod_id->"+vodId);
//            }
            tv=false;
        }
    }


    @Override
    public void onClicked(Object o) {

    }

    @Override
    public void onSelected(Object o)
    {
        try
        {
            Log.i("steward", "onselected");
            if(o instanceof MeshVOD)
            {
                vod = (MeshVOD) o;
                tvPreview = CarlsonPreview.preview(vod, 1);
                Log.i("steward", "onselected->"+vod.getFull());
                getSupportFragmentManager().beginTransaction().replace(R.id.ll_container, tvPreview, "container").commit();
            }
            else if(o instanceof MeshChannel)
            {
                channel = (MeshChannel) o;
                if(tvPreview==null)
                {
                    tvPreview = CarlsonPreview.preview(channel, 1);
                    getSupportFragmentManager().beginTransaction().add(R.id.ll_container, tvPreview, "container").commit();
                }
                else
                {
                    tvPreview.updateChannel(channel);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
