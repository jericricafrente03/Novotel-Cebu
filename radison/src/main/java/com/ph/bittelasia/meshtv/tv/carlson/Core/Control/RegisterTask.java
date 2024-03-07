package com.ph.bittelasia.meshtv.tv.carlson.Core.Control;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;

import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.API.Listeners.MeshParamListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.API.Listeners.MeshRequestListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.API.POST.MeshRegisterTask;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Manager.MeshTVPreferenceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.Util.MeshAPIKeyRetriever;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.App.MeshTVApp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterTask extends AsyncTask<Void, Void, String> implements MeshParamListener {
    public static final String TAG = MeshRegisterTask.class.getSimpleName();
    MeshRequestListener listener = null;
    Context context = null;

    public RegisterTask(Context context, MeshRequestListener listener) {
        if (context == null) {
            this.context = MeshTVApp.get().getApplicationContext();
        } else {
            this.context = context;
        }

        this.listener = listener;
    }

    protected String doInBackground(Void... voids) {
        MeshPOST post = new MeshPOST();
        return post.post(this.context, "stb_register", this);
    }

    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (this.listener != null) {
            this.listener.onResult(s);
        }

    }

    public String requestParams(String url) {
        url = url + "/" + MeshAPIKeyRetriever.getAPIKey(this.context).replaceAll(":", "").toLowerCase();
        url = url + "/" + getWifiMacAddress(this.context).replaceAll(":", "").toLowerCase();
        url = url + "/" + MeshTVPreferenceManager.getRoom(this.context);
        return url;
    }


    private static String convertToHash(String macAddress, String appVersion) {
        StringBuffer sb = null;
        String newMacAddr = macAddress.replace(":", "");
        newMacAddr = newMacAddr.toLowerCase();
        newMacAddr = newMacAddr + appVersion;
        newMacAddr = newMacAddr.replaceAll("\\n", "");

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(newMacAddr.getBytes());
            byte[] byteData = md.digest();
            sb = new StringBuffer();

            for(int i = 0; i < byteData.length; ++i) {
                sb.append(Integer.toString((byteData[i] & 255) + 256, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException var7) {
            var7.printStackTrace();
        }

        return sb.toString();
    }

    public static String getWifiMacAddress(Context context) {
        WifiManager wifiManager = (WifiManager)context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        assert wifiManager != null;
        wifiManager.setWifiEnabled(true);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        return wInfo.getMacAddress();
    }
}