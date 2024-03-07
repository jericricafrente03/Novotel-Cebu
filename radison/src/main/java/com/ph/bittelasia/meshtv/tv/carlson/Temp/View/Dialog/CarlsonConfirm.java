package com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.Core.App.Carlson;
import com.ph.bittelasia.meshtv.tv.carlson.Movies.View.Activity.CarlsonVod;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.Packages;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Activity.CarlsonFullScreen;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Constant.AppDataSource;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Manager.MeshTVDataManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshCheckOutListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Model.MeshGuest;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.App.MeshTVApp;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Bill.MeshBillV2;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Concierge.MeshConciergeRequestItem;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Concierge.MeshConciergeRequestService;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Dining.MeshFood;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacility;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Message.MeshMessage;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Shopping.MeshShoppingItem;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVOD;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVODBought;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVODListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVODManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Transaction.Control.MeshBittelCheckOut;
import com.ph.bittelasia.meshtv.tv.mtvlib.Transaction.Model.MeshCartItem;
import com.ph.bittelasia.meshtv.tv.mtvlib.Transaction.Model.MeshTVCart;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ramil on 1/2/18.
 */

public class CarlsonConfirm extends MeshTVDialog implements MeshRealmListener {

    LinearLayout                 ll_parent;
    LinearLayout                 ll_yes;
    LinearLayout                 ll_no;
    TextView                     tv_ask;
    Object                       object;
    MeshVOD vod;
    MeshCartItem item;
    MeshFood meshFood;
    MeshMessage meshMessage;
    MeshShoppingItem meshShoppingItem;
    MeshFacility meshFacility;
    MeshConciergeRequestService meshService;
    MeshConciergeRequestItem meshItem;
    MeshTVFragmentListener   cb;

    Animation        zoomIn;
    double           percentageWidth;
    double           percentageHeight;
    boolean          checked;
    boolean          rented;
    int              qty;

    public static CarlsonConfirm c;

    public static CarlsonConfirm dialog(Object o,double percentageWidth,double percentageHeight)
    {
        c=new CarlsonConfirm();
        c.object=o;
        c.percentageWidth=percentageWidth;
        c.percentageHeight=percentageHeight;
        return c;
    }

    public static CarlsonConfirm dialog(Object o,double percentageWidth,double percentageHeight,int qty)
    {
        c=new CarlsonConfirm();
        c.object=o;
        c.percentageWidth=percentageWidth;
        c.percentageHeight=percentageHeight;
        c.qty=qty;
        return c;
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cb=(MeshTVFragmentListener)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        cb=null;
    }

