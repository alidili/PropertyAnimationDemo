package com.yl.propertyanimationdemo.widget;

import android.animation.TimeInterpolator;

/**
 * 果冻效果差值器
 * Created by yangle on 2017/3/9.
 */
public class JellyInterpolator implements TimeInterpolator {

    private float factor;

    public JellyInterpolator() {
        this.factor = 0.15f;
    }

    @Override
    public float getInterpolation(float input) {
        return (float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
    }
}
