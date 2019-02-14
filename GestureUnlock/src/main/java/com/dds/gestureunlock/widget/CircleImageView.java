package com.dds.gestureunlock.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;

import com.dds.gestureunlock.JsConst;

public class CircleImageView extends AppCompatImageView {

    private Paint paint = null;
    private static final int strokeWidth = 4;
    private int normalColor;
    private int selectedColor;
    private int errorColor;
    private int currentColor;
    private int state;
    private int blockWidth;
    private int radius;
    private boolean isShowTrack = true;

    public int getRadius() {
        return radius;
    }

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, int width, int normalColor,
                           int selectedColor, int errorColor, boolean isShowTrack) {
        super(context);
        paint = new Paint();
        paint.setAntiAlias(true);

        this.normalColor = normalColor;
        this.selectedColor = selectedColor;
        this.errorColor = errorColor;
        this.currentColor = normalColor;
        this.blockWidth = width;
        this.radius = blockWidth / 2;
        this.isShowTrack = isShowTrack;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(currentColor);
        canvas.drawCircle(blockWidth / 2, blockWidth / 2, radius - 3, paint);

        if (state != JsConst.POINT_STATE_NORMAL) {
            if (state == JsConst.POINT_STATE_SELECTED && !isShowTrack) {
                return;
            }
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(currentColor);
            canvas.drawCircle(blockWidth / 2, blockWidth / 2, radius / 2, paint);
        }

    }

    public void setCurrentState(int state) {
        this.state = state;
        switch (state) {
            case JsConst.POINT_STATE_NORMAL:
                this.currentColor = normalColor;
                break;
            case JsConst.POINT_STATE_SELECTED:
                if (isShowTrack) {
                    this.currentColor = selectedColor;
                } else {
                    this.currentColor = normalColor;
                }
                break;
            case JsConst.POINT_STATE_WRONG:
                this.currentColor = errorColor;
                break;
        }
        invalidate();
    }
}
