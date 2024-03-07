package com.ph.bittelasia.meshtv.tv.carlson.TV.Model;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Channel.MeshChannel;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.ResourceManager.MeshResourceManager;

import java.util.ArrayList;

public class CarlsonChannelListAdapterV2 extends BaseAdapter {


    private ArrayList<MeshChannel> data;
    private Context c;

    public CarlsonChannelListAdapterV2(Context c,ArrayList<MeshChannel> list) {
        this.data=list;
        this.c=c;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        try
        {
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (view == null)
            {
                view = inflater.inflate(R.layout.carlson_list_item_layout, null);
            }

            ImageView image = (ImageView) view.findViewById(R.id.iv_list);
            MeshResourceManager.displayLiveImageFor(c, image,data.get(i).getChannel_image());
            if(i==selectedItem)
            {
                Log.i("apostol","selected item: "+selectedItem);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return view;
    }

    private int selectedItem;

    public void setSelectedItem(int position) {
        selectedItem = position;
    }
}