    final View.OnTouchListener touch=new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                view.performClick();
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                view.requestFocus();
            }
            return false;
        }
    };

    final View.OnHoverListener hover=new View.OnHoverListener() {
        @Override
        public boolean onHover(View view, MotionEvent motionEvent) {
            view.requestFocus();
            return false;
        }
    };


    final View.OnClickListener click=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.ll_yes:
                    try
                    {
                        if (object instanceof MeshVOD && !checked && !rented)
                        {

                            vod = (MeshVOD) object;
                            item = new MeshCartItem(vod, vod.getPrice());
                            item.setItemClass(MeshVOD.class);
                            MeshTVCart.add(item);
                            ll_parent.setBackgroundColor(getResources().getColor(R.color.carlson_skyblue));
                            tv_ask.setText(getContext().getResources().getString(R.string.watch));
                            if(vod.getBought()==0 && !rented)
                            {
                                if(MeshTVApp.get().getDataSourceSetting()== AppDataSource.SERVER) {
                                    MeshVODManager.isRented(new MeshVODListener() {
                                        @Override
                                        public void onBought(boolean b) {
                                            if(!b)
                                            {
                                                getActivity().runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        MeshVODManager.rent(vod, new MeshVODListener() {
                                                            @Override
                                                            public void onBought(boolean b) {
                                                                if (b) {
                                                                    checked=true;
                                                                    Log.i("steward", "bought");
                                                                    cb.onSelected(1);//bought
                                                                    dismissAllDialogs(getFragmentManager());
                                                                }
                                                            }

                                                            @Override
                                                            public void onClear() {

                                                            }

                                                            @Override
                                                            public void bulkBought(ArrayList<Integer> arrayList) {

                                                            }
                                                        });
                                                    }});
                                            }
                                        }

                                        @Override
                                        public void onClear() {

                                        }

                                        @Override
                                        public void bulkBought(ArrayList<Integer> arrayList) {

                                        }
                                    },vod.getId());
                                    rented=true;
                                }
                                else
                                {
                                    setBought(vod.getId());
                                    setRented(vod.getId());
                                    MeshBittelCheckOut.checkout(new MeshCheckOutListener() {
                                        @Override
                                        public void onFail(String s) {
                                            Log.i("steward", "Failed: vod checkout");
                                        }

                                        @Override
                                        public void onSuccess(String s) {
                                            Log.i("steward", "Successful: vod checkout");
                                            checked=true;
                                            cb.onSelected(1);//bought
                                            dismissAllDialogs(getFragmentManager());
                                        }
                                    },  MeshTVApp.get().getDataSourceSetting()!= AppDataSource.SERVER, MeshVOD.class);
                                }
                            }
                        }
                        else if(object instanceof MeshShoppingItem)
                        {
                            meshShoppingItem =(MeshShoppingItem) object;
                            item = new MeshCartItem(meshShoppingItem);
                            item.setQuantity(qty>0?qty:0);
                            item.setItemClass(MeshShoppingItem.class);
                            MeshTVCart.add(item);
                            dismissAllDialogs(getFragmentManager());
                        }
                        else if(object instanceof MeshFood)
                        {
                            meshFood =(MeshFood) object;
                            item = new MeshCartItem(meshFood);
                            item.setQuantity(qty>0?qty:0);
                            item.setItemClass(MeshFood.class);
                            MeshTVCart.add(item);
                            dismissAllDialogs(getFragmentManager());
                        }
                        else if(object instanceof MeshFacility)
                        {
                            meshFacility =(MeshFacility) object;
                            item = new MeshCartItem(meshFacility,meshFacility.getUnit_price());
                            item.setQuantity(qty>0?qty:0);
                            item.setItemClass(MeshFacility.class);
                            MeshTVCart.add(item);
                            dismissAllDialogs(getFragmentManager());
                        }
                        else if(object instanceof MeshConciergeRequestItem)
                        {
                            meshItem =(MeshConciergeRequestItem) object;
                            item = new MeshCartItem(meshItem);
                            item.setQuantity(qty>0?qty:0);
                            item.setItemClass(MeshConciergeRequestItem.class);
                            MeshTVCart.add(item);
                            dismissAllDialogs(getFragmentManager());
                        }
                        else if(object instanceof MeshConciergeRequestService)
                        {
                            meshService =(MeshConciergeRequestService) object;
                            item = new MeshCartItem(meshService);
                            item.setQuantity(qty>0?qty:0);
                            item.setItemClass(MeshConciergeRequestService.class);
                            MeshTVCart.add(item);
                            dismissAllDialogs(getFragmentManager());
                        }
                        else if(object instanceof MeshMessage)
                        {
                            meshMessage=(MeshMessage)object;
                            deleteMessage(meshMessage.getId());
                            dismissAllDialogs(getFragmentManager());
                        }
                        else if(object instanceof String)
                        {
                            MeshGuest guest = new MeshGuest();
//                            guest.setFirstname("Welcome");
//                            guest.setLastname("Guest");
//                            guest.compare();
//                            getActivity().finish();
                            MeshVODManager.clear(new MeshVODListener() {
                                @Override
                                public void onBought(boolean b) {

                                }

                                @Override
                                public void onClear() {
                                    Log.i("steward", "onClear: rented vod clear");
                                }

                                @Override
                                public void bulkBought(ArrayList<Integer> arrayList) {

                                }
                            });
                            MeshRealmManager.clear(MeshVOD.class,CarlsonConfirm.this);
                            MeshRealmManager.clear(MeshCartItem.class,CarlsonConfirm.this);
                            MeshRealmManager.clear(MeshMessage.class,CarlsonConfirm.this);
                            MeshRealmManager.clear(MeshBillV2.class, CarlsonConfirm.this);

                        }
                        else
                        {
                            if(object instanceof MeshVOD && rented)
                            {
                                vod = (MeshVOD) object;
                                checked = false;
                                Intent i = new Intent(getContext(), CarlsonFullScreen.class);
                                i.putExtra("VOD", vod.getId());
                                i.putExtra("MEDIA", "VOD");
                                startActivityForResult(i, 100);
                                dismissAllDialogs(getFragmentManager());
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                    break;
                case R.id.ll_no:
                    dismissAllDialogs(getFragmentManager());
                    break;
            }
        }
    };

    /* Todo: remind sir Mars */
    public void setBought(final int id)
    {
        Realm r = Realm.getDefaultInstance();
        r.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<MeshVOD> result = realm.where(MeshVOD.class).equalTo(MeshVOD.TAG_ID,id).findAll();
                MeshVOD v = result.get(0);
                v.setBought(1);
                realm.copyToRealm(v);
                realm.close();
            }
        });
    }

    /* Todo: remind sir Mars */
    public void setRented(final int id)
    {
        Realm realm=Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                MeshVODBought bought = new MeshVODBought();
                bought.setId(id);
                realm.copyToRealmOrUpdate(bought);
            }
        });
    }

    /*Todo:Remind about message*/
    public void deleteMessage(final int id)
    {
        Realm r = Realm.getDefaultInstance();
        r.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<MeshMessage> result = realm.where(MeshMessage.class).equalTo(MeshMessage.TAG_ID,id).findAll();
                result.deleteAllFromRealm();
                realm.close();
            }
        });
    }


    @Override
    public void setIDs(View view) {
        try
        {
            ll_yes=(LinearLayout)view.findViewById(R.id.ll_yes);
            ll_no=(LinearLayout)view.findViewById(R.id.ll_no);
            tv_ask=(TextView)view.findViewById(R.id.tv_ask);
            ll_parent=(LinearLayout)view.findViewById(R.id.ll_parentcolor);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setContent() {
        try
        {
            checked=false;
            rented=false;
            ll_yes.setOnClickListener(click);
            ll_no.setOnClickListener(click);
            ll_yes.setOnTouchListener(touch);
            ll_no.setOnTouchListener(touch);
            ll_yes.setOnHoverListener(hover);
            ll_no.setOnHoverListener(hover);

            if(object instanceof MeshShoppingItem)
                tv_ask.setText(getContext().getResources().getString(R.string.addcart));
            if(object instanceof MeshFood)
                tv_ask.setText(getContext().getResources().getString(R.string.addcart));
            if(object instanceof MeshFacility)
                tv_ask.setText(getContext().getResources().getString(R.string.addcart));
            if(object instanceof MeshConciergeRequestItem)
                tv_ask.setText(getContext().getResources().getString(R.string.addcart));
            if(object instanceof MeshConciergeRequestService)
                tv_ask.setText(getContext().getResources().getString(R.string.addcart));
            if(object instanceof MeshMessage)
                tv_ask.setText(getContext().getResources().getString(R.string.delete_message));
            if(object instanceof String)
            {
                if(((String)object).equals(getActivity().getResources().getString(R.string.confirmcheckout)))
                {
                    tv_ask.setText(getContext().getResources().getString(R.string.checkout));
                }
            }
            if(object instanceof MeshVOD)
            {
                MeshVODManager.isRented(new MeshVODListener() {
                    @Override
                    public void onBought(final boolean b)
                    {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (b)
                                {
                                    tv_ask.setText(getContext().getResources().getString(R.string.watch));
                                    rented=true;
                                }
                                else
                                {
                                    tv_ask.setText(getContext().getResources().getString(R.string.rent));
                                    rented=false;
                                }
                            }
                        });
                    }

                    @Override
                    public void onClear()
                    {

                    }

                    @Override
                    public void bulkBought(ArrayList<Integer> arrayList)
                    {

                    }
                },((MeshVOD)object).getId());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int setLayout() {
        return R.layout.carlson_confirm;
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100)
        {
            if(resultCode==Activity.RESULT_OK)
                Log.i("steward","result ok");
        }
    }

    //MeshRealmListener
    @Override
    public void onRetrieved(Class aClass, ArrayList<Object> arrayList) {

    }

    @Override
    public void onFailed(Class aClass, String s) {

    }

    @Override
    public void onEmpty(Class aClass, String s) {

    }

    @Override
    public void onCleared(Class aClass) {
      if(aClass==MeshBillV2.class){
          dismissAllDialogs(getFragmentManager());
          Carlson.app.setMessage();
          getActivity().finish();
          startActivity(getActivity().getIntent());
      }
        Log.i("steward", "onCleared: "+aClass.getSimpleName());
    }
}
