package com.ph.bittelasia.mesh.tv.libFragment.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;


import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


import java.util.Objects;

public abstract class ExoplayerFragment extends BasicFragment {

    private SimpleExoPlayerView exoPlayerView;
    private SimpleExoPlayer exoPlayer;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void getView(View v) {
        try {
            exoPlayerView = v.findViewById(exoPlayerID());
            BandwidthMeter meter = new DefaultBandwidthMeter();
            TrackSelection.Factory trackSelectionFactory = new com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection.Factory(meter);
            TrackSelector selector = new DefaultTrackSelector(trackSelectionFactory);
            exoPlayer = ExoPlayerFactory.newSimpleInstance(Objects.requireNonNull(getContext()), selector);
            exoPlayer.setPlayWhenReady(true);
            exoPlayerView.setPlayer(this.exoPlayer);
            exoPlayerView.setUseController(hasControls());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        this.stopExo();
    }

    public void setUrl(String url) {
        if (this.exoPlayerView != null && this.exoPlayer != null) {
            play(this.getActivity(), this.exoPlayer, "MARS", url);
        } else {
            Log.e("Exoplayer", "Exoplayer: Failed to play");
        }

    }

    static  void play(Context context, ExoPlayer player, String useragent, String uri) {
        if (player != null) {
            DefaultBandwidthMeter bandwidthMeter;
            bandwidthMeter = new DefaultBandwidthMeter();
            DataSource.Factory datasourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, useragent), bandwidthMeter);
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource videosource = new ExtractorMediaSource(Uri.parse(uri), datasourceFactory, extractorsFactory, (Handler)null, (ExtractorMediaSource.EventListener)null);
            player.prepare(videosource);
            player.setPlayWhenReady(true);
        } else {
            Log.e("Exoplayer", "play() - Player not found");
        }

    }

    final void stopExo() {
        if (this.exoPlayer != null) {
            this.exoPlayer.stop();
        }

    }


    abstract int exoPlayerID();
    abstract boolean hasControls();
}
