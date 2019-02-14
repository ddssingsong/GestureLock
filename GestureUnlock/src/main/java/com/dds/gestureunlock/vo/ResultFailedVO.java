package com.dds.gestureunlock.vo;

import java.io.Serializable;

public class ResultFailedVO extends ResultVerifyVO implements Serializable {
    private int errorCode;
    private String errorString;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }
}
