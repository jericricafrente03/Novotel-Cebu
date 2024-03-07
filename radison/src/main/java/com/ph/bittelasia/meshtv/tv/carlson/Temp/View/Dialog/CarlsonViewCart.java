package com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog;


import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.CarlsonCartShopListAdapter;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshArrayListCallBack;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshTVCartCompute;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Toast.MeshTVToast;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Constant.AppDataSource;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshCheckOutListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Manager.MeshTVPreferenceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.App.MeshTVApp;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Concierge.MeshConciergeRequestItem;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Concierge.MeshConciergeRequestService;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Dining.MeshFood;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacility;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Shopping.MeshShoppingItem;
import com.ph.bittelasia.meshtv.tv.mtvlib.Transaction.Control.MeshBittelCheckOut;
import com.ph.bittelasia.meshtv.tv.mtvlib.Transaction.Model.MeshCartItem;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by ramil on 12/11/17.
 */

public class CarlsonViewCart extends MeshTVDialog implements MeshArrayListCallBack<MeshCartItem> {

    ListView    lv_cart;
    TextView    tv_total;
    TextView    tv_all_total;
    Button      btn_confirm;
    Button      btn_back;


    Animation zoomIn;


    double percentageWidth;
    double percentageHeight;
    String class_name;

    ArrayList<MeshCartItem> cartList;
    CarlsonCartShopListAdapter adapter;



    public static CarlsonViewCart v;


    public static CarlsonViewCart dialog(ArrayList<MeshCartItem> cartlist, double percentageWidth, double percentageHeight) {
        v = new CarlsonViewCart();
        v.cartList = cartlist;
        v.percentageWidth = percentageWidth;
        v.percentageHeight = percentageHeight;
        return v;
    }
    final View.OnHoverListener hover=new View.OnHoverListener() {
        @Override
        public boolean onHover(View view, MotionEvent motionEvent) {
            view.requestFocus();
            return true;
        }
    };

