package com.yl.propertyanimationdemo.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.yl.propertyanimationdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 基本用法
 * Created by yangle on 2017/2/27.
 */
public class BaseUsageActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_usage);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_trans, R.id.btn_rotate, R.id.btn_alpha, R.id.btn_scale, R.id.btn_combine})
    public void onClick(View view) {
        ObjectAnimator animator = null;

        switch (view.getId()) {
            case R.id.btn_trans: // 移动
                // 沿X轴向右移动100px，然后向左移动回到原位
                // 沿Y轴移动，ofFloat第二个参数传入translationY
                animator = ObjectAnimator.ofFloat(image, "translationX", 0f, 100f, 0f);
                break;

            case R.id.btn_rotate: // 旋转
                // 垂直旋转360度
                // 水平旋转，ofFloat第二个参数传入rotationX
                animator = ObjectAnimator.ofFloat(image, "rotationY", 0f, 360f);
                break;

            case R.id.btn_alpha: // 透明
                // 透明度从0到1
                animator = ObjectAnimator.ofFloat(image, "alpha", 0f, 1f);
                break;

            case R.id.btn_scale: // 缩放
                // 水平缩放0.5倍，然后恢复
                // 垂直缩放，ofFloat第二个参数传入scaleY
                animator = ObjectAnimator.ofFloat(image, "scaleX", 1f, 0.5f, 1f);
                break;

            case R.id.btn_combine: // 组合动画
                // 图片从屏幕左侧移动到右侧，再回到原位，同时透明度从0调节到1，然后垂直旋转360度
                ObjectAnimator trans = ObjectAnimator.ofFloat(image, "translationX", -100f, 100f, 0f);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(image, "alpha", 0f, 1f);
                ObjectAnimator rotation = ObjectAnimator.ofFloat(image, "rotationY", 0f, 360f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(trans).with(alpha).before(rotation);
                animatorSet.setDuration(5000);
                animatorSet.start();
                break;

            default:
                break;
        }

        if (animator != null) {
            // 动画执行时长2s，默认300ms
            animator.setDuration(2000);
            animator.start();
        }
    }
}
