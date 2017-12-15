package com.jf.simplecustomview.tickview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jf.simplecustomview.R;

public class TickViewActivity extends AppCompatActivity {

    private TickView tickView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tick_view);
        tickView = (TickView) findViewById(R.id.tick_view);
        findViewById(R.id.btn_change_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tickView.setCheck(!tickView.getCheck());
            }
        });
    }
}
