package com.mrkzs.android.middle_ksdk;

import android.app.Application;
import android.content.Context;

/**
 * Created by KINCAI
 * <p>
 * Desc 为什么吧application放到中间件
 * 1、首先它也是一个对外接口，接口固定
 * 2、不同渠道对application的接入都有不同的要求，有的调用接口，而有的要继承
 * <p>
 * Date 2020-05-18 16:54
 */
public class KSDKApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //调用sdk
        //渠道
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //调用sdk
        //渠道
    }
}
