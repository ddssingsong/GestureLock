package com.dds.demo.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.dds.demo.R;

/**
 * 一个酷炫的 Dialog
 * Created by dds on 2019/2/14.
 * android_shuai@163.com
 */
public class SweetAlertDialog extends Dialog implements View.OnClickListener {


    public SweetAlertDialog(@NonNull Context context) {
        super(context, R.style.alert_dialog);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog);
    }

    @Override
    public void onClick(View v) {

    }
}
