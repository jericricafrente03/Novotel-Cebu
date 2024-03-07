package com.ph.bittelasia.meshtv.tv.carlson.TV.Control;


import java.util.ArrayList;

public class FixedArrayList extends ArrayList {
    int fixedSize = Integer.MAX_VALUE;

    public FixedArrayList(){}

    public FixedArrayList(int fixedSize){
        this.fixedSize = fixedSize;
    }

    @SuppressWarnings("All")
    public void addItem(Object object){
        if(size() < fixedSize){
            add(object);
        } else{
            remove(0);
            add(object);
        }
    }
}