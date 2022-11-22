package org.techtown.customviewimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomViewImage extends View {
    Paint paint;
    Bitmap cacheBitmap;
    Canvas cacheCanvas;

    public CustomViewImage(Context context) {
        super(context);
        init(context);
    }

    public CustomViewImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        paint = new Paint();
    }

    @Override
    // 뷰가 차지할 영역이 결정되는 시점
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        createCacheBitmap(w, h);
        testDrawing();
    }

    public void createCacheBitmap(int w, int h) {
        cacheBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888); // 메모리에 빈 비트맵 이미지를 만듦 -> 그리는 시간을 줄이기 위해
        cacheCanvas = new Canvas();
        cacheCanvas.setBitmap(cacheBitmap);
    }

    public void testDrawing() {
        Bitmap srcImg = BitmapFactory.decodeResource(getResources(), R.drawable.gummybear);
        cacheCanvas.drawBitmap(srcImg, 100, 100, paint);

        Matrix matrix = new Matrix();
        matrix.postScale(1, -1);
        Bitmap inverseBitmap = Bitmap.createBitmap(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), matrix, false);
        cacheCanvas.drawBitmap(inverseBitmap, 300, 300, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (cacheBitmap != null) {
            canvas.drawBitmap(cacheBitmap, 0, 0, null);
        }
    }
}
