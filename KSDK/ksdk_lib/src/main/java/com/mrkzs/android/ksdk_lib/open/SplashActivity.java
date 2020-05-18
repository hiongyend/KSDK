package com.mrkzs.android.ksdk_lib.open;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mrkzs.android.ksdk_common.utils.DeviceUtil;

/**
 * Created by KINCAI
 * <p>
 * Desc 模拟闪屏页
 * <p>
 * Date 2020-05-18 17:53
 */
public class SplashActivity extends Activity {
    private int delayed = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashBefore();
    }
    protected void splashBefore() {
        splash();
    }
    protected void splash() {
        FrameLayout layout = new FrameLayout(this);
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        layout.addView(imageView);
        setContentView(layout);
        Log.e("SplashActivity", "SplashActivity-onCreate()");

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashAfter();
            }
        },delayed);
    }


    protected void splashAfter() {
        //android manifest配置的游戏入口跳转
        Object ll_launcher_activity = DeviceUtil.getMetaData(this, "KSDK_LAUNCHER_ACTIVITY");
        try {
            if(!TextUtils.isEmpty((String) ll_launcher_activity)) {
                Class<?> aClass = Class.forName((String) ll_launcher_activity);
                Intent intent = new Intent(this, aClass);
                startActivity(intent);
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("SplashActivity", "splashAfter exception "+e.getMessage());
        }
    }
}
