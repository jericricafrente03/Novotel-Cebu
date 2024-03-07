package com.ph.bittelasia.meshtv.tv.carlson.Launcher.View.Activity;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.ViewSwitcher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.ph.bittelasia.meshtv.tv.carlson.Core.App.Carlson;
import com.ph.bittelasia.meshtv.tv.carlson.Core.Control.XMPPConnectTask;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Activity.CarlsonActivity;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment.CarlsonHotelWeather;
import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Fragment.CarslonGuestInfo;
import com.ph.bittelasia.meshtv.tv.carlson.Launcher.View.Fragment.CarlsonConfigFragment;
import com.ph.bittelasia.meshtv.tv.carlson.Launcher.View.Fragment.CarlsonGridFragmentV2;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.Packages;
import com.ph.bittelasia.meshtv.tv.meshxmpplibrary.Core.Control.Manager.MeshXMPPManager;
import com.ph.bittelasia.meshtv.tv.meshxmpplibrary.DataListener.MeshTVMessageListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.API.Listeners.MeshRequestListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.API.POST.MeshRegisterTask;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.ActivitySetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.AttachFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Listener.MeshDataListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Manager.MeshTVDataManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Manager.MeshTVPreferenceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Model.MeshConfig;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Model.MeshGuest;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Bill.MeshBillV2;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Channel.MeshChannel;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Channel.MeshChannelCategory;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Dining.MeshFood;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Dining.MeshFoodCategory;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacility;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacilityCategory;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VC.MeshVC;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VC.MeshVCCategory;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshGenre;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVOD;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


