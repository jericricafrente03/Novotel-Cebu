package com.ph.bittelasia.meshtv.tv.carlson.Dining.View.Fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.Dining.Model.CarlsonDiningCategoryAdapter;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.AdapterInterface;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.DataSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Listener.MeshDataListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Manager.MeshTVDataManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshListItemClickedListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshListItemSelectedListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.RemoteControl.KR301KeyCode;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.CustomView.MeshTVCustomList.MeshTVCustomListView;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmEventListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Dining.MeshFoodCategory;

import java.util.ArrayList;

/**
 * Created by ramil on 1/23/18.
 */
@Layout(R.layout.carlson_category_layout)
@DataSetting(listenToFoodCategory = true)
public class CarlsonDiningCategory extends MeshTVFragment<MeshFoodCategory> implements MeshDataListener,MeshRealmListener,MeshRealmEventListener,AdapterInterface {

    //=====================================Variable=================================================
    public static final String TAG=CarlsonDiningCategory.class.getSimpleName()+"-steward";

    //-------------------------------------Instance-------------------------------------------------
    ArrayList<MeshFoodCategory>      categories;
    CarlsonDiningCategoryAdapter     adapter;
    Object                           object;

    //---------------------------------------View---------------------------------------------------
    @BindWidget(R.id.lv_tab)
    public MeshTVCustomListView lv_categories;

    //==============================================================================================


    //=====================================Lyfe Cycle===============================================

    @Override
    public void onResume() {
        super.onResume();
        MeshRealmManager.addListener(this);
        Log.i(TAG,"din cat 1");
    }

    @Override
    public void onPause() {
        super.onPause();
        MeshRealmManager.removeListener(this);
        Log.i(TAG,"din cat 2");
    }

    //==============================================================================================

    //=====================================MeshTVFragment===========================================
    protected void onDrawDone(View view) {
        try {
            MeshRealmManager.retrieve(MeshFoodCategory.class, this);
            lv_categories.setSelectedListener(new MeshListItemSelectedListener() {
                @Override
                public void onSelected(Object o) {
                    if(getListener()!=null)
                    {
                        getListener().onClicked(o);
                    }
                }
            });
            lv_categories.setClickListener(new MeshListItemClickedListener() {
                @Override
                public void onClicked(Object o) {
                    if(getListener()!=null)
                    {
                        getListener().onClicked(o);
                    }
                }
            });
            lv_categories.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    switch (KR301KeyCode.getEquivalent(keyEvent.getKeyCode()))
                    {
                        case KeyEvent.KEYCODE_DPAD_LEFT:
                            if(keyEvent.getAction()==KeyEvent.ACTION_UP) {
                                lv_categories.prev();
                                lv_categories.select();
                                return true;
                            }
                            return true;
                        case KeyEvent.KEYCODE_DPAD_RIGHT:
                            if(keyEvent.getAction()==KeyEvent.ACTION_UP) {
                                lv_categories.next();
                                lv_categories.select();
                                return true;
                            }
                            return true;
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                            if(keyEvent.getAction()== KeyEvent.ACTION_UP) {
                                lv_categories.select();
                                return true;
                            }
                            return true;
                    }
                    return false;
                }
            });

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        Log.i(TAG,"din cat 3");
    }

    @Override
    protected void onDataUpdated(ArrayList<MeshFoodCategory> arrayList) {
        adapter = new CarlsonDiningCategoryAdapter(arrayList,this);
        if(lv_categories!=null)
        {
            lv_categories.setAdapter(adapter);
        }
        Log.i(TAG,"din cat 4");
    }

    @Override
    protected void onNewData(Object o) {
        MeshRealmManager.retrieve(MeshFoodCategory.class,this);
        getListener().onSelected(true);
        Log.i(TAG,"din cat 5");
    }

    @Override
    protected void onDataUpdated(Object o, int i) {
        Log.i(TAG,"din cat 6");
        if(adapter!=null)
        {
            lv_categories.setAdapter(adapter);
        }
        else
        {
            MeshRealmManager.retrieve(MeshFoodCategory.class,this);
        }
    }

    @Override
    protected void onDeleteData(int i) {
        Log.i(TAG,"din cat 7");
    }

    @Override
    protected void onClearData() {
        Log.i(TAG,"din cat 8");
    }

    @Override
    protected void onDataNotFound(Class aClass) {
        Log.i(TAG,"din cat 8");
    }

    @Override
    protected void refresh() {
        Log.i(TAG,"din cat 9");
    }

    @Override
    protected void update(MeshFoodCategory meshShoppingCategory) {
        Log.i(TAG,"din cat 10");
        if(adapter!=null)
        {
            lv_categories.setAdapter(adapter);
//            getListener().onClicked(object);
        }
        else
        {
            MeshRealmManager.retrieve(MeshFoodCategory.class,this);
        }
    }
    //==============================================================================================


    //=================================MeshDataListener=============================================
    @Override
    public void onNoNetwork(Class aClass) {
        Log.i(TAG,"din cat 11");
    }

    @Override
    public void onNoData(Class aClass) {
        Log.i(TAG,"din cat 12");
    }

    @Override
    public void onParseFailed(Class aClass, String s) {
        Log.i(TAG,"din cat 13");
    }

    @Override
    public void onFileNotFound(Class aClass, String s) {
        Log.i(TAG,"din cat 14");
    }

    @Override
    public void onDataReceived(Class aClass, int i) {
        MeshRealmManager.retrieve(MeshFoodCategory.class,this);
        Log.i(TAG,"din cat 15");
    }
    //===================================MeshRealmListener==========================================
    @Override
    public void onRetrieved(Class aClass, ArrayList<Object> arrayList) {
        try {
            categories = new ArrayList<>();
            for (Object o : arrayList) {
                categories.add((MeshFoodCategory) o);
            }
            adapter = new CarlsonDiningCategoryAdapter(categories,this);
            if (lv_categories != null) {
                lv_categories.setAdapter(adapter);
            }
            if (categories.size() > 0) {
                setSelectedItem(categories.get(0));
            }
            getListener().onSelected(categories.get(0));
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        Log.i(TAG,"din cat 16");
    }

    @Override
    public void onFailed(Class aClass, String s) {

        Log.i(TAG,"din cat 17");
    }

    @Override
    public void onEmpty(Class aClass, String s) {
        MeshTVDataManager.requestData(MeshFoodCategory.class,this);
        Log.i(TAG,"din cat 17");
    }

    @Override
    public void onCleared(Class aClass) {

    }
    //==============================================================================================

    //====================================MeshRealmEventListener====================================
    @Override
    public void onCreate(Object o) {
        Log.i(TAG,"din cat 18");
    }

    @Override
    public void onCreateBulk(ArrayList<Object> arrayList) {
        MeshRealmManager.retrieve(MeshFoodCategory.class,this);
        Log.i(TAG,"din cat 19");
    }

    @Override
    public void onDelete(Class aClass) {
        Log.i(TAG,"din cat 20");
    }

    @Override
    public void onClear(Class aClass) {
        Log.i(TAG,"din cat 21");
    }

    @Override
    public void onClick(Object o) {
        getListener().onClicked(o);
        object=o;
        Log.i(TAG,"din cat 22");
    }

    @Override
    public void onSelected(Object o) {
        if(o instanceof TextView)
        {
            ((TextView)o).setTextColor(Color.RED);
        }
        getListener().onSelected(o);
        object=o;
        Log.i(TAG,"din cat 23");
    }
    //==============================================================================================
}
