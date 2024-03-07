package com.ph.bittelasia.meshtv.tv.carlson.Movies.Model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVOD;

import java.util.ArrayList;

/**
 * Created by ramil on 1/3/18.
 */

public class CarlsonViewSearchListAdapter extends BaseAdapter implements Filterable {

    private Context context;
    private int layoutResourceId;
    ItemFilter filter;
    ArrayList<MeshVOD> filtered;
    ArrayList<MeshVOD> orig;


    public CarlsonViewSearchListAdapter(Context context, int resource, ArrayList<MeshVOD> objects) {
        this.layoutResourceId = resource;
        this.context = context;
        this.filtered = objects;
        this.orig = objects;
    }

    @Override
    public int getCount() {
        return orig.size();
    }

    @Override
    public Object getItem(int i) {
        return orig.get(i);
    }

    @Override
    public long getItemId(int i) {
        return orig.indexOf(getItem(i));
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        try {
            ViewHolder viewHolder = null;
            if (row == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                row = inflater.inflate(layoutResourceId, parent, false);

                viewHolder = new ViewHolder();
                viewHolder.tvApp = (TextView) row.findViewById(R.id.tv_category);
                viewHolder.tvPlay = (TextView) row.findViewById(R.id.tv_play);

                row.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) row.getTag();
            }
            viewHolder.tvApp.setText(orig.get(position).getTitle());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        Log.i("steward",orig.get(position).getTitle()+"");

        return row;
    }


    public class ViewHolder {
        TextView tvApp;
        TextView tvPlay;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new ItemFilter();
        }
        return filter;
    }

    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            try {
                if (charSequence != null && charSequence.length() > 0) {
                    charSequence = charSequence.toString().toUpperCase();
                    ArrayList<MeshVOD> filters = new ArrayList<>();
                    for (int i = 0; i < filtered.size(); i++) {
                        if (filtered.get(i).getTitle().toUpperCase().contains(charSequence)) {
                            filters.add(filtered.get(i));
                        } else {
                            results.count = filtered.size();
                            results.values = filtered;
                        }
                    }
                    results.count = filters.size();
                    results.values = filters;
                } else {
                    results.count = filtered.size();
                    results.values = filtered;
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            try {
                orig = (ArrayList<MeshVOD>) results.values;
                notifyDataSetChanged();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
