package com.ph.bittelasia.meshtv.tv.carlson.Facilities.View.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Widget.CustomViewFlipper;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshArrayListCallBack;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.DataSetting;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.General.Layout;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Annotation.View.Widget.BindWidget;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Listener.MeshDataListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Manager.MeshTVDataManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Data.Control.Parser.MeshParser;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.ResourceManager.MeshResourceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.Util.MeshRawReader;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Object.Query.MeshValuePair;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.View.Fragment.MeshTVFragment;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Control.MeshRealmManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacility;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ramil on 2/12/18.
 */

@Layout(R.layout.carlson_hotelinfo_display)
@DataSetting(listenToFacility = true)
public class CarlsonFacilityList extends MeshTVFragment<MeshFacility> implements MeshDataListener,MeshRealmListener {

    CustomViewFlipper vf;
    Context facilityContext;

    @BindWidget(R.id.vp_slide)
    public View vp_slide;

    @BindWidget(R.id.tv_head)
    public TextView tv_head;

    @BindWidget(R.id.tv_content)
    public TextView tv_content;

    @BindWidget(R.id.iv_logo)
    public ImageView iv_logo;

    @BindWidget(R.id.btn_left)
    public ImageButton btn_left;

    @BindWidget(R.id.btn_right)
    public ImageButton btn_right;



    MeshArrayListCallBack    cb;
    ArrayList<MeshFacility> lists;
    ArrayList<MeshFacility> filteredlists;

    public void setFacilityContext(Context facilityContext) {
        this.facilityContext = facilityContext;
    }

    public Context getFacilityContext() {
        return facilityContext;
    }

