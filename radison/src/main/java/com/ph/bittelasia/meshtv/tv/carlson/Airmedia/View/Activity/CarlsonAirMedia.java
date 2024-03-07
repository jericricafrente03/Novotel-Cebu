package com.ph.bittelasia.meshtv.tv.carlson.Airmedia.View.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ph.bittelasia.meshtv.tv.carlson.Airmedia.View.Fragment.CarlsonAirMediaGrid;
import com.ph.bittelasia.meshtv.tv.carlson.Airmedia.View.Fragment.CarlsonAirMediaInstructions;
import com.ph.bittelasia.meshtv.tv.carlson.Airmedia.View.Fragment.CarlsonAirMediaQRCode;
import com.ph.bittelasia.meshtv.tv.carlson.Airmedia.View.Fragment.CarlsonAirMediaStatus;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Activity.CarlsonActivity;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment.CarlsonHotelWeather;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment.CarslonGuestInfo;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Settings.Model.SessionManager;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.ContentCallBack;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.PlatformCallBack;
import com.ph.bittelasia.meshtv.tv.mtvlib.AirMedia.Control.MeshTVAirmediaControl;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.Event.Init;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.ActivitySetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.AttachFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.BroadcastListeners.MeshTVAirmediaListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.BroadcastListeners.MeshTVPackageListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.RemoteControl.KR301KeyCode;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Activity.MeshTVActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by ramil on 12/5/17.
 */
@Layout(R.layout.carlson_airmedia_layout)
@ActivitySetting()
public class CarlsonAirMedia extends CarlsonActivity implements MeshTVAirmediaListener,MeshTVPackageListener,PlatformCallBack,ContentCallBack
{
    CarlsonAirMediaGrid         airmediaGrid;
    CarlsonAirMediaInstructions instructions;
    CarlsonAirMediaStatus       airmediastatus;
    WifiManager                 wifiManager;
    boolean                     status;


    @BindWidget(R.id.ch_wifi)
    public View ch_wifi;

    @BindWidget(R.id.tv_initial)
    public TextView tv_initial;

    @BindWidget(R.id.btn_show)
    public Button btn_show;

    public boolean isDisplaying=false;

    public void checkWifi()
    {
        wifiManager  = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(wifiManager.isWifiEnabled())
        {
            ((CheckBox)ch_wifi).setChecked(true);
        }
        else
        {
            ((CheckBox)ch_wifi).setChecked(false);
        }
    }


    @Override
    public void onResume()
    {
        super.onResume();
        setContent();
        checkWifi();
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {
        switch (event.getAction())
        {
            case KeyEvent.ACTION_DOWN:
                switch (KR301KeyCode.getEquivalent(event.getKeyCode()))
                {
                    case KeyEvent.KEYCODE_BACK:
                        btn_show.setVisibility(View.GONE);
                        return true;
                }
                break;
            case KeyEvent.ACTION_UP:
                switch (KR301KeyCode.getEquivalent(event.getKeyCode()))
                {
                    case KeyEvent.KEYCODE_BACK:
                        if(isDisplaying)
                        {
                            isDisplaying=false;
                            Log.i("steward","false");
                            tv_initial.setText((getResources().getString(R.string.airmedia)+""));
                            getSupportFragmentManager().beginTransaction().remove(instructions).commit();
                            airmediaGrid= new CarlsonAirMediaGrid();
                            getSupportFragmentManager().beginTransaction().add(R.id.ll_display,airmediaGrid,"list").commit();
                            return true;
                        }
                        else
                        {
                            isDisplaying=false;
                            super.onBackPressed();
                            return false;
                        }
                }
                break;
        }

        return super.dispatchKeyEvent(event);
    }


    @Init(order = 2)
    public void setAirmedia()
    {
        try
        {
            Log.i("steward", "setAirmedia");
            if (getIntent().getBooleanExtra("ready", false))
            {
                status=true;
            }
            else
            {
                MeshTVAirmediaControl.disableToast(this);
                MeshTVAirmediaControl.startAirMedia(this);
                MeshTVAirmediaControl.enableDLNA(this);
                MeshTVAirmediaControl.enableAirplay(this);
                status=false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String allCaps(String value)
    {
        StringBuilder sb = new StringBuilder(value);
        for (int index = 0; index < sb.length(); index++) {
            char c = sb.charAt(index);
            if (Character.isLowerCase(c)) {
                sb.setCharAt(index, Character.toUpperCase(c));
            }
        }
        return sb.toString();
    }

    @AttachFragment(container = R.id.ll_display,tag = "list", order = 3)
    public Fragment attachList()
    {
        airmediaGrid=new CarlsonAirMediaGrid();
        return airmediaGrid;
    }

    @AttachFragment(container = R.id.rl_status,tag="airmediastatus",order=4)
    public Fragment attachStatus()
    {
        airmediastatus=new CarlsonAirMediaStatus();
        return airmediastatus;
    }


    @Override
    public void airmediaReady()
    {
        Log.i("steward","@airmediaReady: airmedia is ready");
        tv_initial.setText((getResources().getString(R.string.airmedia)+""));
        airmediastatus.setStatus(true);
    }

    @Override
    public void airmediaNotReady()
    {
        Log.i("steward","@airmediaNotReady: airmedia is not ready");
        airmediastatus.setStatus(false);
    }

    @Override
    public void appInstalled(String s)
    {
        Log.i("steward","installed: "+s);
    }

    @Override
    public void appUnInstalled(String s) {
        Log.i("steward","@appUnInstalled: uninstalled");
    }

    @Override
    public void setPlatform(boolean show, String platform) {
        try
        {

            if(show)
            {
                Fragment fragment1= (CarlsonAirMediaGrid)airmediaGrid;
                getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
                instructions=CarlsonAirMediaInstructions.get(platform);
                isDisplaying=true;
                tv_initial.setText("");
                btn_show.setVisibility(platform.equals("ANDROID")?View.VISIBLE:View.GONE);
                getSupportFragmentManager().beginTransaction().add(R.id.ll_display,instructions,"instructions").commit();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setContent() {
        airmediastatus.setStatus(true);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("steward","clicked");
                new CarlsonAirMediaQRCode().show(getSupportFragmentManager(),"dialog");
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
