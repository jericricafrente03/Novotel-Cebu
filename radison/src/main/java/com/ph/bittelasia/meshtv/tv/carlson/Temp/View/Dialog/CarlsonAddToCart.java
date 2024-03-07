package com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Dialog;


import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ph.bittelasia.meshtv.tv.carlson.R;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.Model.MeshTVCartCompute;
import com.ph.bittelasia.meshtv.tv.carlson.Temp.View.Toast.MeshTVToast;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Listener.MeshTVFragmentListener;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Preference.Control.Manager.MeshTVPreferenceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Core.Control.Util.ResourceManager.MeshResourceManager;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Concierge.MeshConciergeRequestItem;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Concierge.MeshConciergeRequestService;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Dining.MeshFood;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Facility.MeshFacility;
import com.ph.bittelasia.meshtv.tv.mtvlib.Realm.Model.Shopping.MeshShoppingItem;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

/**
 * Created by ramil on 1/24/18.
 */

public class CarlsonAddToCart extends MeshTVDialog
{

    Animation zoomIn;


    Object object;

    MeshShoppingItem            meshShoppingItem;
    MeshFood                    meshfood;
    MeshFacility                meshFacility;
    MeshConciergeRequestService meshService;
    MeshConciergeRequestItem    meshItem;
    DecimalFormat df;
    double percentageWidth;
    double percentageHeight;

    EditText            et_quantity;
    TextView            tv_total;
    TextView            tv_main;
    TextView            tv_name;
    TextView            tv_price;
    TextView            tv_desc;
    ImageView           iv_image;
    Button              btn_add;
    Button              btn_minus;
    Button              btn_accept;
    Button              btn_cancel;


    double              price;

    public static CarlsonAddToCart d;

    public static CarlsonAddToCart dialog(Object o,double percentageWidth,double percentageHeight)
    {
        d=new CarlsonAddToCart();
        d.object=o;
        d.percentageWidth=percentageWidth;
        d.percentageHeight=percentageHeight;   //MeshTVDemoFileReader.getMediaPath();

        return d;
    }


