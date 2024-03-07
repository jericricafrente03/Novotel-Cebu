package com.ph.bittelasia.meshtv.tv.carlson.Temp.View;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class ScrollingTextView extends AppCompatTextView
{


    public ScrollingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setSingleLine(true);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setMaxLines(1);
    }

    @Override
    public boolean isSelected()
    {
        return true;
    }
}
