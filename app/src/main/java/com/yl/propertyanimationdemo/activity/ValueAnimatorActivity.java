package com.yl.propertyanimationdemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.yl.propertyanimationdemo.R;
import com.yl.propertyanimationdemo.widget.RadarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ValueAnimator的实际应用
 * Created by yangle on 2017/3/1.
 */
public class ValueAnimatorActivity extends AppCompatActivity {

    @BindView(R.id.rv_device_status)
    RadarView rvDeviceStatus;
    @BindView(R.id.ll_device_status)
    RelativeLayout llDeviceStatus;

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
                llDeviceStatus.setBackgroundColor(Color.parseColor("#ED6E74"));
                break;

            default:
                break;
        }
    }
}
