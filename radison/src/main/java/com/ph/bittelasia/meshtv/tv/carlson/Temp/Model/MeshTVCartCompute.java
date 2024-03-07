package com.ph.bittelasia.meshtv.tv.carlson.Temp.Model;


import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.IntRange;

import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Manager.MeshTVPreferenceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Transaction.Model.MeshCartItem;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
/**
 * Created by ramil on 1/24/18.
 */
public class MeshTVCartCompute {

    public static double   totalPrice;



    public static void addQuantity(EditText quantity,TextView total,double price){
        try
        {
            DecimalFormat df;
            int qty=0;
            if (quantity.getText().length() < 1) {
                quantity.setText("0");
            } else {
                qty = Integer.valueOf(quantity.getText().toString());
                if (qty < 99) {
                    qty = qty + 1;
                    quantity.setText((qty + ""));
                }
                Log.i("steward",getQty(qty)+"");
            }
            totalPrice = qty * price;
            df= new DecimalFormat(MeshTVPreferenceManager.getHotelCurrency(quantity.getContext()) + " ###,##0.00");
            total.setText(df.format(totalPrice));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void reduceQuantity(EditText quantity,TextView total,double price){
        try
        {
            DecimalFormat df;
            int qty=0;
            if (quantity.getText().length() < 1) {
                quantity.setText(("0"));
            } else {
                qty = Integer.valueOf(quantity.getText().toString());
                if (qty >= 1) {
                    qty--;
                    quantity.setText((qty + ""));
                } else {
                    quantity.setText("0");
                }
                Log.i("steward",getQty(qty)+"");
            }
            totalPrice = qty * price;
            df= new DecimalFormat(MeshTVPreferenceManager.getHotelCurrency(quantity.getContext()) + " ###,##0.00");
            total.setText(df.format(totalPrice));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static  double totalPrice(ArrayList<MeshCartItem> cartList)
    {
        double total=0;
        for(MeshCartItem cartItem: cartList)
        {
            total=total+(cartItem.getQuantity() * cartItem.getItemPrice());
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
