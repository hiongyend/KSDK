package com.mrkzs.android.ksdk_lib.open;

import com.mrkzs.android.ksdk_lib.open.resp.UserResp;

/**
 * Created by KINCAI
 * <p>
 * Desc 全局回调接口
 * <p>
 * Date 2020-05-18 15:37
 */
public interface ISDKCallback {
    void onLoginSuccess(UserResp userInfo);
    void onLoginFailure(int errorCode, String errorMsg);
    void onLogout();
    void onPayResponse();
    void onPayFailure(int errorCode, String errorMsg);
}
