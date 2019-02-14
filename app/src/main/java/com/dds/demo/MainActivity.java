package com.dds.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.dds.fingerprintidentify.FingerprintIdentify;
import com.dds.fingerprintidentify.base.BaseFingerprint;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button mInitFingerPrint;
    private Button mStartFingerPrint;
    private TextView mVerifyResult;
    private FingerprintIdentify mFingerprintIdentify;
    private SweetAlertDialog mAlertDialog;

    private Button mInitGestureUnlock;
    private Button mModifyGestureUnlock;
    private Button mVerifyGestureUnlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClickListener();
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (mFingerprintIdentify != null) {
            mFingerprintIdentify.cancelIdentify();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (mFingerprintIdentify != null) {
            mFingerprintIdentify.resumeIdentify();
        }
//        GestureUnlock.getInstance().init(MainActivity.this.getApplicationContext());
//        GestureUnlock.getInstance().verifyGestureUnlock(MainActivity.this);
    }

    /**
     * 初始化View
     */
    private void initView() {
        mInitFingerPrint = findViewById(R.id.btn_init_fingerprint);
        mStartFingerPrint = findViewById(R.id.btn_fingerprint);
        mVerifyResult = findViewById(R.id.tv_auth_result);
        mInitGestureUnlock = findViewById(R.id.btn_init_gesture_unlock);
        mModifyGestureUnlock = findViewById(R.id.btn_modify_gesture_unlock);
        mVerifyGestureUnlock = findViewById(R.id.btn_verify_gesture_unlock);
    }

    /**
     * 初始化按钮动作
     */
    private void initClickListener() {
        //指纹初始化
        mInitFingerPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "mInitFingerPrint onClick");
                initFingerPrint();
            }
        });

        //启动指纹认证
        mStartFingerPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i(TAG, "mStartFingerPrint onClick");
                mVerifyResult.setText("等待指纹验证结果");
                mAlertDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                        .setTitleText("指纹验证!")
                        .setContentText("请将手指放于指纹传感器上.")
                        .setCustomImage(R.drawable.icon_finger)
                        .setCancelText("取消");
                mAlertDialog.show();
                mFingerprintIdentify.startIdentify(3, new BaseFingerprint.FingerprintIdentifyListener() {
                    @Override
                    public void onSucceed() {
                        Log.i(TAG, "mFingerprintIdentify onSucceed");
                        mAlertDialog.setTitleText("验证通过")
                                .setContentText("匹配成功")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (mAlertDialog.isShowing()) {
                                    mAlertDialog.dismiss();
                                }
                            }
                        }, 1500);
                        mVerifyResult.setText("指纹验证通过");
                        Toast.makeText(MainActivity.this, "onSucceed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNotMatch(int availableTimes) {
                        Log.i(TAG, "mFingerprintIdentify onNotMatch, availableTimes: " + availableTimes);
                        mVerifyResult.setText("指纹验证不通过");
                        mAlertDialog.setTitleText("验证失败")
                                .setContentText("请重试！")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.WARNING_TYPE);
                        Toast.makeText(MainActivity.this, "onNotMatch", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed() {
                        Log.i(TAG, "mFingerprintIdentify onFailed");
                        mAlertDialog.setTitleText("警告")
                                .setContentText("指纹验证错误次数超过上限！")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.WARNING_TYPE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (mAlertDialog.isShowing()) {
                                    mAlertDialog.dismiss();
                                }
                            }
                        }, 1500);
                        mVerifyResult.setText("指纹验证错误次数超过上限");
                        Toast.makeText(MainActivity.this, "onFailed", Toast.LENGTH_SHORT).show();
                        mFingerprintIdentify.cancelIdentify();
                    }
                });
            }
        });
        // 初始化手势密码事件
        mInitGestureUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GestureUnlock.getInstance().init(MainActivity.this.getApplicationContext());
                GestureUnlock.getInstance().createGestureUnlock(MainActivity.this);
            }
        });
        // 验证手势密码事件
        mVerifyGestureUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GestureUnlock.getInstance().init(MainActivity.this.getApplicationContext());
                GestureUnlock.getInstance().verifyGestureUnlock(MainActivity.this);
            }
        });
        // 修改手势密码
        mModifyGestureUnlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GestureUnlock.getInstance().init(MainActivity.this.getApplicationContext());
                GestureUnlock.getInstance().modifyGestureUnlock(MainActivity.this);
            }
        });
    }

    /**
     * 初始化指纹识别
     */
    private void initFingerPrint() {
        mFingerprintIdentify = new FingerprintIdentify(this, new BaseFingerprint.FingerprintIdentifyExceptionListener() {
            @Override
            public void onCatchException(Throwable exception) {
                Log.i(TAG, "initFingerPrint", exception);
                mVerifyResult.setText("指纹验证初始化异常：" + exception.getMessage());
            }
        });
        if (!mFingerprintIdentify.isHardwareEnable()) {
            mVerifyResult.setText("指纹验证初始化失败：设备硬件不支持指纹识别");
            return;
        }

        if (!mFingerprintIdentify.isRegisteredFingerprint()) {
            mVerifyResult.setText("指纹验证初始化失败：未注册指纹");
            return;
        }

        if (!mFingerprintIdentify.isFingerprintEnable()) {
            mVerifyResult.setText("指纹验证初始化失败：指纹识别不可用");
            return;
        }
        mVerifyResult.setText("指纹验证初始化正常");
    }
}
