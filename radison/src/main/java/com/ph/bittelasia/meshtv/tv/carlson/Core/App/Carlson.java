package com.ph.bittelasia.meshtv.tv.carlson.Core.App;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.ph.bittelasia.meshtv.tv.carlson.Core.Control.XMPPConnectTask;
import com.ph.bittelasia.meshtv.tv.meshxmpplibrary.Core.Control.Manager.MeshXMPPManager;
import com.ph.bittelasia.meshtv.tv.meshxmpplibrary.Core.Control.Preference.MeshXMPPPreference;
import com.ph.bittelasia.meshtv.tv.meshxmpplibrary.DataListener.MeshTVMessageListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.API.GET.ReportAnalytics;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.API.Listeners.MeshRequestListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.API.POST.MeshRegisterTask;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.Event.Init;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.Settings.AppInitSettings;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.Settings.AppSettings;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.Settings.DataMode;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.Settings.RealmEditorSettings;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Constant.AppDataSource;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Listener.MeshDataListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Manager.MeshTVDataManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshNotificationListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Listener.MeshConfigListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Manager.MeshTVPreferenceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Model.MeshConfig;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Model.MeshGuest;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Model.MeshWeatherLocal;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.App.MeshTVApp;
import com.ph.bittelasia.meshtv.tv.mtvlib.Data.Model.MeshData;
import com.ph.bittelasia.meshtv.tv.mtvlib.MeshOverlay.MeshOverlayService;
import com.ph.bittelasia.meshtv.tv.mtvlib.MeshOverlay.MeshPopUpService;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Bill.MeshBillV2;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Channel.MeshChannel;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Channel.MeshChannelCategory;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Dining.MeshFood;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Dining.MeshFoodCategory;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacility;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacilityCategory;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Message.MeshMessage;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VC.MeshVC;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VC.MeshVCCategory;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshGenre;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVOD;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Weather.MeshWeatherV2;

import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@AppInitSettings(updateUser = DataMode.UPDATE)
@AppSettings(dataSource = AppDataSource.RAW)
@RealmEditorSettings()
public class Carlson extends MeshTVApp implements MeshRequestListener,MeshDataListener,ConnectionListener,MeshTVMessageListener, MeshNotificationListener
{

    //==============================================Variable========================================
    //----------------------------------------------Constant----------------------------------------
    public static String TAG=Carlson.class.getSimpleName()+"-app";
    public static String TAG_RELOAD_SCROLL="RELOAD_SCROLL";
    public static Carlson app=null;
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------Instance----------------------------------------
    public boolean isPermissionGranted = false;
    public CarlsonListener listener = null;
    public MeshConfigListener configListener =null;
    public ArrayList<CarlsonScrollingMessageUpdateListener> xmppListener = null;
    public int counter =0;
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //==============================================MeshTVApp=======================================
    @Override
    public String getDB() {
        app = this;
        return Carlson.class.getSimpleName();
    }
    //==============================================================================================
    //================================================Init===========================================

