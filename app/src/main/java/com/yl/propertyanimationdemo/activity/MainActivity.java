package com.yl.propertyanimationdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yl.propertyanimationdemo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangle on 2017/2/27.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_base, R.id.btn_xml, R.id.btn_value_animator, R.id.btn_object_animator,
            R.id.btn_interpolator})
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.btn_base: // 基本用法
                intent = new Intent(this, BaseUsageActivity.class);
                break;

            case R.id.btn_xml: // 使用xml编写
                intent = new Intent(this, XmlUsageActivity.class);
                break;

            case R.id.btn_value_animator: // ValueAnimator的实际应用
                intent = new Intent(this, ValueAnimatorActivity.class);
                break;

            case R.id.btn_object_animator: // 自定义TypeEvaluator
                intent = new Intent(this, ObjectAnimatorActivity.class);
                break;

            case R.id.btn_interpolator: // Interpolator（插值器）
                intent = new Intent(this, InterpolatorActivity.class);
                break;

            default:
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
