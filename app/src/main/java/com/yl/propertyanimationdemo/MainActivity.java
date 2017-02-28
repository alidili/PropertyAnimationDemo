package com.yl.propertyanimationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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

    @OnClick({R.id.btn_base, R.id.btn_xml})
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.btn_base: // 基本用法
                intent = new Intent(this, BaseUsageActivity.class);
                break;

            case R.id.btn_xml: // 使用Xml编写
                intent = new Intent(this, XmlUsageActivity.class);
                break;

            default:
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
