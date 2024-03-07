package com.ph.bittelasia.meshtv.tv.carlson.Airmedia.Model;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.AirMediaPlatform;


import java.util.ArrayList;

/**
 * Created by ramil on 12/29/17.
 */

public class CarlsonAirMediaGridAdapter extends BaseAdapter {
    Context context;
    ArrayList<AirMediaPlatform> list;

    private VelocityTracker mVelocityTracker = null;

    public CarlsonAirMediaGridAdapter(Context c, ArrayList<AirMediaPlatform> list)
    {
        this.context=c;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        try
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (view == null)
            {
                view = inflater.inflate(R.layout.carlson_airmedia_grid_item, null);
            }

            TextView imageText = (TextView) view.findViewById(R.id.tv_platform);
            ImageView image = (ImageView) view.findViewById(R.id.iv_platform);

            imageText.setText(list.get(i).getName());
            if(i==0)
            {
                image.setImageDrawable(context.getResources().getDrawable(R.drawable.and));
            }
            else if(i==1)
            {
                image.setImageDrawable(context.getResources().getDrawable(R.drawable.ios));
            }
            else if(i==2)
            {
                image.setImageDrawable(context.getResources().getDrawable(R.drawable.windows));
            }
            view.setOnHoverListener(new View.OnHoverListener()
            {
                @Override
                public boolean onHover(View view, MotionEvent motionEvent) {
                    int index = motionEvent.getActionIndex();
                    int action = motionEvent.getActionMasked();
                    int pointerId = motionEvent.getPointerId(index);
                    switch(action)
                    {
                        case MotionEvent.ACTION_HOVER_ENTER:
                            if(mVelocityTracker == null)
                            {
                                mVelocityTracker = VelocityTracker.obtain();
                            }
                            else
                                {
                                mVelocityTracker.clear();
                            }
                            mVelocityTracker.addMovement(motionEvent);
                            return true;
                        case MotionEvent.ACTION_HOVER_MOVE:
                            mVelocityTracker.addMovement(motionEvent);
                            mVelocityTracker.computeCurrentVelocity(1000);
                            view.setHovered(true);
                            view.setSelected(true);
                            return true;
                        case MotionEvent.ACTION_HOVER_EXIT:
                            view.setHovered(false);
                            view.setSelected(false);
                            notifyDataSetChanged();
                            return true;
                        case MotionEvent.ACTION_UP:
                            return true;
                        case MotionEvent.ACTION_CANCEL:
                            mVelocityTracker.recycle();
                            return true;
                    }
                    return false;
                }
            });
        }
        catch (Exception e)
        {
            Log.i("steward","adapter");
            e.printStackTrace();
        }
        return view;
    }
}
