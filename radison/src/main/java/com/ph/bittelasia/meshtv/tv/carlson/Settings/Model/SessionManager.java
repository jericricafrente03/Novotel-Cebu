package com.ph.bittelasia.meshtv.tv.carlson.Settings.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.ph.bittelasia.meshtv.tv.carlson.R;

/**
 * Created by ramil on 11/28/17.
 */

public class SessionManager
{

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME       = "Carlson";


    //------------------------Constructor----------------------------------------------
    public SessionManager(Context context)
    {
        this._context=context;
        pref=_context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor=pref.edit();
    }

    //--------------------------Setters------------------------------------------------

    public void setRoomNumber(String roomNumber)
    {
        editor.putString(Content.ROOM_NUMBER.str(),roomNumber);
        editor.commit();
    }

    public void setGuestName(String guestName)
    {
        editor.putString(Content.GUEST_NAME.str(),guestName);
        editor.commit();
    }

    public void setDateToday(String dateToday)
    {
        editor.putString(Content.DATE_TODAY.str(),dateToday);
        editor.commit();
    }
    public void setTemperature(String temperature)
    {
        editor.putString(Content.TEMPERATURE.str(),temperature);
        editor.commit();
    }
    public void setLocation(String location)
    {
        editor.putString(Content.LOCATION.str(),location);
        editor.commit();
    }

    //-------------------------Getters------------------------------------------------

    public String getRoomNumber()
    {
        return pref.getString(Content.ROOM_NUMBER.str(),_context.getResources().getString(R.string.room));
    }

    public String getGuestName()
    {
        return pref.getString(Content.GUEST_NAME.str(),_context.getResources().getString(R.string.guest));
    }

    public String getDateToday()
    {
        return pref.getString(Content.DATE_TODAY.str(),_context.getResources().getString(R.string.date));
    }
    public String getTemperature()
    {
        return pref.getString(Content.TEMPERATURE.str(),_context.getResources().getString(R.string.temperature));
    }

    public String getLocation()
    {
        return pref.getString(Content.LOCATION.str(),_context.getResources().getString(R.string.location));
    }
}