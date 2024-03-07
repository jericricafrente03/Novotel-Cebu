package com.ph.bittelasia.meshtv.tv.carlson.Movies.View.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ph.bittelasia.meshtv.tv.carlson.Core.View.Activity.CarlsonActivity;
import com.ph.bittelasia.meshtv.tv.carlson.Movies.View.Fragment.CarlsonMyVideo;
import com.ph.bittelasia.meshtv.tv.carlson.Movies.View.Fragment.CarlsonVodCategory;
import com.ph.bittelasia.meshtv.tv.carlson.Movies.View.Fragment.CarlsonVodList;
import com.ph.bittelasia.meshtv.tv.carlson.Movies.View.Fragment.CarlsonVodPreview;
import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshArrayListCallBack;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshTVButtons;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.PlayBackState;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Activity.CarlsonFullScreen;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog.CarlsonAddToCart;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog.CarlsonConfirm;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog.CarlsonSearch;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog.CarlsonViewCart;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Fragment.CarlsonButtons;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Fragment.CarlsonPreview;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.ActivitySetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Activity.AttachFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.Broadcast.MeshTVBroadcaster;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.RemoteControl.KR301KeyCode;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshGenre;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVOD;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVODListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.VOD.MeshVODManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Transaction.Model.MeshTVCart;

import java.util.ArrayList;

/**
 * Created by ramil on 1/23/18.
 */
@Layout(R.layout.carlson_vod)
@ActivitySetting()
public class CarlsonVod extends CarlsonActivity implements MeshTVFragmentListener,MeshArrayListCallBack<MeshVOD> {

    public static final String TAG=CarlsonVod.class.getSimpleName();

    CarlsonVodCategory      category;
    CarlsonVodList          list;
    CarlsonButtons          buttons;


    MeshGenre               meshCategory;
    MeshVOD                 item;
    ArrayList<MeshVOD>      itemsList;
    boolean                 loaded=false;
    boolean                 firstLoad=false;
    int                     updated=0;


    @BindWidget(R.id.tv_maintitle)
    public TextView tv_sub;

    @AttachFragment(container = R.id.ll_categories,tag="category",order=3)
    public Fragment attachCategory()
    {

        category=new CarlsonVodCategory();
        return category;
    }

    @AttachFragment(container = R.id.ll_display,tag="list",order = 4)
    public Fragment attachList()
    {
        list =new CarlsonVodList();
        return list;
    }

    @AttachFragment(container = R.id.ll_bottom,tag = "bottom",order = 5)
    public Fragment attachButtons()
    {
        buttons=new CarlsonButtons();
        return buttons;
    }

    @Override
    public void onResume() {
        super.onResume();
        loaded=false;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        tv_sub.setText(("MOVIES"));
        buttons.setVisibility(buttons.btn_myvideos);
        buttons.btn_myvideos.setText("MY VIDE0S");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100)
        {
            switch (resultCode)
            {
                case RESULT_OK:
                    break;
            }
        }
    }

    @Override
    public void onClicked(Object o) {

        try
        {
            if (o instanceof String)
            {
                if (((String) o).equals(MeshTVButtons.VIEWORDERS.getButton()))
                {
                    CarlsonViewCart.dialog(MeshTVCart.display(MeshVOD.class), .70, .70).show(getSupportFragmentManager(), "view");
                }
            }
            if(o instanceof MeshVOD)
            {
                item = (MeshVOD)o;
                CarlsonVodPreview.dialog(item,.70,.90).show(getSupportFragmentManager(),"confirm");
            }
            if (o instanceof MeshGenre)
            {
                meshCategory=(MeshGenre)o;
                if(meshCategory.getId()==1)
                    list.setCategory(null);
                else
                    list.setCategory(meshCategory.getGenre());
            }
            if(o instanceof String)
            {
                if (((String)o).equals(MeshTVButtons.SEARCH.getButton()))
                {
                    CarlsonSearch.dialog(itemsList, .50, .55).show(getSupportFragmentManager(), "search");
                }
                else if (((String)o).equals(MeshTVButtons.MYVIDEOS.getButton()))
                {
                    CarlsonMyVideo.dialog(itemsList, .50, .55).show(getSupportFragmentManager(), "myvideos");
                }
                else if (((String)o).equals(MeshTVButtons.RENT.getButton()))
                {
                    CarlsonConfirm.dialog(item, .35, .30).show(getSupportFragmentManager(), "confirm");
                }
                else if(((String)o).equals(MeshTVButtons.PREVIEW.getButton()))
                {
                    Intent i = new Intent(this, CarlsonFullScreen.class);
                    i.putExtra("VOD",item.getId());
                    i.putExtra("MEDIA","VOD");
                    startActivityForResult(i, 100);
                }
            }
            if(o instanceof Integer)
            {
                Intent i = new Intent(this, CarlsonFullScreen.class);
                i.putExtra("VOD",(Integer)o);
                i.putExtra("MEDIA","VOD");
                startActivityForResult(i, 100);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onSelected(Object o) {
        try
        {
            if(!loaded)
            {
                if (o instanceof MeshGenre) {
                    meshCategory = (MeshGenre) o;
                    if (list != null) {
                        if(meshCategory.getId()==1)
                            list.setCategory(null);
                    }
                    loaded=true;
                    Log.i("steward", "vod notified ok");
                }
            }
            else
            {
                if(list.adapter!=null) {
                    list.adapter.notifyDataSetChanged();
                    list.gv_grid.invalidateViews();
                    Log.i("steward", "vod notified");
                }
            }

            if (o instanceof Integer)
            {
                list.gv_grid.setSelection(0);
                if(((Integer)o)==1)//bought
                {
                    if(item!=null)
                    {
                        CarlsonConfirm.dialog(item,.35,.30).show(getSupportFragmentManager(),"confirm");
                    }
                }
                Log.i(TAG,"selected: ");
            }
            if(o instanceof TextView)
            {
                ((TextView)o).setTextColor(Color.RED);
            }
            if(o instanceof Boolean)
            {
                if(((Boolean)o))
                {
                    list.setCategory(meshCategory.getGenre());
                }
                else
                {
                    list.adapter.notifyDataSetChanged();
                    list.gv_grid.invalidateViews();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void meshArrayList(ArrayList<MeshVOD> list) {
        if(!firstLoad) {
            itemsList = list;
            firstLoad=true;
        }
    }
}
