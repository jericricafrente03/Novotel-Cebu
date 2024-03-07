package com.ph.bittelasia.meshtv.tv.carlson.Temp.Model;

import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Message.MeshMessage;

import io.realm.Realm;

public abstract class MeshMessageRead
{
    public static int getUnreadMessage()
    {
        return Realm.getDefaultInstance().where(MeshMessage.class).equalTo("messg_status", Integer.valueOf(1)).findAll().size()+Realm.getDefaultInstance().where(MeshMessage.class).equalTo("messg_status", Integer.valueOf(0)).findAll().size();
    }
}
