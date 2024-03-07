package com.ph.bittelasia.meshtv.tv.carlson.Billing.View.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import com.ph.bittelasia.meshtv.tv.carlson.Billing.Model.CarlsonBillingListAdapter;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.DataSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Listener.MeshDataListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Manager.MeshTVDataManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Manager.MeshTVPreferenceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Model.MeshConfig;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmEventListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Bill.MeshBillV2;
import com.ph.bittelasia.meshtv.tv.mtvlib.Transaction.Model.MeshCartItem;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by ramil on 1/11/18.
 */
@Layout(R.layout.carlson_bill_list)
@DataSetting(listenToBills = true)
public class CarlsonBillingList extends MeshTVFragment<MeshBillV2> implements MeshDataListener,MeshRealmListener,MeshRealmEventListener {

    public CarlsonBillingListAdapter adapter;
    public ArrayList<MeshCartItem>            cartlist;
    double                                    total=0.0;

    ArrayList<MeshBillV2>  list;

    @BindWidget(R.id.lv_list)
    public ListView lv_billing;

    @BindWidget(R.id.tv_total)
    public TextView tv_total;

    @BindWidget(R.id.tv_hotel)
    public TextView tv_hotel;

    CarlsonBillingTotal billingTotal;

    public static CarlsonBillingList l;

    public static CarlsonBillingList get(CarlsonBillingTotal billingTotal)
    {
        l=new CarlsonBillingList();
        l.billingTotal=billingTotal;
        return l;
    }



    @Override
    protected void onDrawDone(View view) {
        try {
            MeshTVDataManager.requestData(MeshBillV2.class, this);
            MeshRealmManager.retrieve(MeshBillV2.class, this);

            MeshConfig config = new MeshConfig();
            config.update();
            //tv_hotel.setText(config.getHotel_name());
            tv_hotel.setText("NOVOTEL");
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.i("steward","bill 1");
    }

    @Override
    protected void onDataUpdated(ArrayList<MeshBillV2> arrayList) {
        MeshRealmManager.retrieve(MeshBillV2.class,this);
        Log.i("steward","bill 2");
    }

    @Override
    protected void onNewData(Object o) {
        MeshRealmManager.retrieve(MeshBillV2.class,this);
        Log.i("steward","bill 3");
    }

    @Override
    protected void onDataUpdated(Object o, int i) {
        Log.i("steward","bill 4");
    }

    @Override
    protected void onDeleteData(int i) {
        Log.i("steward","bill 5");
    }

    @Override
    protected void onClearData() {
        Log.i("steward","bill 6");
    }

    @Override
    protected void onDataNotFound(Class aClass) {
        Log.i("steward","bill 7");
    }

    @Override
    protected void refresh() {
        MeshRealmManager.retrieve(MeshBillV2.class,this);
        Log.i("steward","bill 8");
    }

    @Override
    protected void update(MeshBillV2 meshBillV2) {
        Log.i("steward","bill 9");
    }

    @Override
    public void onNoNetwork(Class aClass) {
        Log.i("steward","bill 10");
    }

    @Override
    public void onNoData(Class aClass) {
        Log.i("steward","bill 11");
    }

    @Override
    public void onParseFailed(Class aClass, String s) {
        Log.i("steward","bill 12");
    }

    @Override
    public void onFileNotFound(Class aClass, String s) {
        Log.i("steward","bill 13");
    }

    @Override
    public void onDataReceived(Class aClass, int i) {
        MeshRealmManager.retrieve(MeshBillV2.class,this);
        Log.i("steward","bill 13");
    }

    @Override
    public void onRetrieved(Class aClass, ArrayList<Object> arrayList) {
        try {
            double t = 0;
            list = new ArrayList<>();
            for (Object o : arrayList) {
                list.add((MeshBillV2) o);
                t = t + ((MeshBillV2) o).getPrice();
            }
            DecimalFormat df = new DecimalFormat("TOTAL PRICE: "+ MeshTVPreferenceManager.getHotelCurrency(getActivity())+  " ###,##0.00");
            tv_total.setText(df.format(t));
            getListener().onSelected(list.get(0));
            lv_billing.setAdapter(new CarlsonBillingListAdapter(getActivity(), lv_billing, R.layout.carlson_billing_items, list));
            lv_billing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    getListener().onClicked(list.get(i));
                }
            });
            lv_billing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    getListener().onSelected(list.get(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    getListener().onSelected(list.get(0));
                }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        Log.i("steward","bill 14");
    }

    @Override
    public void onFailed(Class aClass, String s) {
        Log.i("steward","bill 15");
    }

    @Override
    public void onEmpty(Class aClass, String s) {
        MeshTVDataManager.requestData(MeshBillV2.class,this);
        Log.i("steward","bill 16");
    }

    @Override
    public void onCleared(Class aClass) {
        MeshRealmManager.retrieve(MeshBillV2.class,this);
    }

    @Override
    public void onResume() {
        super.onResume();
        MeshRealmManager.addListener(this);
        MeshTVDataManager.requestData(MeshBillV2.class,this);
        Log.i("steward","bill 17");
    }

    @Override
    public void onPause() {
        super.onPause();
        MeshRealmManager.removeListener(this);
        Log.i("steward","bill 18");
    }
    @Override
    public void onCreate(Object o) {
        Log.i("steward","bill 19");
    }

    @Override
    public void onCreateBulk(ArrayList<Object> arrayList) {
        MeshRealmManager.retrieve(MeshBillV2.class,this);
        Log.i("steward","bill 20");
    }

    @Override
    public void onDelete(Class aClass) {
        Log.i("steward","bill 21");
    }

    @Override
    public void onClear(Class aClass) {
        Log.i("steward","bill 22");
    }
}
