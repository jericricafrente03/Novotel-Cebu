package com.ph.bittelasia.meshtv.tv.carlson.Temp.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Transaction.Model.MeshCartItem;

import java.util.ArrayList;

/**
 * Created by ramil on 2/9/18.
 */

public class CarlsonViewCartListAdapter extends BaseAdapter implements Filterable {

    private Context context;
    private int layoutResourceId;
    ItemFilter filter;
    ArrayList<MeshCartItem> filtered;
    ArrayList<MeshCartItem> orig;


    public CarlsonViewCartListAdapter(Context context, int resource, ArrayList<MeshCartItem> objects) {
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

        ViewHolder viewHolder = null;
        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(layoutResourceId, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvApp = (TextView) row.findViewById(R.id.tv_category);

            row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) row.getTag();
        }
        viewHolder.tvApp.setText(orig.get(position).getItemName());

        return row;
    }


    public class ViewHolder {
        TextView tvApp;
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

            if (charSequence != null && charSequence.length() > 0) {
                charSequence = charSequence.toString().toUpperCase();
                ArrayList<MeshCartItem> filters = new ArrayList<>();
                for (int i = 0; i < filtered.size(); i++) {
                    if (filtered.get(i).getItemName().toUpperCase().contains(charSequence)) {
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
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            orig = (ArrayList<MeshCartItem>) results.values;
            notifyDataSetChanged();
        }
    }
}
