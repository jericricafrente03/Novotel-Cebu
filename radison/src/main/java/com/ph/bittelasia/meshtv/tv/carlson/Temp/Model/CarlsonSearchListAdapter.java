package com.ph.bittelasia.meshtv.tv.carlson.Temp.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextClock;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.Util.MeshTVTimeManager;

import java.util.ArrayList;

/**
 * Created by ramil on 1/29/18.
 */

public class CarlsonSearchListAdapter extends BaseAdapter implements Filterable {

    private Context   context;
    private int       layoutResourceId;
    ItemFilter        filter;
    ArrayList<Items>  filtered;
    ArrayList<Items>  orig;


    public CarlsonSearchListAdapter(Context context, int resource, ArrayList<Items> objects) {
        this.layoutResourceId = resource;
        this.context = context;
        this.filtered=objects;
        this.orig=objects;
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
                viewHolder.tvDate = (TextClock)row.findViewById(R.id.tv_name);

                row.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) row.getTag();
            }
            if (orig.get(position).getCity() != null && orig.get(position).getCountry() != null)
            {
                viewHolder.tvApp.setText((orig.get(position).getCity() + ", " + orig.get(position).getCountry()));
            }
            else if(orig.get(position).getTitle() !=null)
            {
                viewHolder.tvApp.setText(orig.get(position).getTitle());
            }
            else if(orig.get(position).getCountry()!=null && orig.get(position).getCode()!=null)
            {
                viewHolder.tvApp.setText(orig.get(position).getCountry());
                viewHolder.tvDate.setTimeZone(MeshTVTimeManager.getTimeZoneName(orig.get(position).getCode()));
                viewHolder.tvDate.setVisibility(View.VISIBLE);
            }
            else if(orig.get(position).getStatus() !=null)
            {
                viewHolder.tvApp.setText((orig.get(position).getNumber()+"\t\t"+
                                          orig.get(position).getCarrier()+"\t\t"+
                                          orig.get(position).getCountry()+"\t\t"+
                                          orig.get(position).getStatus()+"("+
                                          orig.get(position).getMin()));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return row;
    }


    public class ViewHolder
    {
        TextView  tvApp;
        TextClock tvDate;
    }

    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new ItemFilter();
        }
        return filter;
    }

    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results=new FilterResults();

            if(charSequence !=null && charSequence.length() > 0)
            {
                charSequence=charSequence.toString().toUpperCase();
                ArrayList<Items> filters=new ArrayList<>();
                for (int i = 0; i < filtered.size(); i++)
                {
                    if(filtered.get(i).getTitle()!=null)
                    {
                        if (filtered.get(i).getTitle().toUpperCase().contains(charSequence))
                        {
                            filters.add(filtered.get(i));
                        }
                    }
                    if(filtered.get(i).getCity()!=null)
                    {
                        if (filtered.get(i).getCity().toUpperCase().contains(charSequence))
                        {
                            filters.add(filtered.get(i));
                        }
                    }
                    if(filtered.get(i).getCountry()!=null)
                    {
                        if (filtered.get(i).getCountry().toUpperCase().contains(charSequence))
                        {
                            filters.add(filtered.get(i));
                        }
                    }
                    if(!(filtered.get(i).getStatus()==null || filtered.get(i).getNumber()==null || filtered.get(i).getMin()==null))
                    {
                        if (filtered.get(i).getStatus().toUpperCase().contains(charSequence))
                        {
                            filters.add(filtered.get(i));
                        }
                    }
                    else
                    {
                        results.count = filtered.size();
                        results.values =filtered;
                    }
                }
                results.count=filters.size();
                results.values=filters;
            }
            else
            {
                results.count=filtered.size();
                results.values=filtered;
            }
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results)
        {
            orig = (ArrayList<Items>) results.values;
            notifyDataSetChanged();
        }

    }
}