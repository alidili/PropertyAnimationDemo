package com.yl.propertyanimationdemo.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * 自定义backgroundColor属性
 * Created by yangle on 2017/3/2.
 */
public class ColorView extends RelativeLayout {

    private String backgroundColor;

    public ColorView(Context context) {
        this(context, null);
    }

    public ColorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.setBackgroundColor(Color.parseColor(backgroundColor));
    }
}
