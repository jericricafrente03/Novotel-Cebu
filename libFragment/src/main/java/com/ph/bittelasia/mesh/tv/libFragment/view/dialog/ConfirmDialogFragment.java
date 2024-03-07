package com.ph.bittelasia.mesh.tv.libFragment.view.dialog;

import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ph.bittelasia.mesh.tv.libFragment.control.model.AddCart;
import com.ph.bittelasia.mesh.tv.libFragment.control.model.CartItem;
import com.ph.bittelasia.mesh.tv.libFragment.control.model.CartList;
import com.ph.bittelasia.mesh.tv.libFragment.view.fragment.CustomDialog;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ConfirmDialogFragment extends CustomDialog implements View.OnHoverListener, View.OnTouchListener{

    private LinearLayout         yesView;
    private LinearLayout         noView;
    private TextView             askView;
    private AddCart              addCart;
    private String               message;
    private int                  quantity;


    public ConfirmDialogFragment addCart(AddCart addCart,int qty,String message){
        setAddCart(addCart);
        setQuantity(qty);
        setMessage(message);
        return this;
    }


    @Override
    public boolean onHover(View view, MotionEvent motionEvent) {
        view.requestFocus();
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            view.performClick();
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            view.requestFocus();
        }
        return false;
    }

    @Override
    public void setIDs(View view) {
        yesView = view.findViewById(getYesViewID());
        noView = view.findViewById(getNoViewID());
        askView = view.findViewById(getAskViewID());
        getYesView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getAddCart()!=null)
                {
                    CartList.getInstance().getItems().add(
                            new CartItem.Builder()
                                    .quantity(getQuantity())
                                    .cart(getAddCart())
                                    .isBought(true)
                                    .buildCartItem());
                }
                assert getFragmentManager() != null;
                dismissAllDialogs(getFragmentManager());
            }
        });
        getNoView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert getFragmentManager() != null;
                dismissAllDialogs(getFragmentManager());
            }
        });
        getYesView().setClickable(true);
        getNoView().setClickable(true);
        getYesView().setOnTouchListener(this);
        getYesView().setOnHoverListener(this);
        getNoView().setOnTouchListener(this);
        getNoView().setOnHoverListener(this);
    }

    @Override
    public void setContent() {
       getAskView().setText(getMessage());
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAddCart(AddCart addCart) {
        this.addCart = addCart;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setYesView(LinearLayout yesView) {
        this.yesView = yesView;
    }

    public void setNoView(LinearLayout noView) {
        this.noView = noView;
    }

    public void setAskView(TextView askView) {
        this.askView = askView;
    }

    public String getMessage() {
        return message;
    }

    public AddCart getAddCart() {
        return addCart;
    }

    public int getQuantity() {
        return quantity;
    }

    public LinearLayout getYesView() {
        return yesView;
    }

    public LinearLayout getNoView() {
        return noView;
    }

    public TextView getAskView() {
        return askView;
    }

    public abstract int getYesViewID();
    public abstract int getNoViewID();
    public abstract int getAskViewID();

}
