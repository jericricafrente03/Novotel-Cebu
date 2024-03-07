package com.ph.bittelasia.meshtv.tv.carlson.Website.View.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ph.bittelasia.meshtv.tv.carlson.Launcher.Model.LauncherCallback;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Settings.Model.SessionManager;
import com.ph.bittelasia.meshtv.tv.carlson.Settings.View.CarlsonModifySettings;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.ActivitySetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.RemoteControl.KR301KeyCode;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Activity.MeshTVActivity;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ramil on 11/22/17.
 */
@SuppressLint("SetJavaScriptEnabled")
@Layout(R.layout.carlson_website_layout)
@ActivitySetting()
public class CarlsonWebsite extends MeshTVActivity{

    WebView webview;
    boolean loadingFinished = true;
    boolean redirect = false;
    int x=0;
    ProgressDialog dialog;


    public static final String TAG=CarlsonWebsite.class.getSimpleName();

//    public  void setGuest()
//    {
//        try {
//            SessionManager session = new SessionManager(this);
//            tv_roomNumber.setText(allCaps( "ROOM NO: "+session.getRoomNumber()));
//            tv_guestName.setText(allCaps( "GUEST: "+session.getGuestName()));
//            Date d = new Date();
//            tv_date.setText(allCaps(new SimpleDateFormat("d MMM yyyy • E", Locale.US).format(d)));
//            tv_tempLoc.setText(allCaps(session.getTemperature() + " °C " + session.getLocation()));
//
//        }catch (Exception e)
//        {
//            Log.i("STEWARD",TAG+"@setGuest 1");
//            e.printStackTrace();
//            Log.i("STEWARD",TAG+"@setGuest 2");
//        }
//    }

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

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        switch (event.getAction())
        {
            case KeyEvent.ACTION_DOWN:
                switch (KR301KeyCode.getEquivalent(event.getKeyCode()))
                {
                    case 173:case 135:
                    case KeyEvent.KEYCODE_PROG_BLUE:
                        return  true;
                }
                break;
            case KeyEvent.ACTION_UP:
                switch (KR301KeyCode.getEquivalent(event.getKeyCode()))
                {
                    case 173:
                    case KeyEvent.KEYCODE_PROG_BLUE:
                      //  CarlsonModifySettings.dialog(this).show(getFragmentManager(),"SETTINGS");
                        return true;
                }
                break;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        webview = (WebView)findViewById(R.id.wv_web);
        webView();
    }

    private void webView() {
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webview.setScrollbarFadingEnabled(false);
        webview.clearHistory();
        webview.clearCache(true);
        webview.clearFormData();
        webview.setWebViewClient(new myWebViewClient());
        webview.loadUrl("http://carlsonrezidor.com/");
    }
    @Override
    public void onBackPressed() {
        if(webview.canGoBack()) {
            webview.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public class myWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if(Uri.parse(url).getHost().endsWith("carlsonrezidor.com")) {
                return false;
            }
            view.loadUrl(url);
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//            view.getContext().startActivity(intent);
            return true;
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap facIcon) {
            loadingFinished = false;
            //SHOW LOADING IF IT ISNT ALREADY VISIBLE
            if (x == 0) {
                dialog = ProgressDialog.show(CarlsonWebsite.this, "Loading", "Preparing the Hotel Info app...", true);
                dialog.setCancelable(true);
                x++;
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if(!redirect){
                loadingFinished = true;
                dialog.dismiss();
            }

            if(loadingFinished && !redirect){
                //HIDE LOADING IT HAS FINISHED
                dialog.dismiss();
                loadingFinished = true;
            } else{
                redirect = false;
                dialog.dismiss();
            }

        }
    }

}
