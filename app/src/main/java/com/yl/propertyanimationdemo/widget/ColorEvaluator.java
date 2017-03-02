package com.yl.propertyanimationdemo.widget;

import android.animation.TypeEvaluator;

/**
 * 计算颜色过渡
 * Created by yangle on 2017/3/2.
 */
public class ColorEvaluator implements TypeEvaluator {

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        String startColor = (String) startValue;
        String endColor = (String) endValue;

        int startRed = Integer.parseInt(startColor.substring(1, 3), 16);
        int startGreen = Integer.parseInt(startColor.substring(3, 5), 16);
        int startBlue = Integer.parseInt(startColor.substring(5, 7), 16);

        int endRed = Integer.parseInt(endColor.substring(1, 3), 16);
        int endGreen = Integer.parseInt(endColor.substring(3, 5), 16);
        int endBlue = Integer.parseInt(endColor.substring(5, 7), 16);

        int currentRed = (int) ((endRed - startRed) * fraction + startRed);
        int currentGreen = (int) ((endGreen - startGreen) * fraction + startGreen);
        int currentBlue = (int) ((endBlue - startBlue) * fraction + startBlue);

        return "#" + getHexString(currentRed) + getHexString(currentGreen) + getHexString(currentBlue);
    }

    private String getHexString(int value) {
        String hexString = Integer.toHexString(value);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString;
    }
}
