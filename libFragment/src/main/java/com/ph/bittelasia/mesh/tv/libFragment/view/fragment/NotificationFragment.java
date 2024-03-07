package com.ph.bittelasia.mesh.tv.libFragment.view.fragment;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.HashMap;

public abstract class NotificationFragment extends BasicFragment {

    private static final String TAG = "NotificationFragment";

    private Drawable statusDrawable;
    private String count;
    private String message;
    private ImageView statusImageView;
    private TextView countView;
    private TextView messageView;
    private ImageView messageIconView;

    public abstract int getStatusImageViewID();
    public abstract int getCountViewID();
    public abstract int getMessageViewID();

    @Override
    public void getView(View v) {
        countView = v.findViewById(getCountViewID());
        statusImageView = v.findViewById(getStatusImageViewID());
        messageView = v.findViewById(getMessageViewID());
        updateDetails(false);
    }

   public void updateDetails(final boolean isReset){
        try {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            if(!isReset) {
                                if (getCount() != null)
                                    getCountView().setText(getCount());
                                if (getMessage() != null)
                                    getMessageView().setText(getMessage());
                                if(getStatusDrawable()!=null)
                                    getMessageIconView().setImageDrawable(getStatusDrawable());

                            }else {
                                getCountView().setText("");
                                getMessageView().setText("");
                                getMessageIconView().setImageDrawable(null);
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
   }


    public void setStatusDrawable(Drawable statusDrawable) {
        this.statusDrawable = statusDrawable;
    }


    public void setCount(String count) {
        this.count = count;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatusImageView(ImageView statusImageView) {
        this.statusImageView = statusImageView;
    }

    public void setCountView(TextView countView) {
        this.countView = countView;
    }


    public void setMessageView(TextView messageView) {
        this.messageView = messageView;
    }

    public void setMessageIconView(ImageView messageIconView) {
        this.messageIconView = messageIconView;
    }

    public Drawable getStatusDrawable() {
        return statusDrawable;
    }
    public String getCount() {
        return count;
    }

    public String getMessage() {
        return message;
    }

    public ImageView getStatusImageView() {
        return statusImageView;
    }

    public TextView getCountView() {
        return countView;
    }

    public TextView getMessageView() {
        return messageView;
    }

    public ImageView getMessageIconView() {
        return messageIconView;
    }
}
