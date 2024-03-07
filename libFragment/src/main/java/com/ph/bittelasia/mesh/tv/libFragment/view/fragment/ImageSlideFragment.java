package com.ph.bittelasia.mesh.tv.libFragment.view.fragment;

import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import java.util.HashMap;

public  abstract class ImageSlideFragment extends BasicFragment {

   private ImageSwitcher imageSwitcher;

    @Override
    public void getView(View v) {
        imageSwitcher = v.findViewById(getImageSwitcherID());
        updateDetails();
    }

    public void updateDetails(){
        getImageSwitcher().setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView myView = new ImageView(getContext());
                myView.setScaleType(ImageView.ScaleType.FIT_XY);
                myView.setLayoutParams(new
                        ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
                return myView;
            }
        });

        Animation in = AnimationUtils.loadAnimation(getContext(),android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(getContext(),android.R.anim.fade_out);
        getImageSwitcher().setInAnimation(in);
        getImageSwitcher().setOutAnimation(out);

        if(getActivity()!=null){
            Handler handler = new Handler();

        }
    }



    public void setImageSwitcher(ImageSwitcher imageSwitcher) {
        this.imageSwitcher = imageSwitcher;
    }
    public ImageSwitcher getImageSwitcher() {
        return imageSwitcher;
    }

    public abstract int getImageSwitcherID();
}
