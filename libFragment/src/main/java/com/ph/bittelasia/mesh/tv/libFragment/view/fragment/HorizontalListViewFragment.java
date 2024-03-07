package com.ph.bittelasia.mesh.tv.libFragment.view.fragment;

import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ph.bittelasia.mesh.tv.libFragment.control.adapter.RecyclerAdapter;
import com.ph.bittelasia.mesh.tv.libFragment.control.viewholder.RecycleViewHolder;

public abstract class HorizontalListViewFragment extends BasicFragment {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;


    @Override
    public void getView(View v) {
        recyclerView = v.findViewById(getRecyclerViewID());
    }

    public void updateDetails(){
        if(getContext()!=null) {
            if(getAdapter()!=null) {
                LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                getRecyclerView().setLayoutManager(mLayoutManager);
                getRecyclerView().setItemAnimator(new DefaultItemAnimator());
                getRecyclerView().setAdapter(getAdapter());
            }
        }
    }

    public void setAdapter(RecyclerAdapter adapter) {
        this.adapter = adapter;
    }

    public RecyclerAdapter<RecycleViewHolder> getAdapter() {
        return adapter;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public abstract int getRecyclerViewID();

}