    @Init(order = 1)
    public void create()
    {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            isPermissionGranted = true;
            xmppListener = new ArrayList<>();
            addListener(this);
            MeshTVPreferenceManager.updateIPTV(this);
        }else {
            isPermissionGranted=false;
        }

    }
    @Init(order = 2)
    public void register()
    {
        if(isPermissionGranted)
        if(getDataSourceSetting()== AppDataSource.SERVER)
        {
            new MeshRegisterTask(this,this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }
    @Init(order = 3)
    public void connectXMPP()
    {
        if(isPermissionGranted)
        if(getDataSourceSetting()==AppDataSource.SERVER)
        {
            MeshXMPPManager.setListener(this);
            new XMPPConnectTask(this,this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }
    @Init(order = 4)
    public void getGuest()
    {
        if(isPermissionGranted)
        if(getDataSourceSetting()==AppDataSource.SERVER)
        {
            MeshTVDataManager.requestData(MeshGuest.class,this);
            MeshTVDataManager.requestData(MeshConfig.class,this);
        }
    }
    @Init(order = 5)
    public void reportAnalytics()
    {
        if(isPermissionGranted)
        if(getDataSourceSetting()==AppDataSource.SERVER)
        {
            new ReportAnalytics(getApplicationContext(), new MeshRequestListener() {
                @Override
                public void onFailed(String s) {

                }

                @Override
                public void onResult(String s) {

                }
            }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }
    @Init(order = 6)
    public void updateMessages()
    {
        if(isPermissionGranted)
        if(getDataSourceSetting()==AppDataSource.SERVER)
        {
            MeshTVDataManager.requestData(MeshMessage.class);
        }
    }
    @Init(order = 8)
    public void updateWeather()
    {
        if(isPermissionGranted)
        if(getDataSourceSetting()==AppDataSource.SERVER)
        {
            MeshTVDataManager.requestData(MeshWeatherV2.class);
            MeshTVDataManager.requestData(MeshWeatherLocal.class,this);
        }
    }
    @Init(order = 9)
    public void updateData()
    {
        if(isPermissionGranted)
        if(getDataSourceSetting()==AppDataSource.SERVER)
        {

            MeshTVDataManager.requestData(MeshChannel.class);
            MeshTVDataManager.requestData(MeshChannelCategory.class);
            MeshTVDataManager.requestData(MeshFood.class);
            MeshTVDataManager.requestData(MeshFoodCategory.class);
            MeshTVDataManager.requestData(MeshVOD.class);
            MeshTVDataManager.requestData(MeshGenre.class);
            MeshTVDataManager.requestData(MeshVC.class);
            MeshTVDataManager.requestData(MeshVCCategory.class);
            MeshTVDataManager.requestData(MeshBillV2.class);
            MeshTVDataManager.requestData(MeshFacility.class);
            MeshTVDataManager.requestData(MeshFacilityCategory.class);

        }
    }
    @Init(order = 10)
    public void setMessage(){
        counter =1;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                MeshMessage message = new MeshMessage();
                message.setId(counter);
                message.setIs_new(true);

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                Date date = null;
                try {
                    date = parser.parse(String.valueOf(c));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
                String formattedDate = formatter.format(date);

                message.setMessg_datetime(formattedDate);
                message.setMessg_from("NOVOTEL");
                message.setMessg_status(0);
                message.setMessg_subject("GREETINGS");
                message.setMessg_text("<br>Dear <b>Pavan Gidvani</b>,\n" +
                        "<br><br>\n" +
                        "It is our pleasure to welcome you to <b>Novotel</b>. \n" +
                        "<br>\n" +
                        "The professional and friendly staffs at <b>Novotel</b> are committed to making your stay both enjoyable and comfortable.\n" +
                        "<br><br><br><br>\n" +
                        "Thank you for choosing <b>Novotel</b> again.");
                counter++;
                MeshRealmManager.insert(message);
                if(counter>3){
                    timer.purge();
                    timer.cancel();
                }
            }
        },9000,120000);

    }    //==============================================================================================
    //===========================================MeshRequestListener================================
    @Override
    public void onFailed(String s) {

    }
    @Override
    public void onResult(String s) {

    }
    //==============================================================================================
    //============================================MeshDataListener==================================
    @Override
    public void onNoNetwork(Class aClass)
    {

    }
    @Override
    public void onNoData(Class aClass)
    {

    }
    @Override
    public void onParseFailed(Class aClass, String s)
    {

    }
    @Override
    public void onFileNotFound(Class aClass, String s)
    {

    }
    @Override
    public void onDataReceived(Class aClass, int i)
    {

    }
    //==============================================================================================

    //===========================================XMPP===============================================
    @Override
    public void connected(XMPPConnection connection) {

    }

    @Override
    public void authenticated(XMPPConnection connection, boolean resumed) {

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
    public void reconnectingIn(int seconds) {

    }

    @Override
    public void reconnectionFailed(Exception e) {

    }

    @Override
    public void onNotify(final MeshMessage meshMessage)
    {
        switch (meshMessage.getMessg_type())
        {
                case MeshMessage.TYPE_SCROLLING:
                    Log.i(TAG,"Scrolling Notification");
                    Handler  handler = new Handler(getApplicationContext().getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(MeshTVApp.get().getApplicationContext(), MeshOverlayService.class);
                            MeshTVPreferenceManager.setScrollingMessage(MeshTVApp.get().getApplicationContext(),meshMessage.getMessg_text());
                            MeshTVPreferenceManager.setScrollTimeout(MeshTVApp.get().getApplicationContext(),meshMessage.getMessg_display_time());
                            startService(i);
                        }
                    });
                    break;
                    case MeshMessage.TYPE_POP_UP:
                        Handler  handler2 = new Handler(getApplicationContext().getMainLooper());
                        handler2.post(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(MeshTVApp.get().getApplicationContext(), MeshPopUpService.class);
                                MeshTVPreferenceManager.setScrollingMessage(MeshTVApp.get().getApplicationContext(),meshMessage.getMessg_text());
                                MeshTVPreferenceManager.setScrollTimeout(MeshTVApp.get().getApplicationContext(),meshMessage.getMessg_display_time());
                                MeshTVPreferenceManager.setNotificationIMGURL(MeshTVApp.get().getApplicationContext(),meshMessage.getMessg_img());
                                startService(i);
                            }
                        });
                        break;

                default:
                    Log.i(TAG,"Unknown Notification");
                    break;
        }


    }

    //==============================================================================================
    //==========================================XMPP Connection Task================================

    //==============================================================================================
    //=============================================Method===========================================
    //----------------------------------------Listener-Control--------------------------------------
    public void addListener(CarlsonScrollingMessageUpdateListener listener)
    {
        if(xmppListener==null)
        {
            xmppListener = new ArrayList<>();
        }
        xmppListener.add(listener);
    }
    public void removeListener(CarlsonScrollingMessageUpdateListener listener)
    {
        if(xmppListener!=null)
        {
            xmppListener.remove(listener);
        }
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //==========================================MeshMessageListener=================================
    @Override
    public void receiveMessage(String s, String s1)
    {
        Log.i(TAG,"@receiveMessage: "+s+"@"+s1);

        final MeshData data =new MeshData(s1);
        Log.i(TAG,"@receiveMessage: classname: "+data.getClass_name());

        if(data.getClass_name().equals(MeshData.CONFIG))
        {
            JSONObject rs = null;
            try {
                rs = new JSONObject(s1);
                MeshConfig config = new MeshConfig(rs.getString("data"));
                config.update();
                getConfigListener().onHotelConfigurationChange(config);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        else if(data.getClass_name().equals(MeshData.VIEW_BILL))
        {
            Log.i(TAG+"-@receiveMessage: ",s+":"+s1);
            if(MeshXMPPPreference.shouldReport(getApplicationContext()))
            {
                MeshXMPPManager.respond(s,s1);
            }
            data.updateData();
        }
        else
        {
            Log.i(TAG+"-@receiveMessage: ",data.getData()+"");
            if(MeshXMPPPreference.shouldReport(getApplicationContext()))
            {
                MeshXMPPManager.respond(s,s1);
            }
            data.updateData();
        }
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================

    //=======================================Method=================================================
    //----------------------------------------------------------------------------------------------
    public MeshConfigListener getConfigListener() {
        return configListener;
    }

    public void setConfigListener(MeshConfigListener configListener) {
        this.configListener = configListener;
    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================

}
