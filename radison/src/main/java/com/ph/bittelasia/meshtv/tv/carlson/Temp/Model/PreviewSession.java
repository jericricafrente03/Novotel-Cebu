package com.ph.bittelasia.meshtv.tv.carlson.Temp.Model;

import java.util.HashMap;

/**
 * Created by ramil on 2/1/18.
 */

public class PreviewSession {

    private int    id;
    private long    position;
    private Object object;

    public HashMap<Integer,Long> vodList;
    public HashMap<Integer,Long> channelList;

    public PreviewSession(){}

    public PreviewSession(int id, long position, Object object)
    {
        this.id=id;
        this.position=position;
        this.object=object;
    }

    public int getId() {
        return id;
    }

    public long getPosition() {
        return position;
    }

    public Object getObject() {
        return object;
    }
}
