package com.ph.bittelasia.mesh.tv.libFragment.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ViewFlipper;


public class SlideViewFlipper extends ViewFlipper
{

    Paint paint = new Paint();
    Paint paint2 = new Paint();

    public int currentDisplayedColor;

    public int defaultColor;

    public int yIndicatorPlace;

    public float margin;

    public float radius;

    public OnViewFlipperListener listener;

    public SlideViewFlipper(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas)
    {
        super.dispatchDraw(canvas);
        int width = getWidth();


        float cx = width / 2 - ((radius + margin) * 2 * getChildCount() / 2);
        float cy = getHeight() - yIndicatorPlace;

        canvas.save();

        paint.setAntiAlias(true);
        paint2.setAntiAlias(true);

        if(getChildCount()<10 && getChildCount()>1)
        for (int i = 0; i < getChildCount(); i++)
        {
            if (i == getDisplayedChild())
            {
                paint2.setColor(currentDisplayedColor);
                paint.setColor(defaultColor);
                paint.setStrokeWidth(2);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeCap(Paint.Cap.ROUND);
                canvas.drawCircle(cx, cy, radius, paint2);
                canvas.drawCircle(cx, cy, radius, paint);
                if(listener!=null)
                    listener.onViewFlipperIndex(i);

            } else
            {
                paint2.setColor(defaultColor);
                paint.setColor(defaultColor);
                paint.setStrokeWidth(2);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeCap(Paint.Cap.ROUND);
                canvas.drawCircle(cx, cy, radius, paint2);
                canvas.drawCircle(cx, cy, radius, paint);
            }
            cx += 2 * (radius + margin);
        }
        canvas.restore();
    }

    public void setListener(OnViewFlipperListener listener) {
        this.listener = listener;
    }


    public interface OnViewFlipperListener{
         abstract void onViewFlipperIndex(int index);
    }

}