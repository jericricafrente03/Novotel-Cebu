package com.ph.bittelasia.meshtv.tv.carlson.Movies.Model;


import android.content.Context;

import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.ViewHolder.ViewHolderLayout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.ResourceManager.MeshResourceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.Adapter.MeshTVAdapter;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.VH.MeshTVVHolder;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVOD;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ramil on 1/23/18.
 */
@ViewHolderLayout(layout = R.layout.carlson_grid_list_item)
public class CarlsonVodListAdapter extends MeshTVAdapter<MeshVOD> {

    private VelocityTracker mVelocityTracker = null;
    ViewGroup vg;


    public CarlsonVodListAdapter(Context context, GridView gv_view, int layoutResourceId, ArrayList<MeshVOD> data) {
        super(context, gv_view, layoutResourceId, data);
    }

    @Override
    public MeshTVVHolder setViewHolder() {
        return new CarlsonVodListViewHolder();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return super.areAllItemsEnabled();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void updateRow(MeshTVVHolder meshTVVHolder, final MeshVOD meshVod) {
        CarlsonVodListViewHolder vh=(CarlsonVodListViewHolder)meshTVVHolder;
        try
        {
           // MeshResourceManager.displayLiveImageFor(getContext(),vh.getIv_icon(),meshVod.getImg());
            Picasso.get().load(meshVod.getImg()).into(vh.getIv_icon());
//            vh.getIv_icon().setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId(meshVod.getImg())));
            vh.getTv_name().setText(meshVod.getTitle());
//            vg=(ViewGroup) vh.getV();
//            vg.setOnHoverListener(new View.OnHoverListener() {
//                @Override
//                public boolean onHover(View view, MotionEvent motionEvent) {
//                    int index = motionEvent.getActionIndex();
//                    int action = motionEvent.getActionMasked();
//                    int pointerId = motionEvent.getPointerId(index);
//                    switch(action) {
//                        case MotionEvent.ACTION_HOVER_ENTER:
//                            if(mVelocityTracker == null) {
//                                mVelocityTracker = VelocityTracker.obtain();
//                            }
//                            else {
//                                mVelocityTracker.clear();
//                            }
//                            mVelocityTracker.addMovement(motionEvent);
//                            return true;
//                        case MotionEvent.ACTION_HOVER_MOVE:
//                            mVelocityTracker.addMovement(motionEvent);
//                            mVelocityTracker.computeCurrentVelocity(1000);
//                            getGv_view().setSelection(getPosition(meshVod));
//                            view.setHovered(true);
//                            view.setSelected(true);
//                            return true;
//                        case MotionEvent.ACTION_HOVER_EXIT:
//                            view.setHovered(false);
//                            view.setSelected(false);
//                            notifyDataSetChanged();
//                            return true;
//                        case MotionEvent.ACTION_UP:
//                            return true;
//                        case MotionEvent.ACTION_CANCEL:
//                            mVelocityTracker.recycle();
//                            return true;
//                    }
//                    return false;
//                }
//            });

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
