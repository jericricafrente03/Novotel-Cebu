package com.ph.bittelasia.meshtv.tv.carlson.Launcher.View.Fragment;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ph.bittelasia.meshtv.tv.carlson.Core.App.Carlson;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Listener.MeshConfigListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Model.MeshConfig;
import com.squareup.picasso.Picasso;


public class CarlsonConfigFragment extends Fragment implements MeshConfigListener
{

    //=====================================Variable=================================================
    //-------------------------------------Constant-------------------------------------------------
    private static final String TAG = "CarlsonConfigFragment";
    //--------------------------------------View----------------------------------------------------
    public  ImageView iv_config;
    //=====================================Fragment=================================================
    //==============================================================================================

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.carlson_config,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            iv_config = view.findViewById(R.id.iv_config);
            MeshConfig config = new MeshConfig();
            config.update();
            Uri uri = Uri.parse("android.resource://"+getContext().getPackageName()+"/drawable/"+R.drawable.novotel_crop);
            Picasso.get().load(uri).into(iv_config);
            //Picasso.get().load(config.getHotel_logo()).into(iv_config);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((Carlson)Carlson.get()).setConfigListener(this);
    }

    @Override
    public void onHotelConfigurationChange(MeshConfig meshConfig) {
        if(getActivity()!=null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Picasso.get().load(meshConfig.getHotel_logo()).into(iv_config);
                }
            });
        }

    }
    //----------------------------------------------------------------------------------------------
    //==============================================================================================


}
