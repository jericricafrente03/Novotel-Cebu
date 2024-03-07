package com.ph.bittelasia.meshtv.tv.carlson.Core.App;

import com.ph.bittelasia.meshtv.tv.mtvlib.SignageV2.MeshScrollingMessage;

public interface CarlsonScrollingMessageUpdateListener
{
    public abstract void updateScrollingMessages(MeshScrollingMessage meshScrollingMessage);
    public abstract int getZone();
}
