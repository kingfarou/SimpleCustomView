package com.jf.simplecustomview.tickview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jf.simplecustomview.R;

public class TickViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tick_view);
        final TickView tickView = (TickView) findViewById(R.id.tick_view);
        final Button button = (Button)findViewById(R.id.btn_change_select);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tickView.getCheck()){
                    button.setText("点击选中");
                }else{
                    button.setText("点击取消");
                }
                tickView.setCheck(!tickView.getCheck());
            }
        });
    }
}
