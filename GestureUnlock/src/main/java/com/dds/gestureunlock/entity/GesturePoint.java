package com.dds.gestureunlock.entity;

import com.dds.gestureunlock.widget.CircleImageView;

public class GesturePoint {
    /**
     * 左边x的值
     */
    private int leftX;
    /**
     * 右边x的值
     */
    private int rightX;
    /**
     * 上边y的值
     */
    private int topY;
    /**
     * 下边y的值
     */
    private int bottomY;
    /**
     * 这个点对应的ImageView控件
     */
    private CircleImageView image;

    /**
     * 中心x值
     */
    private int centerX;

    /**
     * 中心y值
     */
    private int centerY;

    /**
     * 状态值
     */
    private int pointState;

    /**
     * 代表这个Point对象代表的数字，从1开始(直接感觉从1开始)
     */
    private int num;

    public GesturePoint(int leftX, int rightX, int topY, int bottomY,
                        CircleImageView image, int num) {
        super();
        this.leftX = leftX;
        this.rightX = rightX;
        this.topY = topY;
        this.bottomY = bottomY;
        this.image = image;

        this.centerX = (leftX + rightX) / 2;
        this.centerY = (topY + bottomY) / 2;

        this.num = num;
    }

    public int getLeftX() {
        return leftX;
    }

    public void setLeftX(int leftX) {
        this.leftX = leftX;
    }

    public int getRightX() {
        return rightX;
    }

    public void setRightX(int rightX) {
        this.rightX = rightX;
    }

    public int getTopY() {
        return topY;
    }

    public void setTopY(int topY) {
        this.topY = topY;
    }

    public int getBottomY() {
        return bottomY;
    }

    public void setBottomY(int bottomY) {
        this.bottomY = bottomY;
    }

    public CircleImageView getImage() {
        return image;
    }

    public void setImage(CircleImageView image) {
        this.image = image;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getPointState() {
        return pointState;
    }

    public void setPointState(int state) {
        pointState = state;
        this.image.setCurrentState(state);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + bottomY;
        result = prime * result + ((image == null) ? 0 : image.hashCode());
        result = prime * result + leftX;
        result = prime * result + rightX;
        result = prime * result + topY;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GesturePoint other = (GesturePoint) obj;
        if (bottomY != other.bottomY)
            return false;
        if (image == null) {
            if (other.image != null)
                return false;
        } else if (!image.equals(other.image))
            return false;
        if (leftX != other.leftX)
            return false;
        if (rightX != other.rightX)
            return false;
        if (topY != other.topY)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Point [leftX=" + leftX + ", rightX=" + rightX + ", topY="
                + topY + ", bottomY=" + bottomY + "]";
    }
}
