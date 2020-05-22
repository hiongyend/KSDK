// IPayRemoteCallback.aidl
package com.mrkzs.android.ksdk_payplugin;

import android.os.Bundle;

interface IPayRemoteCallback {
    void startActivity(String packname, String className, int type, String orderId);
    void payResponse(int code, String msg);
}
