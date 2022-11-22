package org.techtown.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CustomView extends View {
    Paint paint;

    public CustomView(Context context) {
        super(context);

        init(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context) {
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // canvas.drawRect(100, 100, 200, 200, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawRect(10, 10, 300, 300, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10.0F);
        paint.setColor(Color.GREEN);
        canvas.drawRect(10, 10, 300, 300, paint);

         paint.setAntiAlias(true);
         canvas.drawCircle(400, 400, 200, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Toast.makeText(getContext(), "touched : " + event.getX() + ", " + event.getY(), Toast.LENGTH_LONG).show();
        }
        return super.onTouchEvent(event);
    }
}
