package com.jf.simplecustomview.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jf.simplecustomview.R;

/**
 * Created by JF on 2016/10/10.
 * 展示向两边滚动的进度条的页面
 */
public class TwoSideProgressBarActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_side_progress_bar);
    }
}
