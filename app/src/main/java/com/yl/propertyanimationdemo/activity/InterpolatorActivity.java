package com.yl.propertyanimationdemo.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.yl.propertyanimationdemo.R;
import com.yl.propertyanimationdemo.widget.JellyInterpolator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Interpolator（插值器）
 * Created by yangle on 2017/3/9.
 */
public class InterpolatorActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolator);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.image)
    public void onClick() {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(image, "scaleX", 0f, 1f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(image, "scaleY", 0f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
        // 设置插值器
        animatorSet.setInterpolator(new JellyInterpolator());
        animatorSet.setDuration(3000);
        animatorSet.start();
    }
}
