package com.ph.bittelasia.mesh.tv.libFragment.view.fragment;

import android.graphics.Color;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import com.ph.bittelasia.mesh.tv.libFragment.control.model.Slide;
import com.ph.bittelasia.mesh.tv.libFragment.view.customview.SlideViewFlipper;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public abstract class SlideFragment extends BasicFragment implements SlideViewFlipper.OnViewFlipperListener {

    private Animation in;
    private Animation out;
    private SlideViewFlipper viewFlipper;
    private TextView headView;
    private TextView contentView;
    private ImageButton nextView;
    private ImageButton prevView;
    private ArrayList<Slide> list;
    private int index;


    @Override
    public void getView(View v) {
        viewFlipper = v.findViewById(getSlideViewFlipperID());
        nextView = v.findViewById(getNextViewID());
        prevView = v.findViewById(getPrevViewID());
        headView = v.findViewById(getHeadViewID());
        contentView = v.findViewById(getContentViewID());

        out = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left);
        in = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_out_right);

        nextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewFlipper != null) {
                    viewFlipper.showNext();
                }
            }
        });
        prevView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewFlipper != null) {
                    viewFlipper.showPrevious();
                }
            }
        });
    }

    @Override
    public void onViewFlipperIndex(final int index) {
        if(getActivity()!=null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    getHeadView().setText(getList().get(index).getName());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        getContentView().setText(Html.fromHtml(getList().get(index).getContent(), Html.FROM_HTML_MODE_COMPACT));
                    } else {
                        getContentView().setText(Html.fromHtml("<html><body>"+getList().get(index).getContent()+"</body></html>"));
                    }
                }
            });
        }
    }


    public void updateDetails() {
        try {
            if (getList() != null) {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                    getViewFlipper().removeAllViews();
                                    for (Slide o : getList()) {
                                        ImageView img = new ImageView(getContext());
                                        Picasso.get().load(o.getPath()).into(img);
                                        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                                        getViewFlipper().addView(img);
                                    }
                                    if(getList().size()>1) {
                                        getViewFlipper().currentDisplayedColor = Color.RED;
                                        getViewFlipper().defaultColor = Color.WHITE;
                                        getViewFlipper().yIndicatorPlace = 100;
                                        getViewFlipper().margin = 4;
                                        getViewFlipper().radius = 7;
                                        getViewFlipper().setInAnimation(in);
                                        getViewFlipper().setOutAnimation(out);
                                        getViewFlipper().setFlipInterval((getInterval() * 1000));
                                        getViewFlipper().startFlipping();
                                    }
                                    getViewFlipper().setListener(SlideFragment.this);
                                    getHeadView().setText(getList().get(getViewFlipper().getDisplayedChild()).getName());
                                    getContentView().setText(Html.fromHtml(getList().get(getViewFlipper().getDisplayedChild()).getContent()));
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setList(ArrayList<Slide> list) {
        this.list = list;
    }

    public void setHeadView(TextView headView) {
        this.headView = headView;
    }

    public void setContentView(TextView contentView) {
        this.contentView = contentView;
    }

    public void setViewFlipper(SlideViewFlipper viewFlipper) {
        this.viewFlipper = viewFlipper;
    }

    public SlideViewFlipper getViewFlipper() {
        return viewFlipper;
    }

    public ArrayList<Slide> getList() {
        return list;
    }

    public TextView getHeadView() {
        return headView;
    }

    public TextView getContentView() {
        return contentView;
    }

    public abstract int getInterval();
    public abstract int getSlideViewFlipperID();
    public abstract int getNextViewID();
    public abstract int getPrevViewID();
    public abstract int getHeadViewID();
    public abstract int getContentViewID();
}
