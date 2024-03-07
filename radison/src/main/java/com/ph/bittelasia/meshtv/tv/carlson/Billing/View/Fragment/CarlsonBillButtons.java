package com.ph.bittelasia.meshtv.tv.carlson.Billing.View.Fragment;

import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshTVButtons;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.RemoteControl.KR301KeyCode;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;

import java.util.ArrayList;

/**
 * Created by ramil on 1/25/18.
 */
@Layout(R.layout.carlson_buttons)
public class CarlsonBillButtons extends MeshTVFragment {
    Animation zoomIn;

    @BindWidget(R.id.btn_add)
    public Button btn_add;

    @BindWidget(R.id.btn_view)
    public Button btn_view;

    @BindWidget(R.id.btn_checkout)
    public Button btn_checkout;


    @BindWidget(R.id.btn_delete)
    public Button btn_delete;


    @BindWidget(R.id.btn_myvideos)
    public Button btn_myvideos;

    @BindWidget(R.id.btn_rent)
    public Button btn_rent;
    View[] views;
    int index=0;

    int count=0;

    final View.OnClickListener click=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.btn_add:
                    getListener().onClicked(MeshTVButtons.ADDORDERS.getButton());
                    break;
                case R.id.btn_view:
                    getListener().onClicked(MeshTVButtons.VIEWORDERS.getButton());
                    break;
                case R.id.btn_checkout:
                    getListener().onClicked(MeshTVButtons.CHECKOUT.getButton());
                    break;
                case R.id.btn_delete:
                    getListener().onClicked(MeshTVButtons.DELETE.getButton());
                    break;
                case R.id.btn_myvideos:
                    getListener().onClicked(MeshTVButtons.MYVIDEOS.getButton());
                    break;
                case R.id.btn_rent:
                    getListener().onClicked(MeshTVButtons.RENT.getButton());
                    break;
            }
        }
    };

    final View.OnFocusChangeListener focus=new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            switch (view.getId())
            {
                case R.id.btn_add:
                    checkImage(view,b,zoomIn);
                    index=0;
                    break;
                case R.id.btn_view:
                    checkImage(view,b,zoomIn);
                    index=1;
                    break;
                case R.id.btn_checkout:
                    checkImage(view,b,zoomIn);
                    index=2;
                    break;
                case R.id.btn_delete:
                    checkImage(view,b,zoomIn);
                    index=4;
                    break;
                case R.id.btn_myvideos:
                    checkImage(view,b,zoomIn);
                    index=6;
                    break;

                case R.id.btn_rent:
                    checkImage(view,b,zoomIn);
                    index=8;
                    break;

            }
        }
    };

    final View.OnKeyListener keyListener=new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            switch (KR301KeyCode.getEquivalent(keyEvent.getKeyCode()))
            {
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    if(keyEvent.getAction()==KeyEvent.ACTION_UP) {
                        if(count==0) {
                            views[index].requestFocus();
                            count=1;
                        }else
                        {
                            if(index < views.length)
                                views[index++].requestFocus();
                        }
                        return true;
                    }
                    return true;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    if(keyEvent.getAction()==KeyEvent.ACTION_UP) {
                        if(count==0) {
                            views[index].requestFocus();
                            count=1;
                        }else
                        {
                            if(index != 0)
                                views[index--].requestFocus();
                        }
                        return true;
                    }
                    return true;
            }
            return false;
        }
    };

    public void checkImage(final View view, Boolean b, Animation animation)
    {
        if(b)
        {
            view.startAnimation(animation);
        }
        else
        {
            view.clearAnimation();
        }

    }

    public void setVisibility(Button ... btn)
    {
        try
        {
            for (int x = 0; x < btn.length; x++) {
                btn[x].setVisibility(View.VISIBLE);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void onDrawDone(View view)
    {
        zoomIn = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);

        btn_add.setOnClickListener(click);
        btn_view.setOnClickListener(click);
        btn_checkout.setOnClickListener(click);
        btn_delete.setOnClickListener(click);
        btn_myvideos.setOnClickListener(click);
        btn_rent.setOnClickListener(click);


        btn_add.setOnFocusChangeListener(focus);
        btn_view.setOnFocusChangeListener(focus);
        btn_checkout.setOnFocusChangeListener(focus);
        btn_delete.setOnFocusChangeListener(focus);
        btn_myvideos.setOnFocusChangeListener(focus);
        btn_rent.setOnFocusChangeListener(focus);

        btn_checkout.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onDataUpdated(ArrayList arrayList) {

    }

    @Override
    protected void onNewData(Object o) {

    }

    @Override
    protected void onDataUpdated(Object o, int i) {

    }

    @Override
    protected void onDeleteData(int i) {

    }

    @Override
    protected void onClearData() {

    }

    @Override
    protected void onDataNotFound(Class aClass) {

    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void update(Object o) {

    }
}

