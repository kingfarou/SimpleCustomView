package com.jf.simplecustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by JF on 2016/10/14.
 * 钟表
 */
public class ClockView extends View{

    private final float paddingSpace = 10;              //控件内容到控件边界的距离
    private final float circleWidth = 20;               //圆周宽度

    private final Paint circlePaint = new Paint();      //绘制圆周用的画笔
    private final Paint linePaint = new Paint();        //绘制刻度线用的笔
    private final Paint textPaint = new Paint();        //绘制文字用的画笔
    private final Paint pointerPaint = new Paint();     //绘制指针用个画笔
    private final Paint arcPaint = new Paint();         //画弧线的笔
    private final RectF arcRectF = new RectF();         //画弧线用的矩形

    //时、分、毫秒，为了技术方便没有使用秒，而是毫秒，不过对外提供的是秒
    private int hour = 8;
    private int minute = 0;
    private int millisecond = 0;
    //标记时钟是否是在运行
    private boolean isRun;

    public ClockView(Context context) {
        super(context);
        init();
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //对绘图所用的工具做初始化
    private void init(){
        //绘制时钟盘的画笔的初始化
        circlePaint.setColor(Color.RED);
        circlePaint.setStrokeWidth(circleWidth);
        circlePaint.setStyle(Paint.Style.STROKE);

        //绘制刻度线画笔的初始化
        linePaint.setColor(Color.BLACK);
        linePaint.setStrokeWidth(4);

        //绘制文字画笔的初始化
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(30);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextAlign(Paint.Align.CENTER);

        //绘制指针画笔的初始化
        pointerPaint.setColor(Color.BLACK);
        pointerPaint.setStrokeWidth(10);

        //绘制弧线画笔的初始化
        arcPaint.setColor(Color.GREEN);
        arcPaint.setStrokeWidth(circleWidth);
        arcPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getMeasuredSize(widthMeasureSpec), getMeasuredSize(heightMeasureSpec));
    }

    //都是比较常规的测量代码
    private int getMeasuredSize(int measureSpec){
        int measuredMode = MeasureSpec.getMode(measureSpec);
        //默认大小要稍微大一些
        int measuredSize = 800;
        if(measuredMode == MeasureSpec.EXACTLY){
            measuredSize = MeasureSpec.getSize(measureSpec);
        }else if(measuredMode == MeasureSpec.AT_MOST){
            measuredSize = Math.min(measuredSize, MeasureSpec.getSize(measureSpec));
        }
        return measuredSize;
    }

    //这里是核心代码
    @Override
    protected void onDraw(Canvas canvas) {
        int halfMeasuredWidth = getMeasuredWidth()/2;
        canvas.translate(halfMeasuredWidth, getMeasuredHeight()/2);
        //画圆
        float radius = halfMeasuredWidth - paddingSpace - circleWidth/2;
        canvas.drawCircle(0, 0, radius, circlePaint);
        //画刻度以及写字，普通刻度线长为10，整点刻度线长度为20
        canvas.save();
        //将刻度1转到最上面，30是相应的角度
        canvas.rotate(30);
        //在转动坐标系过程里刻度线的起点位置、普通线的终点位置，20表示线的长度，可以根据自己需要修改
        float lineStart = -(radius-circleWidth/2), commonLineEnd = lineStart+20;
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        //这里的20表示的是整点的刻度线比普通刻度线长多少，可以修改，但是记住在循环里面的划线部分需要跟着变化
        float textY = commonLineEnd+20-fontMetrics.ascent;//计算基线Y轴坐标
        for(int lineNumber = 5, clockNumber = 1; lineNumber < 65; lineNumber++){
            if(lineNumber%5 == 0){
                //如果画到了整点
                canvas.drawLine(0, lineStart, 0, commonLineEnd+20, linePaint);
                canvas.drawText(clockNumber+"", 0, textY, textPaint);
                clockNumber++;
            }else{
                //绘制普通的刻度
                canvas.drawLine(0, lineStart, 0, commonLineEnd, linePaint);
            }
            canvas.rotate(6);
        }
        canvas.restore();
        //画指针
        canvas.save();
        //画时针
        float pointerDegree = ( hour+minute/60f )*360/12;     //计算时针的角度
        canvas.rotate(pointerDegree);
        canvas.drawLine(0, 40, 0, -radius/3, pointerPaint);
        canvas.restore();
        //划分针
        canvas.save();
        pointerDegree = minute/60f*360;
        canvas.rotate(pointerDegree);
        canvas.drawLine(0, 40, 0, -radius/2, pointerPaint);
        canvas.restore();
        //用弧线的形式来填充秒数
        float position = halfMeasuredWidth-paddingSpace-circleWidth/2;
        arcRectF.set(-position, -position, position, position);
        canvas.drawArc(arcRectF, -90, millisecond /60000f*360, false, arcPaint);
        super.onDraw(canvas);
        //如果时钟需要继续走动
        if(isRun){
            calculateTime();
            postInvalidateDelayed(100);
        }
    }

    //在时间变化过程中计算时间用的方法
    private void calculateTime(){
        if(millisecond == 60000){
            millisecond = 0;
            if(minute == 59){
                minute = 0;
                if(hour == 11) hour = 0;
                else hour++;
            }else{
                minute++;
            }
        }else{
            millisecond+=100;
        }
    }

    /**
     * 设置要显示的时间
     * @param hour 小时（0-11）
     * @param minute 分钟（0-59）
     * @param second 秒钟（0-59）
     */
    public void setTime(int hour, int minute, int second){
        //输入有误
        if(hour<0 || hour>11 || minute<0 || minute>59 || second<0 || second>59){
            return;
        }
        this.hour = hour;
        this.minute = minute;
        this.millisecond = second*1000;
    }


    /**
     * 让时钟开始运行
     */
    public void star(){
        isRun = true;
        invalidate();
    }

    /**
     * 让时钟停止运行
     */
    public void paus(){
        isRun = false;
    }
}
