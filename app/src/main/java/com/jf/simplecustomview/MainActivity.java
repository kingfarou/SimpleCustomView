package com.jf.simplecustomview;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jf.simplecustomview.clockview.ClockActivity;
import com.jf.simplecustomview.mergepicture.MergePictureActivity;
import com.jf.simplecustomview.temperatureprogress.TemperatureProgressActivity;
import com.jf.simplecustomview.twosideprogressbar.TwoSideProgressBarActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> contentList = initData();
        //设置ListView
        ListView listView = (ListView)findViewById(R.id.lv);
        listView.setOnItemClickListener(this);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contentList));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0){
            //两边滚动的进度条展示页面
            openActivity(TwoSideProgressBarActivity.class);
        }else if(position == 1){
            //圆形进度条展示页面
            openActivity(TemperatureProgressActivity.class);
        }else if(position == 2){
            //合并图片展示页面
            openActivity(MergePictureActivity.class);
        }else if(position == 3){
            openActivity(ClockActivity.class);
        }
    }

    //初始化ListView的数据
    private List<String> initData(){
        List<String> contentList = new ArrayList<>();
        contentList.add("点击打开两边滚动的进度条");
        contentList.add("点击打开圆形的进度条");
        contentList.add("点击打开合并图片展示页面");
        contentList.add("点击打开时钟界面");
        return contentList;
    }

    private <T extends FragmentActivity> void openActivity(Class<T> activity){
        startActivity(new Intent(this, activity));
    }
}
