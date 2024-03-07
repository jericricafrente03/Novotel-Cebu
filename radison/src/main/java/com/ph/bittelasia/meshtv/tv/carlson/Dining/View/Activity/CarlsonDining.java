package com.ph.bittelasia.meshtv.tv.carlson.Dining.View.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Activity.CarlsonActivity;
import com.ph.bittelasia.meshtv.tv.carlson.Dining.View.Fragment.CarlsonDiningCategory;
import com.ph.bittelasia.meshtv.tv.carlson.Dining.View.Fragment.CarlsonDiningList;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshArrayListCallBack;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshTVButtons;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog.CarlsonAddToCart;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog.CarlsonViewCart;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Fragment.CarlsonButtons;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.ActivitySetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.AttachFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Dining.MeshFood;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Dining.MeshFoodCategory;
import com.ph.bittelasia.meshtv.tv.mtvlib.Transaction.Model.MeshTVCart;


import java.util.ArrayList;

/**
 * Created by ramil on 1/23/18.
 */
@Layout(R.layout.carlson_vod)
@ActivitySetting()
public class CarlsonDining extends CarlsonActivity implements MeshTVFragmentListener,MeshArrayListCallBack<MeshFood> {

    public static final String TAG=CarlsonDining.class.getSimpleName()+"-steward";

    CarlsonDiningCategory   category;
    CarlsonDiningList       list;
    CarlsonButtons          buttons;

    MeshFoodCategory        meshCategory;
    MeshFood                item;
    boolean                 loaded=false;
    int                     updated=0;


    @BindWidget(R.id.tv_maintitle)
    public TextView tv_sub;

    @AttachFragment(container = R.id.ll_categories,tag="category",order=3)
    public Fragment attachCategory()
    {
        category=new CarlsonDiningCategory();
        return category;
    }

    @AttachFragment(container = R.id.ll_display,tag="list",order = 4)
    public Fragment attachList()
    {
        list =new CarlsonDiningList();
        return list;
    }

    @AttachFragment(container = R.id.ll_bottom,tag = "bottom",order = 5)
    public Fragment attachButtons()
    {
        buttons=new CarlsonButtons();
        return buttons;
    }

    @Override
    public void onResume() {
        super.onResume();
        loaded=false;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        tv_sub.setText(("DINING"));
        buttons.setVisibility(buttons.btn_view);
        buttons.btn_view.setText(("VIEW CART"));
    }

    @Override
    public void onClicked(Object o) {

        try
        {
            if (o instanceof String)
            {
                if (((String) o).equals(MeshTVButtons.VIEWORDERS.getButton()))
                {
                    CarlsonViewCart.dialog(MeshTVCart.display(MeshFood.class), .70, .70).show(getSupportFragmentManager(), "view");
                }
            }
            if(o instanceof MeshFood)
            {
                item = (MeshFood)o;
                CarlsonAddToCart.dialog(item, .60, .60).show(getSupportFragmentManager(), "dialog");
            }
            if (o instanceof MeshFoodCategory)
            {
                meshCategory = (MeshFoodCategory) o;
                list.setCategory(meshCategory.getId());
                Log.i(TAG, "dining onclicked");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onSelected(Object o) {
        try
        {
            if(!loaded)
            {
                if (o instanceof MeshFoodCategory) {
                    meshCategory = (MeshFoodCategory) o;
                    list.setCategory(meshCategory.getId());
                    loaded=true;
                    Log.i(TAG, "dining notified ok");
                }
            }
            else
            {
                    list.adapter.notifyDataSetChanged();
                    list.gv_grid.invalidateViews();
                    Log.i(TAG, "dining notified");
            }
            if (o instanceof Integer)
            {
                list.gv_grid.setSelection(0);
            }
            if(o instanceof TextView)
            {
                ((TextView)o).setTextColor(Color.RED);
            }
            if(o instanceof Boolean)
            {
                if(((Boolean)o))
                {
                    list.setCategory(category.getId());
                }
                else
                {
                    list.adapter.notifyDataSetChanged();
                    list.gv_grid.invalidateViews();
                }
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void meshArrayList(ArrayList<MeshFood> list) {

    }
}
