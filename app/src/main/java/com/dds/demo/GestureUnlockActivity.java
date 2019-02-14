package com.dds.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dds.gestureunlock.fragment.GestureCreateFragment;
import com.dds.gestureunlock.fragment.GestureVerifyFragment;
import com.dds.gestureunlock.vo.ConfigGestureVO;
import com.dds.gestureunlock.vo.ResultVerifyVO;

/**
 * File Description: 手势密码解锁认证Activity
 */
public class GestureUnlockActivity extends AppCompatActivity {
    public Toolbar toolbar;

    private Fragment currentFragment;
    private GestureCreateFragment mGestureCreateFragment;
    private GestureVerifyFragment mGestureVerifyFragment;


    public static final int TYPE_GESTURE_CREATE = 1;
    public static final int TYPE_GESTURE_VERIFY = 2;

    public static void openActivity(Context activity, int type) {
        Intent intent = new Intent(activity, GestureUnlockActivity.class);
        intent.putExtra("type", type);
        if (!(activity instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_unlock);
        initView();
        initVar();
        initListener();


    }


    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    private void initVar() {
        Intent intent = getIntent();
        int type = intent.getIntExtra("type", 2);
        if (type == TYPE_GESTURE_CREATE) {
            //初始化手势密码
            showCreateGestureLayout();
        } else if (type == TYPE_GESTURE_VERIFY) {
            //手势密码认证
            showVerifyGestureLayout();
        } else {
            //无效操作，退出
            finish();
        }
    }

    private void initListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GestureUnlockActivity.this.finish();
            }
        });
    }

    /**
     * 显示初始化手势密码的布局
     */
    private void showCreateGestureLayout() {
        if (mGestureCreateFragment == null) {
            mGestureCreateFragment = new GestureCreateFragment();
            mGestureCreateFragment.setGestureCreateListener(new GestureCreateFragment.GestureCreateListener() {

                @Override
                public void onCreateFinished(String gestureCode) {
                    // 创建手势密码完成
                    GestureUnlock.getInstance().setGestureCode(GestureUnlockActivity.this, gestureCode);
                    GestureUnlockActivity.this.finish();
                }

                @Override
                public void onCreateFailed(ResultVerifyVO result) {
                    // 创建手势密码失败
                }

                @Override
                public void closeLayout() {
                    // 关闭
                }

                @Override
                public void onCancel() {
                    // 取消创建手势密码
                    GestureUnlockActivity.this.finish();
                }

                @Override
                public void onEventOccur(int eventCode) {

                }
            });
        }
        mGestureCreateFragment.setData(ConfigGestureVO.defaultConfig());
        safeAddFragment(mGestureCreateFragment, R.id.fragment_container, "GestureCreateFragment");
    }

    /**
     * 显示验证手势密码的布局
     */
    private void showVerifyGestureLayout() {
        if (mGestureVerifyFragment == null) {
            mGestureVerifyFragment = new GestureVerifyFragment();
            mGestureVerifyFragment.setGestureVerifyListener(new GestureVerifyFragment.GestureVerifyListener() {
                @Override
                public void onVerifyResult(ResultVerifyVO result) {

                }

                @Override
                public void closeLayout() {

                }

                @Override
                public void onStartCreate() {

                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onEventOccur(int eventCode) {

                }
            });
        }
        mGestureVerifyFragment.setData(ConfigGestureVO.defaultConfig());
        safeAddFragment(mGestureVerifyFragment, R.id.fragment_container, "GestureVerifyFragment");
        mGestureVerifyFragment.setGestureCodeData(GestureUnlock.getInstance().getGestureCodeSet(this));
    }

    /**
     * 检查fragment是否已经加入，防止重复
     */
    private void safeAddFragment(Fragment fragment, int id, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //优先检查，fragment是否存在，避免重叠
        Fragment tempFragment = fragmentManager.findFragmentByTag(tag);
        if (tempFragment != null) {
            fragment = tempFragment;
        }
        if (fragment.isAdded()) {
            addOrShowFragment(fragmentTransaction, fragment, id, tag);
        } else {
            if (currentFragment != null && currentFragment.isAdded()) {
                fragmentTransaction.hide(currentFragment).add(id, fragment, tag).commit();
            } else {
                fragmentTransaction.add(id, fragment, tag).commit();
            }
            currentFragment = fragment;
        }
    }

    /**
     * 添加或者直接显示
     *
     * @param transaction
     * @param fragment
     * @param containerLayoutId
     * @param tag
     */
    private void addOrShowFragment(FragmentTransaction transaction, Fragment fragment, int containerLayoutId, String tag) {
        if (currentFragment == fragment)
            return;
        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment).add(containerLayoutId, fragment, tag).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment.setUserVisibleHint(false);
        currentFragment = fragment;
        currentFragment.setUserVisibleHint(true);
    }


}
