# GestureLock
手势解锁 指纹解锁，兼容绝大数手机
#### 手势解锁功能预览

* 设置手势密码

* 修改手势密码
* 验证手势密码
* 选中震动效果
* 顶部预览效果
* 可自定义密码存储位置（sp,db）
* 可设置访客密码，默认数组[0]为超级密码，数组[1]为访客密码

![功能预览](https://github.com/ddssingsong/GuestureLock/blob/master/image/image1.jpg)

1. 设置手势密码

![功能预览](https://github.com/ddssingsong/GuestureLock/blob/master/image/test1.gif)

2. 验证手势密码

![功能预览](https://github.com/ddssingsong/GuestureLock/blob/master/image/test2.gif)



#### 指纹解锁预览

![指纹解锁](https://github.com/ddssingsong/GuestureLock/blob/master/image/test3.gif)

#### 定制开发

1. 自定义密码存储，实现下面接口内容

 ```java
/**
 * 密码逻辑接口
 * Created by dds on 2019/2/13.
 * android_shuai@163.com
 */
public interface ICache {

    // 判断是否设置了手势密码
    boolean isGestureCodeSet(Context context);
    // 获取手势密码
    String getGestureCodeSet(Context context);
    
    String getGuestGestureCodeSet(Context context);
    // 清空手势密码
    void clearGestureCode(Context context);
    // 设置手势密码
    void setGestureCode(Context context, String gestureCode);
   
    void setGuestGestureCode(Context context, String gestureCode);

}

 ```

