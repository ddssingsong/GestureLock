package com.dds.gestureunlock.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.dds.gestureunlock.entity.GesturePoint;
import com.dds.gestureunlock.util.GestureUtil;
import com.dds.gestureunlock.util.OnDrawArrowListener;
import com.dds.gestureunlock.vo.ConfigGestureVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 手势密码容器类
 */
public class GestureContentView extends FrameLayout {

    /**
     * 用来控制圆圈的大小
     */
    private int baseNum = 6;

    private int[] screenDisplay;

    /**
     * 每个点区域的宽度
     */
    private int blockWidth;
    /**
     * 声明一个集合用来封装坐标集合
     */
    private List<GesturePoint> list;
    private Context context;
    private GestureDrawLine gestureDrawline;

    private int circleWidth;

    public GestureContentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 包含9个ImageView的容器，初始化
     */
    public GestureContentView(Context context, boolean isVerify, String[] passWord,
                              GestureDrawLine.GestureCallBack callBack,
                              OnDrawArrowListener listener,
                              ConfigGestureVO data) {
        super(context);
        screenDisplay = GestureUtil.getScreenDisplay(context);
        blockWidth = screenDisplay[0] / 4;
        this.list = new ArrayList<>();
        this.context = context;
        // 小圆圈大小计算
        circleWidth = blockWidth - 2 * blockWidth / baseNum;
        // 添加9个图标
        addChild(data);
        // 初始化一个可以画线的view
        gestureDrawline = new GestureDrawLine(context, list, isVerify, passWord,
                callBack, listener, circleWidth, data);


    }

    private void addChild(ConfigGestureVO data) {
        for (int i = 0; i < 9; i++) {
            CircleImageView image = new CircleImageView(context, circleWidth,
                    data.getNormalThemeColor(),
                    data.getSelectedThemeColor(),
                    data.getErrorThemeColor(), data.isShowTrack());
            this.addView(image);
            invalidate();
            // 第几行
            int row = i / 3;
            // 第几列
            int col = i % 3;
            // 定义点的每个属性
            int leftX = col * blockWidth + blockWidth / baseNum;
            int topY = row * blockWidth + blockWidth / baseNum;
            int rightX = col * blockWidth + blockWidth - blockWidth / baseNum;
            int bottomY = row * blockWidth + blockWidth - blockWidth / baseNum;
            GesturePoint p = new GesturePoint(leftX, rightX, topY, bottomY, image, i + 1);
            this.list.add(p);
        }
    }

    public void setParentView(ViewGroup parent) {
        // 得到屏幕的宽度
        int width = screenDisplay[0] * 3 / 4;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, width);
        this.setLayoutParams(layoutParams);
        gestureDrawline.setLayoutParams(layoutParams);
        parent.addView(gestureDrawline);
        parent.addView(this);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            //第几行
            int row = i / 3;
            //第几列
            int col = i % 3;
            View v = getChildAt(i);
            v.layout(col * blockWidth + blockWidth / baseNum,
                    row * blockWidth + blockWidth / baseNum,
                    col * blockWidth + blockWidth - blockWidth / baseNum,
                    row * blockWidth + blockWidth - blockWidth / baseNum);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 遍历设置每个子view的大小
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            v.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * 保留路径delayTime时间长
     */
    public void clearDrawLineState(long delayTime, boolean flag) {
        gestureDrawline.clearDrawLineState(delayTime, flag);
    }
}
