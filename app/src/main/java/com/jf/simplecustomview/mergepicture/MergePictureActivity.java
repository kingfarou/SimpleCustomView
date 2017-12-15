package com.jf.simplecustomview.mergepicture;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jf.simplecustomview.R;

/**
 * Created by JF on 2016/10/12.
 * 仿微信朋友圈图片合并效果展示页面
 */
public class MergePictureActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge_picture);

        //展示只有一张图片时的效果
        int[] resourcesIdsFirst = new int[]{R.mipmap.t1};
        ((MergePictureView)findViewById(R.id.merge_picture_view_first)).setDrawableIds(resourcesIdsFirst);

        //两张图片合并效果
        int[] resourceIdsSecond = new int[]{R.mipmap.t1, R.mipmap.t2};
        ((MergePictureView)findViewById(R.id.merge_picture_view_second)).setDrawableIds(resourceIdsSecond);

        //三张图片合并效果
        int[] resourceIdsThird = new int[]{R.mipmap.t1, R.mipmap.t2, R.mipmap.t3};
        ((MergePictureView)findViewById(R.id.merge_picture_view_third)).setDrawableIds(resourceIdsThird);

        //四张图片合并效果
        int[] resourceIdForth = new int[]{R.mipmap.t1, R.mipmap.t2, R.mipmap.t3, R.mipmap.t4};
        ((MergePictureView)findViewById(R.id.merge_picture_view_forth)).setDrawableIds(resourceIdForth);
    }
}
