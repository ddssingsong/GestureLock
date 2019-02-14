package com.dds.gestureunlock.vo;

import java.io.Serializable;

public class ResultVerifyVO implements Serializable {
    private boolean isFinished;

    public boolean isFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

}
