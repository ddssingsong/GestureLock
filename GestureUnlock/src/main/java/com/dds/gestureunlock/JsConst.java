package com.dds.gestureunlock;

public class JsConst {
    public static final String CALLBACK_IS_GESTURE_CODE_SET = "uexGestureUnlock.cbIsGestureCodeSet";
    public static final String CALLBACK_VERIFY = "uexGestureUnlock.cbVerify";
    public static final String CALLBACK_CREATE = "uexGestureUnlock.cbCreate";
    public static final String ON_EVENT_OCCUR = "uexGestureUnlock.onEventOccur";

    //*/ 手势密码点的状态
    public static final int POINT_STATE_NORMAL = 0; // 正常状态

    public static final int POINT_STATE_SELECTED = 1; // 按下状态

    public static final int POINT_STATE_WRONG = 2; // 错误状态

    public static final int ERROR_CODE_NONE_GESTURE = 1;//在未设置密码的情况下进行验证密码操作
    public static final int ERROR_CODE_CANCEL_CREATE = 2;//用户取消了创建密码过程
    public static final int ERROR_CODE_CANCEL_VERIFY = 3;//用户取消了验证密码过程
    public static final int ERROR_CODE_TOO_MANY_TRY = 4;//尝试密码次数过多
    public static final int ERROR_CODE_CANCEL_OUTSIDE = 5;//插件被cancel接口强制关闭
    public static final int ERROR_CODE_UNKNOWN = 6;//发生未知错误

    public static final int EVENT_PLUGIN_INIT = 1;//插件初始化 plugin_uexGestureUnlock_plugin_init
    public static final int EVENT_START_VERIFY = 2;//开始手势密码验证 plugin_uexGestureUnlock_start_verify
    public static final int EVENT_VERIFY_ERROR = 3;//手势密码验证失败 plugin_uexGestureUnlock_verify_error
    public static final int EVENT_CANCEL_VERIFY = 4;//验证过程被用户取消 plugin_uexGestureUnlock_cancel_verify
    public static final int EVENT_VERIFY_SUCCESS = 5;//手势密码验证成功 plugin_uexGestureUnlock_verify_success
    public static final int EVENT_START_CREATE = 6;//开始手势密码设置 plugin_uexGestureUnlock_start_create
    public static final int EVENT_LENGTH_ERROR = 7;//用户输入的密码不符合长度要求 plugin_uexGestureUnlock_length_error
    public static final int EVENT_SECOND_INPUT = 8;//开始第二次输入手势密码 plugin_uexGestureUnlock_second_input
    public static final int EVENT_NOT_SAME = 9;//两次输入的密码不一致 plugin_uexGestureUnlock_not_same
    public static final int EVENT_CANCEL_CREATE = 10;//手势密码设置被用户取消 plugin_uexGestureUnlock_cancel_create
    public static final int EVENT_CREATE_SUCCESS = 11;//手势密码设置完成 plugin_uexGestureUnlock_create_success

}
