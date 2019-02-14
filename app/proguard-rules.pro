# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\androidstudio\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#==========================================基本不动区域==============================================

-optimizationpasses 5          # 指定代码的压缩级别
-dontusemixedcaseclassnames   # 是否使用大小写混合
-dontpreverify           # 混淆时是否做预校验
-verbose                # 混淆时是否记录日志
 # 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
#忽略警告
-ignorewarning

# 保持哪些类不被混淆
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}


-keep class !android.support.v7.internal.view.menu.**

-keepclasseswithmembernames class * {  # 保持 native 方法不被混淆
    native <methods>;
}


##保持自定义控件类不被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity { # 保持自定义控件类不被混淆
    public void *(android.view.View);
}
-keepclassmembers enum * {     # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable { # 保持 Parcelable 不被混淆
    public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}

# WebView
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}

-keep class * extends java.lang.annotation.Annotation {*;}
-keep class * implements java.lang.annotation.Annotation {*;}

#==========================================实体类混淆============================================
-keep public class * extends com.trustmobi.mixin.base.bean.BaseBean
-keep public class com.trustmobi.mixin.base.bean.BaseBean { *; }

-keep public class * extends com.trustmobi.mixin.longconn.response.MinaResponse
-keep public class com.trustmobi.mixin.longconn.response.MinaResponse { *; }
-keep public class com.trustmobi.mixin.longconn.response.**{*;}

-keep class com.android.mobi.**{ *; }

-keep public class com.trustmobi.mixin.utils.JsonUtil { *; }

#=========================================第三方包===============================================

#gson
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

-keep class java.lang.reflect.** { *; }


#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
#okio
-dontwarn okio.**
-keep class okio.**{*;}

#glide
 -keep public class * implements com.bumptech.glide.module.GlideModule
 -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
      **[] $VALUES;
      public *;
    }


#RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
#photoview
-keep class uk.co.senab.photoview** { *; }
-keep interface uk.co.senab.photoview** { *; }

# ripplebackground
-dontwarn com.skyfishjy.library.**
-keep public class com.skyfishjy.library.**{*;}


#gms
-keep class com.google.android.gms.** { *; }

#bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}


#baiduLBS
-keep class com.baidu.** { *; }

-keep class com.baidu.ocr.sdk.** { *; }

#bcprov-jdk16
-keep class org.bouncycastle.** { *; }

#zxing
-keep class com.google.zing.** { *; }

#jsoup
-keep class org.jsoup.** { *; }

#pinyin4j
-keep class com.hp.hpl.sparta.** { *; }

# mupdf
-keep class com.mupdf.libaray.**{ *;}

##华为推送
-keep class com.hianalytics.android.**{*;}
-keep class com.huawei.updatesdk.**{*;}
-keep class com.huawei.hms.**{*;}
-keep public class com.huawei.android.hms.agent.** extends android.app.Activity { public *; protected *; }
-keep interface com.huawei.android.hms.agent.common.INoProguard {*;}
-keep class * extends com.huawei.android.hms.agent.common.INoProguard {*;}

#tbs 文件浏览器功能
-keep class com.tencent.smtt.**{*;}
-keep class com.tencent.tbs.video.interfaces.**{*;}
-keep class com.trustmobi.mixin.base.openfile.XFileReaderActivity{*;}

# linphone
-keep class org.linphone.**{*;}

# sqlcipher
-keep  class net.sqlcipher.** {*;}
-keep  class net.sqlcipher.database.** {*;}

#mina
-keep class org.apache.mina.**{*;}

#小米推送
-keep class com.trustmobi.mixin.broadcast.XiaomiPushReceiver {*;}
-dontwarn com.xiaomi.push.**

#oti
-keep class com.taisys.oti.**{*;}
-dontwarn com.taisys.oti.**
-keep class org.simalliance.openmobileapi.**{*;}
-dontwarn org.simalliance.openmobileapi.**

#webrtc
-keep class org.appsport.apprtc.**{*;}
-dontwarn org.appsport.apprtc.**

-keep class org.webrtc.**{*;}
-keep class de.tavendo.autobahn.**{*;}

#高德地图
-keep class amap.api.**{*;}
-keep class com.autonavi.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}


#===========================================js调用类============================================


#==========================================反射类==============================================