@Layout(R.layout.carlson_launcher_layout)
@ActivitySetting()
public class CarlsonLauncher extends CarlsonActivity implements MeshDataListener,
        MeshRequestListener,
        MeshTVMessageListener,
        MeshTVFragmentListener,
        ConnectionListener
{

    //===================================Variable===================================================
    //-----------------------------------Constant---------------------------------------------------
    public static final String TAG=CarlsonLauncher.class.getSimpleName();
    @SuppressLint("StaticFieldLeak")
    public static CarlsonLauncher activity;
    private static final int STORAGE_PERMISSION_CODE = 101;
    //-----------------------------------Instance---------------------------------------------------
    private CarslonGuestInfo                guestInfoFragment;
    private CarlsonHotelWeather             hotelWeatherFragment;
    private CarlsonGridFragmentV2           gridListFragment;
    public ImageSwitcher                    iv_building;
    public VideoView                        vv_video;
    private String                          display;
    private boolean                         show=false;
    //----------------------------------------------------------------------------------------------
    private int ctr = 0;
    private Timer timer;
    private Handler h  = new Handler();
    private Runnable runnable = new Runnable()
    {
        @Override
        public void run()
        {
            if(ctr>6)
            {
                ctr = 0;
            }
            if(vv_video!=null){
                vv_video.setVisibility(View.GONE);
            }
            if(iv_building!=null)
            {
                switch (ctr)
                {
                    case 0:
                        iv_building.setImageDrawable(getResources().getDrawable(R.drawable.novotel1));
                        break;
                    case 1:
                        iv_building.setImageDrawable(getResources().getDrawable(R.drawable.novotel2));
                        break;
                    case 2:
                        iv_building.setImageDrawable(getResources().getDrawable(R.drawable.novotel3));
                        break;
                    case 3:
                        iv_building.setImageDrawable(getResources().getDrawable(R.drawable.novotel4));
                        break;
                    case 4:
                        iv_building.setImageDrawable(getResources().getDrawable(R.drawable.novotel5));
                        break;
                    case 5:
                        iv_building.setImageDrawable(getResources().getDrawable(R.drawable.novotel6));
                        break;
                }
                ctr++;
                h.postDelayed(this,5000);
            }
            else
            {
                h.postDelayed(this,1000);
            }
            if(ctr>=5){
                playPromotional();
            }

        }
    };

    public void playPromotional(){
        try{
           if(timer!=null){
             timer.cancel();
             timer.purge();
             timer=null;
           }
           vv_video.setVisibility(View.VISIBLE);
            vv_video.setVideoURI(Uri.parse("file:///storage/emulated/0/Android/promotional.mp4"));
            vv_video.start();
            vv_video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    vv_video.setVisibility(View.GONE);
                    vv_video.stopPlayback();
                    playSlide();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void playSlide(){
        try{
            if(timer==null) {
                timer = new Timer();
            }
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            if(vv_video!=null){
                                vv_video.setVisibility(View.GONE);
                            }
                            if(iv_building!=null)
                            {
                                switch (ctr)
                                {
                                    case 0:
                                        iv_building.setImageDrawable(getResources().getDrawable(R.drawable.novotel1));
                                        break;
                                    case 1:
                                        iv_building.setImageDrawable(getResources().getDrawable(R.drawable.novotel2));
                                        break;
                                    case 2:
                                        iv_building.setImageDrawable(getResources().getDrawable(R.drawable.novotel3));
                                        break;
                                    case 3:
                                        iv_building.setImageDrawable(getResources().getDrawable(R.drawable.novotel4));
                                        break;
                                    case 4:
                                        iv_building.setImageDrawable(getResources().getDrawable(R.drawable.novotel5));
                                        break;
                                    case 5:
                                        iv_building.setImageDrawable(getResources().getDrawable(R.drawable.novotel6));
                                        break;
                                }
                                ctr++;
                                if(ctr>=5)
                                {
                                    ctr = 0;
                                    playPromotional();
                                }
                            }
                        }
                    });

                }
            },0,2000);
        }catch (Exception e){
            e.printStackTrace();
        }

    }    //==============================================================================================


    //========================================LifeCycle=============================================
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onStart() {
        super.onStart();
        activity= this;
        timer= new Timer();
        Log.i(TAG, "onStart: ");
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        iv_building = (ImageSwitcher) findViewById(R.id.iv_building);
        vv_video = (VideoView) findViewById(R.id.vv_video);
        iv_building.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView myView = new ImageView(getApplicationContext());
                myView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                myView.setLayoutParams(new
                        ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
                return myView;
            }
        });

        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.fade_out);
        iv_building.setInAnimation(in);
        iv_building.setOutAnimation(out);
    }
    @Override
    public void onResume()
    {
        super.onResume();
        try {
          //  h.post(runnable);
            playSlide();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            Intent i = new Intent();
            i.setAction("android.intent.action.KILL");
            i.putExtra("package", Packages.DISPLAY_TV2);
            sendBroadcast(i);
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(h!=null)
        {
            h.removeCallbacks(runnable);
        }
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //====================================MeshTVFragmentListener====================================
    //----------------------------------------------------------------------------------------------
    @Override
    public void onClicked(Object o)
    {
        if(o instanceof String)
        {
            display=(String) o;
            if(vv_video!=null){
                vv_video.stopPlayback();
            }
            if(display.equals(Packages.DISPLAY_AIRMEDIA))
            {
                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(true);
                appLaunch(Packages.YOUTUBE);

            }
            else if(display.equals(Packages.DISPLAY_NETFLIX))
            {
                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                assert wifiManager != null;
                wifiManager.setWifiEnabled(true);
                appLaunch(Packages.NETFLIX);

            }
            else if(display.equals(Packages.DISPLAY_WEATHER))
            {
                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                assert wifiManager != null;
                wifiManager.setWifiEnabled(true);
                appLaunch(Packages.WEATHER);
            }
            else if(display.equals(Packages.DISPLAY_TV2))
            {
                try {
                    Intent i = new Intent();
                    i.setAction("android.intent.action.LAUNCH");
                    i.putExtra("package",Packages.DISPLAY_TV2 );
                    sendBroadcast(i);
                }catch (Exception e){
                    e.printStackTrace();

                }            }
            else
            {

                Log.i("steward", "class name: " + ((String) o));
                try
                {
                    Class<?> cls = Class.forName(((String) o));
                    appLaunch(cls);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public void onSelected(Object o) {

    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //=======================================MeshDataListener=======================================
    //----------------------------------------------------------------------------------------------
    @Override
    public void onNoNetwork(Class aClass) {

    }

    @Override
    public void onNoData(Class aClass) {

    }

    @Override
    public void onParseFailed(Class aClass, String s) {

    }

    @Override
    public void onFileNotFound(Class aClass, String s) {

    }

    @Override
    public void onDataReceived(Class aClass, int i) {

    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //========================================MeshRequestListener===================================
    //----------------------------------------------------------------------------------------------
    @Override
    public void onFailed(String s) {

    }

    @Override
    public void onResult(String s) {

    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //======================================MeshTvMessageListener===================================
    //----------------------------------------------------------------------------------------------
    @Override
    public void receiveMessage(String s, String s1) {

    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //======================================ConnectionListener======================================
    //----------------------------------------------------------------------------------------------
    @Override
    public void connected(XMPPConnection xmppConnection) {

    }

    @Override
    public void authenticated(XMPPConnection xmppConnection, boolean b) {

    }

    @Override
    public void connectionClosed() {

    }

    @Override
    public void connectionClosedOnError(Exception e) {

    }

    @Override
    public void reconnectionSuccessful() {

    }

    @Override
    public void reconnectingIn(int i) {

    }

    @Override
    public void reconnectionFailed(Exception e) {

    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //========================================MeshTVActivity========================================
    //----------------------------------------------------------------------------------------------
    @AttachFragment(container = R.id.ll_location,tag="location",order = 2)
    public Fragment attachHotelWeather()
    {
        hotelWeatherFragment =new CarlsonHotelWeather();
        return hotelWeatherFragment;
    }

    @AttachFragment(container = R.id.ll_guest,tag = "guest",order=3)
    public Fragment attachHomeWeather()
    {
        guestInfoFragment =new CarslonGuestInfo();
        return guestInfoFragment;
    }

    @AttachFragment(container = R.id.carlson_grid,tag="grid",order=0)
    public Fragment attachGrid()
    {
        gridListFragment =new CarlsonGridFragmentV2();
        return gridListFragment;
    }

    @AttachFragment(container = R.id.ll_config,tag="config",order=4)
    public Fragment attachConfig()
    {
        return new CarlsonConfigFragment();
    }

    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //=====================================Method===================================================
    //----------------------------------------------------------------------------------------------
    public void appLaunch(String packageName)
    {
        Intent i;
        i = getPackageManager().getLaunchIntentForPackage(packageName);
        if(i!=null)
        {
            String packageString = i.getComponent().getPackageName();
            String mainActivity = i.getComponent().getClassName();
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            i.setComponent(new ComponentName(packageString, mainActivity));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            startActivity(i);
        }
    }

    public void appLaunch(Class classname)
    {
        Intent i;
        i = new Intent(this, classname);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
}
