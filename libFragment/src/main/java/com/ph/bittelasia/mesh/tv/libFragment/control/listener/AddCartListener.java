package com.ph.bittelasia.mesh.tv.libFragment.control.listener;

import com.ph.bittelasia.mesh.tv.libFragment.control.model.AddCart;

public interface AddCartListener {
    public abstract void onConfirmCart(AddCart cart,int qty);
}
