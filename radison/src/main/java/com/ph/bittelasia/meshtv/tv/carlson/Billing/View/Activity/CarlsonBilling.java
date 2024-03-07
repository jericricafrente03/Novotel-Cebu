package com.ph.bittelasia.meshtv.tv.carlson.Billing.View.Activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ph.bittelasia.meshtv.tv.carlson.Billing.View.Fragment.CarlsonBillButtons;
import com.ph.bittelasia.meshtv.tv.carlson.Billing.View.Fragment.CarlsonBillingList;
import com.ph.bittelasia.meshtv.tv.carlson.Billing.View.Fragment.CarlsonBillingTotal;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Activity.CarlsonActivity;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshTVButtons;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog.CarlsonConfirm;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.ActivitySetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.AttachFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.RemoteControl.KR301KeyCode;

/**
 * Created by ramil on 1/11/18.
 */
@Layout(R.layout.carlson_checkout)
@ActivitySetting()
public class CarlsonBilling extends CarlsonActivity implements MeshTVFragmentListener {

    CarlsonBillingList list;
    CarlsonBillingTotal billingTotal;
    CarlsonBillButtons buttons;
    Button btn_checkout;


    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {
        try {
            switch (event.getAction()) {
                case KeyEvent.ACTION_DOWN:
                    switch (event.getKeyCode()) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                        case KeyEvent.KEYCODE_NUMPAD_ENTER:
                        case KeyEvent.KEYCODE_DPAD_RIGHT:
                        case KeyEvent.KEYCODE_DPAD_LEFT:
                        case KeyEvent.KEYCODE_MENU:
                        case KeyEvent.KEYCODE_POWER:
                        case KeyEvent.KEYCODE_SETTINGS:
                        case KeyEvent.KEYCODE_PAGE_UP:
                        case KeyEvent.KEYCODE_CHANNEL_UP:
                        case KeyEvent.KEYCODE_DPAD_UP:
                        case KeyEvent.KEYCODE_PAGE_DOWN:
                        case KeyEvent.KEYCODE_CHANNEL_DOWN:
                        case KeyEvent.KEYCODE_DPAD_DOWN://Channel down

                            return true;
                        default:
                    }
                    break;
                case KeyEvent.ACTION_UP:
                    switch (event.getKeyCode()) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                        case KeyEvent.KEYCODE_NUMPAD_ENTER:
                        case KeyEvent.KEYCODE_DPAD_RIGHT:
                        case KeyEvent.KEYCODE_DPAD_LEFT:
                        case KeyEvent.KEYCODE_MENU:
                        case KeyEvent.KEYCODE_POWER:
                        case KeyEvent.KEYCODE_SETTINGS:
                        case KeyEvent.KEYCODE_PAGE_UP:
                        case KeyEvent.KEYCODE_CHANNEL_UP:
                        case KeyEvent.KEYCODE_DPAD_UP:
                        case KeyEvent.KEYCODE_PAGE_DOWN:
                        case KeyEvent.KEYCODE_CHANNEL_DOWN:
                        case KeyEvent.KEYCODE_DPAD_DOWN://Channel down
                            if(btn_checkout!=null)
                                btn_checkout.callOnClick();
                            return true;
                    }
                    break;
                default:
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        btn_checkout = findViewById(R.id.btn_checkout);
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarlsonConfirm.dialog(getResources().getString(R.string.confirmcheckout), .35, .25).show(getSupportFragmentManager(), "checkoutconfirm");
            }
        });
    }



    @AttachFragment(container = R.id.ll_display,tag = "list",order = 2)
    public Fragment attachList()
    {
        list=CarlsonBillingList.get(billingTotal);
        return list;
    }

    @AttachFragment(container = R.id.ll_total,tag="total",order=1)
    public Fragment attachTotal()
    {
        billingTotal=new CarlsonBillingTotal();
        return  billingTotal;
    }


    @Override
    public void onClicked(Object o) {
        try
        {
            if (o instanceof String) {
                if (((String) o).equals(MeshTVButtons.CHECKOUT.getButton())) {
                    CarlsonConfirm.dialog(getResources().getString(R.string.confirmcheckout), .35, .25).show(getSupportFragmentManager(), "checkoutconfirm");
                }
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void onSelected(Object o) {

    }

}
