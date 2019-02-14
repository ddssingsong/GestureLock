package com.dds.gestureunlock.vo;

import java.io.Serializable;

public class CreateGestureVO implements Serializable {
    private boolean isNeedVerifyBeforeCreate = true;

    public boolean isNeedVerifyBeforeCreate() {
        return isNeedVerifyBeforeCreate;
    }

    public void setIsNeedVerifyBeforeCreate(boolean isNeedVerifyBeforeCreate) {
        this.isNeedVerifyBeforeCreate = isNeedVerifyBeforeCreate;
    }
}
