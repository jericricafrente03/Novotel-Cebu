package com.ph.bittelasia.mesh.tv.libFragment.control.model;

import java.util.ArrayList;

public class CartList {

    private ArrayList<CartItem> items = new ArrayList<>();
    private static CartList instance=null;

    public static CartList getInstance(){
        if(instance==null){
            instance = new CartList();
        }
        return instance;
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }
}
