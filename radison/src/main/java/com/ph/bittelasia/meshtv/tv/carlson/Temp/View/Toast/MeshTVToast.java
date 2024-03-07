package com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Toast;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ph.bittelasia.meshtv.tv.carlson.R;


/**
 * Created by ramil on 2/1/18.
 */

public class MeshTVToast {

    private Activity context;
    private String  text;
    private int     layout;
    private int     duration;
    public Toast toast;


    public static MeshTVToast t;

    public Activity getContext() {
        return context;
    }

    public int getDuration() {
        return duration;
    }

    public String getText() {
        return text;
    }

    public int getLayout() {
        return layout;
    }

    public static MeshTVToast makeText(Activity context, int layout, String text, int duration)
    {
        t=new MeshTVToast();
        t.context=context;
        t.layout=layout;
        t.text=text;
        t.duration=duration;
        return t;
    }

    public void show()
    {
        LayoutInflater inflater = getContext().getLayoutInflater();

        View toastRoot = inflater.inflate(getLayout(), null);
        toast = new Toast(getContext());
        TextView tv_message=(TextView)toastRoot.findViewById(R.id.tv_message);
        tv_message.setText(getText());
        toast.setView(toastRoot);
        toast.setGravity(Gravity.TOP, 0, 450);
        toast.setDuration(getDuration());
        toast.show();
    }

}