    final View.OnClickListener click=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.btn_left:
                    if(vf!=null)
                    {
                        vf.showPrevious();
                    }
                    break;
                case R.id.btn_right:
                    if(vf!=null)
                    {
                        vf.showNext();
                    }
                    break;
            }
        }
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        cb=(MeshArrayListCallBack)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        cb=null;
    }

    public ArrayList<MeshFacility> vods;

    int category = -1;
    MeshValuePair filter;

    public void setCategory(int category) {
        try {
            this.category = category;
//        if(category>0)
//        {
//            filter.setValue(category);
//            MeshRealmManager.retrieve(MeshFacility.class,this, filter);
//        }
//        else
//        {
//            MeshRealmManager.retrieve(MeshFacility.class,this);
//        }
//
            lists = new ArrayList<>();
            filteredlists = new ArrayList<>();
            if(vf!=null)
               vf.removeAllViews();
            String facilities = MeshRawReader.read(R.raw.get_all_facilities);
            lists.addAll(MeshParser.parseFacilities(facilities));
            for (MeshFacility o : lists) {
                ImageView img = new ImageView(getFacilityContext());
                //img.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId(((MeshFacility) o).getImg_uri())));
                Picasso.get().load(((MeshFacility) o).getImg_uri()).into(img);
                img.setScaleType(ImageView.ScaleType.FIT_START);
                if (o.getCategory_id() == category) {
                    filteredlists.add(((MeshFacility) o));
                    if(vf!=null)
                    vf.addView(img);
                }
            }
            if (vf != null) {
                vf.setFlipInterval(5000);
                vf.startFlipping();
                tv_head.setText(filteredlists.get(vf.getDisplayedChild()).getItem_name());
                tv_content.setText(Html.fromHtml(filteredlists.get(vf.getDisplayedChild()).getItem_description()));
                if (lists.size() > 0) {
                    switch (category) {
                        case 2:
                            iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__quorvus")));
                            break;
                        case 3:
                            iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__radissonblu")));
                            break;
                        case 4:
                            iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__radisson")));
                            break;
                        case 5:
                            iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__radissonred")));
                            break;
                        case 6:
                            iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__parkplaza")));
                            break;
                        case 7:
                            iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__parkinn")));
                            break;
                        case 8:
                            iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__countryinn")));
                            break;
                        default:
                             iv_logo.setImageDrawable(null);
                    }

                }
            }
//            btn_left.setOnClickListener(click);
//            btn_right.setOnClickListener(click);
            getListener().onSelected(filteredlists.get(0));
            cb.meshArrayList(filteredlists);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDrawDone(View v) {
        try {

//        filter = new MeshValuePair(MeshFacility.TAG_CATEGORY_ID,category);
//        filter.setString(false);
            vf = (CustomViewFlipper) vp_slide;
            vf.currentDisplayedColor = Color.RED;
            vf.defaultColor = Color.WHITE;
            vf.yIndicatorPlace = 100;
            vf.margin = 4;
            vf.radius = 7;

            /*----temporary-----*/

            lists = new ArrayList<>();
            filteredlists = new ArrayList<>();
            vf.removeAllViews();
            String facilities = MeshRawReader.read(R.raw.get_all_facilities);
            lists.addAll(MeshParser.parseFacilities(facilities));
            for (MeshFacility o : lists) {
                ImageView img = new ImageView(getFacilityContext());
                //img.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId(((MeshFacility) o).getImg_uri())));
                Picasso.get().load(((MeshFacility) o).getImg_uri()).into(img);
                img.setScaleType(ImageView.ScaleType.FIT_START);
                filteredlists.add(((MeshFacility) o));
                vf.addView(img);
            }
            if (vf != null) {
                vf.setFlipInterval(5000);
                vf.startFlipping();
                tv_head.setText(filteredlists.get(vf.getDisplayedChild()).getItem_name());
                tv_content.setText(Html.fromHtml(filteredlists.get(vf.getDisplayedChild()).getItem_description()));
                if (lists.size() > 0) {
                    switch (category) {
                        case 2:
                            iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__quorvus")));
                            break;
                        case 3:
                            iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__radissonblu")));
                            break;
                        case 4:
                            iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__radisson")));
                            break;
                        case 5:
                            iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__radissonred")));
                            break;
                        case 6:
                            iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__parkplaza")));
                            break;
                        case 7:
                            iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__parkinn")));
                            break;
                        case 8:
                            iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__countryinn")));
                            break;
                    }

                }
            }
            btn_left.setOnClickListener(click);
            btn_right.setOnClickListener(click);
            getListener().onSelected(filteredlists.get(0));
            cb.meshArrayList(filteredlists);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        /*-------------temporary-----------------*/
    }

    @Override
    protected void onDataUpdated(ArrayList<MeshFacility> items) {


    }

    @Override
    protected void onNewData(Object o) {

    }

    @Override
    protected void onDataUpdated(Object o, int index) {

    }

    @Override
    protected void onDeleteData(int index) {

    }

    @Override
    protected void onClearData() {

    }

    @Override
    protected void onDataNotFound(Class c) {

    }

    @Override
    protected void refresh() {
//        if(category<1)
//        {
//            MeshRealmManager.retrieve(MeshFacility.class,this);
//        }
//        else
//        {
//            MeshRealmManager.retrieve(MeshFacility.class,this,filter);
//        }
    }

    @Override
    protected void update(MeshFacility item) {

    }

    @Override
    public void onNoNetwork(Class c) {

    }

    @Override
    public void onNoData(Class c) {
//        MeshTVDataManager.requestData(MeshFacility.class,this);
    }

    @Override
    public void onParseFailed(Class c, String message) {

    }

    @Override
    public void onFileNotFound(Class c, String message) {

    }

    @Override
    public void onDataReceived(Class c, int size) {
        MeshRealmManager.retrieve(MeshFacility.class,this);
    }

    @Override
    public void onRetrieved(Class c, ArrayList<Object> results)
    {
//        lists = new ArrayList<>();
//        vf.removeAllViews();
//        for(Object o:results)
//        {
//            ImageView img=new ImageView(getContext());
//            img.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId(((MeshFacility)o).getImg_uri())));
//            img.setScaleType(ImageView.ScaleType.FIT_START);
//            lists.add(((MeshFacility)o));
//            vf.addView(img);
//        }
//        if(vf!=null)
//        {
//            vf.setFlipInterval(5000);
//            vf.startFlipping();
//            tv_head.setText(lists.get(vf.getDisplayedChild()).getItem_name());
//            tv_content.setText(Html.fromHtml(lists.get(vf.getDisplayedChild()).getItem_description()));
//            if(lists.size()>0)
//            {
//                switch(category)
//                {
//                    case 2:
//                        iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__quorvus")));
//                        break;
//                    case 3:
//                        iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__radissonblu")));
//                        break;
//                    case 4:
//                        iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__radisson")));
//                        break;
//                    case 5:
//                        iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__radissonred")));
//                        break;
//                    case 6:
//                        iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__parkplaza")));
//                        break;
//                    case 7:
//                        iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__parkinn")));
//                        break;
//                    case 8:
//                        iv_logo.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId("logo__countryinn")));
//                        break;
//                }
//
//            }
//        }
//        getListener().onSelected(lists.get(0));
//        cb.meshArrayList(lists);

    }

    @Override
    public void onFailed(Class c, String message) {

    }

    @Override
    public void onEmpty(Class c, String message)
    {
//        MeshTVDataManager.requestData(MeshFacility.class,this);
    }

    @Override
    public void onCleared(Class aClass) {

    }
}