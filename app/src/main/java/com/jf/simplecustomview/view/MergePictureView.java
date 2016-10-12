package com.jf.simplecustomview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by JF on 2016/10/9.
 * 仿微信朋友圈合并图片效果
 */
public class MergePictureView extends View{

    //要显示的图片资源数组（即要合并的图片）
    private int[] drawableIds;
    //裁剪图片时的裁剪区域
    private Rect srcRect = new Rect();
    //要将图片绘制到哪一个区域
    private Rect dstRect = new Rect();
    private Paint bitmapPaint = new Paint();
    private Paint linePaint = new Paint();

    public MergePictureView(Context context) {
        super(context);
    }

    public MergePictureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getMeasureSize(widthMeasureSpec), getMeasureSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(drawableIds == null || drawableIds.length == 0){
            super.onDraw(canvas);
            return;
        }
        int length = drawableIds.length;
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        Bitmap bitmap;
        if(length == 1){
            //如果只有一张图片，则将该图片裁剪成合适大小，直接绘制就可以了
            bitmap = decodeSampledBitmapFromResource(drawableIds[0], measuredWidth, measuredHeight);
            srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            dstRect.set(0, 0, measuredWidth, measuredHeight);
            canvas.drawBitmap(bitmap, srcRect, dstRect, bitmapPaint);
        }else if(length == 2){
            //如果有两张图片，则两张图片各占左右一半位置，中间画一条分隔线
            //两张图片中间分隔线的宽度
            int lineWidth = 4;
            //图片的目标宽度
            int dstWidth = (measuredWidth-lineWidth)/2;
            //绘制第一张图片
            bitmap = decodeSampledBitmapFromResource(drawableIds[0], dstWidth, measuredHeight);
            srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            dstRect.set(0, 0, dstWidth, measuredHeight);
            canvas.drawBitmap(bitmap, srcRect, dstRect, bitmapPaint);
            //绘制分割线
            linePaint.setColor(Color.WHITE);
            canvas.drawLine(dstWidth, 0, dstWidth+lineWidth, getMeasuredHeight(), linePaint);
            //绘制第二张图片
            bitmap = decodeSampledBitmapFromResource(drawableIds[1], dstWidth, getMeasuredHeight());
            srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            dstRect.set(dstWidth+lineWidth, 0, getMeasuredWidth(), getMeasuredHeight());
            canvas.drawBitmap(bitmap, srcRect, dstRect, bitmapPaint);
        }else if(length == 3){
            //如果有三张图片，则第一张图片在左边占一半位置，其余两张在右边占四分之一位置，图片之间画线分隔
            //左右分割线宽度，上下分割线高度
            int leftRightWidth = 4, topBottomHeight = 4;
            //每一张图片的宽度
            int dstWidth = (getMeasuredWidth()-leftRightWidth)/2;
            //绘制第一张图片
            bitmap = decodeSampledBitmapFromResource(drawableIds[0], dstWidth, getMeasuredHeight());
            srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            dstRect.set(0, 0, dstWidth, getMeasuredHeight());
            canvas.drawBitmap(bitmap, srcRect, dstRect, bitmapPaint);
            //绘制左右分割线
            linePaint.setColor(Color.WHITE);
            canvas.drawLine(dstWidth, 0, dstWidth+leftRightWidth, getMeasuredHeight(), linePaint);
            //绘制第二张图片
            bitmap = decodeSampledBitmapFromResource(drawableIds[1], dstWidth, getMeasuredHeight()/2);
            srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            dstRect.set(dstWidth+leftRightWidth, 0, getMeasuredWidth(), (getMeasuredHeight()-topBottomHeight)/2);
            canvas.drawBitmap(bitmap, srcRect, dstRect, bitmapPaint);
            //回执上下分割线
            canvas.drawLine(measuredWidth/2, measuredHeight/2, measuredWidth, measuredHeight/2, linePaint);
            //绘制第三张图片
            Bitmap thirdBitmap = decodeSampledBitmapFromResource(drawableIds[2], dstWidth, getMeasuredHeight());
            srcRect.set(0, 0, thirdBitmap.getWidth(), thirdBitmap.getHeight());
            dstRect.set(dstWidth+leftRightWidth, (measuredHeight-topBottomHeight)/2+topBottomHeight, measuredWidth, getMeasuredHeight());
            canvas.drawBitmap(thirdBitmap, srcRect, dstRect, bitmapPaint);
        }else{
            //四张以及以上图片统一处理，最多只能显示四张，将四张图片已“田”字形分布
            //这是分割线的尺寸，横线的高，竖线的宽，都等于他
            int lineSize = 4;
            //四张图片都是相同宽度，相同高度
            int dstWidth = (measuredWidth-lineSize)/2;
            int dstHeight = (measuredHeight-lineSize)/2;
            //先将四张图画上去
            bitmap = decodeSampledBitmapFromResource(drawableIds[0], dstWidth, dstHeight);
            srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            dstRect.set(0, 0, dstWidth, dstHeight);
            canvas.drawBitmap(bitmap, srcRect, dstRect, bitmapPaint);

            bitmap = decodeSampledBitmapFromResource(drawableIds[1], dstWidth, dstHeight);
            srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            dstRect.set(dstWidth+lineSize, 0, measuredWidth, dstHeight);
            canvas.drawBitmap(bitmap, srcRect, dstRect, bitmapPaint);

            bitmap = decodeSampledBitmapFromResource(drawableIds[2], dstWidth, dstHeight);
            srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            dstRect.set(0, dstHeight+lineSize, dstWidth, measuredHeight);
            canvas.drawBitmap(bitmap, srcRect, dstRect, bitmapPaint);

            bitmap = decodeSampledBitmapFromResource(drawableIds[3], dstWidth, dstHeight);
            srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            dstRect.set(dstWidth+lineSize, dstHeight+lineSize, measuredWidth,measuredHeight);
            canvas.drawBitmap(bitmap, srcRect, dstRect, bitmapPaint);
            //最后画两条分割线
            canvas.drawLine(dstWidth, 0, dstWidth, measuredHeight, linePaint);
            canvas.drawLine(0, dstHeight, measuredWidth, dstHeight, linePaint);
        }
        super.onDraw(canvas);
    }


    public void setDrawableIds(int[] drawableIds){
        this.drawableIds = drawableIds;
        invalidate();
    }

    /**
     * 从Resources中加载图片
     * @param resId 图片资源
     * @param reqWidth 目标宽度
     * @param reqHeight 目标高度
     * @return
     */
    private Bitmap decodeSampledBitmapFromResource(int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 设置成了true,不占用内存，只获取bitmap宽高
        BitmapFactory.decodeResource(getResources(), resId, options); // 读取图片长宽，目的是得到图片的宽高
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight); // 调用上面定义的方法计算inSampleSize值
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        Bitmap src = BitmapFactory.decodeResource(getResources(), resId, options); // 载入一个稍大的缩略图
        return createScaleBitmap(src, reqWidth, reqHeight, options.inSampleSize); // 通过得到的bitmap，进一步得到目标大小的缩略图
    }

    //根据"measureSpec"返回具体尺寸值
    private static int getMeasureSize(int measureSpec){
        int measureMode = MeasureSpec.getMode(measureSpec);
        //先给一个默认值
        int measureSize = 200;
        if(measureMode == MeasureSpec.EXACTLY){
            measureSize = MeasureSpec.getSize(measureSpec);
        }else if(measureMode == MeasureSpec.AT_MOST){
            measureSize = Math.min(measureSize, MeasureSpec.getSize(measureSpec));
        }
        return measureSize;
    }

    /**
     * 计算图片的压缩比率
     * @param options 参数
     * @param reqWidth 目标的宽度
     * @param reqHeight 目标的高度
     * @return inSampleSize 压缩比率
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        //源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * 通过传入的bitmap，进行压缩，得到符合标准的bitmap
     * @param src 原图片Bitmap
     * @param dstWidth 目标宽度
     * @param dstHeight 目标高度
     * @return 压缩后的图片Bitmap
     */
    private static Bitmap createScaleBitmap(Bitmap src, int dstWidth, int dstHeight, int inSampleSize) {
        // 如果是放大图片，filter决定是否平滑，如果是缩小图片，filter无影响，我们这里是缩小图片，所以直接设置为false
        Bitmap dst = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, false);
        //如果图片有缩放，回收原来的图片
        if (src != dst) src.recycle();
        return dst;
    }

}
