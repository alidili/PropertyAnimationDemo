package com.yl.propertyanimationdemo.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
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
 * 使用xml编写动画
 * Created by yangle on 2017/2/27.
 */
public class XmlUsageActivity extends AppCompatActivity {

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
        Animator animator = null;

        switch (view.getId()) {
            case R.id.btn_trans: // 移动
                // 沿X轴向右移动100px，然后向左移动回到原位
                animator = AnimatorInflater.loadAnimator(this, R.animator.trans);
                break;

            case R.id.btn_rotate: // 旋转
                // 垂直旋转360度
                animator = AnimatorInflater.loadAnimator(this, R.animator.rotate);
                break;

            case R.id.btn_alpha: // 透明
                // 透明度从0到1
                animator = AnimatorInflater.loadAnimator(this, R.animator.alpha);
                break;

            case R.id.btn_scale: // 缩放
                // 水平缩放0.5倍，然后恢复
                animator = AnimatorInflater.loadAnimator(this, R.animator.scale);
                break;

            case R.id.btn_combine: // 组合动画
                // 图片从屏幕左侧移动到右侧，再回到原位，同时透明度从0调节到1，然后垂直旋转360度
                animator = AnimatorInflater.loadAnimator(this, R.animator.combine);
                break;

            default:
                break;
        }

        if (animator != null) {
            animator.setTarget(image);
            animator.start();
        }
    }
}
