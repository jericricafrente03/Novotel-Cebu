package com.ph.bittelasia.mesh.tv.libFragment.control.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ph.bittelasia.mesh.tv.libFragment.control.viewholder.RecycleViewHolder;

import java.util.ArrayList;

public  abstract class RecyclerAdapter<V extends RecycleViewHolder> extends RecyclerView.Adapter<V> {

    private ArrayList<?> objects;

    public RecyclerAdapter(ArrayList<?> objects){
        this.objects=objects;
    }

    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(getItemLayout(), parent, false);
        return holder(itemView);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public ArrayList<?> getObjects() {
        return objects;
    }

    public abstract int getItemLayout();
    public abstract V holder(View v);
}
