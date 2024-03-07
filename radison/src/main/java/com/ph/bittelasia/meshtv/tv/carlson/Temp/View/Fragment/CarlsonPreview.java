package com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlaybackControlView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.PreviewSession;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.SessionManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Exoplayer.MeshTVPlayerSettings;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Constant.AppDataSource;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.FileReader.MeshTVDemoFileReader;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.App.MeshTVApp;
import com.ph.bittelasia.meshtv.tv.mtvlib.ExoPlayer.View.Fragment.MeshTVExoplayerFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Channel.MeshChannel;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVOD;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.Model.MeshSignage;

/**
 * Created by ramil on 2/8/18.
 */
@MeshTVPlayerSettings(layout = R.layout.carlson_player, exoplayer = R.id.exo_prev,hasControls=true)
public class CarlsonPreview extends MeshTVExoplayerFragment
{

    /* 0 = trailer, 1 = full */

    //=========================================Variable=============================================
    //-----------------------------------------Constant---------------------------------------------
    public static final String TAG=CarlsonPreview.class.getSimpleName();
    public static CarlsonPreview fragment;
    //----------------------------------------------------------------------------------------------
    //-----------------------------------------Instance---------------------------------------------
    public                 SimpleExoPlayerView sv;
    public                 LinearLayout        ll_error;
    LinearLayout           ll_press;
    TextView               tv_insruct;
    PreviewSession         previewSession;
    int                    full;
    MeshVOD                meshVod;
    MeshChannel            meshChannel;
    MeshSignage             meshSignage;
    Object                 object;
    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //======================================Constructor=============================================
    //----------------------------------------------------------------------------------------------
    public static CarlsonPreview preview(Object object,int full)
    {
        fragment=new CarlsonPreview();
        fragment.object=object;
        fragment.full=full;
        return fragment;
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    
    //======================================Fragment================================================
    //----------------------------------------------------------------------------------------------

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        sv = view.findViewById(R.id.exo_prev);
        sv.getVideoSurfaceView().bringToFront();
        sv.setFocusable(false);
        ll_press=view.findViewById(R.id.ll_press);
        ll_error=view.findViewById(R.id.ll_nosignal);
        tv_insruct=view.findViewById(R.id.tv_instruct);
        play();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        play();
    }

