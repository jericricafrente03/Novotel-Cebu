package com.ph.bittelasia.mesh.tv.libFragment.view.fragment;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;

import com.ph.bittelasia.mesh.tv.libFragment.control.adapter.ListAdapter;
import com.ph.bittelasia.mesh.tv.libFragment.control.listener.OnListClickListener;

import java.util.HashMap;

public abstract class GridViewFragment extends BasicFragment {

    private OnListClickListener listClickListener;
    private GridView gridView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listClickListener = (OnListClickListener)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listClickListener = null;
    }

    @Override
    public void getView(View v) {
        gridView = v.findViewById(getGridViewID());
        updateDetails();
    }


    public void setGridView(GridView gridView) {
        this.gridView = gridView;
    }

    public GridView getGridView() {
        return gridView;
    }

    public void updateDetails()
    {
        if(getAdapter()!=null) {
            getGridView().setAdapter(getAdapter());
        }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listClickListener.onClicked(adapterView.getAdapter().getItem(i));
            }
        });
        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                listClickListener.onSelected(adapterView.getAdapter().getItem(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        gridView.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View view, MotionEvent motionEvent) {
                view.requestFocus();
                return false;
            }
        });
        getAdapter().notifyDataSetChanged();
    }

    public abstract int getGridViewID();
    public abstract ListAdapter getAdapter();

}
