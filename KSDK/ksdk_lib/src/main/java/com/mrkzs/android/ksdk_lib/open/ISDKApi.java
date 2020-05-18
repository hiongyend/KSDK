package com.mrkzs.android.ksdk_lib.open;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;

import com.mrkzs.android.ksdk_lib.open.req.OrderReq;
import com.mrkzs.android.ksdk_lib.open.req.RoleInfoReq;

import java.util.HashMap;

/**
 * Created by KINCAI
 * <p>
 * Desc 定义对外游戏接入的接口
 * <p>
 * Date 2020-05-18 16:22
 */
public interface ISDKApi {
    void registerSDKCallback(ISDKCallback callback);
    ISDKApi initSDK(Activity activity, ISDKInitCallback callback);
    void setDebugMode(boolean debug);
    void login(Activity activity);
    void logout(Activity activity);
    void pay(Activity activity, OrderReq orderReq);
    void setRoleData(RoleInfoReq roleInfoReq);
    String getSDKVersion();
    void onStart(Activity activity);
    void onResume(Activity activity);
    void onPause(Activity activity);
    void onStop(Activity activity);
    void onRestart(Activity activity);
    void onDestroy(Activity activity);
    void onExit(Activity activity,ISDKExitCallback callback);
    void onNewIntent(Activity activity, Intent intent);
    void onConfigurationChanged(Activity activity, Configuration newConfig);
    void onKeyBack(Activity activity);
    void onActivityResult(Activity activity,int requestCode, int resultCode, Intent data);
    void setExtendedData(HashMap<String, String> extend);
    void appOnCreate(Application application);
    void appAttachBaseContext(Application application);
}
