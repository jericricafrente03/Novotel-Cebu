package com.ph.bittelasia.meshtv.tv.carlson.FacilitiesV2.control;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.ph.bittelasia.mesh.tv.libFragment.control.adapter.RecyclerAdapter;
import com.ph.bittelasia.mesh.tv.libFragment.control.viewholder.RecycleViewHolder;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshListItemClickedListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Dining.MeshFoodCategory;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacilityCategory;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Shopping.MeshShoppingCategory;

import java.util.ArrayList;

public class CategoryListAdapter extends RecyclerAdapter<CategoryListAdapter.ViewHolder> {

    MeshListItemClickedListener clickedListener;

    public CategoryListAdapter(ArrayList<?> objects,MeshListItemClickedListener clickedListener) {
        super(objects);
        this.clickedListener = clickedListener;
    }

    @Override
    public int getItemLayout() {
        return R.layout.category_list_item;
    }

    @Override
    public ViewHolder holder(View v) {
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(getObjects()!=null && getObjects().size()>0)
        {
            Object o = getObjects().get(position);
            if(o instanceof MeshFacilityCategory) {
                MeshFacilityCategory category = (MeshFacilityCategory)o;
                holder.category.setText((category.getCategory_name()+""));
            }else if(o instanceof MeshFoodCategory){
                MeshFoodCategory category = (MeshFoodCategory)o;
                holder.category.setText((category.getCategory_name()+""));
            }else if(o instanceof MeshShoppingCategory){
                MeshShoppingCategory category = (MeshShoppingCategory)o;
                holder.category.setText((category.getCategory_name()+""));
            }

            if(position==getObjects().size()-1)
                holder.end.setVisibility(View.GONE);
            holder.category.setFocusable(true);
            holder.category.setClickable(true);
            holder.category.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(b) {
                        clickedListener.onClicked(o);
                        ((TextView) view).setTextColor(Color.RED);
                    }else {
                        ((TextView) view).setTextColor(Color.WHITE);
                    }
                }
            });
            if(position==0) {
                holder.category.requestFocus();
                clickedListener.onClicked(o);
            }
            holder.category.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.requestFocus();
                    clickedListener.onClicked(o);
                }
            });
        }
    }

    static class ViewHolder extends RecycleViewHolder {
        final TextView category;
        final View end;
        public ViewHolder(@NonNull View view) {
            super(view);
            category = view.findViewById(R.id.category);
            end  = view.findViewById(R.id.v_bar);
        }
    }



}
