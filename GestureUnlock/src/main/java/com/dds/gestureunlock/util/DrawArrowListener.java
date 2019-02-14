package com.dds.gestureunlock.util;

import android.content.Context;
import android.widget.FrameLayout;

import com.dds.gestureunlock.entity.ArrowPoint;
import com.dds.gestureunlock.entity.GesturePoint;
import com.dds.gestureunlock.vo.ConfigGestureVO;
import com.dds.gestureunlock.widget.ArrowSlideLine;

import java.util.ArrayList;
import java.util.List;

public class DrawArrowListener implements OnDrawArrowListener {

    private Context mContext;
    private FrameLayout mParent;
    private List<ArrowSlideLine> list;
    private ConfigGestureVO mData;

    public DrawArrowListener(Context mContext, FrameLayout mParent, ConfigGestureVO data) {
        this.mContext = mContext;
        this.mParent = mParent;
        list = new ArrayList<>();
        this.mData = data;
    }

    @Override
    public void onDrawArrow(GesturePoint first, GesturePoint second, int blockWidth) {
        ArrowPoint startPoint, centerPoint;
        startPoint = new ArrowPoint(first.getCenterX(), first.getCenterY() - 7 * (blockWidth - 4) / 24);
        centerPoint = new ArrowPoint(first.getCenterX(), first.getCenterY());
        double angle = (Math.atan2(first.getCenterY() - second.getCenterY(),
                first.getCenterX() - second.getCenterX()) - Math.PI / 2) * 180 / Math.PI;
        ArrowSlideLine arrow = new ArrowSlideLine(mContext,
                startPoint, centerPoint, (float) angle,
                blockWidth / 18,
                mData);
        list.add(arrow);
        mParent.addView(arrow);
    }

    @Override
    public void onErrorState() {
        for (int i = 0; i < list.size(); i++) {
            ArrowSlideLine arrow = list.get(i);
            arrow.setStateError();
        }
    }

    @Override
    public void clearAllArrow() {
        for (int i = 0; i < list.size(); i++) {
            mParent.removeView(list.get(i));
        }
    }
}
