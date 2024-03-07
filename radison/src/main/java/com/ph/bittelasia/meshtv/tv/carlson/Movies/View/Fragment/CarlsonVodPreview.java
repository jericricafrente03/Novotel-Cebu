package com.ph.bittelasia.meshtv.tv.carlson.Movies.View.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Activity.CarlsonFullScreen;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog.CarlsonConfirm;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog.MeshTVDialog;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Fragment.CarlsonPreview;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Manager.MeshTVPreferenceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVOD;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVODListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVODManager;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by ramil on 2/8/18.
 */

public class CarlsonVodPreview extends MeshTVDialog implements MeshTVFragmentListener{

    public static final String TAG=CarlsonVodPreview.class.getSimpleName();

    Animation zoomIn;

    double percentageWidth;
    double percentageHeight;

    CarlsonPreview preview;

    FrameLayout  ll_preview;
    TextView     tv_title;
    TextView     tv_price;
    TextView     tv_desc;
    TextView     tv_yes;
    TextView     tv_no;
    Button       btn_rent;
    Button       btn_close;
    LinearLayout   ll_yes;
    LinearLayout   ll_no;
    boolean      rented;

    MeshVOD vod;

    public static CarlsonVodPreview d;

    public static CarlsonVodPreview dialog(MeshVOD vod,double percentageWidth,double percentageHeight)
    {
        d=new CarlsonVodPreview();
        d.vod=vod;
        d.percentageWidth=percentageWidth;
        d.percentageHeight=percentageHeight;   //MeshTVDemoFileReader.getMediaPath();

        return d;
    }

    final View.OnClickListener click=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.ll_yes:
                case R.id.btn_rent:
                    if(!rented) {
                        CarlsonConfirm.dialog(vod, .35, .30).show(getChildFragmentManager(), "confirm");
                    }else{
                        Intent i = new Intent(getContext(), CarlsonFullScreen.class);
                        i.putExtra("VOD", vod.getId());
                        i.putExtra("MEDIA", "VOD");
                        startActivityForResult(i, 100);
                        dismissAllDialogs(getFragmentManager());
                    }
                    break;
                case R.id.ll_no:
                case R.id.btn_close:
                    dismiss();
                    break;
            }
        }
    };

    @Override
    public void setIDs(View view) {
        zoomIn = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
        ll_preview= view.findViewById(R.id.ll_preview);
        tv_title  = view.findViewById(R.id.tv_title);
        tv_price  = view.findViewById(R.id.tv_price);
        tv_desc   = view.findViewById(R.id.tv_desc);
        tv_yes  = view.findViewById(R.id.tv_yes);
        tv_no   = view.findViewById(R.id.tv_no);
        btn_rent  = view.findViewById(R.id.btn_rent);
        btn_close = view.findViewById(R.id.btn_close);
        ll_yes=view.findViewById(R.id.ll_yes);
        ll_no=view.findViewById(R.id.ll_no);
        btn_rent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                checkImage(view,b,zoomIn);
            }
        });
        btn_close.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                checkImage(view,b,zoomIn);
            }
        });
        btn_rent.setFocusable(true);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setContent() {
        try {
            tv_title.setText(vod.getTitle());
            DecimalFormat df = new DecimalFormat(MeshTVPreferenceManager.getHotelCurrency(getActivity()) + " ###,##0.00");
            tv_price.setText(df.format(vod.getPrice()));
            tv_desc.setText(Html.fromHtml(vod.getPlot()));
            btn_rent.setOnClickListener(click);
            btn_close.setOnClickListener(click);
            ll_yes.setOnClickListener(click);
            ll_no.setOnClickListener(click);
            preview = CarlsonPreview.preview(vod, 0);
            getChildFragmentManager().beginTransaction().add(R.id.ll_preview, preview, "container").commit();
            MeshVODManager.isRented(new MeshVODListener() {
                @Override
                public void onBought(final boolean b) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (b)
                            {
                                btn_rent.setText(("WATCH FULL"));
                                tv_yes.setText(("WATCH FULL"));
                                rented=true;
                            }
                            else
                            {
                                btn_rent.setText(("RENT"));
                                tv_yes.setText(("RENT"));
                                rented=false;
                            }
                        }
                    });
                }
                @Override
                public void onClear() {

                }
                @Override
                public void bulkBought(ArrayList<Integer> arrayList) {

                }
            },vod.getId());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int setLayout() {
        return R.layout.carlson_vod_preview_dialog;
    }

    @Override
    public int setConstraintLayout() {
            return R.id.cl_layout;
    }

    @Override
    public double setPercentageWidth() {
        return percentageWidth;
    }

    @Override
    public double setPercentageHeight() {
        return percentageHeight;
    }

    @Override
    public void onClicked(Object o) {
        if(o instanceof String )
        {
            if(((String)o).equals(getActivity().getResources().getString(R.string.yes)))
            {
                getDialog().dismiss();
            }
        }
    }

    @Override
    public void onSelected(Object o) {
        Log.i(TAG,"selected: ");
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


}
