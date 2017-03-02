package com.yl.propertyanimationdemo.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yl.propertyanimationdemo.R;
import com.yl.propertyanimationdemo.widget.ColorEvaluator;
import com.yl.propertyanimationdemo.widget.ColorView;
import com.yl.propertyanimationdemo.widget.RadarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ObjectAnimator的高级用法，自定义TypeEvaluator
 * Created by yangle on 2017/3/2.
 */
public class ObjectAnimatorActivity extends AppCompatActivity {

    @BindView(R.id.rv_device_status)
    RadarView rvDeviceStatus;
    @BindView(R.id.rl_device_status)
    ColorView rlDeviceStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_alarm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_alarm:
                int[] deviceStatus = {RadarView.ONLINE, RadarView.ONLINE, RadarView.ONLINE,
                        RadarView.ONLINE, RadarView.ALARM, RadarView.ALARM};
                rvDeviceStatus.setDeviceStatus(deviceStatus);

                // 颜色过渡动画
                ObjectAnimator animator = ObjectAnimator.ofObject(rlDeviceStatus, "backgroundColor",
                        new ColorEvaluator(), "#2B93EC", "#ED6E74");
                animator.setDuration(2000);
                animator.start();
                break;

            default:
                break;
        }
    }
}
