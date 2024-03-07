package com.ph.bittelasia.mesh.tv.libFragment.view.fragment;

import android.view.View;

public abstract class GuestFragment extends BasicFragment {

    private android.widget.TextView nameView;
    private android.widget.TextView roomView;

    private String name;
    private String roomNo;


    @Override
    public void getView(View v) {
        nameView = v.findViewById(getNameViewId());
        roomView = v.findViewById(getRoomViewId());
        updateDetails(false);
    }
    public void updateDetails(final boolean isReset)
    {
        try {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (!isReset) {
                                if (getName() == null || getName().equals(""))
                                    getNameView().setText(("Welcome"));
                                else
                                    getNameView().setText(getName());
                                if (getRoomNo() == null || getRoomNo().equals(""))
                                    getRoomView().setText("");
                                else
                                    getRoomView().setText(getRoomNo());
                            } else {
                                getNameView().setText("");
                                getRoomView().setText("");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public void setNameView(android.widget.TextView nameView) {
        this.nameView = nameView;
    }

    public void setRoomView(android.widget.TextView roomView) {
        this.roomView = roomView;
    }

    public android.widget.TextView getNameView() {
        return nameView;
    }

    public android.widget.TextView getRoomView() {
        return roomView;
    }

    public String getName() {
        return name;
    }
    public String getRoomNo() {
        return roomNo;
    }


    public abstract int getNameViewId();
    public abstract int getRoomViewId();

}
