package com.ph.bittelasia.meshtv.tv.carlson.Airmedia.View.Fragment;

import android.content.Context;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.ph.bittelasia.meshtv.tv.carlson.Airmedia.Model.CarlsonAirMediaGridAdapter;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.AirMediaPlatform;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.PlatformCallBack;
import com.ph.bittelasia.meshtv.tv.mtvlib.AirMedia.Model.MeshAirmediaInstructions;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;

import java.util.ArrayList;

/**
 * Created by ramil on 12/29/17.
 */
@Layout(R.layout.carlson_airmedia_grid)
public class CarlsonAirMediaGrid extends MeshTVFragment {

    Animation animZoomIn;


    PlatformCallBack cb;

    @BindWidget(R.id.gv_air)
    public GridView gv_air;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cb=(PlatformCallBack)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        cb=null;
    }
    public void checkImage(final View view, Boolean b, Animation animation)
    {
        if(b)
        {
            view.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.carlson_image_item_selectedv1));
            view.startAnimation(animation);
            view.setTranslationZ(1);
        }
        else
        {
            view.setBackground(null);
            view.setPadding(0,0,0,0 );
            view.setTranslationZ(0);
            view.clearAnimation();
        }

    }
    //----------------global onfocuschangelistener------------------------------------------------

    final  View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            checkImage(view,b,animZoomIn);
        }
    };

    //---------------global onHoverListener------------------------------------------------------
    final View.OnHoverListener hover=new View.OnHoverListener() {
        @Override
        public boolean onHover(View view, MotionEvent motionEvent) {
            view.requestFocus();
            return false;
        }
    };


    protected void onDrawDone(View view) {
        animZoomIn = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
        ArrayList<AirMediaPlatform> list;
        list=new ArrayList<>();
        list.add(new AirMediaPlatform("ANDROID",R.drawable.and));
        list.add(new AirMediaPlatform("IOS",R.drawable.ios));
        list.add(new AirMediaPlatform("WINDOWS",R.drawable.windows));
        gv_air.setAdapter(new CarlsonAirMediaGridAdapter(getContext(),list));
        gv_air.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(i==0)
                {
                    cb.setPlatform(true, "ANDROID");
                }
                else if(i==1)
                {
                    cb.setPlatform(true,"IOS");
                }
                else
                {
                    cb.setPlatform(true,"WINDOWS");
                }
            }
        });
     for(int i=0;i< gv_air.getChildCount();i++)
     {
         gv_air.getChildAt(i).setFocusable(true);
         gv_air.getChildAt(i).setOnHoverListener(hover);
         gv_air.getChildAt(i).setOnFocusChangeListener(focusChangeListener);
     }
    }

    @Override
    protected void onDataUpdated(ArrayList arrayList) {

    }

    @Override
    protected void onNewData(Object o) {

    }

    @Override
    protected void onDataUpdated(Object o, int i) {

    }

    @Override
    protected void onDeleteData(int i) {

    }

    @Override
    protected void onClearData() {

    }

    @Override
    protected void onDataNotFound(Class aClass) {

    }

    @Override
    protected void refresh() {

    }

    @Override
    protected void update(Object o) {

    }
}
