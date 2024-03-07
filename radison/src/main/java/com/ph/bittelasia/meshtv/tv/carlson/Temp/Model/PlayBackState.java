package com.ph.bittelasia.meshtv.tv.carlson.Temp.Model;

public enum PlayBackState {
    STATE_IDLE(1),
    STATE_BUFFERING(2),
    STATE_READY(3),
    STATE_ENDED(4),
    STATE_ERROR(5);

    private int state;

    public int getState() {
        return state;
    }

    PlayBackState(int state)
    {
        this.state=state;
    }
}
