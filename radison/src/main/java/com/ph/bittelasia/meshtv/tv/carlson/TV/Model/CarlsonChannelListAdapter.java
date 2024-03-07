package com.ph.bittelasia.meshtv.tv.carlson.TV.Model;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.ViewHolder.ViewHolderLayout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.ResourceManager.MeshResourceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.Adapter.MeshTVAdapter;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.VH.MeshTVVHolder;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Channel.MeshChannel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Created by ramil on 2/7/18.
 */
@ViewHolderLayout(layout = R.layout.carlson_list_item_layout)
public class CarlsonChannelListAdapter extends MeshTVAdapter<MeshChannel> {


    public CarlsonChannelListAdapter(Context context, GridView gv_view, int layoutResourceId, ArrayList<MeshChannel> data) {
        super(context, gv_view, layoutResourceId, data);
    }

    @Override
    public MeshTVVHolder setViewHolder() {
        return new CarlsonChannelListViewHolder();
    }

    @Override
    public void updateRow(MeshTVVHolder meshTVVHolder, MeshChannel channel) {
        final CarlsonChannelListViewHolder vh=(CarlsonChannelListViewHolder)meshTVVHolder;
        // MeshResourceManager.displayLiveImageFor(getContext(), vh.getIv_list(),channel.getChannel_image());
        Picasso.get().load(channel.getChannel_image()).into(vh.getIv_list());
    }

}
