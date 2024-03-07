package com.ph.bittelasia.mesh.tv.libFragment.view.dialog;


import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ph.bittelasia.mesh.tv.libFragment.R;
import com.ph.bittelasia.mesh.tv.libFragment.view.fragment.CustomDialog;


public abstract class MoviePreviewDialogFragment extends CustomDialog implements View.OnFocusChangeListener, View.OnClickListener {


    private Animation zoomIn;
    private FrameLayout ll_preview;
    private TextView     tv_title;
    private TextView     tv_price;
    private TextView     tv_desc;
    private TextView     tv_yes;
    private TextView     tv_no;
    private Button       btn_rent;
    private Button       btn_close;
    private LinearLayout   ll_yes;
    private LinearLayout   ll_no;
    private boolean      rented;

    @Override
    public void setIDs(View view) {
        ll_preview = view.findViewById(getLayoutPreviewID());
        tv_title = view.findViewById(getTitleViewID());
        tv_price = view.findViewById(getPriceViewID());
        tv_desc = view.findViewById(getDescViewID());
        tv_yes = view.findViewById(getYESViewID());
        tv_no = view.findViewById(getNOViewID());
        btn_rent = view.findViewById(getRENTViewID());
        btn_close = view.findViewById(getCLOSEViewID());

    }

    @Override
    public void setContent() {
        zoomIn = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
        btn_rent.setOnFocusChangeListener(this);
        btn_rent.setOnFocusChangeListener(this);
        ll_yes.setOnClickListener(this);
        ll_no.setOnClickListener(this);
        btn_rent.setOnClickListener(this);
        btn_close.setOnClickListener(this);
        btn_rent.setFocusable(true);
    }

    @Override
    public void onClick(View view) {
       if(view.getId()==getNOViewID() || view.getId() ==getCLOSEViewID()) {
                dismiss();
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
            checkImage(view,b,zoomIn);
    }



    public void checkImage(final View view, Boolean b, Animation animation)
    {
        if(b)
        {
            view.startAnimation(animation);
        }
        else
        {
            view.clearAnimation();
        }

    }


    public abstract int getLayoutPreviewID();
    public abstract int getTitleViewID();
    public abstract int getPriceViewID();
    public abstract int getDescViewID();
    public abstract int getYESViewID();
    public abstract int getNOViewID();
    public abstract int getRENTViewID();
    public abstract int getCLOSEViewID();

}
