package com.ph.bittelasia.mesh.tv.libFragment.view.dialog;

import android.content.Context;
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

import androidx.annotation.NonNull;

import com.ph.bittelasia.mesh.tv.libFragment.R;
import com.ph.bittelasia.mesh.tv.libFragment.control.cart.CartCompute;
import com.ph.bittelasia.mesh.tv.libFragment.control.listener.AddCartListener;
import com.ph.bittelasia.mesh.tv.libFragment.control.listener.OnListClickListener;
import com.ph.bittelasia.mesh.tv.libFragment.control.model.AddCart;
import com.ph.bittelasia.mesh.tv.libFragment.view.fragment.CustomDialog;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.HashMap;

public abstract class AddCartDialogFragment extends CustomDialog implements View.OnFocusChangeListener, View.OnHoverListener {

    private AddCartListener     addCartListener;
    private Animation           zoomIn;
    private EditText            quantityView;
    private Double              price;
    private AddCart             addCart;
    private DecimalFormat       priceFormat;
    private TextView            totalView;
    private TextView            mainView;
    private TextView            nameView;
    private TextView            priceView;
    private TextView            descView;
    private ImageView           imageView;
    private Button              addView;
    private Button              minusView;
    private Button              acceptView;
    private Button              cancelView;

    public AddCartDialogFragment cart(AddCart cart){
        setAddCart(cart);
        return this;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        addCartListener = (AddCartListener)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        addCartListener = null;
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        checkImage(view,b,zoomIn);
    }


    @Override
    public boolean onHover(View view, MotionEvent motionEvent) {
        view.setHovered(true);
        view.requestFocus();
        return false;
    }

    @Override
    public void setIDs(View view) {
        quantityView = view.findViewById(getQuantityViewID());
        totalView = view.findViewById(getTotalViewID());
        mainView = view.findViewById(getMainViewID());
        nameView = view.findViewById(getNameViewID());
        priceView = view.findViewById(getPriceViewID());
        descView = view.findViewById(getDescViewID());
        imageView = view.findViewById(getImageViewID());
        addView = view.findViewById(getAddViewID());
        minusView = view.findViewById(getMinusViewID());
        acceptView = view.findViewById(getAcceptViewID());
        cancelView = view.findViewById(getCancelViewID());

        zoomIn = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
        getAddView().setOnFocusChangeListener(this);
        getMinusView().setOnFocusChangeListener(this);
        getAcceptView().setOnFocusChangeListener(this);
        getCancelView().setOnFocusChangeListener(this);
        getAddView().setOnHoverListener(this);
        getMinusView().setOnHoverListener(this);
        getAcceptView().setOnHoverListener(this);

        getMinusView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartCompute.reduceQuantity(getQuantityView(), getTotalView(),price,priceFormat);
                view.requestFocus();
            }
        });
        getAddView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartCompute.addQuantity(getQuantityView(),getTotalView(), price,priceFormat);
                view.requestFocus();
            }
        });
        getAcceptView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty=Integer.parseInt(getQuantityView().getText().toString());
                if(qty>0)
                {
                    addCartListener.onConfirmCart(getAddCart(),qty);
                }
                else
                {
                    Toast.makeText(getActivity(), "invalid quantity", Toast.LENGTH_SHORT).show();
                }
                view.requestFocus();
            }
        });
        getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.requestFocus();
                assert getFragmentManager() != null;
                dismissAllDialogs(getFragmentManager());
            }
        });
    }

    @Override
    public void setContent() {
        if(getAddCart()!=null){
            if(getActivity()!=null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        price = getAddCart().getPrice() > 0 ? getAddCart().getPrice() : 0.00;
                        priceFormat = new DecimalFormat(getAddCart().getPriceFormat());
                        Picasso.get().load(getAddCart().getImagePath()).into(getImageView());
                        getMainView().setText(getAddCart().getName());
                        getTotalView().setText(priceFormat.format(price));
                        getDescView().setText(Html.fromHtml(getAddCart().getDesc()));
                        getQuantityView().setText("1");
                        getNameView().setText(getAddCart().getName());
                        getPriceView().setText(price > 0 ? priceFormat.format(price) : "FREE");
                    }
                });
            }
        }
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


    public void setAddCart(AddCart addCart) {
        this.addCart = addCart;
    }

    public AddCart getAddCart() {
        return addCart;
    }

    public EditText getQuantityView() {
        return quantityView;
    }

    public void setQuantityView(EditText quantityView) {
        this.quantityView = quantityView;
    }


    public TextView getTotalView() {
        return totalView;
    }

    public void setTotalView(TextView totalView) {
        this.totalView = totalView;
    }

    public TextView getMainView() {
        return mainView;
    }

    public void setMainView(TextView mainView) {
        this.mainView = mainView;
    }

    public TextView getNameView() {
        return nameView;
    }

    public void setNameView(TextView nameView) {
        this.nameView = nameView;
    }

    public TextView getPriceView() {
        return priceView;
    }

    public void setPriceView(TextView priceView) {
        this.priceView = priceView;
    }

    public TextView getDescView() {
        return descView;
    }

    public void setDescView(TextView descView) {
        this.descView = descView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Button getAddView() {
        return addView;
    }

    public void setAddView(Button addView) {
        this.addView = addView;
    }

    public Button getMinusView() {
        return minusView;
    }

    public void setMinusView(Button minusView) {
        this.minusView = minusView;
    }

    public Button getAcceptView() {
        return acceptView;
    }

    public void setAcceptView(Button acceptView) {
        this.acceptView = acceptView;
    }

    public Button getCancelView() {
        return cancelView;
    }

    public void setCancelView(Button cancelView) {
        this.cancelView = cancelView;
    }


    public abstract int getQuantityViewID();
    public abstract int getTotalViewID();
    public abstract int getMainViewID();
    public abstract int getNameViewID();
    public abstract int getPriceViewID();
    public abstract int getDescViewID();
    public abstract int getImageViewID();
    public abstract int getAddViewID();
    public abstract int getMinusViewID();
    public abstract int getAcceptViewID();
    public abstract int getCancelViewID();
}
