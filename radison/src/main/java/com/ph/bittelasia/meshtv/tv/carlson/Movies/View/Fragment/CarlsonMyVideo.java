package com.ph.bittelasia.meshtv.tv.carlson.Movies.View.Fragment;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.ph.bittelasia.meshtv.tv.carlson.Movies.Model.CarlsonViewSearchListAdapter;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog.MeshTVDialog;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVOD;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVODListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVODManager;

import java.util.ArrayList;

/**
 * Created by ramil on 1/29/18.
 */

public class CarlsonMyVideo extends MeshTVDialog {

    Animation                             zoomIn;
    Button                                btn_play;
    Button                                btn_back;
    ListView                              lv_myvideos;
    double                                percentageWidth;
    double                                percentageHeight;
    ArrayList<MeshVOD>                    cartList;
    ArrayList<MeshVOD>                    filteredList;
    CarlsonViewSearchListAdapter          adapter;

    MeshTVFragmentListener cb;
    int id;
    int vodId=0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cb=(MeshTVFragmentListener)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        cb=null;
    }

    public static CarlsonMyVideo v;

    public static CarlsonMyVideo dialog(ArrayList<MeshVOD> cartlist, double percentageWidth, double percentageHeight)
    {
        v=new CarlsonMyVideo();
        v.cartList=cartlist;
        v.percentageWidth=percentageWidth;
        v.percentageHeight=percentageHeight;
        return v;
    }

    final View.OnClickListener click=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.btn_play:
                    cb.onSelected(id);
                    break;
                case R.id.btn_back:
                    dismissAllowingStateLoss();
                    break;
            }
        }
    };

    public void setIDs(View view)
    {
        zoomIn = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
        btn_play    =view.findViewById(R.id.btn_play);
        btn_back    =view.findViewById(R.id.btn_back);
        lv_myvideos =view.findViewById(R.id.lv_myvideos);
        btn_play.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                checkImage(view,b,zoomIn);
            }
        });
        btn_back.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                checkImage(view,b,zoomIn);
            }
        });
    }

    @Override
    public void setContent()
    {
        try
        {

            filteredList = new ArrayList<>();
            btn_play.setOnClickListener(click);
            btn_back.setOnClickListener(click);
            if (cartList.size() > 0) {
                for (final MeshVOD vod : cartList) {
                    MeshVODManager.isRented(new MeshVODListener() {
                        @Override
                        public void onBought(final boolean b) {
                            try {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Log.i("steward", "vod id: " + vod.getId() + " -> " + b);
                                            if (b) {
                                                filteredList.add(vod);
                                                if (filteredList.size() > 0 && vod.getId() > vodId) {
                                                    adapter = new CarlsonViewSearchListAdapter(getContext(), R.layout.carlson_search_item_list, filteredList);
                                                    lv_myvideos.setTextFilterEnabled(true);
                                                    lv_myvideos.setAdapter(adapter);
                                                    lv_myvideos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                        @Override
                                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                            cb.onClicked(filteredList.get(i).getId());
                                                        }
                                                    });
                                                    lv_myvideos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                        @Override

                                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                            id = filteredList.get(i).getId();
                                                            view.findViewById(R.id.tv_play).setVisibility(View.VISIBLE);
                                                        }

                                                        @Override
                                                        public void onNothingSelected(AdapterView<?> adapterView) {
                                                        }
                                                    });
                                                    vodId = vod.getId();
                                                }
                                            }
                                        }catch (Exception e)
                                        {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onClear() {

                        }

                        @Override
                        public void bulkBought(ArrayList<Integer> arrayList) {

                        }
                    }, vod.getId());
                }
               setCancelable(true);
            }
        }
        catch (Exception e )
        {
            e.printStackTrace();
        }
    }

    @Override
    public int setLayout() {
        return R.layout.carlson_vod_myvid;
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
