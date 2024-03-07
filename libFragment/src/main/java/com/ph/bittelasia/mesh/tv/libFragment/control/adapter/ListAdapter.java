package com.ph.bittelasia.mesh.tv.libFragment.control.adapter;

import android.view.View;
import android.widget.BaseAdapter;


import com.ph.bittelasia.mesh.tv.libFragment.control.listener.OnListClickListener;

import java.util.ArrayList;
import java.util.Collections;

public abstract class ListAdapter extends BaseAdapter {


    //===========================================Variable===========================================

    //------------------------------------------Instance-------------------------------------------
    private OnListClickListener clickedListener;
    private ArrayList<?> objects;
    private ArrayList<?> filteredObjects;
    private View parent;
    private Object object;
    private  int                                selectedItem;
    //----------------------------------------------------------------------------------------------
    //==============================================================================================


    //=========================================Constructor==========================================
    //----------------------------------------------------------------------------------------------
    public ListAdapter() {
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================



    //==========================================BaseAdapter=========================================
    //----------------------------------------------------------------------------------------------
    @Override
    public int getCount() {
        return getObjects().size();
    }

    @Override
    public Object getItem(int i) {
        return getObjects().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================



    //===========================================Methods============================================

    //-------------------------------------------Getter---------------------------------------------

    public Object getObject() {
        return object;
    }

    public OnListClickListener getClickedListener() {
        return clickedListener;
    }

    public ArrayList<?> getObjects() {
        return objects;
    }

    private ArrayList<?> getFilteredObjects() {
        return filteredObjects;
    }


    public int getSelectedItem() {
        return selectedItem;
    }

    public View getParent() {
        return parent;
    }
   //----------------------------------------------------------------------------------------------

    //-------------------------------------------Setter---------------------------------------------

    public void setParent(View parent) {
        this.parent = parent;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void setClickedListener(OnListClickListener clickedListener) {
        this.clickedListener = clickedListener;
    }
    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void setObjects(ArrayList<?> objects, boolean performSort) {
        try {
            if(performSort)
            {
                this.objects = sortObjects(objects);
                this.filteredObjects = sortObjects(objects);
            }
            else
            {
                this.objects = objects;
                this.filteredObjects = objects;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("rawtypes")
    public ListAdapter sort(ArrayList list) throws RuntimeException{
        if(list==null)
            throw new RuntimeException("ListAdapter:  list must not be null");
        Collections.sort(list);
        setObjects(list, true);
        return this;
    }
    //----------------------------------------------------------------------------------------------

    //-------------------------------------Filter Lists---------------------------------------------

    /**
     * Method to Filter Objects based on attributes assigned
     * @param field attribute of Object
     * @param performSort is boolean, if you want to perform sorting of objects
     * @param <T> is the Object to assign when performing filter
     */
    @SuppressWarnings("unchecked")
    public <T> void perFormFiltering(CharSequence field, boolean performSort) {
        try
        {
            ArrayList<T> filters = new ArrayList<>();

            if (field != null && field.length() > 0) {
                field = field.toString().toLowerCase();
                for (int i = 0; i < getFilteredObjects().size(); i++) {
                    T object = ((T) getFilteredObjects().get(i));
                    filterObjects(filters, object, field);
                }
            } else {
                filters = ((ArrayList<T>) getFilteredObjects());
            }
            setObjects(filters,performSort);
            this.notifyDataSetChanged();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method to Sort List by Fields
     * @param list is the Lists of the Objects
     */
    @SuppressWarnings("unchecked")
    public void performSort(ArrayList list)
    {
        try
        {
            setObjects(list,true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //===================================Abstract Methods===========================================
    //----------------------------------------------------------------------------------------------

    /**
     * Method to filter the  of Objects
     * @param filters is the List of Objects
     * @param object an Object to Assign in filtering the ArrayList of objects
     * @param charSequence is the Sequence of character
     * @param <T> is any type of Object
     */
    public abstract <T> void filterObjects(ArrayList<T> filters, Object object, CharSequence charSequence);
    //----------------------------------------------------------------------------------------------
    /**
     * Method to sort object
     * @param objects is the List of Objects
     * @param <T> is any type of objects
     * @return
     */
    public abstract <T> ArrayList sortObjects(ArrayList<T> objects);
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
}
