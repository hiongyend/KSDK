package com.mrkzs.android.middle_ksdk;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;

import com.mrkzs.android.ksdk_lib.open.ISDKApi;
import com.mrkzs.android.ksdk_lib.open.ISDKCallback;
import com.mrkzs.android.ksdk_lib.open.ISDKExitCallback;
import com.mrkzs.android.ksdk_lib.open.ISDKInitCallback;
import com.mrkzs.android.ksdk_lib.open.SDKBridge;
import com.mrkzs.android.ksdk_lib.open.req.OrderReq;
import com.mrkzs.android.ksdk_lib.open.req.RoleInfoReq;

import java.util.HashMap;

/**
 * Created by KINCAI
 * <p>
 * Desc KSDK, 外部调用接口
 * <p>
 * Date 2020-05-18 16:58
 */
public class KSDK implements ISDKApi {
    private static KSDK sZSDK;
    private KSDKAuth mAuth;

    private KSDK() {
    }

    /**
     * 初始化SDK
     */
    public static synchronized ISDKApi getInstance() {
        if (sZSDK == null) {
            sZSDK = new KSDK();
        }
        return sZSDK;
    }

    @Override
    public void registerSDKCallback(ISDKCallback isdkCallback) {
        SDKBridge.get().setIsdkCallback(isdkCallback);
    }

    @Override
    public ISDKApi initSDK(Activity activity, ISDKInitCallback isdkInitCallback) {
        mAuth = new KSDKAuth(activity);
        //其他初始化 如服务等
        mAuth.initSdkApi(activity, isdkInitCallback);
        return sZSDK;
    }

    @Override
    public void setDebugMode(boolean b) {

    }

    @Override
    public void login(Activity activity) {
        if (mAuth != null) {
            mAuth.login(activity);
        }
    }

    @Override
    public void logout(Activity activity) {
        if (mAuth != null) {
            mAuth.logout(activity);
        }
    }

    @Override
    public void pay(Activity activity, OrderReq orderReq) {
        if (mAuth != null) {
            mAuth.pay(activity, orderReq);
        }
    }

    @Override
    public void setRoleData(RoleInfoReq roleInfoReq) {
        if (mAuth != null) {
            mAuth.setRoleData(roleInfoReq);
        }
    }

    @Override
    public String getSDKVersion() {
        if (mAuth != null) {
            return mAuth.getSDKVersion();
        }
        return "";
    }

    @Override
    public void onStart(Activity activity) {
        if (mAuth != null) {
            mAuth.onStart(activity);
        }
    }

    @Override
    public void onResume(Activity activity) {
        if (mAuth != null) {
            mAuth.onResume(activity);
        }
    }

    @Override
    public void onPause(Activity activity) {
        if (mAuth != null) {
            mAuth.onPause(activity);
        }
    }

    @Override
    public void onStop(Activity activity) {
        if (mAuth != null) {
            mAuth.onStop(activity);
        }
    }

    @Override
    public void onRestart(Activity activity) {
        if (mAuth != null) {
            mAuth.onRestart(activity);
        }
    }

    @Override
    public void onDestroy(Activity activity) {
        if (mAuth != null) {
            mAuth.onDestroy(activity);
        }
    }


    @Override
    public void onExit(Activity activity, ISDKExitCallback callback) {
        if (mAuth != null) {
            mAuth.onExit(activity, callback);
        }
    }

    @Override
    public void onNewIntent(Activity activity, Intent intent) {
        if (mAuth != null) {
            mAuth.onNewIntent(intent);
        }
    }

    @Override
    public void onConfigurationChanged(Activity activity, Configuration configuration) {
        if (mAuth != null) {
            mAuth.onConfigurationChanged(activity, configuration);
        }
    }

    @Override
    public void onKeyBack(Activity activity) {
        if (mAuth != null) {
            mAuth.onKeyBack(activity);
        }
    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (mAuth != null) {
            mAuth.onActivityResult(activity, requestCode, resultCode, data);
        }
    }

    @Override
    public void setExtendedData(HashMap<String, String> extend) {
        if (mAuth != null) {
            mAuth.setExtendedData(extend);
        }
    }

    /**
     * 在无法接入KSDKApplication的情况，提供给外部调用application接口
     *
     */
    @Override
    public void appOnCreate(Application application) {
        // TODO
    }

    /**
     * 在无法接入KSDKApplication的情况，提供给外部调用application接口
     */
    @Override
    public void appAttachBaseContext(Application application) {
        // TODO
    }
}
