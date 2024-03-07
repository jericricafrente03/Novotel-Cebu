package com.ph.bittelasia.meshtv.tv.carlson.Shopping.Model;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
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
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Shopping.MeshShoppingItem;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ramil on 1/23/18.
 */
@ViewHolderLayout(layout = R.layout.carlson_grid_list_item)
public class CarlsonShoppingListAdapter extends MeshTVAdapter<MeshShoppingItem> {

    private VelocityTracker mVelocityTracker = null;


    public CarlsonShoppingListAdapter(Context context, GridView gv_view, int layoutResourceId, ArrayList<MeshShoppingItem> data) {
        super(context, gv_view, layoutResourceId, data);
    }

    @Override
    public MeshTVVHolder setViewHolder() {
        return new CarlsonShoppingListViewHolder();
    }

    @Override
    public void updateRow(MeshTVVHolder meshTVVHolder, final MeshShoppingItem meshShoppingItem) {
        CarlsonShoppingListViewHolder vh=(CarlsonShoppingListViewHolder)meshTVVHolder;
        try
        {
          //  MeshResourceManager.displayLiveImageFor(getContext(),vh.getIv_icon(),meshShoppingItem.getImg_uri());
            Picasso.get().load(meshShoppingItem.getImg_uri()).into(vh.getIv_icon());
//            vh.getIv_icon().setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId(meshShoppingItem.getImg_uri())));
            vh.getTv_name().setText(meshShoppingItem.getItem_name());
//            ViewGroup vg=(ViewGroup) vh.getV();
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
//                            Log.i("steward","postion"+getPosition(meshShoppingItem));
//                            getGv_view().setSelection(getPosition(meshShoppingItem));
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