    @Override
    public void onPause()
    {
        super.onPause();

    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    
    //=========================================Method===============================================
    //----------------------------------------------------------------------------------------------
    
    public void play()
    {
        try
        {
            Log.i("MARS-RAY",(object instanceof MeshSignage?"SIGNAGE":"NOT SIGNAGE"));
            previewSession = new PreviewSession();
            if (object instanceof MeshVOD)
            {
                sv.setUseController(true);
                meshVod = (MeshVOD) object;
                if (full == 0) {
                    if(MeshTVApp.get().getDataSourceSetting()== AppDataSource.SERVER)
                    {
                        setObjectURL(meshVod.getTrailer());
                        sv.getPlayer().setPlayWhenReady(true);
                        sv.getPlayer().setRepeatMode(Player.REPEAT_MODE_ONE);
                        ll_press.setVisibility(View.GONE);
                        Log.i(TAG,"trailer: server->: "+meshVod.getTrailer());
                    }
                    else
                    {
                        setObjectURL(meshVod.getTrailer());
                        //setObjectURL(MeshTVDemoFileReader.getMediaPath() + meshVod.getTrailer());
                        ll_press.setVisibility(View.VISIBLE);
                        Log.i(TAG, "vod preview: " + MeshTVDemoFileReader.getMediaPath() + meshVod.getTrailer());
                    }
                    sv.hideController();
                    sv.setControllerVisibilityListener(new PlaybackControlView.VisibilityListener() {
                        @Override
                        public void onVisibilityChange(int i) {
                            if(i == 0) {
                                sv.hideController();
                            }
                        }
                    });
                } else {
                    if(MeshTVApp.get().getDataSourceSetting()== AppDataSource.SERVER)
                    {
                        setObjectURL(meshVod.getFull());
                        Log.i(TAG,"full: server->: "+meshVod.getFull());
                    }
                    else
                    {
                        setObjectURL( meshVod.getFull());
                        //setObjectURL(MeshTVDemoFileReader.getMediaPath() + meshVod.getFull());
                        Log.i(TAG, "vod preview: " + MeshTVDemoFileReader.getMediaPath() + meshVod.getFull());
                        //sv.getPlayer().seekTo(previewSession.vodList.get(meshVod.getId()));
                    }
                }
                tv_insruct.setText(getActivity().getResources().getString(R.string.vod_instruct));
            } else if (object instanceof MeshChannel) {
                meshChannel = (MeshChannel) object;
                sv.setUseController(false);
                Log.i(TAG, "channel preview: " + MeshTVDemoFileReader.getMediaPath() + meshChannel.getChannel_uri());
                if(MeshTVApp.get().getDataSourceSetting()== AppDataSource.SERVER)
                {
                    setObjectURL(meshChannel.getChannel_uri());
                }
                else
                {
                    setObjectURL(meshChannel.getChannel_uri());
                //    setObjectURL(MeshTVDemoFileReader.getMediaPath() + meshChannel.getChannel_uri());
                    sv.getPlayer().seekTo(previewSession.vodList.get(meshChannel.getId()));
                }
                tv_insruct.setText(getActivity().getResources().getString(R.string.tv_instruct));
            }
            else if (object instanceof MeshSignage)
            {
                meshSignage = (MeshSignage) object;
                sv.setUseController(false);
                if(MeshTVApp.get().getDataSourceSetting()== AppDataSource.SERVER)
                {
                    setObjectURL(meshSignage.getMedia());
                }
                else
                {
                    setObjectURL(MeshTVDemoFileReader.getMediaPath() + meshSignage.getMedia());

                }
                tv_insruct.setText("");
            }else if(object instanceof String){
                setObjectURL((String)object);
                sv.setUseController(false);
                sv.getPlayer().setPlayWhenReady(true);
                sv.getPlayer().setRepeatMode(Player.REPEAT_MODE_ONE);
                //ll_press.setVisibility(View.GONE);
            }
            sv.getPlayer().addListener(new Player.EventListener() {
                @Override
                public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {
                    Log.i(TAG+"-state", "timeline: "+timeline.getPeriodCount()+" ->manifest: "+manifest);
                }

                @Override
                public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
                    Log.i(TAG+"-state", "track: "+trackGroups.length);
                }

                @Override
                public void onLoadingChanged(boolean isLoading) {
                    Log.i(TAG+"-state", "loading change: " + isLoading);
                }

                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                    if (playWhenReady) {
                        switch (playbackState)
                        {
                            case 1: //STATE_IDLE
                                Log.i(TAG+"-state", "STATE_IDLE");

                                setErrorVisibility(true);
                                break;
                            case 2: //STATE_BUFFERING
                                Log.i(TAG+"-state", "STATE_BUFFERING");

                                break;
                            case 3: //STATE_READY
                                Log.i(TAG+"-state", "STATE_READY");

                                setErrorVisibility(false);
                                break;
                            case 4: //STATE_ENDED
                                Log.i(TAG+"-state", "STATE_ENDED");

                                break;
                        }
                    }else
                    {
                        setErrorVisibility(true);
                        if(playbackState==3) //STATE_READY
                        {
                            setErrorVisibility(false);
                        }
                        Log.i(TAG+"-playback",""+playbackState);
                    }
                }

                @Override
                public void onRepeatModeChanged(int repeatMode) {
                    Log.i(TAG+"-state", "repeatmode");
                }

                @Override
                public void onPlayerError(ExoPlaybackException error) {
                    Log.i(TAG+"-state", "playerror");
                    setErrorVisibility(true);
                }

                @Override
                public void onPositionDiscontinuity(int reason) {
                    Log.i(TAG+"-state", "discontinue");
                }

                @Override
                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                    Log.i(TAG+"-state", "playback");
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void setErrorVisibility(boolean b)
    {
        if(b)
        {
            sv.setVisibility(View.GONE);
            ll_error.setVisibility(View.VISIBLE);
        }
        else
        {
            sv.setVisibility(View.VISIBLE);
            ll_error.setVisibility(View.GONE);
        }
    }

    public void updateChannel(MeshChannel channel)
    {
        sv.getPlayer().stop();
        setObjectURL(channel.getChannel_uri());
        sv.getPlayer().setPlayWhenReady(true);
    }

    public void getPosition()
    {
        try
        {
            if (sv.getPlayer().getPlayWhenReady())
            {
                if(object instanceof MeshVOD)
                {
                    previewSession=new  PreviewSession(meshVod.getId(),sv.getPlayer().getCurrentPosition(),meshVod);
                    previewSession.vodList.put(meshVod.getId(),sv.getPlayer().getCurrentPosition());
                    new SessionManager(getContext()).setKeyChange(sv.getPlayer().getCurrentPosition(), meshVod.getId());
                }
                if(object instanceof MeshChannel)
                {
                    previewSession=new  PreviewSession(meshVod.getId(),sv.getPlayer().getCurrentPosition(),meshChannel);
                    previewSession.vodList.put(meshChannel.getId(),sv.getPlayer().getCurrentPosition());
                    new SessionManager(getContext()).setKeyChange(sv.getPlayer().getCurrentPosition(), meshChannel.getId());
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    

    public void setChannel(MeshVOD meshVod) {
//        try
//        {
//            handler.removeCallbacks(r);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        this.meshVod = meshVod;
//        handler.postDelayed(r,20000);;
    }

    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    
}
