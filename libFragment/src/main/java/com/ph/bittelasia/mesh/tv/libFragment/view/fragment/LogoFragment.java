package com.ph.bittelasia.mesh.tv.libFragment.view.fragment;

import android.view.View;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.HashMap;

public abstract class LogoFragment extends BasicFragment {

    private ImageView logoView;
    private String logoPath;

    @Override
    public void getView(View v) {
        logoView = v.findViewById(getLogoViewID());
        updateDetails();
    }

    public void updateDetails()
    {
        if(getActivity()!=null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(getLogoPath()!=null)
                        Picasso.get().load(getLogoPath()).into(getLogoView());
                }
            });
        }
    }

    public void setLogoView(ImageView logoView) {
        this.logoView = logoView;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public ImageView getLogoView() {
        return logoView;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public abstract int getLogoViewID();

}
