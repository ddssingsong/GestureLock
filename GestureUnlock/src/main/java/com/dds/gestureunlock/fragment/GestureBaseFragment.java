package com.dds.gestureunlock.fragment;

import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.dds.gestureunlock.util.DrawArrowListener;
import com.dds.gestureunlock.vo.ConfigGestureVO;

public class GestureBaseFragment extends Fragment {

    protected DrawArrowListener mDrawArrowListener;
    private ConfigGestureVO mData;

    public void setParentViewFrameLayout(FrameLayout view) {
        this.mDrawArrowListener = new DrawArrowListener(this.getActivity(), view, mData);
    }

    public void setData(ConfigGestureVO data) {
        this.mData = data;
    }
}