    final View.OnFocusChangeListener focus=new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            checkImage(view,b,zoomIn);
        }
    };

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
    final View.OnClickListener click=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.btn_checkout:
                    if(cartList.size()>0)
                    {
                        if (class_name.equals(MeshShoppingItem.class.getSimpleName())) {
                            MeshBittelCheckOut.checkout(new MeshCheckOutListener() {
                                @Override
                                public void onFail(String s) {
                                    Toast.makeText(getContext(), "check out failed", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onSuccess(String s) {
                                    MeshTVToast.makeText(getActivity(), R.layout.toast, "All items have been checked out!", Toast.LENGTH_LONG).show();
                                    cartList.clear();
                                    DecimalFormat df = new DecimalFormat(MeshTVPreferenceManager.getHotelCurrency(getActivity()) + " ###,##0.00");
                                    tv_all_total.setText((df.format(MeshTVCartCompute.totalPrice(cartList))));
                                    adapter.notifyDataSetChanged();
                                    dismiss();
                                }
                            }, MeshTVApp.get().getDataSourceSetting() != AppDataSource.SERVER, MeshShoppingItem.class);
                        } else if (class_name.equals(MeshFood.class.getSimpleName())) {
                            MeshBittelCheckOut.checkout(new MeshCheckOutListener() {
                                @Override
                                public void onFail(String s) {
                                    Toast.makeText(getContext(), "check out failed", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onSuccess(String s) {
                                    MeshTVToast.makeText(getActivity(), R.layout.toast, "All items have been checked out!", Toast.LENGTH_LONG).show();
                                    cartList.clear();
                                    DecimalFormat df = new DecimalFormat(MeshTVPreferenceManager.getHotelCurrency(getActivity()) + " ###,##0.00");
                                    tv_all_total.setText((df.format(MeshTVCartCompute.totalPrice(cartList))));
                                    adapter.notifyDataSetChanged();
                                    dismiss();
                                }
                            }, MeshTVApp.get().getDataSourceSetting() != AppDataSource.SERVER, MeshFood.class);
                        } else if (class_name.equals(MeshFacility.class.getSimpleName())) {
                            MeshBittelCheckOut.checkout(new MeshCheckOutListener() {
                                @Override
                                public void onFail(String s) {
                                    Toast.makeText(getContext(), "check out failed", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onSuccess(String s) {
                                    MeshTVToast.makeText(getActivity(), R.layout.toast, "All items have been checked out!", Toast.LENGTH_LONG).show();
                                    cartList.clear();
                                    DecimalFormat df = new DecimalFormat(MeshTVPreferenceManager.getHotelCurrency(getActivity()) + " ###,##0.00");
                                    tv_all_total.setText((df.format(MeshTVCartCompute.totalPrice(cartList))));
                                    adapter.notifyDataSetChanged();
                                    dismiss();
                                }
                            }, MeshTVApp.get().getDataSourceSetting() != AppDataSource.SERVER, MeshFacility.class);
                        } else if (class_name.equals(MeshConciergeRequestItem.class.getSimpleName())) {
                            MeshBittelCheckOut.checkout(new MeshCheckOutListener() {
                                @Override
                                public void onFail(String s) {
                                    Toast.makeText(getContext(), "check out failed", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onSuccess(String s) {
                                    MeshTVToast.makeText(getActivity(), R.layout.toast, "All items have been checked out!", Toast.LENGTH_LONG).show();
                                    cartList.clear();
                                    DecimalFormat df = new DecimalFormat(MeshTVPreferenceManager.getHotelCurrency(getActivity()) + " ###,##0.00");
                                    tv_all_total.setText((df.format(MeshTVCartCompute.totalPrice(cartList))));
                                    adapter.notifyDataSetChanged();
                                    dismiss();
                                }
                            }, MeshTVApp.get().getDataSourceSetting() != AppDataSource.SERVER, MeshConciergeRequestItem.class);
                        } else if (class_name.equals(MeshConciergeRequestService.class.getSimpleName())) {
                            MeshBittelCheckOut.checkout(new MeshCheckOutListener() {
                                @Override
                                public void onFail(String s) {
                                    Toast.makeText(getContext(), "check out failed", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onSuccess(String s) {
                                    MeshTVToast.makeText(getActivity(), R.layout.toast, "All items have been checked out!", Toast.LENGTH_LONG).show();
                                    cartList.clear();
                                    DecimalFormat df = new DecimalFormat(MeshTVPreferenceManager.getHotelCurrency(getActivity()) + " ###,##0.00");
                                    tv_all_total.setText((df.format(MeshTVCartCompute.totalPrice(cartList))));
                                    adapter.notifyDataSetChanged();
                                    dismiss();
                                }
                            }, MeshTVApp.get().getDataSourceSetting() != AppDataSource.SERVER, MeshConciergeRequestService.class);
                        }
                    }
                    else
                    {
                        MeshTVToast.makeText(getActivity(),R.layout.toast2,"Invalid", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btn_back:
                    dismiss();
                    break;
            }
        }
    };

    @Override
    public void setIDs(View view) {
        lv_cart       = (ListView) view.findViewById(R.id.lv_cart);
        tv_total      = (TextView) view.findViewById(R.id.tv_total);
        tv_all_total  = (TextView) view.findViewById(R.id.tv_all_total);
        btn_confirm = (Button) view.findViewById(R.id.btn_checkout);
        btn_back      = (Button) view.findViewById(R.id.btn_back);
    }

    @Override
    public void setContent() {
        try {
            zoomIn = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
            btn_back.setOnFocusChangeListener(focus);
            btn_back.setOnHoverListener(hover);
            btn_back.setOnClickListener(click);
            btn_confirm.setOnFocusChangeListener(focus);
            btn_confirm.setOnHoverListener(hover);
            if (cartList.size() > 0) {
                btn_confirm.setOnClickListener(click);
                DecimalFormat df = new DecimalFormat(MeshTVPreferenceManager.getHotelCurrency(getActivity()) + " ###,##0.00");
                tv_all_total.setText((df.format(MeshTVCartCompute.totalPrice(cartList))));
                adapter = new CarlsonCartShopListAdapter(getContext(), R.layout.carlson_cart_item, cartList,this);
                lv_cart.setAdapter(adapter);
                class_name=cartList.get(0).getItemClass().getSimpleName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int setLayout() {
        return R.layout.carlson_viewcart;
    }

    @Override
    public int setConstraintLayout() {
        return R.id.cl_layout;
    }

    @Override
    public double setPercentageWidth() {
        return percentageWidth;
    }

    @Override
    public double setPercentageHeight() {
        return percentageHeight;
    }

    @Override
    public void meshArrayList(ArrayList<MeshCartItem> list) {
        double totalPrice=0;
        DecimalFormat df = new DecimalFormat(MeshTVPreferenceManager.getHotelCurrency(getActivity()) + " ###,##0.00");
        for(MeshCartItem item:list)
        {
            totalPrice=totalPrice+(item.getItemPrice()*item.getQuantity());
            Log.i("steward",(totalPrice*item.getQuantity())+"");
        }
        tv_all_total.setText((df.format(totalPrice)));
        adapter.notifyDataSetChanged();
    }
}

