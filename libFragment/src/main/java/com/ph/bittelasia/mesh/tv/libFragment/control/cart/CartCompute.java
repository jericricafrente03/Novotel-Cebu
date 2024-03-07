package com.ph.bittelasia.mesh.tv.libFragment.control.cart;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.IntRange;

import com.ph.bittelasia.mesh.tv.libFragment.control.model.CartItem;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartCompute {

    public static double   totalPrice;


    public static void addQuantity(EditText quantity, TextView total, double price,DecimalFormat priceFormat){
        try
        {
            int qty=0;
            if (quantity.getText().length() < 1) {
                quantity.setText("0");
            } else {
                qty = Integer.parseInt(quantity.getText().toString());
                if (qty < 99) {
                    qty = qty + 1;
                    quantity.setText((qty + ""));
                }
                Log.i("steward",getQty(qty)+"");
            }
            totalPrice = qty * price;
            total.setText(priceFormat.format(totalPrice));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void reduceQuantity(EditText quantity,TextView total,double price,DecimalFormat priceFormat){
        try
        {
            int qty=0;
            if (quantity.getText().length() < 1) {
                quantity.setText(("0"));
            } else {
                qty = Integer.parseInt(quantity.getText().toString());
                if (qty >= 1) {
                    qty--;
                    quantity.setText((qty + ""));
                } else {
                    quantity.setText("0");
                }
                Log.i("steward",getQty(qty)+"");
            }
            totalPrice = qty * price;
            total.setText(priceFormat.format(totalPrice));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static  double totalPrice(ArrayList<CartItem> cartList)
    {
        double total=0;
        for(CartItem cartItem: cartList)
        {
            total=total+(cartItem.getQuantity() * cartItem.getAddCart().getPrice());
        }
        return total;
    }


    public double totalPrice()
    {
        return  totalPrice;
    }

    public static int getQty(@IntRange(from=0,to=99) int qty)
    {
        return qty;
    }
}
