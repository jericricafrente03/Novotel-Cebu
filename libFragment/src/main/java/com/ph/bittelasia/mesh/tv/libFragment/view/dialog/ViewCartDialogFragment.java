package com.ph.bittelasia.mesh.tv.libFragment.view.dialog;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ph.bittelasia.mesh.tv.libFragment.R;
import com.ph.bittelasia.mesh.tv.libFragment.control.adapter.ListAdapter;
import com.ph.bittelasia.mesh.tv.libFragment.control.cart.CartCompute;
import com.ph.bittelasia.mesh.tv.libFragment.control.model.BillItem;
import com.ph.bittelasia.mesh.tv.libFragment.control.model.BillList;
import com.ph.bittelasia.mesh.tv.libFragment.control.model.CartItem;
import com.ph.bittelasia.mesh.tv.libFragment.control.model.CartList;
import com.ph.bittelasia.mesh.tv.libFragment.view.fragment.CustomDialog;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public abstract class ViewCartDialogFragment extends CustomDialog implements View.OnHoverListener, View.OnFocusChangeListener {

    private ListView  cartView;
    private TextView  totalView;
    private TextView  allTotalView;
    private Button    confirmView;
    private Button    backView;
    private Animation zoomIn;
    private HashMap<Class,ArrayList<CartItem>> classMap;
    private ArrayList<CartItem> classCartItems;
    private ArrayList<CartItem> items;
    private Class     aClass;



    public ViewCartDialogFragment get(ArrayList<CartItem> items, Class aClass){
        setItems(items);
        setaClass(aClass);
        return this;
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        checkImage(view,b,zoomIn);
    }

    @Override
    public boolean onHover(View view, MotionEvent motionEvent) {
        view.requestFocus();
        return false;
    }

    @Override
    public void setIDs(View view) {
        cartView = view.findViewById(getCartViewID());
        totalView = view.findViewById(getTotalViewID());
        allTotalView = view.findViewById(getAllTotalViewID());
        confirmView = view.findViewById(getConfirmViewID());
        backView = view.findViewById(getBackViewID());
    }

    @Override
    public void setContent() {
        classCartItems = new ArrayList<>();
        classMap = new HashMap<>();
        if(getItems()!=null){

            for(int x =0;x<getItems().size();x++)
            {
                if(getaClass()!=null)
                {
                    if(getaClass()==getItems().get(x).getAddCart().getaClass())
                    {
                        classCartItems.add(getItems().get(x));
                    }
                }
            }
         classMap.put(getaClass(),classCartItems);
        }
        zoomIn = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
        getBackView().setOnFocusChangeListener(this);
        getBackView().setOnHoverListener(this);
        getBackView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        getConfirmView().setOnFocusChangeListener(this);
        getCartView().setOnHoverListener(this);
        if (getItems().size() > 0) {
            getConfirmView().setOnClickListener(new View.OnClickListener() {
                @SuppressWarnings("rawtypes")
                @Override
                public void onClick(View view) {
                        try {
                            if (getActivity() != null)
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Iterator it = classMap.entrySet().iterator();
                                        while (it.hasNext()) {
                                            HashMap.Entry pair = (HashMap.Entry) it.next();
                                            if (pair.getValue() instanceof CartItem) {
                                                CartItem cart =(CartItem)pair.getValue();
                                                BillList.getInstance().getItems().add((new BillItem.Builder()
                                                   .id(cart.getId())
                                                   .name(cart.getAddCart().getName())
                                                   .price(cart.getAddCart().getPrice()))
                                                   .qty(cart.getQuantity())
                                                   .total((double)(cart.getAddCart().getPrice()) * cart.getQuantity())
                                                   .buildBillItem());
                                            }
                                            it.remove();
                                        }
                                        for (Iterator<CartItem> iterator = CartList.getInstance().getItems().iterator(); iterator.hasNext(); ) {
                                            CartItem item = iterator.next();
                                            if (getaClass() != null) {
                                                if (getaClass() == item.getAddCart().getaClass()) {
                                                    iterator.remove();
                                                }
                                            }
                                        }
                                        classCartItems.clear();
                                        getListAdapter().getObjects().clear();
                                        getListAdapter().notifyDataSetChanged();
                                        getAllTotalView().setText((""));
                                        Toast.makeText(getContext(), "All items have been checked out", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        }catch (Exception e){
                            e.printStackTrace();

                        }
                }
            });
            DecimalFormat df = new DecimalFormat(getItems().get(0).getAddCart().getPriceFormat());
            getAllTotalView().setText((df.format(CartCompute.totalPrice(getItems()))));
            getCartView().setAdapter(getListAdapter());
        }
    }

    public void checkImage(final View view, Boolean b, Animation animation)
    {
        try
        {
            if (b)
            {
                view.startAnimation(animation);
            }
            else
            {
                view.clearAnimation();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setItems(ArrayList<CartItem> items) {
        this.items = items;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public void setCartView(ListView cartView) {
        this.cartView = cartView;
    }

    public ListView getCartView() {
        return cartView;
    }

    public void setTotalView(TextView totalView) {
        this.totalView = totalView;
    }

    public TextView getTotalView() {
        return totalView;
    }

    public void setAllTotalView(TextView allTotalView) {
        this.allTotalView = allTotalView;
    }

    public TextView getAllTotalView() {
        return allTotalView;
    }

    public void setConfirmView(Button confirmView) {
        this.confirmView = confirmView;
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public Class getaClass() {
        return aClass;
    }

    public Button getConfirmView() {
        return confirmView;
    }

    public void setBackView(Button backView) {
        this.backView = backView;
    }

    public Button getBackView() {
        return backView;
    }


    public abstract ListAdapter getListAdapter();
    public abstract int getCartViewID();
    public abstract int getTotalViewID();
    public abstract int getAllTotalViewID();
    public abstract int getConfirmViewID();
    public abstract int getBackViewID();

}
