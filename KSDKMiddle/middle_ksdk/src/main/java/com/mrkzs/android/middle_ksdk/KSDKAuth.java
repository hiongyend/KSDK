package com.mrkzs.android.middle_ksdk;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;

import com.mrkzs.android.ksdk_lib.open.ISDKExitCallback;
import com.mrkzs.android.ksdk_lib.open.ISDKInitCallback;
import com.mrkzs.android.ksdk_lib.open.SDKBridge;
import com.mrkzs.android.ksdk_lib.open.req.OrderReq;
import com.mrkzs.android.ksdk_lib.open.req.RoleInfoReq;

import java.util.HashMap;

/**
 * Created by KINCAI
 * <p>
 * Desc 中间件渠道接入
 * <p>
 * Date 2020-05-18 17:04
 */
public class KSDKAuth {
    private Activity mContext;

    public KSDKAuth(Activity activity) {
        this.mContext = activity;
        //配置初始化，检查配置等
    }

    public void initSdkApi(Activity activity, ISDKInitCallback isdkInitCallback) {
        //根据接入渠道不同调用渠道的初始化
        SDKBridge.get().initSDK(activity, isdkInitCallback);
    }

    public void login(Activity activity) {
        //调用渠道sdk的登录
        SDKBridge.get().login(activity);

    }

    public void logout(Activity activity) {
        //调用渠道注销
    }

    public void pay(Activity activity, OrderReq orderReq) {
        //调用支付 先获取订单
        SDKBridge.get().pay(activity);
    }

    public void setRoleData(RoleInfoReq roleInfoReq) {
    }

    public String getSDKVersion() {
        return null;
    }

    public void onStart(Activity activity) {
    }

    public void onResume(Activity activity) {
    }

    public void onPause(Activity activity) {
    }

    public void onStop(Activity activity) {
    }

    public void onRestart(Activity activity) {
    }

    public void onDestroy(Activity activity) {
    }

    public void onExit(Activity activity, ISDKExitCallback callback) {
        //调用对应渠道的退出接口
        SDKBridge.get().exitGame(activity, callback);
    }

    public void onNewIntent(Intent intent) {
    }

    public void onConfigurationChanged(Activity activity, Configuration configuration) {
    }

    public void onKeyBack(Activity activity) {
    }

    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
    }

    public void setExtendedData(HashMap<String, String> extend) {
    }
}
