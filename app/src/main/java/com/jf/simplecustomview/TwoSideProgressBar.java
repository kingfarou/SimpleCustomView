package com.jf.simplecustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by JF on 2016/10/9.
 * 两边滚动的进度条
 */
public class TwoSideProgressBar extends View{

    //左右两边第一个“条”的宽度
    private float firstBarWidth = 0;
    //其他普通“条”的宽度
    private float barWidth = 40;
    //两个条之间的间隔
    private int barSpace = 20;
    //当地一个“条”宽度达到普通条的宽度时，第一个“条”到坐标原点的间距
    private float firstBarDistance = barWidth/10;

    private Paint barPaint = new Paint();

    public TwoSideProgressBar(Context context) {
        super(context);
        barPaint.setColor(Color.BLUE);
    }

    public TwoSideProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        barPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getMeasuredSize(widthMeasureSpec), getMeasuredSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int measuredWidth = getMeasuredWidth();
        //将坐标移到中间
        canvas.translate(measuredWidth/2, 0);
        /*绘制时有两类情况，第一个“条”宽度小于普通“条”；第一个“条”宽度达到普通“条”的宽度。
        两类情况的绘制逻辑不一样*/
        //第一个“条”宽度小于普通“条”
        if(firstBarWidth<=barWidth){
            //绘制右边第一个“条”
            int index = 0;
            canvas.drawLine(index, 0, firstBarWidth, 0, barPaint);
            index+=firstBarWidth+barSpace;
            //循环绘制右边其它普通的“条”
            while (index <= measuredWidth/2){
                canvas.drawLine(index, 0, index+barWidth, 0, barPaint);
                index+=barWidth+barSpace;
            }
            //绘制左边第一个“条”
            index = 0;
            canvas.drawLine(-firstBarWidth, 0, index, 0, barPaint);
            index-=(firstBarWidth+barSpace);
            //循环绘制左边其它普通的“条”
            while (index >= -measuredWidth/2){
                canvas.drawLine(index-barWidth, 0, index, 0, barPaint);
                index-=(barWidth+barSpace);
            }
            firstBarWidth+=barWidth/10;
        }else{
            //循环绘制右边的“条”
            float index = firstBarDistance;
            while (index <= measuredWidth/2){
                canvas.drawLine(index, 0, index+barWidth, 0, barPaint);
                index+=barWidth+barSpace;
            }
            //循环绘制左边的“条”
            index = -firstBarDistance;
            while (index >= -measuredWidth/2){
                canvas.drawLine(index-barWidth, 0, index, 0, barPaint);
                index-=(barWidth+barSpace);
            }
            firstBarDistance+=barWidth/10;
            //临界点
            if(firstBarDistance > barSpace) {
                firstBarDistance = barWidth/10;
                firstBarWidth = barWidth/10;
            }
        }
        invalidate();
    }

    private int getMeasuredSize(int measureSpec){
        int measuredMode = MeasureSpec.getMode(measureSpec);
        int measuredSize = 200;
        if(measuredMode == MeasureSpec.EXACTLY){
            measuredSize = MeasureSpec.getSize(measureSpec);
        }else if(measuredMode == MeasureSpec.AT_MOST){
            measuredSize = Math.min(measuredSize, MeasureSpec.getSize(measureSpec));
        }
        return measuredSize;
    }
}
