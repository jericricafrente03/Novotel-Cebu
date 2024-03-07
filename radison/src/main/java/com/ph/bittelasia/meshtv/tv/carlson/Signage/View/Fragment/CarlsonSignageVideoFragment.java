package com.ph.bittelasia.meshtv.tv.carlson.Signage.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Exoplayer.MeshTVPlayerSettings;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.ExoPlayer.View.Fragment.MeshTVExoplayerFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Signage.Model.MeshSignage;


@MeshTVPlayerSettings(layout = R.layout.carlson_signage_video_layout, exoplayer = R.id.exo_play,hasControls=false)
public class CarlsonSignageVideoFragment extends MeshTVExoplayerFragment {

    //======================================Variable================================================
    //---------------------------------------Constant-----------------------------------------------
    public static final int STATE_IDLE        = 1;
    public static final int STATE_BUFFERING   = 2;
    public static final int STATE_READY       = 3;
    public static final int STATE_ENDED       = 4;
    public static final String TYPE_VIDEO     = "video";
    public static final String TAG=CarlsonSignageVideoFragment.class.getSimpleName();
    public static CarlsonSignageVideoFragment svp;
    //----------------------------------------------------------------------------------------------

    //---------------------------------------Instance-----------------------------------------------
    SimpleExoPlayerView                       sv;
    MeshSignage                               signage;
    MeshTVFragmentListener                    cb;
    int                                       position;
    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    public static CarlsonSignageVideoFragment get(MeshSignage signage, int position)
    {
        svp=new CarlsonSignageVideoFragment();
        svp.setSignage(signage);
        svp.setPosition(position);
        Bundle args = new Bundle();
        args.putInt("pos",position);
        args.putString("type",TYPE_VIDEO);
        svp.setArguments(args);
        return svp;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }



    public void setSignage(MeshSignage signage) {
        this.signage = signage;
    }

    public MeshSignage getSignage() {
        return signage;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser)
        {
            play();
        }
        else
        {
            stop();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cb=(MeshTVFragmentListener)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        cb=null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        sv = view.findViewById(R.id.exo_play);
        sv.getVideoSurfaceView().bringToFront();
        Log.i(TAG,"vid created");
        Log.i(TAG,"sv id: "+sv.getId());
    }

    public void play()
    {
        try {
            if (getSignage() != null && sv != null) {
                if (getSignage().getFileType().equals(MeshSignage.TYPE_VIDEO)) {
                    Log.i(TAG, "getSignage().getMedia()=" + getSignage().getMedia());
                    setObjectURL(getSignage().getMedia());
                    sv.getPlayer().setPlayWhenReady(true);
                    sv.getPlayer().addListener(new Player.EventListener() {

                        @Override
                        public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {
                            Log.i(TAG, "timeline");
                        }

                        @Override
                        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
                            Log.i(TAG, "track");
                        }

                        @Override
                        public void onLoadingChanged(boolean isLoading) {
                            Log.i(TAG, "loading change: " + isLoading);
                        }

                        @Override
                        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                            if (playWhenReady) {
                                switch (playbackState) {
                                    case 1: //STATE_IDLE
                                        Log.i(TAG, "STATE_IDLE");
                                        break;
                                    case 2: //STATE_BUFFERING
                                        Log.i(TAG, "STATE_BUFFERING");
                                        break;
                                    case 3: //STATE_READY
                                        Log.i(TAG, "STATE_READY");
                                        break;
                                    case 4: //STATE_ENDED
                                        Log.i(TAG, "STATE_ENDED");
                                        if(cb!=null)
                                            cb.onSelected(STATE_ENDED);
                                        else
                                            Log.i(TAG, "cb is null");
                                        break;
                                }
                            }
                        }

                        @Override
                        public void onIsPlayingChanged(boolean isPlaying) {

                        }

                        @Override
                        public void onRepeatModeChanged(int repeatMode) {
                            Log.i(TAG, "repeatmode");
                        }

                        @Override
                        public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

                        }

                        @Override
                        public void onPlayerError(ExoPlaybackException error) {
                            Log.i(TAG, "playerror");
                        }

                        @Override
                        public void onPositionDiscontinuity(int reason) {
                            Log.i(TAG, "discontinue");
                        }


                        @Override
                        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                            Log.i(TAG, "playback");
                        }

                        @Override
                        public void onSeekProcessed() {

                        }
                    });
                    Log.i(TAG, "playing");
                }
            } else {
                Log.i(TAG, "player is null");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (sv != null) {
            sv.getPlayer().stop();
            Log.i(TAG, "player stop");
        }
    }
}
