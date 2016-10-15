package com.jf.simplecustomview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jf.simplecustomview.R;
import com.jf.simplecustomview.view.ClockView;

/**
 * Created by JF on 2016/10/14.
 */
public class ClockActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        ClockView clockView = (ClockView)findViewById(R.id.clock_view);
        clockView.setTime(8, 20, 40);
        clockView.star();
    }
}
