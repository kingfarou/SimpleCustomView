package com.jf.simplecustomview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jf.simplecustomview.R;
import com.jf.simplecustomview.view.TemperatureProgress;

/**
 * Created by JF on 2016/10/10.
 * 展示圆形进度条的界面
 */
public class TemperatureProgressActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progress);
        TemperatureProgress circleProgress = (TemperatureProgress)findViewById(R.id.circle_progress);
        circleProgress.setTemperature(40, 24, 12);
    }
}