    @Override
    public void setIDs(View view) {
        try
        {
            et_quantity   = view.findViewById(R.id.et_quantity);
            tv_total      = view.findViewById(R.id.et_total);
            tv_main       = view.findViewById(R.id.tv_main);
            tv_name       = view.findViewById(R.id.tv_name);
            tv_price      = view.findViewById(R.id.tv_price);
            tv_desc       = view.findViewById(R.id.tv_desc);
            iv_image      = view.findViewById(R.id.iv_image);
            btn_add       = view.findViewById(R.id.btn_add);
            btn_minus     = view.findViewById(R.id.btn_minus);
            btn_accept    = view.findViewById(R.id.btn_accept);
            btn_cancel    = view.findViewById(R.id.btn_cancel);
        }
        catch (Exception e )
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setContent() {
        try
        {
            df = new DecimalFormat(MeshTVPreferenceManager.getHotelCurrency(getContext()) + " ###,##0.00");
            zoomIn = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
            btn_add.setOnFocusChangeListener(focus);
            btn_minus.setOnFocusChangeListener(focus);
            btn_accept.setOnFocusChangeListener(focus);
            btn_cancel.setOnFocusChangeListener(focus);
            btn_add.setOnHoverListener(hover);
            btn_minus.setOnHoverListener(hover);
            btn_accept.setOnHoverListener(hover);
            btn_cancel.setOnHoverListener(hover);
            if (this.object instanceof MeshShoppingItem)
            {
                meshShoppingItem = (MeshShoppingItem) object;
               // MeshResourceManager.displayLiveImageFor(getContext(),iv_image,meshShoppingItem.getImg_uri());
//                iv_image.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId(meshShoppingItem.getImg_uri())));
                Picasso.get().load(meshShoppingItem.getImg_uri()).into(iv_image);
                tv_main.setText(meshShoppingItem.getItem_name());
                price = meshShoppingItem.getUnit_price()>0?meshShoppingItem.getUnit_price():0.00;
                tv_total.setText(df.format(price));
                tv_desc.setText(Html.fromHtml(meshShoppingItem.getItem_description()));
                et_quantity.setText("1");
                tv_name.setText(meshShoppingItem.getItem_name());
                DecimalFormat df = new DecimalFormat(MeshTVPreferenceManager.getHotelCurrency(getActivity()) + " ###,##0.00");
                tv_price.setText(price>0?df.format(price):"FREE");
            }
            else if (this.object instanceof MeshFood)
            {
                meshfood = (MeshFood) object;
//                MeshResourceManager.displayLiveImageFor(getContext(),iv_image,meshfood.getImg_uri());
//                iv_image.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId(meshfood.getImg_uri())));
                Picasso.get().load(meshfood.getImg_uri()).into(iv_image);
                tv_main.setText(meshfood.getItem_name());
                tv_desc.setText(Html.fromHtml(meshfood.getItem_description()));
                price = meshfood.getUnit_price()>0?meshfood.getUnit_price():0.00;
                tv_total.setText(df.format(price));
                et_quantity.setText("1");
                tv_name.setText(meshfood.getItem_name());
                DecimalFormat df = new DecimalFormat(MeshTVPreferenceManager.getHotelCurrency(getActivity()) + " ###,##0.00");
                tv_price.setText(price>0?df.format(price):"FREE");
            }
            else if(this.object instanceof MeshFacility)
            {
                meshFacility = (MeshFacility) object;
//                MeshResourceManager.displayLiveImageFor(getContext(),iv_image,meshFacility.getImg_uri());
//                iv_image.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId(meshFacility.getImg_uri())));
                Picasso.get().load(meshFacility.getImg_uri()).into(iv_image);
                tv_main.setText(meshFacility.getItem_name());
                tv_desc.setText(Html.fromHtml(meshFacility.getItem_description()));
                price = meshFacility.getUnit_price()>0?meshFacility.getUnit_price():0.00;
                tv_total.setText(df.format(price));
                et_quantity.setText("1");
                tv_name.setText(meshFacility.getItem_name());
                DecimalFormat df = new DecimalFormat(MeshTVPreferenceManager.getHotelCurrency(getActivity()) + " ###,##0.00");
                tv_price.setText(price>0?df.format(price):"FREE");
            }
            else if(this.object instanceof MeshConciergeRequestItem)
            {
                meshItem = (MeshConciergeRequestItem) object;
//                MeshResourceManager.displayLiveImageFor(getContext(),iv_image,meshItem.getImg_uri());
//                iv_image.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId(meshItem.getImg_uri())));
                Picasso.get().load(meshItem.getImg_uri()).into(iv_image);
                tv_main.setText(meshItem.getItem_name());
                tv_desc.setText(Html.fromHtml(meshItem.getItem_description()));
                price = meshItem.getUnit_price()>0?meshItem.getUnit_price():0.00;
                tv_total.setText(df.format(price));
                et_quantity.setText("1");
                tv_name.setText(meshItem.getItem_name());
                DecimalFormat df = new DecimalFormat(MeshTVPreferenceManager.getHotelCurrency(getActivity()) + " ###,##0.00");
                tv_price.setText(price>0?df.format(price):"FREE");
            }
            else if(this.object instanceof MeshConciergeRequestService)
            {
                meshService = (MeshConciergeRequestService) object;
//                MeshResourceManager.displayLiveImageFor(getContext(),iv_image,meshService.getImg_uri());
//                iv_image.setImageDrawable(getContext().getResources().getDrawable(MeshResourceManager.get().getResourceId(meshService.getImg_uri())));
                Picasso.get().load(meshService.getImg_uri()).into(iv_image);
                tv_main.setText(meshService.getItem_name());
                tv_desc.setText(Html.fromHtml(meshService.getItem_description()));
                price = meshService.getUnit_price()>0?meshService.getUnit_price():0.00;
                tv_total.setText(df.format(price));
                et_quantity.setText("1");
                tv_name.setText(meshService.getItem_name());
                DecimalFormat df = new DecimalFormat(MeshTVPreferenceManager.getHotelCurrency(getActivity()) + " ###,##0.00");
                tv_price.setText(price>0?df.format(price):"FREE");
            }
            btn_add.setOnClickListener(clickListener);
            btn_minus.setOnClickListener(clickListener);
            btn_accept.setOnClickListener(clickListener);
            btn_cancel.setOnClickListener(clickListener);
            btn_add.onHoverChanged(true);
            btn_add.requestFocus();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int setLayout() {
        return R.layout.carlson_cart_layout;
    }

    @Override
    public int setConstraintLayout() {
        return R.id.cl_layout;
    }

    @Override
    public double setPercentageWidth() {
        return this.percentageWidth;
    }

    @Override
    public double setPercentageHeight() {
        return this.percentageHeight;
    }


    public void checkImage(final View view, Boolean b, Animation animation)
    {
        try
        {
            if (b)
            {
                view.startAnimation(animation);
            }
            else
            {
                view.clearAnimation();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    final View.OnHoverListener hover=new View.OnHoverListener() {
        @Override
        public boolean onHover(View view, MotionEvent motionEvent) {
            view.setHovered(true);
            view.requestFocus();
            return false;
        }
    };

    final View.OnFocusChangeListener focus=new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            checkImage(view,b,zoomIn);
        }
    };


    final View.OnClickListener clickListener = new View.OnClickListener() {

        public void onClick(final View v) {
            try
            {
                switch (v.getId()) {
                    case R.id.btn_minus:
                        MeshTVCartCompute.reduceQuantity(et_quantity, tv_total, price);
                        v.requestFocus();
                        break;
                    case R.id.btn_add:
                        MeshTVCartCompute.addQuantity(et_quantity, tv_total, price);
                        v.requestFocus();
                        break;
                    case R.id.btn_accept:
                        int qty=Integer.parseInt(et_quantity.getText().toString());
                        if(qty>0)
                        {
                            CarlsonConfirm.dialog(object, .35, .25, qty).show(getFragmentManager(), "itemconfirm");
                        }
                        else
                        {
                            MeshTVToast.makeText(getActivity(),R.layout.toast2,"invalid quantity", Toast.LENGTH_SHORT).show();
                        }
                        v.requestFocus();
                        break;
                    case R.id.btn_cancel:
                        v.requestFocus();
                        dismissAllDialogs(getFragmentManager());
                        break;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    };
}
