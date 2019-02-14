package com.dds.gestureunlock.util;

import com.dds.gestureunlock.entity.GesturePoint;

public interface OnDrawArrowListener {
    void onDrawArrow(GesturePoint first, GesturePoint second, int blockWidth);

    void onErrorState();

    void clearAllArrow();
}
