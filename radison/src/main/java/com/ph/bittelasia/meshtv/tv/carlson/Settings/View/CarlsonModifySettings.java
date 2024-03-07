package com.ph.bittelasia.meshtv.tv.carlson.Settings.View;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ph.bittelasia.meshtv.tv.carlson.Launcher.Model.LauncherCallback;
import com.ph.bittelasia.meshtv.tv.carlson.Launcher.View.Activity.CarlsonLauncher;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Settings.Model.SessionManager;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.Packages;
import com.ph.bittelasia.meshtv.tv.meshinstallerinterface.MeshPackageManager;


import java.util.ArrayList;

/**
 * Created by ramil on 11/28/17.
 */

public class CarlsonModifySettings extends DialogFragment
{

    public static CarlsonModifySettings dialog;
    SessionManager   session;
    int              index=0;
    String           roomNumber;
    String           guestName;
    String           temp;
    String           location;



    EditText         ed_roomnumber;
    EditText         ed_guestname;
    EditText         ed_temp;
    EditText         ed_location;
    Button           btn_modify;
    ConstraintLayout cl_layout;

    LauncherCallback  launcher;

    public LauncherCallback getLauncher() {
        return launcher;
    }

    public void setLauncher(LauncherCallback launcher) {
        this.launcher = launcher;
    }

    public static CarlsonModifySettings  dialog(LauncherCallback callback)
    {
        dialog=new CarlsonModifySettings();
        dialog.setLauncher(callback);
        return dialog;
    }

    public void setIDs(View view)
    {

        ed_roomnumber    =   (EditText)view.findViewById(R.id.ed_roomnumber);
        ed_guestname     =   (EditText)view.findViewById(R.id.ed_guestname);
        ed_temp          =   (EditText)view.findViewById(R.id.ed_temp);
        ed_location      =   (EditText)view.findViewById(R.id.ed_location);
        btn_modify       =   (Button)view.findViewById(R.id.btn_modify);
        cl_layout        =   (ConstraintLayout) view.findViewById(R.id.cl_layout);
    }

    public void setEvents()
    {

        session=new SessionManager(getActivity());

        ed_guestname.setText(session.getGuestName());
        ed_roomnumber.setText(session.getRoomNumber());
        ed_temp.setText(session.getTemperature());
        ed_location.setText(session.getLocation());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        cl_layout.setMinWidth((int)(width * .50));
        cl_layout.setMinHeight((int)(height * .40));

        btn_modify.setOnTouchListener(touchListener);
        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContent();
            }
        });
    }

    public void getContent()
    {
      if(check(ed_roomnumber))
          setRoomNumber(getText(ed_roomnumber));
      if(check(ed_guestname))
          setGuestName(getText(ed_guestname));
      if(check(ed_temp))
          setTemp(getText(ed_temp));
      if(check(ed_location))
          setLocation(getText(ed_location));
      if(check(ed_roomnumber) && check(ed_guestname) && check(ed_temp) && check(ed_location))
      {
          if(!(session.getGuestName().toUpperCase().equals(getGuestName().toUpperCase())))
          {
              MeshPackageManager.uninstall(getActivity(), Packages.FACEBOOK);
              MeshPackageManager.uninstall(getActivity(), Packages.ENEWS);
              MeshPackageManager.uninstall(getActivity(), Packages.NETFLIX);
              MeshPackageManager.uninstall(getActivity(), Packages.SPOTIFY);
              MeshPackageManager.uninstall(getActivity(), Packages.TWITTER);
          }
          session.setRoomNumber(getRoomNumber());
          session.setGuestName(getGuestName());
          session.setTemperature(getTemp());
          session.setLocation(getLocation());
          if (launcher != null) {
              launcher.setGuest();
          }
          dismiss();
//          startActivity(new Intent(getActivity(), CarlsonLauncher.class));
      }
    }

    public boolean check(EditText view)
    {
        boolean checked=false;
        if(view.getText().toString().isEmpty()||view.getText().toString().length() < 1)
        {
            view.setError("INVALID");
            checked=false;
        }
        else
        {
            view.setError(null);
            checked=true;
        }
        return checked;
    }
    public String getText(EditText view)
    {
        return view.getText().toString();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.carlson_settings_layout,container,false);
        setIDs(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        getDialog().setCanceledOnTouchOutside(true);
        setEvents();
    }

    final View.OnTouchListener touchListener=new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                switch (view.getId())
                {
                    case R.id.btn_modify:
                        view.performClick();
                        break;
                }
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                view.requestFocus();
            }
            return false;
        }
    };

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }


    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
