package com.ph.bittelasia.meshtv.tv.carlson.Temp.Model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ramil on 12/28/17.
 */

public class SessionManager {


    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "INTERCONTINENTAL";
    private static final String KEY_POSITION="position";
    private static final String KEY_ID="id";
    private static final String KEY_RENT="rent";
    private static final String KEY_MOVIE_ID="movie_id";
    private static final String KEY_MESSAGE_ID="message_id";

    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setRent(int id, boolean rent)
    {
        editor.putInt(KEY_MOVIE_ID,id);
        editor.putBoolean(KEY_RENT,rent);
        editor.commit();
    }

    public void setPosition(long pos)
    {
        editor.putLong(KEY_POSITION,pos);
        editor.commit();
    }

    public void setKeyChange(long pos,int id){
        editor.putLong(KEY_POSITION,pos);
        editor.putInt(KEY_ID,id);
        editor.commit();
    }

    public void setMessageID(int id)
    {
        editor.putInt(KEY_MESSAGE_ID,id);
        editor.commit();
    }



    public long getPosition()
    {
        return pref.getLong(KEY_POSITION,0);
    }

    public int getID()
    {
        return pref.getInt(KEY_ID,0);
    }

    public boolean isRented(){ return  pref.getBoolean(KEY_RENT,false);}

    public int getMovieID(){return pref.getInt(KEY_MOVIE_ID,0);}

    public int getMessageID(){return pref.getInt(KEY_MESSAGE_ID,0);}
}