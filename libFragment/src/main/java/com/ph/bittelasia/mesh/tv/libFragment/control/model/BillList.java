package com.ph.bittelasia.mesh.tv.libFragment.control.model;

import java.util.ArrayList;

public class BillList {

    private ArrayList<BillItem> items = new ArrayList<>();
    private static BillList instance=null;

    public static BillList getInstance(){
        if(instance==null){
            instance = new BillList();
        }
        return instance;
    }

    public ArrayList<BillItem> getItems() {
        return items;
    }
}
