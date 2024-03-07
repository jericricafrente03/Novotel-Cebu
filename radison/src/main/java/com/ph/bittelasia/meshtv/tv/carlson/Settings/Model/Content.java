package com.ph.bittelasia.meshtv.tv.carlson.Settings.Model;

/**
 * Created by ramil on 11/28/17.
 */

public enum Content
{
    //---------------String Enums--------------------------------------------

    ROOM_NUMBER("room_number"),
    GUEST_NAME("guest_name"),
    DATE_TODAY("date_today"),
    TEMPERATURE("temperature"),
    LOCATION("location");


    private String str;

    public String str()
    {
        return  str;
    }

    Content(String str)
    {
        this.str=str;
    }
}
