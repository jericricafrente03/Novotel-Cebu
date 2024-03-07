package com.ph.bittelasia.meshtv.tv.carlson.Temp.Model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Manager.MeshTVPreferenceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Transaction.Model.MeshCartItem;
import com.ph.bittelasia.meshtv.tv.mtvlib.Transaction.Model.MeshTVCart;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by ramil on 1/3/18.
 */

public class CarlsonCartShopListAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private int layoutResourceId;
    Animation zoomIn;
    MeshArrayListCallBack cb;
    ItemFilter filter;
    ArrayList<MeshCartItem> filtered;
    ArrayList<MeshCartItem> orig;
    DecimalFormat df;


    public CarlsonCartShopListAdapter(Context context, int resource, ArrayList<MeshCartItem> objects,MeshArrayListCallBack cb) {
        this.layoutResourceId = resource;
        this.context = context;
        this.filtered = objects;
        this.orig = objects;
        this.cb=cb;
        zoomIn = AnimationUtils.loadAnimation(context, R.anim.zoom_in);
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
            viewHolder.tv_name     = (TextView) row.findViewById(R.id.tv_name);
            viewHolder.tv_price    = (TextView) row.findViewById(R.id.tv_price);
            viewHolder.et_quantity = (EditText) row.findViewById(R.id.et_quantity);
            viewHolder.et_total    = (EditText) row.findViewById(R.id.et_total);
            viewHolder.btn_add     = (Button) row.findViewById(R.id.btn_add);
            viewHolder.btn_minus   = (Button) row.findViewById(R.id.btn_minus);
            viewHolder.btn_remove  = (Button) row.findViewById(R.id.btn_remove);
            viewHolder.ll_button   = (LinearLayout) row.findViewById(R.id.ll_buttons);

            row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) row.getTag();
        }
        df = new DecimalFormat(MeshTVPreferenceManager.getHotelCurrency(context) + " ###,##0.00");
        viewHolder.tv_name.setText(orig.get(position).getItemName());
        viewHolder.tv_price.setText(orig.get(position).getItemPrice()>0?df.format(orig.get(position).getItemPrice())+"": " FREE");
        viewHolder.et_quantity.setText((orig.get(position).getQuantity()+""));
        viewHolder.et_total.setText((df.format(orig.get(position).getQuantity()* orig.get(position).getItemPrice())+""));
        viewHolder.btn_add.setTag(position);
        viewHolder.btn_minus.setTag(position);
        viewHolder.btn_remove.setTag(position);
        viewHolder.ll_button.setFocusable(true);

        viewHolder.ll_button.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 1; i < viewGroup.getChildCount(); i++) {
                    viewGroup.getChildAt(i).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View view, boolean b) {
                            checkImage(view,b,zoomIn);
                        }
                    });
                    viewGroup.getChildAt(i).setFocusable(true);
                    viewGroup.getChildAt(i).setClickable(true);
                }
                viewGroup.requestFocus();
            }
        });

        viewHolder.btn_add.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                checkImage(view,b,zoomIn);
            }
        });
        viewHolder.btn_minus.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                checkImage(view,b,zoomIn);
            }
        });
        viewHolder.btn_remove.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                checkImage(view,b,zoomIn);
            }
        });

        viewHolder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position =(Integer)view.getTag();
                int qty=orig.get(position).getQuantity();
                if(qty<99)
                {
                    orig.get(position).setQuantity(qty + 1);
                }
                cb.meshArrayList(orig);
            }
        });

        viewHolder.btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer)view.getTag();
                int qty= orig.get(position).getQuantity()-1;
                if(qty==0)
                {
                    MeshTVCart.remove(orig.get(position));
                    orig.remove(position);
                }
                else
                {
                    orig.get(position).setQuantity(orig.get(position).getQuantity() - 1);
                    Log.i("steward", "getQuantity: " + orig.get(position).getQuantity());
                }
                cb.meshArrayList(orig);
            }
        });

        viewHolder.btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    int position = (Integer) view.getTag();
                    MeshTVCart.remove(orig.get(position));
                    orig.remove(position);
                    cb.meshArrayList(orig);
                    Log.i("steward", "getQuantity: " + orig.get(position).getItemName());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        return row;
    }


    public class ViewHolder {
        TextView tv_name;
        TextView tv_price;
        EditText et_quantity;
        EditText et_total;
        Button   btn_add;
        Button   btn_minus;
        Button   btn_remove;
        LinearLayout ll_button;
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

    public void checkImage(final View view, Boolean b, Animation animation)
    {
        if(b)
        {
            view.startAnimation(animation);
        }
        else
        {
            view.clearAnimation();
        }

    }
}
