package com.mrkzs.android.middle_ksdk;

/**
 * Created by KINCAI
 * <p>
 * Desc ysdk应用宝接入的 需要在闪屏接入操作 具体不写了
 * <p>
 * Date 2020-05-18 17:47
 */
public class SplashActivity extends com.mrkzs.android.ksdk_lib.open.SplashActivity {

    @Override
    protected void splashBefore() {
        //在这里一系列的操作ysdk需要的  然后在跳转我们的闪屏

        splash();
    }
}
