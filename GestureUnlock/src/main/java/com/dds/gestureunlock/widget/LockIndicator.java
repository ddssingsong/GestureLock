package com.dds.gestureunlock.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.dds.gestureunlock.vo.ConfigGestureVO;

/**
 * 屏幕上方：手势密码图案提示
 */
public class LockIndicator extends View {
    private int numRow = 3;    // 行
    private int numColumn = 3; // 列
    private int patternWidth = 40;
    private int patternHeight = 40;
    private int f = 5;
    private int g = 5;
    private static final int strokeWidth = 3;
    private Paint paint = null;
    private int normalColor = Color.BLACK;
    private int pressedColor = Color.GREEN;
    private int errorColor = Color.RED;
    private String lockPassStr; // 手势密码
    private int width;
    private int height;
    private int currentPressedColor;

    public LockIndicator(Context paramContext) {
        super(paramContext);
    }

    public LockIndicator(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet, 0);
    }

    public LockIndicator(Context context, ConfigGestureVO data) {
        super(context);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokeWidth);
        this.f = (patternWidth / 4);
        this.g = (patternHeight / 4);
        width = numColumn * patternHeight + this.g
                * (-1 + numColumn);
        height = numRow * patternWidth + this.f
                * (-1 + numRow);
        this.normalColor = data.getNormalThemeColor();
        this.pressedColor = data.getSelectedThemeColor();
        this.errorColor = data.getErrorThemeColor();
        this.currentPressedColor = pressedColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int baseX = width / 3;
        int baseY = height / 3;
        int baseRadius = (baseX > baseY ? baseY : baseX);
        int radius = baseRadius / 3;
        // 绘制3*3的图标
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numColumn; j++) {
                int x = baseX / 2 + baseX * j;
                int y = baseY / 2 + baseY * i;
                canvas.save();
                String curNum = String.valueOf(numColumn * i + (j + 1));
                if (!TextUtils.isEmpty(lockPassStr)) {
                    if (!lockPassStr.contains(curNum)) {
                        // 未选中
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setColor(normalColor);
                        canvas.drawCircle(x, y, radius, paint);

                    } else {
                        // 被选中
                        paint.setColor(currentPressedColor);
                        paint.setStyle(Paint.Style.FILL);
                        canvas.drawCircle(x, y, radius, paint);
                    }
                } else {
                    // 重置状态
                    paint.setColor(normalColor);
                    paint.setStyle(Paint.Style.STROKE);
                    canvas.drawCircle(x, y, radius, paint);
                }
                canvas.restore();
            }
        }

    }

    @Override
    protected void onMeasure(int paramInt1, int paramInt2) {
        setMeasuredDimension(numColumn * patternHeight + this.g
                * (-1 + numColumn), numRow * patternWidth + this.f
                * (-1 + numRow));
    }

    /**
     * 请求重新绘制
     *
     * @param paramString 手势密码字符序列
     */
    public void setPath(String paramString) {
        if (TextUtils.isEmpty(lockPassStr)) {
            this.currentPressedColor = pressedColor;
        }
        lockPassStr = paramString;
        invalidate();
    }

    public void setErrorState() {
        this.currentPressedColor = errorColor;
        invalidate();
    }
}
