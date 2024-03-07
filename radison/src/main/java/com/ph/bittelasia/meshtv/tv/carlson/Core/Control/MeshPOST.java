//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ph.bittelasia.meshtv.tv.carlson.Core.Control;

import android.content.Context;
import android.util.Log;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.API.Listeners.MeshParamListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Manager.MeshTVPreferenceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.App.MeshTVApp;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;

final class MeshPOST {
    static final String TAG = MeshPOST.class.getSimpleName();
    static final String DIR = "/index.php/apicall/";
    static final String REGISTER = "stb_register";
    static final String SUBSCRIBE = "device_register";

    MeshPOST() {
    }

    public final String post(Context context, String api, MeshParamListener listener) {
        String result = null;
        if (context == null) {
            context = MeshTVApp.get().getApplicationContext();
        }

        String source = MeshTVPreferenceManager.getHTTPHost(context) + ":" + MeshTVPreferenceManager.getHTTPPort(context) + "/index.php/apicall/" + api;
        source = listener.requestParams(source);

        try {
            Log.i(TAG, "POSTING:" + source.replaceAll("\\n", ""));
            URL url = new URL(source.replaceAll("\\n", ""));
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(MeshTVPreferenceManager.getTimeOut(context));
            conn.setConnectTimeout(MeshTVPreferenceManager.getConnectionTimeout(context));
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.flush();
            writer.close();
            os.close();
            StringBuilder output = new StringBuilder("Request URL " + url);
            HashMap<String, String> params = new HashMap();
            output.append(System.getProperty("line.separator") + "Request Parameters " + this.getQuery(params));
            output.append(System.getProperty("line.separator") + "Response Code " + conn.getResponseCode());
            output.append(System.getProperty("line.separator") + "Type POST");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            StringBuilder responseOutput = new StringBuilder();

            while((line = br.readLine()) != null) {
                responseOutput.append(line);
            }

            result = responseOutput.toString();
            return result;
        } catch (Exception var15) {
            var15.printStackTrace();
            return var15.getMessage();
        }
    }

    private String getQuery(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator var4 = params.keySet().iterator();

        while(var4.hasNext()) {
            String key = (String)var4.next();
            if (first) {
                first = false;
            } else {
                result.append("&");
            }

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode((String)params.get(key), "UTF-8"));
        }

        return result.toString();
    }
}
